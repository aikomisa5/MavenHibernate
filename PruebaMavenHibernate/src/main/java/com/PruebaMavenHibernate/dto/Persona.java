package com.PruebaMavenHibernate.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "personas")
public class Persona implements CRUD{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idPersona")
	private Long id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "apellido")
	private String apellido;
	@Column(name = "edad")
	private int edad;
	@Column(name = "mail")
	private String mail;
	@Column(name = "dni")
	private String dni;
	@Column(name = "estadoRegistro")
	private boolean estaBorrado;
	
	public Persona() {
		this.estaBorrado = false;		
	}
	
	public Persona(String nombre, String apellido, int edad, String mail, String dni) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.mail = mail;
		this.dni = dni;	
		this.estaBorrado = false;	
	}
	
	protected Persona(Builder b) {
		this.nombre = b.nombre;
		this.apellido = b.apellido;
		this.dni = b.dni;
		this.mail = b.mail;
		this.edad = b.edad;
		this.estaBorrado = false;
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
	
	public void setBorrado(Boolean estaBorrado) {
		this.estaBorrado = estaBorrado;
		
	}
	
	public Boolean getBorrado() {
		return this.estaBorrado;
	}
	
	public static class Builder {
		protected Long id;
		protected String nombre;
		protected String apellido;
		protected int edad;
		protected String mail;
		protected String dni;
		
		public Builder setId (Long dato) {
			this.id = dato;
			return this;
		}
		
		public Builder setNombre(String dato) {
			this.nombre = dato;
			return this;
		}
		
		public Builder setApellido(String dato) {
			this.apellido = dato;
			return this;
		}
		
		public Builder setEdad(int dato) {
			this.edad = dato;
			return this;
		}
		
		public Builder setMail(String dato) {
			this.mail = dato;
			return this;
		}
		
		public Builder setDNI(String dato) {
			this.dni = dato;
			return this;
		}
		
		public Persona build() {
			return new Persona(this);
		}
	}

	
	
}
