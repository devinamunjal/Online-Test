package onlineTest;

import java.io.Serializable;
import java.util.ArrayList;

public class Exam implements Serializable{
	
	private ArrayList<Exam> questions;
	private ArrayList <Object> answers;
	private int id;
	private String title;
	
	//creates an exam object w questions and answers arraylists
	public Exam(int id, String title) {
		this.id = id;
		this.title = title;
		questions = new ArrayList<Exam>();
		answers = new ArrayList <Object>();
	}
	

	//returns questions arraylist
	public ArrayList<Exam> getQuestions(){
		return questions;
	}
	
	//returns answers arraylist
	public ArrayList <Object> getAnswers(){
		return answers;
	}

	



}
