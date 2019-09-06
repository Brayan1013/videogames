package com.example.springboot.controllers;



import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.springboot.entities.Role;
import com.example.springboot.entities.User;
import com.example.springboot.services.IRole;
import com.example.springboot.services.IUplodaImage;
import com.example.springboot.services.IUser;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private IUser userService;
	
	@Autowired
	private IUplodaImage uploadImageService;
	
	@Autowired
	private IRole roleService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/usuarios")
	public List<User> getUsuarios(){
		return userService.findAll();
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/usuarios/{id}")
	public User getUser(@PathVariable Long id) {
		return userService.findById(id);
		
	}
	
	@GetMapping("/usuario/{username}")
	public boolean existUsername(@PathVariable(name="username")String username) {
		User user = userService.findByUsername(username);
		if(user == null) {
			return false;
		}
		return true;
	}
	
	@GetMapping("usuario/email/{email}")
	public boolean existEmail(@PathVariable(name="email")String email) {
		User user = userService.findByEmail(email);
		if(user == null) {
			return false;
		}
		
		return true;
	}
	
	
	@PostMapping("/usuarios")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult result) {
		Map<String, Object> response = new HashMap<String, Object>();
		//User nuevoUsuario = null;
		List<Role> allRoles = roleService.findAll();
		
		List<Role> user_Role = Arrays.asList(allRoles.get(1));
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors().stream()
					.map(error -> error.getField() + ":" + error.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errores", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			try {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				user.setEnable(true);
				user.setRoles(user_Role);
				 userService.save(user);
			} catch (DataIntegrityViolationException e) {
				response.put("mensaje", "El usuario ya existe en la base de datos");
				response.put("error", e.getMostSpecificCause().getMessage());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (DataAccessException e) {
			response.put("mensaje", "ha ocurrio un error en la base de datos");
			response.put("error", e.getMessage() + ":" + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		response.put("mensaje", "Usuario creado con exito");
	
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	@PostMapping("upload/image/{id}")
	public ResponseEntity<?> uploadImageUser(@PathVariable Long id, @RequestParam(name="foto")MultipartFile foto){
		
		User user = userService.findById(id);
		String fotoNombre = null;
		
		if(user != null) {
			try {
				fotoNombre = uploadImageService.guardarImage(foto);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(user.getFoto() != null && user.getFoto().length() > 0) {
			uploadImageService.deleteImageAnterior(user.getFoto());
		}
		user.setFoto(fotoNombre);
		userService.save(user);
		
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}
	
	@GetMapping("image/{nombre:.+}")
	public ResponseEntity<Resource> obtenerUserImage(@PathVariable String nombre){
		Resource resource = null;
		try {
			 resource = uploadImageService.showImage(nombre);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + nombre + "\"");
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}

}
