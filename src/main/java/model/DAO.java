package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mysql.cj.Query;

//Classe responsável por conter os métodos de interação com o banco de dados
public class DAO {
	//Objeto que lê o arquivo persistence.xml
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("agenda");
	//Objeto que realiza o CRUD
	private EntityManager manager = factory.createEntityManager();
	
	//Adiciona uma anotação no banco de dados
	public void adicionarAgenda(Agenda agenda) {
		try {
			manager.getTransaction().begin();
			manager.persist(agenda);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			System.out.println(e.getMessage());
		}
	}
	
	//Retorna todos as anotações do banco de dados
	public List<Agenda> retornarAgenda(){
		try {
			List<Agenda> agendaList = manager.createQuery("select a from Agenda a").getResultList();
			return agendaList;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	//Remove uma anotação do banco de dados
	public void removerAgenda(long id) {
		try {
			Agenda agenda = manager.find(Agenda.class, id);
			manager.getTransaction().begin();
			manager.remove(agenda);
			manager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			manager.getTransaction().rollback();
		}
	}
	
	//Obtem uma anotação a partir do id
	public Agenda obterAgenda(long id) {
		Agenda agenda = manager.find(Agenda.class, id);
		if(agenda == null) {
			return null;
		}else {
			return agenda;	
		}
	}
	
	//Atualiza uma anotação
	public void atualizarAgenda(Agenda a) {
		Agenda agenda = manager.find(Agenda.class, a.getId());
		if(agenda == null) {
			throw new NullPointerException();
		}
		try {
			agenda.setTitulo(a.getTitulo());
			agenda.setConteudo(a.getConteudo());
			manager.getTransaction().begin();
			manager.merge(agenda);
			manager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			manager.getTransaction().rollback();
		}
	}
}
