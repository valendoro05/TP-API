package com.example.demo.modelo;
import com.example.demo.datos.EdificioRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.demo.datos.EdificioRepository;
import com.example.demo.views.EdificioView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Id;


@Entity
@Table(name = "edificios")
public class Edificio {
	//@JsonIgnoreProperties("edificios")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo")
	private Integer codigo;
    @Column(name = "nombre")
	private String nombre;
    @Column(name = "direccion")
	private String direccion;
	
	
	//LINKEO CON UNIDADES
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "codigo_edificio")
	//@JsonIgnore
    //@Transient
	private List<Unidad> unidades;
	
	
	public Edificio() {}
	
	public Edificio(Integer codigo, String nombre, String direccion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.direccion = direccion;
		unidades = new ArrayList<Unidad>();
	}
	
	public Edificio(String nombre, String direccion) {
		this.nombre = nombre;
		this.direccion = direccion;
		unidades = new ArrayList<Unidad>();
	}
	
	public void agregarUnidad(Unidad unidad) {
		unidades.add(unidad);
	}
	
	public Set<Persona> habilitados() {
	    Set<Persona> habilitados = new HashSet<Persona>();
	    for (Unidad unidad : unidades) {
	        List<Duenio> duenios = unidad.getDuenios();
	        for (Duenio d : duenios) {
	            habilitados.add(d.getPersona()); // Accede a la persona del Duenio
	        }
	        List<Inquilino> inquilinos = unidad.getInquilinos();
	        for (Inquilino i : inquilinos) {
	            habilitados.add(i.getPersona()); // Accede a la persona del Inquilino
	        }
	    }
	    return habilitados;
	   
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public List<Unidad> getUnidades() {
		return unidades;
	}

	
	public Set<Persona> duenios() {
	    Set<Persona> resultado = new HashSet<Persona>();
	    for (Unidad unidad : unidades) {
	        List<Duenio> duenios = unidad.getDuenios();
	        for (Duenio d : duenios) {
	            resultado.add(d.getPersona()); // Accede a la persona del Duenio
	        }
	    }
	    return resultado;
	}

	public Set<Persona> habitantes() {
	    Set<Persona> resultado = new HashSet<Persona>();
	    for (Unidad unidad : unidades) {
	        if (unidad.estaHabitado()) {
	            List<Inquilino> inquilinos = unidad.getInquilinos();
	            if (inquilinos.size() > 0) {
	                for (Inquilino i : inquilinos) {
	                    resultado.add(i.getPersona()); // Accede a la persona del Inquilino
	                }
	            } else {
	                List<Duenio> duenios = unidad.getDuenios();
	                for (Duenio d : duenios) {
	                    resultado.add(d.getPersona()); // Accede a la persona del Duenio
	                }
	            }
	        }
	    }
	    return resultado;
	}
	
    
	public EdificioView toView() {
		return new EdificioView(codigo, nombre, direccion);
	}
	
	@Override
	public String toString() {
		return "Edificio " + codigo + " : " + nombre + " Direccion : " + direccion ;
	}

}