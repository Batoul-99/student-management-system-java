package Project_OOP1_2026;

//Why did we use Comparator not Comparable?
//Because system requires multiple sorting strategies
//Comparable supports only one default sorting inside a class
//AS a conclusion:
//Comparable: one default ordering
//Comparator: multiple custom orderings


import java.util.Comparator;

public class sortByCourseName implements Comparator<Course> {
	public int compare(Course c1,Course c2) {
		return c1.getCourseName().compareTo(c2.getCourseName());
	}
}
