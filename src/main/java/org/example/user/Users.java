package org.example.user;

import org.example.books.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Users implements UserInterface {
    private int userID;
    private String username;
    private String surname;
    private int houseNumber;
    private String postcode;
    private String city;
    private Role role;

    public List<Book> booksList = new ArrayList<>();


    public Users(int userID, String username, String surname, int houseNumber,
                 String postcode, String city, Role role) {
        this.userID = userID;
        this.username = username;
        this.surname = surname;
        this.houseNumber = houseNumber;
        this.postcode = postcode;
        this.city = city;
        this.role = role;


    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public List<Book> viewAllBooks() {
        BookFactory bookFactory = new BookFactory();
        List<Book> sortedList = null;

        if(this.role == Role.ADMIN){
            return sortedList = bookFactory.typeOfBookListObj().stream()
                    .sorted(Comparator.comparingInt(Book::getQuantity))
                    .collect(Collectors.toList());
        }

        else if(this.role == Role.CUSTOMER){
            return sortedList = bookFactory.typeOfBookListObj().stream()
                    .sorted(Comparator.comparingDouble(Book::getRetailPrice))
                    .collect(Collectors.toList());
        }


        return null;
    }


    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getSurname() {
        return surname;
    }

    public Role getRole() {
        return role;
    }





}



