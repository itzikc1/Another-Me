package servlet.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		  System.out.println("new task!!!!!");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String personId = request.getParameter("personId").toString();
		System.out.println(personId);
		String address = request.getParameter("address").toString();
		String txt = request.getParameter("txt").toString();
		String withPerson = request.getParameter("withPerson").toString();
		String popUpString = request.getParameter("popUp").toString();
		String smsString = request.getParameter("sms").toString();
		String actionString = request.getParameter("action").toString();
		String start = request.getParameter("start").toString();
		String end = request.getParameter("end").toString();
        long startMillisecond = Long.parseLong(start);
        long endMillisecond = Long.parseLong(end);

        Date s = new Date(startMillisecond);
        Date en = new Date(endMillisecond);
        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		
		Date DateTimeStart = null;
		Date DateTimeEnd = null;
		try {
			DateTimeStart = df.parse(dateformat.format(s));
			DateTimeEnd = df.parse(dateformat.format(en));
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Double sms;
        Double popUp;
        if(smsString.equals("null")){
        	sms = 1.0;
        }
        else{
        	sms=  Double.valueOf(smsString);

        	
        }
   if(popUpString.equals("null")){
	    popUp = 1.0;
        }
   else{
	    popUp=Double.valueOf(popUpString);

   }
        
      
        int platform=7;
        
        if(address.equals("null")){
        	address =null;
		}
        int action;
        if(actionString.equals("null")){
        	action =0;
		}
        else{
        	action=Integer.valueOf(actionString);
        }
        
        if(withPerson.equals("null")){
        	withPerson ="noBodey";
		}
		view.addNewTaskFromView(personId, txt, DateTimeStart, DateTimeEnd, address, platform,withPerson,popUp,sms,action);

	}

}
