package Project_OOP1_2026;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;

@Entity //tells Hibernate class becomes DB table 
@Inheritance(strategy = InheritanceType.JOINED)
//this means that child classes will take their own tables

public abstract class Student {

	@Id // to that studentId is a primary key
	private int studentId;
	private String fullName;
	private String email;
	private String major;
	
	@OneToMany(mappedBy = "student") //one student can have many enrollments
	private List<Enrollment> enrollments =new ArrayList<Enrollment>();
	
	public Student() {
		
	}
	
	public Student(int studentId,String fullName,String email,String major) {
		this.studentId = studentId;
		this.fullName = fullName;
		this.email = email;
		this.major = major;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public List<Enrollment> getEnrollments() {
		return enrollments;
	}
	
	public void enrollCourse(Enrollment enrollment) {
		if(enrollments.contains(enrollment)) {
			System.out.println("This  course is already enrolled.");
			return;
		}
		enrollments.add(enrollment);
		System.out.println("This course enrolled successfully!");
	}
	
	public void dropCourse(Enrollment enrollment) {
		if(enrollments.contains(enrollment)) {
			enrollments.remove(enrollment);
			System.out.println("This course dropped successfully!");
			return;
		}
		System.out.println("This course is not enrolled yet.");
	}
	
	public abstract double calculateAverage();
	
	@Override
	public String toString() {
		return "Student Id: "+studentId+" /Full Name: "+ fullName+" /Email: "+email+" /Major: "+major;
	}
	
	public void printStudentInfo() {
		System.out.println(this.toString());
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null || !(o instanceof Student)) return false;
		
		Student student = (Student) o;
		if(student.getStudentId() == this.getStudentId())
			return true;
		else return false;
	}
}