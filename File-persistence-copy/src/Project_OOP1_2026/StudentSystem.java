package Project_OOP1_2026;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

//Why are collections better than arrays in this system?

//Because number of students, courses, enrollments is dynamic , unknown
//so collections as arraylist grow automatically
//And provide useful methods like add, remove, isEmpty, size etc..
//which makes system easier to manage 

//We did not use :
//LinkedList since it is slower for indexed access
//HashSet since we need ordering and sorting, and it doesn't 
   //preserve order,or support indexed access
//ArrayDeque, for stack and queue operations , not required for this system


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
	
	public void enrollStudent(int studentId,int courseId) {
		Student student =this.findStudent(studentId);
		Course course = this.findCourse(courseId);
		if((student == null || course == null) ) {
			System.out.println("Cannot be enrolled. Something went wrong!");
			return;
		}
		
		//here I added this to prevent a student to enroll in a course twice
		
		for(Enrollment e : enrollments) {
		    if(e.getStudent().getStudentId() == studentId &&
		       e.getCourse().getCourseId() == courseId) {
		        System.out.println("This student is already enrolled in this course.");
		        return;
		    }
		}
		
		Enrollment enrollment = new Enrollment(enrollmentID, student, course);
		enrollments.add(enrollment);
		student.enrollCourse(enrollment);
		course.addStudent(student);
		
		enrollmentID++;
		System.out.println("Student of Id: "+studentId+" is successfully enrolled in course of Id: "+courseId);
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
				//if true ->print
				//if false-> skip
				System.out.println(student);
		}
	}
	
	public void sortByStudentName() {
	    Collections.sort(students, new sortByStudentName());
	    System.out.println("Students sorted by name successfully!");
	}

	public void sortByStudentAverage(){
	    Collections.sort(students, new sortByStudentAverage());
	    System.out.println("Students sorted by average successfully!");
	}

	public void sortByCourseName() {
	    Collections.sort(courses, new sortByCourseName());
	    System.out.println("Courses sorted by name successfully!");
	}

	public void sortByCourseStudentsNb() {
	    Collections.sort(courses, new sortByCourseStudentsNb());
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
	
	//We used BufferedReader and BufferedWriter
	//because they allow efficient reading/writing of text line by line
	
	//Why did  we use Path ?
	//It provides modern,flexible,and efficient file handling
	
	//AS doctor's  format
	public void saveEnrollmentsToFile(String filePath) throws IOException{
		
	  //here we're creating a Path object representing the file location
		//Where name of a file is a relative path instead of absolute path
		//means look for this file in current prj directory
	  Path path = Paths.get(filePath);
	  BufferedWriter writer = Files.newBufferedWriter(path);
	  
	  for(Enrollment e:  enrollments) {
		  String line = e.getStudent().getStudentId() + "," +e.getStudent().getFullName() + "," +e.getStudent().getEmail() + "," +e.getStudent().getMajor() + "," +e.getCourse().getCourseId() + "," +e.getGrade() + "," +e.getAttendance();

          writer.write(line);
          writer.newLine();  
	  }
	  writer.close();
	  System.out.println("Enrollments saved successfully!");
	}
	
	
	//Mine to know students exact type in order  to fill arrays again with data
	
	public void saveStudentsToFile(String filePath) throws IOException {

	    Path path = Paths.get(filePath);
	    BufferedWriter writer = Files.newBufferedWriter(path);

	    for(Student s : students) {
	        String line = "";

	        //instanceOf checks whether an object belongs
	        //to a class or subclass type at runTime
	        
	        if(s instanceof UndergraduateStudent) {
	            UndergraduateStudent u = (UndergraduateStudent) s;
	            line = "undergraduate," + u.getStudentId() + "," +u.getFullName() + "," +u.getEmail() + "," +u.getMajor() + "," +u.getYearLevel();
	        }

	        else if(s instanceof GraduateStudent) {
	            GraduateStudent g = (GraduateStudent) s;
	            line = "graduate," +g.getStudentId() + "," +g.getFullName() + "," +g.getEmail() + "," +g.getMajor() + "," +g.getThesisTitle() + "," + g.getSupervisorName();
	        }

	        else if(s instanceof ExchangeStudent) {
	            ExchangeStudent ex = (ExchangeStudent) s;
	            line = "exchange," +ex.getStudentId() + "," +ex.getFullName() + "," +ex.getEmail() + "," +ex.getMajor() + "," +ex.getHomeUniversity() + "," +ex.getExchangePeriod();
	        }

	        writer.write(line);
	        writer.newLine();
	    }

	    writer.close();
	    System.out.println("Students saved successfully!");
	}
	
	// same for courses 
	public void saveCoursesToFile(String filePath) throws IOException {

	    Path path = Paths.get(filePath);
	    BufferedWriter writer = Files.newBufferedWriter(path);

	    for(Course c : courses) {
	        String line =c.getCourseId() + "," + c.getCredits() + "," +c.getCourseName() + "," +c.getInstructorName();

	        writer.write(line);
	        writer.newLine();
	    }

	    writer.close();
	    System.out.println("Courses saved successfully!");
	}
	
	//Now LOADING DATA 
	
	//throws IOException ?
	//means this method may throw file-related errors
	
	public void loadStudentsFromFile(String filePath)throws IOException {

	    Path path = Paths.get(filePath);
	    
	    //Here creating a reader to read file line by line
	    BufferedReader reader =Files.newBufferedReader(path);

	    String line;
	    while((line = reader.readLine()) != null) {
	    	
	    	//Read one line from file
	    	//if not null-> continue loop
	    	//if null end of file reached
	    	//empty line is not a null line
	    	
	    	//means if line is empty skip to next line
	    	if(line.trim().isEmpty())
	    		continue;

	        String[] parts = line.split(",");
	        String type =parts[0].trim().toLowerCase();

	        if(type.equals("undergraduate")) {

	            UndergraduateStudent u =new UndergraduateStudent(Integer.parseInt(parts[1].trim()),parts[2].trim(),parts[3].trim(),parts[4].trim(),Integer.parseInt(parts[5].trim()));
	            students.add(u);
	        }

	        else if(type.equals("graduate")) {

	            GraduateStudent g =new GraduateStudent( Integer.parseInt(parts[1].trim()),parts[2].trim(),parts[3].trim(),parts[4].trim(),parts[5].trim(),parts[6].trim());
	            students.add(g);
	        }

	        else if(type.equals("exchange")) {

	            ExchangeStudent ex =new ExchangeStudent(Integer.parseInt(parts[1].trim()),parts[2].trim(), parts[3].trim(),parts[4].trim(),parts[5].trim(),Integer.parseInt(parts[6].trim()));
	            students.add(ex);
	        }
	    }

	    reader.close();//without it file will remain opened, or resource leak
	    System.out.println("Students loaded successfully!");
	    System.out.println();
	}
	
	//Load Courses
	public void loadCoursesFromFile(String filePath)throws IOException {

	    Path path = Paths.get(filePath);
	    BufferedReader reader = Files.newBufferedReader(path);

	    String line;
	    while((line = reader.readLine()) != null) {
	    	
	    	if(line.trim().isEmpty())
	    		continue;

	        String[] parts = line.split(",");

	        int courseId = Integer.parseInt(parts[0].trim());
	        int credits = Integer.parseInt(parts[1].trim());
	        String courseName = parts[2].trim();
	        String instructorName = parts[3].trim();

	        Course c =new Course(courseId, credits,courseName, instructorName);
	        courses.add(c);
	    }

	    reader.close();
	    System.out.println("Courses loaded successfully!");
	    System.out.println();
	}

	//Load Enrollments 
	public void loadEnrollmentsFromFile(String filePath) throws IOException {

	    Path path = Paths.get(filePath);
	    BufferedReader reader =Files.newBufferedReader(path);

	    String line;
	    while((line = reader.readLine()) != null) {
	    	
	    	if(line.trim().isEmpty())
	    		continue;

	        String[] parts = line.split(",");

	        int studentId = Integer.parseInt(parts[0].trim());
	        int courseId = Integer.parseInt(parts[4].trim());
	        double grade = Double.parseDouble(parts[5].trim());
	        double attendance = Double.parseDouble(parts[6].trim());

	        Student student = findStudent(studentId);
	        Course course = findCourse(courseId);

	        if(student != null && course != null) {

	            Enrollment enrollment =new Enrollment(enrollmentID, student, course, grade, attendance);

	            enrollments.add(enrollment);
	            student.enrollCourse(enrollment);
	            course.addStudent(student);

	            enrollmentID++;
	        }
	    }

	    reader.close();
	    System.out.println("Enrollments loaded successfully!");
	    System.out.println();
	}
	
}
