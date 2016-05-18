package server.controller;

import java.util.ArrayList;
import java.util.Date;

import server.model.ModelController;
import server.model.ModelDb;
import entities.GPS.Gps;
import entities.Task.PopUp;
import entities.Task.Task;
import entities.person.Person;
import entities.sms.SMS;

public class Controller implements ControllerInterface {

	ModelController modelDb = new ModelController();

	
	@Override
	public Gps GetGpsLastTime(String personId) {
		return null;
	}
	
	@Override
	public Person getPerson(String personId) {

		return modelDb.getPerson(personId);
	}

	@Override
	public Task getTask(Date date, String personId) {

		return modelDb.getTask(date, personId);
	}

	@Override
	public ArrayList<SMS> getSms(String personId,String sendId,Boolean Default) {

		return modelDb.getSms(personId,sendId, Default);
	}

	@Override
	public ArrayList<PopUp> getPopUp(String personId,String sendId,Boolean Default) {
		return modelDb.getPopUp(personId,sendId, Default);
	}


	@Override
	public void addNewPersonFromView(String personId, String password,
			Date DateTimeRegister, String mail, String phoneNumber){ 
		modelDb.addNewPersonFromView(personId, password, DateTimeRegister, mail, phoneNumber);
	}
	@Override
	public void addNewTaskFromView(String personId, String taskText,
			Date start, Date end,String address, int platform,String withPerson,Double popUp,Double sms,int action) {
		modelDb.addNewTaskFromView(personId, taskText, start, end, address, platform,withPerson,popUp,sms,action);
		
	}
	@Override
	public void addPopUpToDefaultFromView(String text, boolean popUpTamplates,String senderId,
			String personId) {
		modelDb.addPopUpToDefaultFromView(text, popUpTamplates, senderId, personId);
		
		
	}
	@Override
	public void addSmsToDefaultFromView(boolean SmsTamplates, String msg,
			String senderId, String personId) {
		modelDb.addSmsToDefaultFromView(SmsTamplates, msg, senderId, personId);
		
	}
	@Override
	public void addNewGpsLocationFromView(Double x, Double y, Date gpsDate,
			String personId) {
		modelDb.addNewGpsLocationFromView(x, y, gpsDate, personId);

	}

	@Override
	public ArrayList<Task> getAllTaskFromView(String personId) {
		
		return modelDb.getAllTaskFromView(personId);
	}


	@Override
	public void sendTaskWithSolution(Task task) {
		//view.sendTaskWithSolution(task);
		
	}

}
