package com.pim.lucas.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pim.lucas.domain.Conta;
import com.pim.lucas.dto.ContaDTO;
import com.pim.lucas.services.ContaService;


@RestController
@RequestMapping("/contas")
public class ContaResource {
	
	@Autowired
	private ContaService service;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Conta> find(@PathVariable Integer id) {	
		Conta obj = service.find(id);
		return ResponseEntity.ok().body(obj);		
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ContaDTO objDto) {
		 Conta obj = service.fromDTO(objDto);
		 obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}


}
