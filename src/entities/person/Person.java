package entities.person;

import java.io.Serializable;
import java.util.List;

import entities.GPS.Gps;
import entities.Solution.Solution;
import entities.Task.PopUp;
import entities.Task.Task;
import entities.sms.SMS;

public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String personId;// like mail
	Settings settings;
	private List<Task> Task;
	private List<Gps> gps;
	private List<SMS> sms;
	private List<PopUp> popUp;
	private List<Solution> solution;

	public Person(String personId, Settings settings) {
		this.personId = personId;
		this.settings = settings;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public void addGps(Gps gps) {
		this.gps.add(gps);
	}

	public void addtask(Task tasks) {
		this.Task.add(tasks);
	}

	public void addSms(SMS sms) {
		this.sms.add(sms);
	}

	public void addSolution(Solution solution) {
		this.solution.add(solution);
	}

	public void addpopUp(PopUp popUp) {
		this.popUp.add(popUp);
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public List<Task> getTask() {
		return Task;
	}

	public void setTask(List<Task> task) {
		Task = task;
	}

	public List<Gps> getGps() {
		return gps;
	}

	public void setGps(List<Gps> gps) {
		this.gps = gps;
	}

	public List<SMS> getSms() {
		return sms;
	}

	public void setSms(List<SMS> sms) {
		this.sms = sms;
	}

	public List<PopUp> getPopUp() {
		return popUp;
	}

	public void setPopUp(List<PopUp> popUp) {
		this.popUp = popUp;
	}

	public List<Solution> getSolution() {
		return solution;
	}

	public void setSolution(List<Solution> solution) {
		this.solution = solution;
	}

}
