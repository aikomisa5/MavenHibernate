package com.PruebaMavenHibernate.data.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.PruebaMavenHibernate.data.conexion.ConexionJPA;
import com.PruebaMavenHibernate.data.dao.interfaces.DAOGenerico;
import com.PruebaMavenHibernate.dto.CRUD;


public class DAOGenericoImplJPA <T extends CRUD>  implements DAOGenerico<T> {
	Class<T>clase;
	
	public DAOGenericoImplJPA(Class<T> clase) {
		this.clase=clase;
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
	
	private String nombreClase() {
		return clase.getName().substring(clase.getName().lastIndexOf(".")+1);
	}

	public boolean save(T entidad) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(T entidad) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<T> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> readAllActives() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(T entidad) {
		// TODO Auto-generated method stub
		return false;
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