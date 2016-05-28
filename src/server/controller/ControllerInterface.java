package server.controller;

import java.util.ArrayList;
import java.util.Date;

import entities.GPS.Gps;
import entities.Task.PopUp;
import entities.Task.Task;
import entities.person.Person;
import entities.sms.SMS;

// facade
public interface ControllerInterface {

	// /////////////////////////////////get////////////////////////

	public Gps GetGpsLastTime (String personId);//return gps //////////////////my be
	
	public Person getPerson(String personId);//return person with all data

	public Task getTask(Date date, String personId);//return specific task by date

	public  ArrayList<Task> getAllTaskFromView(String personId);

	public ArrayList<SMS> getSms(String personId,String sendId,Boolean Default);//return all the sms from user

	public ArrayList<PopUp> getPopUp(String personId,String sendId,Boolean Default);//return all the popUp from user


	///////////////////////////////////////////////////////////////////////////////////////////
	
	public void addNewPersonFromView(String personId,
			String password, Date DateTimeRegister, String mail,String phoneNumber);

	public void addNewTaskFromView(String personId,String taskText, Date start,
			Date end,String address,int platform,String withPerson,Double popUp,Double sms,int action);

	public void addPopUpToDefaultFromView(String text, boolean popUpTamplates,String senderId,
			String personId);

	public void addSmsToDefaultFromView( boolean SmsTamplates, String msg, String senderId,
			String personId);

	public void addNewGpsLocationFromView(Double x, Double y, Date gpsDate, String personId);
	
	public void sendTaskWithSolution(Task task);
	
	public void addPictures(String pictureName,String person,Date datePic);
	
	public void addPicturesToShare(String pictureName,String person,Date datePic,String personToSend,String txt);

	 public ArrayList<Task> CheckSolutionForPerson(String person);


}
