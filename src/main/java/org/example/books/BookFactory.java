package org.example.books;

import org.example.TypesOfBooks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BookFactory {
    public List<Book> booksObjList = new ArrayList<>();
    public List<Book> typeOfBookListObj() {

        AudiobookFormat audiobookFormat = null;
        EbookFormat ebookFormat = null;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Stock.txt"));
            String line;
            while ((line = reader.readLine()) != null) {


                String[] check = line.split(", ");


                if(check[1].equals("paperback")){
                    booksObjList.add(new Paperback(Integer.parseInt(check[0]), TypesOfBooks.PAPERBACK,
                            check[2],check[3],check[4],check[5]
                            ,Integer.parseInt(check[6]),Double.parseDouble(check[7]),Integer.parseInt(check[8]),check[9]));
                }

                else if (check[1].equals("audiobook")){

                    if(check[9].equals("MP3")){
                        audiobookFormat = AudiobookFormat.MP3;
                    }

                    else if(check[9].equals("WMA")){
                        audiobookFormat = AudiobookFormat.WMA;
                    } else if (check[9].equals("AAC")) {
                        audiobookFormat = AudiobookFormat.AAC;
                    }


                    booksObjList.add(new Audiobook(Integer.parseInt(check[0]), TypesOfBooks.AUDIOBOOK,
                            check[2],check[3],check[4],check[5]
                            ,Integer.parseInt(check[6]),Double.parseDouble(check[7]),Double.parseDouble(check[8]), audiobookFormat));
                }
                else if (check[1].equals("ebook")) {
                    if(check[9].equals("EPUB")){
                        ebookFormat = EbookFormat.EPUB;
                    }

                    else if(check[9].equals("MOBI")){
                        ebookFormat = EbookFormat.MOBI;
                    } else if (check[9].equals("PDF")) {
                        ebookFormat = EbookFormat.PDF;
                    }

                    booksObjList.add(new Ebook(Integer.parseInt(check[0]), TypesOfBooks.EBOOK,
                            check[2],check[3],check[4],check[5]
                            ,Integer.parseInt(check[6]),Double.parseDouble(check[7]),Integer.parseInt(check[8]),ebookFormat));

                }



            }
            reader.close();
        } catch (IOException e) {
        }


        return booksObjList;

    }


}