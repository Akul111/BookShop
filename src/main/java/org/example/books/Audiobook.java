package org.example.books;

import org.example.TypesOfBooks;

public class Audiobook extends Book{
    private double listeningLength;
    private AudiobookFormat format;
    public Audiobook(int barcode, TypesOfBooks type, String title, String language,
                     String genre, String releaseDate, int quantity,
                     double retailPrice, double listeningLength, AudiobookFormat format) {
        super(barcode, type, title, language, genre, releaseDate, quantity, retailPrice);
        this.listeningLength= listeningLength;
        this.format = format;
    }

    @Override
    public String toString() {
        if(this.getType() == TypesOfBooks.AUDIOBOOK){
            return  this.getBarcode() + "," +this.getType()+","+this.getTitle()+"," + this.getLanguage()+"," + this.getGenre() +","+
                    this.getReleaseDate() + ","+this.getQuantity()
                    + ","+this.getRetailPrice() +","+ this.listeningLength +","+ this.format;
        }
        return "";
    }

    public double getListeningLength() {
        return listeningLength;
    }
}
