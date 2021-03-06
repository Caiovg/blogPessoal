package com.blogpessoal.Turma29.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name  = "tb_postagem")
public class Postagem implements Serializable{

	/*teste*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank /*Não aceita espaço vazios*/
	@Size(min = 5, max = 100)
	private String titulo;
	
	@NotBlank
	@Size(min = 15, max = 400)
	private String txt;
	
	/*foreign key tema*/
	@ManyToOne
	@JoinColumn(name = "tema_id")
	@JsonIgnoreProperties({"postagens"})
	private Tema tema;
	
	/*foreign key usuario*/
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	@JsonIgnoreProperties({"minhasPostagens"})
	private Usuario usuario;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", locale = "pt-BR", timezone = "Brazil/East")
	private Date data = new java.sql.Date(System.currentTimeMillis());

	public Postagem(Long id, String titulo, String txt, Date data) {
		this.id = id;
		this.titulo = titulo;
		this.txt = txt;
		this.data = data;
	}
	
	public Postagem(@NotBlank @Size(min = 5, max = 100) String titulo,
			@NotBlank @Size(min = 15, max = 400) String txt, Tema tema, Usuario usuario) {
		this.titulo = titulo;
		this.txt = txt;
		this.tema = tema;
		this.usuario = usuario;
	}



	public Postagem(){}

	//Metodos Get And Set
	public String getTitulo() {
		return titulo;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getTxt() {
		return txt;
	}


	public void setTxt(String txt) {
		this.txt = txt;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
