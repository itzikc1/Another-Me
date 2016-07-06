package servlet.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Task.PopUp;

/**
 * Servlet implementation class GetSms
 */
@WebServlet("/GetPopUp")
public class GetPopUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private ServlerViewInterface view;
	
    public GetPopUp() {
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
		
		String personId = request.getParameter("name").toString();
		
		String sendId = request.getParameter("sendTo").toString();
		String defaultt = request.getParameter("default").toString();
		
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

		ArrayList<PopUp> popUp= view.getPopUpFromView(personId,sendId, bool);

		for (int z = 0; z < popUp.size(); z++) {
			
			System.out.println(popUp.get(z).getSenderId().getPersonId());
	 }
	}
}
