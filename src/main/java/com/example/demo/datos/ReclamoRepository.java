
package com.example.demo.datos;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Edificio;
import com.example.demo.modelo.Persona;
import com.example.demo.modelo.Reclamo;
import com.example.demo.modelo.Unidad;

public interface ReclamoRepository extends JpaRepository<Reclamo, Integer> {

	List<Reclamo> findAllByEdificio(Edificio edificio);
	List<Reclamo> findAllByUnidad(Unidad unidad);
	List<Reclamo> findAllByUsuario(Persona persona);
	
}
