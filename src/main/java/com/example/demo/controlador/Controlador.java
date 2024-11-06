package com.example.demo.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.datos.*;
import com.example.demo.modelo.*;
import com.example.demo.views.*;
import exceptions.*;

@RestController
@RequestMapping("/")
public class Controlador {
	
	private static Controlador instancia;
	
	private Controlador() {}
	
	public static Controlador getInstancia() {
		if(instancia == null)
			instancia = new Controlador();
		return instancia;
	}
	
	
	@Autowired 
	private EdificioDAO edificioDAO;
	@Autowired
	private EdificioRepository edificioRepository;
	@Autowired 
	private UnidadDAO unidadDAO;
	@Autowired
	private UnidadRepository unidadRepository;
	@Autowired 
	private PersonaDAO personaDAO;
	@Autowired 
	private PersonaRepository personaRepository;
	@Autowired 
	private InquilinoDAO inquilinoDAO;
	@Autowired 
	private InquilinoRepository inquilinoRepository;
	@Autowired 
	private DuenioDAO duenioDAO;
	@Autowired 
	private DuenioRepository duenioRepository;
	@Autowired 
	private ReclamoDAO reclamoDAO;
	@Autowired 
	private ReclamoRepository reclamoRepository;
	@Autowired 
	private ImagenDAO imagenDAO;
	@Autowired 
	private ImagenRepository imagenRepository;
	
    @GetMapping("/")
    public String mensaje() {
    	return "Bienvenido"; 
    }
    
    @GetMapping("/login")
    public ResponseEntity<String> Login(@RequestParam(defaultValue = "juanp") String nombreUser, @RequestParam(defaultValue = "pass123") String contrasenia) throws PersonaException {
    	Persona p = buscarUsuario(nombreUser);
	    	if(p.getContrasenia().equals(contrasenia)) {
	            return ResponseEntity.ok(p.toString());
	    	}
	        return ResponseEntity.ok("El usuario y la contrasenia no coinciden");

    }
    

    @GetMapping("/edificios")
    public ResponseEntity<List<Edificio>> getAllEdi() {
        List<Edificio> edificios = edificioDAO.getAllEdi(edificioRepository);
        return new ResponseEntity<>(edificios, HttpStatus.OK);
    }
   
    

    @GetMapping("/unidades")
    public ResponseEntity<List<Unidad>> getAllUnidades() {
        List<Unidad> unidades = unidadDAO.getAllUnidades(unidadRepository);
        return new ResponseEntity<>(unidades, HttpStatus.OK);
    }
     
    

    @GetMapping("/duenios")
    public ResponseEntity<List<Duenio>> getAllDuenios() {
        List<Duenio> duenios = duenioDAO.getAllDuenios(duenioRepository);
        return new ResponseEntity<>(duenios, HttpStatus.OK);
    }
    

    @GetMapping("/personas")
    public ResponseEntity<List<Persona>> getAllPersonas() {
        List<Persona> personas = personaDAO.getAllPersonas();
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }
    

    @GetMapping("/inquilinos")
    public ResponseEntity<List<Inquilino>> getAllInquilinos() {
        List<Inquilino> inquilinos = inquilinoDAO.getAllInquilinos();
        return new ResponseEntity<>(inquilinos, HttpStatus.OK);
    }
    

    @GetMapping("/getUnidadesPorEdificios")
    public List<UnidadView> getUnidadesPorEdificio(@RequestParam(defaultValue = "2") Integer codigo) throws EdificioException {        // Se usará el valor predeterminado 1 si no se proporciona el parámetro
        List<UnidadView> resultado = new ArrayList<>();
        Edificio edificio = buscarEdificio(codigo);
        List<Unidad> unidades = edificio.getUnidades();
        for (Unidad unidad : unidades) {
            resultado.add(unidad.toView());
        }
        return resultado;
    }
    

