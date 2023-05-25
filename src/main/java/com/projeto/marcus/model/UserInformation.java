package com.projeto.marcus.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name = "UserInformation")
@Table(name = "user_information")
public class UserInformation implements Serializable {
	
	private static final long serialVersionUID = -2856924708271801222L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", nullable = false)
	private String nome;

	@Column(name = "cpf", nullable = false)
	private String cpf;

	private String telefone;

	private String dataDeNascimento;

	private String cep;

	private Long numeroEndereco;

	private String logradouro;

	private String complemento;

	private String bairro;

	private String localidade;

	private String uf;

}
