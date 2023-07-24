package org.example.user;

import org.example.TypesOfBooks;
import org.example.books.AudiobookFormat;
import org.example.books.EbookFormat;

import java.io.*;

public class Admin  extends Users{
    public Admin(int userID, String username,String surname, int houseNumber, String postcode, String city, Role role) {
        super(userID, username,surname, houseNumber, postcode, city, role);
    }

    public void addPaperback(int barcode, TypesOfBooks type, String title, String language, String genre,
                             String releaseDate, int quantity, double retailPrice,int noOfPages, String condition) {


        try(FileWriter fileWriter = new FileWriter("Stock.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);) {




            printWriter.println(barcode + ", "+type.name().toLowerCase()+", " +title+", " + language+", " + genre +", "+
                    releaseDate + ", "+quantity
                    + ", "+retailPrice +", "+ noOfPages +", "+ condition);


        } catch (IOException e) {

        }
    }

    public void addEbook(int barcode, TypesOfBooks type, String title, String language,
                         String genre, String releaseDate, int quantity, double retailPrice, int noOfPages, EbookFormat format) {
        try(FileWriter fileWriter = new FileWriter("Stock.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);) {



            printWriter.println(barcode + ", "+type.name().toLowerCase()+", " +title+", " + language+", " + genre +", "+
                    releaseDate + ", "+quantity
                    + ", "+retailPrice +", "+ noOfPages +", "+ format.name());


        } catch (IOException e) {

        }

    }

    public void addAudioBook(int barcode, TypesOfBooks type, String title,
                             String language, String genre, String releaseDate,
                             int quantity, double retailPrice,double listeningLength, AudiobookFormat format) {

        try(FileWriter fileWriter = new FileWriter("Stock.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);) {



            printWriter.println(barcode + ", "+type.name().toLowerCase()+", " +title+", " + language+", " + genre +", "+
                    releaseDate + ", "+quantity
                    + ", "+retailPrice +", "+ listeningLength +", "+ format.name());


        } catch (IOException e) {

        }
    }


    @Override
    public String toString() {

        return getUserID()+", "+getUsername()+", "+getSurname()+", "+getHouseNumber()+", "+
                getPostcode()+", "+getCity()+", "+" , "+ getRole().name().toLowerCase();
    }

}
