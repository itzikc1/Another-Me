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
 * Servlet implementation class GetPicture
 */
@WebServlet("/AddPicture")
public class AddPicture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ServlerViewInterface view;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPicture() {
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
		PrintWriter out = response.getWriter();
		String personId = request.getParameter("personId").toString();
		String PicId = request.getParameter("pictureId").toString();
		
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateTime = new Date();
		
		view.addPictures(PicId, personId, dateTime);
		 response.sendRedirect("nuv.jsp");

	}

}
