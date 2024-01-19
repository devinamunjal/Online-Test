package onlineTest;

import java.io.Serializable;
import java.util.ArrayList;

public class TrueFalse extends Exam implements Serializable{
	
	//declare class variables
	private int examId;
	private int questionNumber;
	private String text;
	private double points;
	private boolean answer;
	
	
	//creates a true false object w given parameters
	public TrueFalse(int id, int questionNumber, double points, String text, boolean answer) 
	{
		super(id, text);
		this.questionNumber = questionNumber;
		this.text = text;
		this.points = points;
		this.answer = answer;
		
	}
	
	//returns the text
	public String getText() {
		return text;
	}
	
	//returns the answer
	public String getAnswer() 
	{
		String str = "";
		str += answer;
		return str.substring(0,1).toUpperCase() + str.substring(1);
		
	}
	
	//returns points
	public double getPoints() {
		return points;
	}
	
	//returns questionnumber
	public int getQuestionNumber() {
		return questionNumber;
	}
	
	//to string
	public String toString() {
		String str = "Question Text: " + text + "\n";
		str+="Points: " + points + "\n";
		str += "Correct Answer: " + getAnswer();
		return str;
	}
	
	//sets the points
	public void setPoints(double points) {
		this.points = points;
	}
}

