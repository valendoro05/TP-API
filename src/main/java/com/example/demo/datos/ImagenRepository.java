package com.example.demo.datos;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Edificio;
import com.example.demo.modelo.Imagen;

public interface ImagenRepository extends JpaRepository<Imagen, Integer> {

}
