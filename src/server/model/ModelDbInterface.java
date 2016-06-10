package server.model;

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

public interface ModelDbInterface {

	public void deleteRow(String nameTable, String ID);

	// /////////////////////////set new///////////////////////////////////////
	public void newTable(String name, String id, int number);

	public void deleteTable(String name);

	public void newColumn(String tableName, String name, int number);

	public void addPerson(Person person);//add new person

	public void addNewTask(Task task, Person person);//add new task from user

	public void addPopUpToDefault(PopUp popUp, Person person); //add popUp template from user 

	public void addSmsToDefault(SMS sms, Person person);//add sms template from user

	public void addNewGpsLocation(Gps gps, Person person);//add one location from device
	
	public Double numberOfColumn(String nameTable,String nameColum);//set Settings
	
	public void addNewSolution(Task task);
	
	public void changeStatusSolution(String bool,Double idTask);
	
	public boolean checkStatusFromDB(Double idTask);
	
	public void addNewPictures(Pictures pictures);
	 
	public void addNewPicturesToShare(SharePictures sharePictures);
	
	public void changeStatusShared(Double Share);


	// /////////////////////////////////get////////////////////////

	public ArrayList<Gps> getGpsLocation(String personId);

	public ArrayList<PopUp> getPopUp(String personId,String sendId,Boolean Default);//return all the popUp from user

	public ArrayList<SMS> getSms(String personId,String sendId,Boolean Default);//return all the sms from user
	
	public ArrayList<Task> getTasks(String personId);

	public ArrayList<Task> getAllTasksWithDate(Date date);//return specific tasks by date
	
	public ArrayList<Solution> getSolution(String personId);

	public ArrayList<SMS> getSmsDefault(String personId,Boolean Default);
	
	public ArrayList<PopUp> getPopUpDefault(String personId,Boolean Default);
	
	public Settings getSettings(String personId);
	
	public Solution getSolutionForTask(Task taskId);

	public SMS getSms(Double sms);
	
	public PopUp getPopUp(Double popUpId);
	
	public Gps getGps(String personId);
	
	public Task getTask(Date date, String personId);//return specific task by date
	
	public Person getPerson(String personId);//return person with all data

	public Boolean signIn(String personId,String password);

	public ArrayList<Pictures> getPictures(String personId);
	
	public  ArrayList<SharePictures> getShareUpdate(String person);//download update of pic

	public  ArrayList<SharePictures> getShareSender(String person);// get all shared pic and txt, to sender 

	public Boolean checkIfPersonExists(String personId); 

	// ///////////////////////////GET ALL/////////////////////////////
	public ArrayList<Person> getAllPersons();

	public ArrayList<SMS> getAllSms();

	public ArrayList<PopUp> getAllPopUps();

	public ArrayList<Task> getAllTasks();

	public ArrayList<Gps> getAllGpsLocation();
	// ////////////////////////////////////////////////

}
