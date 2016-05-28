package server.view;

import java.util.ArrayList;
import java.util.Date;

import entities.Task.PopUp;
import entities.Task.Task;
import entities.person.Person;
import entities.sms.SMS;

public interface ViewInterface {
	
	
	
	public void addNewPersonFromView(String personId,
			String password, Date DateTimeRegister, String mail,String phoneNumber);

	public void addNewTaskFromView(String personId,String taskText, Date start,
			Date end,String address,int platform,String withPerson,Double popUp,Double sms,int action);

	public void addPopUpToDefaultFromView(String text, boolean popUpTamplates,String senderId,
			String personId);

	public void addSmsToDefaultFromView( boolean SmsTamplates, String msg, String senderId,
			String personId);

	public void addNewGpsLocationFromView(Double x, Double y, Date gpsDate, String personId);

	// /////////////////////////////////get////////////////////////
	
	public Person getPersonFromView(String personId);
	
	
	public Task getTaskFromView(Date date, String personId);
	
	public  ArrayList<Task> getAllTaskFromView(String personId);

	public ArrayList<SMS> getSmsFromView(String personId,String sendId,Boolean Default);

	public ArrayList<PopUp> getPopUpFromView(String personId,String sendId,Boolean Default);

	public void sendTaskWithSolution(String personId,String taskText, Date start,
			Date end,int platform,String withPerson,Double popUp,Double sms,int action);
	
	public void addPictures(String pictureName,String person,Date datePic);

	public void addPicturesToShare(String pictureName,String person,Date datePic,String personToSend,String txt);

	 public ArrayList<Task> CheckSolutionForPerson(String person);

	public Boolean signIn(String personId,String password);

}
