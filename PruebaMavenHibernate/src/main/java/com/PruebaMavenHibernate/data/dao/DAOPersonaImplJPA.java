package com.PruebaMavenHibernate.data.dao;

import com.PruebaMavenHibernate.data.conexion.ConexionJPA;
import com.PruebaMavenHibernate.data.conexion.TipoConexion;
import com.PruebaMavenHibernate.data.dao.interfaces.DAOPersona;
import com.PruebaMavenHibernate.dto.Persona;

public class DAOPersonaImplJPA extends DAOGenericoImplJPA<Persona> implements DAOPersona{
	
	public DAOPersonaImplJPA() {
		super(Persona.class);
	}

	public void setTipoConexion(TipoConexion tipo) {
		ConexionJPA.setTipoConexion(tipo);
	}
}
