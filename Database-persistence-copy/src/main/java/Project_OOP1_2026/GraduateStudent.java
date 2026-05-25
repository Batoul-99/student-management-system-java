package Project_OOP1_2026;

import jakarta.persistence.Entity;

@Entity
public class GraduateStudent extends Student {
	private String thesisTitle;
	private String supervisorName;
	
	public GraduateStudent() {
		
	}
	
	public GraduateStudent(int studentId, String fullName, String email, String major,String thesisTitle,String supervisorName) {
		super(studentId, fullName, email, major);
		this.thesisTitle = thesisTitle;
		this.supervisorName =supervisorName;
	}

	
	public String getThesisTitle() {
		return thesisTitle;
	}

	public void setThesisTitle(String thesisTitle) {
		this.thesisTitle = thesisTitle;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
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
		return "Status: Graduate Student /"+super.toString()+" /Thesis Title: "+thesisTitle+" /Supervisor Name: "+supervisorName;
	}

}
