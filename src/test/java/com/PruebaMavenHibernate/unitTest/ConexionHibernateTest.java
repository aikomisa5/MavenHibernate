package com.PruebaMavenHibernate.unitTest;

import static org.junit.Assert.assertTrue;

import com.PruebaMavenHibernate.data.dao.implementations.DAOPersonaImplHibernate;
import com.PruebaMavenHibernate.dto.Persona;
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
		ConexionHibernate.setTipoConexion(TipoConexion.MySQLServer);
		ConexionHibernate.openSession();
		assertTrue(true);
	}

	@Test
	public void pruebaCreate(){

		DAOPersonaImplHibernate dao = new DAOPersonaImplHibernate();
		Persona persona = new Persona();
		persona.setNombre("Ricardo");
		persona.setApellido("Ruben");
		boolean resultado = false;
		resultado = dao.create(persona);

		System.out.print("Resultado create: " + resultado);

	}

}
