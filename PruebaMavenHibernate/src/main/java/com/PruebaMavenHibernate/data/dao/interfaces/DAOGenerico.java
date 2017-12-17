package com.PruebaMavenHibernate.data.dao.interfaces;

public interface DAOGenerico <T> {
	public boolean save(T entidad);
	public boolean delete(T entidad);
}
