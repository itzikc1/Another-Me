package server.view;

import java.util.ArrayList;
import java.util.Date;

import server.controller.Controller;
import server.controller.ControllerInterface;
import servlet.view.ServlerViewInterface;
import entities.GPS.Gps;
import entities.Task.PopUp;
import entities.Task.Task;
import entities.person.Person;
import entities.sms.SMS;

public class View implements ViewInterface, ControllerInterface {

	
		
	Controller controller = new Controller();
   // ServlerViewInterface servlerViewInterfac = new ServlerViewInterface();
    
   
//  public void SetController(Controller controller,ServlerViewInterface servlerViewInterface ){
//	  this.controller= controller;
//	  this.servlerViewInterfac =servlerViewInterface;
//  }
    
    
	@Override
	public void addNewPersonFromView(String personId, String password,
			Date DateTimeRegister, String mail, String phoneNumber) {
		controller.addNewPersonFromView(personId, password, DateTimeRegister,
				mail, phoneNumber);
	}

	@Override
	public void addNewTaskFromView(String personId, String taskText,
			Date start, Date end,String address, int platform, String withPerson,
			Double popUp, Double sms, int action) {
		controller.addNewTaskFromView(personId, taskText, start, end,address, platform,
				withPerson, popUp, sms, action);

	}

	@Override
	public void addPopUpToDefaultFromView(String text, boolean popUpTamplates,
			String senderId, String personId) {
		controller.addPopUpToDefaultFromView(text, popUpTamplates, senderId,
				personId);

	}

	@Override
	public void addSmsToDefaultFromView(boolean SmsTamplates, String msg,
			String senderId, String personId) {
		controller.addSmsToDefaultFromView(SmsTamplates, msg, senderId,
				personId);

	}

	@Override
	public void addNewGpsLocationFromView(Double x, Double y, Date gpsDate,
			String personId) {
		controller.addNewGpsLocationFromView(x, y, gpsDate, personId);

	}

	@Override
	public ArrayList<SMS> getSmsFromView(String personId, String sendId,
			Boolean Default) {

		return getSms(personId, sendId, Default);
	}

	@Override
	public ArrayList<PopUp> getPopUpFromView(String personId, String sendId,
			Boolean Default) {

		return getPopUp(personId, sendId, Default);
	}

	@Override
	public ArrayList<Task> getAllTaskFromView(String personId) {
		return controller.getAllTaskFromView(personId);
	}

	@Override
	public Person getPerson(String personId) {

		return controller.getPerson(personId);
	}

	@Override
	public Task getTask(Date date, String personId) {

		return controller.getTask(date, personId);
	}

	@Override
	public Person getPersonFromView(String personId) {
		return controller.getPerson(personId);
	}

	@Override
	public Task getTaskFromView(Date date, String personId) {

		return controller.getTask(date, personId);
	}

	@Override
	public Gps GetGpsLastTime(String personId) {// my be
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SMS> getSms(String personId, String sendId, Boolean Default) {
		return controller.getSms(personId, sendId, Default);
	}

	@Override
	public ArrayList<PopUp> getPopUp(String personId, String sendId,
			Boolean Default) {
		return controller.getPopUp(personId, sendId, Default);
	}

	@Override
	public void sendTaskWithSolution(Task task) {
		// need to be with parameters of application
		//sendTaskWithSolution(personId, taskText, start, end, platform, withPerson, popUp, sms, action);
	}

	@Override
	public void sendTaskWithSolution(String personId, String taskText,
			Date start, Date end, int platform, String withPerson,
			Double popUp, Double sms, int action) {
//		servlerViewInterfac.sendTaskWithSolution(personId, taskText, start,
//				end, platform, withPerson, popUp, sms, action);

	}

	@Override
	public void addPictures(String pictureName, String person, Date datePic) {
		controller.addPictures(pictureName, person, datePic);
		
	}

	@Override
	public void addPicturesToShare(String pictureName, String person,
			Date datePic, String personToSend, String txt) {
		controller.addPicturesToShare(pictureName, person, datePic, personToSend, txt);
		
	}

	@Override
	public ArrayList<Task> CheckSolutionForPerson(String person) {
	
		return controller.CheckSolutionForPerson(person);
	}

	@Override
	public Boolean signIn(String personId, String password) {
		return controller.signIn(personId, password);
	}

}
