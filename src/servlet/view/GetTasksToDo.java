package servlet.view;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
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

import entities.GPS.Gps;
import entities.Solution.Solution;
import entities.Task.PopUp;
import entities.Task.Task;
import entities.person.Person;
import entities.pictures.SharePictures;
import entities.sms.SMS;

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

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String personId = request.getParameter("name").toString();
			ArrayList<Task> task = view.CheckSolutionForPerson(personId);
			ArrayList<ClientTask> clientTask = new ArrayList<ClientTask>();
		 ObjectMapper taskMapper = new ObjectMapper();
		 		// Convert object to JSON string and save into file directly
		try {
			if (!task.isEmpty()) {
				for (int i = 0; i < task.size(); i++) {
					
					task.get(i).setWithPerson(null);
					task.get(i).setPerson(null);
					task.get(i).getSolution().setTask(null);

					if(task.get(i).getSolution().getSms().getSenderId().getPersonId().indexOf("&") !=-1){
						String[] parts = task.get(i).getSolution().getSms().getSenderId().getPersonId().split("&");
						task.get(i).getSolution().getSms().getSenderId().setPersonId(parts[0]);	
					}
					ClientTask c = new ClientTask();
					c.setAddress(task.get(i).getAddress());
					c.setDateTimeSend(task.get(i).getSolution().getSms().getDateTimeSend());
					c.setDateTimeShow(task.get(i).getSolution().getPopUp().getDateTimeShow());
					c.setEnd(task.get(i).getEnd());
					c.setStart(task.get(i).getStart());
					c.setPopup(task.get(i).getSolution().getPopUp().getText());
					c.setSms(task.get(i).getSolution().getSms().getMsg());
					c.setTaskText(task.get(i).getTaskText());
					c.setTimeToArriving(task.get(i).getSolution().getTimeToArriving());
					c.setWithPerson(task.get(i).getSolution().getSms().getSenderId().getPersonId());
					c.setWhatToDo(task.get(i).getWhatToDo());
					clientTask.add(c);
				}
		
				System.out.println("Task is no empty");

			taskMapper.writeValue(new File("task.json"), task);
				// Convert object to JSON string
				
				//String taskString = taskMapper.writeValueAsString(task);

				// Convert object to JSON string and pretty print
				
				//taskString = taskMapper.writerWithDefaultPrettyPrinter().writeValueAsString(task);
				
				//out.print(taskString);
			String taskString = taskMapper.writeValueAsString(clientTask);
				out.print(taskString);

				
				
//				n kk = new n();
//				kk.setCc("hii");
//				kk.setIt("or");
//				
//				itzik itt = new itzik();
//				itt.setCc("hii");
//				itt.setIt("or");
//				itzik ittt = new itzik();
//				ittt.setCc("hiiyy");
//				ittt.setIt("oryy");
//				itt.setTt(kk);
//				ittt.setTt(kk);
//				
//				ArrayList<itzik> y = new ArrayList<itzik>();
//				y.add(itt);
//				y.add(ittt);
//				
//				String tt = taskMapper.writeValueAsString(y);
//				out.print(tt);

				
			}
			else{
				taskMapper.writeValue(new File("task.json"), null);
				// Convert object to JSON string
				System.out.println("Task is empty!!!!");
				String taskString = taskMapper.writeValueAsString(null);

				// Convert object to JSON string and pretty print
				taskString = taskMapper.writerWithDefaultPrettyPrinter()
						.writeValueAsString(null);

				out.print(taskString);
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
//	  public  class   itzik{
//	        public String it;
//	        public String cc;
//
//	     // public itzik(){}
//	        public String getIt() {
//	            return it;
//	        }
//	        public void setIt(String it) {
//	            this.it = it;
//	        }
//	        public String getCc() {
//	            return cc;
//	        }
//	        public void setCc(String cc) {
//	            this.cc = cc;
//	        }
//	        
//	    }
	
	 public  class  ClientTask{
		 public	String taskText;
		 public	Date start;
		 public	Date end;
		 public	String address;
		 public	int whatToDo;
		 public	String withPerson;
			
		 public	int timeToArriving;
		 public String Sms;
			public	Date DateTimeSend;// if it send if not ->null
			public	String Popup;
			public	Date DateTimeShow; // if it show if not ->null

			public ClientTask() {
				// TODO Auto-generated constructor stub
			}
			public String getTaskText() {
				return taskText;
			}
			public void setTaskText(String taskText) {
				this.taskText = taskText;
			}
			public Date getStart() {
				return start;
			}
			public void setStart(Date start) {
				this.start = start;
			}
			public Date getEnd() {
				return end;
			}
			public void setEnd(Date end) {
				this.end = end;
			}
			public String getAddress() {
				return address;
			}
			public void setAddress(String address) {
				this.address = address;
			}
			public int getWhatToDo() {
				return whatToDo;
			}
			public void setWhatToDo(int whatToDo) {
				this.whatToDo = whatToDo;
			}
			public String getWithPerson() {
				return withPerson;
			}
			public void setWithPerson(String withPerson) {
				this.withPerson = withPerson;
			}
			
			public int getTimeToArriving() {
				return timeToArriving;
			}
			public void setTimeToArriving(int timeToArriving) {
				this.timeToArriving = timeToArriving;
			}
			public String getSms() {
				return Sms;
			}
			public void setSms(String sms) {
				Sms = sms;
			}
			public Date getDateTimeSend() {
				return DateTimeSend;
			}
			public void setDateTimeSend(Date dateTimeSend) {
				DateTimeSend = dateTimeSend;
			}
			public String getPopup() {
				return Popup;
			}
			public void setPopup(String popup) {
				Popup = popup;
			}
			public Date getDateTimeShow() {
				return DateTimeShow;
			}
			public void setDateTimeShow(Date dateTimeShow) {
				DateTimeShow = dateTimeShow;
			}
			
	    }
	  
}
