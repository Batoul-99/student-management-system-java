package Project_OOP1_2026;

import java.util.ArrayList;
import java.util.Scanner;

//Here we're writing HQL:Hibernate Query Language
//using entity names and Java fields, not table columns directly

//ORM: Object Relational Mapping
//it maps Java objects into database tables automatically

//Why use Hibernate? it converts Java objects into SQL database tables
//it simplifies database interaction
//allows us to work with Java objects instead of 
//writing SQL statements for every operation

//Session is the connection between Java application & DB
//workspace to talk to DB
//it performs CRUD: Create, Read, Update, Delete.
//Why session opened many times?
	//since it represents a temporary interaction with DB
	//so it is short-lived
import org.hibernate.Session;

//SessionFactory is heavy object, created once, shared by application
//it creates session
//Why created once?
	//because it is heavyweight and expensive to create
	//it must be reused during application lifetime
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

	public static void main(String[] args)  {
		
		
		StudentSystem system =new StudentSystem();
		
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.openSession();
		
		system.loadStudentsFromDatabase(session);
		system.loadCoursesFromDatabase(session);
		system.loadEnrollmentsFromDatabase(session);
		
		Report  report = new Report(system);
		
		Scanner input = new Scanner(System.in);
		Scanner scan = new Scanner(System.in);
		Scanner scanner = new Scanner(System.in);
		
		int choice;
		
		while(true) {
			System.out.println("----Student Management System----");
			System.out.println();
			System.out.println();
			System.out.println("1. Add Student");
			System.out.println("2. Add Course");
			System.out.println("3. Enroll Student");
			System.out.println("4. Update Grade");
			System.out.println("5. Update Attendance");
			System.out.println("6. Show All Students");
			System.out.println("7. Show All Courses");
			System.out.println("8. Report Options");
			System.out.println("9. Filtering");
			System.out.println("10. Exit");
			System.out.println();
			System.out.println("Please enter your choice: ");
			choice = input.nextInt();
			
			switch(choice) {
			
			case 1:
				System.out.println();
				System.out.println("INPUT FORM: Type(Undergraduate/ Graduate/ Exchange), Student_ID, Full_Name, Email, Major");
				String line1 = scan.nextLine();
				String[] parts1 = line1.split(",");
				
				//Cut line whenever there's a comma 
				//.trim() remove leading(at beginning) spaces and tailing spaces
				
				if(parts1.length<5) {
					System.out.println("Invalid input. Try again!");
					continue;
				}
				
				
				String type = parts1[0].trim().toLowerCase();
				
			
				if(type.equals("undergraduate")) {
					System.out.println("Enter Year Level: ");
					int year = input.nextInt();
					input.nextLine();
					
					UndergraduateStudent student = new UndergraduateStudent(Integer.parseInt(parts1[1].trim()), parts1[2].trim(), parts1[3].trim(), parts1[4].trim(), year);
					system.addStudent(student);
					
					//transactions are important to ensure data 
					//consistency and reliability
					//either all changes are saved or non
					
					//starts transaction
					session.beginTransaction();
					
					//Create<-> session.persist()<-> INSERT INTO ....
					session.persist(student);
					session.getTransaction().commit();
					//saves changes permanently, without commit
					//changes are not saved
				}
				
				else if(type.equals("graduate")) {
					System.out.println("Enter Thesis Title: ");
					String thesis = scanner.nextLine().trim();
					System.out.println("Enter Supervisor Name: ");
					String supName = scanner.nextLine().trim();
					GraduateStudent student = new GraduateStudent(Integer.parseInt(parts1[1].trim()), parts1[2].trim(), parts1[3].trim(), parts1[4].trim(), thesis, supName);
					system.addStudent(student);
					
					session.beginTransaction();
					
					//Create<-> session.persist()<-> INSERT INTO ....					
					session.persist(student);
					session.getTransaction().commit();
				}
				
				else if(type.equals("exchange")) {
					System.out.println("Enter Home University: ");
					String homeUni = scanner.nextLine().trim();
					System.out.println("Enter Exchange Period: ");
					int period = input.nextInt();
					input.nextLine();
					
					ExchangeStudent student = new ExchangeStudent(Integer.parseInt(parts1[1].trim()), parts1[2].trim(), parts1[3].trim(), parts1[4].trim(), homeUni, period);
					system.addStudent(student);
					
					session.beginTransaction();
					session.persist(student);
					session.getTransaction().commit();
				}
				else {
					System.out.println("Invalid type of student. Try Again!");
					continue;
				}
				
				break;
				
			case 2:
				System.out.println();
				System.out.println("Enter : Course_ID, Number_Of_Credits, Course_Name, Instructor_Name");
				String line2 = scan.nextLine();
				String[] parts2 = line2.split(",");
				
				if(parts2.length<4) {
					System.out.println("Invalid input. Try again!");
					continue;
				}
				
				Course course =  new Course(Integer.parseInt(parts2[0].trim()), Integer.parseInt(parts2[1].trim()), parts2[2].trim(), parts2[3].trim());
				system.addCourse(course);
				
				session.beginTransaction();
				session.persist(course);
				session.getTransaction().commit();
				break;
				
			case 3:
				System.out.println();
				System.out.println("Enter : Student_ID, Course_ID");
				
				String line3 = scan.nextLine().toLowerCase();
				String[] parts3 = line3.split(",");
				
				if(parts3.length<2) {
					System.out.println("Invalid input. Try Again!");
					continue;				
				}
				
				Enrollment enrollment =system.enrollStudent(Integer.parseInt(parts3[0].trim()), Integer.parseInt(parts3[1].trim()));
				if(enrollment != null) {
					session.beginTransaction();
					session.persist(enrollment);
					session.getTransaction().commit();
				}
				
				break;
				
			case 4:
				System.out.println();
				System.out.println("Enter : Student_ID, Course_ID, Grade");
				
				String line4 = scan.nextLine().toLowerCase();
				String[] parts4 = line4.split(",");
				
				if(parts4.length<3) {
					System.out.println("Invalid input. Try Again!");
					continue;				
				}
				
				system.updateGrade(Integer.parseInt(parts4[0].trim()), Integer.parseInt(parts4[1].trim()), Double.parseDouble(parts4[2].trim()));
				
				Enrollment enrollmentGrade =session.createQuery("from Enrollment e where e.student.studentId = :studentId and e.course.courseId = :courseId", Enrollment.class).setParameter("studentId", Integer.parseInt(parts4[0].trim())).setParameter("courseId", Integer.parseInt(parts4[1].trim())).uniqueResult();

				if(enrollmentGrade != null) {
					session.beginTransaction();
					enrollmentGrade.updateGrade(Double.parseDouble(parts4[2].trim()));
					
					//Update<->session.merge()<->UPDATE ...
					session.merge(enrollmentGrade);
					session.getTransaction().commit();
				}
				break;
				
				
			case 5:
				System.out.println();
				System.out.println("Enter : Student_ID, Course_ID, Attendance");
				
				String line5 = scan.nextLine().toLowerCase();
				String[] parts5 = line5.split(",");
				
				if(parts5.length<3) {
					System.out.println("Invalid input. Try Again!");
					continue;				
				}
				
				system.updateAttendance(Integer.parseInt(parts5[0].trim()), Integer.parseInt(parts5[1].trim()), Double.parseDouble(parts5[2].trim()));
				
				Enrollment enrollmentAttendance =session.createQuery("from Enrollment e where e.student.studentId = :studentId and e.course.courseId = :courseId", Enrollment.class).setParameter("studentId", Integer.parseInt(parts5[0].trim())).setParameter("courseId", Integer.parseInt(parts5[1].trim())).uniqueResult();

				if(enrollmentAttendance != null) {
					session.beginTransaction();
					enrollmentAttendance.updateAttendance(Double.parseDouble(parts5[2].trim()));
					session.merge(enrollmentAttendance);
					session.getTransaction().commit();
				}
				
				break;
				
				
			case 6:
				while(true) {
				System.out.println();
				System.out.println();
				System.out.println("----Show Students Options----");
			    System.out.println("1. Sort Students By Name");
				System.out.println("2. Sort Students By Average");
			    System.out.println("3. Go Back..");
			    int option = input.nextInt();
				input.nextLine();
				
				switch(option) {
				
				 case 1:
		            	System.out.println();
		                system.sortByStudentName();
		                system.printAllStudents();
		                break;

		         case 2:
		            	System.out.println();
		                system.sortByStudentAverage();
		                system.printAllStudents();
		                break;
		            	
		         case 3:
		        	 System.out.println();
		        	 System.out.println("Going Back..");
		        	 break;
		        	 
		        default:
					System.out.println();
					System.out.println("Invalid option. Try Again!");
					System.out.println();
					continue;
				}
				
				if(option ==3)
					break;
				
				}
				break;
				
			case 7 :
				while(true) {
					System.out.println();
					System.out.println();
					System.out.println("----Show Courses Options----");
					System.out.println("1. Sort Courses By Name");
			        System.out.println("2. Sort Courses By Number Of Students");
				    System.out.println("3. Go Back..");				    
				    int option = input.nextInt();
					input.nextLine();
					
					switch(option) {
					
					  case 1:
			            	System.out.println();
			                system.sortByCourseName();
			                system.printAllCourses();
			                break;

			         case 2:
			            	System.out.println();
			                system.sortByCourseStudentsNb();
			                system.printAllCourses();
			                break;
			            	
			         case 3:
			        	 System.out.println();
			        	 System.out.println("Going Back..");
			        	 break;
			        	 
			        default:
						System.out.println();
						System.out.println("Invalid option. Try Again!");
						System.out.println();
						continue;
					}
					
					if(option ==3)
						break;
					
					}
					break;
					
				
			case 8:
				
				while(true) {
				System.out.println();
				System.out.println();
				System.out.println("----Report Options----");
				System.out.println("1. Number Of Students");
				System.out.println("2. Number Of Courses");
				System.out.println("3. Average Grade Per Course");
				System.out.println("4. Top Student");
				System.out.println("5. Get Students By Course");
				System.out.println("6. Go Back..");
				System.out.println();
				System.out.println("Please enter your option: ");
				
				int option = input.nextInt();
				input.nextLine();
				
				switch(option) {
				case 1:
					System.out.println();
					System.out.println("Total Number of Students is "+report.getNumberOfStudents());
					System.out.println();
					break;
					
				case 2:
					System.out.println();
					System.out.println("Total Number of Courses is "+report.getNumberOfCourses());
					System.out.println();
					break;
					
				case 3:
					System.out.println();
					System.out.println("Enter Course ID :");
					Integer courseId = input.nextInt();
					input.nextLine();
					System.out.println("Average grade per course of Id: "+courseId+" is "+report.getAverageGradePerCourse(courseId));
					System.out.println();
					break;
					
				case 4:
					System.out.println();
					Student top = report.getTopStudent();

					if(top != null)
					    System.out.println(top.toString());
					else
					    System.out.println("No students exist yet.");
					System.out.println();
					break;
					
				case 5:
					System.out.println();
					System.out.println("Enter Course ID :");
					Integer courseID = input.nextInt();
					input.nextLine();
					ArrayList<Student> studentsOfCourse = report.getStudentsByCourse(courseID);
					for(Student student : studentsOfCourse) {
						System.out.println(student.toString());
					}
					System.out.println();
					break;
					
				case 6:
					System.out.println();
					System.out.println("Going Back...");
					System.out.println();
					break;
					
				default:
						System.out.println();
						System.out.println("Invalid option. Try Again!");
						System.out.println();
						continue;
				}
				
				if(option==6)
					break;
			}
			break;
			
			case 9 :

			    while(true) {
			    	System.out.println();
			        System.out.println();
			        System.out.println("---- FILTERING ----");
			        System.out.println("1. Filter By Major");
			        System.out.println("2. Filter By Average");
			        System.out.println("3. Filter Passed Students");
			        System.out.println("4. Go Back..");
			        System.out.println();

			        int option = input.nextInt();
			        input.nextLine();

			        switch(option) {
			        
			        case 1:
			        	System.out.println();
			        	System.out.println("Enter Major: ");
			        	String major = scanner.nextLine().trim();
			        	
			        	system.filterStudents(s -> s.getMajor().equalsIgnoreCase(major));
			        	break;
			        	
			        case 2:
			        	System.out.println();
			        	System.out.println("Enter Minimum Average:");
			        	    double avg = input.nextDouble();
			        	    input.nextLine();

			        	    system.filterStudents( s -> s.calculateAverage() >= avg );
			        	    break;
			        	    
			        case 3:
			        	System.out.println();
			            system.filterStudents(s -> s.calculateAverage() >= 60);
			            break;
			            
			        case 4:
			        	System.out.println();
			        	System.out.println("Going back...");
			                break;
			        }
			        if (option == 4)
			        	break;        	
			}
			break;
			
			case 10:
			    System.out.println();
			    System.out.println("Exiting Student Management System...");
			    System.out.println("Data saved successfully!");
			    System.out.println();
			    
			    session.close();
			    factory.close();
			    
			    return;
			    
			default:
				 System.out.println();
				 System.out.println("Invalid choice. Try again!");
				 System.out.println();
				 continue;
				 
		}
			
			
			
		
		
	 }

   }
}
