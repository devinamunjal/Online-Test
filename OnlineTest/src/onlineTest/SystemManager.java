package onlineTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.io.*;

public class SystemManager implements Manager, Serializable{

	private Map <Integer, Exam> map;
	private ArrayList <Exam> questions;
	private ArrayList <Object> answers;
	//private ArrayList <Double> totalPoints;
	private Map <String, Student> students;
	ArrayList <Exam> studentExam;
	String [] letterGrades;
	double [] cutoffs;
	
	public SystemManager()
	{
		map = new HashMap < Integer, Exam>();
		students = new HashMap <String, Student>();
		questions = new ArrayList<>();
		answers = new ArrayList<>();

	}
	

	public boolean addExam(int examId, String title) 
	{
		if(map.containsKey(examId))
		{
			return false;
		}
		Exam newExam = new Exam(examId, title);
		map.put(examId, newExam);
		
		return true;
	}

	@Override
	//adds a true false question based on the given parameters
	public void addTrueFalseQuestion(int examId, int questionNumber, String text, double points, boolean answer) 
	{
		if(map.containsKey(examId))
		{
			questions =map.get(examId).getQuestions();
			questions.add(questionNumber-1, new TrueFalse(examId, questionNumber, points, text, answer));
			answers = map.get(examId).getAnswers();
			answers.add(questionNumber-1, answer);
		
		}
		
	}

	//adds a multiple choice question based on the given parameters
	@Override
	public void addMultipleChoiceQuestion(int examId, int questionNumber, String text, double points, String[] answer) {
		if(map.containsKey(examId))
		{
			questions =map.get(examId).getQuestions();
			System.out.println("questions" + questions.size());
			questions.add(questionNumber-1, new MultipleChoice(examId, questionNumber, points, text, answer));
			answers = map.get(examId).getAnswers();
			System.out.println("before putting in" + Arrays.toString(answer));
			answers.add(questionNumber-1, Arrays.toString(answer));
	
		}
		
	}
	
	//adds a fill in the blank question based on the given parameters
	@Override
	public void addFillInTheBlanksQuestion(int examId, int questionNumber, String text, double points,
			String[] answer) {
		if(map.containsKey(examId))
		{
			questions =map.get(examId).getQuestions();
			questions.add(questionNumber-1, new FillInTheBlank(examId, questionNumber, points, text , answer));
			answers = map.get(examId).getAnswers();
			Arrays.sort(answer);
			answers.add(questionNumber-1, Arrays.toString(answer));
		}
		
	}

	@Override
	public String getKey(int examId) {
		String str = "";
		if(map.containsKey(examId))
		{
			ArrayList <Exam> list = map.get(examId).getQuestions();
			for(int i = 0; i < list.size(); i++)
			{
				str += list.get(i).toString() + "\n";
			}
		}
		return str;
		
	}

	@Override
	public boolean addStudent(String name) {
		if(students.containsKey(name))
		{
			return false;
		}
		students.put(name, new Student(name));
		System.out.println(students);
		
		return true;
		

	}
	
	//adds the students answer to the specific arraylist based on the examid
	@Override
	public void answerTrueFalseQuestion(String studentName, int examId, int questionNumber, boolean answer) 
	{
		students.get(studentName).checkMap(examId);
		ArrayList <Exam> studentExam = students.get(studentName).getExam(examId);
		studentExam.add(questionNumber-1,new TrueFalse(examId, questionNumber, 0, studentName, answer));
		
		
	}

