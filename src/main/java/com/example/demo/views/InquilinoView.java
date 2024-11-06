package com.example.demo.views;

import java.util.HashSet;
import java.util.Set;

import com.example.demo.modelo.Persona;
import com.example.demo.modelo.Unidad;

public class InquilinoView {
    private Integer id;
    private Unidad unidad;
    private Persona persona;

    public InquilinoView() {}
	
	public InquilinoView(Integer id, Persona persona, Unidad unidad) {
	    this.id = id;
	    this.persona = persona;
	    this.unidad = unidad; // Asignar la unidad
	}
	
	public void agregarUnidad(Unidad unidad) {
		this.unidad = unidad;
	}
	
	public Set<Persona> getPersonas() {
		Set<Persona> resultado = new HashSet<Persona>();
		resultado.add(persona);
		return resultado;
	}

	public Integer getId() {
		return id;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public Persona getPersona() {
		return persona;
	}

	@Override
	public String toString() {
		return "Inquilino: " + id + "Persona: " + persona.getDocumento() + "Departamento: " +unidad.getNumero();
	}
	

}
