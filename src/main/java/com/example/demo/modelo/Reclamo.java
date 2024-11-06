package com.example.demo.modelo;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.datos.ImagenDAO;
import com.example.demo.datos.ImagenRepository;
import com.example.demo.views.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "reclamos") 
public class Reclamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idreclamo")
    private Integer idReclamo;

    @ManyToOne
    @JoinColumn(name = "documento", nullable = false)
    private Persona usuario;

    @ManyToOne
    @JoinColumn(name = "codigo", nullable = false)
    private Edificio edificio;

    @ManyToOne
    @JoinColumn(name = "identificador", nullable = true)
    private Unidad unidad;

    @Column(name = "ubicacion")
    private String ubicacion;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @OneToMany(mappedBy = "reclamo")
    private List<Imagen> imagenes;
    
    @ManyToOne
    @JoinColumn(name = "imagen_id")
    private Imagen imagenActual;
    
    @Enumerated(EnumType.STRING) 
    private Estado estado;

    
    public Reclamo() {}

    public Reclamo(Persona usuario, Edificio edificio, String ubicacion, String descripcion, Unidad unidad) {
        this.usuario = usuario;
        this.edificio = edificio;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.unidad = unidad;
        this.imagenes = new ArrayList<>();
        this.estado = Estado.nuevo; 

    }

    public Reclamo(Persona usuario, Edificio edificio, String ubicacion, Unidad unidad) {
        this.usuario = usuario;
        this.edificio = edificio;
        this.ubicacion = ubicacion;
        this.unidad = unidad;
        this.imagenes = new ArrayList<>();
    }
    

    // Getters y setters
    public Integer getReclamoId() {
        return idReclamo;
    }

    public void setReclamoId(Integer idReclamo) {
        this.idReclamo = idReclamo;
    }
    
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
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

    
    public List<Imagen> getImagenes() {
        return imagenes;
    }
    
    public Imagen getImagenActual() {
        return imagenActual;
    }

    public void setImagenActual(Imagen imagenActual) {
        this.imagenActual = imagenActual;
    }
    
    public Estado getEstado() { 
        return estado;
    }
    
	public void cambiarEstado(Estado nuevoEstado) {
		// TODO Auto-generated method stub
        this.estado = nuevoEstado; 

	}
	
	public ReclamoView toView() {
		ReclamoView view = new ReclamoView(idReclamo,usuario,edificio,ubicacion,descripcion,unidad,estado,imagenes);
		return view;
	}
	
	
	@Override
	public String toString() {
	    return "ID: " + idReclamo + " Documento: " + usuario + " Codigo:" + edificio + " Ubicacion: " + ubicacion 
	    		+ " Identificador: " + unidad + " Descripcion: " + descripcion + " Estado: " + estado;//+ " Imagenes=" + imagenes.size();
	}



}