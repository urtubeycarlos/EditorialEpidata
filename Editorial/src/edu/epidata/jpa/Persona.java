package edu.epidata.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity //Indica que se persiste en la BD
public class Persona {
	
	@Id //Indica que es el identificador de las personas
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
	
	public Persona(int id, String nombre, String apellido, String mail){
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Persona: id = " + this.id + ", ");
		sb.append(this.nombre + " " +this.apellido + ", ");
		sb.append(this.mail);
		return sb.toString();
	}
	
}