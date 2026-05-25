package Project_OOP1_2026;

import jakarta.persistence.Entity;

@Entity
public class UndergraduateStudent extends Student {

	private int yearLevel;
	
	public UndergraduateStudent() {
		
	}
	
	public UndergraduateStudent(int studentId,String fullName,String email,String major,int yearLevel) {
		super(studentId, fullName, email, major);
		this.yearLevel = yearLevel;
	}

	public int getYearLevel() {
		return yearLevel;
	}


	public void setYearLevel(int yearLevel) {
		this.yearLevel = yearLevel;
	}



	@Override
	public double calculateAverage() {
		if(this.getEnrollments().isEmpty()) {
			return 0.0;
		}
		double totalCredits= 0.0;
		double sumOfGrades= 0.0;
		
		for(Enrollment e: getEnrollments()) {
			sumOfGrades+=e.getGrade()* e.getCourse().getCredits();
			totalCredits += e.getCourse().getCredits();
		}
		return sumOfGrades/totalCredits;
	}

	@Override
	public String toString() {
		return "Status: Undergraduate Student /"+super.toString()+" /Year Level: "+yearLevel;
	}
}