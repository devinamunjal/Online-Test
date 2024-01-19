package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import onlineTest.SystemManager;
import onlineTest.TrueFalse;

/**
 * 
 * You need student tests if you are looking for help during office hours about
 * bugs in your code.
 * 
 * @author UMCP CS Department
 *
 */
public class StudentTests {

	@Test
	public void TestingAddNAnswerTrueFalseQuestion() {
		SystemManager manager = new SystemManager();
		manager.addExam(1001, "exam1");
		boolean check = false;
		manager.addTrueFalseQuestion(1001, 1, "10 + 5 = 10", 2, false);
		manager.addTrueFalseQuestion(1001, 2, "2 * 8 = 16 ", 4, true);
		manager.addTrueFalseQuestion(1001, 3, "9 / 3 = 2", 3, false);

		String studentName = "munjal,devina";
		manager.addStudent(studentName);
		manager.answerTrueFalseQuestion(studentName, 1001, 1, false);
		manager.answerTrueFalseQuestion(studentName, 1001, 2, true);
		manager.answerTrueFalseQuestion(studentName, 1001, 3, true);
		System.out.println(manager.getExamScore(studentName, 1001));
		
		if(manager.getExamScore(studentName, 1001) == 6.0)
		{
			check = true;
		}
		assertTrue(check);
		
	}
	
	@Test
	public void TestingAddNAnswerMultipleChoice() {
		SystemManager manager = new SystemManager();
		String questionText = "\"10 + 5 = ?\n";
		questionText += "A: 15   B: 1   C: 2   D: 3 ";
		String questionText2 = "\"2 * 8 = ?\n";
		questionText2 += "A: 10   B: 1   C: 16   D: 0 ";
		
		manager.addExam(1002, "exam2");
		manager.addMultipleChoiceQuestion(1002, 1, questionText, 5, new String[]{"A"});
		manager.addMultipleChoiceQuestion(1002, 2, questionText2, 5, new String[]{"C"});
		

		String studentName = "munjal,devina";
		manager.addStudent(studentName);
		manager.answerMultipleChoiceQuestion(studentName, 1002, 1, new String[]{"A"});
		manager.answerMultipleChoiceQuestion(studentName, 1002, 2, new String[]{"D"});
		System.out.println(manager.getExamScore(studentName, 1002));
		boolean check = false;
		if(manager.getExamScore(studentName, 1002) == 5.0)
		{
			check = true;
		}
		assertTrue(check);
		
	}
	
	public void TestingAddNAnswerFillInTheBlank() {
		SystemManager manager = new SystemManager();
		String questionText = "Name two kinds of fruits";
		double points = 4;
		manager.addExam(1003, "exam3");
		questionText = "name two founding fathers";
		double points2 = 6;
		manager.addFillInTheBlanksQuestion(1003, 1, questionText, points, new String[]{"apple","banana"});
	
		manager.addFillInTheBlanksQuestion(1003, 2, questionText, points2, new String[]{"jefferson","washington"});
		String studentName = "munjal,devina";
		manager.addStudent(studentName);
		manager.answerMultipleChoiceQuestion(studentName, 1003, 1, new String[]{"D"});
		manager.answerMultipleChoiceQuestion(studentName, 1003, 2, new String[]{"D"});
		System.out.println(manager.getExamScore(studentName, 1003));
		boolean check = false;
		if(manager.getExamScore(studentName, 1003) == 5.0)
		{
			check = true;
		}
		assertTrue(check);
	}

	@Test
	public void TestingGetCourseLetterGrade() {
		/* First Exam */
		SystemManager manager = new SystemManager();
		manager.addExam(1, "Midterm #1");
		manager.addTrueFalseQuestion(1, 1, "Abstract classes cannot have constructors.", 10, false);
		manager.addTrueFalseQuestion(1, 2, "The equals method returns a boolean.", 20, true);

		String questionText = "Which of the following are valid ids?\n";
		questionText += "A: house   B: 674   C: age   D: &";
		double points = 40;
		manager.addMultipleChoiceQuestion(1, 3, questionText, points, new String[]{"A","C"});
		
		questionText = "Name the first three types of primitive types discussed in class.";
		double points2 = 30;
		manager.addFillInTheBlanksQuestion(1, 4, questionText, points2, new String[]{"int","double","boolean"});	
		
		String studentName = "munjal,devina";
		manager.addStudent(studentName);
		manager.answerTrueFalseQuestion(studentName, 1, 1, true);
		manager.answerTrueFalseQuestion(studentName, 1, 2, false);
		manager.answerMultipleChoiceQuestion(studentName, 1, 3, new String[]{"B", "C"});
		manager.answerFillInTheBlanksQuestion(studentName, 1, 4, new String[]{"double"});
		
		/* Second Exam */
		manager.addExam(2, "Midterm #2");
		manager.addTrueFalseQuestion(2, 1, "A break statement terminates a loop.", 10, true);
		manager.addTrueFalseQuestion(2, 2, "A class can implement multiple interfaces.", 50, true);
		manager.addTrueFalseQuestion(2, 3, "A class can extend multiple classes.", 40, false);
		manager.answerTrueFalseQuestion(studentName, 2, 1, false);
		manager.answerTrueFalseQuestion(studentName, 2, 2, true);
		manager.answerTrueFalseQuestion(studentName, 2, 3, true);

	
		manager.setLetterGradesCutoffs(new String[]{"A","B","C","D","F"}, 
									   new double[] {90,80,70,60,0});
		System.out.println("\nCourse letter grade: " + manager.getCourseLetterGrade(studentName));
		assertEquals(manager.getCourseLetterGrade(studentName), "F");
	}
	
