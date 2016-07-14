package com.example.visen.sqlitedemo;

/**
 * Created by www40 on 2016/6/10.
 */
public class Book {

    private String bookName;
    private String author;
    private String pages;
    private String price;

    public Book(String bookName,String author,String pages,String price){
        this.bookName = bookName;
        this.author = author;
        this.pages = pages;
        this.price = price;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public String getPages() {
        return pages;
    }

    public String getPrice() {
        return price;
    }

}
