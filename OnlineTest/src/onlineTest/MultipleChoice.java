package onlineTest;

import java.io.Serializable;
import java.util.Arrays;

public class MultipleChoice extends Exam implements Serializable{
	
	private int examId;
	private int questionNumber;
	private String text;
	private double points;
	private String[] answer;
	
	//creates a multiple choice object with the given parameters
	public MultipleChoice(int examId, int questionNumber, double points, String text, String[] answer) 
	{
		super(examId, text);
		this.text= text;
		this.questionNumber = questionNumber;
		this.answer = answer;
		this.points = points;
	}
	
	//returns the text
	public String getText() {
		return text;
	}
	
	//returns the points
	public double getPoints() {
		return points;
	}

	//returns the id
	public int getExamId() {
		return examId;
	}
	
	//returns the question number
	public int getQuestionNumber() {
		return questionNumber;
	}
	
	//returns the answer
	public String[] getAnswer() {
		return answer;
	}
	
	//to string
	public String toString() {
		String str = "Question Text: " + text + "\n";
		str+="Points: " + points + "\n";
		str += "Correct Answer: ";
		str += Arrays.toString(answer);
		return str;
	}

	//sets the points
	public void setPoints(double points) {
		this.points = points;
		
	}

}
	
	
	
	
	
	
