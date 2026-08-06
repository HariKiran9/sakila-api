package com.sakila.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.sakila.dao.InventoryDAO;
import com.sakila.db.util.SKUtility;
import com.sakila.modal.Inventory;
import com.sakila.vo.FilmVO;
import com.sakila.vo.InventoryVO;
import com.sakila.vo.StoreVO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("inventoryDAO")
public class InventoryDAOImpl implements InventoryDAO {

	@PersistenceContext
	private EntityManager entityManagerFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sakila.dao.InventoryDAO#getInventory()
	 */
	@Override
	public List<InventoryVO> getInventory() {
		log.info("... Entered into getActors() of InventoryDAOImpl ...");
		List<InventoryVO> inventoryList = new ArrayList<InventoryVO>();

		Session session = (Session) entityManagerFactory.getDelegate();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Inventory> criteria = builder.createQuery(Inventory.class);
		Root<Inventory> contactRoot = criteria.from(Inventory.class);
		criteria.select(contactRoot);

		Query<Inventory> query = session.createQuery(criteria);
		List<Inventory> list = query.getResultList();

		list.stream().forEach(inventory1 -> {
			InventoryVO inventory = new InventoryVO();
			inventory.setInventoryId(inventory1.getInventoryId());

			FilmVO film = SKUtility.getFilmDetailsById(session, inventory1.getFilm().getFilmId());
			inventory.setFilm(film);

			StoreVO store = SKUtility.getStoreDetailsById(session, inventory1.getStore().getStoreId());
			inventory.setStore(store);

			inventory.setLastUpdate(inventory1.getLastUpdate());

			inventoryList.add(inventory);
		});
		return inventoryList;
	}

}
