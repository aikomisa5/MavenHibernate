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
    private static final String estadoRegistro = "estadoRegistro";

    private DAOGenericoImplHibernate() {

    };

    public DAOGenericoImplHibernate(Class<T> clase) {

		this.clase = clase;
    }
	
	public boolean save(T entidad) {
		if (exists(entidad)) {
		    return update(entidad);
		}
		return create(entidad);
	}
	
	public boolean exists(T entidad) {
		if (entidad.getId() == null) {
		    return false;
		}
		T ret = findById(entidad.getId());
		if (ret != null) {
		    return true;
		}
		return false;
	    }

	public boolean delete(T entidad) {
		boolean ret = false;
		Session session = ConexionHibernate.openSession();
		Transaction tx = null;
		try {
		    tx = session.beginTransaction();
		    @SuppressWarnings("unchecked")
		    T toDelete = (T) session.get(entidad.getClass(), entidad.getId());
		    session.delete(toDelete);
		    tx.commit();
		    ret = true;
		} catch (HibernateException e) {
		    System.err.println("Error al delete: " + entidad);
		    e.printStackTrace();
		   if(tx!=null) tx.rollback();
		} finally {
		    session.close();
		}
		return ret;
	}

	public boolean saveOrUpdate(T entidad) {
		boolean ret = false;
		Session session = ConexionHibernate.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(entidad);
			tx.commit();
			ret = true;
		} catch (HibernateException e) {
			System.err.println("Error al guardar: " + entidad);
			e.printStackTrace();
			if(tx!=null) tx.rollback();
		} finally {
			session.close();
		}
		return ret;
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
		} catch (HibernateException e) {
		    System.err.println("---------------------------------------------");
		    System.err.println("---------------------------------------------");
		    System.err.println("Error al intentar crear: ");
		    System.err.println(entidad);
		    System.err.println("---------------------------------------------");
		    System.err.println("---------------------------------------------");

		    e.printStackTrace();
		} finally {
		    session.close();
		}
		return resultado;
	}

	public List<T> readAll() {
		List<T> entidades = new ArrayList<T>();
		Session session = ConexionHibernate.openSession();
		Transaction tx = null;
		try {
		    tx = session.beginTransaction();
		    entidades = session.createQuery("from " + getClaseEntidad().getSimpleName()).list();
		    tx.commit();
		} catch (HibernateException e) {
		    System.err.println("Error al leer " + getClaseEntidad().getSimpleName());
		     e.printStackTrace();
		     if(tx!=null) tx.rollback();
		} finally {
		    session.close();
		}
		return entidades;
	}
	
	public Class<T> getClaseEntidad() {
		return clase;
	    }


	public List<T> readAllActives() {
		DetachedCriteria criteria = DetachedCriteria.forClass(getClaseEntidad())
				.add(Restrictions.eq(estadoRegistro, true)).setResultTransformer(
					Criteria.DISTINCT_ROOT_ENTITY);
			return borrarRepetidos(findByCriteria(criteria));
	}
	
	protected List<T> borrarRepetidos(List<T> lista) {
		Set<T> conjunto = new HashSet<>();
		List<T> ret = new ArrayList<>();
		for (T elem : lista){
			conjunto.add(elem);
		}
		for (T elem: conjunto){
			ret.add(elem);
		}
		//lista.forEach(elemento -> conjunto.add(elemento));
		//conjunto.forEach(elemento -> ret.add(elemento));
		return ret;
	    }


	public boolean update(T entidad) {
		boolean ret = false;
		Session session = ConexionHibernate.openSession();
		Transaction tx = null;
		try {
		    tx = session.beginTransaction();
		    session.merge(entidad);
		    tx.commit();
		    ret = true;
		} catch (HibernateException e) {
		    System.err.println("Error al realizar update: " + entidad);
		    e.printStackTrace();
		   if(tx!=null) tx.rollback();
		} finally {
		    session.close();
		}
		return ret;
	}

	public boolean logicalDelete(T entidad) {
		boolean ret = false;
		Session session = ConexionHibernate.openSession();
		Transaction tx = null;
		try {
		    tx = session.beginTransaction();
		    entidad.setBorrado(true);
		    session.update(entidad);
		    tx.commit();
		    ret = true;
		} catch (HibernateException e) {
		    System.err.println("Error al ejecutar LogicalDelte: " + entidad);
		    e.printStackTrace();
		   if(tx!=null) tx.rollback();
		} finally {
		    session.close();
		}
		return ret;
	}


	public T findById(Long id) {
		T entidad = null;
		Session session = ConexionHibernate.openSession();
		Transaction tx = null;
		try {
		    tx = session.beginTransaction();
		    entidad = session.get(clase, id);
		    tx.commit();
		} catch (HibernateException e) {
		    System.err.println("Error al buscar por Id: " + id);
		    e.printStackTrace();
		   if(tx!=null) tx.rollback();
		} finally {
		    session.close();
		}
		return entidad;
	}

	public boolean merge(T entidad) {
		// TODO Auto-generated method stub
		return false;
	}

}
