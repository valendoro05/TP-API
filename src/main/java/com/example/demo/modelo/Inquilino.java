package com.example.demo.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.example.demo.views.DuenioView;
import com.example.demo.views.InquilinoView;
import com.example.demo.views.PersonaView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "inquilinos")
public class Inquilino {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "identificador", referencedColumnName = "identificador")
    private Unidad unidad;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "documento", referencedColumnName = "documento")
    @JsonIgnoreProperties({"duenio", "hibernateLazyInitializer", "handler"}) // Ignorar hibernateLazyInitializer en Persona
    private Persona persona;

	public Inquilino() {}
	
	public Inquilino(Integer id, Persona persona, Unidad unidad) {
	    this.id = id;
	    this.persona = persona;
	    this.unidad = unidad; // Asignar la unidad
	}
	
	public void agregarUnidad(Unidad unidad) {
		this.unidad = unidad;
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
	
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

	@Override
	public String toString() {
		return "Inquilino: " + id + "Persona: " + persona.getDocumento() + "Departamento: " +unidad.getId();
	}

	public InquilinoView toView() {
		// TODO Auto-generated method stub
		return new InquilinoView(id, persona, unidad);
	}
}