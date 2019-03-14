package com.PruebaMavenHibernate.dto;

import com.PruebaMavenHibernate.interfaces.CRUD;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "personas")
public class Persona implements CRUD {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idPersona", nullable = false, unique = true, length = 11)
	private Long id;
	@Column(name = "nombre", length = 30, nullable = true)
	private String nombre;
	@Column(name = "apellido", length = 30, nullable = true)
	private String apellido;
	@Column(name = "edad", length = 3, nullable = true)
	private int edad;
	@Column(name = "mail", nullable = true)
	private String mail;
	@Column(name = "dni", nullable = true)
	private String dni;
	@Column(name = "activo")
	private boolean activo;
	
	public Persona() {
		this.activo = true;
	}
	
	public Persona(String nombre, String apellido, int edad, String mail, String dni) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.mail = mail;
		this.dni = dni;	
		this.activo = true;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	public boolean getActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
}
