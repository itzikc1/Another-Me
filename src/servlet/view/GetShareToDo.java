package servlet.view;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import entities.pictures.SharePictures;

/**
 * Servlet implementation class GetShareToDo
 */
@WebServlet("/GetShareToDo")
public class GetShareToDo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private ServlerViewInterface view;

    public GetShareToDo() {
        super();
        // TODO Auto-generated constructor stub

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
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
	
		String personId = request.getParameter("name").toString();
		ArrayList<SharePictures> sharePicturesUpdate = view
				.getShareUpdate(personId);

		ArrayList<ClientShare> clientShare = new ArrayList<ClientShare>();
		ObjectMapper sharePicturesUpdateMapper = new ObjectMapper();

		// Convert object to JSON string and save into file directly
		try {
			if (!sharePicturesUpdate.isEmpty()) {
				
				
				for (int i = 0; i <sharePicturesUpdate.size(); i++) {
					if(sharePicturesUpdate.get(i).getWithPerson().getPersonId().indexOf("&") !=-1){
						String[] parts = sharePicturesUpdate.get(i).getWithPerson().getPersonId().split("&");
						sharePicturesUpdate.get(i).getWithPerson().setPersonId(parts[0]);	
					}
					ClientShare c = new ClientShare();
					c.setPerson(sharePicturesUpdate.get(i).getPerson().getPersonId());
					c.setPictureName(sharePicturesUpdate.get(i).getPictureName());
					c.setTxt(sharePicturesUpdate.get(i).getTxt());
					c.setWithPerson(sharePicturesUpdate.get(i).getWithPerson().getPersonId());
					clientShare.add(c);
				}
				
				
				sharePicturesUpdateMapper.writeValue(new File("sharePicturesUpdate.json"), clientShare);
				// Convert object to JSON string
				String sharePicturesUpdateString = sharePicturesUpdateMapper.writeValueAsString(clientShare);

				System.out.println("share Pictures Update :  "+sharePicturesUpdateString);

				// Convert object to JSON string and pretty print
				sharePicturesUpdateString = sharePicturesUpdateMapper.writerWithDefaultPrettyPrinter().writeValueAsString(
						clientShare);

				out.print(sharePicturesUpdateString);
			}
			else{
				sharePicturesUpdateMapper.writeValue(new File(
						"sharePicturesUpdate.json"), null);
				// Convert object to JSON string
				String sharePicturesUpdateString = sharePicturesUpdateMapper
						.writeValueAsString(null);

				System.out.println("share Pictures Update :  "+sharePicturesUpdateString);

				// Convert object to JSON string and pretty print
				sharePicturesUpdateString = sharePicturesUpdateMapper
						.writerWithDefaultPrettyPrinter().writeValueAsString(
								null);

				out.print(sharePicturesUpdateString);
			}
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
public class ClientShare{
	public String pictureName;
	public String person;
	public String withPerson;
	public String txt;
	
	public ClientShare() {
		// TODO Auto-generated constructor stub
	}
	public String getPictureName() {
		return pictureName;
	}
	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	
	public String getWithPerson() {
		return withPerson;
	}
	public void setWithPerson(String withPerson) {
		this.withPerson = withPerson;
	}
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	
}

}
