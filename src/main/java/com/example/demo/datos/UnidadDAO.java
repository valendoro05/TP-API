package com.example.demo.datos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Persona;
import com.example.demo.modelo.Unidad;


@Repository
public class UnidadDAO {
	
	@Autowired
	private UnidadRepository unidadRepository;
	
    public  List<Unidad> getAllUnidades(UnidadRepository unidadRepository) {
        return unidadRepository.findAll();
    }

    public Optional<Unidad> getUnidadById(Integer id) {
        return unidadRepository.findById(id);
    }
    
	private static UnidadDAO instancia;
	
	public static UnidadDAO getInstancia() {
		if(instancia == null)
			instancia = new UnidadDAO();
		return instancia;
	}

    public void save(UnidadRepository unidadRepository, Unidad unidad) {
    	unidadRepository.save(unidad);
    }
	
	public void delete(UnidadRepository unidadRepository, Unidad unidad) {
		unidadRepository.delete(unidad);;
	}
}
