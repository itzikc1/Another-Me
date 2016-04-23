package server.model;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import entities.GPS.Gps;
import entities.Solution.Solution;
import entities.Task.Task;

public class Model implements ModelInterface {


	//public ModelController modelController;
	 ModelDb db;

	
	
	
	//need to do
	// after algo
	@Override
	public Task TaskMaker(String personId, String taskText, Date start,
			Date end, int platform) {

		return null;
	}

	@Override
	public void CheckSolution() {
		// get the time now (on server)
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date date = new Date();
		//5 hours before we check the time to arriving
		date.setHours(date.getHours() - 5);
		
		ArrayList<Task> task = getTaskOnTime(date);
		for (int i = 0; i < task.size(); i++) {
			if (task.get(i).getWhatToDo() != 1) {

				Gps gps = getLastLocation(task.get(i).getPerson().getPersonId());

				int dateForSolution = CalculatorTime(task.get(i), gps);// waze
				//Check the time to came to task
				if (dateForSolution < task.get(i).getSolution().getTimeToArriving()) {
					
					DoSolution(task.get(i));
				}
			}
		}
	}

	@Override
	public int CalculatorTime(Task task, Gps gps) {

		String locationTask = task.getAddress();
		Double locationGpsX = gps.getX();
		Double locationGpsY = gps.getY();

		return CalculatorTimeFromJson(locationTask, null, locationGpsX,
				locationGpsY);

	}

	@Override
	public ArrayList<Task> getTaskOnTime(Date date) {

		return db.getAllTasksWithDate(date);
	}

	@Override
	public Gps getLastLocation(String PersonId) {

		return db.getGps(PersonId);
	}

	@Override
	public void DoSolution(Task task) {
		//modelController.sendTaskWithSolution(task);

	}

	@Override
	public int CalculatorTimeFromJson(String task, String gps, Double x,
			Double y) {
		int timeToArriving = 100;
		String start;
		String url = null;
		try {

			start = URLEncoder.encode(gps, "UTF-8");
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
				url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="
						+ start
						+ "&destinations="
						+ end
						+ ",&mode=driving&language=he-HE&key=AIzaSyBW002RI6PeIk47XTwZChp23vtjNiKupFo";

			}

			System.out.println("url to get time in json file :" + url);
			java.io.BufferedInputStream in = new java.io.BufferedInputStream(
					new java.net.URL(url).openStream());
			java.io.FileOutputStream fos = new java.io.FileOutputStream(
					"dataJson/dataJson.json");
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

			obj = parser.parse(new FileReader("file.json"));//get file

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
	public String Algo(String task) {
		// TODO Auto-generated method stub
		return "null";
		
	}
}
