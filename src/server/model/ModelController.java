package server.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import entities.GPS.Gps;
import entities.Solution.Solution;
import entities.Task.PopUp;
import entities.Task.Task;
import entities.person.Person;
import entities.person.Settings;
import entities.pictures.Pictures;
import entities.pictures.SharePictures;
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
	String pictures = "Pictures";
	String picturesShare = "PicturesShare";

	

	 @Override
	 public void addSmsToDefaultFromView(boolean SmsTamplates, String msg,
	 String senderId, String personId) {
	 Double num;
	 num=modelDb.numberOfColumn(sms,"ID");
	 Person with=null;
	 SMS sms = null;
	 if(!senderId.equals("default")){
		 with =addPersonFromOtherUser(personId, senderId);
		  sms = new SMS(num, SmsTamplates, msg,with, null,
				 getPerson(personId));
	 }
	 else{
		  sms = new SMS(num, SmsTamplates, msg, getPerson(senderId), null,
				 getPerson(personId));
	 }
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
	 Date start, Date end,String address, int platform,String withPerson,Double popUp,Double
	 sms,int action) {
		 
	 Double num;
	 num=modelDb.numberOfColumn(task,"ID");
	 Person p = getPerson(personId);
	 Person with =addPersonFromOtherUser(personId, withPerson);
	 Task task = model.TaskMaker(num, p, taskText, start, end, address, action, platform);
	 task.setWithPerson(with);
	 Double numm;
	 numm=modelDb.numberOfColumn(solution,"ID");
	 Solution solution=new Solution(numm, task.getPerson(), task,
			 modelDb.getSms(sms),modelDb.getPopUp(popUp),task.getWhatToDo());
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

	@Override
	public void addPictures(String pictureName, String person, Date datePic) {
		Double num;
		 num=modelDb.numberOfColumn(pictures,"ID");
		
		 Pictures pictures = new Pictures(num,pictureName,getPerson(person), datePic);
		 modelDb.addNewPictures(pictures);
		
	}

	@Override
	public void addPicturesToShare(String pictureName, String person,
			Date datePic, String personToSend, String txt) {
		Double num;
		 num=modelDb.numberOfColumn(picturesShare,"ID");
		 Person with =addPersonFromOtherUser(person, personToSend);		 
		 SharePictures pictures = new SharePictures(num,pictureName, getPerson(person), datePic, with, txt, false);
		 modelDb.addNewPicturesToShare(pictures);
		
	}

	@Override
	public ArrayList<Task> CheckSolutionForPerson(String person) {
		return model.CheckSolutionForPerson(person);
	}

	@Override
	public Boolean signIn(String personId, String password) {
		
		return modelDb.signIn(personId, password);
	}

	@Override
	public ArrayList<Pictures> getPictures(String personId) {

		return modelDb.getPictures(personId);
	}

	@Override
	public ArrayList<SharePictures> getShareUpdate(String person) {

		return modelDb.getShareUpdate(person);
	}

	@Override
	public ArrayList<SharePictures> getShareSender(String person) {

		return modelDb.getShareSender(person);
	}

	@Override
	public Boolean checkIfPersonExists(String personId) {


		return modelDb.checkIfPersonExists(personId);
	}

	@Override
	public Person addPersonFromOtherUser(String personId, String other) {
		 Boolean bool = checkIfPersonExists(other);
		
		 Person with =null;
		 if(bool){
			 with = getPerson(other);
			 return with;
		 }
		 Boolean bool1 = checkIfPersonExists(other+"&"+personId);
		  if(bool1){
			 with = getPerson(other+"&"+personId);
			 return with;
		 }
		 else{
			 DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
				Date DateTimeRegister = new Date();
		     addNewPersonFromView(other+"&"+personId,"111111", DateTimeRegister,"mail", "055");
		     with = getPerson(other+"&"+personId);
		     return with;
		     
		 }
		
	}

	


}

