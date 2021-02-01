package edu.epidata.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //Indica que se persiste en la BD
public class Persona {
	
	@Id //Indica que es el identificador de las personas
	@GeneratedValue(strategy = GenerationType.AUTO) //Y que se crea automáticamente
	private int id;
	
	@Column //Indica que es una columna en la BDs
	private String nombre;
	
	@Column
	private String apellido;
	
	@Column(nullable=false) //Indica que no puede ser null
	private String mail;
	
	public Persona() {
	//El constructor por defecto es obligatorio.
	}
}