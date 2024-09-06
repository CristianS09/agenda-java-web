package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Agenda;
import model.DAO;


//Mapeia as urls
@WebServlet(urlPatterns = {"/Controller","/list","/form","/add","/delete","/update","/put"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAO dao = new DAO();
	
    public Controller() {
        super();
    }

    //Lida com as requisições do tipo GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Faz um determinada ação de acordo com o caminho do servlet
		String path = request.getServletPath();
		if(path.equals("/list")) {
			anotacao(request, response);
		}else if (path.equals("/form")) {
			response.sendRedirect("adicionar.html");
		}else if (path.equals("/delete")) {
			removerAnotacao(request, response);
		}else if (path.equals("/put")) {
			//Obtem uma anotação a partir do id
			long id = Long.parseLong(request.getParameter("id"));
			Agenda agenda = dao.obterAgenda(id);
			//Envia os paramentros da anotação para o arquivo atualizar.jsp
			request.setAttribute("agenda",agenda);
			RequestDispatcher dispatcher = request.getRequestDispatcher("atualizar.jsp");
			dispatcher.forward(request, response);
		}else if (path.equals("/update")) {
			atualizarAnotacao(request, response);
		}
		else{
			response.sendRedirect("index.html");
		}
	}
	
	//Atualiza uma anotação
	protected void atualizarAnotacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		String titulo = request.getParameter("titulo");
		String conteudo = request.getParameter("conteudo");
		//Verifica os paramentros
		if(conteudo.isEmpty() || conteudo.length() > 255) {
			response.sendRedirect("index.html");
		}else if (titulo.isEmpty() || titulo.length() > 50) {
			response.sendRedirect("index.html");
		}else {
			//Objeto agenda
			Agenda agenda = new Agenda(id,titulo,conteudo);
			//Atualiza a agenda
			dao.atualizarAgenda(agenda);
			response.sendRedirect("list");
		}
	}
	
	//Remove uma anotação
	protected void removerAnotacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Faz um casting para receber o paramentro id como long
		long id = Long.parseLong(request.getParameter("id"));
		//Remove a anotação com base no id
		dao.removerAgenda(id);
		response.sendRedirect("list");
	}
	
	//Envia as anotações do banco de dados para ser exibido no agenda.jsp
	protected void anotacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Seta o atributo a ser envido
		request.setAttribute("lista", dao.retornarAgenda());
		RequestDispatcher dispatcher = request.getRequestDispatcher("agenda.jsp");
		dispatcher.forward(request, response);
	}

	//Lida com as requisições do tipo POST
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Adiciona o tipo de codificação da requisição para UTF-8
		request.setCharacterEncoding("UTF-8");
		//Recebe os paramentros do formulário do arquivo adicionar.html
		String titulo = request.getParameter("titulo");
		String conteudo = request.getParameter("conteudo");
		//Verifica os paramentros
		if(conteudo.isEmpty() || conteudo.length() > 255) {
			response.sendRedirect("index.html");
		}else if (titulo.isEmpty() || titulo.length() > 50) {
			response.sendRedirect("index.html");
		}else {
			//Objeto agenda
			Agenda agenda = new Agenda();
			agenda.setConteudo(conteudo);
			agenda.setTitulo(titulo);
			//Adiciona uma agenda no banco de dados
			dao.adicionarAgenda(agenda);
			response.sendRedirect("list");
		}
	}
}
