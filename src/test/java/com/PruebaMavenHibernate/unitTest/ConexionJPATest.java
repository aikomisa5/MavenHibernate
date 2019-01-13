package com.PruebaMavenHibernate.unitTest;

import org.junit.Test;

import com.PruebaMavenHibernate.data.conexion.ConexionJPA;
import com.PruebaMavenHibernate.data.conexion.TipoConexion;
import com.PruebaMavenHibernate.data.dao.implementations.DAOPersonaImplJPA;

public class ConexionJPATest {

	@Test
	public void test() {
		DAOPersonaImplJPA dao = new DAOPersonaImplJPA();
		dao.setTipoConexion(TipoConexion.H2Test);
	}
	
	@Test
	public void entityManagerFactory()  {
		ConexionJPA.setTipoConexion(TipoConexion.H2Test);
		ConexionJPA.getEntityManagerFactory();
	}
	
	@Test
	public void entityManager()  {
		ConexionJPA.setTipoConexion(TipoConexion.H2Test);
		ConexionJPA.getEntityManager();
	}

}
