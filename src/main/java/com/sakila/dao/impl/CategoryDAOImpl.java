package com.sakila.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.sakila.dao.CategoryDAO;
import com.sakila.db.util.SKUtility;
import com.sakila.modal.Category;
import com.sakila.vo.CategoryVO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sakila.dao.CategoryDAO#getCategories()
	 */
	@Override
	public List<CategoryVO> getCategories() {
		log.info("... Entered into getCategories() of CategoryDAOImpl ...");
		List<CategoryVO> categoryVOList = new ArrayList<CategoryVO>();

		Session session = (Session) entityManager.getDelegate();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
		Root<Category> contactRoot = criteriaQuery.from(Category.class);
		criteriaQuery.select(contactRoot);

		Query<Category> query = session.createQuery(criteriaQuery);
		List<Category> categoryList = query.getResultList();
		categoryList.stream().forEach(category1 -> {
			CategoryVO category = new CategoryVO();
			category.setCategoryId(category1.getCategoryId());
			category.setName(category1.getName());
			category.setLastUpdate(category1.getLastUpdate());
			categoryVOList.add(category);
		});
		return categoryVOList;
	}

	@Override
	public List<CategoryVO> getCategoriesByPagination(PageRequest pageable) {
		log.info("... Entered into getCategoriesByPagination() of CategoryDAOImpl ...");

		int pageNumber = pageable.getPageNumber();
		int pageSize = pageable.getPageSize();

		List<CategoryVO> categoryVOList = new ArrayList<CategoryVO>();

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		countQuery.select(criteriaBuilder.count(countQuery.from(Category.class)));
		Long countResults = entityManager.createQuery(countQuery).getSingleResult();
		log.info(" Total Count : {} ", countResults);
		int lastPageNumber = 0;
		if (pageNumber - 1 == 0) {
			lastPageNumber = 0;
		} else {
			lastPageNumber = pageNumber - 1;
			lastPageNumber = lastPageNumber * pageSize;
		}
		log.info("Last Page Number : {}, Page Number : {}, Page Size : {} ", lastPageNumber, pageNumber, pageSize);
		CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
		Root<Category> from = criteriaQuery.from(Category.class);
		CriteriaQuery<Category> select = criteriaQuery.select(from);
		TypedQuery<Category> typedQuery = entityManager.createQuery(select);
		typedQuery.setFirstResult(lastPageNumber);
		typedQuery.setMaxResults(pageSize);
		log.info(" Result Size : {} ", typedQuery.getResultList().size());
		List<Category> categoryList = typedQuery.getResultList();
		categoryList.stream().forEach(categoryObj -> {
			CategoryVO category = new CategoryVO();
			category.setCategoryId(categoryObj.getCategoryId());
			category.setName(categoryObj.getName());
			category.setLastUpdate(categoryObj.getLastUpdate());
			categoryVOList.add(category);
		});

//		new PagedResult<>(typedQuery.getResultList()
//                .stream()
//                .map(CategoryVO::categoryVO)
//                .sorted(comparing(SomethingDto::getDatum))
//                .collect(toList()), somethings.getTotalElements(), somethings.getTotalPages();

		return categoryVOList;

	}

	@Override
	public CategoryVO getCategoryDetailsById(int categoryId) {
		log.info("... Entered into getCategoryDetailsById() of CategoryDAOImpl ...");
		Session session = (Session) entityManager.getDelegate();
		CategoryVO category = SKUtility.getCategoryDetailsById(session, categoryId);
		log.info("Category Obj : " + category);
		return category;
	}

	@Override
	public int saveCategory(CategoryVO category) {
		log.info("... Entered into saveCategory() of CategoryDAOImpl ...category : {}", category);
		int categoryId = 0;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Category categoryObj = new Category();
			categoryObj.setName(category.getName());
			categoryObj.setLastUpdate(sdf.format(new Date()));

			// Use the modern JPA spec method via the injected entityManager
			entityManager.persist(categoryObj);

			// Retrieve the generated ID directly from the managed object
			// Replace getCategoryId() with your exact getter name if different
			categoryId = categoryObj.getCategoryId();
		} catch (Exception e) {
			log.error("Exception: {}", e);
			throw e; // Recommended practice to let Spring handle transaction rollbacks on exceptions
		}

		return categoryId;
	}

	@Override
	public boolean updateCategory(CategoryVO category) {
		log.info("... Entered into updateCategory() of CategoryDAOImpl ...category : {}", category);
		boolean isUpdate = false;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Category categoryObj = new Category();
			categoryObj.setCategoryId(category.getCategoryId());
			categoryObj.setName(category.getName());
			categoryObj.setLastUpdate(sdf.format(new Date()));

			// Use merge instead of update for detached entities
			entityManager.merge(categoryObj);

			isUpdate = true;
		} catch (Exception e) {
			log.error("Exception: {}", e);
			throw e; // RETHROW to ensure Spring triggers a transaction rollback if needed
		}

		return isUpdate;
	}

	@Override
	public boolean deleteCategory(int categoryId) {
		log.info("... Entered into deleteCategory() of CategoryDAOImpl ...categoryId : {}", categoryId);
		boolean isDeleted = false;

		try {
			// 1. Fetch the managed entity from the context first
			Category categoryObj = entityManager.find(Category.class, categoryId);

			if (categoryObj != null) {
				// 2. Remove the managed entity using the standard JPA method
				entityManager.remove(categoryObj);
				isDeleted = true;
			} else {
				log.warn("Category with ID {} not found for deletion", categoryId);
			}
		} catch (Exception e) {
			log.error("Exception: {}", e);
			throw e; // Rethrow to ensure Spring triggers a transaction rollback
		}

		return isDeleted;
	}

}
