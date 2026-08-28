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

import com.example.demo.dto.LocalidadeMotoristaDTO;
import com.example.demo.entities.LocalidadeMotorista;
import com.example.demo.repositories.LocalidadeMotoristaRepository;
import com.example.demo.services.exceptions.DataBaseException;
import com.example.demo.services.exceptions.ResourceNotFoundException;

@Service
public class LocalidadeMotoristaService {

	@Autowired
	private LocalidadeMotoristaRepository repository;

	@Transactional(readOnly = true)
	public Page<LocalidadeMotoristaDTO> findAllPaged(PageRequest pageRequest) {
		Page<LocalidadeMotorista> list = repository.findAll(pageRequest);
		return list.map(LocalidadeMotoristaDTO::new);
	}

	@Transactional(readOnly = true)
	public LocalidadeMotoristaDTO findById(Long id) {
		Optional<LocalidadeMotorista> obj = repository.findById(id);
		LocalidadeMotorista entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada"));
		return new LocalidadeMotoristaDTO(entity);
	}

	@Transactional
	public LocalidadeMotoristaDTO insert(LocalidadeMotoristaDTO dto) {
		LocalidadeMotorista entity = new LocalidadeMotorista();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new LocalidadeMotoristaDTO(entity);
	}

	@Transactional
	public LocalidadeMotoristaDTO update(Long id, LocalidadeMotoristaDTO dto) {
		try {
			LocalidadeMotorista entity = repository.getById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new LocalidadeMotoristaDTO(entity);
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

	private void copyDtoToEntity(LocalidadeMotoristaDTO dto, LocalidadeMotorista entity) {
		entity.setIdentificadorString(dto.getIdentificadorString());
		entity.setCoordenada(dto.getCoordenada());
		entity.setValorDiferencial(dto.getValorDiferencial());
		entity.setAtivo(dto.isAtivo());
	}
}
