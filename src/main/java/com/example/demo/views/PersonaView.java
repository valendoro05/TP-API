package com.example.demo.views;

import com.example.demo.modelo.TipoUsuario;

public class PersonaView {
	private String documento;
	private String nombre;
	
    private String nombreUser;
    private String contrasenia;
    private TipoUsuario tipoUser;
	
	public PersonaView() {}

    public PersonaView(String documento, String nombre, String nombreUser, String contrasenia, TipoUsuario tipoUser) { // Cambia a String
        this.documento = documento;
        this.nombre = nombre;
        this.nombreUser = nombreUser;
        this.contrasenia = contrasenia;
        this.tipoUser = tipoUser;
    }

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String toString() {
		return documento + " " + nombre;
	}

}
