package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.LocalidadeMotorista;

@Repository
public interface LocalidadeMotoristaRepository extends JpaRepository<LocalidadeMotorista, Long> {

}
