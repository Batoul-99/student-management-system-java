package Project_OOP1_2026;

public class Enrollment {
	
	private int enrollmentId;
	private Student student;
	private Course course;
	private double grade;
	private double attendance;

	public Enrollment(int enrollmentId,Student student,Course course,double grade,double attendance) {
	
		this.enrollmentId = enrollmentId;
		this.student = student;
		this.course = course;
		this.attendance = attendance;
		this.grade = grade;
	}

	public Enrollment(int enrollmentId,Student student,Course course) {
		this.enrollmentId = enrollmentId;
		this.student = student;
		this.course = course;
		
		this.grade = 0.0;
		this.attendance =0.0;
	}
	
	public int getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(int enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public double getAttendance() {
		return attendance;
	}

	public void setAttendance(double attendance) {
		this.attendance = attendance;
	}
	
	public void updateGrade(double grade) {
		if(grade >=0 && grade<=100) {
			this.grade = grade;
			System.out.println("Grade is updated successfully to "+ grade);
			return;
		}
		System.out.println("The grade must be between 0 and 100.");
	}
	
	public void updateAttendance(double attendance) {
		if(attendance >=0 && attendance<=100) {
			this.attendance = attendance;
			System.out.println("Attendance is updated successfully to "+ attendance);
			return;
		}
		System.out.println("Attendance must be between 0 and 100.");
	}
	
	public boolean isPassed() {
		if(this.grade >= 60 && attendance >=70) return true;
		else return false;
	}
	
	@Override
	public String toString() {
		return "Enrollment Id: "+enrollmentId+" /Student Id: "+student.getStudentId()+" /Student Full Name: "+student.getFullName()+" /Course Name: "+ course.getCourseName()+" /Grade: "+grade+" /Attendance: "+ attendance+" %";
	}
	
	public void printEnrollmentDetails() {
		System.out.println(this.toString());
	}

	@Override
	public boolean equals(Object o) {
		if(o == null || !(o instanceof Enrollment)) return false;
		
		Enrollment enrollment = (Enrollment) o;
		if(enrollment.getEnrollmentId() == this.getEnrollmentId())
			return true;
		else return false;
	}
}
