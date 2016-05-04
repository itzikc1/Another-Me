package server.model;

import java.util.ArrayList;
import java.util.Date;

import server.controller.ControllerInterface;
import server.db.MySql;
import entities.GPS.Gps;
import entities.Solution.Solution;
import entities.Task.PopUp;
import entities.Task.Task;
import entities.person.Person;
import entities.person.Settings;
import entities.sms.SMS;

public class ModelDb implements ModelDbInterface {

	 MySql sql = new MySql();


	String person = "Person";
	String sms = "Sms";
	String gps = "Gps";
	String settings = "Settings";
	String smsTemplates = "SmsTemplates";
	String popUp = "PopUp";
	String task = "Task";
	String solution = "Solution";

	@Override
	public void deleteTable(String name) {
		sql.deleteTable(name);
	}

	@Override
	public void newTable(String name, String id, int number) {
		sql.newTable(name, id, number);
	}

	@Override
	public Double numberOfColumn(String nameTable, String nameColum) {
		Double num = sql.numberOfColumn(nameTable, nameColum);
		num++;
		return num;
	}

	@Override
	public void addPerson(Person person) {
		sql.addPerson(person);

	}

	@Override
	public void newColumn(String tableName, String name, int number) {
		sql.newColumn(tableName, name, number);
		System.out.println("work!!  " + name);
	}

	@Override
	public void deleteRow(String nameTable, String ID) {
		sql.deleteRow(nameTable, ID);

	}

	@Override
	public void addNewTask(Task task, Person person) {

		sql.addNewTask(task, person);

	}

	@Override
	public void addPopUpToDefault(PopUp popUp, Person person) {
		sql.addPopUpToDefault(popUp, person);

	}

	@Override
	public void addSmsToDefault(SMS sms, Person person) {
		sql.addSmsToDefault(sms, person);

	}

	@Override
	public void addNewGpsLocation(Gps gps, Person person) {
		sql.addNewGpsLocation(gps, person);

	}

	@Override
	public Settings getSettings(String personId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	// model interface
	public Gps getGps(String personId) {

		return sql.getGps(personId);
	}

	@Override
	public SMS getSms(Double sms) {
		// TODO Auto-generated method stub
		return sql.getSms(sms);
	}

	@Override
	public PopUp getPopUp(Double popUp) {
		// TODO Auto-generated method stub
		return sql.getPopUp(popUp);
	}

	@Override
	public Solution getSolutionForTask(Task taskId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Gps> getGpsLocation(String personId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Task> getTasks(String personId) {

		return sql.getTasks(personId);
	}

	@Override
	public ArrayList<Solution> getSolution(String personId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Task> getAllTasksWithDate(Date date) {

		return sql.getAllTasksWithDate(date);
	}

	@Override
	public ArrayList<Person> getAllPersons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SMS> getAllSms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PopUp> getAllPopUps() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Task> getAllTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Gps> getAllGpsLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	// TODO need to start parameters like adders and what to do with algo

	@Override
	public Person getPerson(String personId) {
		return sql.getPerson(personId);
	}

	@Override
	public Task getTask(Date date, String personId) {
		Task task = sql.getTask(date, personId);

		if (task.getWhatToDo() != 1) {
			task.setSolution(getSolutionForTask(task));
		}

		return task;
	}

	@Override
	public ArrayList<SMS> getSms(String personId, String sendId, Boolean Default) {
		if (Default == true && sendId.equals("default")) {
			return sql.getSmsDefault(personId, Default);
		} else {
			return sql.getSms(personId, sendId, Default);
		}
	}

	@Override
	public ArrayList<PopUp> getPopUp(String personId, String sendId,
			Boolean Default) {
		if (Default) {
			return sql.getPopUpDefault(personId, Default);
		} else {
			return sql.getPopUp(personId, sendId, Default);
		}
	}

	@Override
	public void addNewSolution(Task task) {

		Double num;
		num = numberOfColumn(this.solution, "ID");
		Solution solution = new Solution(num, task.getPerson(), task, null,
				null, 3);

	}

	@Override
	public ArrayList<SMS> getSmsDefault(String personId, Boolean Default) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PopUp> getPopUpDefault(String personId, Boolean Default) {
		// TODO Auto-generated method stub
		return null;
	}

//	 @Override
//	 public void addSmsToDefaultFromView(boolean SmsTamplates, String msg,
//	 String senderId, String personId) {
//	 Double num;
//	 num=numberOfColumn(sms,"ID");
//	
//	 SMS sms = new SMS(num, SmsTamplates, msg, getPerson(senderId), null,
//	 getPerson(personId));
//	 addSmsToDefault(sms, sms.getPerson());
//	 }
//	
//	 @Override
//	 public void addNewGpsLocationFromView(Double x, Double y, Date gpsDate,
//	 String personId) {
//	 Double num;
//	 num=numberOfColumn(gps,"ID");
//	
//	 Gps gps = new Gps(num, x, y, gpsDate, getPerson(personId));
//	 addNewGpsLocation(gps, gps.getPerson());
//	 }
//	
//	 @Override
//	 public void sendTaskWithSolution(Task task) {
//	
//	
//	 }
//	
//	 @Override
//	 public Gps GetGpsLastTime(String personId) {
//	 ////////////// my be
//	 return null;
//	 }
//	 @Override
//	 public ArrayList<Task> getAllTaskFromView(String personId) {
//	 return getTasks(personId);
//	 }
//	 @Override
//	 public void addNewPersonFromView(String personId, String password,
//	 Date DateTimeRegister, String mail, String phoneNumber) {
//	
//	 Double num;
//	 num=numberOfColumn(settings,"ID");
//	
//	 Settings settings = new Settings(num, phoneNumber, password,
//	 DateTimeRegister, mail, personId);
//	 Person person = new Person(personId, settings);
//	 addPerson(person);
//	
//	 }
//	
//	 @Override
//	 public void addNewTaskFromView(String personId, String taskText,
//	 Date start, Date end, int platform,String withPerson,Double popUp,Double
//	 sms,int action) {
//	 Double num;
//	 num=numberOfColumn(task,"ID");
//	 Person p = getPerson(personId);
//	 Person with = getPerson(withPerson);
//	
//	 Task task = new Task(num,p, taskText, start, end,
//	"algo", action, platform);
//	 //need to start the solution with algo
//	 //Default solution:
//	 task.setWithPerson(with);
//	
//	 Double numm;
//	 numm=numberOfColumn(solution,"ID");
//	 Solution solution=new Solution(numm, task.getPerson(), task,
//	 getSms(sms),getPopUp(popUp),action);
//	 solution.setToDo(task.getStart());
//	 task.setSolution(solution);
//	
//	 addNewTask(task, task.getPerson());
//	 }
//	
//	 @Override
//	 public void addPopUpToDefaultFromView(String text, boolean
//	 popUpTamplates,String senderId,
//	 String personId) {
//	 Double num;
//	 num=numberOfColumn(popUp,"ID");
//	
//	 PopUp popUp = new PopUp(num, text, popUpTamplates,getPerson(senderId),
//	 getPerson(personId));
//	 addPopUpToDefault(popUp, popUp.getPersonId());
//	
//	 }

}
