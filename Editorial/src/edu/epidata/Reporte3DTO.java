package edu.epidata;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Reporte3DTO {
	
	private int personaId;
	private long cantCaps;
	
	public Reporte3DTO() {
		
	}
	
	public Reporte3DTO(int personaId, long cantCaps) {
		super();
		this.personaId = personaId;
		this.cantCaps = cantCaps;
	}
	
	@Override
	public String toString() {
		return "Reporte2DTO [personaId=" + personaId +
		 ", cantCaps=" + cantCaps + "]";
	}
	
	public static void main(String... args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Editorial");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		TypedQuery<Reporte3DTO> q = em.
		createQuery("SELECT new "
				 + "edu.epidata.Reporte3DTO(a.id, count(l))"
				+ " FROM Libro l JOIN l.capitulos.autores a"
				+ " GROUP BY a.id ", Reporte3DTO.class);
		List<Reporte3DTO> res = q.getResultList();
		//Imprime los resultados
		res.forEach(r -> System.out.println(r));
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
	
}
