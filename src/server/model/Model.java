package server.model;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.omg.CORBA.portable.InputStream;

import server.controller.Controller;
import entities.GPS.Gps;
import entities.Solution.Solution;
import entities.Task.Task;
import entities.person.Person;

public class Model implements ModelInterface,Runnable {



	//ModelControllerInterface modelController = new ModelController();
	 //ModelDbInterface modelDb = new ModelDb();
	 ModelDb modelDb = new ModelDb();
	// Controller controller;
	 
	 static String dictionaryLocationCityTwoRows="C:/Users/Itzik/workspaceAnother-Me/Another-Me/WebContent/WEB-INF/dataForAlgorithm/israelCity.txt";
	 static String dictionaryLocationStreetCities="C:/Users/Itzik/workspaceAnother-Me/Another-Me/WebContent/WEB-INF/dataForAlgorithm/israelStreetsCities.txt";
	 static	String dictionaryMissions="C:/Users/Itzik/workspaceAnother-Me/Another-Me/WebContent/WEB-INF/dataForAlgorithm/missions.txt";
	 static	String clean="C:/Users/Itzik/workspaceAnother-Me/Another-Me/WebContent/WEB-INF/dataForAlgorithm/hebrewLanguage.txt";
	 private static SpellingCorrector singletonInstance;
	 SpellingCorrector algo=  new SpellingCorrector(dictionaryLocationCityTwoRows, dictionaryLocationStreetCities, dictionaryMissions,clean);
	 //to WAR file
	 
//	 static String dictionaryLocationCityTwoRows="israelCity.txt";
//	 static String dictionaryLocationStreetCities="israelStreetsCities.txt";
//	 static	String dictionaryMissions="missions.txt";
//	 static	String clean="hebrewLanguage.txt";
//	 private static SpellingCorrector singletonInstance;
//	 SpellingCorrector algo=  new SpellingCorrector(dictionaryLocationCityTwoRows, dictionaryLocationStreetCities, dictionaryMissions,clean);

	@Override
	public void CheckSolution() {
		// get the time now (on server)
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date date = new Date();
		
		//5 hours before we check the time to arriving
		date.setHours(date.getHours() +5);
		System.out.println("Check Solution from Mysql");
		ArrayList<Task> task = getTaskOnTime(date);
		for (int i = 0; i < task.size(); i++) {
			if ((task.get(i).getWhatToDo() != 1)&&checkStatus(task.get(i).getIdTask())) {
				switch (task.get(i).getWhatToDo()) {
				case 2:
					// send ask what to do
					break;

				case 3:
					//send popup
					break;

				case 4:
					//send sms
					break;

				case 5:
					//buy ticket
					break;

				case 6:
					//go to shoping
					break;
				case 7:
					//send sms to babysiter
					break;
				case 8:
					if(task.get(i).getAddress()!=null){
						Gps gps = getLastLocation(task.get(i).getPerson().getPersonId());//get last location

						int timeToArrive = CalculatorTime(task.get(i), gps);						
						int timeToGo =  TimeToGo(task.get(i),timeToArrive);
						if(timeToGo==1){
						modelDb.changeStatusSolution("true", task.get(i).getIdTask());
						
						}
					}
					break;

				default:
					break;
				}

			}
		}
	}

	@Override
	public int CalculatorTime(Task task, Gps gps) {
		
		String locationTask= task.getAddress();
		
		//String locationTask="אלי ויזל 20 ראשון לציון ישראל";
		Double locationGpsX = gps.getX();
		Double locationGpsY = gps.getY();
		if( locationTask.equals("null")){
			return 0;
		}
		else{
		return CalculatorTimeFromJson(locationTask, null, locationGpsX,
				locationGpsY);
		}

	}

	@Override
	public ArrayList<Task> getTaskOnTime(Date date) {

		return modelDb.getAllTasksWithDate(date);
	}

	@Override
	public Gps getLastLocation(String PersonId) {

		return modelDb.getGps(PersonId);
	}

	@Override
	public void DoSolution(Task task) {
	
		modelDb.changeStatusSolution("true", task.getIdTask());
		System.out.println("Do somthing!!!!");

	}

