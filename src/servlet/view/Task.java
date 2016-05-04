package servlet.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Task.PopUp;
import entities.sms.SMS;

/**
 * Servlet implementation class Task
 */
@WebServlet("/Task")
public class Task extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private ServlerViewInterface view;
	public final static int CALENDARTASK = 6;
	public final static int APPTASK = 7;
	public Task() {
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
		String txt = request.getParameter("txt").toString();
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date DateTimeStart = new Date();
		DateTimeStart.setMinutes(DateTimeStart.getMinutes()+8);
		Date DateTimeEnd = new Date();
        DateTimeEnd.setMonth(4);
        
        Double sms=2.0;
        Double popUp=2.0;
        int action=3;
        int platform=7;
        String withPerson = "eldar";
        
		view.addNewTaskFromView(personId, txt, DateTimeStart, DateTimeEnd, platform,withPerson,popUp,sms,action);
		 response.sendRedirect("nuv.jsp");

	}

}
