package com.example.demo.datos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.*;

public interface PersonaRepository extends JpaRepository<Persona, String> {

	Persona findByDocumento(String string);
}
