package com.example.springboot.controllers;



import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.entities.Orden;
import com.example.springboot.services.IOrden;

@RestController
@RequestMapping("/api")
public class OrdenController {
	
	@Autowired
	private IOrden ordenService;
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/ordenes/{id}")
	public Orden getOrden(@PathVariable Long id) {
		return this.ordenService.findOrdeById(id);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@DeleteMapping("/ordenes/{id}")
	public ResponseEntity<?> deleteOrden(@PathVariable Long id) {
		HashMap<String, String> response = new HashMap<>();
		this.ordenService.deleteOrdenById(id);
		response.put("response", "Se ha eliminado correctamente");
		return new ResponseEntity<HashMap<String, String>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/guardar")
	public Orden saveOrden(@RequestBody Orden orden) {
		return this.ordenService.saveOrden(orden);
	}

}
