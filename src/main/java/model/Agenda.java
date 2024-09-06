package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//Entidade agenda
@Entity
public class Agenda {
	// Atributos
	@Id
	@GeneratedValue
	private long id;
	@Column(nullable = false, length = 50)
	private String titulo;
	@Column(nullable = false)
	private String conteudo;

	// Construtor padrão
	public Agenda() {}

	//Construtor completo
	public Agenda(long id, String titulo, String conteudo) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.conteudo = conteudo;
	}

	//Getters e Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	//ToString
	@Override
	public String toString() {
		return "Agenda [id=" + id + ", titulo=" + titulo + ", conteudo=" + conteudo + "]";
	}
}
