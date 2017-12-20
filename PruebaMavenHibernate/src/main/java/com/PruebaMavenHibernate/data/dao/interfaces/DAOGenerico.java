package com.PruebaMavenHibernate.data.dao.interfaces;

import com.PruebaMavenHibernate.dto.CRUD;

public interface DAOGenerico <T extends CRUD> {
	public boolean save(T entidad);
	public boolean delete(T entidad);
}
