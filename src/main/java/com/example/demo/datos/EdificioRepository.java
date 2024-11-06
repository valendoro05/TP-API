package com.example.demo.datos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Edificio;

public interface EdificioRepository extends JpaRepository<Edificio, Integer> {
    Edificio findByCodigo(int codigo);

	
}
