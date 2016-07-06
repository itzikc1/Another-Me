package servlet.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Task.Task;


/**
 * Servlet implementation class GetSms
 */
@WebServlet("/GetTask")
public class GetTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private ServlerViewInterface view;
	
    public GetTask() {
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
		
		ArrayList<Task> task = view.getAllTaskFromView(personId);

		for (int z = 0; z < task.size(); z++) {		
			System.out.println(task.get(z).getTaskText());
	 }

	}
	
}
