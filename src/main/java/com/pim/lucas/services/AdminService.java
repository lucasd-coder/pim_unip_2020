package com.pim.lucas.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pim.lucas.domain.Cliente;
import com.pim.lucas.domain.enums.Perfil;
import com.pim.lucas.domain.enums.TipoCliente;
import com.pim.lucas.dto.AdminDTO;
import com.pim.lucas.repositories.ClienteRepository;
import com.pim.lucas.security.UserSS;
import com.pim.lucas.services.exceptions.AuthorizationException;
import com.pim.lucas.services.exceptions.ObjectNotFoundException;



@Service
public class AdminService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
		
	
	@Autowired  
	private ClienteRepository repo;
	
	
	public Cliente find(Integer id) {
		
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}

		
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())
				);
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj.getNome();
		obj.getDataNascimento();
		obj.getEmail();
		obj.getCpfOuCnpj();
		obj.getTipo();
		pe.encode(obj.getSenha());
		obj.addPerfil(Perfil.ADMIN);
		obj = repo.save(obj);
		
		return obj;
		
	}
	
		
	public Cliente fromDTO2(AdminDTO objDto) {
		Cliente cli = new Cliente( objDto.getId(), objDto.getNome(), objDto.getDataNascimento() ,objDto.getEmail(), objDto.getCpfOuCnpj(),TipoCliente.toEnum(objDto.getTipo()), pe.encode(objDto.getSenha()));
		
		cli.getTelefones().add(objDto.getTelefone1());
		if(objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if(objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}


}
