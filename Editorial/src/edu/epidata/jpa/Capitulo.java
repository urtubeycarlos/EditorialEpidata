package edu.epidata.jpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Capitulo {
	
	@Id //Indica que es el identificador de las personas
	@GeneratedValue(strategy = GenerationType.AUTO) //Y que se crea autom�ticamente
	private int id;
	
	@Column
	private String titulo;
	
	@Column
	private int paginas;
	
	//Un cap�tulo tiene un solo revisor
	//Un revisor puede corregir muchos cap�tulos
	@ManyToOne
	private Persona revisor;
	
	//Un cap�tulo puede tener muchos autores
	//Un autor puede haber escrito muchos cap�tulos.
	@ManyToMany
	private List<Persona> autores = new ArrayList<>();
	
	//Un libro tiene muchos cap�tulos, pero un capitulo est� en un solo libro
	@ManyToOne
	private Libro libro;

	public Capitulo() {
	//Requerido
	}
}