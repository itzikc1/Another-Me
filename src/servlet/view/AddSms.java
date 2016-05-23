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
 * Servlet implementation class Sms
 */
@WebServlet("/AddSms")
public class AddSms extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private ServlerViewInterface view;
    public AddSms() {
        super();
    	view = new ServlerViewInterface();

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
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String personId = request.getParameter("personId").toString();
		String txt = request.getParameter("txt").toString();
		String sendId = request.getParameter("sendTo").toString();
		System.out.println(sendId);
		Boolean bool = true;
		if(sendId==""){
			sendId ="default";
		}
		else{
			bool=false; 
		}
		view.addSmsToDefaultFromView(bool, txt, sendId, personId);
		 //response.sendRedirect("nuv.jsp");

	}

}
