package com.pim.lucas.services.validation;


import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.pim.lucas.domain.Cliente;
import com.pim.lucas.domain.enums.TipoCliente;
import com.pim.lucas.dto.AdminDTO;
import com.pim.lucas.repositories.ClienteRepository;
import com.pim.lucas.resources.exception.FieldMessage;
import com.pim.lucas.services.validation.utils.BR;


public class AdminInsertValidator implements ConstraintValidator<AdminInsert, AdminDTO> {
		
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(AdminInsert ann) {
	}

	@Override
	public boolean isValid(AdminDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();

		// inclua os testes aqui, inserindo erros na lista
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			 list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			 list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

	

}
