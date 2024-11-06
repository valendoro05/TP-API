package com.example.demo.views;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.modelo.Edificio;
import com.example.demo.modelo.Imagen;
import com.example.demo.modelo.Persona;
import com.example.demo.modelo.Unidad;

public class ReclamoView {
    private Integer idReclamo;
	private Persona usuario;
	private Edificio edificio;
	private String ubicacion;
	private String descripcion;
	private Unidad  unidad;
	private Estado estado;
	private List<Imagen> imagenes;

	
    public ReclamoView(Integer idReclamo, Persona usuario, Edificio edificio, String ubicacion, String descripcion, 
    		Unidad unidad, Estado estado, List<Imagen> imagenes) {
        this.idReclamo = idReclamo;
    	this.usuario = usuario;
        this.edificio = edificio;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.unidad = unidad;
        this.imagenes = new ArrayList<>();
        this.estado = Estado.nuevo; 
    
    }

    // Getters y setters
    public Integer getReclamoId() {
        return idReclamo;
    }

    public void setReclamoId(Integer idReclamo) {
        this.idReclamo = idReclamo;
    }

    public Persona getUsuario() {
        return usuario;
    }

    public void setUsuario(Persona usuario) {
        this.usuario = usuario;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Unidad getUnidad() {
        return unidad;
    }

	public void setImagenes(List<Imagen> imagenes) {
		this.imagenes = imagenes;
	}
    
    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public Estado getEstado() { 
        return estado;
    }
    
	public void cambiarEstado(Estado nuevoEstado) {
		// TODO Auto-generated method stub
        this.estado = nuevoEstado; 

	}
	
	@Override
	public String toString() {
	    return "ID: " + idReclamo + " Documento: " + usuario + " Codigo:" + edificio + " Ubicacion: " + ubicacion 
	    		+ " Identificador: " + unidad + " Descripcion: " + descripcion + " Estado: " + estado;//+ " Imagenes=" + imagenes.size();
	}
}




