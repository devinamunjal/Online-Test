package onlineTest;

import java.util.Map.Entry;
import java.io.Serializable;
import java.util.*;

public class Student implements Serializable{
	
	private String name;
	private ArrayList <Exam> list;
	private double score;
	private Map <Integer, ArrayList <Exam>> allExams;
	
	//creates a student object w the given name and makes an arraylist to store all of the exams
	public Student(String name) {
		this.name = name;
		allExams = new LinkedHashMap<Integer, ArrayList <Exam>>();
	}

	//returns the exam
	public ArrayList<Exam> getExam(int examId) {
		return allExams.get(examId);
	}
	
	//returns the exam arraylist for a particular examId
	public ArrayList<Exam> getValueForKey(int examId) {
		if(allExams.containsKey(examId))
		{
			for(Entry<Integer, ArrayList<Exam>> entry: allExams.entrySet()) 
			{

				if(entry.getKey() == examId) 
				{
					return entry.getValue();
				}    
			 }
		}
		return null;
	}
	
	//checks to see if the exam exsists for a given examid and if not makes a new exam arraylist
	//and it to the all exams map
	public void checkMap(int examId)
	{
		if(!allExams.containsKey(examId))
		{
			allExams.put(examId, new ArrayList <Exam>());
		}
			
	}
	
	//returns the map
	public Map <Integer, ArrayList <Exam>> returnAllExams(){
		return allExams;
	}
	
	//sets the final score
	public void setFinalExamScore(double score) {
		this.score = score;
	}
	
	//returns final score
	public double getFinalExamScore() {
		return this.score;
	}
	

	
}
