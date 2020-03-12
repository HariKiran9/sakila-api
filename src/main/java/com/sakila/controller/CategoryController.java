/**
 * 
 */
package com.sakila.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sakila.service.CategoryService;
import com.sakila.vo.CategoryVO;

/**
 * @author bc887d
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/category")
public class CategoryController {
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	public CategoryService categoryService;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<? extends Object> getCategories() {
		return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<? extends Object> getCategoryDetailsById(@PathVariable(name = "id") int categoryId) {
		return new ResponseEntity<>(categoryService.getCategoryDetailsById(categoryId), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<? extends Object> saveCategory(@RequestBody CategoryVO category) {
		logger.info("...Entered into saveCategory() of CategoryController...category = {} ", category);
		return new ResponseEntity<>(categoryService.saveCategory(category), HttpStatus.OK);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<? extends Object> updateCategory(@PathVariable(name = "id") int categoryId,
			@RequestBody CategoryVO category) {
		logger.info("...Entered into updateCategory() of CategoryController...id = {} ", categoryId);
		return new ResponseEntity<>(categoryService.updateCategory(category), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<? extends Object> deleteCategory(@PathVariable(name = "id") int categoryId) {
		logger.info("...Entered into updateCategory() of CategoryController...id = {} ", categoryId);
		return new ResponseEntity<>(categoryService.deleteCategory(categoryId), HttpStatus.OK);
	}

	@RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
	public ResponseEntity<? extends Object> listArticlesPageByPage(@PathVariable("page") int page) {
		int pageSize = 10;
		PageRequest pageable = new PageRequest(page, pageSize);
//		Page<CategoryVO> articlePage = categoryService.getCategoriesByPagination(pageable);
//		int totalPages = articlePage.getTotalPages();
//		if (totalPages > 0) {
//			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
//			modelAndView.addObject("pageNumbers", pageNumbers);
//		}
//		modelAndView.addObject("activeArticleList", true);
//		modelAndView.addObject("articleList", articlePage.getContent());

		return new ResponseEntity<>(categoryService.getCategoriesByPagination(pageable), HttpStatus.OK);
	}

}
