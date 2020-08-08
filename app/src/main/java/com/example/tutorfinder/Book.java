package com.example.tutorfinder;

public class Book {

    private String book_title;
    private String book_url;

    public Book() {
    }

    public Book(String book_title, String book_url) {
        this.book_title = book_title;
        this.book_url = book_url;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_url() {
        return book_url;
    }

    public void setBook_url(String book_url) {
        this.book_url = book_url;
    }


}
