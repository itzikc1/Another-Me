package server.model;

import java.util.ArrayList;
import java.util.Date;

import entities.GPS.Gps;
import entities.Solution.Solution;
import entities.Task.Task;
import entities.person.Person;

public interface ModelInterface extends Runnable {
   public Task TaskMaker(String personId, String taskText, Date start, Date end, int platform);
   
   public void CheckSolution();
   
   public ArrayList<Task> getTaskOnTime(Date date);

   public int CalculatorTime(Task task, Gps gps);
   
   public Gps getLastLocation(String PersonId);
   
    public void DoSolution(Task task);
   
    public int CalculatorTimeFromJson(String task,String gps,Double x,Double y);
    
    public String Algo(String task);//need to change with Algo
    
    public int TimeToGo(Task task,int timeToArrive);
}
