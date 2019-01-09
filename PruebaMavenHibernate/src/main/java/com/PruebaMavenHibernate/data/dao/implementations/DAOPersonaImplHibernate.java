package com.PruebaMavenHibernate.data.dao.implementations;

import com.PruebaMavenHibernate.data.dao.interfaces.DAOPersona;
import com.PruebaMavenHibernate.dto.Persona;

public class DAOPersonaImplHibernate extends DAOGenericoImplHibernate<Persona> implements DAOPersona {

	/*public DAOPersonaImplHibernate(Class<Persona> clase) {
		super(clase);
		// TODO Auto-generated constructor stub
	}*/

	public DAOPersonaImplHibernate() {
		super(Persona.class);
		// TODO Auto-generated constructor stub
	}

}
