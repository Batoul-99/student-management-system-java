package Project_OOP1_2026;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Course {
	
	@Id
	private int courseId;
	private int credits;
	private String courseName;
	private String instructorName;
	
	@OneToMany(mappedBy = "course")
	private List<Enrollment> enrollments = new ArrayList<Enrollment>();
	
	public Course() {
		
	}
	
	public Course(int courseId, int credits,String courseName,String instructorName) {
		this.courseId =courseId;
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

public void addEnrollment(Enrollment enrollment) {
	if(enrollment == null) {
		System.out.println("You can not add a null Enrollment!");
		return;
	}
	
	if(!enrollments.contains(enrollment)) {
		enrollments.add(enrollment);
		System.out.println("Enrollment added successfully to this course!");
		return;
	}
	
	System.out.println("This enrollment already exists in this course.");
}

public void removeEnrollment(Enrollment enrollment) {
	if(enrollments.contains(enrollment)) {
		enrollments.remove(enrollment);
		System.out.println("Enrollment removed successfully from this course!");
		return;
	}
	
	System.out.println("This enrollment is not found in this course.");
}

public void printStudentsEnrolled() {
	if(enrollments.isEmpty()) {
		System.out.println("No students enrolled in this course yet!");
		return;
	}
	
	for(Enrollment e : enrollments) {
		System.out.println(e.getStudent().toString());
	}
}

@Override	
public String toString() {
	return "Course Id: "+courseId +" /Course Name: "+ courseName+"  /Number Of Credits: "+credits+" /Instructor's Name: "+instructorName;
}

public void printCourseSummary() {
	System.out.println(this.toString());
}

public List<Enrollment> getEnrollments() {
	return enrollments;
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