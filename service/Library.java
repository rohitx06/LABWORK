package service;

import models.Book;
import models.Member;
import java.util.ArrayList;
import java.util.List;


public class Library {
    private final List<Book> books;
    private final List<Member> members;

    public Library() {
        this.books = new ArrayList<>(); // Using basic ArrayList
        this.members = new ArrayList<>();
        System.out.println("Library initialized.");
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully: " + book);
    }

    public void displayBooks() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                if (books.isEmpty()) {
                    System.out.println("No books available in the library.");
                } else {
                    for (Book book : books) {
                        System.out.println(book);
                    }
                }
            }
        });
        t.start();
    }

    // multi-threading implemented here
    public void searchBook(String id) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Book book : books) {
                    if (book.getBookID().equals(id)) {
                        System.out.println(book);
                        return;
                    }
                }
                System.out.println("Book not found.");
            }
        });
        t.start();
    }

    public void removeBook(String id) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Book book : books) {
                    if (book.getBookID().equals(id)) {
                        books.remove(book);
                        System.out.println("Book removed successfully.");
                        return;
                    }
                }
                System.out.println("Book not found.");
            }
        });
        t.start();
    }

    public void addMember(Member member) {
        members.add(member);
        System.out.println("Member added successfully: " + member);
    }

    public void displayMembers() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                if (members.isEmpty()) {
                    System.out.println("No members available.");
                } else {
                    for (Member member : members) {
                        System.out.println(member);
                    }
                }
            }
        });
        t.start();
    }

    public void borrowBook(String bookID) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Book book : books) {
                    if (book.getBookID().equals(bookID) && !book.isBorrowed()) {
                        book.borrowBook();
                        System.out.println("Book borrowed successfully.");
                        return;
                    }
                }
                System.out.println("Book not available or already borrowed.");
            }
        });
        t.start();
    }

    public void returnBook(String bookID) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Book book : books) {
                    if (book.getBookID().equals(bookID) && book.isBorrowed()) {
                        book.returnBook();
                        System.out.println("Book returned successfully.");
                        return;
                    }
                }
                System.out.println("Book is not currently borrowed.");
            }
        });
        t.start();
    }
}
