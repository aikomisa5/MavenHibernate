package com.PruebaMavenHibernate.services;

import com.PruebaMavenHibernate.data.dao.implementations.DAOPersonaImplHibernate;
import org.apache.log4j.Logger;

import com.PruebaMavenHibernate.dto.Persona;
import com.PruebaMavenHibernate.exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class PersonaServiceHibernate {
	
	private DAOPersonaImplHibernate dao;
	private final static Logger logger = Logger.getLogger("logFile");

	public PersonaServiceHibernate() {
		dao = new DAOPersonaImplHibernate();
	}

	//Métodos..

	public boolean create (Persona persona) throws PersonaServiceException {
		boolean ret = false;

		dao = new DAOPersonaImplHibernate();

		ret = dao.create(persona);

		if (ret == false) {
			logger.info("Error al intentar hacer create de la persona");
			throw new PersonaServiceException("No se ha podido hacer create de la persona: " + persona.getNombre()
												+ " " + persona.getApellido() + " " + persona.getDni());
		}
		else {
			logger.info("Persona: " + persona.getNombre() + " " + persona.getApellido() + " guardada con éxito a través de create");
		}
		return ret;
	}

	public boolean save(Persona persona) throws PersonaServiceException  {

		boolean ret = false;
		dao = new DAOPersonaImplHibernate();

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

	public boolean update(Persona persona) throws PersonaServiceException {
		boolean ret = false;
		dao = new DAOPersonaImplHibernate();
		ret = dao.update(persona);

		if (ret == false){
			logger.info("Error al intentar hacer update de la persona");
			throw new PersonaServiceException("No se ha podido hacer update de la persona con éxito");
		}
		else {
			logger.info("Update sobre Persona hecho con éxito");
		}
		return ret;
	}

	public boolean saveOrUpdate(Persona persona) throws PersonaServiceException {
		boolean ret = false;
		dao = new DAOPersonaImplHibernate();
		ret = dao.saveOrUpdate(persona);

		if (ret == false){
			logger.info("Error al intentar hacer saveOrUpdate de la persona");
			throw new PersonaServiceException("No se ha podido hacer saveOrUpdate de la persona con éxito");
		}
		else {
			logger.info("saveOrUpdate sobre Persona hecho con éxito");
		}
		return ret;

	}
	public boolean delete(Persona persona) throws PersonaServiceException {
		boolean ret = false;
		dao = new DAOPersonaImplHibernate();
		ret = dao.delete(persona);

		if (ret == false){
			logger.info("Ha ocurrido un error al intentar hacer un delete de la persona: " + persona.getNombre());
			throw new PersonaServiceException("No se ha podido hacer el delete de la persona: " + persona.getNombre());
		}
		else{
			logger.info("delete sobre la persona: " + persona.getNombre() + " realizado con éxito..");
		}

		return ret;

	}

	public boolean exists (Persona persona) throws PersonaServiceException{

		boolean ret = false;
		dao = new DAOPersonaImplHibernate();

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

	public List<Persona> readAll() throws PersonaServiceException{
		List<Persona> lista = new ArrayList<Persona>();
		dao = new DAOPersonaImplHibernate();
		lista = dao.readAll();

		return lista;
	}
	public List<Persona> readAllActives() throws PersonaServiceException{
		List<Persona> lista = new ArrayList<Persona>();
		dao = new DAOPersonaImplHibernate();
		lista = dao.readAllActives();

		return lista;


	}
	public Persona findById(Long id) throws PersonaServiceException{
		Persona persona;
		dao = new DAOPersonaImplHibernate();
		persona = dao.findById(id);

		return persona;

	}


}
