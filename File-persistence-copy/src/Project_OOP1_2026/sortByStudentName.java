package Project_OOP1_2026;

import java.util.Comparator;

public class sortByStudentName implements Comparator<Student>{

	public int compare(Student s1,Student s2) {
		return s1.getFullName().compareTo(s2.getFullName());
	}

}
