package br.ufrn.tads.model;

import java.util.Date;
import java.util.Objects;

public class Consulta {

	private Long id;
	private Long fkPaciente;
	private Long fkMedico;
	private Date data = new Date();
	private String queixa;
	private String descricao;
	private String relatosClinicos;

	public Consulta() {
		
	}
	

	public Consulta(Long fkPaciente, Long fkMedico, Date data, String queixa, String descricao, String relatosClinicos) {
		this.fkPaciente = fkPaciente;
		this.fkMedico = fkMedico;
		this.data = data;
		this.queixa = queixa;
		this.descricao = descricao;
		this.relatosClinicos = relatosClinicos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFkPaciente() {
		return fkPaciente;
	}

	public void setFkPaciente(Long fkPaciente) {
		this.fkPaciente = fkPaciente;
	}

	public Long getFkMedico() {
		return fkMedico;
	}

	public void setFkMedico(Long fkMedico) {
		this.fkMedico = fkMedico;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getQueixa() {
		return queixa;
	}

	public void setQueixa(String queixa) {
		this.queixa = queixa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getRelatosClinicos() {
		return relatosClinicos;
	}

	public void setRelatosClinicos(String relatosClinicos) {
		this.relatosClinicos = relatosClinicos;
	}
	
	public void exibirDados() {
		
		System.out.println("ConsultaId: " + id);
		System.out.println("PacienteId: " + fkPaciente);
		System.out.println("MedicoId: " + fkMedico);
		System.out.println("Data: " + data);
		System.out.println("Queixa: " + queixa);
		System.out.println("Descricao: " + descricao);
		System.out.println("Relatos Clinicos Analisados: " + relatosClinicos);
		
	}


	@Override
	public int hashCode() {
		return Objects.hash(data, descricao, fkMedico, fkPaciente, id, queixa, relatosClinicos);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consulta other = (Consulta) obj;
		return Objects.equals(data, other.data) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(fkMedico, other.fkMedico) && Objects.equals(fkPaciente, other.fkPaciente)
				&& Objects.equals(id, other.id) && Objects.equals(queixa, other.queixa)
				&& Objects.equals(relatosClinicos, other.relatosClinicos);
	}
	
	

}
