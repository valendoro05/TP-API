package com.example.demo.datos;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.modelo.Edificio;
import com.example.demo.modelo.Inquilino;


@Repository
public class EdificioDAO {

	
	@Autowired
	private EdificioRepository edificioRepository;
	
	
	private static EdificioDAO instancia;
		
	public static EdificioDAO getInstancia() {
		if(instancia == null)
			instancia = new EdificioDAO();
		return instancia;
	}


    @Transactional
	public List<Edificio> getAllEdi(EdificioRepository edificioRepository){
		return edificioRepository.findAll();
	}
    
    public void save(EdificioRepository edificioRepository, Edificio edificio) {
    	edificioRepository.save(edificio);
    }
	
	public void delete(EdificioRepository edificioRepository, Edificio edificio) {
		edificioRepository.delete(edificio);;
	}

}
