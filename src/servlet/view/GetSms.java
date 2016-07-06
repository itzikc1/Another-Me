package servlet.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.sms.SMS;

/**
 * Servlet implementation class GetSms
 */
@WebServlet("/GetSms")
public class GetSms extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private ServlerViewInterface view;
	
    public GetSms() {
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
		request.setCharacterEncoding("UTF-8");

		String personId = request.getParameter("name").toString();
		
		String sendId = request.getParameter("sendTo").toString();
		String defaultt = request.getParameter("default").toString();
		
		//System.out.println(sendId);
		Boolean bool = true;
		if(sendId==""){
			sendId ="default";
			if(defaultt.equals("no")){//get all
				bool =false;
			}
			
		}
		else{
			if(defaultt.equals("yes")||sendId.equals("")){
			bool=true; 
			}
			else{
				bool=false; 
			}
		}
		
		
		ArrayList<SMS> sms= view.getSmsFromView(personId,sendId, bool);//Default

		for (int z = 0; z < sms.size(); z++) {
			
			System.out.println(sms.get(z).getSenderId().getPersonId());
	 }
	}
}
