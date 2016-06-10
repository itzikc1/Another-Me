package servlet.view;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import entities.Task.Task;
import entities.pictures.SharePictures;

/**
 * Servlet implementation class GetTasksToDo
 */
@WebServlet("/GetTasksToDo")
public class GetTasksToDo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private ServlerViewInterface view;

	public GetTasksToDo() {
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

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		String personId = request.getParameter("name").toString();

		ArrayList<SharePictures> sharePictures = view.getShareSender(personId);
		ArrayList<SharePictures> sharePicturesUpdate = view
				.getShareUpdate(personId);
		ArrayList<Task> task = view.CheckSolutionForPerson(personId);

		ObjectMapper sharePicturesMapper = new ObjectMapper();
		ObjectMapper sharePicturesUpdateMapper = new ObjectMapper();
		ObjectMapper taskMapper = new ObjectMapper();

		// Convert object to JSON string and save into file directly
		try {
			if (!sharePicturesUpdate.isEmpty()) {
				sharePicturesUpdateMapper.writeValue(new File(
						"sharePicturesUpdate.json"), sharePicturesUpdate);
				// Convert object to JSON string
				String sharePicturesUpdateString = sharePicturesUpdateMapper
						.writeValueAsString(sharePicturesUpdate);

				System.out.println(sharePicturesUpdateString);

				// Convert object to JSON string and pretty print
				sharePicturesUpdateString = sharePicturesMapper
						.writerWithDefaultPrettyPrinter().writeValueAsString(
								sharePicturesUpdate);

				out.print(sharePicturesUpdateString);
			}
			if (!task.isEmpty()) {
				taskMapper.writeValue(new File("task.json"), task);
				// Convert object to JSON string
				String taskString = taskMapper.writeValueAsString(task);

				System.out.println(taskString);

				// Convert object to JSON string and pretty print
				taskString = taskMapper.writerWithDefaultPrettyPrinter()
						.writeValueAsString(task);

				out.print(taskString);
			}
			sharePicturesMapper
					.writeValue(new File("user.json"), sharePictures);
			// Convert object to JSON string
			String jsonInString = sharePicturesMapper
					.writeValueAsString(sharePictures);

			System.out.println(jsonInString);

			// Convert object to JSON string and pretty print
			jsonInString = sharePicturesMapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(sharePictures);

			out.print(jsonInString);
			// System.out.println(jsonInString);
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

}
