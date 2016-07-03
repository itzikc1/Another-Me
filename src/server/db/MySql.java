package server.db;

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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MySql implements MySqlInterface {

	
	ConnectToMysql connectToMysql = new ConnectToMysql();
	String person = "Person";
	String sms = "Sms";
	String gps = "Gps";
	String settings = "Settings";
	String popUp = "PopUp";
	String task = "Task";
	String solution = "Solution";
	String pictures = "Pictures";
	String picturesShare = "PicturesShare";


	@Override
	public void deleteRow(String nameTable, String ID) {
		String sql = "DELETE FROM " + nameTable + " WHERE ID= '" + ID + "'";
		connectToMysql.setSQL(sql);

	}

	@Override
	public void newTable(String name, String id, int number) {

		String sql = "CREATE TABLE " + name + "(" + id + " VARCHAR(" + number
				+ ") NOT NULL, " + "PRIMARY KEY (" + id + ") " + ")";
		connectToMysql.setSQL(sql);

	}

	@Override
	public void deleteTable(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void newColumn(String tableName, String name, int number) {
		String sql = "ALTER TABLE " + tableName + " ADD " + name + " VARCHAR("
				+ number + ")";
		connectToMysql.setSQL(sql);

	}

	@Override
	public Double numberOfColumn(String nameTable, String nameColum) {
		
		String sql = "SELECT COUNT(*) FROM " + nameTable;
		ResultSet result = null;
		ConnectionParm con;
		con = (connectToMysql.getSql(sql));
		result = con.getRs();
		//result = (connectToMysql.getSql(sql));
		Double num = 0.0;

		try {
			result.next();
			num = result.getDouble(1);
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public void addPerson(Person person) {
		String sql = "INSERT INTO " + this.person + " (ID) VALUES ('"
				+ person.getPersonId() + "')";
		connectToMysql.setSQL(sql);
		setSettings(person);

	}

	@Override
	public void setSettings(Person person) {

		
		java.sql.Timestamp DateTimeRegister = new java.sql.Timestamp(person.getSettings().getDateTimeRegister().getTime());
		
		//System.out.println(DateTimeRegister);
		String sql = "INSERT INTO "
				+ this.settings
				+ " (ID,FullName,PhoneNumber,Age,Password,DateTimeRegister,Mail,PersonId,PopUps,Sms,Solution,Gps) VALUES ('"
				+ person.getSettings().getIdSettings() + "','"
				+ person.getSettings().getFullName() + "','"
				+ person.getSettings().getPhoneNumber() + "','"
				+ person.getSettings().getAge() + "','"
				+ person.getSettings().getPassword() + "','" + DateTimeRegister
				+ "','" + person.getSettings().getMail() + "','"
				+ person.getPersonId() + "','"
				+ person.getSettings().getPopUps().toString() + "','"
				+ person.getSettings().getSms().toString() + "','"
				+ person.getSettings().getSolution().toString() + "','"
				+ person.getSettings().getGps().toString() + "')";
		connectToMysql.setSQL(sql);

	}

	@Override
	public void addNewGpsLocation(Gps gps, Person person) {
		java.sql.Timestamp GpsDate = new java.sql.Timestamp(gps.getGpsDate()
				.getTime());

		String sql = "INSERT INTO " + this.gps
				+ " (ID,Date,X,Y,PersonId) VALUES ('" + gps.getIdGps() + "','"
				+ GpsDate + "','" + gps.getX() + "','" + gps.getY() + "','"
				+ gps.getPerson().getPersonId() + "')";
		//System.out.println(sql);
		connectToMysql.setSQL(sql);

	}

	@Override
	public void addNewTask(Task task, Person person) {

		java.sql.Timestamp start = new java.sql.Timestamp(task.getStart()
				.getTime());
		java.sql.Timestamp end = new java.sql.Timestamp(task.getEnd().getTime());

		String sql = "INSERT INTO "
				+ this.task
				+ " (ID,PersonId,TaskText,Start,End,Address,WhatToDo,Platform,WithPerson,Solution) VALUES ('"
				+ task.getIdTask() + "','" + task.getPerson().getPersonId()
				+ "','" + task.getTaskText() + "','" + start + "','" + end
				+ "','" + task.getAddress() + "','" + task.getWhatToDo()
				+ "','" + task.getPlatform() + "','"
				+ task.getWithPerson().getPersonId() + "','"
				+ task.getSolution().getIdSolution() + "')";
		connectToMysql.setSQL(sql);
		addNewSolution(task);
	}

	@Override
	public void addNewSolution(Task task) {

		java.sql.Timestamp Todo = new java.sql.Timestamp(task.getSolution()
				.getToDo().getTime());
		java.sql.Timestamp end = new java.sql.Timestamp(task.getEnd().getTime());

		String sql = "INSERT INTO " + this.solution
				+ " (ID,TaskId,Sms,ToDo,PopUp,PersonId,Action,TimeToArriving) VALUES ('"
				+ task.getSolution().getIdSolution() + "','" + task.getIdTask()
				+ "','" + task.getSolution().getSms().getIdSMS() + "','" + Todo + "','" + task.getSolution().getPopUp().getIdPopUp() + "','"
				+ task.getPerson().getPersonId() + "','"
				+ task.getSolution().getAction() + "','"+task.getSolution().getTimeToArriving() +  "')";
		connectToMysql.setSQL(sql);

	}

	@Override
	public void addPopUpToDefault(PopUp popUp, Person person) {

		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date DateTimeRegister = new Date();

		java.sql.Timestamp DateTimeShow = new java.sql.Timestamp(
				DateTimeRegister.getTime());

		String sql = "INSERT INTO "
				+ this.popUp
				+ " (ID,Text,PopUpTamplates,DateTimeShow,SenderId,PersonId) VALUES ('"
				+ popUp.getIdPopUp() + "','" + popUp.getText() + "','"
				+ popUp.isPopUpTamplates() + "','" + DateTimeShow + "','"
				+ popUp.getSenderId().getPersonId() + "','"
				+ popUp.getPersonId().getPersonId() + "')";
		connectToMysql.setSQL(sql);

	}

	@Override
	public void addSmsToDefault(SMS sms, Person person) {

		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date DateTimeRegister = new Date();

		java.sql.Timestamp DateTimeSend = new java.sql.Timestamp(
				DateTimeRegister.getTime());

		Boolean b = sms.isSmsTamplates();

		String sql = "INSERT INTO "
				+ this.sms
				+ " (ID,SmsTamplates,Message,SenderId,DateTimeSend,PersonId) VALUES ('"
				+ sms.getIdSMS() + "','" + b.toString() + "','" + sms.getMsg()
				+ "','" + sms.getSenderId().getPersonId() + "','"
				+ DateTimeSend + "','" + sms.getPerson().getPersonId() + "')";
		connectToMysql.setSQL(sql);
	}

	
	@Override
	public void addNewPictures(Pictures pictures) {
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date DateTimeRegister = new Date();

		java.sql.Timestamp DateTime = new java.sql.Timestamp(
				pictures.getDatePic().getTime());

		String sql = "INSERT INTO "
				+ this.pictures
				+ " (ID,PictureName,PersonId,dateTime) VALUES ('"
				+ pictures.getIdpicture() + "','" + pictures.getPictureName() + "','" + pictures.getPerson().getPersonId()
				+ "','" +DateTime + "')";
		connectToMysql.setSQL(sql);
		
	}

	@Override
	public void addNewPicturesToShare(SharePictures sharePictures) {
		java.sql.Timestamp DateTime = new java.sql.Timestamp(
				sharePictures.getDatePic().getTime());
		String sql = "INSERT INTO "
				+ this.picturesShare
				+ " (ID,PictureName,PersonId,dateTime,withPerson,PicText,SendToPerson) VALUES ('"
				+ sharePictures.getIdPictures() + "','" + sharePictures.getPictureName() + "','" + sharePictures.getPerson().getPersonId()
				+ "','" +DateTime +"','"+sharePictures.getWithPerson().getPersonId()+ "','"+ sharePictures.getTxt() +"','"+sharePictures.getSendToPerson() +"')";
		connectToMysql.setSQL(sql);
	}
	
	@Override
	public Task getTask(Date date, String personId) {

		java.sql.Timestamp start = new java.sql.Timestamp(date.getTime());

		String sql = "SELECT* FROM " + this.task + " where PersonId= " + "'"
				+ personId + "'" + " AND Start='" + start + "'";
		
		ResultSet result;
		ConnectionParm con;
		con = (connectToMysql.getSql(sql));
		result = con.getRs();
		Task task = null;
		Person person = getPerson(personId);
		try {
			result.next();
			String taskText = result.getString("TaskText");
			java.util.Date end = new java.sql.Timestamp(result.getTimestamp("End")
					.getTime());
			String address = result.getString("Address");
			int whatToDo = result.getInt("WhatToDo");
			int platform = result.getInt("Platform");
			task = new Task(result.getDouble("ID"), person,
					taskText, start, end, address, whatToDo, platform);
			task.setWithPerson(getPerson(result.getString("WithPerson")));
			con.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println("get task");
		
		return task;
	}

	@Override
	public Solution getSolutionForTask(Task taskId) {
		String sql = "SELECT* FROM " + this.solution + " where TaskId= " + "'"
				+ taskId.getIdTask() + "'";
		//System.out.println(sql);
		ResultSet result;
		ConnectionParm con;
		con = (connectToMysql.getSql(sql));
		result = con.getRs();
		Solution solution = null;
		try {
			result.next();
			solution = new Solution(result.getDouble("ID"), taskId.getPerson(),
					taskId, getSms(result.getDouble("Sms")),
					getPopUp(result.getDouble("PopUp")),
					result.getInt("Action"));
			solution.setTimeToArriving(result.getInt("TimeToArriving"));
			con.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println("get solution");
		
		return solution;
	}

	@Override
	public Person getPerson(String personId) {

		String sql = "SELECT* FROM " + this.person + " where ID= " + "'"
				+ personId + "'";
		ResultSet result;
		ConnectionParm con;
		con = (connectToMysql.getSql(sql));
		result = con.getRs();
		Person person = null;
		try {
			result.next();
			String personid = result.getString("ID");
			person = new Person(personid, getSettings(personId));
			con.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("get person");
	
		return person;
	}

	@Override
	public Gps getGps(String personId) {

	String sql ="SELECT * FROM Gps  INNER JOIN(SELECT PersonId, MAX(Date) AS MaxDateTime FROM Gps where PersonId= '"+personId+"') grouped ON Gps.PersonId = grouped.PersonId AND Gps.Date = grouped.MaxDateTime";	
				
//		String sql = "SELECT* FROM " + this.gps + " where ID= " + "'"
//				+ personId + "'";
	//System.out.println(sql);
	ResultSet result;
	ConnectionParm con;
	con = (connectToMysql.getSql(sql));
	result = con.getRs();
		Gps gps = null;
		try {
			result.next();
			java.util.Date date = new java.sql.Timestamp(result.getTimestamp("Date")
					.getTime());
			gps = new Gps(result.getDouble("ID"), result.getDouble("X"),
					result.getDouble("Y"), date, getPerson(personId));
			con.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("get gps");
		
		return gps;
	}

	@Override
	public Settings getSettings(String personId) {

		String sql = "SELECT* FROM " + this.settings + " where PersonId= "
				+ "'" + personId + "'";
		ResultSet result;
		ConnectionParm con;
		con = (connectToMysql.getSql(sql));
		result = con.getRs();
		Settings settings = null;
		try {
			result.next();
			java.util.Date dateTimeRegister = new java.sql.Timestamp(result
					.getTimestamp("DateTimeRegister").getTime());
			//System.out.println(result.getTimestamp("DateTimeRegister"));
			settings = new Settings(result.getDouble("ID"),
					result.getString("PhoneNumber"),
					result.getString("Password"), dateTimeRegister,
					result.getString("Mail"), personId);
			con.close();
			return settings;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("get settings");
	
		return settings;
	}

	@Override
	public ArrayList<Gps> getGpsLocation(String personId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Task> getTasks(String personId) {

		String sql = "SELECT* FROM " + this.task + " where PersonId= " + "'"
				+ personId + "'";
		ResultSet result;
		ConnectionParm con;
		con = (connectToMysql.getSql(sql));
		result = con.getRs();
		Person person = getPerson(personId);
		ArrayList<Task> task = new ArrayList<Task>();
		try {
			while (result.next()) {
				Task t;
				String taskText = result.getString("TaskText");
				java.util.Date end = new java.sql.Timestamp(result.getTimestamp(
						"End").getTime());
				java.util.Date start = new java.sql.Timestamp(result.getTimestamp(
						"Start").getTime());
				String address = result.getString("Address");
				int whatToDo = result.getInt("WhatToDo");
				int platform = result.getInt("Platform");
				t = new Task(result.getDouble("ID"),person,
						taskText, start, end, address, whatToDo, platform);
				t.setWithPerson(getPerson(result.getString("WithPerson")));
				if (t.getWhatToDo() != 1) {
					t.setSolution(getSolutionForTask(t));
				}
				task.add(t);
			}
			con.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return task;
	}

	@Override
	public ArrayList<Solution> getSolution(String personId) {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public ArrayList<SMS> getSms(String personId, String sendId, Boolean Default) {
		String sql;
		if (sendId.equals("default") && Default == false) {
			sql = "SELECT* FROM " + this.sms + " where PersonId= " + "'"
					+ personId + "'";
		} else {
			sql = "SELECT* FROM " + this.sms + " where PersonId= " + "'"
					+ personId + "' AND SenderId= '" + sendId + "'"
					+ " AND SmsTamplates= '" + Default.toString() + "'";
		}
		//System.out.println(sql);
		ResultSet result;
		ConnectionParm con;
		con = (connectToMysql.getSql(sql));
		result = con.getRs();
		ArrayList<SMS> sms = new ArrayList<SMS>();
		try {

			while (result.next()) {
				java.util.Date DateTimeSend = new java.sql.Timestamp(result
						.getTimestamp("DateTimeSend").getTime());

				Boolean b = Boolean.parseBoolean(result
						.getString("SmsTamplates"));
				SMS s = new SMS(result.getDouble("ID"), b,
						result.getString("Message"),
						getPerson(result.getString("SenderId")), DateTimeSend,
						getPerson(result.getString("PersonId")));
				sms.add(s);
			}
			con.close();
	
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("array of sms");
		
		return sms;
	}

	@Override
	public ArrayList<PopUp> getPopUp(String personId, String sendId,
			Boolean Default) {
		String sql;
		if (sendId.equals("default") && Default == false) {
			sql = "SELECT* FROM " + this.popUp + " where PersonId= " + "'"
					+ personId + "'";
		} else {
			sql = "SELECT* FROM " + this.popUp + " where PersonId= " + "'"
					+ personId + "' AND SenderId= '" + sendId + "'"
					+ " AND PopUpTamplates= '" + Default.toString() + "'";
		}
		ResultSet result;
		ConnectionParm con;
		con = (connectToMysql.getSql(sql));
		result = con.getRs();
		ArrayList<PopUp> popUp = new ArrayList<PopUp>();
		try {

			while (result.next()) {
				java.util.Date DateTimeSend = new java.sql.Timestamp(result
						.getTimestamp("DateTimeShow").getTime());

				Boolean b = Boolean.parseBoolean(result
						.getString("PopUpTamplates"));
				PopUp p = new PopUp(result.getDouble("ID"),
						result.getString("Text"), b,
						getPerson(result.getString("SenderId")),
						getPerson(result.getString("PersonId")));
				p.setDateTimeShow(DateTimeSend);
				popUp.add(p);
			}
			con.close();
		
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //System.out.println("array of popup");
		
		return popUp;
	}

	@Override
	public ArrayList<SMS> getSmsDefault(String personId, Boolean Default) {

		String sql = "SELECT* FROM " + this.sms + " where PersonId= " + "'"
				+ personId + "' AND SenderId= 'default'";
		ResultSet result;
		ConnectionParm con;
		con = (connectToMysql.getSql(sql));
		result = con.getRs();
		ArrayList<SMS> sms = new ArrayList<SMS>();
		try {
			while (result.next()) {
				java.util.Date DateTimeSend = new java.sql.Timestamp(result
						.getTimestamp("DateTimeSend").getTime());

				Boolean b = Boolean.parseBoolean(result
						.getString("SmsTamplates"));
				SMS s = new SMS(result.getDouble("ID"), b,
						result.getString("Message"),
						getPerson(result.getString("SenderId")), DateTimeSend,
						getPerson(result.getString("PersonId")));
				sms.add(s);
			}
			con.close();
		
		
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("array of sms Default");
		return sms;
	}

	@Override
	public ArrayList<Task> getAllTasksWithDate(Date date) {
        //System.out.println("check Task time");
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date ddate = new Date();//from now
		java.sql.Timestamp Date = new java.sql.Timestamp(date.getTime());
		java.sql.Timestamp DDate = new java.sql.Timestamp(ddate.getTime());

		String sql = "SELECT* FROM " + this.task + " where Start BETWEEN '"
				+ DDate.toString() + "' AND '" + Date.toString() + "'";
		ResultSet result;
		ConnectionParm con;
		con = (connectToMysql.getSql(sql));
		result = con.getRs();

		ArrayList<Task> task = new ArrayList<Task>();
		try {
	
			while (result.next()) {
				Task t =null;
				String taskText = result.getString("TaskText");
				java.util.Date end = new java.sql.Timestamp(result.getTimestamp(
						"End").getTime());
				java.util.Date start = new java.sql.Timestamp(result.getTimestamp(
						"Start").getTime());
				String address = result.getString("Address");
				int whatToDo = result.getInt("WhatToDo");
				int platform = result.getInt("Platform");
				 t = new Task(result.getDouble("ID"), getPerson(result.getString("PersonId")),
				 taskText,start, end, address, whatToDo, platform);
				 t.setWithPerson(getPerson(result.getString("WithPerson")));
				 if(t.getWhatToDo()!=1){
				 t.setSolution(getSolutionForTask(t));
				 }
			 task.add(t);
			
			}
			con.close();
		
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println("array of task with date");
		 
		
		return task;
	}

	@Override
	public ArrayList<PopUp> getPopUpDefault(String personId, Boolean Default) {

		String sql = "SELECT* FROM " + this.popUp + " where PersonId= " + "'"
				+ personId + "' AND SenderId= 'default'";
		ResultSet result;
		ConnectionParm con;
		con = (connectToMysql.getSql(sql));
		result = con.getRs();
		ArrayList<PopUp> popUp = new ArrayList<PopUp>();
		try {

			while (result.next()) {
				java.util.Date DateTimeSend = new java.sql.Timestamp(result
						.getTimestamp("DateTimeShow").getTime());

				Boolean b = Boolean.parseBoolean(result
						.getString("PopUpTamplates"));
				PopUp p = new PopUp(result.getDouble("ID"),
						result.getString("Text"), b,
						getPerson(result.getString("SenderId")),
						getPerson(result.getString("PersonId")));
				p.setDateTimeShow(DateTimeSend);
				popUp.add(p);
			}
			con.close();
	
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("array of popup default");
		
		return popUp;
	}

	@Override
	public void join() {
		// TODO Auto-generated method stub

	}

	@Override
	public SMS getSms(Double sms) {
		String sql;
		
			sql = "SELECT* FROM " + this.sms + " where ID= " + "'"
					+ sms + "'";
		
		ResultSet result;
		ConnectionParm con;
		con = (connectToMysql.getSql(sql));
		result = con.getRs();
		SMS smsNew = null;
		try {
			result.next();
				java.util.Date DateTimeSend = new java.sql.Timestamp(result
						.getTimestamp("DateTimeSend").getTime());

				Boolean b = Boolean.parseBoolean(result
						.getString("SmsTamplates"));
				smsNew = new SMS(result.getDouble("ID"), b,
						result.getString("Message"),
						getPerson(result.getString("SenderId")), DateTimeSend,
						getPerson(result.getString("PersonId")));
				con.close();
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("get sms");
		return smsNew;
	}

	@Override
	public PopUp getPopUp(Double popUp) {
		String sql = "SELECT* FROM " + this.popUp + " where ID= " + "'"
				+ popUp + "'";
		ResultSet result;
		ConnectionParm con;
		con = (connectToMysql.getSql(sql));
		result = con.getRs();
		PopUp newPopUp = null;
		try {

		result.next();
				java.util.Date DateTimeSend = new java.sql.Timestamp(result.getTimestamp("DateTimeShow").getTime());
				Boolean b = Boolean.parseBoolean(result
						.getString("PopUpTamplates"));
				newPopUp = new PopUp(result.getDouble("ID"),
						result.getString("Text"), b,
						getPerson(result.getString("SenderId")),
						getPerson(result.getString("PersonId")));
				newPopUp.setDateTimeShow(DateTimeSend);
				con.close();
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("get popup");
		return newPopUp;
	}

	@Override
	public void changeStatusSolution(String bool,Double idTask){
		
		String sql ="UPDATE Task SET StatusSolution='"+bool+"'WHERE ID='"+idTask+"'";
		connectToMysql.setSQL(sql);
		
	}

	@Override
	public boolean checkStatusFromDB(Double idTask) {
		String sql = "SELECT* FROM " + this.task + " where ID= " + "'"
				+ idTask + "'";
		ResultSet result;
		ConnectionParm con;
		con = (connectToMysql.getSql(sql));
		result = con.getRs();
		Boolean bool = null;
		try {
		result.next();
			String boolNull= result.getString("StatusSolution");
			if(boolNull==null){
				bool= false;
			}
			else{
				bool=true;
			}
			con.close();
		
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("get popup");
		//return newPopUp;
		return bool;
	}

	@Override
	public Boolean signIn(String personId, String password) {

		String sql = "SELECT* FROM " + this.settings + " where PersonId= "
				+ "'" + personId + "'";
		ResultSet result;
		ConnectionParm con;
		con = (connectToMysql.getSql(sql));
		result = con.getRs();
		Boolean bool=false;
		try {
			
			if(result.next()){
			
			if(password.equals(result.getString("Password"))){
				bool = true;
			}
		}
			con.close();
			return bool;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("get settings");
	
		return bool;
	}

	@Override
	public ArrayList<Pictures> getPictures(String personId) {
		String sql = "SELECT* FROM " + this.pictures + " where PersonId= " + "'"
				+ personId + "'";
		ResultSet result;
		ConnectionParm con;
		con = (connectToMysql.getSql(sql));
		result = con.getRs();
		Person person = getPerson(personId);
		ArrayList<Pictures> pictures = new ArrayList<Pictures>();
		try {
			while (result.next()) {
				Pictures picture;
				
				java.util.Date dateTime = new java.sql.Timestamp(result.getTimestamp(
						"dateTime").getTime());
				picture = new Pictures(result.getDouble("ID"),result.getString("pictureName"),person,dateTime);	
				pictures.add(picture);
			}
			con.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		return pictures;
	}
	@Override
	public ArrayList<SharePictures> getShareUpdate(String person) {
		String sql = "SELECT* FROM " + this.picturesShare + " where withPerson= " + "'"
				+ person + "' AND sendToPerson='false'";
		ResultSet result;
		ConnectionParm con;
		con = (connectToMysql.getSql(sql));
		result = con.getRs();
		Person personId = getPerson(person);
		ArrayList<SharePictures> sharePictures = new ArrayList<SharePictures>();
		try {
			while (result.next()) {
				SharePictures pictures;
				Boolean bool = Boolean.parseBoolean(result
						.getString("sendToPerson"));
				
				java.util.Date dateTime = new java.sql.Timestamp(result.getTimestamp(
						"dateTime").getTime());
				pictures = new SharePictures(result.getDouble("ID"),result.getString("pictureName"),getPerson(result.getString("PersonId")),dateTime,personId,result.getString("PicText"),bool);
				sharePictures.add(pictures);
			}
			con.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		return sharePictures;
	}


	@Override
	public ArrayList<SharePictures> getShareSender(String person) {
		String sql = "SELECT* FROM " + this.picturesShare + " where PersonId= " + "'"
				+ person + "'";
		ResultSet result;
		ConnectionParm con;
		con = (connectToMysql.getSql(sql));
		result = con.getRs();
		Person personId = getPerson(person);
		ArrayList<SharePictures> sharePictures = new ArrayList<SharePictures>();
		try {
			while (result.next()) {
				SharePictures pictures;
				Boolean bool = Boolean.parseBoolean(result
						.getString("sendToPerson"));
				
				java.util.Date dateTime = new java.sql.Timestamp(result.getTimestamp(
						"dateTime").getTime());
				pictures = new SharePictures(result.getDouble("ID"),result.getString("pictureName"),personId,dateTime,	getPerson(result.getString("withPerson")),result.getString("PicText"),bool);
				sharePictures.add(pictures);
			}
			con.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		return sharePictures;
	}

	@Override
	public void changeStatusShared(Double Share) {
		String sql ="UPDATE " +this.picturesShare+ " SET SendToPerson= 'true' WHERE ID='"+Share+"'";
		//System.out.println(sql);
		connectToMysql.setSQL(sql);
		
	}

	@Override
	public Boolean checkIfPersonExists(String personId) {
		String sql = "SELECT* FROM " + this.settings + " where PersonId= "
				+ "'" + personId + "'";
		ResultSet result;
		ConnectionParm con;
		con = (connectToMysql.getSql(sql));
		result = con.getRs();
		Boolean bool=false;
		try {
			
			if(result.next()){
			
			bool=true;
		}
			con.close();
			return bool;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	 System.out.println("get settings");
	
		return bool;
	}

	
	

}
