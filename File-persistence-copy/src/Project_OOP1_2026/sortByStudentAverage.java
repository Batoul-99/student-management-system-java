package Project_OOP1_2026;

import java.util.Comparator;

public class sortByStudentAverage implements Comparator<Student> {
	public int compare(Student s1,Student s2) {
		return Double.compare(s2.calculateAverage(), s1.calculateAverage());
	}
}

//Double.compare is used for primitive doubles 
//so it compares two double numbers
//I put s2 before s1 because I wanna highest first descending order
//compareTo is used for Strings,double objects..
