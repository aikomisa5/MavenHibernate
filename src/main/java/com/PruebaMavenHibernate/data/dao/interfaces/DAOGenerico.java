package com.PruebaMavenHibernate.data.dao.interfaces;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.PruebaMavenHibernate.interfaces.CRUD;

public interface DAOGenerico <T extends CRUD> {
	public boolean saveOrUpdate(T entidad);
	public boolean delete(T entidad);	
	public boolean create(T entidad) throws Exception;
	public List<T> readAll() throws Exception;
	public List<T> readAllActives();
	public boolean update(T entidad);
	public boolean logicalDelete(T entidad);
	public boolean save(T entidad);
	public T findById(Long id);
	default public List<T> findByCriteria(DetachedCriteria criteria) {
        throw new RuntimeException("Metodo FindByCriteria no Implementado!");
    }
	boolean merge(T entidad); 
}