	@Override
	public int CalculatorTimeFromJson(String task, String gps, Double x,
			Double y) {
		int timeToArriving = 100;
		String start;
		String url = null;
		try {

			
			String end = URLEncoder.encode(task, "UTF-8");
			// String num = "31.9735387,34.7945931";
				
			if (gps == null) {//with x,y location 
				url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="
						+ x
						+ ","
						+ y
						+ "&destinations="
						+end
						+ ",&mode=driving&language=he-HE&key=AIzaSyBW002RI6PeIk47XTwZChp23vtjNiKupFo";
			} else {//with full address
				start = URLEncoder.encode(gps, "UTF-8");
				url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="
						+ start
						+ "&destinations="
						+ end
						+ ",&mode=driving&language=he-HE&key=AIzaSyBW002RI6PeIk47XTwZChp23vtjNiKupFo";

			}

			java.io.BufferedInputStream in = new java.io.BufferedInputStream(
					new java.net.URL(url).openStream());
			java.io.FileOutputStream fos = new java.io.FileOutputStream(
					"dataJson.json");
			java.io.BufferedOutputStream bout = new BufferedOutputStream(fos,
					1024);
			byte[] data = new byte[1024];
			int num = 0;
			while ((num = in.read(data, 0, 1024)) >= 0) {
				bout.write(data, 0, num);
			}
			bout.close();
			in.close();

			JSONParser parser = new JSONParser();

			Object obj;

			obj = parser.parse(new FileReader("dataJson.json"));//get file

			JSONObject jsonObject = (JSONObject) obj;

			JSONArray msg = (JSONArray) jsonObject.get("rows");
			Object[] jsonOb = msg.toArray();
			JSONObject jsont = (JSONObject) jsonOb[0];

			JSONArray msgg = (JSONArray) jsont.get("elements");
			Object[] jsonObb = msgg.toArray();
			JSONObject jsontt = (JSONObject) jsonObb[0];

			JSONObject noww = (JSONObject) msgg.get(0);

			JSONObject ms = (JSONObject) noww.get("duration");
			Object m = ms.get("text");//get time in txt

			String l = m.toString();
			String[] c = l.split(" ");
			timeToArriving = new Integer(c[0]);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return timeToArriving;

	}

	@Override
	public String [] Algo(String task) {
// the string is: location, time, am/pm, action
		try{
		String[] features =new String[4];
		//System.out.println("the text input to algo: "+task);
		features=algo.parse(task);
	
		for(String feature:features){
			System.out.println("after algo: "+feature);
		}
		return features;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[]  st = null ;
		return st;		
	}
	
	
	@Override
	public int TimeToGo(Task task,int timeToArrive) {
		
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateNow = new Date();
		dateNow.setMinutes(dateNow.getMinutes()-timeToArrive);
		
		Date dateTask= task.getStart();
		dateTask.setMinutes(dateTask.getMinutes()-task.getSolution().getTimeToArriving());
				
		if( dateTask.before(dateNow)){	
			System.out.println("now need to do ");
			return 1;
		}
		return 400;
	}

	@Override
	public Task TaskMaker(Double idTask, Person person, String taskText,
			Date start, Date end, String address, int whatToDo, int platform) {

		String[] afterAlgo = null;
		String newAddress = address;
		int newWhatToDo = whatToDo;
		
		if (start == null || address == null || whatToDo == 0) {
			afterAlgo = Algo(taskText);
			newAddress = Address(address, afterAlgo[0]);
			if(!(afterAlgo[3] == null)){
			newWhatToDo = whatToDo(whatToDo, Integer.parseInt(afterAlgo[3]));
			}

		}

		// if(start==null){
		// DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		// Date DateTimeRegister=null;
		// try {
		// DateTimeRegister = df.parse(afterAlgo[1]);
		// } catch (java.text.ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }

		//System.out.println("the task: " + afterAlgo[3]);

		Task task = new Task(idTask, person, taskText, start, end, newAddress,
				newWhatToDo, platform);

		return task;

	}

	public int whatToDo(int old, int fromAlgo) {

		if (old == 0) {
			return fromAlgo;

		} else {
			return old;
		}

	}

	public String Address(String old, String fromAlgo) {
		if (old == null) {
			return fromAlgo;
		} else {
			return old;
		}
	}

	@Override
	public boolean checkStatus(Double idTask) {
	
		return modelDb.checkStatusFromDB(idTask);
	}
	@Override
	public void run() {
		System.out.println("run back");
		//CheckSolution();
	}

	@Override
	public ArrayList<Task> CheckSolutionForPerson(String person) {
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date date = new Date();
	//	CheckSolution();
		ArrayList<Task> tasks = getTaskOnTime(date);
		ArrayList<Task> taskToDo = new ArrayList<Task>();
		ArrayList<Task> task = modelDb.getTasks(person);
		
		for (int i = 0; i < task.size(); i++) {
			if ((task.get(i).getWhatToDo() != 1)&&!(checkStatus(task.get(i).getIdTask()))) {
				
					if(task.get(i).getAddress()!=null){
						Gps gps = getLastLocation(task.get(i).getPerson().getPersonId());//get last location

						int timeToArrive = CalculatorTime(task.get(i), gps);						
						int timeToGo =  TimeToGo(task.get(i),timeToArrive);
						System.out.println(timeToGo);
						if(timeToGo<=task.get(i).getSolution().getTimeToArriving()){
						modelDb.changeStatusSolution("true", task.get(i).getIdTask());
						taskToDo.add(task.get(i));
					}
				}
			}
		}
		return taskToDo;
	}
	
	
}
