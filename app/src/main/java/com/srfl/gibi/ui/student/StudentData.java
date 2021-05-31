package com.srfl.gibi.ui.student;

public class StudentData {
    private String studentId;
    private String name;
    private String isStudent;
//    private String password;


    public StudentData(String studentId, String name, String isStudent){
        this.studentId = studentId;
        this.name = name;
        this.isStudent = isStudent;
//        this.password = password;
    }
    public StudentData(){

    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsStudent() {
        return isStudent;
    }

    public void setIsStudent(String isStudent) {
        this.isStudent = isStudent;
    }


}
