package server.model;

import entities.Task.Task;
import entities.person.Person;
import server.controller.ControllerInterface;

public interface ModelControllerInterface extends ControllerInterface {
		
	public Person addPersonFromOtherUser(String personId,String other);
}
