package edu.epidata.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Main {

	
	public static void main(String[] args) {
		//Crea el Entity manager factory con la configuración
		//llamada editorial
		EntityManagerFactory emf = Persistence.
		createEntityManagerFactory("Editorial");
		EntityManager em = emf.createEntityManager();
		//Inicia la transacción con la DBs
		//Persiste una persona
		//Hace el commit
		em.getTransaction().begin();
		em.persist(new Persona(1, "Juan", "Perez", "juan@perez.com"));
		em.getTransaction().commit();
		em.close();
		//Inicio otra session
		em = emf.createEntityManager();
		em.getTransaction().begin();
		//Pregunto por todas las Personas. Persona en este caso
		//es la clase Persona, ya que la query es sobre JPQL
		TypedQuery<Persona> qp = em.
		createQuery("SELECT p FROM Persona p",
		Persona.class);
		for (Persona p: qp.getResultList()) {
		System.out.println(p);
		}
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}
