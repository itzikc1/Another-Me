package server.model;

import server.controller.ControllerInterface;
import entities.person.Person;

public interface ModelControllerInterface extends ControllerInterface {
		
	public Person addPersonFromOtherUser(String personId,String other);
}
