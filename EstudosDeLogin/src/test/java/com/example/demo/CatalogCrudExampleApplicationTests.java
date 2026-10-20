package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.services.CategoryService;
import com.example.demo.services.ProductService;
import com.example.demo.services.exceptions.ResourceNotFoundException;

@SpringBootTest
class CatalogCrudExampleApplicationTests {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@Test
	void contextLoads() {
	}

	@Test
	void categoryCrudOperationsShouldWork() {
		// Find All
		Page<CategoryDTO> categories = categoryService.findAllPaged(PageRequest.of(0, 10));
		assertNotNull(categories);

		// Insert
		CategoryDTO newCategory = new CategoryDTO(null, "Test Category");
		CategoryDTO inserted = categoryService.insert(newCategory);
		assertNotNull(inserted.getId());
		assertEquals("Test Category", inserted.getName());

		// Find By Id
		CategoryDTO found = categoryService.findById(inserted.getId());
		assertEquals("Test Category", found.getName());

		// Update
		inserted.setName("Updated Category");
		CategoryDTO updated = categoryService.update(inserted.getId(), inserted);
		assertEquals("Updated Category", updated.getName());

		// Delete
		categoryService.delete(inserted.getId());
		assertThrows(ResourceNotFoundException.class, () -> categoryService.findById(inserted.getId()));
	}

	@Test
	void productCrudOperationsShouldWork() {
		// Find All
		Page<ProductDTO> products = productService.findAllPaged(PageRequest.of(0, 10));
		assertNotNull(products);

		// Insert
		ProductDTO newProduct = new ProductDTO(null, "Test Product", "Description", 100.0, "http://img.com", null);
		ProductDTO inserted = productService.insert(newProduct);
		assertNotNull(inserted.getId());
		assertEquals("Test Product", inserted.getName());

		// Find By Id
		ProductDTO found = productService.findById(inserted.getId());
		assertEquals("Test Product", found.getName());

		// Update
		inserted.setName("Updated Product");
		ProductDTO updated = productService.update(inserted.getId(), inserted);
		assertEquals("Updated Product", updated.getName());

		// Delete
		productService.delete(inserted.getId());
		assertThrows(ResourceNotFoundException.class, () -> productService.findById(inserted.getId()));
	}

}