	@Test
	public void testingGetCourseNumericGrade() {
		SystemManager manager = new SystemManager();
		manager.addExam(1, "Midterm #1");
		manager.addTrueFalseQuestion(1, 1, "Abstract classes cannot have constructors.", 10, false);
		manager.addTrueFalseQuestion(1, 2, "The equals method returns a boolean.", 20, true);

		String questionText = "Which of the following are valid ids?\n";
		questionText += "A: house   B: 674   C: age   D: &";
		double points = 40;
		manager.addMultipleChoiceQuestion(1, 3, questionText, points, new String[]{"A","C"});
		
		questionText = "Name the first three types of primitive types discussed in class.";
		double points2 = 30;
		manager.addFillInTheBlanksQuestion(1, 4, questionText, points2, new String[]{"int","double","boolean"});	
		
		String studentName = "munjal,devina";
		manager.addStudent(studentName);
		manager.answerTrueFalseQuestion(studentName, 1, 1, true);
		manager.answerTrueFalseQuestion(studentName, 1, 2, false);
		manager.answerMultipleChoiceQuestion(studentName, 1, 3, new String[]{"B", "C"});
		manager.answerFillInTheBlanksQuestion(studentName, 1, 4, new String[]{"double"});
		
		/* Second Exam */
		manager.addExam(2, "Midterm #2");
		manager.addTrueFalseQuestion(2, 1, "A break statement terminates a loop.", 10, true);
		manager.addTrueFalseQuestion(2, 2, "A class can implement multiple interfaces.", 50, true);
		manager.addTrueFalseQuestion(2, 3, "A class can extend multiple classes.", 40, false);
		manager.answerTrueFalseQuestion(studentName, 2, 1, false);
		manager.answerTrueFalseQuestion(studentName, 2, 2, true);
		manager.answerTrueFalseQuestion(studentName, 2, 3, true);

	
		manager.setLetterGradesCutoffs(new String[]{"A","B","C","D","F"}, 
									   new double[] {90,80,70,60,0});
		System.out.println("\nCourse letter grade: " + manager.getCourseNumericGrade(studentName));
		boolean check = false;
		if((manager.getCourseNumericGrade(studentName)) == 30.0)
		{
			check = true;
		}
		assertTrue(check);
	}
	
	@Test
	public void testingMinMaxNAverageScore() {
		SystemManager manager = new SystemManager();
		
		manager.addExam(1, "Midterm #1");
		manager.addTrueFalseQuestion(1, 1, "Abstract classes cannot have constructors.", 35, false);
		manager.addTrueFalseQuestion(1, 2, "The equals method returns a boolean.", 15, true);
		manager.addTrueFalseQuestion(1, 3, "The hashCode method returns an int", 50, true);
		
		String studentName = "munjal,surya";
		manager.addStudent(studentName);
		manager.answerTrueFalseQuestion(studentName, 1, 1, true);
		manager.answerTrueFalseQuestion(studentName, 1, 2, true);
		manager.answerTrueFalseQuestion(studentName, 1, 3, true);
		
		studentName = "munjal,poorva";
		manager.addStudent(studentName);
		manager.answerTrueFalseQuestion(studentName, 1, 1, false);
		manager.answerTrueFalseQuestion(studentName, 1, 2, true);
		manager.answerTrueFalseQuestion(studentName, 1, 3, true);

		studentName = "munjal,devina";
		manager.addStudent(studentName);
		manager.answerTrueFalseQuestion(studentName, 1, 1, true);
		manager.answerTrueFalseQuestion(studentName, 1, 2, false);
		manager.answerTrueFalseQuestion(studentName, 1, 3, false);
		
		System.out.println("Maximum Score Midterm " + manager.getMaxScore(1) + "\n");
		System.out.println("Minimum Score Midterm " + manager.getMinScore(1) + "\n");
		System.out.println("Average Score Midterm " + manager.getAverageScore(1) + "\n");
		boolean check = false;
		if(manager.getMaxScore(1) == 100.0 && manager.getMinScore(1) == 0.0 && manager.getAverageScore(1) == 55.0)
		{
			check = true;
		}
		assertTrue(check);
	}
}
