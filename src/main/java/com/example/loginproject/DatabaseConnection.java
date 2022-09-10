package com.example.loginproject;

import com.example.loginproject.leftSideControllers.MODEL.LISTSforStudentandBook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseConnection {
    private final String URL = "jdbc:mysql://localhost:3306/librarymanagment";
    private final String USERNAME = "MAHMUT";
    private final String PASSWORD = "M.celik2001";
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    //THIS METHOD WILL CALL ONLY BEGINNING FROM HELLOAPPLICATION
    public void makeConnectionBeginning(){
        //APPENDING OF SQL DRIVER
        try {
            Class.forName("java.sql.Driver");
        }catch (ClassNotFoundException e){
            System.out.println("CLASS FOUND EXCEPTION IN DRIVER PART");
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }catch (SQLException e){
            System.out.println("CONNECTION PROBLEM FROM DRIVER MANAGER");
            e.printStackTrace();
        }
    }



    //KULLANICI VARSA TRUE YOKSA FALSE DONDURUR
    public boolean checkUser(String username,String password,String email,String contactNo){
        String queryToSend = "SELECT * FROM users WHERE USERNAME = ? AND PASSWORDofUSER = ? AND EMAIL = ? AND CONTACTNO = ?";
        try {
            preparedStatement = connection.prepareStatement(queryToSend);

            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,email);
            preparedStatement.setString(4,contactNo);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        }catch (SQLException e){
            System.out.println("CHECK USER PREPARE STATEMENT PROBLEM");
            e.printStackTrace();
        }
        return false;
    }

    //KULLANICI MEVCUT OLMA DURUMU KONTROL EDILIYOR. KULLANICI VARSA FALSE DONDURULUR
    public boolean signUpMethod(String username,String password,String email,String contactNo){
        if(checkUser(username,password,email,contactNo)) return false;

        String QueryToAdd = "INSERT INTO users (USERNAME,PASSWORDofUSER,EMAIL,CONTACTNO) VALUES (?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(QueryToAdd);

            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,email);
            preparedStatement.setString(4,contactNo);

            preparedStatement.executeUpdate();

            return true;
        }catch (SQLException e){
            System.out.println("SIGN UP METHOD CONNECTION PROBLEM");
            e.printStackTrace();
        }
        return false;
    }

    //AYNI STUDENTTAN VARSA TRUE YOKSA FALSE DONDURUR
    public boolean checkStudent(Student studentToCheck){
        String queryToSend = "SELECT * FROM students WHERE NAMEofSTUDENT = ? AND SURNAMEofSTUDENT = ? AND DEPARTMENT = ? AND DATEofBIRTH = ?  AND GENDER = ? AND EMAIL = ? ";

        try {
            preparedStatement = connection.prepareStatement(queryToSend);

            preparedStatement.setString(1,studentToCheck.getNameOfStudent());
            preparedStatement.setString(2, studentToCheck.getSurnameOfStudent());
            preparedStatement.setString(3, studentToCheck.getDepartment());
            preparedStatement.setString(4,studentToCheck.getDateOfBirth());
            preparedStatement.setString(5, studentToCheck.getGender());
            preparedStatement.setString(6,studentToCheck.getEmail());

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        }catch (SQLException e){
            System.out.println("CHECK PROBLEM EXCEPTION");
            e.printStackTrace();
        }
        return false;
    }

    //EKLENMISSE TRUE EKLENEMEMISSE FALSE DONDURUR
    public boolean addStudentToDatabase(Student studentToAdd){
        if(checkStudent(studentToAdd)) return false;

        String queryToSend = "INSERT INTO students (ID ,NAMEofSTUDENT,SURNAMEofSTUDENT,DEPARTMENT,DATEofBIRTH,GENDER,EMAIL,ISSUEDBOOK) VALUES (?,?,?,?,?,?,?,?)";

        try {
            preparedStatement = connection.prepareStatement(queryToSend);

            int newID = new LISTSforStudentandBook().getSizeOfStudents()+1; //IT HAS TO MINUS 1

            preparedStatement.setInt(1,newID);
            preparedStatement.setString(2,studentToAdd.getNameOfStudent());
            preparedStatement.setString(3,studentToAdd.getSurnameOfStudent());
            preparedStatement.setString(4,studentToAdd.getDepartment());
            preparedStatement.setString(5,studentToAdd.getDateOfBirth());
            preparedStatement.setString(6, studentToAdd.getGender());
            preparedStatement.setString(7,studentToAdd.getEmail());
            preparedStatement.setString(8,"-");

            preparedStatement.executeUpdate();

            return true;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    //SILINMISSE TRUE SILINEMEMISSE FALSE DONDURUR (0 a esitse return false degilse return true)
    public boolean deleteStudentFromDatabase(Student studentToDelete){
        String queryToDelete = "DELETE FROM students WHERE NAMEofSTUDENT = ? AND SURNAMEofSTUDENT= ? AND DEPARTMENT = ? AND DATEofBIRTH= ? AND GENDER = ? AND EMAIL = ? ";

        try{
            preparedStatement = connection.prepareStatement(queryToDelete);

            preparedStatement.setString(1,studentToDelete.getNameOfStudent());
            preparedStatement.setString(2,studentToDelete.getSurnameOfStudent());
            preparedStatement.setString(3,studentToDelete.getDepartment());
            preparedStatement.setString(4,studentToDelete.getDateOfBirth());
            preparedStatement.setString(5,studentToDelete.getGender());
            preparedStatement.setString(6,studentToDelete.getEmail());

            return preparedStatement.executeUpdate() != 0 ;
        }catch (SQLException e){
            System.out.println("DELETE STUDENT FROM DATABASE EXCEPTION");
            e.printStackTrace();
            return false;
        }
    }

    //OGRENCILERI DATABASE DEN OBS LIST E ATAMAYI SAGLAYAN METOD
    public ObservableList<Student> getStudentFromDatabase(){
        makeConnectionBeginning();
        String query = "SELECT * FROM students";

        ObservableList<Student> obsListToReturn = FXCollections.observableArrayList();

        try {
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()){
                obsListToReturn.add(new Student(resultSet.getInt("ID"),resultSet.getString("NAMEofSTUDENT"),resultSet.getString("SURNAMEofSTUDENT"),resultSet.getString("DEPARTMENT"),resultSet.getString("DATEofBIRTH"),resultSet.getString("GENDER"),resultSet.getString("EMAIL"),resultSet.getString("ISSUEDBOOK")));
            }

        }catch (SQLException e){
            System.out.println("GET STUDENT FROM DATABASE EXCEPTION");
            e.printStackTrace();
        }
        return obsListToReturn;
    }

    public boolean addBookToDatabase(Book bookToAdd){
        if(checkBook(bookToAdd)) return false;

        String queryToSend = "INSERT INTO books (ID ,NAMEofBOOK,AUTHOR ,TYPE,YEAR,NAMEofISSUEDFROM,IDofISSUEDFROM) VALUES (?,?,?,?,?,?,?)";

        try {
            preparedStatement = connection.prepareStatement(queryToSend);

            int newID = new LISTSforStudentandBook().getSizeOfBooks()+1; //IT HAS TO MINUS 1

            preparedStatement.setInt(1,newID);
            preparedStatement.setString(2,bookToAdd.getNameOfBook());
            preparedStatement.setString(3,bookToAdd.getAuthor());
            preparedStatement.setString(4,bookToAdd.getType());
            preparedStatement.setInt(5,bookToAdd.getYear());
            preparedStatement.setString(6, "-");
            preparedStatement.setInt(7,-1);


            preparedStatement.executeUpdate();

            return true;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    private boolean checkBook(Book bookToCheck){
        String queryToSend = "SELECT * FROM books WHERE NAMEofBOOK = ? AND AUTHOR = ? AND TYPE = ? AND YEAR = ?";

        try {
            preparedStatement = connection.prepareStatement(queryToSend);

            preparedStatement.setString(1,bookToCheck.getNameOfBook());
            preparedStatement.setString(2, bookToCheck.getAuthor());
            preparedStatement.setString(3, bookToCheck.getType());
            preparedStatement.setInt(4,bookToCheck.getYear());

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        }catch (SQLException e){
            System.out.println("CHECK PROBLEM EXCEPTION");
            e.printStackTrace();
        }
        return false;
    }

    //KITAPLARI DATABASE DEN OBS LIST E ATAMAYI SAGLAYAN METOD
    public ObservableList<Book> getBookFromDatabase(){
        makeConnectionBeginning();
        String query = "SELECT * FROM books";

        ObservableList<Book> obsListToReturn = FXCollections.observableArrayList();

        try {
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()){
                obsListToReturn.add(new Book(resultSet.getInt("ID"),resultSet.getString("NAMEofBOOK"),resultSet.getString("AUTHOR"),resultSet.getString("TYPE"),resultSet.getInt("YEAR"),resultSet.getString("NAMEofISSUEDFROM"),resultSet.getInt("IDofISSUEDFROM")));
            }

        }catch (SQLException e){
            System.out.println("GET BOOK FROM DATABASE EXCEPTION");
            e.printStackTrace();
        }
        return obsListToReturn;
    }

    public boolean deleteBookFromDatabase(Book bookToDelete){
        String queryToDelete = "DELETE FROM books WHERE NAMEofBOOK = ? AND AUTHOR = ? AND TYPE = ? AND YEAR = ?";

        try {
            preparedStatement = connection.prepareStatement(queryToDelete);

            preparedStatement.setString(1,bookToDelete.getNameOfBook());
            preparedStatement.setString(2,bookToDelete.getAuthor());
            preparedStatement.setString(3,bookToDelete.getType());
            preparedStatement.setInt(4,bookToDelete.getYear());

            return preparedStatement.executeUpdate() != 0;

        }catch (SQLException e){
            System.out.println("DELETE BOOK EXCEPTION");
            e.printStackTrace();
            return false;
        }
    }

    public void updateStudentFromDatabase(String issueBook,int ID){
        String queryToUpdate = "UPDATE students SET ISSUEDBOOK = ? WHERE ID = ?";

        try {
            preparedStatement = connection.prepareStatement(queryToUpdate);

            preparedStatement.setString(1,issueBook);
            preparedStatement.setInt(2,ID);

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println("UPDATE STUDENT EXCEPTION");
        }
    }

    public void updateBookFromDatabase(String nameOfBorrower , int ID,int borrowerID){
        String queryToUpdate = "UPDATE books SET NAMEofISSUEDFROM = ? , IDofISSUEDFROM = ? WHERE ID = ?";


        try {
            preparedStatement = connection.prepareStatement(queryToUpdate);

            preparedStatement.setString(1,nameOfBorrower);
            preparedStatement.setInt(2,borrowerID);
            preparedStatement.setInt(3,ID);

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println("UPDATE BOOK EXCEPTION");
        }
    }







    //END OF PROGRAM CLOSE NECESSECARY FIELDS
}
