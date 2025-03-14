package models;

public class Book {
    private final String bookID;
    private final String title;
    private final String author;
    private boolean isBorrowed;

    public Book(String bookID, String title, String author) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public Book(String bookID, String title) {
       this(bookID, title, title);
    }

    public String getBookID() {
        return bookID;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrowBook() {
        this.isBorrowed = true;
    }

    public void returnBook() {
        this.isBorrowed = false;
    }

    @Override
    public String toString() {
        return "ID: " + bookID + " | Title: " + title + " | Author: " + author + " | Status: "
                + (isBorrowed ? "Borrowed" : "Available");
    }
}
