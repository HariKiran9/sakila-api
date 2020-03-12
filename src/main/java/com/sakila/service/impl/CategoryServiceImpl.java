/**
 * 
 */
package com.sakila.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sakila.dao.CategoryDAO;
import com.sakila.service.CategoryService;
import com.sakila.vo.CategoryVO;

/**
 * @author bc887d
 *
 */
@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDAO categoryDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sakila.service.CategoryService#getCategories()
	 */
	@Override
	public List<CategoryVO> getCategories() {
		return categoryDAO.getCategories();
	}

	@Override
	public CategoryVO getCategoryDetailsById(int categoryId) {
		return categoryDAO.getCategoryDetailsById(categoryId);
	}

	@Override
	public boolean updateCategory(CategoryVO category) {
		return categoryDAO.updateCategory(category);
	}

	@Override
	public List<CategoryVO> getCategoriesByPagination(PageRequest pageable) {
		return categoryDAO.getCategoriesByPagination(pageable);
	}

	@Override
	public int saveCategory(CategoryVO category) {
		return categoryDAO.saveCategory(category);
	}

	@Override
	public boolean deleteCategory(int categoryId) {
		return categoryDAO.deleteCategory(categoryId);
	}

}
