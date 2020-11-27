package com.pim.lucas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pim.lucas.domain.Conta;
import com.pim.lucas.domain.enums.Perfil;
import com.pim.lucas.domain.enums.TipoCliente;
import com.pim.lucas.dto.ContaDTO;
import com.pim.lucas.repositories.ContaRepository;
import com.pim.lucas.security.UserSS;
import com.pim.lucas.services.exceptions.AuthorizationException;
import com.pim.lucas.services.exceptions.ObjectNotFoundException;

@Service
public class ContaService {

	@Autowired
	private ContaRepository repo;

	@Autowired
	private ClienteService clienteService;

	public Conta find(Integer id) {
		
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Conta> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Conta.class.getName()));
	}
	
	

	@Transactional
	public Conta insert(Conta obj) {
		obj.setId(null);
		obj.getNumConta();
		obj.getNomeBanco();
		obj.getNomeCorrentista();
		obj.getCpfOuCnpj();
		obj.getTipo();
		obj.setSaldo(0.0);
		obj.setCliente_conta(clienteService.find(obj.getCliente_conta().getId()));
		obj = repo.save(obj);

		return obj;
	}

	public Conta fromDTO(ContaDTO objDto) {
		return new Conta(objDto.getId(), objDto.getNumConta(), objDto.getNomeBanco(), objDto.getNomeCorrentista(),
				objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()), objDto.getSaldo(), objDto.getCliente_conta());
	}

}
