package edu.epidata.jpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Capitulo {
	
	@Id //Indica que es el identificador de las personas
	private int id;
	
	@Column
	private String titulo;
	
	@Column
	private int paginas;
	
	//Un capítulo tiene un solo revisor
	//Un revisor puede corregir muchos capítulos
	@ManyToOne
	private Persona revisor;
	
	//Un capítulo puede tener muchos autores
	//Un autor puede haber escrito muchos capítulos.
	@ManyToMany
	private List<Persona> autores = new ArrayList<>();
	
	//Un libro tiene muchos capítulos, pero un capitulo está en un solo libro
	@ManyToOne
	private Libro libro;

	public Capitulo() {
	//Requerido
	}
	
	public Capitulo(int id, String titulo, int paginas, Persona revisor, List<Persona> autores, Libro libro) {
		this.id = id;
		this.titulo = titulo;
		this.paginas = paginas;
		this.revisor = revisor;
		this.autores = autores;
		this.libro = libro;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public Persona getRevisor() {
		return revisor;
	}

	public void setRevisor(Persona revisor) {
		this.revisor = revisor;
	}

	public List<Persona> getAutores() {
		return autores;
	}

	public void setAutores(List<Persona> autores) {
		this.autores = autores;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}
	
}