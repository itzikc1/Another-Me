package servlet.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entities.person.Person;

/**
 * Servlet implementation class PostPersonServlet
 */
@WebServlet("/PostPersonServlet")
public class PostPersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private ServlerViewInterface view;

	public PostPersonServlet() {
		super();
		view = new ServlerViewInterface();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String personId = request.getParameter("name").toString();
		Person person = view.getPersonFromView(personId);

		// response.setContentType("text/html;charset=UTF-8");
		// String nuv=response.encodeURL("nuv.jsp");
	
		String Mail = person.getSettings().getMail();
		String PhoneNumber = person.getSettings().getPhoneNumber();
		int Age = person.getSettings().getAge();
		Date DateTimeRegister = person.getSettings().getDateTimeRegister();
		String FullName = person.getSettings().getFullName();
		Boolean PopUps = person.getSettings().getPopUps();
		Boolean Sms = person.getSettings().getSms();
		Boolean Solution = person.getSettings().getSolution();
		Boolean Gps = person.getSettings().getGps();
				
//		request.setAttribute("PersonId",personId);
//		request.getRequestDispatcher("GetPerson.jsp").forward(request, response); 

 		PrintWriter out = response.getWriter();

		out.println("<html><body>");

		out.println("<p>" + person.getPersonId() + "</p>");
		out.println("<p>" + Mail + "</p>");
		out.println("<p>" + PhoneNumber + "</p>");
		out.println("<p>" + Age + "</p>");
		out.println("<p>" + DateTimeRegister + "</p>");
		out.println("<p>" + FullName + "</p>");
		out.println("<p>" + PopUps + "</p>");
		out.println("<p>" + Sms + "</p>");
		out.println("<p>" + Solution + "</p>");
		out.println("<p>" + Gps + "</p>");
		out.println("</body></html>");

	}

}

//request.setAttribute("Age",Age);
//request.getRequestDispatcher("GetPerson.jsp").forward(request, response); 
//request.setAttribute("PhoneNumber",PhoneNumber);
//request.getRequestDispatcher("GetPerson.jsp").forward(request, response); 
//request.setAttribute("DateTimeRegister",DateTimeRegister);
//request.getRequestDispatcher("GetPerson.jsp").forward(request, response); 
//request.setAttribute("FullName",FullName);
//request.getRequestDispatcher("GetPerson.jsp").forward(request, response); 
//request.setAttribute("PopUps",PopUps);
//request.getRequestDispatcher("GetPerson.jsp").forward(request, response); 
//request.setAttribute("Sms",Sms);
//request.getRequestDispatcher("GetPerson.jsp").forward(request, response); 
//request.setAttribute("Solution",Solution);
//request.getRequestDispatcher("GetPerson.jsp").forward(request, response); 
//request.setAttribute("Gps",Gps);
//request.getRequestDispatcher("GetPerson.jsp").forward(request, response); 

//String PhoneNumber = (String)request.getAttribute("PhoneNumber");
//int Age = (int)request.getAttribute("Age");
////Date DateTimeRegister = (Date)request.getAttribute("DateTimeRegister");
//String FullName = (String)request.getAttribute("FullName");
//		Boolean PopUps = (Boolean)request.getAttribute("PopUps");
//		Boolean Sms = (Boolean)request.getAttribute("Sms");
//		Boolean Solution = (Boolean)request.getAttribute("Solution");
//		Boolean Gps = (Boolean)request.getAttribute("Gps");



//System.out.println(PhoneNumber);
//System.out.println(Age);
//System.out.println(FullName);
//System.out.println(PopUps);
//System.out.println(Sms);
//System.out.println(Solution);
//System.out.println(Gps);