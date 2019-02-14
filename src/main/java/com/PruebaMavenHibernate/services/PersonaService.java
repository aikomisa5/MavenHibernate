package com.PruebaMavenHibernate.services;

import com.PruebaMavenHibernate.data.dao.implementations.DAOPersonaImplHibernate;
import org.apache.log4j.Logger;

import com.PruebaMavenHibernate.dto.Persona;
import com.PruebaMavenHibernate.exceptions.*;

public class PersonaService {
	
	DAOPersonaImplHibernate dao;
	
	private final static Logger logger = Logger.getLogger("logFile");

	public PersonaService() {
		dao = new DAOPersonaImplHibernate();
	}
	
	public boolean save(Persona persona) throws PersonaServiceException  {
		
		dao = new DAOPersonaImplHibernate();

		boolean ret = false;
		
		ret = dao.save(persona);
		
		if (ret == false) {
			logger.info("Error al intentar guardar la persona");
			throw new PersonaServiceException("No se ha podido guardar la persona con éxito");
		}
		
		else {
			logger.info("Persona guardada con éxito");
		}
		
		return ret;
	}

	public boolean exists (Persona persona){

		dao = new DAOPersonaImplHibernate();

		boolean ret = false;

		ret = dao.exists(persona);

		if (ret == false){
			logger.info("La persona: " + persona.getNombre() + " " +  persona.getApellido() + ", " +
						 "con dni: " + persona.getDni() + "no existe en la base");
		}

		else{
			logger.info("La persona: " + persona.getNombre() + " " +  persona.getApellido() + ", " +
					"con dni: " + persona.getDni()  + "si existe en la base");
		}

		return ret;

		}

	}
