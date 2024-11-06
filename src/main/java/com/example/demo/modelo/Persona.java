package com.example.demo.modelo;

import com.example.demo.views.EdificioView;
import com.example.demo.views.PersonaView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "personas")
public class Persona {
	
    @Id
    @Column(name = "documento")
    private String documento; 

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "nombreuser")
    private String nombreUser;
    
    @Column(name = "contrasenia")
    private String contrasenia;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipoUser")
    private TipoUsuario tipoUser;

    //@JsonIgnoreProperties("personas")
    
    //@JsonIgnore
    public Persona() {}

    public Persona(String documento, String nombre, String nombreUser, String contrasenia, TipoUsuario tipoUser) { // Cambia a String
        this.documento = documento;
        this.nombre = nombre;
        this.nombreUser = nombreUser;
        this.contrasenia = contrasenia;
        this.tipoUser = tipoUser;
    }

    public String getDocumento() { // Cambia a String
        return documento;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getNombreUser() {
    	return nombreUser;
    }
    
    public String getContrasenia() {
    	return contrasenia;
    }
    
    public TipoUsuario getTipoUser() {
    	return tipoUser;
    }

    public PersonaView toView() {
		return new PersonaView(nombre, documento, nombreUser, contrasenia, tipoUser);
	}
    
    @Override
    public String toString() {
        return "Documento: " + documento + "  Nombre: " + nombre + "  Nombre Usuario: " + nombreUser + "  Tipo Usuario: " + tipoUser;
    }

}
