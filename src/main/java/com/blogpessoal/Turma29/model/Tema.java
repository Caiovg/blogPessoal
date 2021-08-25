package com.blogpessoal.Turma29.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name  = "tema")
public class Tema implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTema;
	@NotBlank
	private String tema;
	
	public Tema() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Tema(Integer idTema, String tema) {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getIdTema() {
		return idTema;
	}
	public void setIdTema(Integer idTema) {
		this.idTema = idTema;
	}
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	
	
}
