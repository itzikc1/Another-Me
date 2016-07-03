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
		request.setCharacterEncoding("UTF-8");

		String personId = request.getParameter("name").toString();
		Person person = view.getPersonFromView(personId);
	
		String Mail = person.getSettings().getMail();
		String PhoneNumber = person.getSettings().getPhoneNumber();
		int Age = person.getSettings().getAge();
		Date DateTimeRegister = person.getSettings().getDateTimeRegister();
		String FullName = person.getSettings().getFullName();
		Boolean PopUps = person.getSettings().getPopUps();
		Boolean Sms = person.getSettings().getSms();
		Boolean Solution = person.getSettings().getSolution();
		Boolean Gps = person.getSettings().getGps();
				
 		PrintWriter out = response.getWriter();
 		
 		
		out.println(person.getPersonId());
		out.println(Mail);
		out.println(PhoneNumber);
		out.println(Age);
		out.println(DateTimeRegister);
		out.println(FullName);
		out.println(PopUps);
		out.println(Sms);
		out.println(Solution);
		out.println(Gps);
//		out.println("<html><body>");
//		out.println("<p>" + person.getPersonId() + "</p>");
//		out.println("<p>" + Mail + "</p>");
//		out.println("<p>" + PhoneNumber + "</p>");
//		out.println("<p>" + Age + "</p>");
//		out.println("<p>" + DateTimeRegister + "</p>");
//		out.println("<p>" + FullName + "</p>");
//		out.println("<p>" + PopUps + "</p>");
//		out.println("<p>" + Sms + "</p>");
//		out.println("<p>" + Solution + "</p>");
//		out.println("<p>" + Gps + "</p>");
//		out.println("</body></html>");
	}
}
