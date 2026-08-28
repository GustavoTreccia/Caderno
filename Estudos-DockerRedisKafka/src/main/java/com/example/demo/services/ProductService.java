package com.example.demo.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entities.Category;
import com.example.demo.entities.Product;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.exceptions.DataBaseException;
import com.example.demo.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {
	
	private ProductRepository repository;
	private CategoryRepository categoryRepository;
	private KafkaProducer kafkaProducer;
	
	public ProductService(ProductRepository repository, CategoryRepository categoryRepository, KafkaProducer kafkaProducer) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.kafkaProducer = kafkaProducer;
    }

	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
		Page<Product> list = repository.findAll(pageRequest);
		return list.map(x -> new ProductDTO(x));
	}

	@Transactional(readOnly = true)
	@Cacheable(value = "products", key = "#id")
	public ProductDTO findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada"));
		return new ProductDTO(entity, entity.getCategories());
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		ProductDTO result = new ProductDTO(entity);
		kafkaProducer.sendProductEvent("PRODUCT_CREATED", result);
		return result;
	}

	@Transactional
	@CacheEvict(value = "products", key = "#id")
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = repository.getById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			ProductDTO result = new ProductDTO(entity);
			kafkaProducer.sendProductEvent("PRODUCT_UPDATED", result);
			return result;

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found: " + id);
		}
	}

	@CacheEvict(value = "products", key = "#id")
	public void delete(Long id) {
		try {
			ProductDTO dto = findById(id);
			repository.deleteById(id);
			kafkaProducer.sendProductEvent("PRODUCT_DELETED", dto);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found: " + id);
		} catch (DataIntegrityViolationException f) {
			throw new DataBaseException("Integrity Violation");
		}

	}
	
	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.name());
		entity.setDescription(dto.description());
		entity.setPrice(dto.price());
		entity.setImgUrl(dto.imgUrl());
		entity.setDate(dto.date());
		entity.getCategories().clear();
		for (CategoryDTO catDto : dto.categories()) {
			Category category = categoryRepository.getReferenceById(catDto.id());
			entity.getCategories().add(category);
		}
	}
}
