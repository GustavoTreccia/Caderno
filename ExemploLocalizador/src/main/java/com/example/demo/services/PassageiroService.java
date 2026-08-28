package com.example.demo.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.PassageiroDTO;
import com.example.demo.entities.Passageiro;
import com.example.demo.repositories.PassageiroRepository;
import com.example.demo.services.exceptions.DataBaseException;
import com.example.demo.services.exceptions.ResourceNotFoundException;

@Service
public class PassageiroService {

	@Autowired
	private PassageiroRepository repository;

	@Transactional(readOnly = true)
	public Page<PassageiroDTO> findAllPaged(PageRequest pageRequest) {
		Page<Passageiro> list = repository.findAll(pageRequest);
		return list.map(PassageiroDTO::new);
	}

	@Transactional(readOnly = true)
	public PassageiroDTO findById(Long id) {
		Optional<Passageiro> obj = repository.findById(id);
		Passageiro entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada"));
		return new PassageiroDTO(entity);
	}

	@Transactional
	public PassageiroDTO insert(PassageiroDTO dto) {
		Passageiro entity = new Passageiro();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new PassageiroDTO(entity);
	}

	@Transactional
	public PassageiroDTO update(Long id, PassageiroDTO dto) {
		try {
			Passageiro entity = repository.getById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new PassageiroDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found: " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found: " + id);
		} catch (DataIntegrityViolationException f) {
			throw new DataBaseException("Integrity Violation");
		}
	}

	private void copyDtoToEntity(PassageiroDTO dto, Passageiro entity) {
		entity.setName(dto.getName());
		entity.setCoordenada(dto.getCoordenada());
		entity.setAtivo(dto.isAtivo());
	}
}
