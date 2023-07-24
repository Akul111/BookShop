package org.example.books;


import org.example.TypesOfBooks;

public class Ebook extends Book{
    private int noOfPages;
    private EbookFormat format;

    public Ebook(int barcode, TypesOfBooks type, String title, String language,
                 String genre, String releaseDate, int quantity, double retailPrice, int noOfPages, EbookFormat format) {
        super(barcode, type, title, language, genre, releaseDate, quantity, retailPrice);
        this.noOfPages = noOfPages;
        this.format = format;
    }

    @Override
    public String toString() {
        if(this.getType() == TypesOfBooks.EBOOK){
            return   this.getBarcode() + "," +this.getType()+","+this.getTitle()+"," + this.getLanguage()+"," + this.getGenre() +","+
                    this.getReleaseDate() + ","+this.getQuantity()
                    + ","+this.getRetailPrice() +","+ this.noOfPages +","+ this.format;
        }
        return "";
    }


}
