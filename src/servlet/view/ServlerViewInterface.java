package servlet.view;

import java.util.ArrayList;
import java.util.Date;

import entities.Task.PopUp;
import entities.Task.Task;
import entities.person.Person;
import entities.sms.SMS;
import server.view.View;
import server.view.ViewInterface;

public class ServlerViewInterface implements ViewInterface{

	 
	
	
	View view = new View();
	
	
	@Override
	public void addNewPersonFromView(String personId, String password,
			Date DateTimeRegister, String mail, String phoneNumber) {	
		view.addNewPersonFromView(personId, password, DateTimeRegister, mail,
				phoneNumber);
		
	}

	@Override
	public void addNewTaskFromView(String personId, String taskText,
			Date start, Date end, int platform,String withPerson,Double popUp,Double sms,int action) {
		view.addNewTaskFromView(personId, taskText, start, end, platform,withPerson,popUp,sms,action);
		
	}

	@Override
	public void addPopUpToDefaultFromView(String text, boolean popUpTamplates,String senderId,
			String personId) {
		view.addPopUpToDefaultFromView(text, popUpTamplates, senderId, personId);
		
	}

	@Override
	public void addSmsToDefaultFromView(boolean SmsTamplates, String msg,
			String senderId, String personId) {
		view.addSmsToDefaultFromView(SmsTamplates, msg, senderId, personId);	
	}

	@Override
	public void addNewGpsLocationFromView(Double x, Double y, Date gpsDate,
			String personId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Person getPersonFromView(String personId) {
		
		return view.getPerson(personId);
	}

	@Override
	public Task getTaskFromView(Date date, String personId) {
		
		return view.getTask(date, personId);
	}

	@Override
	public ArrayList<SMS> getSmsFromView(String personId,String sendId,Boolean Default) {
		
		return view.getSmsFromView(personId,sendId, Default);
	}

	@Override
	public ArrayList<PopUp> getPopUpFromView(String personId,String sendId,Boolean Default) {
		return view.getPopUp(personId,sendId, Default);
	}

	@Override
	public ArrayList<Task> getAllTaskFromView(String personId) {
		return view.getAllTaskFromView(personId);
	}

	@Override
	public void sendTaskWithSolution(String personId, String taskText,
			Date start, Date end, int platform, String withPerson,
			Double popUp, Double sms, int action) {
		// TODO Auto-generated method stub
		
	}

	
		
}
