package edu.epidata;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Reporte2DTO {
	
	private int personaId;
	private long cantPags;
	
	public Reporte2DTO(int personaId, long cantPags) {
		super();
		this.personaId = personaId;
		this.cantPags = cantPags;
	}
	
	public int getPersonaId() {
		return personaId;
	}

	public void setPersonaId(int personaId) {
		this.personaId = personaId;
	}

	public long getCantPags() {
		return cantPags;
	}

	public void setCantPags(long cantPags) {
		this.cantPags = cantPags;
	}

	@Override
	public String toString() {
		return "Reporte2DTO [personaId=" + personaId +
		 ", cantPags=" + cantPags + "]";
	}
	
	public static void main(String... args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Editorial");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		TypedQuery<Reporte2DTO> q = em.
		createQuery("SELECT new "
			 + "edu.epidata.Reporte2DTO(r.id, count(c.paginas))"
			+ " FROM Capitulo c JOIN c.revisor r"
			+ " WHERE c.libro.anio = :anio"
			+ " GROUP BY r.id ", Reporte2DTO.class);
		q.setParameter("anio", 2017);
		List<Reporte2DTO> res = q.getResultList();
		//Imprime los resultados
		res.forEach(r -> System.out.println(r));
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
}
