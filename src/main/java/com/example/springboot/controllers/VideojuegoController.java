package com.example.springboot.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.springboot.entities.Videojuego;
import com.example.springboot.services.IVideojuego;

@RestController
@RequestMapping(path = "/api")
public class VideojuegoController {

	@Autowired
	private IVideojuego videojuegoService;
	
	@GetMapping("/videojuegos/paginador/{page}")
	public Page<Videojuego> getAllVideojuegos(@PathVariable(name="page") int page){
		return videojuegoService.findAll(PageRequest.of(page, 6));
	}
	
	@GetMapping("/videojuegos")
	public List<Videojuego> getVideojuegos() {
		return videojuegoService.findAll();
	}

	@GetMapping("/videojuego/{id}")
	public ResponseEntity<?> getVideojuego(@PathVariable(name = "id") Long id) {
		Videojuego videojuego = null;
		Map<String, Object> response = new HashMap<String, Object>();

		try {
			videojuego = videojuegoService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Ocurrio un error en la base de datos");
			response.put("error", e.getMessage() + ":" + e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (videojuego == null) {
			response.put("mensaje", "el videojuego con el id: " + id + " no se ha encontado");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Videojuego>(videojuego, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/videojuego")
	public ResponseEntity<?> save(@Valid @RequestBody Videojuego videojuego, BindingResult result) {
		Map<String, Object> response = new HashMap<String, Object>();
		Videojuego videojuegoNuevo = null;

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(e -> "error en el campo: " + e.getField() + " " + e.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errores", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			if(videojuego.getCantidad() == 0) {
				videojuego.setDisponible(false);  
			}
			try {
				videojuego.setDisponible(true);
				videojuegoNuevo = videojuegoService.save(videojuego);
				
			} catch (DataIntegrityViolationException e) {
				response.put("mensaje", "El videojuego ya existe en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (DataAccessException e) {
			response.put("mensaje", "Ha ocurrido un error en el servidor");
			response.put("error", e.getMessage() + ":" + e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Videojuego creado con exito");
		response.put("Videojuego", videojuegoNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("videojuego/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
		Videojuego videojuego = null;
		Map<String, Object> resposne = new HashMap<String, Object>();

		try {
			videojuego = videojuegoService.findById(id);
		} catch (DataAccessException e) {
			resposne.put("mensaje", "Ha ocurrido un error en el servidor");
			resposne.put("error", e.getMessage() + ":" + e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(resposne, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (videojuego == null) {
			resposne.put("mensaje", "El videojuego con el id: " + id + " no existe");
			return new ResponseEntity<Map<String, Object>>(resposne, HttpStatus.NOT_FOUND);
		}

		videojuegoService.deleteByEntity(videojuego);
		resposne.put("mesaje", "el cliente con el id: " + id + " ha sido eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(resposne, HttpStatus.OK);

	}
	
	@Secured("ROLE_ADMIN")
	@PutMapping("videojuego/{id}")
	public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @Valid @RequestBody Videojuego videojuego,
			BindingResult result) {
		Videojuego videojuegoActual = null;
		Videojuego videojuegoActualizado = null;
		Map<String, Object> response = new HashMap<String, Object>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(e -> "error en el campo: " + e.getField() + " " + e.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("error", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			videojuegoActual = videojuegoService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Ocurrio un error en el servidor");
			response.put("error", e.getMessage() + ":" + e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (videojuegoActual == null) {
			response.put("mensaje", "no se ha encontrado el videojuego con el id: " + id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		videojuegoActual.setNombre(videojuego.getNombre());
		videojuegoActual.setPrecio(videojuego.getPrecio());
		videojuegoActual.setConsola(videojuego.getConsola());
		videojuegoActual.setDisponible(videojuego.isDisponible());
		videojuegoActualizado = videojuegoService.save(videojuegoActual);

		response.put("mensaje", "Videojuego actualizado correctamente");
		response.put("Videojuego", videojuegoActualizado);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/videojuego/upload/{id}")
	public ResponseEntity<?> upload(@RequestParam(name = "foto") MultipartFile foto,
			@PathVariable(name = "id") Long id) {
		Map<String, Object> response = new HashMap<>();
		Videojuego videojuego = videojuegoService.findById(id);
		Videojuego videjuegoActualizado = null;

		if (videojuego == null) {
			response.put("Error", "El id: " + "no se ha encontrado en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (!foto.isEmpty()) {
			String nombreFoto = UUID.randomUUID() + "_" + foto.getOriginalFilename().replace(" ", "");
			Path path = Paths.get("imagenes").resolve(nombreFoto).toAbsolutePath();
			try {
				Files.copy(foto.getInputStream(), path);
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (videojuego.getFoto() != null && videojuego.getFoto().length() > 0) {
				Path ruta = Paths.get("imagenes").resolve(videojuego.getFoto()).toAbsolutePath();
				File imagenAnterior = ruta.toFile();
				if (imagenAnterior.exists() && imagenAnterior.canRead()) {
					imagenAnterior.delete();
				}
			}
			videojuego.setFoto(nombreFoto);
			videjuegoActualizado = videojuegoService.save(videojuego);
		}

		response.put("mensaje", "Videojuego actualizado correctamente");
		response.put("Videojuego", videjuegoActualizado);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
	
	
	@GetMapping("/videojuegos/image/{fileName:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String fileName){
		
		Path path = Paths.get("imagenes").resolve(fileName).toAbsolutePath();
		Resource resource = null;
		
		try {
			 resource = new UrlResource(path.toUri());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!resource.exists() && !resource.isReadable()) {
			throw new RuntimeException("No se pudo cargar la foto: " + fileName);
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		
		httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachent; fileName=\"" + fileName + "\"");
		
		return new ResponseEntity<Resource>(resource, httpHeaders, HttpStatus.OK);
	}

}
