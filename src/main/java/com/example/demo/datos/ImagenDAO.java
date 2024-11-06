package com.example.demo.datos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Edificio;
import com.example.demo.modelo.Imagen;

@Repository
public class ImagenDAO {

	
	@Autowired
	private ImagenRepository imagenRepository;
	
	private static ImagenDAO instancia;
		
	public static ImagenDAO getInstancia() {
		if(instancia == null)
			instancia = new ImagenDAO();
		return instancia;
	}
	
	
    public void save(ImagenRepository imagenRepository, Imagen imagen) {
    	imagenRepository.save(imagen);
    }
	
	public void delete(ImagenRepository imagenRepository, Imagen imagen) {
		imagenRepository.delete(imagen);;
	}
	
	/*
	public List<Imagen> getAllEdi(ImagenRepository imagenRepository){
		return imagenRepository.findAll();
	}*/
}