/**
 * 
 */
package com.sakila.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.sakila.dao.CategoryDAO;
import com.sakila.db.util.SKUtility;
import com.sakila.modal.Category;
import com.sakila.vo.CategoryVO;

/**
 * @author bc887d
 *
 */
@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {

	private static final Logger logger = LoggerFactory.getLogger(CategoryDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sakila.dao.CategoryDAO#getCategories()
	 */
	@Override
	public List<CategoryVO> getCategories() {
		logger.info("... Entered into getCategories() of CategoryDAOImpl ...");
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
		logger.info("... Entered into getCategoriesByPagination() of CategoryDAOImpl ...");

		int pageNumber = pageable.getPageNumber();
		int pageSize = pageable.getPageSize();

		List<CategoryVO> categoryVOList = new ArrayList<CategoryVO>();

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		countQuery.select(criteriaBuilder.count(countQuery.from(Category.class)));
		Long countResults = entityManager.createQuery(countQuery).getSingleResult();
		logger.info(" Total Count : {} ", countResults);
		int lastPageNumber = 0;
		if (pageNumber - 1 == 0) {
			lastPageNumber = 0;
		} else {
			lastPageNumber = pageNumber - 1;
			lastPageNumber = lastPageNumber * pageSize;
		}
		logger.info("Last Page Number : {}, Page Number : {}, Page Size : {} ", lastPageNumber, pageNumber, pageSize);
		CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
		Root<Category> from = criteriaQuery.from(Category.class);
		CriteriaQuery<Category> select = criteriaQuery.select(from);
		TypedQuery<Category> typedQuery = entityManager.createQuery(select);
		typedQuery.setFirstResult(lastPageNumber);
		typedQuery.setMaxResults(pageSize);
		logger.info(" Result Size : {} ", typedQuery.getResultList().size());
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
		logger.info("... Entered into getCategoryDetailsById() of CategoryDAOImpl ...");
		Session session = (Session) entityManager.getDelegate();
		CategoryVO category = SKUtility.getCategoryDetailsById(session, categoryId);
		logger.info("Category Obj : " + category);
		return category;
	}

	@Override
	public int saveCategory(CategoryVO category) {
		logger.info("... Entered into saveCategory() of CategoryDAOImpl ...category : {}", category);
		int categoryId = 0;
		Session session = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			session = entityManager.unwrap(Session.class);
			Category categoryObj = new Category();
			categoryObj.setName(category.getName());
			categoryObj.setLastUpdate(sdf.format(new Date()));
			categoryId = (int) session.save(categoryObj);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		} // finally
		return categoryId;
	}

	@Override
	public boolean updateCategory(CategoryVO category) {
		logger.info("... Entered into updateCategory() of CategoryDAOImpl ...category : {}", category);
		boolean isUpdate = false;
		Session session = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			session = entityManager.unwrap(Session.class);
			Category categoryObj = new Category();
			categoryObj.setCategoryId(category.getCategoryId());
			categoryObj.setName(category.getName());
			categoryObj.setLastUpdate(sdf.format(new Date()));
			session.update(categoryObj);
			isUpdate = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		} // finally
		return isUpdate;
	}

	@Override
	public boolean deleteCategory(int categoryId) {
		logger.info("... Entered into deleteCategory() of CategoryDAOImpl ...categoryId : {}", categoryId);
		boolean isDeleted = false;
		Session session = null;
		try {
			session = entityManager.unwrap(Session.class);
			Category categoryObj = new Category();
			categoryObj.setCategoryId(categoryId);
			session.delete(categoryObj);
			isDeleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		} // finally
		return isDeleted;
	}

}
