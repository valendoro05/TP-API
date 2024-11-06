package com.example.demo.views;

import com.example.demo.modelo.Persona;
import com.example.demo.modelo.Unidad;

public class DuenioView {
	
	private Integer id;
	private Unidad unidad;
	private Persona persona;
	
	public DuenioView (){}
	
	public DuenioView(Integer id, Unidad unidad, Persona persona) {
		this.id = id;
    	this.unidad = unidad;
    	this.persona = persona;
	}
	
	
	public Integer getId() {
		return id;
	}
	
	
	public void setId( Integer id) {
		this.id = id;
	}
	

	public Unidad getUnidad() {
		return unidad;
	}
	
	public void setUnidad( Unidad unidad) {
		this.unidad = unidad;
	}

	public Persona getPersona() {
		return persona;
	}
	
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public String toString() {
		return id + " " + unidad.getNumero() + " " + persona.getNombre(); 
	}
	
    

}
