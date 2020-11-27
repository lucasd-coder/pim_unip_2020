package com.pim.lucas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pim.lucas.domain.Cidade;
import com.pim.lucas.domain.Cliente;
import com.pim.lucas.domain.Endereco;
import com.pim.lucas.domain.enums.Perfil;
import com.pim.lucas.domain.enums.TipoCliente;
import com.pim.lucas.dto.ClienteDTO;
import com.pim.lucas.dto.ClienteNewDTO;
import com.pim.lucas.repositories.ClienteRepository;
import com.pim.lucas.repositories.EnderecoRepository;
import com.pim.lucas.security.UserSS;
import com.pim.lucas.services.exceptions.AuthorizationException;
import com.pim.lucas.services.exceptions.DataIntegrityException;
import com.pim.lucas.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
		
	
	@Autowired  
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
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
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEndereco());
		return obj;
		
	}

	
	public Cliente update(Cliente obj) {
		Cliente newObj =find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		
		if(id == 2) {
			throw new DataIntegrityException("Proibido excluir");
		} 	

		try {
			repo.deleteById(id);
		} 
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
			
		}
	}
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Cliente findByEmail(String email) {
		
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Cliente obj = repo.findByEmail(email);
		if(obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Cliente.class.getName());
		}
		return obj;
	}

	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);		

	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getDataNascimento() ,objDto.getEmail(), null, null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(),  objDto.getDataNascimento() ,objDto.getEmail(), objDto.getCpfOuCnpj(),TipoCliente.toEnum(objDto.getTipo()), pe.encode(objDto.getSenha()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradoura(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEndereco().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if(objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if(objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}

	
	
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	


}

