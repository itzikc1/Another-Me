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
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

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
		String address = request.getParameter("address").toString();
		String txt = request.getParameter("txt").toString();
		String withPerson = request.getParameter("withPerson").toString();
		String popUpString = request.getParameter("popUp").toString();
		String smsString = request.getParameter("sms").toString();
		String actionString = request.getParameter("action").toString();		
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date DateTimeStart = new Date();
		DateTimeStart.setMinutes(DateTimeStart.getMinutes()+30);
		Date DateTimeEnd = new Date();
        DateTimeEnd.setMonth(8);  
        Double sms=Double.valueOf(smsString);
        Double popUp=Double.valueOf(popUpString);
        int platform=7;
        
        if(address==""){
        	address =null;
		}
        int action;
        if(actionString==""){
        	action =0;
		}
        else{
        	action=Integer.valueOf(actionString);
        }
        
        if(withPerson==""){
        	withPerson ="noBodey";
		}
		view.addNewTaskFromView(personId, txt, DateTimeStart, DateTimeEnd, address, platform,withPerson,popUp,sms,action);
		 response.sendRedirect("nuv.jsp");

	}

}
