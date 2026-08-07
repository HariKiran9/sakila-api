package com.sakila.dao.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.sakila.dao.CategoryDAO;
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

	// Thread-safe date formatting asset replacement for SimpleDateFormat
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@Override
	public List<CategoryVO> getCategories() {
		log.info("... Entered into getCategories() of CategoryDAOImpl ...");
		List<CategoryVO> categoryVOList = new ArrayList<>();

		// Fixed: Removed explicit Hibernate Session cast; using universal standard
		// CriteriaBuilder
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
		Root<Category> contactRoot = criteriaQuery.from(Category.class);
		criteriaQuery.select(contactRoot);

		TypedQuery<Category> query = entityManager.createQuery(criteriaQuery);
		List<Category> categoryList = query.getResultList();
		categoryList.forEach(getCategoryConsumer(categoryVOList));
		return categoryVOList;
	}

	private static @NonNull Consumer<Category> getCategoryConsumer(List<CategoryVO> categoryVOList) {
		return category1 -> {
			CategoryVO category = new CategoryVO();
			category.setCategoryId(category1.getCategoryId());
			category.setName(category1.getName());
			category.setLastUpdate(category1.getLastUpdate());
			categoryVOList.add(category);
		};
	}

	@Override
	public List<CategoryVO> getCategoriesByPagination(PageRequest pageable) {
		log.info("... Entered into getCategoriesByPagination() of CategoryDAOImpl ...");

		List<CategoryVO> categoryVOList = new ArrayList<>();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		// Count Query Logic Setup
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		countQuery.select(criteriaBuilder.count(countQuery.from(Category.class)));
		Long countResults = entityManager.createQuery(countQuery).getSingleResult();
		log.info(" Total Count : {} ", countResults);

		// Fixed: Replaced custom fragile pagination offsets with safe Spring framework
		// defaults
		long offset = pageable.getOffset();
		int pageSize = pageable.getPageSize();
		log.info("Calculated Pagination Settings -> Offset: {}, Limit/PageSize: {} ", offset, pageSize);

		CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
		Root<Category> from = criteriaQuery.from(Category.class);
		CriteriaQuery<Category> select = criteriaQuery.select(from);

		TypedQuery<Category> typedQuery = entityManager.createQuery(select);
		typedQuery.setFirstResult((int) offset);
		typedQuery.setMaxResults(pageSize);

		List<Category> categoryList = typedQuery.getResultList();
		log.info(" Fetched Result Size : {} ", categoryList.size());

		categoryList.forEach(getCategoryConsumer(categoryVOList));
		return categoryVOList;
	}

	@Override
	public CategoryVO getCategoryDetailsById(int categoryId) {
		log.info("... Entered into getCategoryDetailsById() of CategoryDAOImpl for ID: {}", categoryId);

		// Fixed: Replaced legacy delegate utilities with native, safe entity manager
		// operations
		Category categoryObj = entityManager.find(Category.class, categoryId);
		if (categoryObj == null) {
			log.warn("No category found matching target ID: {}", categoryId);
			return null;
		}

		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryId(categoryObj.getCategoryId());
		categoryVO.setName(categoryObj.getName());
		categoryVO.setLastUpdate(categoryObj.getLastUpdate());
		return categoryVO;
	}

	@Override
	public int saveCategory(CategoryVO category) {
		log.info("... Entered into saveCategory() of CategoryDAOImpl ...");
		if (category == null) {
			throw new IllegalArgumentException("Category database payroll payload cannot be null");
		}

		try {
			Category categoryObj = new Category();
			categoryObj.setName(category.getName());

			// Fixed: Concurrency safe date population step using java.time API
			categoryObj.setLastUpdate(LocalDateTime.now());

			entityManager.persist(categoryObj);
			return categoryObj.getCategoryId();
		} catch (Exception e) {
			log.error("Failed to commit persist transaction inside saveCategory", e);
			throw e;
		}
	}

	@Override
	public boolean updateCategory(CategoryVO category) {
		log.info("... Entered into updateCategory() of CategoryDAOImpl ...");
		if (category == null) {
			return false;
		}

		try {
			Category categoryObj = new Category();
			categoryObj.setCategoryId(category.getCategoryId());
			categoryObj.setName(category.getName());
			categoryObj.setLastUpdate(LocalDateTime.now());
			entityManager.merge(categoryObj);
			return true;
		} catch (Exception e) {
			log.error("Failed to commit merge transaction inside updateCategory", e);
			throw e;
		}
	}

	@Override
	public boolean deleteCategory(int categoryId) {
		log.info("... Entered into deleteCategory() of CategoryDAOImpl ...categoryId : {}", categoryId);
		try {
			Category categoryObj = entityManager.find(Category.class, categoryId);
			if (categoryObj != null) {
				entityManager.remove(categoryObj);
				return true;
			} else {
				log.warn("Category with ID {} not found for deletion", categoryId);
				return false;
			}
		} catch (Exception e) {
			log.error("Failed to commit remove transaction inside deleteCategory", e);
			throw e;
		}
	}
}
