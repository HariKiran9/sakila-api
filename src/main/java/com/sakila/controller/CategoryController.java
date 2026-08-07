package com.sakila.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sakila.service.CategoryService;
import com.sakila.vo.CategoryVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

	private final CategoryService categoryService;

	// Use constructor injection instead of field-level @Autowired for better
	// testability and immutability
	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	public ResponseEntity<List<CategoryVO>> getCategories() {
		// Enforced explicit return types over generic '? extends Object'
		return ResponseEntity.ok(categoryService.getCategories());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryVO> getCategoryDetailsById(@PathVariable("id") int categoryId) {
		return ResponseEntity.ok(categoryService.getCategoryDetailsById(categoryId));
	}

	@PostMapping
//	@PreAuthorize("hasRole('ROLE_ADMIN')") // Restrict mutation actions to Authorized users
	public ResponseEntity<Integer> saveCategory(@RequestBody CategoryVO category) {
		// Sanitize incoming logger logs to prevent Log Injection flaws (CWE-117)
		if (log.isInfoEnabled() && category != null) {
			String cleanId = String.valueOf(category.getCategoryId()).replaceAll("[\r\n]", "");
			log.info("Processing saveCategory for Category ID: {}", cleanId);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.saveCategory(category));
	}

	@PutMapping("/{id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Boolean> updateCategory(@PathVariable("id") int categoryId,
			@RequestBody CategoryVO category) {
		log.info("Processing updateCategory for ID: {}", categoryId);
		return ResponseEntity.ok(categoryService.updateCategory(category));
	}

	@DeleteMapping("/{id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Void> deleteCategory(@PathVariable("id") int categoryId) {
		log.info("Processing deleteCategory for ID: {}", categoryId);
		categoryService.deleteCategory(categoryId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/page/{page}")
	public ResponseEntity<List<CategoryVO>> listArticlesPageByPage(@PathVariable("page") int page) {
		int pageSize = 10;
		PageRequest pageable = PageRequest.of(page, pageSize);
		return ResponseEntity.ok(categoryService.getCategoriesByPagination(pageable));
	}
}
