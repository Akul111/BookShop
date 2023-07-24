package org.example.user;

import org.example.TypesOfBooks;
import org.example.books.Audiobook;
import org.example.books.Book;
import org.example.books.BookFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Customer extends Users {

    private double creditBalanace;
    private List<Book> shoppingBasket;

    public Customer(int userID, String username, String surname, int houseNumber, String postcode,
                    String city, double creditBalance, Role role, List<Book> shoppingBasket) {
        super(userID, username, surname, houseNumber, postcode, city, role);
        this.creditBalanace = creditBalance;
        this.shoppingBasket = shoppingBasket;

    }
    BookFactory bookFactory = new BookFactory();
    List<Book> listOfBooksAvailable = bookFactory.typeOfBookListObj();
    public String addItem(int chosenBook) {

        for (Book book: listOfBooksAvailable) {
            if(book.getQuantity() == 0 && book.getBarcode() == chosenBook){
                return "This book is out of stock.We Apologise.";
            }  else if(book.getBarcode() == chosenBook){
                System.out.println(this.shoppingBasket);
                this.shoppingBasket.add(book);
                book.setQuantity(book.getQuantity()-1);
                System.out.println(book.getQuantity());
            }
        }
        return null;
    }

    public void clearBasket() {
        BookFactory bookFactory2 = new BookFactory();
        List<Book> listOfBooksAvailable2 = bookFactory2.typeOfBookListObj();
        for (int i =0; i<listOfBooksAvailable.size(); i++) {
            if(listOfBooksAvailable.get(i).getQuantity() != listOfBooksAvailable2.get(i).getQuantity()){
                listOfBooksAvailable.get(i).setQuantity(listOfBooksAvailable2.get(i).getQuantity()) ;
            }
        }

        this.shoppingBasket.clear();
    }



    public String checkout() {
        double total = 0;
        for (int i = 0; i < this.shoppingBasket.size(); i++) {
            total = total + this.shoppingBasket.get(i).getRetailPrice();
        }



        if (total > this.creditBalanace) {
            return  "Insufficient funds";
        } else {
            this.creditBalanace = this.creditBalanace - total;
            for (int i = 0; i < this.shoppingBasket.size(); i++) {

                try {
                    BufferedReader readerStock = new BufferedReader(new FileReader("Stock.txt"));
                    BufferedReader readerAccount = new BufferedReader(new FileReader("UserAccounts.txt"));

                    StringBuilder stringBuilderStock = new StringBuilder();
                    StringBuilder stringBuilderAccount = new StringBuilder();


                    String line;
                    while ((line = readerStock.readLine()) != null) {
                        String[] check = line.split(", ");
                        if (Integer.parseInt(check[0]) == this.shoppingBasket.get(i).getBarcode()) {
                            //Add string builder
                            check[6] = String.valueOf(Integer.parseInt(check[6]) - 1);
                            line = check[0] + ", " + check[1] + ", " + check[2] + ", " + check[3] + ", " + check[4] + ", "
                                    + check[5] + ", " + check[6] + ", " + check[7] + ", " + check[8] + ", " + check[9];
                        }
                        stringBuilderStock.append(line).append("\n");

                    }

                    while ((line = readerAccount.readLine()) != null) {
                        String[] check = line.split(", ");
                        if (Integer.parseInt(check[0]) == this.getUserID()) {

                            check[6] = String.valueOf(this.creditBalanace);
                            line = check[0] + ", " + check[1] + ", " + check[2] + ", " + check[3] + ", " + check[4] + ", "
                                    + check[5] + ", " + check[6] + ", " + check[7];
                        }
                        stringBuilderAccount.append(line).append("\n");

                    }

                    FileWriter fileWriterStock = new FileWriter("Stock.txt");
                    BufferedWriter bufferedWriterStock = new BufferedWriter(fileWriterStock);
                    fileWriterStock.write(stringBuilderStock.toString());

                    FileWriter fileWriterAccount = new FileWriter("UserAccounts.txt");
                    BufferedWriter bufferedWriterAccount = new BufferedWriter(fileWriterAccount);
                    fileWriterAccount.write(stringBuilderAccount.toString());

                    readerStock.close();
                    bufferedWriterStock.close();

                    readerAccount.close();
                    bufferedWriterAccount.close();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            clearBasket();

            return "Thank you for the purchase! £ " + total +
                    " paid and your remaining credit balance is £" + this.creditBalanace + "." +
                    " Your delivery address is " + this.getHouseNumber() + " , " + this.getCity() + " , " + this.getPostcode() + ".";




        }

    }

    public List<Book> getShoppingBasket() {
        return shoppingBasket;
    }

    public List<Book> barcodeSearch(int barcode) {
        BookFactory bookFactory = new BookFactory();

        List<Book> listOfBooks = bookFactory.typeOfBookListObj();

        List<Book> searchedBooksList = new ArrayList<>();

        for(Book book : listOfBooks){
            if(book.getBarcode() == barcode){
                searchedBooksList.add(book);
            }
        }

        return  searchedBooksList;
    }

    public List<Book> listeningLengthFilter(double listeningLengthRequired) {

        BookFactory bookFactory = new BookFactory();

        List<Book> listOfBooks = bookFactory.typeOfBookListObj();

        List<Book> filteredBooksList = new ArrayList<>();

        for (Book book : listOfBooks) {
            if (book.getType() == TypesOfBooks.AUDIOBOOK && ((Audiobook) book).getListeningLength() > listeningLengthRequired) {
                filteredBooksList.add(book);

            }
        }
        return filteredBooksList;
    }


    @Override
    public String toString() {

        return getUserID()+", "+getUsername()+", "+getSurname()+", "+getHouseNumber()+", "+
                getPostcode()+", "+getCity()+", "+String.format("%.2f", creditBalanace)+", "+ getRole().name().toLowerCase();
    }

}

