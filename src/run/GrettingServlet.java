package run;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.db.ConnectToMysql;
import server.db.MySql;

/**
 * Servlet implementation class GrettingServlet
 */
@WebServlet("/GrettingServlet")
public class GrettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GrettingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
//        String firstName = request.getParameter("firstName").toString();
//        String lastName = request.getParameter("lastName").toString();

        String name = request.getParameter("Username").toString();
        System.out.println(name);
        String id = request.getParameter("id").toString();

			 MySql m = new MySql();
			 m.newTable(name, id,5);
	         
	        
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet GreetingServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>Welcome " + name + " " + id + "</p>");
        out.println("</body>");
        out.println("</html>");

        out.close();

	}

}
