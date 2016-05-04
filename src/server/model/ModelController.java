package server.model;

import java.util.ArrayList;
import java.util.Date;

import server.db.MySql;
import entities.GPS.Gps;
import entities.Solution.Solution;
import entities.Task.PopUp;
import entities.Task.Task;
import entities.person.Person;
import entities.person.Settings;
import entities.sms.SMS;

public class ModelController implements ModelControllerInterface {
	ModelDbInterface modelDb = new ModelDb();
	ModelInterface model = new Model();
	
	String person = "Person";
	String sms = "Sms";
	String gps = "Gps";
	String settings = "Settings";
	String smsTemplates = "SmsTemplates";
	String popUp = "PopUp";
	String task = "Task";
	String solution = "Solution";

	

	 @Override
	 public void addSmsToDefaultFromView(boolean SmsTamplates, String msg,
	 String senderId, String personId) {
	 Double num;
	 num=modelDb.numberOfColumn(sms,"ID");
	
	 SMS sms = new SMS(num, SmsTamplates, msg, getPerson(senderId), null,
	 getPerson(personId));
	 modelDb.addSmsToDefault(sms, sms.getPerson());
	 }
	
	 @Override
	 public void addNewGpsLocationFromView(Double x, Double y, Date gpsDate,
	 String personId) {
	 Double num;
	 num=modelDb.numberOfColumn(gps,"ID");
	
	 Gps gps = new Gps(num, x, y, gpsDate, getPerson(personId));
	 modelDb.addNewGpsLocation(gps, gps.getPerson());
	 }
	
	 @Override
	 public void sendTaskWithSolution(Task task) {
	System.out.println("need to do : "+task.getWhatToDo());
	
	 }
	
	 @Override
	 public Gps GetGpsLastTime(String personId) {
	 
	 return modelDb.getGps(personId);
	 }
	 @Override
	 public ArrayList<Task> getAllTaskFromView(String personId) {
	 return modelDb.getTasks(personId);
	 }
	 @Override
	 public void addNewPersonFromView(String personId, String password,
	 Date DateTimeRegister, String mail, String phoneNumber) {
	
	 Double num;
	 num=modelDb.numberOfColumn(settings,"ID");
	
	 Settings settings = new Settings(num, phoneNumber, password,
	 DateTimeRegister, mail, personId);
	 Person person = new Person(personId, settings);
	 modelDb.addPerson(person);
	
	 }
	
	 @Override
	 public void addNewTaskFromView(String personId, String taskText,
	 Date start, Date end, int platform,String withPerson,Double popUp,Double
	 sms,int action) {
	 Double num;
	 num=modelDb.numberOfColumn(task,"ID");
	 Person p = getPerson(personId);
	 Person with = getPerson(withPerson);
	 String [] algo= model.Algo(taskText);
	 Task task = new Task(num,p, taskText, start, end,
	algo[0], Integer.parseInt(algo[3]), platform);
	 task.setWithPerson(with);
		String [] a= model.Algo(taskText);
	 
	 Double numm;
	 numm=modelDb.numberOfColumn(solution,"ID");
	 Solution solution=new Solution(numm, task.getPerson(), task,
			 modelDb.getSms(sms),modelDb.getPopUp(popUp),Integer.parseInt(algo[3]));
	 solution.setToDo(task.getStart());
	 task.setSolution(solution);
	
	 modelDb.addNewTask(task, task.getPerson());
	 }
	
	 @Override
	 public void addPopUpToDefaultFromView(String text, boolean
	 popUpTamplates,String senderId,
	 String personId) {
	 Double num;
	 num=modelDb.numberOfColumn(popUp,"ID");
	
	 PopUp popUp = new PopUp(num, text, popUpTamplates,getPerson(senderId),
	 getPerson(personId));
	 modelDb.addPopUpToDefault(popUp, popUp.getPersonId());
	
	 }

	@Override
	public Person getPerson(String personId) {
	
		return 	modelDb.getPerson(personId);
	}

	@Override
	public Task getTask(Date date, String personId) {
		
		return modelDb.getTask(date, personId);
	}

	@Override
	public ArrayList<SMS> getSms(String personId, String sendId, Boolean Default) {
		
		return modelDb.getSms(personId, sendId, Default);
	}

	@Override
	public ArrayList<PopUp> getPopUp(String personId, String sendId,
			Boolean Default) {
		return modelDb.getPopUpDefault(personId, Default);
	}


}

