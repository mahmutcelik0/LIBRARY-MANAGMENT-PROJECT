package com.example.loginproject;


import javafx.scene.control.DatePicker;

import java.util.*;
import java.util.regex.Pattern;

/**
 * BU CLASS TA LOGIN VE SIGN UP KISIMLARINDAN ALINAN INPUTLARIN ICERIKLERI KONTROL EDILIR
 * */
public class InputVerification {




    // SPACE ICERIYORSA FALSE ICERMIYORSA TRUE DONDURUR -> USE ALL OF THEM
    private boolean generalVerification(String field){
        return !field.contains(Character.toString(' '));
    }
    public boolean usernameVerification(String username){
        return generalVerification(username);
    }
    //PASSWORD 8 VEYA DAHA UZUN OLMALI OZEL KARAKTER ICERMEMELI ICERIYORSA FALSE DONDURUR UZUNLUK YETERLI DEGILSE FALSE DONDURUR
    public boolean passwordVerification(String password) {
        return (Pattern.matches("[a-zA-Z0-9]{8,}", password)) &&generalVerification(password);
    }

    // @ ICERMIYORSA FALSE ICERIYORSA TRUE DONDURUR
    public boolean mailVerification(String mailAddress){
        return mailAddress.contains(Character.toString('@')) &&generalVerification(mailAddress);
    }

    // 11 TANE DIGITTEN OLUSMUYORSA FALSE DONDURUR
    public boolean contactNoVerification(String contactNo){
        return Pattern.matches("[0-9]{11}", contactNo) && generalVerification(contactNo);
    }

    //TUM ALANLARIN DOLU OLMASI GEREKIR HERHANGI BIRI BOS ISE TRUE DONDURUR
    public boolean controlOffFillity(String username,String password,String mailAddress,String contactNo){
        return (username.isEmpty() || password.isEmpty() || mailAddress.isEmpty() || contactNo.isEmpty());
    }

    //STUDENT TUM FIELDLAR SPACE ICERMIYORSA O ZAMAN TRUE DONDURUYOR
    public boolean studentControlOfSpace(Student student){
        return generalVerification(student.getNameOfStudent()) &&
                generalVerification(student.getSurnameOfStudent()) &&
                generalVerification(student.getEmail()) ;
    }

    //NAME VEYA SURNAME HARF HARICI BARINDIRIRSA FALSE DONDURUR
    public boolean studentLetterControl(Student student){
        return Pattern.matches("[a-zA-Z]{1,}",student.getNameOfStudent()) && Pattern.matches("[a-zA-Z]{1,}",student.getSurnameOfStudent());
    }

    //mail @ içermiyorsa false içeriyorsa true dondurur
    public boolean studentMailControl(Student student){
        return mailVerification(student.getEmail());
    }

    //BOS OLAN VEYA NULL OLAN VARSA FALSE DONDURUR
    public boolean studentFillityControl(Student student){


        return !student.getNameOfStudent().isEmpty() &&
                !student.getSurnameOfStudent().isEmpty() &&
                !student.getEmail().isEmpty() &&
                !student.getDepartment().isEmpty() &&
                !student.getDateOfBirth().isEmpty() &&
                !(student.getDateOfBirth().equals("null")) &&
                !student.getGender().isEmpty() &&
                !(student.getGender().equals("null")) ;
    }

    public HashMap<Boolean,String> makeControlsForStudents(Student studentToControl){
        HashMap<Boolean,String> toReturn = new HashMap<>();
        if(!studentFillityControl(studentToControl)){
            toReturn.put(false,"FILL ALL FIELDS");
            return toReturn;
        }

        if(!studentControlOfSpace(studentToControl)){
            toReturn.put(false,"FIELDS CAN'T \nCONTAIN SPACE");
            return toReturn;
        }

        if(!studentLetterControl(studentToControl)){
            toReturn.put(false,"NAME or SURNAME \nCAN CONTAIN ONLY LETTER");
            return toReturn;
        }
        if(!studentMailControl(studentToControl)){
            toReturn.put(false,"MAIL MUST HAVE \n\t@ SYMBOL");
            return toReturn;
        }
        toReturn.put(true,"");
        return toReturn;
    }
    //----------------------------------------------------------------------------------------

    //BOS OLAN VEYA NULL OLAN VARSA FALSE DONDURUR
    private boolean bookFillityControl(Book bookToControl){
        return !bookToControl.getNameOfBook().isEmpty()&&
                !bookToControl.getAuthor().isEmpty()&&
                !bookToControl.getType().isEmpty();

    }

    public boolean bookLetterControl(Book bookToControl){
        return Pattern.matches("[a-z A-Z]{1,}",bookToControl.getNameOfBook()) && Pattern.matches("[a-z A-Z]{1,}",bookToControl.getAuthor())
                && Pattern.matches("[a-z A-Z]{1,}",bookToControl.getType()) ;
    }

    public HashMap<Boolean,String> makeControlsForBooks(Book bookToControl){
        HashMap<Boolean,String> toReturn = new HashMap<>();

        if(!bookFillityControl(bookToControl)){
            toReturn.put(false,"FILL ALL FIELDS");
            return toReturn;
        }

        if(!bookLetterControl(bookToControl)){
            toReturn.put(false,"NAME, AUTHOR or TYPE \nCAN CONTAIN ONLY LETTER");
            return toReturn;
        }

        toReturn.put(true,"");
        return toReturn;
    }


    //DATE CONTROL EDILMIYOR TARIH YAPISI YANI (BOS OLMADIGINDA HATA VErıYOR TABI)
}
