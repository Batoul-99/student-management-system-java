package Project_OOP1_2026;

import jakarta.persistence.Entity;

@Entity
public class ExchangeStudent extends Student {

	private String homeUniversity;
	private int exchangePeriod; 
	
	public ExchangeStudent() {
		
	}
	
	public ExchangeStudent(int studentId, String fullName, String email, String major,String homeUniversity,int exchangePeriod) {
		super(studentId, fullName, email, major);
		this.homeUniversity = homeUniversity;
		this.exchangePeriod = exchangePeriod;
	}

	
	public String getHomeUniversity() {
		return homeUniversity;
	}


	public void setHomeUniversity(String homeUniversity) {
		this.homeUniversity = homeUniversity;
	}


	public int getExchangePeriod() {
		return exchangePeriod;
	}


	public void setExchangePeriod(int exchangePeriod) {
		this.exchangePeriod = exchangePeriod;
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
		return "Status: Exchange Student /"+super.toString()+" /Home University: "+homeUniversity+" /Exchange Period: "+exchangePeriod;
	}


}
