
package com.example.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.datos.*;
import com.example.demo.modelo.*;
import com.example.demo.views.*;

import exceptions.*;
import jakarta.transaction.Transactional;

@SpringBootApplication
public class TpApiApplication implements CommandLineRunner {
	
	@Autowired
	EdificioDAO edificioDAO;
	
	@Autowired
	private EdificioRepository edificioRepository;
	
	@Autowired
	UnidadDAO unidadDAO;
	
	@Autowired
	private UnidadRepository unidadRepository;
	
	@Autowired
	DuenioDAO duenioDAO;
	
	@Autowired
	private DuenioRepository duenioRepository;
	
	@Autowired
	PersonaDAO personaDAO;
	
	@Autowired
	private PersonaRepository personaRepository;
	
	@Autowired
	InquilinoDAO inquilinoDAO;
	
	@Autowired
	private InquilinoRepository inquilinoRepository;

	@Autowired
	ImagenDAO imagenDAO;
	
	@Autowired
	private ImagenRepository imagenRepository;
	
	
	@Autowired
	ReclamoDAO reclamoDAO;
	
	@Autowired
	private ReclamoRepository reclamoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(TpApiApplication.class, args);
	
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Empezamos a ejecutar");
		List<Edificio> edificios = edificioRepository.findAll();
		for(Edificio edificio : edificios)
			System.out.println(edificio.toString());
		
