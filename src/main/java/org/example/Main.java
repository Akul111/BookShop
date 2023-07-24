package org.example;

import org.example.books.AudiobookFormat;
import org.example.books.Book;
import org.example.books.EbookFormat;
import org.example.gui.LoginFrame;
import org.example.user.Admin;
import org.example.user.Customer;
import org.example.user.Role;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        //System.out.println("Please enter the userId displayed in the first column to log in");

        logIn();


    }


    public static void logIn() {
        List<String> usersStringList = new ArrayList<>();
        List<String> userName = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("UserAccounts.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                usersStringList.add(line);
                userName.add(line.split(", ")[1]);
            }
            reader.close();
        } catch (IOException e) {
        }

        LoginFrame loginFrame = new LoginFrame(usersStringList,userName);

    }
}