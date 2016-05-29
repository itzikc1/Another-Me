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
 * Servlet implementation class SharePictures
 */
@WebServlet("/SharePictures")
public class SharePictures extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ServlerViewInterface view;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SharePictures() {
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
		String personId = request.getParameter("personId").toString();
		String pictureName = request.getParameter("pictureName").toString();
		String personToSend = request.getParameter("personToSend").toString();
		String txt = request.getParameter("txt").toString();
		
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateTime = new Date();
	
		view.addPicturesToShare(pictureName, personId, dateTime, personToSend, txt);
    
		response.sendRedirect("nuv.jsp");
	
	}

}
