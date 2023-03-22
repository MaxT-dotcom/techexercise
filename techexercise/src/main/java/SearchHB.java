

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.UtilDB;
import datamodel.Project;

/**
 * Servlet implementation class SearchHB
 */
@WebServlet("/SearchHB")
public class SearchHB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchHB() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword").trim();
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n";
		
		out.println(docType +
				"<html>\n" +
				"<head><title>Search Success!</title></head>\n");
		out.println("<ul>");
		
		List<Project> listProjects = null;
		if (keyword != null && !keyword.isEmpty()) {
			listProjects = UtilDB.listProjects(keyword);
		} else {
			listProjects = UtilDB.listProjects();
		}
		
		for (Project project : listProjects) {
			out.println("<li>" + project.getName() + ", " +
							project.getDeadline() + ", " +
							project.getMemberList() + ", " +
							project.getOrganization() + "</li>");
		}
		
		out.println("</ul>");
		out.println("<a href=/techexercise/search.html> Search the database again</a><br>");
		out.println("<a href=/techexercise/insert.html> Insert data </a><br>");
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
