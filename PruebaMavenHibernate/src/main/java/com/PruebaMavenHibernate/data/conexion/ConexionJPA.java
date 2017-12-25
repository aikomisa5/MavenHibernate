package com.PruebaMavenHibernate.data.conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexionJPA {

	private static EntityManagerFactory emf=null;
	private static EntityManager em=null;
	private static TipoConexion tipoConexion;
	

	public static void setTipoConexion(TipoConexion tc) {
		tipoConexion=tc;
	}
	
	public static EntityManagerFactory getEntityManagerFactory() {
		try {
			emf = Persistence.createEntityManagerFactory("test", tipoConexion.properties());
			System.out.println("[INFO] Entity manager factory creado correctamente");
		} catch (Exception e) {
			System.out.println("[ERROR] Error al conectar");
			e.printStackTrace();
		}
		return emf;
	}
	
	public static EntityManager getEntityManager() {
		try {
			if(emf==null)
				emf=getEntityManagerFactory();
			em = emf.createEntityManager();
			System.out.println("[INFO] Entity manager creado correctamente");
		} catch (Exception e) {
			System.out.println("[ERROR] Error al conectar");
			e.printStackTrace();
		}
		return em;
	}
	
	
}