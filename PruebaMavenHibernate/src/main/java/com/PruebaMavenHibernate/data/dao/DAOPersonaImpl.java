package com.PruebaMavenHibernate.data.dao;

import com.PruebaMavenHibernate.data.dao.interfaces.DAOPersona;
import com.PruebaMavenHibernate.dto.Persona;

public class DAOPersonaImpl extends DAOGenericoImpl<Persona> implements DAOPersona {

	/*public DAOPersonaImpl(Class<Persona> clase) {
		super(clase);
		// TODO Auto-generated constructor stub
	}*/

	public DAOPersonaImpl() {
		super(Persona.class);
		// TODO Auto-generated constructor stub
	}

}
