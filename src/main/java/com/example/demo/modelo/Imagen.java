package com.example.demo.modelo;

import com.example.demo.views.ReclamoView;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "imagenes")
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer numero;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "tipo")
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "reclamo")
    @JsonIgnore
    private Reclamo reclamo;

    public Imagen() {}
    
    public Imagen(Reclamo reclamo, String direccion, String tipo) {
    	this.reclamo = reclamo;
    	this.direccion = direccion;
        this.tipo = tipo;
    }

    // Getters y setters
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Reclamo getReclamo() {
        return reclamo;
    }

    public void setReclamo(Reclamo reclamoView) {
        this.reclamo = reclamoView;
    }

	public void save(Integer idReclamo) {
		// TODO Auto-generated method stub
		
	}

}