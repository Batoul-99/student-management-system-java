package Project_OOP1_2026;

import java.util.ArrayList;

public class Course {
	private int courseId;
	private int credits;
	private String courseName;
	private String instructorName;
	private ArrayList<Student> students= new ArrayList<Student>();
	//I did not put students static , since each course has it's own students
	
	public Course(int courseId,int credits,String courseName,String instructorName) {
		this.courseId = courseId;
		this.credits = credits;
		this.courseName = courseName;
		this.instructorName = instructorName;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public void addStudent(Student student) {
		if(student == null) {
			System.out.println("You can not add a null Student!");
			return;
		}
		
		if(!students.contains(student)) {
			students.add(student);
			System.out.println("Student of Id: "+student.getStudentId()+" is added successfully to this course!");
			return;
		}
		System.out.println("This student of Id: "+student.getStudentId()+" is already enrolled in this course.");
	}
	
	public void removeStudent(Student student) {
		if(students.contains(student)) {
			students.remove(student);
			System.out.println("Student of Id: "+student.getStudentId()+" is removed successfully from this course!");
			return;
		}
		System.out.println("This student of Id: "+student.getStudentId()+" isn't found enrolled in this course.");
	}
	
	public void printStudentsEnrolled() {
		if(students.isEmpty()) {
			System.out.println("No students enrolled in this course yet!");
			return;
		}
		
		for(Student s : students) {
			System.out.println(s.toString());
		}
		
	}
	
	@Override	
	public String toString() {
		return "Course Id: "+courseId +" /Course Name: "+ courseName+"  /Number Of Credits: "+credits+" /Instructor's Name: "+instructorName;
	}
	
	public void printCourseSummary() {
		System.out.println(this.toString());
	}
	
	public ArrayList<Student> getStudents() {
	    return students;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null || !(o instanceof Course)) return false;
		
		Course course = (Course) o;
		if(course.getCourseId() == this.getCourseId())
			return true;
		else return false;
	}
	
	
}
