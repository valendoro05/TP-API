
package com.example.demo.datos;

import com.example.demo.modelo.*;
import com.example.demo.views.*;


import jakarta.transaction.Transactional;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReclamoDAO {
	
	@Autowired
	private ReclamoRepository reclamoRepository;
	
	@Autowired
    private EdificioRepository edificioRepository; 
	
	public List<Reclamo> getAllReclamos(ReclamoRepository reclamoRepository){
		return reclamoRepository.findAll();
	}
	
	@Transactional
    public void save(ReclamoRepository reclamoRepository, Reclamo reclamo) {
    	reclamoRepository.save(reclamo);
    }
	
	@Transactional
	public void delete(ReclamoRepository reclamoRepository, Reclamo reclamo) {
		reclamoRepository.delete(reclamo);
	}
	
    public Edificio getEdificioByCodigo(int codigo) {
        return edificioRepository.findByCodigo(codigo);
    }
    
	public Reclamo obtenerReclamo(ReclamoRepository reclamoRepository, int id) {
		return reclamoRepository.findById(id).orElseThrow(null);
	}

	@Transactional
	public void actualizarDescripcionReclamo(ReclamoRepository reclamoRepository, int idReclamo, String nuevaDescripcion) {
		Reclamo reclamoExistente = reclamoRepository.findById(idReclamo).orElseThrow(() -> new RuntimeException("Reclamo no encontrado"));
		reclamoExistente.setDescripcion(nuevaDescripcion);
		reclamoRepository.save(reclamoExistente);
	}

	@Transactional
	public void actualizarEstadoReclamo(ReclamoRepository reclamoRepository, int idReclamo, Estado estado) {
		Reclamo reclamoExistente = reclamoRepository.findById(idReclamo).orElseThrow(() -> new RuntimeException("Reclamo no encontrado"));
		reclamoExistente.setEstado(estado);
		reclamoRepository.save(reclamoExistente);
	}

	@Transactional
	public void agregarImagen(ReclamoRepository reclamoRepository, ImagenRepository imagenRepository, int idReclamo, String direccion, String tipo) {
		Reclamo reclamo = reclamoRepository.findById(idReclamo).orElseThrow(() -> new RuntimeException("Reclamo no encontrado"));
		Imagen imagen = new Imagen(reclamo, direccion, tipo);
		imagen = imagenRepository.save(imagen); 
		reclamo.getImagenes().add(imagen);
	    reclamo.setImagenActual(imagen); // Asigna la última imagen
	    reclamoRepository.save(reclamo); 

	}
	/*
	public void updateImagen(ReclamoRepository reclamoRepository, Imagen imagen) {
		reclamoRepository.save(imagen);
		
	}
    */
}
