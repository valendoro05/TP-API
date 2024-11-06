package com.example.demo.datos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Inquilino;
import com.example.demo.modelo.Unidad;



@Repository
public class InquilinoDAO {

	
	@Autowired
	private InquilinoRepository inquilinoRepository;
	
    public List<Inquilino> getAllInquilinos() {
        return inquilinoRepository.findAll();
    }

    public Optional<Inquilino> getInquilinosById(Integer id) {
        return inquilinoRepository.findById(id);
    }
    
    public void save(InquilinoRepository inquilinoRepository, Inquilino inquilino) {
    	inquilinoRepository.save(inquilino);
    }
	
	public void delete(InquilinoRepository inquilinoRepository, Inquilino inquilino) {
		inquilinoRepository.delete(inquilino);;
	}
	
    // agregar buscar por nombre u otra cosa
    
    //EJEMPLO
    
    /*
     
         public List<Provincia> buscarProvinciasPorNombre(String nombre) {
        return provinciaRepository.findAll()
                                  .stream()
                                  .filter(provincia -> provincia.getNombre().equalsIgnoreCase(nombre))
                                  .toList();
    }
     
     */
	
}
