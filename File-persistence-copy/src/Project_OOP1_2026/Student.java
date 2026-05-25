package Project_OOP1_2026;

import java.util.ArrayList;


//Why Student is an abstract class??

//Because it is not meant to be general and instantiated directly
//it is a parent class for child classes(subclasses) graduate,undergraduate,exchange
//Abstract class allow subclasses to inherit common attributes and methods
//which will avoid code rewriting (duplication) and improves reusability 
//Also it has an abstract method , which forces each subclass to implement it's own implementation
//Therefore abstract class not only shared variables, but also polymorphism+ enforced behavior

//So here creating a generic student doesn't make sense, because every student must belong to 
//specific type

public abstract class Student {

	private int studentId;
	private String fullName;
	private String email;
	private String major;
	private ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();
	
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

	public ArrayList<Enrollment> getEnrollments() {
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
	
	
	//instanceOf checks whether an object belongs
    //to a class or subclass type at runTime
	//We put it inside equals , inn order 
	//to check if o is a Student before casting 
	
	@Override
	public boolean equals(Object o) {
		//Why parameter is an Object not a student?
		//because I am overriding an existing method
		//public boolean equals(Object o)
		//otherwise it won't be overriding 
		
		if(o == null || !(o instanceof Student)) return false;
		
		Student student = (Student) o;
		if(student.getStudentId() == this.getStudentId())
			return true;
		else return false;
	}
}