	@Override
	//adds the students answer to the specific arraylist based on the examid
	public void answerMultipleChoiceQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		students.get(studentName).checkMap(examId);
		ArrayList <Exam> studentExam = students.get(studentName).getExam(examId);
		studentExam.add(questionNumber-1,new MultipleChoice(examId, questionNumber, 0, studentName, answer));
		
	}

	@Override
	//adds the students answer to the specific arraylist based on the examid
	public void answerFillInTheBlanksQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		students.get(studentName).checkMap(examId);
		ArrayList <Exam> studentExam = students.get(studentName).getExam(examId);
		Arrays.sort(answer);
		studentExam.add(questionNumber-1,new FillInTheBlank(examId, questionNumber, 0, studentName, answer));
		
	}

	@Override
	public double getExamScore(String studentName, int examId) 
	{
		double points = 0;
		ArrayList <Exam> exam = students.get(studentName).getExam(examId);
		ArrayList<Object> ans = map.get(examId).getAnswers();
		ArrayList <Exam> questions = map.get(examId).getQuestions();
	
		for(int x = 0; x < ans.size(); x++)
		{
			//checks if the question is true and false
			if(exam.get(x) instanceof TrueFalse)
			{
				String studentAns = ((TrueFalse) exam.get(x)).getAnswer();
				Object ansO = ans.get(x);
				String ansString = ansO.toString();
				ansString = ansString.substring(0,1).toUpperCase() + ansString.substring(1);
				if(studentAns.equals(ansString))
				{
					points += ((TrueFalse) questions.get(x)).getPoints();
					((TrueFalse) exam.get(x)).setPoints(((TrueFalse) questions.get(x)).getPoints());
				}
				
			}
			//checks if the question is multiple choice
			if(exam.get(x) instanceof MultipleChoice)
			{
				String[] studentAns = ((MultipleChoice) exam.get(x)).getAnswer();
				Object ansO = ans.get(x);
				String ansString = ansO.toString();
				System.out.println("what is x" + x);
				if(Arrays.toString(studentAns).equals(ansString))
				{
					if(questions.get(x) instanceof MultipleChoice)
					{
						points += ((MultipleChoice) questions.get(x)).getPoints();
						((MultipleChoice) exam.get(x)).setPoints(((MultipleChoice) questions.get(x)).getPoints());
					}
					
				}
	
			}
			//checks if the question is fill in the blank
			if(exam.get(x) instanceof FillInTheBlank)
			{
				String[] studentAns = ((FillInTheBlank) exam.get(x)).getAnswer();
				Object ansO = ans.get(x);
				String ansString = ansO.toString();
				String [] questionAns = ansString.split(",");
				
				if(Arrays.toString(studentAns).equals(ansString))
				{
					points += ((FillInTheBlank) questions.get(x)).getPoints();
					((FillInTheBlank) exam.get(x)).setPoints((((FillInTheBlank) questions.get(x)).getPoints()));
				
				}
				else
				{
					int counter = 0;
					if(studentAns.length != 1 || questionAns.length != 1)					
					{
						for(int i = 0; i < studentAns.length; i++)
						{
							if(ansString.contains(studentAns[i]))
							{
								counter++;
							}
						}
						if(counter == 1 )
						{
							if(questionAns.length == 2)
							{
								points += ((FillInTheBlank) questions.get(x)).getPoints()/ 2;
								((FillInTheBlank) exam.get(x)).setPoints((((FillInTheBlank) questions.get(x)).getPoints())/2);
								System.out.println("points" + points);
								System.out.println("am i crossing fill blnak");
							}
							else
							{
								points += ((FillInTheBlank) questions.get(x)).getPoints()/ 3;
								((FillInTheBlank) exam.get(x)).setPoints((((FillInTheBlank) questions.get(x)).getPoints())/3);
							}
						}
						
						if(counter == 2)
						{
							points += (Math.round(((FillInTheBlank) questions.get(x)).getPoints())/1.5);
							((FillInTheBlank) exam.get(x)).setPoints(Math.round((((FillInTheBlank) questions.get(x)).getPoints())/1.5));
						}
	
					}
//						
				}
			}
					
		}
		return points;
		
//		
	
	}

	//goes through every single question for a given exam of the student and outputs both question and final scores
	public String getGradingReport(String studentName, int examId) {
		
		//declare variables
		String str = "";
		ArrayList<Exam> studentExam = students.get(studentName).getExam(examId);
		ArrayList <Exam> questions = map.get(examId).getQuestions();
		double points = 0;
		double totalPoints = 0;
		points = getExamScore(studentName, examId);

		for(int i = 0; i < studentExam.size(); i++)
		{
			Exam question = studentExam.get(i);
			str += "Question #";
			if(studentExam.get(i) instanceof TrueFalse)
			{
				str += (((TrueFalse) question).getQuestionNumber());
				str += " " + (((TrueFalse) question).getPoints());
				str += " points out of " + ((TrueFalse) questions.get(((TrueFalse) studentExam.get(i)).getQuestionNumber() -1)).getPoints();
				totalPoints += ((TrueFalse) questions.get(((TrueFalse) studentExam.get(i)).getQuestionNumber() -1)).getPoints();
			}
			if(studentExam.get(i) instanceof MultipleChoice)
			{
				str += (((MultipleChoice) question).getQuestionNumber());
				str += " " + (((MultipleChoice) question).getPoints());
				str += " points out of " + ((MultipleChoice) questions.get(((MultipleChoice) studentExam.get(i)).getQuestionNumber() -1)).getPoints();
				totalPoints += ((MultipleChoice) questions.get(((MultipleChoice) studentExam.get(i)).getQuestionNumber() -1)).getPoints();
			}
			if(studentExam.get(i) instanceof FillInTheBlank)
			{
				str += (((FillInTheBlank) question).getQuestionNumber());
				str += " " + (((FillInTheBlank) question).getPoints());
				str += " points out of " + ((FillInTheBlank) questions.get(((FillInTheBlank) studentExam.get(i)).getQuestionNumber() -1)).getPoints();
				totalPoints += ((FillInTheBlank) questions.get(((FillInTheBlank) studentExam.get(i)).getQuestionNumber() -1)).getPoints();
			}
			str += "\n";
					
		}
		str += "Final Score: " + points + " out of " + totalPoints;
		return str;
	}

	@Override
	//sets cutoffs and lettergrades based on the given parameters
	public void setLetterGradesCutoffs(String[] letterGrades, double[] cutoffs) {
		this.letterGrades = letterGrades;
		this.cutoffs = cutoffs;
	
	}
	
	//return  a grade(numeric) for the course - - - goes through all exams of the given student
	@Override
	public double getCourseNumericGrade(String studentName) {
		Map <Integer, ArrayList <Exam>> allExams = new HashMap<>();
		allExams = students.get(studentName).returnAllExams();
		String str = "";
		double score = 0;
		for(int key: allExams.keySet())
		{
			
			str = getGradingReport(studentName, key);
			str = str.substring(str.lastIndexOf(' '));			
			Double x = Double.valueOf(str);
			System.out.println("stu name" + studentName + getExamScore(studentName, key));
			score += getExamScore(studentName, key) / x;
		}
		return (score / allExams.size()) * 100;
		
	}

	@Override
	public String getCourseLetterGrade(String studentName) 
	{
		
		String lGrade = "";
		System.out.println("course numric grade" + getCourseNumericGrade(studentName));
		double numericGrade = getCourseNumericGrade(studentName);
		for(int i = cutoffs.length-1; i > 0; i--)
		{
			if(numericGrade >= cutoffs[i] && numericGrade < cutoffs[i - 1])
			{
				lGrade = letterGrades[i];

				System.out.println("lGrade" + lGrade);
				break;
			}
			if(numericGrade >= cutoffs[0]) 
			{
				lGrade = letterGrades[0];
				break;
			}
		}
	
		return lGrade;

	}

	@Override
	public String getCourseGrades() {
		String str = "";
	    ArrayList<String> keys = new ArrayList<String>(students.keySet());
        for(int i=keys.size()-1; i>=0;i--)
        {
        	str +=  keys.get(i) + " " + getCourseNumericGrade(keys.get(i)) + " " + getCourseLetterGrade(keys.get(i)) + "\n";
        }

		return str;
		
	}

	@Override
	public double getMaxScore(int examId) {
		double maxScore = 0;
		for(String s: students.keySet())
		{
			if(students.get(s).getValueForKey(examId) != null)
			{
				if(getExamScore(s,examId) > maxScore)
				{
					maxScore = getExamScore(s, examId);
				}
				
			}
		}
		return maxScore;
	}

	@Override
	public double getMinScore(int examId) 
	{
		double minScore = 500;
		for(String s: students.keySet())
		{
			if(students.get(s).getValueForKey(examId) != null)
			{
				if(getExamScore(s,examId) < minScore)
				{
					minScore = getExamScore(s, examId);
				}
				
			}
		}
		return minScore;
		// TODO Auto-generated method stub
	}

	@Override
	public double getAverageScore(int examId) {
		double avgScore = 0;
		int counter = 0;
		for(String s: students.keySet())
		{
			if(students.get(s).getValueForKey(examId) != null)
			{
				avgScore += getExamScore(s, examId);
				counter++;
				
			}
		}
		return avgScore / counter;
	}

	@Override
	public void saveManager(Manager manager, String fileName) {
		File file = new File(fileName);
		ObjectOutputStream output;
		try {
			output = new ObjectOutputStream(new FileOutputStream(file));
			output.writeObject(manager);
			output.close();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Manager restoreManager(String fileName) {
		try
		{
			File file = new File(fileName);

			if (!file.exists()) 
			{
				return new SystemManager();
			} else 
			{
				ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
				
				try {
					Manager manager = (Manager) input.readObject();
					return manager;

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			
				}
				input.close();
				return null;
			}
		}
		
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}catch (IOException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

}
	