		Unidad u = new Unidad();
		List<Unidad> unidad = UnidadDAO.getInstancia().getAllUnidades(unidadRepository);
		if(unidad.size() == 0)
			System.out.println(unidad.toString());
		
	}
 
 	
 	

 	/*
 	

	@Bean
 	public CommandLineRunner crearEdificio(){
 	    return args -> {
 			Edificio edificio = new Edificio("uade", "independencia 123");
 			edificioRepository.save(edificio);
 	 		System.out.println(edificio);
 		};
 	}
 

	@Bean
 	public CommandLineRunner eliminarEdificio() {
 		return args -> {
 			try {
 				Edificio edificio = edificioRepository.findById(1014).orElseThrow(()-> new EdificioException("No se encontró el edificio"));
  				List<Unidad> unidadesAEliminar = new ArrayList<>(edificio.getUnidades());
 
 				for (Unidad unidad : unidadesAEliminar) {
 					unidadRepository.delete(unidad);
 				}
 				edificioRepository.delete(edificio);
 
 			} catch (EdificioException e) {
 				System.err.println(e.getMessage());
 			}
 		};
 	}
 		


 	@Bean
 	@Transactional
 	public CommandLineRunner getUnidadesPorEdificio() {
 	    return args -> {
 	        Edificio edificio = edificioRepository.findById(1).orElseThrow(
 	                () -> new EdificioException("No se encontró el edificio con ese id"));
 	        List<Unidad> unidades = edificio.getUnidades(); 
 	        for(Unidad unidad : unidades) {
 				System.out.println(unidad.toString());
 	        }
 	    };
 	}
*/
 	/*
 	@Bean
 	@Transactional
 	public CommandLineRunner buscarEdificioPorId(EdificioRepository edificioRepository){
 		return args -> {
 			System.out.println(edificioRepository.findById(7).orElseThrow(() 
 					-> new EdificioException("No se encontro el edificio con ese id")).toView());
 		};
 	}
 
	
 	@Bean
 	@Transactional
 	public CommandLineRunner habilitadosPorEdificio(EdificioRepository edificioRepository) throws EdificioException {
 		return args -> {
 			edificioRepository.findById(3).orElseThrow(() -> new EdificioException("No se encontro el edificio con ese id")).getUnidades()
 					.stream()
 					.flatMap(unidad -> Stream.concat(unidad.getDuenios().stream(), unidad.getInquilinos().stream()))
 					.forEach(persona -> System.out.println(persona));
 		};
 	}

 	@Bean
 	@Transactional
 	public CommandLineRunner dueniosPorEdificio(EdificioRepository edificioRepository) throws EdificioException {
 	    return args -> {
 	        edificioRepository.findById(2).orElseThrow(() -> new EdificioException("No se encontro ese edificio"))
 	                .duenios()
 	                .stream()
 	                .forEach(duenio -> System.out.println(duenio));
 	    };
 	}
 
 
 	@Bean
 	@Transactional
 	public CommandLineRunner habitantesPorEdificio(EdificioRepository edificioRepository) throws EdificioException {
 		return args -> {
 			edificioRepository.findById(6)
 					.orElseThrow(() -> new EdificioException("No se encontro ese edificio"))
 					.duenios()
 					.stream()
 					.forEach(duenio -> System.out.println(duenio));
 		};
 	}
 



 	@Bean
 	public CommandLineRunner crearUnidad(){
 		return args -> {
 			Edificio edificio = new Edificio("nuevo", "segurola y habana");
 			Unidad unidad = new Unidad(8, 3, edificio);
 			edificioRepository.save(edificio);
 			unidadRepository.save(unidad);
 		};
 	}

	
 	@Bean
 	public CommandLineRunner eliminarUnidad(UnidadRepository unidadRepository){
 		return args -> {
 			try {
 				Unidad unidad = unidadRepository.findById(10)
 						.orElseThrow(() -> new UnidadException("No se encontro la unidad"));
 				unidadRepository.delete(unidad);
 			}catch (UnidadException e){
 				System.err.println(e.getMessage());
 			}
 		};
 	}
 
 	@Bean
 	public CommandLineRunner buscarUnidad(UnidadRepository unidadRepository){
 		return args -> {
 			try {
 				System.out.println(
 						unidadRepository.findById(163).orElseThrow(() -> new UnidadException("No se encontro la unidad con ese id")));
 			}catch(UnidadException e){
 				System.err.println(e.getMessage());
 			}
 		};
 	}
 

 	@Bean
 	public CommandLineRunner dueniosPorUnidad(UnidadRepository unidadRepository) throws UnidadException {
 		return  args -> {
 			unidadRepository.findById(268)
 					.orElseThrow(() -> new UnidadException("No se encontro esa unidad"))
 					.getDuenios()
 					.stream()
 					.forEach(duenio -> System.out.println(duenio));
 		};
 	}
 
	
 	@Bean
 	public CommandLineRunner inquilinosPorUnidad(UnidadRepository unidadRepository) throws UnidadException {
 		return  args -> {
 			unidadRepository.findById(443)
 					.orElseThrow(() -> new UnidadException("No se encontro esa unidad"))
 					.getInquilinos()
 					.stream()
 					.forEach(inquilino -> System.out.println(inquilino));
 		};
 	}

 	@Bean
 	public CommandLineRunner transferirUnidad(UnidadRepository unidadRepository, PersonaRepository personaRepository)
 			throws UnidadException, PersonaException{
 		return args -> {
 			try {
 				Unidad unidad = unidadRepository.findById(192).orElseThrow(() -> new UnidadException("No se encontro la unidad."));
 				Persona persona = personaRepository.findByDocumento("DNI100000000");
 				if (persona == null) {
 					throw new PersonaException("No se encontró la persona.");
 				}
 				Duenio duenio = new Duenio();
 				unidad.transferir(duenio);
 				unidadRepository.save(unidad);
 				personaRepository.save(persona);
 			}catch (UnidadException | PersonaException e) {
 				System.err.println("Error: " + e.getMessage());
 			}
 		};
 	}

 	@Bean
 	public CommandLineRunner agregarDuenioUnidad(UnidadRepository unidadRepository, PersonaRepository personaRepository)
 			throws UnidadException, PersonaException{
 		return args -> {
 			try {
 				Unidad unidad = unidadRepository.findById(640).orElseThrow(() -> new UnidadException("No se encontro la unidad."));
 				Persona persona = personaRepository.findByDocumento("DNI100000000");
 				if (persona == null) {
 					throw new PersonaException("No se encontró la persona.");
 				}
 				Duenio duenio = new Duenio();
 				unidad.agregarDuenio(duenio);
 				unidadRepository.save(unidad);
 				personaRepository.save(persona);
 			}catch (UnidadException | PersonaException e) {
 				System.err.println("Error: " + e.getMessage());
 			}
 		};
 	}
 
 
 	@Bean
 	public CommandLineRunner alquilarUnidad(UnidadRepository unidadRepository, PersonaRepository personaRepository)
 			throws UnidadException, PersonaException {
         return args -> {
 			try{
 				Unidad unidad = unidadRepository.findById(60).orElseThrow(() -> new UnidadException("No se encontro la unidad."));
 				Persona persona = personaRepository.findByDocumento("DNI30963661");
 				if (persona == null) {
 					throw new PersonaException("No se encontró la persona.");
 				}
 				Inquilino inquilino = new Inquilino();
 				unidad.alquilar(inquilino);
 				unidadRepository.save(unidad);
 				personaRepository.save(persona);
 			} catch (UnidadException | PersonaException e) {
 				System.err.println("Error: " + e.getMessage());
 			}
         };
     }
 

 	@Bean
 	public CommandLineRunner agregarInquilinoUnidad(UnidadRepository unidadRepository, PersonaRepository personaRepository)
 			throws UnidadException, PersonaException {
 		return args -> {
 			try {
 				Unidad unidad = unidadRepository.findById(700).orElseThrow(() -> new UnidadException("No se encontro la unidad."));
 				Persona persona = personaRepository.findByDocumento("DNI100000000");
 				if (persona == null) {
 					throw new PersonaException("No se encontró la persona.");
 				}
 				Inquilino inquilino = new Inquilino();
 				unidad.agregarInquilino(inquilino);
 				unidadRepository.save(unidad);
 				personaRepository.save(persona);
 			}catch (UnidadException | PersonaException e) {
 				System.err.println("Error: " + e.getMessage());
 			}
 		};
 	}
 
	
 	@Bean
 	public CommandLineRunner liberarUnidad(UnidadRepository unidadRepository)
 			throws UnidadException {
 		return args -> {
 			try {
 				Unidad unidad = unidadRepository.findById(90).orElseThrow(() -> new UnidadException("No se encontro la unidad."));
 				unidad.liberar();
 				unidadRepository.save(unidad);
 			}catch (UnidadException e) {
 				System.err.println("Error: " + e.getMessage());
 			}
 		};
 	}
 

 	@Bean
 	public CommandLineRunner habitarUnidad(UnidadRepository unidadRepository)
 			throws UnidadException {
 		return args -> {
 			try {
 				Unidad unidad = unidadRepository.findById(93).orElseThrow(() -> new UnidadException("No se encontro la unidad."));
 				unidad.habitar();
 				unidadRepository.save(unidad);
 			}catch (UnidadException e) {
 				System.err.println("Error: " + e.getMessage());
 			}
 		};
 	}


 

 	@Bean
 	public CommandLineRunner crearPersona(PersonaRepository personaRepository){
 		return args -> {
 			Persona persona = new Persona("DNI100000000", "FERNANDEZ, JULIAN", "holachau", "contrasenia", TipoUsuario.administrador);
 			personaRepository.save(persona);
 		};
 	}
 

 	@Bean
 	public CommandLineRunner buscarPersona(PersonaRepository personaRepository) throws PersonaException {
 		return args -> {
 			Persona persona = personaRepository.findByDocumento("DNI38670209");
 			if (persona == null) {
 	            throw new PersonaException("No se encontro la persona con ese documento");
 			}
 		};
 	
 	}

 	@Bean
 	public CommandLineRunner eliminarPersona(PersonaRepository personaRepository, UnidadRepository unidadRepository, DuenioRepository duenioRepository, InquilinoRepository inquilinoRepository) 
 	        throws PersonaException {
 	    return args -> {
 	        Persona persona = personaRepository.findByDocumento("DNI31731313");
 	        if (persona == null) {
 	            throw new PersonaException("No se encontró la persona.");
 	        }

 	        List<Duenio> duenios = duenioRepository.findByPersona(persona);
 	        List<Inquilino> inquilinos = inquilinoRepository.findByPersona(persona);

 	        List<Unidad> unidadesComoDuenio = unidadRepository.findByDuenios(duenios);
 	        List<Unidad> unidadesComoInquilino = unidadRepository.findByInquilinos(inquilinos);

 	        Set<Unidad> unidades = new HashSet<>(unidadesComoDuenio);
 	        unidades.addAll(unidadesComoInquilino);

 	        for (Unidad unidad : unidades) {
 	            unidad.getDuenios().removeIf(duenio -> duenio.getPersona().equals(persona));
 	            unidad.getInquilinos().removeIf(inquilino -> inquilino.getPersona().equals(persona));
 	            unidadRepository.save(unidad);
 	        }

 	        personaRepository.delete(persona);
 	    };
 	}

 		




 	@Bean
 	public CommandLineRunner buscarReclamo(ReclamoRepository reclamoRepository){
 		return args -> {
 			System.out.println(
 					reclamoRepository.findById(1).orElseThrow(() -> new ReclamoException("No se encontro el reclamo")));
 		};
 	}
 

 	@Bean
 	public CommandLineRunner reclamosPorEdificio(EdificioRepository edificioRepository, ReclamoRepository reclamoRepository)
 	throws EdificioException {
 		return args -> {
 			Edificio edificio = edificioRepository.findById(1).orElseThrow(() -> new EdificioException("No se encontro la edificio."));
 			reclamoRepository.findAllByEdificio(edificio)
 					.stream()
 					.forEach(reclamo -> System.out.println(reclamo));
 		};
 	}
 
 
 	@Bean
 	public CommandLineRunner reclamosPorUnidad(UnidadRepository unidadRepository, ReclamoRepository reclamoRepository)
 			throws UnidadException {
 		return args -> {
 			Unidad unidad = unidadRepository.findById(61).orElseThrow(() -> new UnidadException("No se encontro la unidad."));
 			reclamoRepository.findAllByUnidad(unidad)
 					.stream()
 					.forEach(reclamo -> System.out.println(reclamo));
 		};
 	}
 
 
 
 	 @Bean
 	public CommandLineRunner reclamosPorPersona(ReclamoRepository reclamoRepository, PersonaRepository personaRepository)
 			throws PersonaException {
 
 		return args -> {
 			try {
 				Persona persona = personaRepository.findByDocumento("DNI30944156");
 				if (persona == null) {
 					throw new PersonaException("No se encontro la persona.");
 				}
 				reclamoRepository.findAllByUsuario(persona)
 						.stream()
 						.forEach(reclamo -> System.out.println(reclamo));
 			}catch (PersonaException e){System.err.println(e.getMessage()); }
 		};
 	}
 
 
 	@Bean
 	public CommandLineRunner agregarReclamoComun(ReclamoRepository reclamoRepository, UnidadRepository unidadRepository,
 	        EdificioRepository edificioRepository, PersonaRepository personaRepository)
 	        throws ReclamoException, EdificioException {
 	    return args -> {
 	        try {
 	            Persona persona = personaRepository.findByDocumento("DNI30944156");
 	            if (persona == null) {
 	                throw new PersonaException("No se encontro la persona.");
 	            }
 	            Edificio edificio = edificioRepository.findById(2).orElseThrow(() -> new EdificioException("No se encontro la edificio."));
 	            Unidad unidad = unidadRepository.findById(61).orElseThrow(() -> new UnidadException("No se encontro la unidad.")); 
 	            Reclamo reclamo = new Reclamo(persona, edificio, "Mogliani 425", "Plomeria", unidad);
 	            reclamoRepository.save(reclamo);
 	        } catch (EdificioException | PersonaException e) {
 	            System.err.println(e.getMessage());
 	        }
 	    };
 	}

 	@Bean
 	public CommandLineRunner agregarReclamoUnidad(ReclamoRepository reclamoRepository, PersonaRepository personaRepository, 
 	        EdificioRepository edificioRepository, UnidadRepository unidadRepository)
 	        throws ReclamoException, EdificioException {
 	    return args -> {
 	        try {
 	            Persona persona = personaRepository.findByDocumento("DNI30944156");
 	            if (persona == null) {
 	                throw new PersonaException("No se encontro la persona.");
 	            }
 	            Edificio edificio = edificioRepository.findById(2)
 	                    .orElseThrow(() -> new EdificioException("No se encontro el edificio."));
 	            Unidad unidad = unidadRepository.findById(197)
 	                    .orElseThrow(() -> new UnidadException("No se encontro la unidad."));
 	            if (!unidad.getEdificio().equals(edificio)) {
 	                throw new EdificioException("La unidad no pertenece a ese edificio");
 	            }
 	            Reclamo reclamo = new Reclamo(persona, edificio, "Arrayanes 1230", "Descripcion del reclamo", unidad);
 	            reclamoRepository.save(reclamo);
 	        } catch (EdificioException | PersonaException | UnidadException e) {
 	            System.err.println(e.getMessage());
 	        }
 	    };
 	}
 	
 	@Bean
 	public CommandLineRunner agregarImagenAReclamo(ReclamoRepository reclamoRepository, ImagenRepository imagenRepository){
 		return args -> {
 			try{
 				Reclamo reclamo = reclamoRepository.findById(1)
 						.orElseThrow(() -> new ReclamoException("No se encontro el reclamo."));
 				reclamoDAO.agregarImagen(reclamoRepository, imagenRepository, 1, "pileta", "problema");
 				reclamoRepository.save(reclamo);
 			}catch (ReclamoException e){
 				System.err.println(e.getMessage());
 			}
 		};
 	}
 
 	
 	 
 	@Bean
 	public CommandLineRunner cambiarEstado(ReclamoRepository reclamoRepository){
 		return args -> {
 			try{
 				Reclamo reclamo = reclamoRepository.findById(5)
 						.orElseThrow(() -> new ReclamoException("No se encontro el reclamo."));
 				reclamo.cambiarEstado(Estado.terminado);
 				reclamoRepository.save(reclamo);
 			}catch(ReclamoException e){
 				System.err.println(e.getMessage());
 			}
 		};
 	}
 	
 	 
 
 	@Bean
 	public CommandLineRunner eliminarReclamo(ReclamoRepository reclamoRepository, ImagenRepository imagenRepository){
 		return args -> {
 			try {
 				Reclamo reclamo = reclamoRepository.findById(1)
 						.orElseThrow(() -> new ReclamoException("No se encontro el reclamo."));
 				for(Imagen imagen : reclamo.getImagenes()){
 					imagenRepository.delete(imagen);
 				}
 				reclamoRepository.delete(reclamo);
 			}catch (ReclamoException e){
 				System.err.println(e.getMessage());
 			}
 		};
 	}
 
*/

}
