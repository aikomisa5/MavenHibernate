package com.PruebaMavenHibernate.data.dao.implementations;

import java.util.List;

import javax.persistence.EntityManager;

import com.PruebaMavenHibernate.data.conexion.ConexionJPA;
import com.PruebaMavenHibernate.data.dao.interfaces.DAOGenerico;
import com.PruebaMavenHibernate.interfaces.CRUD;


public class DAOGenericoImplJPA <T extends CRUD>  implements DAOGenerico<T>{

	Class <T> clase;

	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("logFile");
	
	public DAOGenericoImplJPA(Class<T> clase) {
		this.clase=clase;
	}

	private String nombreClase() {
		return clase.getName().substring(clase.getName().lastIndexOf(".")+1);
	}

	private Class<T> getClaseEntidad() {
		return clase;
	}

	public boolean create(T entidad) {

		boolean resultado = false;
		EntityManager em = ConexionJPA.getEntityManager();

		try {
			em.getTransaction().begin();

			if (!exists(entidad)) {
				em.persist(entidad);
				em.getTransaction().commit();
				resultado = true;
				logger.info("Entidad creada con éxito..");
			} else {
				logger.info("No se puede crear la entidad porque ya existe..");
				resultado = false;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			em.close();
			logger.info("Cerrando el entity manager");
		}

		return resultado;
	}

	public boolean save(T entidad) {
		boolean resultado = false;

		try {
			if (exists(entidad)) {
				resultado = update(entidad);
			} else {
				resultado = create(entidad);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}

		return resultado;
	}

	public boolean update(T entidad) {
		boolean resultado = false;

		EntityManager em = ConexionJPA.getEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(entidad);
			em.getTransaction().commit();
			resultado=true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			logger.info("Cerrando el Entity Manager..");
			em.close();
		}

		return resultado;
	}

	public boolean saveOrUpdate(T objeto) {
		boolean resultado = false;

		EntityManager em =ConexionJPA.getEntityManager();

		try {
			if (exists(objeto) == false){
				create(objeto);
				resultado = true;
			}
			else {
				em.getTransaction().begin();
				em.merge(objeto);
				em.getTransaction().commit();
				resultado = true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
			logger.info("Cerrando EntityManager en saveOrUpdate..");
		}
		return resultado;
	}

	public boolean delete(T objeto) throws IllegalArgumentException {
		if(objeto.getId()==null) {
			throw new IllegalArgumentException("El id del objeto es nulo");
		}

		boolean resultado=false;
		EntityManager em =ConexionJPA.getEntityManager();

		try {
			em.getTransaction().begin();
			em.remove(em.contains(objeto) ? objeto : em.merge(objeto));
			em.getTransaction().commit();
			em.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			em.close();
			logger.info("Cerrando entity manager..");
		}
		return resultado;
	}

	public boolean exists (T entidad){
		boolean resultado = false;

		try {
			T objetoTemp = this.findById(entidad.getId());

			if (objetoTemp != null) {
				resultado = true;
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}

		return resultado;
	}

	@SuppressWarnings("unchecked")
	public T findById(Long id) {
		T resultado=null;
		EntityManager em =ConexionJPA.getEntityManager();

		try {
			em.getTransaction().begin();
			resultado = (T) em.createQuery("SELECT o FROM " + clase.getName() + " o WHERE o.id = " + id).getResultList().get(0);
			em.getTransaction().commit();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			em.close();
			logger.info("Cerrando el Entity Manager..");
		}

		return resultado;
	}

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
			em.close();
			logger.info("Cerrando el Entity Manager..");
		}

		return lista;
	}

	public List<T> readAllActives() {
		List <T> lista = null;
		T ret = null;

		EntityManager em = ConexionJPA.getEntityManager();

		try {
			em.getTransaction().begin();

			lista = (List<T>) em.createQuery("SELECT o FROM " + getClaseEntidad().getName() + " o WHERE o.activo = 'true'").getResultList();
			em.getTransaction().commit();
		}
		catch (Exception e){
			logger.info("Ocurrio una excepcion al intentar obtener todas las entidades activas..");
			throw new RuntimeException("",e);
		}
		finally {
			em.close();
			logger.info("Cerrando el Entity Manager..");
		}

		return lista;
	}

}