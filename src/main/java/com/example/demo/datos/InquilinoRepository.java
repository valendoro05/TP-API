package com.example.demo.datos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Inquilino;
import com.example.demo.modelo.Persona;

public interface InquilinoRepository extends JpaRepository<Inquilino, Integer>{

    List<Inquilino> findByPersona(Persona persona);

}
