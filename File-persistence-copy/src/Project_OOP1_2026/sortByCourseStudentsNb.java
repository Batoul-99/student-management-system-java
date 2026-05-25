package Project_OOP1_2026;

import java.util.Comparator;

public class sortByCourseStudentsNb implements Comparator<Course>{

	public int compare(Course c1,Course c2) {
		return Integer.compare(c2.getStudents().size(), c1.getStudents().size());
	}
	
}
