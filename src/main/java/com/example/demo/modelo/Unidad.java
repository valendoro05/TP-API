package com.example.demo.modelo;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.views.EdificioView;
import com.example.demo.views.UnidadView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import exceptions.UnidadException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "unidades")
public class Unidad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="identificador")
	private Integer identificador;
	
	//LINKEO CON EDIFICIO
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_edificio")
	@JsonIgnoreProperties("edificios")
	@JsonIgnore
	private Edificio edificio;
	
    @Column(name = "piso")
	private int piso;
    @Column(name = "numero")
	private int numero;
    @Column(name = "habitado")
	private String habitado;
	
	//LINKEO CON INQUILINO
    @OneToMany(mappedBy = "unidad", fetch = FetchType.LAZY)
	//@JoinColumn(name="identificador")
	@JsonIgnoreProperties("inquilinos")
    @JsonIgnore
	private List<Inquilino> inquilinos;
	
	//LINKEO CON DUENIO
	@OneToMany(mappedBy = "unidad", fetch = FetchType.LAZY)
	//@JoinColumn(name="identificador")
	@JsonIgnoreProperties("personas")
    @JsonIgnore
	private List<Duenio> duenios;
	
	
	public Unidad() {}
	
	public Unidad(Integer identificador, Integer piso, int numero, Edificio edificio) {
		this.identificador = identificador;
		this.piso = piso;
		this.numero = numero;
		this.habitado = "N";
		this.edificio = edificio;
		this.duenios = new ArrayList<Duenio>();
		this.inquilinos = new ArrayList<Inquilino>();
	}
	
	public Unidad(Integer piso, int numero, Edificio edificio) {
		this.piso = piso;
		this.numero = numero;
		this.duenios = new ArrayList<Duenio>();
		this.inquilinos = new ArrayList<Inquilino>();
	}

	public void transferir(Duenio duenio) {
	    duenios.clear(); 
	    duenios.add(duenio);
	}

	
	public void transferir(Inquilino inquilino) {
	    inquilinos.clear(); // Elimina a todos los inquilinos actuales antes de transferir
	    inquilinos.add(inquilino);
	}
	
	public void agregarDuenio(Duenio duenio) {
		if (!duenios.contains(duenio)) {
			duenios.add(duenio);
		}	
	}
	
	public void eliminarDuenio(Duenio duenio) {
		duenios.remove(duenio);
	}
	
	public void eliminarInquilino(Inquilino inquilino) {
		inquilinos.remove(inquilino);
	}
	
	public void alquilar(Inquilino inquilino) throws UnidadException {
		if(this.habitado.equals("No")) {
			this.habitado = "Si";
			inquilinos.add(inquilino);
		}
		else
			throw new UnidadException("La unidad esta ocupada");
	}

	public void agregarInquilino(Inquilino inquilino) {
		inquilinos.add(inquilino);
	}
	
	public boolean estaHabitado() {	
	    return this.habitado.equals("Si");

	}
		
	
	public void liberar() {
		this.inquilinos = new ArrayList<Inquilino>();
		this.habitado = "No";
	}
	
	public void habitar() throws UnidadException {
	    if (this.habitado.equals("H"))
	        throw new UnidadException("La unidad ya está habitada");
	    else
	        this.habitado = "H";
	}
	
	public Integer getId() {
		return identificador;
	}

	public Integer getPiso() {
		return piso;
	}

	public int getNumero() {
		return numero;
	}

	
	public Edificio getEdificio() {
		return edificio;
	}
	

	public List<Duenio> getDuenios() {
		return duenios;
	}

	public List<Inquilino> getInquilinos() {
		return inquilinos;
	}


	public UnidadView toView() {
		EdificioView auxEdificio = edificio.toView();
		return new UnidadView(identificador, piso, numero, habitado, auxEdificio);
	}

	@Override
	public String toString() {
		return "Id " + identificador+ " Piso: " + piso + "Numero de depto: " + numero + "Esta habitado: " + habitado + " Edificio: " + edificio;
	}

}