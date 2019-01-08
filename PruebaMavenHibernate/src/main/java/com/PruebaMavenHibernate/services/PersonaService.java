package main.java.com.PruebaMavenHibernate.services;

import org.apache.log4j.Logger;

import com.PruebaMavenHibernate.data.dao.DAOPersonaImpl;
import com.PruebaMavenHibernate.dto.Persona;
import com.PruebaMavenHibernate.exceptions.services.PersonaServiceException;

public class PersonaService {
	
	DAOPersonaImpl dao;
	
	private final static Logger logger = Logger.getLogger(PersonaService.class);

	public PersonaService() {
		dao = new DAOPersonaImpl();
	}
	
	public boolean save(Persona persona) throws PersonaServiceException {
		
		dao = new DAOPersonaImpl();

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
	}