    @GetMapping("/habilitadosPorEdificios")
	public List<PersonaView> habilitadosPorEdificio(@RequestParam(defaultValue = "2") int codigo) throws EdificioException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Edificio edificio = buscarEdificio(codigo);
		Set<Persona> habilitados = edificio.habilitados();
		for(Persona persona : habilitados)
			resultado.add(persona.toView());
		return resultado;
	}


    @GetMapping("/dueniosPorEdificios")
	public List<PersonaView> dueniosPorEdificio(@RequestParam(defaultValue = "2") int codigo) throws EdificioException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Edificio edificio = buscarEdificio(codigo);
		Set<Persona> duenios = edificio.duenios();
		for(Persona persona : duenios)
			resultado.add(persona.toView());
		return resultado;
	}


    @GetMapping("/habitantesPorEdificio")
	public List<PersonaView> habitantesPorEdificio(@RequestParam(defaultValue = "2") int codigo) throws EdificioException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Edificio edificio = buscarEdificio(codigo);
		Set<Persona> habitantes = edificio.duenios();
		for(Persona persona : habitantes)
			resultado.add(persona.toView());
		return resultado;
	}


    @GetMapping("/dueniosPorUnidad")
	public List<DuenioView> dueniosPorUnidad(@RequestParam(defaultValue = "2") int codigo, @RequestParam(defaultValue = "2") int piso, @RequestParam(defaultValue = "202") int numero) throws UnidadException{
		List<DuenioView> resultado = new ArrayList<DuenioView>();
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		List<Duenio> duenios = unidad.getDuenios();
		for(Duenio duenio : duenios)
			resultado.add(duenio.toView());
		return resultado;
	}
    
    //Hasta aca andaaa

    @GetMapping("/inquilinosPorUnidad")
	public List<InquilinoView> inquilinosPorUnidad(@RequestParam(defaultValue = "2") int codigo, @RequestParam(defaultValue = "2") int piso, @RequestParam(defaultValue = "202") int numero) throws UnidadException{
		List<InquilinoView> resultado = new ArrayList<InquilinoView>();
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		List<Inquilino> inquilinos = unidad.getInquilinos();
		for(Inquilino inquilino : inquilinos)
			resultado.add(inquilino.toView());
		return resultado;
	}
	
    //Anda
    @GetMapping("/transferirUnidadDuenio")
    public ResponseEntity<String> transferirUnidadDuenio(
        @RequestParam(defaultValue = "2") int codigo,
        @RequestParam(defaultValue = "2") int piso,
        @RequestParam(defaultValue = "202") int numero,
        @RequestParam(defaultValue = "DOC002") String documento,
        @RequestParam(defaultValue = "DOC004") String docNuevo) throws UnidadException, PersonaException {
        try {
            Unidad unidad = buscarUnidad(codigo, piso, numero);
            System.out.println("Unidad encontrada: " + unidad);


            Duenio duenioActual = buscarDuenio(documento);
            System.out.println("Dueño actual encontrado: " + duenioActual);
            unidad.eliminarDuenio(duenioActual);


            Persona nuevoDuenioPersona = buscarPersona(docNuevo);
            if (nuevoDuenioPersona == null) {
                throw new PersonaException("No se encontró la persona con el documento: " + docNuevo);
            }


            Duenio nuevoDuenio = buscarDuenio(docNuevo);
            nuevoDuenio.setPersona(nuevoDuenioPersona);
            
            unidad.transferir(nuevoDuenio);

            // Guardar la unidad actualizada en la base de datos
            unidadDAO.save(unidadRepository, unidad);

            System.out.println("Transferencia de dueño realizada con éxito.");
            return ResponseEntity.ok("Transferencia realizada con éxito");
        } catch (UnidadException | PersonaException e) {
            System.out.println("Error durante la transferencia: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    
    //ANDA
    @GetMapping("/transferirUnidadInquilino")
    public ResponseEntity<String> transferirUnidadInquilino(@RequestParam(defaultValue = "2") int codigo,
                                                            @RequestParam(defaultValue = "2") int piso,
                                                            @RequestParam(defaultValue = "202") int numero,
                                                            @RequestParam(defaultValue = "DOC002") String documento,
                                                            @RequestParam(defaultValue = "46741415") String docNuevo) throws UnidadException, PersonaException {
        try {
         
            System.out.println("Buscando unidad...");
            Unidad unidad = buscarUnidad(codigo, piso, numero);
            
            
            System.out.println("Buscando inquilino actual...");
            Inquilino inquilino = buscarInquilino(documento);
            unidad.eliminarInquilino(inquilino);
            
            
            System.out.println("Buscando nuevo inquilino...");
            Persona nuevoInquilinoPersona = buscarPersona(docNuevo);
            
            
            Inquilino nuevoInquilino = new Inquilino();
            nuevoInquilino.setPersona(nuevoInquilinoPersona);
            
            
            System.out.println("Realizando la transferencia...");
            unidad.transferir(nuevoInquilino);
            
           
            unidadDAO.save(unidadRepository, unidad);
            
            System.out.println("Transferencia realizada con éxito.");
            return ResponseEntity.ok("Transferencia exitosa");
        } catch (UnidadException | PersonaException e) {
            
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    
    //Anda
    @GetMapping("/agregarDuenioUni")
	public ResponseEntity<String> agregarDuenioUnidad(@RequestParam(defaultValue = "2") int codigo, @RequestParam(defaultValue = "2") int piso, @RequestParam(defaultValue = "202") int numero, @RequestParam(defaultValue = "DOC002") String documento) throws UnidadException, PersonaException {
        try {

	    	Unidad unidad = buscarUnidad(codigo, piso, numero);
	    	
		    Persona nuevoDuenioPersona = buscarPersona(documento);
			Duenio nuevoDuenio = new Duenio();
			nuevoDuenio = buscarDuenio(documento);
		    nuevoDuenio.setPersona(nuevoDuenioPersona);
			unidad.agregarDuenio(nuevoDuenio);
			unidadDAO.save(unidadRepository, unidad);
			
            return ResponseEntity.ok("Se agrego el duenio con exito");
        } catch (UnidadException | PersonaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

	}
    //Anda
    @GetMapping("/alquilarUnidad")
    public ResponseEntity<String> alquilarUnidadInquilino(
        @RequestParam(defaultValue = "5") int codigo,
        @RequestParam(defaultValue = "5") int piso,
        @RequestParam(defaultValue = "505") int numero,
        @RequestParam(defaultValue = "DOC005") String documento) throws UnidadException, PersonaException {
        try {
            // Buscar la unidad en base a los parámetros
            Unidad unidad = buscarUnidad(codigo, piso, numero);
            System.out.println("Estado de unidad antes de alquilar: habitado=" + unidad.estaHabitado());

            // Verificar si la unidad ya está ocupada
            if (unidad.estaHabitado()) {
                return ResponseEntity.ok("La unidad ya está alquilada");
            }

            // Buscar la persona que será el nuevo inquilino
            Persona nuevoInquilinoPersona = buscarPersona(documento);
            
            // Crear y asignar el nuevo inquilino
            Inquilino nuevoInquilino = new Inquilino();
            nuevoInquilino.setPersona(nuevoInquilinoPersona);
            
            // Alquilar la unidad
            unidad.alquilar(nuevoInquilino);

            // Guardar el cambio en la base de datos
            unidadDAO.save(unidadRepository, unidad);
            System.out.println("Alquiler realizado con éxito para documento: " + documento);

            return ResponseEntity.ok("Alquiler exitoso");

        } catch (UnidadException | PersonaException e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    //Anda
    @GetMapping("/agregarInquilunoUni")
	public ResponseEntity<String> agregarInquilinoUnidad(@RequestParam(defaultValue = "9") int codigo, @RequestParam(defaultValue = "9") int piso, @RequestParam(defaultValue = "909") int numero, @RequestParam(defaultValue = "DOC010") String documento) throws UnidadException, PersonaException{
        try {
	    	Unidad unidad = buscarUnidad(codigo, piso, numero);
	  
			Persona nuevoInquilino = buscarPersona(documento);
			
			Inquilino nuevo = new Inquilino();
			nuevo = buscarInquilino(documento);
			nuevo.setPersona(nuevoInquilino);
			unidad.agregarInquilino(nuevo);
			unidadDAO.save(unidadRepository, unidad);
			
            return ResponseEntity.ok("Se agrego el inquilino con exito");

        } catch (UnidadException | PersonaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
	}

    //Anda
    @GetMapping("/liberarUnidad")
    public ResponseEntity<String> liberarUnidad(
        @RequestParam(defaultValue = "1") int codigo,
        @RequestParam(defaultValue = "1") int piso,
        @RequestParam(defaultValue = "101") int numero) throws UnidadException {
        try {
            // Buscar la unidad en base a los parámetros
            Unidad unidad = buscarUnidad(codigo, piso, numero);
            System.out.println("Unidad encontrada: " + unidad);

            // Verificar si la unidad ya está liberada (no habitada)
            if (!unidad.estaHabitado()) {
                return ResponseEntity.ok("La unidad ya está liberada");
            }

            // Liberar la unidad
            unidad.liberar();

            // Guardar el cambio en la base de datos
            unidadDAO.save(unidadRepository, unidad);
            System.out.println("Estado de la unidad actualizado a liberado.");

            return ResponseEntity.ok("Se ha liberado la unidad con éxito");

        } catch (UnidadException e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



	
    //Anda
    @GetMapping("/agregarPersona")
	public ResponseEntity<String> agregarPersona(@RequestParam(defaultValue = "DNI123456") String documento, @RequestParam(defaultValue = "Cacho") String nombre, @RequestParam(defaultValue = "usuario123456") String nombreUser, @RequestParam(defaultValue = "123456") String contrasenia, @RequestParam(defaultValue = "general") TipoUsuario tipoUser) throws PersonaException {
		Persona persona = new Persona(documento, nombre, nombreUser, contrasenia, tipoUser);
		personaDAO.save(personaRepository, persona);
		
		return ResponseEntity.ok("Se agrego la persona con exito");
  
	}
    
    //Anda
    @GetMapping("/eliminarPersona")
	public ResponseEntity<String> eliminarPersona(@RequestParam(defaultValue = "46741415") String documento) throws PersonaException {
		Persona persona = buscarPersona(documento);
		personaDAO.delete(personaRepository, persona);
		
		return ResponseEntity.ok("Se elimino la persona con exito");

	}

    //Anda
    @GetMapping("/reclamosEdificio")
	public List<ReclamoView> reclamosPorEdificio(@RequestParam(defaultValue = "1") int codigo) throws EdificioException{
		List<ReclamoView> resultado = new ArrayList<ReclamoView>();
	    List<Reclamo> reclamos = reclamoDAO.getAllReclamos(reclamoRepository);
	    for (Reclamo reclamo : reclamos) {
	        if(reclamo.getEdificio().getCodigo() == codigo) {
	        	resultado.add(reclamo.toView());
	        }
	    }
		return resultado;
	}

	
    //Anda
    @GetMapping("/reclamosUnidad")
	public List<ReclamoView> reclamosPorUnidad(@RequestParam(defaultValue = "1") int codigo) {
		List<ReclamoView> resultado = new ArrayList<ReclamoView>();
	    List<Reclamo> reclamos = reclamoDAO.getAllReclamos(reclamoRepository);
	    for (Reclamo reclamo : reclamos) {
	        if(reclamo.getUnidad().getId() == codigo) {
	        	resultado.add(reclamo.toView());
	        }
	    }
		return resultado;
	}
	
    //Anda
    @GetMapping("/reclamosNumero")
	public ReclamoView reclamosPorNumero(@RequestParam(defaultValue = "4") int numero) {
	    List<Reclamo> reclamos = reclamoDAO.getAllReclamos(reclamoRepository);
	    for (Reclamo reclamo : reclamos) {
	        if(reclamo.getReclamoId() == numero) {
	        	return reclamo.toView();
	        }
	    }
		return null;
	}
	
    //Anda
    @GetMapping("/reclamosPersona")
	public List<ReclamoView> reclamosPorPersona (@RequestParam(defaultValue = "DOC004") String documento) {
		List<ReclamoView> resultado = new ArrayList<ReclamoView>();
	    List<Reclamo> reclamos = reclamoDAO.getAllReclamos(reclamoRepository);
	    for (Reclamo reclamo : reclamos) {
	        if(reclamo.getUsuario().getDocumento().equals(documento)) {
	        	resultado.add(reclamo.toView());
	        }
	    }
		return resultado;
	}

    //Anda
    @GetMapping("/agregarReclamo")
	public ResponseEntity<String> agregarReclamo (@RequestParam(defaultValue = "4") int codigo, @RequestParam(defaultValue = "4") int piso, @RequestParam(defaultValue = "404") int numero,  @RequestParam(defaultValue = "DNI123456") String documento, @RequestParam(defaultValue = " Ascensor ") String ubicacion, @RequestParam(defaultValue = "No anda") String descripcion) throws EdificioException, UnidadException, PersonaException {
		Edificio edificio = buscarEdificio(codigo);
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		Reclamo reclamo = new Reclamo(persona, edificio, ubicacion, descripcion, unidad);
		reclamoDAO.save(reclamoRepository, reclamo);

	    return ResponseEntity.ok("Se agrego el reclamo con exito");
		
	}
    
    //anda
    @GetMapping("/agregarImagenReclamo")
	public ResponseEntity<String> agregarImagenAReclamo(@RequestParam(defaultValue = "img92") String direccion, @RequestParam(defaultValue = "jpg") String tipo, @RequestParam(defaultValue = "11") int idReclamo) throws ReclamoException {
		Reclamo reclamo = buscarReclamoByID(idReclamo);
		reclamoDAO.agregarImagen(reclamoRepository, imagenRepository, idReclamo, direccion, tipo);
		
	    return ResponseEntity.ok("Se agrego la imagen con exito");

	}
	
    
    //Anda
    @GetMapping("/cambiarEstado")
	public ResponseEntity<String> cambiarEstado(@RequestParam(defaultValue = "4") int numero, @RequestParam(defaultValue = "abierto") Estado estado) throws ReclamoException {
		Reclamo reclamo = buscarReclamoByID(numero);
		reclamo.cambiarEstado(estado);
		reclamoDAO.save(reclamoRepository, reclamo);
		
	    return ResponseEntity.ok("Se cambio el estado con exito");

	}
	
    //Anda
    @GetMapping("/buscarEdificio")
    public Edificio buscarEdificio(@RequestParam (defaultValue = "4") int codigo) throws EdificioException {
    	List<Edificio> edificios = edificioDAO.getAllEdi(edificioRepository);
    	for (Edificio e : edificios) {
    		if(e.getCodigo() == codigo) {
    			return e;
    		}
    	}
    	throw new EdificioException("No se encontro el edificio con ese codigo");
	}

    //anda
    @GetMapping("/buscarUnidad")
    public Unidad buscarUnidad(@RequestParam(defaultValue = "1")int codigo, @RequestParam(defaultValue = "1") int piso, @RequestParam(defaultValue = "101") int numero) throws UnidadException {
        List<Unidad> unidades = unidadDAO.getAllUnidades(unidadRepository);
        
        for (Unidad u : unidades) {
            // Comparar solo si piso y numero son no nulos
        	if(u.getId() == codigo && u.getPiso() ==  piso && u.getNumero() == numero) {
    			return u;
        	}
        
        }
        throw new UnidadException("No se encontró la unidad con esas características");
    }
	
    //Anda
    @GetMapping("/buscarPersona")
    public Persona buscarPersona(@RequestParam(defaultValue = "DOC004") String documento) throws PersonaException {
		List<Persona> persona = personaDAO.getAllPersonas();
		for(Persona p : persona) {
			if(p.getDocumento().equals(documento)) {
				return p;
			}
		}
	    throw new PersonaException("No se encontro la persona con ese documento");
	}
    //Anda
    @GetMapping("/buscarUsuario")
    public Persona buscarUsuario(@RequestParam(defaultValue = "martal") String nombreUser) throws PersonaException {
		List<Persona> persona = personaDAO.getAllPersonas();
		for(Persona p : persona) {
			if(p.getNombreUser().equals(nombreUser)) {
				return p;
			}
		}
	    throw new PersonaException("No se encontro la persona con ese usuario");
	}
    
	
    //Anda
    @GetMapping("/buscarDuenio")
    public Duenio buscarDuenio(@RequestParam(defaultValue = "DOC001") String documento) throws PersonaException {
		List<Duenio> buscarDuenios = duenioDAO.getAllDuenios(duenioRepository);
		for(Duenio d : buscarDuenios) {
			if(d.getPersona().getDocumento().equals(documento)) {
				return d;
			}
		}
	    throw new PersonaException("No se encontro la persona con ese documento");
	}

    //Anda
    @GetMapping("/buscarInquilino")
    public Inquilino buscarInquilino(@RequestParam(defaultValue = "DOC010") String documento) throws PersonaException {
		List<Inquilino> buscarInquilinos = inquilinoDAO.getAllInquilinos();
		for(Inquilino i : buscarInquilinos) {
			if(i.getPersona().getDocumento().equals(documento)) {
				return i;
			}
		}
	    throw new PersonaException("No se encontro la persona con ese documento");
	}
    
    //Anda
    @GetMapping("/buscarReclamo")
    public Reclamo buscarReclamoByID(@RequestParam(defaultValue = "2") int numero) throws ReclamoException {
        List<Reclamo> reclamos = reclamoDAO.getAllReclamos(reclamoRepository);
        for (Reclamo r : reclamos) { 
            if (r.getReclamoId() == numero) {
                return r;
            }
        
        }
        throw new ReclamoException("No se encontró el reclamo con ese id");
    }


	
	
}