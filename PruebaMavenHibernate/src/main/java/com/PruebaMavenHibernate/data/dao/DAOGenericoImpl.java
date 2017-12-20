package com.PruebaMavenHibernate.data.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.PruebaMavenHibernate.data.conexion.ConexionHibernate;
import com.PruebaMavenHibernate.data.dao.interfaces.DAOGenerico;
import com.PruebaMavenHibernate.dto.CRUD;

public class DAOGenericoImpl<T extends CRUD> implements DAOGenerico<T>{
	
	private Class<T> clase;
    private static final String estadoRegistro = "estadoRegistro";

    private DAOGenericoImpl() {

    };

    public DAOGenericoImpl(Class<T> clase) {
	this.clase = clase;
    }
	
	@Override
	public boolean save(T entidad) {
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

	@Override
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

}
