package com.example.demo.datos;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.*;

public interface UnidadRepository extends JpaRepository<Unidad, Integer> {

	List<Unidad> findByDuenios(List<Duenio> duenios);
	
	List<Unidad> findByInquilinos(List<Inquilino> inquilinos);

}
