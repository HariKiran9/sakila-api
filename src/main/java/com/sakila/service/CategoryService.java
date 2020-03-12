/**
 * 
 */
package com.sakila.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.sakila.vo.CategoryVO;

/**
 * @author bc887d
 *
 */
public interface CategoryService {

	public List<CategoryVO> getCategories();

	public List<CategoryVO> getCategoriesByPagination(PageRequest pageable);

	public CategoryVO getCategoryDetailsById(int categoryId);

	public int saveCategory(CategoryVO category);

	public boolean updateCategory(CategoryVO category);

	public boolean deleteCategory(int categoryId);

}
