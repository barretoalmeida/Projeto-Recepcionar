package controller;

import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO;
import model.ImpressoraTermica;
import model.JavaBeans;

/**
 * Servlet implementation class Controller
 */
@WebServlet({ "/main", "/insert", "/update", "/imprimir" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans cadastro = new JavaBeans();

	/**
	 * Default constructor.
	 */
	public Controller() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dao.testeConexao();

		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/insert")) {
			tipoCadastro(request, response);
		}else if (action.equals("/update")) {
			inserirCadastro(request, response);
		}else if (action.equals("/imprimir")) {
			imprimirSenha(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    doGet(request, response);
	}

	protected void tipoCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tipo = request.getParameter("tipo");

		JavaBeans cadastro = new JavaBeans();
		cadastro.setTipo(tipo);

		//dao.tipoCadastro(cadastro);

		System.out.println(tipo);

		// teste para saber o que esta lendo
		System.out.println("Tipo recebido: [" + tipo + "]");

		if (tipo.equalsIgnoreCase("Estudante")) {
			
			int idGerado =  dao.tipoCadastro(cadastro);
			System.out.println("ID GERADO: " + idGerado);
			request.setAttribute("id", idGerado);
			RequestDispatcher rd = request.getRequestDispatcher("estudante.jsp");
			rd.forward(request, response);
			
		} else if (tipo.equalsIgnoreCase("Visitante")) {
			
			int idGerado =  dao.tipoCadastro(cadastro);
			System.out.println("ID GERADO: " + idGerado);
			request.setAttribute("id", idGerado);
			System.out.println("Entrou no visitante");
			RequestDispatcher rd = request.getRequestDispatcher("visitante.jsp");
			rd.forward(request, response);
		}
		
	}
	
	protected void inserirCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		System.out.println(request.getParameter("id"));
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("data"));
		System.out.println(request.getParameter("cursos"));
		
		cadastro.setId(request.getParameter("id"));
		cadastro.setNome(request.getParameter("nome"));
		cadastro.setData(request.getParameter("data"));
		cadastro.setCursos(request.getParameter("cursos"));
		
		//enviar para o dao
		dao.inserirCadastro(cadastro);
		
		//ENVIA O ID PARA O JSP
	    request.setAttribute("id", cadastro.getId());
	    request.setAttribute("nome", cadastro.getNome());
	    request.setAttribute("data", cadastro.getData());
	    request.setAttribute("cursos", cadastro.getCursos());
		
		String userAgent = request.getHeader("User-Agent");

		boolean celular =
		    userAgent != null &&
		    (userAgent.contains("Android")
		    || userAgent.contains("iPhone")
		    || userAgent.contains("Mobile"));

		if (celular) {
		    gerarPdf(request, response);
		    return;
		}

		RequestDispatcher rd = request.getRequestDispatcher("cadastrado.jsp");
		rd.forward(request, response);
	
	}
	
	protected void imprimirSenha(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		String cursos = request.getParameter("cursos");
		String nome = request.getParameter("nome");
	
		 ImpressoraTermica impressora = new ImpressoraTermica();

		 impressora.imprimirSenha(String.valueOf(id), cursos,nome);
		 
		 System.out.println("Entrou no método imprimir");
		 System.out.println("ID recebido: " + id);
		 System.out.println("Nome recebido: " + nome);
		 System.out.println("Tipo recebido: " + cursos);
		
		response.sendRedirect("index.html");
	}
	
	protected void gerarPdf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Entrou no método gerarPdf");
	    response.setContentType("application/pdf");
	    response.setHeader("Content-Disposition", "attachment; filename=senha.pdf");
	    
	    System.out.println("ID PDF: " + cadastro.getId());
	    System.out.println("Nome PDF: " + cadastro.getNome());
	    System.out.println("Curso PDF: " + cadastro.getCursos());

	    Document documento = new Document();

	    try {
	    	
	        PdfWriter.getInstance(documento, response.getOutputStream());

	        	documento.open();

	        	documento.add(new Paragraph("TESTE PDF"));
	        	documento.add(new Paragraph("123456"));
	        /*documento.add(new Paragraph("Senha de Atendimento"));
	        documento.add(new Paragraph("ID: " + cadastro.getId()));
	        documento.add(new Paragraph("Nome: " + cadastro.getNome()));
	        documento.add(new Paragraph("Tipo: " + cadastro.getCursos()));*/

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        documento.close();
	    }
	}
	
}
