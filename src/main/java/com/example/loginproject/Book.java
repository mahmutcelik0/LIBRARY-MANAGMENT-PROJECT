package com.example.loginproject;

public class Book {
    private int ID;
    private String nameOfBook;
    private String author;
    private String type;
    private int year;
    private String nameOfIssuedFrom;
    private int IDofIssuedFrom;

    public Book(int ID, String nameOfBook, String author, String type, int year, String nameOfIssuedFrom, int IDofIssuedFrom) {
        this.ID = ID;
        this.nameOfBook = nameOfBook;
        this.author = author;
        this.type = type;
        this.year = year;
        this.nameOfIssuedFrom = nameOfIssuedFrom;
        this.IDofIssuedFrom = IDofIssuedFrom;
    }

    public Book(int ID, String nameOfBook, String author, String type, int year) {
        this(ID,nameOfBook,author,type,year,"-",-1);
    }

    public int getID() {
        return ID;
    }

    public String getNameOfBook() {
        return nameOfBook;
    }

    public String getAuthor() {
        return author;
    }

    public String getType() {
        return type;
    }

    public int getYear() {
        return year;
    }

    public String getNameOfIssuedFrom() {
        return nameOfIssuedFrom;
    }

    public int getIDofIssuedFrom() {
        return IDofIssuedFrom;
    }
}
