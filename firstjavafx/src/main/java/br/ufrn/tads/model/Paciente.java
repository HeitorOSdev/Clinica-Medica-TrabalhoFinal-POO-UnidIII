package br.ufrn.tads.model;

import java.util.Date;
import java.util.Objects;

public class Paciente {

	private Long id;
	private String nome;
	private String cpf;
	private Date dataNascimento = new Date();
	private String genero;
	private String telefone;
	private String email;
	
	public Paciente () {
		
	}

	public Paciente(String cpf, String nome, Date dataNascimento, String telefone, String email, String genero) {
		this.cpf = cpf;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.email = email;
		this.genero = genero;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public void exibirDados() {
		
		System.out.println("PacienteId: " + id);
		System.out.println("Paciente: " + nome);
		System.out.println("CPF: " + cpf);
		System.out.println("Data de nascimento: " + dataNascimento);
		System.out.println("Telefone: " + telefone);
		System.out.println("E-mail: " + email);
		System.out.println("Genero: " + genero);
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, dataNascimento, email, genero, id, nome, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(dataNascimento, other.dataNascimento)
				&& Objects.equals(email, other.email) && Objects.equals(genero, other.genero)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(telefone, other.telefone);
	}
}