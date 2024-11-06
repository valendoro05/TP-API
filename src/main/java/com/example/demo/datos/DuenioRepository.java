package com.example.demo.datos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Duenio;
import com.example.demo.modelo.Edificio;
import com.example.demo.modelo.Persona;

public interface DuenioRepository extends JpaRepository<Duenio, Integer>{

    List<Duenio> findByPersona(Persona persona);

}
