package com.pim.lucas.domain;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pim.lucas.domain.enums.TipoCliente;

@Entity
public class Conta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	
	private String numConta;
	private String nomeBanco;
	private String nomeCorrentista;
	
	private String cpfOuCnpj;
	private Integer tipo;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name= "conta_id")
	private Cliente cliente_conta;
	
	private Double saldo;
	
	public Conta() {		
	}
	

	public Conta(Integer id,String numConta, String nomeBanco, String nomeCorrentista, String cpfOuCnpj, TipoCliente tipo,
			 Double saldo,Cliente cliente_conta) {
		super();
		this.id = id;
		this.numConta = numConta;
		this.nomeBanco = nomeBanco;
		this.nomeCorrentista = nomeCorrentista;	
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = (tipo == null) ? null : tipo.getCod();
		this.saldo = saldo;
		this.cliente_conta = cliente_conta;
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


	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}


	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();
	}


	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
		
	public Cliente getCliente_conta() {
		return cliente_conta;
	}


	public void setCliente_conta(Cliente cliente_conta) {
		this.cliente_conta = cliente_conta;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
