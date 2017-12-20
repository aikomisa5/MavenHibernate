package com.PruebaMavenHibernate.services;

import com.PruebaMavenHibernate.data.dao.DAOPersonaImpl;
import com.PruebaMavenHibernate.dto.Persona;

public class PersonaService {
	
	DAOPersonaImpl dao;

	public PersonaService() {
		dao = new DAOPersonaImpl();

	}
	
	public boolean save(Persona persona) {
		
		dao = new DAOPersonaImpl();		
		return dao.save(persona);
	}
	

}
