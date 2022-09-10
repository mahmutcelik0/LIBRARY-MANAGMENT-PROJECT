package com.example.loginproject;

public class Student {
    private int ID;
    private String nameOfStudent;
    private String surnameOfStudent;
    private String department;
    private String dateOfBirth;
    private String gender;
    private String email;
    private String issuedBook;

    public Student(int ID, String nameOfStudent, String surnameOfStudent, String department, String dateOfBirth, String gender, String email, String issuedBook) {
        this.ID = ID;
        this.nameOfStudent = nameOfStudent;
        this.surnameOfStudent = surnameOfStudent;
        this.department = department;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.issuedBook = issuedBook;
    }

    public Student(){
        this(0,"","","","","","","");
    }

    public Student(int ID, String nameOfStudent, String surnameOfStudent, String department, String dateOfBirth, String gender, String email) {
        this(ID,nameOfStudent,surnameOfStudent,department,dateOfBirth,gender,email,"---");
    }

    public int getID() {
        return ID;
    }

    public String getNameOfStudent() {
        return nameOfStudent;
    }

    public String getSurnameOfStudent() {
        return surnameOfStudent;
    }

    public String getDepartment() {
        return department;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getIssuedBook() {
        return issuedBook;
    }


    @Override
    public String toString(){
        return
                "ID: "+ this.ID
                +"\nNAME: "+ this.nameOfStudent
                +"\nSURNAME: "+ this.surnameOfStudent
                +"\nDEPARTMENT: "+ this.department
                +"\nBIRTH DAY: "+ this.dateOfBirth
                +"\nGENDER: "+ this.gender
                +"\nEMAIL : "+ this.email
                +"\nISSUED BOOK: "+ this.issuedBook;
    }
}
