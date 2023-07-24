package org.example.books;

import org.example.TypesOfBooks;

public class Paperback extends Book{

    private int noOfPages;
    private String condition;
    public Paperback(int barcode, TypesOfBooks type, String title, String language,
                     String genre, String releaseDate, int quantity, double retailPrice,
                     int noOfPages, String condition) {
        super(barcode, type, title, language, genre, releaseDate, quantity, retailPrice);
        this.noOfPages =noOfPages;
        this.condition = condition;
    }

    @Override
    public String toString() {
        if(this.getType() == TypesOfBooks.PAPERBACK){
            return   this.getBarcode() + ","+this.getType()+"," +this.getTitle()+"," + this.getLanguage()+"," + this.getGenre() +","+
                    this.getReleaseDate() + ","+this.getQuantity()
                    + ","+this.getRetailPrice() +","+ this.noOfPages +","+ this.condition;
        }
        return "";
    }

}

