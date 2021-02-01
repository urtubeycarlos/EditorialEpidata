package edu.epidata;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.epidata.jpa.Capitulo;
import edu.epidata.jpa.Libro;
import edu.epidata.jpa.Persona;

public class CargaDatos {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		//Crea el Entity manager factory con la configuración
		//llamada editorial
		EntityManagerFactory emf = Persistence.
				createEntityManagerFactory("Editorial");
		EntityManager em = emf.createEntityManager();
		cargarPersonas(em);
		cargarLibros(em);
		cargarCapitulos(em);
		em.close();
		emf.close();
	}

	private static void cargarCapitulos(EntityManager em) throws FileNotFoundException, IOException {
		CSVParser parser = CSVFormat.DEFAULT.
				withHeader().parse(new FileReader("autor.csv"));
		Map<Integer, List<Integer>> autores = new HashMap<>();
		parser.forEach(r -> {
			int chapter = Integer.parseInt(r.get("Capitulo_id"));
			int autor = Integer.parseInt(r.get("autores_id"));
			List<Integer> aut = autores.get(chapter);
			if (aut == null) {
				aut = new ArrayList<>();
				autores.put(chapter, aut);
			}
			aut.add(autor);
		});
		parser = CSVFormat.DEFAULT.
				withHeader().parse(new FileReader("capitulo.csv"));
		em.getTransaction().begin();
		for(CSVRecord row: parser) {
			int id = Integer.parseInt(row.get("id"));
			@SuppressWarnings("unchecked")
			List<Persona> authors = (List<Persona>) autores.getOrDefault(id, Collections.EMPTY_LIST).
					stream().
					map(pid -> em.find(Persona.class, pid)).
					collect(Collectors.toList());
			Persona revisor = em.find(Persona.class, Integer.parseInt(row.get("revisor_id")));
			Libro libro = em.find(Libro.class, Integer.parseInt(row.get("libro_id")));
			Capitulo c = new Capitulo(Integer.parseInt(row.get("id")), 
					row.get("titulo"), 
					Integer.parseInt(row.get("paginas")), 
					revisor, 
					authors, 
					libro);
			em.persist(c);
		}
		em.getTransaction().commit();
	}

	private static void cargarLibros(EntityManager em) throws FileNotFoundException, IOException {
		CSVParser parser = CSVFormat.DEFAULT.
				withHeader().parse(new FileReader("editor.csv"));
		Map<Integer, List<Integer>> editores = new HashMap<>();
		parser.forEach(r -> {
			int book = Integer.parseInt(r.get("Libro_id"));
			int editor = Integer.parseInt(r.get("editores_id"));
			List<Integer> edi = editores.get(book);
			if (edi == null) {
				edi = new ArrayList<>();
				editores.put(book, edi);
			}
			edi.add(editor);
		});
		parser = CSVFormat.DEFAULT.
				withHeader().parse(new FileReader("libro.csv"));
		em.getTransaction().begin();
		for(CSVRecord row: parser) {
			int id = Integer.parseInt(row.get("id"));
			@SuppressWarnings("unchecked")
			List<Persona> editors = (List<Persona>) editores.getOrDefault(id, Collections.EMPTY_LIST).
					stream().
					map(pid -> em.find(Persona.class, pid)).
					collect(Collectors.toList());
			Libro l = new Libro(id, 
					row.get("nombre"),
					Integer.parseInt(row.get("anio")),
					editors);
			em.persist(l);
		}
		em.getTransaction().commit();
	}

	private static void cargarPersonas(EntityManager em) throws FileNotFoundException, IOException {
		CSVParser parser = CSVFormat.DEFAULT.
				withHeader().parse(new FileReader("persona.csv"));
		em.getTransaction().begin();
		for(CSVRecord row: parser) {
			Persona p = new Persona(Integer.parseInt(row.get("id")), 
					row.get("nombre"),
					row.get("apellido"),
					row.get("mail"));
			em.persist(p);
		}
		em.getTransaction().commit();
	}
}
