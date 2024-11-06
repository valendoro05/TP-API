package com.example.demo.modelo;

import com.example.demo.views.DuenioView;
import com.example.demo.views.EdificioView;
import com.example.demo.views.PersonaView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "duenios")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Ignorar hibernateLazyInitializer
public class Duenio{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    

    //@ManyToOne(fetch = FetchType.EAGER)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identificador", referencedColumnName = "identificador")
    @JsonIgnore
    private Unidad unidad;

    //@OneToOne(fetch = FetchType.EAGER)
   // @JoinColumn(name = "documento")
    
    
    //@JsonIgnoreProperties("duenios")
    //@JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "documento")
    //@JsonIgnoreProperties({"duenios"})
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Ignorar hibernateLazyInitializer en Persona
    private Persona persona;
    public Duenio() {}
    
    public Duenio (Unidad unidad, Persona persona) {
       	this.unidad = unidad;
    	this.persona = persona;
    	
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
    
    public void setId(Integer id) {
        this.id = id;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

	public DuenioView toView() {
		return new DuenioView(id, unidad, persona);
	}
	
	@Override
	public String toString() {
		return "Duenio:  " + id + " Propiedad:  " + unidad + unidad.getId();
	}
	
}