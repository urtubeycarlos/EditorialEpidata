package edu.epidata.jpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Libro {
	
	@Id //Indica que es el identificador de las personas
	private int id;
	
	@Column
	private String nombre;
	
	@Column
	private int anio;
	
	@ManyToMany
	private List<Persona> editores = new ArrayList<>();
	//Un libro tiene muchos capítulos, pero un capitulo está en un solo libro
	
	//El mapeo es realizado por Capitulo.
	@OneToMany(mappedBy = "libro")
	private List<Capitulo> capitulos = new ArrayList<>();
	
	public Libro() {
	//Requerido
	}
	
	public Libro(int id, String nombre, int anio, List<Persona> editores) {
		this.id = id;
		this.nombre = nombre;
		this.anio = anio;
		this.editores = editores;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public List<Persona> getEditores() {
		return editores;
	}

	public void setEditores(List<Persona> editores) {
		this.editores = editores;
	}

	public List<Capitulo> getCapitulos() {
		return capitulos;
	}

	public void setCapitulos(List<Capitulo> capitulos) {
		this.capitulos = capitulos;
	}
	
}
