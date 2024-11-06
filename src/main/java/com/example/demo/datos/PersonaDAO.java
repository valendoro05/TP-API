package com.example.demo.datos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Persona;
import com.example.demo.modelo.Unidad;

@Repository
public class PersonaDAO {
	
	@Autowired
	private PersonaRepository personaRepository;
	
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }
    
    public void save(PersonaRepository personaRepository, Persona persona) {
    	personaRepository.save(persona);
    }
	
	public void delete(PersonaRepository personaRepository, Persona persona) {
		personaRepository.delete(persona);;
	}
		
	

}
