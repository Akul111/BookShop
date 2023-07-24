package org.example.books;

import org.example.TypesOfBooks;

public abstract class Book {
    private int barcode;
    private String title;
    private String language;

    private String genre;
    private String releaseDate;
    private int quantity;
    private double retailPrice;
    private TypesOfBooks type;

    public Book(int barcode,TypesOfBooks  type, String title,String language,String genre
            ,String releaseDate,int quantity,double retailPrice){
        this.barcode = barcode;
        this.title = title;
        this.language = language;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.quantity = quantity;
        this.retailPrice = retailPrice;
        this.type = type;
    }

    public double getRetailPrice() {
        return retailPrice;
    }



    public int getBarcode() {
        return barcode;
    }

    public String getTitle() {
        return title;
    }

    public String getLanguage() {
        return language;
    }

    public String getGenre() {
        return genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public TypesOfBooks getType() {
        return type;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

