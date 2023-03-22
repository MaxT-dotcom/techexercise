

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Project;
import util.UtilDB;

/**
 * Servlet implementation class InsertHB
 */
@WebServlet("/InsertHB")
public class InsertHB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertHB() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name").trim();
		String deadline = request.getParameter("deadline").trim();
		String memberList = request.getParameter("memberList").trim();
		String organization = request.getParameter("organization").trim();
		Project project = new Project(name, deadline, memberList, organization);
		UtilDB.createProject(name, deadline, memberList, organization);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n";
		out.println(docType +
				"<html>\n" +
				"<head><title>Submit Success!</title></head>");
		out.println("<body>");
		out.println("<p>Inserted project with the data:\n" + project.toString() + "</p><br>");
		out.println("<a href=techexercise/search.html> Search the database </a><br>");
		out.println("<a href=techexercise/insert.html> Insert more data </a><br>");
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
