// package LABWORK;

import models.Book;
import models.Member;
import service.Library;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LibraryManagementGUI {
    private final Library library;
    private final JFrame frame;
    private final JTextArea displayArea;
    private final JTextField bookIdField, titleField, authorField, memberIdField, nameField, emailField;

    public LibraryManagementGUI() {
        library = new Library();
        frame = new JFrame("Library Management System");
        frame.setSize(600, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(12, 2, 5, 5));

        bookIdField = new JTextField();
        titleField = new JTextField();
        authorField = new JTextField();
        memberIdField = new JTextField();
        nameField = new JTextField();
        emailField = new JTextField();

        panel.add(new JLabel("Book ID:"));
        panel.add(bookIdField);
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Author:"));
        panel.add(authorField);
        panel.add(new JLabel("Member ID:"));
        panel.add(memberIdField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        JButton addBookBtn = new JButton("Add Book");
        JButton addMemberBtn = new JButton("Add Member");
        JButton displayBooksBtn = new JButton("Display Books");
        JButton displayMembersBtn = new JButton("Display Members");
        JButton searchBookBtn = new JButton("Search Book");
        JButton removeBookBtn = new JButton("Remove Book");
        JButton borrowBookBtn = new JButton("Borrow Book");
        JButton returnBookBtn = new JButton("Return Book");

        panel.add(addBookBtn);
        panel.add(addMemberBtn);
        panel.add(displayBooksBtn);
        panel.add(displayMembersBtn);
        panel.add(searchBookBtn);
        panel.add(removeBookBtn);
        panel.add(borrowBookBtn);
        panel.add(returnBookBtn);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        addBookBtn.addActionListener(this::addBook);
        addMemberBtn.addActionListener(this::addMember);
        displayBooksBtn.addActionListener(this::displayBooks);
        displayMembersBtn.addActionListener(this::displayMembers);
        searchBookBtn.addActionListener(this::searchBook);
        removeBookBtn.addActionListener(this::removeBook);
        borrowBookBtn.addActionListener(this::borrowBook);
        returnBookBtn.addActionListener(this::returnBook);

        frame.setVisible(true);
    }

    private void addBook(ActionEvent e) {
        String id = bookIdField.getText();
        String title = titleField.getText();
        String author = authorField.getText();
        library.addBook(new Book(id, title, author));
        displayArea.setText("Book Added Successfully!\n");
    }

    private void addMember(ActionEvent e) {
        String memberId = memberIdField.getText();
        String name = nameField.getText();
        String email = emailField.getText();
        library.addMember(new Member(memberId, name, email));
        displayArea.setText("Member Added Successfully!\n");
    }

    private void displayBooks(ActionEvent e) {
        displayArea.setText(library.getAllBooks());
    }

    private void displayMembers(ActionEvent e) {
        displayArea.setText(library.getAllMembers());
    }

    private void searchBook(ActionEvent e) {
        String bookID = bookIdField.getText();
        displayArea.setText(library.searchBook(bookID));
    }

    private void removeBook(ActionEvent e) {
        String bookID = bookIdField.getText();
        library.removeBook(bookID);
        displayArea.setText("Book Removed Successfully!\n");
    }

    private void borrowBook(ActionEvent e) {
        String bookID = bookIdField.getText();
        library.updateBookStatus(bookID, true);
        displayArea.setText("Book Borrowed Successfully!\n");
    }

    private void returnBook(ActionEvent e) {
        String bookID = bookIdField.getText();
        library.updateBookStatus(bookID, false);
        displayArea.setText("Book Returned Successfully!\n");
    }

    public static void main(String[] args) {
        new LibraryManagementGUI();
    }
}