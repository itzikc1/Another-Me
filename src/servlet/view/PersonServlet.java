package servlet.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.controller.Controller;
import server.view.View;
import server.view.ViewInterface;
import entities.GPS.Gps;
import entities.Task.PopUp;
import entities.Task.Task;
import entities.person.Settings;
import entities.person.Person;
import entities.sms.SMS;

/**
 * Servlet implementation class View
 */
@WebServlet("/PersonServlet")
public class PersonServlet extends HttpServlet  {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	// private View view;
	private ServlerViewInterface view;

	public PersonServlet() {
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
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String personId = request.getParameter("personId").toString();
		System.out.println(personId+"  !!!!!!1");
		String password = request.getParameter("password").toString();
		String mail = request.getParameter("mail").toString();
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date DateTimeRegister = new Date();
         System.out.println(DateTimeRegister);
		String phoneNumber = request.getParameter("phoneNumber").toString();

		if(!view.checkIfPersonExists(personId)){
		
		
		view.addNewPersonFromView(personId, password, DateTimeRegister, mail,
				phoneNumber);
		out.println("true");
		}
		else{
			System.out.println("the id id exists");
			out.println("false");
			
		}
		

	}

}
