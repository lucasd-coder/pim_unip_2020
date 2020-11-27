package com.pim.lucas.dto;
import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.pim.lucas.domain.Cliente;
import com.pim.lucas.domain.Conta;
import com.pim.lucas.services.validation.ContaInsert;


@ContaInsert
public class ContaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String numConta;
	
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String nomeBanco;
	
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String nomeCorrentista;
	
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String cpfOuCnpj;
	private Integer tipo;

	private Cliente cliente_conta;

	private Double saldo;
	
	public ContaDTO() {		
	}
	
	public ContaDTO(Conta obj) {
		id = obj.getId();
		numConta = obj.getNumConta();
		nomeBanco = obj.getNomeBanco();
		nomeCorrentista = obj.getNomeCorrentista();
		cpfOuCnpj = obj.getCpfOuCnpj();
		cliente_conta = obj.getCliente_conta();
		saldo = obj.getSaldo();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumConta() {
		return numConta;
	}

	public void setNumConta(String numConta) {
		this.numConta = numConta;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public String getNomeCorrentista() {
		return nomeCorrentista;
	}

	public void setNomeCorrentista(String nomeCorrentista) {
		this.nomeCorrentista = nomeCorrentista;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Cliente getCliente_conta() {
		return cliente_conta;
	}

	public void setCliente_conta(Cliente cliente_conta) {
		this.cliente_conta = cliente_conta;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	

	
	

}
