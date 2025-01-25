package br.ufrn.tads.model;

import java.util.Objects;

public class Medico {

	private Long id;
	private String crm;
	private String nome;
	private String especialidade;
	private String telefone;
	private String email;

	public Medico(String nome, String crm, String especialidade, String telefone, String email) {
		this.nome = nome;
		this.crm = crm;
		this.especialidade = especialidade;
		this.telefone = telefone;
		this.email = email;
	}

	public Medico() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
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
	
	public void exibirDados() {
		
		System.out.println("MedicoId: " + id);
		System.out.println("Medico: " + nome);
		System.out.println("CRM: " + crm);
		System.out.println("Especialidade: " + especialidade);
		System.out.println("Telefone: " + telefone);
		System.out.println("E-mail: " + email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(crm, email, especialidade, id, nome, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medico other = (Medico) obj;
		return Objects.equals(crm, other.crm) && Objects.equals(email, other.email)
				&& Objects.equals(especialidade, other.especialidade) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(telefone, other.telefone);
	}
	
	
	
}