package edu.epidata;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Reporte1DTO {

	private int personaId;
	private long cant;
	
	public Reporte1DTO(int personaId, long cant) {
		super();
		this.personaId = personaId;
		this.cant = cant;
	}
	
	public int getPersonaId() {
		return personaId;
	}

	public void setPersonaId(int personaId) {
		this.personaId = personaId;
	}

	public long getCant() {
		return cant;
	}

	public void setCant(long cant) {
		this.cant = cant;
	}
	
	@Override
	public String toString() {
		return "Reporte1DTO [personaId=" + personaId +
		 ", cant=" + cant + "]";
	}
	
	public static void main(String... args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Editorial");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		TypedQuery<Reporte1DTO> q = em.
		createQuery("SELECT new "
			 + "edu.epidata.Reporte1DTO(p.id, count(*))"
			+ " FROM Libro l JOIN l.editores p"
			+ " WHERE l.anio = :anio"
			+ " GROUP BY p.id ", Reporte1DTO.class);
		q.setParameter("anio", 2017);
		List<Reporte1DTO> res = q.getResultList();
		//Imprime los resultados
		res.forEach(r -> System.out.println(r));
		em.getTransaction().commit();
		
		em.close();
		emf.close();

	}
}
