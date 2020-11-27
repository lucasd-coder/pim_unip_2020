package com.pim.lucas.services.validation;


import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.pim.lucas.domain.Conta;
import com.pim.lucas.domain.enums.TipoCliente;
import com.pim.lucas.dto.ContaDTO;
import com.pim.lucas.repositories.ContaRepository;
import com.pim.lucas.resources.exception.FieldMessage;
import com.pim.lucas.services.validation.utils.BR;

public class ContaInsertValidator implements ConstraintValidator<ContaInsert, ContaDTO> {

	@Autowired
	private ContaRepository repo;
		

	@Override
	public void initialize(ContaInsert ann) {
	}
	

	@Override
	public boolean isValid(ContaDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			 list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			 list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		
		Conta aux = repo.findByCpfOuCnpj(objDto.getCpfOuCnpj());
		if(aux != null) {		
			list.add(new FieldMessage("cliente_conta", "Conta já existente"));
		} 
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
		
		

	}
}
