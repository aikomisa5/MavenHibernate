package com.PruebaMavenHibernate.unitTest;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import com.PruebaMavenHibernate.data.conexion.ConexionHibernate;
import com.PruebaMavenHibernate.data.conexion.TipoConexion;

public class ConexionHibernateTest {

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void pruebaConexion() {
		ConexionHibernate.setTipoConexion(TipoConexion.H2Server);
		ConexionHibernate.openSession();
		assertTrue(true);
	}

}
