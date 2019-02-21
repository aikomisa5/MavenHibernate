package com.PruebaMavenHibernate.data.dao.implementations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.PruebaMavenHibernate.data.conexion.ConexionHibernate;
import com.PruebaMavenHibernate.data.dao.interfaces.DAOGenerico;
import com.PruebaMavenHibernate.interfaces.CRUD;

public class DAOGenericoImplHibernate<T extends CRUD> implements DAOGenerico<T>{
	
	private Class<T> clase;
    private static final String activo = "activo";

    private DAOGenericoImplHibernate() {
    }

    public DAOGenericoImplHibernate(Class<T> clase) {
		this.clase = clase;
    }

	private String nombreClase() {
		return clase.getName().substring(clase.getName().lastIndexOf(".")+1);
	}

	private Class<T> getClaseEntidad() {
		return clase;
	}

	public boolean create(T entidad) {
		boolean resultado = false;

		Session session = ConexionHibernate.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.persist(entidad);
			tx.commit();
			resultado = true;
		}
		catch (HibernateException e) {
			System.err.println("---------------------------------------------");
			System.err.println("---------------------------------------------");
			System.err.println("Error al intentar hacer create de la entidad: " + entidad);
			System.err.println("---------------------------------------------");
			System.err.println("---------------------------------------------");

			e.printStackTrace();
		}
		finally {
			session.close();
		}

		return resultado;
	}

	public boolean save(T entidad) {
		if (exists(entidad)) {
			return update(entidad);
		}
		else {
			return create(entidad);
		}
	}

	public boolean update(T entidad) {
		boolean resultado = false;

		Session session = ConexionHibernate.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.merge(entidad);
			tx.commit();
			resultado = true;
		}
		catch (HibernateException e) {
			System.err.println("Error al realizar update: " + entidad);
			e.printStackTrace();
			if(tx!=null) tx.rollback();
		}
		finally {
			session.close();
		}

		return resultado;
	}

	public boolean saveOrUpdate(T entidad) {
		boolean resultado = false;

		Session session = ConexionHibernate.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(entidad);
			tx.commit();
			resultado = true;
		}
		catch (HibernateException e) {
			System.err.println("Error al intentar hacer saveOrUpdate de la entidad: " + entidad);
			e.printStackTrace();
			if(tx!=null) tx.rollback();
		}
		finally {
			session.close();
		}

		return resultado;
	}

	public boolean delete(T entidad) {
		boolean resultado = false;

		Session session = ConexionHibernate.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			@SuppressWarnings("unchecked")
			T toDelete = (T) session.get(entidad.getClass(), entidad.getId());
			session.delete(toDelete);
			tx.commit();
			resultado = true;
		}
		catch (HibernateException e) {
			System.err.println("Error al intentar hacer delete de la entidad: " + entidad);
			e.printStackTrace();
			if(tx!=null) tx.rollback();
		}
		finally {
			session.close();
		}

		return resultado;
	}
	
	public boolean exists(T entidad) {
		if (entidad.getId() == null) {
		    return false;
		}

		T resultado = findById(entidad.getId());

		if (resultado != null) {
		    return true;
		}
		else {
			return false;
		}
	}

	public List<T> readAll() {
		List<T> entidades = new ArrayList<T>();

		Session session = ConexionHibernate.openSession();
		Transaction tx = null;

		try {
		    tx = session.beginTransaction();
		    entidades = session.createQuery("from " + getClaseEntidad().getSimpleName()).list();
		    tx.commit();
		}
		catch (HibernateException e) {
		    System.err.println("Error al hacer un readAll para leer: " + getClaseEntidad().getSimpleName());
		     e.printStackTrace();
		     if(tx!=null) tx.rollback();
		}
		finally {
		    session.close();
		}

		return entidades;
	}

	public List<T> readAllActives() {
		List<T> lista = new ArrayList<T>();

		try{
			DetachedCriteria criteria = DetachedCriteria.forClass(getClaseEntidad())
				.add(Restrictions.eq(activo, true)).setResultTransformer(
						Criteria.DISTINCT_ROOT_ENTITY);

			lista = borrarRepetidos(findByCriteria(criteria));

		}
		catch(HibernateException e){
			System.err.println("Error al hacer un readAll para leer: " + getClaseEntidad().getSimpleName());
			e.printStackTrace();
		}

		return lista;
	}

	public T findById(Long id) {
		T entidad = null;
		Session session = ConexionHibernate.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			entidad = session.get(clase, id);
			tx.commit();
		}
		catch (HibernateException e) {
			System.err.println("Error al buscar por entidad por id con findById: " + id);
			e.printStackTrace();
			if(tx!=null) tx.rollback();
		}
		finally {
			session.close();
		}

		return entidad;
	}
	
	protected List<T> borrarRepetidos(List<T> lista) {
		List<T> ret =  new ArrayList<>();;
		try {
			Set<T> conjunto = new HashSet<>();
			for (T elem : lista) {
				conjunto.add(elem);
			}
			for (T elem : conjunto) {
				ret.add(elem);
			}
			//lista.forEach(elemento -> conjunto.add(elemento));
			//conjunto.forEach(elemento -> ret.add(elemento));
		}
		catch(Exception e){
			System.err.println("Error al intentar borrrar valores repetidos..");
			e.printStackTrace();
		}

		return ret;
	    }



}
