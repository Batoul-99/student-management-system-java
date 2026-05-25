package Project_OOP1_2026;


import java.util.ArrayList;
import java.util.Collections;
import org.hibernate.Session;
public class StudentSystem {

	
	//here I did not make them static 
	//Although they are shared in same university, but we may not just have a system for one uni
	//I may make several universities
	//like StudentSystem antonine = new StudentSystem();
	//StudentSystem liu = new StudentSystem();
	
	private ArrayList<Student> students = new ArrayList<Student>();
	private ArrayList<Course> courses = new ArrayList<Course>();
	private ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();
	
	private int enrollmentID= 1;

	public void addStudent(Student student) {
		if(student == null) {
			System.out.println("Can not add a null student.");
			return;
		}
		if(!students.contains(student)) {
			students.add(student);
			System.out.println("Student of Id: "+student.getStudentId()+" is added successfully!");
			return;
		}
		System.out.println("This student of Id: "+student.getStudentId()+" already exists.");
	}
	
	public void addCourse(Course course) {
		if(course == null) {
			System.out.println("Can not add a null course.");
			return;
		}
		if(!courses.contains(course)) {
			courses.add(course);
			System.out.println("Course: "+course.getCourseName()+" is added successfully!");
			return;
		}
		System.out.println("This course: "+course.getCourseName()+" already exists.");
	}
	
	public Student findStudent(int studentId) {
		if(students.isEmpty()) {
			System.out.println("There is no students yet!");
			return null;
		}
		for(int i=0 ; i<students.size();i++) {
			if(students.get(i).getStudentId()==studentId) {
				System.out.println("Searching...Student is found!");
				return students.get(i);
			}
		}
			System.out.println("Searching...Student not found!");
			return null;
	}
	
	public Course findCourse(int courseId) {
		if(courses.isEmpty()) {
			System.out.println("There is no courses yet!");
			return null;
		}
		for(int i=0 ; i<courses.size();i++) {
			if(courses.get(i).getCourseId()==courseId) {
				System.out.println("Searching...Course is found!");
				return courses.get(i);
			}
		}
			System.out.println("Searching...Course not found!");
			return null;
	}
	
	public Enrollment enrollStudent(int studentId,int courseId) {
		Student student =this.findStudent(studentId);
		Course course = this.findCourse(courseId);
		if((student == null || course == null) ) {
			System.out.println("Cannot be enrolled. Something went wrong!");
			return null;
		}
		
		//here I added this to prevent a student to enroll in a course twice
		
		for(Enrollment e : enrollments) {
		    if(e.getStudent().getStudentId() == studentId &&
		       e.getCourse().getCourseId() == courseId) {
		        System.out.println("This student is already enrolled in this course.");
		        return null;
		    }
		}
		
		Enrollment enrollment = new Enrollment( student, course);
		enrollments.add(enrollment);
		student.enrollCourse(enrollment);
		course.addEnrollment(enrollment);
		
		enrollmentID++;
		System.out.println("Student of Id: "+studentId+" is successfully enrolled in course of Id: "+courseId);
		return enrollment;
	}
	
	public void updateGrade(int studentId,int courseId,double grade) {
		if(enrollments.isEmpty()) {
			System.out.println("There is no student enrolled in any course yet!");
			return;
		}
		for(int i=0;i<enrollments.size();i++) {
			if(enrollments.get(i).getStudent().getStudentId() == studentId && enrollments.get(i).getCourse().getCourseId()==courseId) {
				enrollments.get(i).updateGrade(grade);
				System.out.println("The grade of Student Id: "+studentId+" and  Course Id: "+courseId+" is updated to "+grade);
				return;
			}
		}
		System.out.println("Failed to update the grade.Something went wrong!");
	}
	
	
	public void updateAttendance(int studentId,int courseId,double attendance) {
		
	    if(enrollments.isEmpty()) {
	        System.out.println("No enrollments yet!");
	        return;
	    }

	    for(int i=0; i<enrollments.size();i++) {

	        if( enrollments.get(i).getStudent().getStudentId() == studentId && enrollments.get(i).getCourse().getCourseId()== courseId) {
	        	enrollments.get(i).updateAttendance(attendance);

	            System.out.println("Attendance updated successfully!");
	            return;
	        }
	    }

	    System.out.println( "Enrollment not found!");
	}
	
	
	public void printAllStudents() {
		if(students.isEmpty()) {
			System.out.println("No students enrolled yet!");
			return;
		}
		for(Student student : students) {
			System.out.println(student.toString());
		}
	}
	
	public void printAllCourses() {
		if(courses.isEmpty()) {
			System.out.println("No courses exist yet!");
			return;
		}
		for(Course course :courses) {
			System.out.println(course.toString());
		}
	}
	
	public void filterStudents(StudentFilter filter) {
		if(students.isEmpty()) {
			System.out.println("No students present yet!");
			return;
		}
		for(Student student : students) {
			if(filter.apply(student))
				System.out.println(student);
		}
	}
	
	public void sortByStudentName() {
	    Collections.sort(students, new SortByStudentName());
	    System.out.println("Students sorted by name successfully!");
	}

	public void sortByStudentAverage(){
	    Collections.sort(students, new SortByStudentAverage());
	    System.out.println("Students sorted by average successfully!");
	}

	public void sortByCourseName() {
	    Collections.sort(courses, new SortByCourseName());
	    System.out.println("Courses sorted by name successfully!");
	}

	public void sortByCourseStudentsNb() {
	    Collections.sort(courses, new SortByCourseStudentsNb());
	    System.out.println("Courses sorted by number of students successfully!");
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public ArrayList<Enrollment> getEnrollments() {
		return enrollments;
	}

	public int getEnrollmentID() {
		return enrollmentID;
	}

	
	//Load Students 	
	public void loadStudentsFromDatabase(Session session) {
		
		//Query is a request sent to database
		
		//Read
		//here replacing old ArrayList reference with a new ArrayList
		students = new ArrayList<Student>(session.createQuery("from Student", Student.class).list());
		//from Student:retrieve all Student entities from DB<->SELECT * FROM...
		//Student.class:objects returned by this query are Student objects
			//without it they will be returned as Object->need casting
		//.list():method returns list , and then we create ArrayList of it

		
		System.out.println("Students loaded from database successfully!");
	}
	
	//Load Courses
	public void loadCoursesFromDatabase(Session session) {
		courses = new ArrayList<Course>(session.createQuery("from Course", Course.class).list());
		System.out.println("Courses loaded from database successfully!");
	}

	//Load Enrollments 
	public void loadEnrollmentsFromDatabase(Session session) {
		enrollments = new ArrayList<Enrollment>(session.createQuery("from Enrollment", Enrollment.class).list());
		System.out.println("Enrollments loaded from database successfully!");
	}
}
