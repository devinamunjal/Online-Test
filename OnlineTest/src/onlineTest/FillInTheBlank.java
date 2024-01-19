package onlineTest;

import java.io.Serializable;
import java.util.Arrays;

public class FillInTheBlank extends Exam implements Serializable {
	
	private int questionNumber;
	private String text;
	private double points;
	private String[] answer;
	private int examId;
	
	//creates an exam object w the given paraameters
	public FillInTheBlank(int examId, int questionNumber, double points, String text, String[] answer) {
		super(examId, text);
		this.text= text;
		this.questionNumber = questionNumber;
		this.examId = examId;
		this.answer = answer;
		this.points = points;
	}
	
	
	//returns text
	public String getText() {
		return text;
	}
	
	//returns points
	public double getPoints() {
		return points;
	}
	
	//returns id
	public int getExamId() {
		return examId;
	}
	
	//returns question number
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
		Arrays.sort(answer);
		str += Arrays.toString(answer);
		return str;
	}


	//sets the points
	public void setPoints(double points) {
		this.points = points;
		
	}

}
	
	
	
	
	
	
