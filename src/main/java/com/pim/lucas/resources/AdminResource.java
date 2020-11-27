package com.pim.lucas.resources;


import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pim.lucas.domain.Cliente;
import com.pim.lucas.dto.AdminDTO;
import com.pim.lucas.services.AdminService;


@RestController
@RequestMapping("/admins")
public class AdminResource {
	
	

	@Autowired
	private AdminService service;
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/4b19fc64e8b955d9",method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody AdminDTO objDto) {
		Cliente obj = service.fromDTO2(objDto);		
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}


}
