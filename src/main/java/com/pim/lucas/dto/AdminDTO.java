package com.pim.lucas.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.pim.lucas.services.validation.AdminInsert;


@AdminInsert
public class AdminDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 8, max = 10, message = "Preenchimento no foramato dd/MM/yyyy")
	private String dataNascimento;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String cpfOuCnpj;
	
	private Integer tipo;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 6, max = 120, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String senha;
	
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	
	
	public AdminDTO() {		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
		
	@NotNull(message = "Preenchimento obrigatório")
	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	} 

	
	

}
