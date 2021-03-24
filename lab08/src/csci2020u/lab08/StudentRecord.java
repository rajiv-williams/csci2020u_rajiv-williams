package csci2020u.lab08;

public class StudentRecord{
    private final String studentID;
    private final double midterm;
    private final double assignments;
    private final double finalExam;
    private final double finalMark;
    private String letterGrade;

    public StudentRecord(String studentID, double assignments,
                         double midterm, double finalExam){

        this.studentID = studentID;
        this.midterm = midterm;
        this.assignments = assignments;
        this.finalExam = finalExam;

        finalMark = 0.20 * this.assignments
                    + 0.30 * this.midterm
                    + 0.5 * this.finalExam;

        if (finalMark >= 80) {
            letterGrade = "A";
        }
        if ((finalMark < 80) && (finalMark >= 70)) {
            letterGrade = "B";
        }
        if ((finalMark < 70) && (finalMark >= 60)) {
            letterGrade = "C";
        }
        if ((finalMark < 60) && (finalMark >= 50)) {
            letterGrade = "D";
        }
        if (finalMark < 50) {
            letterGrade = "F";
        }

    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }
    public String getLetterGrade() {
        return letterGrade;
    }

    public double getFinalExam() {
        return finalExam;
    }

    public double getMidterm() {
        return midterm;
    }

    public String getStudentID() {
        return studentID;
    }

    public double getAssignments() {
        return assignments;
    }

    public double getFinalMark() {
        return finalMark;
    }


}