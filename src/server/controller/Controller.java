package server.controller;

import java.util.ArrayList;
import java.util.Date;

import server.model.ModelController;
import entities.GPS.Gps;
import entities.Task.PopUp;
import entities.Task.Task;
import entities.person.Person;
import entities.pictures.Pictures;
import entities.pictures.SharePictures;
import entities.sms.SMS;

public class Controller implements ControllerInterface {

	ModelController model = new ModelController();

	@Override
	public Gps GetGpsLastTime(String personId) {
		return null;
	}

	@Override
	public Person getPerson(String personId) {

		return model.getPerson(personId);
	}

	@Override
	public Task getTask(Date date, String personId) {

		return model.getTask(date, personId);
	}

	@Override
	public ArrayList<SMS> getSms(String personId, String sendId, Boolean Default) {

		return model.getSms(personId, sendId, Default);
	}

	@Override
	public ArrayList<PopUp> getPopUp(String personId, String sendId,
			Boolean Default) {
		return model.getPopUp(personId, sendId, Default);
	}

	@Override
	public void addNewPersonFromView(String personId, String password,
			Date DateTimeRegister, String mail, String phoneNumber) {
		model.addNewPersonFromView(personId, password, DateTimeRegister, mail,
				phoneNumber);
	}

	@Override
	public void addNewTaskFromView(String personId, String taskText,
			Date start, Date end, String address, int platform,
			String withPerson, Double popUp, Double sms, int action) {
		model.addNewTaskFromView(personId, taskText, start, end, address,
				platform, withPerson, popUp, sms, action);

	}

	@Override
	public void addPopUpToDefaultFromView(String text, boolean popUpTamplates,
			String senderId, String personId) {
		model.addPopUpToDefaultFromView(text, popUpTamplates, senderId,
				personId);

	}

	@Override
	public void addSmsToDefaultFromView(boolean SmsTamplates, String msg,
			String senderId, String personId) {
		model.addSmsToDefaultFromView(SmsTamplates, msg, senderId, personId);

	}

	@Override
	public void addNewGpsLocationFromView(Double x, Double y, Date gpsDate,
			String personId) {
		model.addNewGpsLocationFromView(x, y, gpsDate, personId);

	}

	@Override
	public ArrayList<Task> getAllTaskFromView(String personId) {

		return model.getAllTaskFromView(personId);
	}

	@Override
	public void sendTaskWithSolution(Task task) {

	}

	@Override
	public void addPictures(String pictureName, String person, Date datePic) {
		model.addPictures(pictureName, person, datePic);
	}

	@Override
	public void addPicturesToShare(String pictureName, String person,
			Date datePic, String personToSend, String txt) {
		model.addPicturesToShare(pictureName, person, datePic, personToSend,
				txt);
	}

	@Override
	public ArrayList<Task> CheckSolutionForPerson(String person) {
		return model.CheckSolutionForPerson(person);
	}

	@Override
	public Boolean signIn(String personId, String password) {

		return model.signIn(personId, password);
	}

	@Override
	public ArrayList<Pictures> getPictures(String personId) {

		return model.getPictures(personId);
	}

	@Override
	public ArrayList<SharePictures> getShareUpdate(String person) {

		return model.getShareUpdate(person);
	}

	@Override
	public ArrayList<SharePictures> getShareSender(String person) {

		return model.getShareSender(person);
	}

	@Override
	public Boolean checkIfPersonExists(String personId) {

		return model.checkIfPersonExists(personId);
	}

}
