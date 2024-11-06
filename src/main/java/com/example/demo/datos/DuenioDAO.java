package com.example.demo.datos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Duenio;
import com.example.demo.modelo.Edificio;
import com.example.demo.modelo.Reclamo;

@Repository
public class DuenioDAO {

	@Autowired
	private DuenioRepository duenioRepository;
	
	public List<Duenio> getAllDuenios(DuenioRepository duenioRepository){
		return duenioRepository.findAll();
	}
	
    public void save(DuenioRepository duenioRepository, Duenio duenio) {
    	duenioRepository.save(duenio);
    }
	
	public void delete(DuenioRepository duenioRepository, Duenio duenio) {
		duenioRepository.delete(duenio);;
	}
	
}
