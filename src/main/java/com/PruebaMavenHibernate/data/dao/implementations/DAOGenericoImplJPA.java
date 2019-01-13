package com.PruebaMavenHibernate.data.dao.implementations;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.PruebaMavenHibernate.data.conexion.ConexionJPA;
import com.PruebaMavenHibernate.data.dao.interfaces.DAOGenerico;
import com.PruebaMavenHibernate.interfaces.CRUD;
import com.PruebaMavenHibernate.services.PersonaService;


public class DAOGenericoImplJPA <T extends CRUD>  implements DAOGenerico<T>{

	Class <T> clase;
	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DAOGenericoImplJPA.class);
	
	public DAOGenericoImplJPA(Class<T> clase) {
		this.clase=clase;
	}

	private String nombreClase() {
		return clase.getName().substring(clase.getName().lastIndexOf(".")+1);
	}

	public boolean saveOrUpdate(T objeto) {
		boolean ret=false;
		EntityManager em =ConexionJPA.getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(objeto);
			em.getTransaction().commit();
			ret=true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("Cerrado");
			em.close();
		}
		return ret;
	}

	public boolean delete(T objeto) throws IllegalArgumentException {
		if(objeto.getId()==null)
			throw new IllegalArgumentException("El id del objeto es nulo");
		boolean ret=false;
		EntityManager em =ConexionJPA.getEntityManager();
		em.getTransaction().begin();
		em.remove(em.contains(objeto) ? objeto : em.merge(objeto));
		em.getTransaction().commit();
		em.close();
		return ret;
	}


	@SuppressWarnings("unchecked")
	public T findById(Long id) {
		T ret=null;
		EntityManager em =ConexionJPA.getEntityManager();
		em.getTransaction().begin();
		ret=(T) em.createQuery("SELECT o FROM "+clase.getName()+" o WHERE o.id = "+id).getResultList().get(0);
		em.getTransaction().commit();
		em.close();
		return ret;
	}

	public boolean exists (T entidad){
		boolean ret = false;
		T objetoTemp = this.findById(entidad.getId());

		if (objetoTemp != null){
			ret = true;
		}

		return ret;
	}

	public boolean save(T entidad) {
		boolean ret = false;

		if (exists(entidad)){
			ret = update(entidad);
		}
		else{
			ret = create(entidad);
		}

		return ret;
	}

	@Override
	public boolean create(T entidad) {

		boolean ret = false;
		EntityManager em = ConexionJPA.getEntityManager();
		em.getTransaction().begin();

		if (!exists(entidad)){
			em.persist(entidad);
			em.getTransaction().commit();
			ret = true;
			logger.info("Entidad creada con exito..");
		}

		else{
			logger.info("No se puede crear la entidad porque ya existe la entidad");
			ret = false;
		}
		return ret;
	}

	@Override
	public List<T> readAll() throws Exception {
		List <T> lista = null;
		T ret = null;

		EntityManager em = ConexionJPA.getEntityManager();

		try {
			em.getTransaction().begin();
			lista = (List<T>) em.createQuery("SELECT o FROM " + clase.getName() + " o ").getResultList();
			em.getTransaction().commit();
		}
		catch (Exception e){
			logger.info("Ocurrio una excepcion al intentar obtener todas las entidades..");
			throw new RuntimeException("",e);
		}
		finally {
			logger.info("Cerrando el Entity Manager..");
			em.close();
		}

		return lista;
	}

	@Override
	public List<T> readAllActives() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(T entidad) {
		boolean ret=false;
		EntityManager em =ConexionJPA.getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(entidad);
			em.getTransaction().commit();
			ret=true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			logger.info("Cerrando Entity Manager");
			em.close();
		}
		return ret;
	}

	@Override
	public boolean logicalDelete(T entidad) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean merge(T entidad) {
		// TODO Auto-generated method stub
		return false;
	}


}