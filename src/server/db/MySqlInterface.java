package server.db;

import java.util.ArrayList;

import entities.Task.PopUp;
import entities.person.Person;
import entities.sms.SMS;
import server.model.ModelDbInterface;

public interface MySqlInterface extends ModelDbInterface {

	public void join();

	public void setSettings(Person person);//set Settings
	
	public ArrayList<SMS> getSmsDefault(String personId,Boolean Default);
	
	public ArrayList<PopUp> getPopUpDefault(String personId,Boolean Default);

}
