package servlet.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.pictures.Pictures;

/**
 * Servlet implementation class GetPictures
 */
@WebServlet("/GetPictures")
public class GetPictures extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServlerViewInterface view;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPictures() {
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
		PrintWriter out = response.getWriter();
		String personId = request.getParameter("name").toString();		
	    
		ArrayList<Pictures> pictures = view.getPictures(personId);

		for (int z = 0; z < pictures.size(); z++) {		
			System.out.println(pictures.get(z).getPictureName());
	 }
		
		 response.sendRedirect("nuv.jsp");
		
	}

}
