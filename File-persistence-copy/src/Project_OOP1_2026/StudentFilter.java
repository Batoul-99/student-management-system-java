package Project_OOP1_2026;

//Difference between inheritance and interface? 

//inheritance is a relationship between classes 
//where child classes extends parent classes to inherit it's attributes and methods(behaviors)
//So inheritance what the object is 
//Where interface defines set of behaviors that must be implemented by the class
//enforce certain functionality .
//So interface what the object CAN DO
//Thus, StudentFilter is an interface because 
//StudentSystem can do multiple filtering operations using lambda by using same strategy 

public interface StudentFilter {

	boolean apply(Student s);
	
}
//Explanation about Main+ StudentSystem void filterStudents(StudentFilter filter) + StudentFilter

//when sending from main s->s.condition 
//Java automatically converts the lambda into StudentFilter Object
//lambda expression is treated as StudentFilter
//s->s.condition, Java will know s is a student , which is defined
//in Student filter as Student s


//StudentFilter is a functional interface with one abstract method
//that represents filtering condition

//Method in StudentSystem it receives as a parameter StudentFilter Object
//means it can work with  any filtering strategy

//Lambda expressions were used to provide implementations
//of filtering conditions
//without creating separate classes for each filter