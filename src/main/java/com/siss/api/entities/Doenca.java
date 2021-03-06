package com.siss.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "doenca")
public class Doenca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	private CondicaoClinica condicaoClinica;

	@Column(name = "tipo", nullable = true, length = 100)
	private String tipo;

	@Column(name = "data_Cadastro", nullable = false)
	private Date dataCadastro;

	@Column(name = "data_Alteracao", nullable = false)
	private Date dataAlteracao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id= id;
	}

	public CondicaoClinica getCondicaoClinica() {
		return condicaoClinica;
	}

	public void setCondicaoClinica(CondicaoClinica condicaoClinica) {
		this.condicaoClinica = condicaoClinica;
	}

	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	@PreUpdate
	public void preUpdate() {
		this.setDataAlteracao(new Date());
	}

	@PrePersist
	public void prePersist() {
		this.setDataCadastro(new Date());
		this.setDataAlteracao(new Date());
	}

	@Override
	public String toString() {
		return "Doenca[id=" + id+ "idCondicaoClinica=" + condicaoClinica.getId() + ","
				+ "tipo=" + tipo + "]";
	}
}
