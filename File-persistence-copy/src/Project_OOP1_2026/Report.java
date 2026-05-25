package Project_OOP1_2026;

import java.util.ArrayList;
import java.util.Collections;

public class Report {

	private StudentSystem system;
	
	public Report(StudentSystem system) {
		this.system =system;
	}
	
	public int getNumberOfStudents() {
		return system.getStudents().size();
	}
	
	public int getNumberOfCourses() {
		return system.getCourses().size();
	}
	
	public double getAverageGradePerCourse(int courseId) {
		if(system.getEnrollments().isEmpty()) {
			System.out.println("No enrollments yet!");
			return 0.0;
			}
		
		double sumOfGrades =0.0;
		int count=0;
		
		for(int i=0 ; i<system.getEnrollments().size();i++) {
			Enrollment enrollment =system.getEnrollments().get(i);
			
			if(enrollment.getCourse().getCourseId() == courseId) {
				sumOfGrades += enrollment.getGrade();
				count++;
			}
				
		}
		
		if(count ==0) {
			System.out.println("No students enrolled in this course.");
			return 0.0;
		}
		return sumOfGrades/count;
	 }
	
	//Method 1 By using Comparator
	public Student getTopStudent() {
		if(system.getStudents().isEmpty()) {
			System.out.println("No students exist yet!");
			return null;
		}
		Collections.sort(system.getStudents(),new sortByStudentAverage());
		return system.getStudents().get(0);
	}
	
	//Method2 manually
	public Student getTopStudentManually() {
		if(system.getStudents().isEmpty()) {
			System.out.println("No students exist yet!");
			return null;
		}
		
		Student top =system.getStudents().get(0);
		
		for(int i=1;i<system.getStudents().size();i++) {
			if(system.getStudents().get(i).calculateAverage()>top.calculateAverage())
				top =system.getStudents().get(i);
		}
		return top;
	}
	
	public ArrayList<Student> getStudentsByCourse(int courseId) {
		ArrayList<Student> res = new ArrayList<Student>();
		
		if(system.getEnrollments().isEmpty()) {
			System.out.println("No enrollments yet!");
			return res;
		}
		
		for(int i=0;i<system.getEnrollments().size();i++) {
			if(system.getEnrollments().get(i).getCourse().getCourseId() == courseId) {
				res.add(system.getEnrollments().get(i).getStudent());
			}
		}
		return res;
	}
	
}
