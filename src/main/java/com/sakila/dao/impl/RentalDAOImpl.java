/**
 * 
 */
package com.sakila.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sakila.dao.RentalDAO;
import com.sakila.db.util.SKUtility;
import com.sakila.modal.Rental;
import com.sakila.vo.CustomerVO;
import com.sakila.vo.InventoryVO;
import com.sakila.vo.RentalVO;
import com.sakila.vo.StaffVO;

/**
 * @author bc887d
 *
 */
@Repository("rentalDAO")
public class RentalDAOImpl implements RentalDAO {

	private static final Logger logger = LoggerFactory.getLogger(RentalDAOImpl.class);

	@PersistenceContext
	public EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sakila.dao.RentalDAO#getRental()
	 */
	@Override
	public List<RentalVO> getRental() {
		logger.info("... Entered into getRental() of RentalDAOImpl ...");
		List<RentalVO> rentalVOList = new ArrayList<RentalVO>();

		Session session = (Session) entityManager.getDelegate();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Rental> criteria = builder.createQuery(Rental.class);
		Root<Rental> contactRoot = criteria.from(Rental.class);
		criteria.select(contactRoot);

		Query<Rental> query = session.createQuery(criteria);
		query.setFirstResult(0);
		query.setMaxResults(10);

		List<Rental> rentalList = query.getResultList();

		rentalList.stream().forEach(rental1 -> {
			RentalVO rental = new RentalVO();

			rental.setRentalId(rental1.getRentalId());
			rental.setRentalDate(rental1.getRentalDate());

			InventoryVO inventory = SKUtility.getInventoryDetailsById(session, rental1.getInventory().getInventoryId());
			rental.setInventory(inventory);

			CustomerVO customer = SKUtility.getCustomerDetailsById(session, rental1.getCustomer().getCustomerId());
			rental.setCustomer(customer);

			rental.setReturnDate(rental1.getRentalDate());

			StaffVO staff = SKUtility.getStaffDetailsById(session, rental1.getStaff().getStaffId());
			rental.setStaff(staff);

			rental.setLastUpdate(rental1.getLastUpdate());

			rentalVOList.add(rental);
		});
		return rentalVOList;
	}

}
