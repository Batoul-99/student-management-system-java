package Project_OOP1_2026;

import java.util.Comparator;

public class SortByCourseName implements Comparator<Course> {
	public int compare(Course c1,Course c2) {
		return c1.getCourseName().compareTo(c2.getCourseName());
	}
}
