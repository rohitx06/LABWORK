// package LABWORK;



import models.Book;
import models.Member;
import service.Library;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryManagementGUI {
    private Library library;
    private JFrame frame;
    private JTextArea displayArea;
    private JTextField bookIdField, titleField, authorField, memberIdField, nameField, emailField;

    public LibraryManagementGUI() {
        library = new Library();
        frame = new JFrame("Library Management System");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2, 5, 5));
        
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
        JButton displayBooksBtn = new JButton("Display Books");
        JButton searchBookBtn = new JButton("Search Book");
        JButton borrowBookBtn = new JButton("Borrow Book");
        JButton returnBookBtn = new JButton("Return Book");
        JButton addMemberBtn = new JButton("Add Member");
        JButton displayMembersBtn = new JButton("Display Members");
        
        panel.add(addBookBtn);
        panel.add(displayBooksBtn);
        panel.add(searchBookBtn);
        panel.add(borrowBookBtn);
        panel.add(returnBookBtn);
        panel.add(addMemberBtn);
        panel.add(displayMembersBtn);
        
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        
        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        
        addBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = bookIdField.getText();
                String title = titleField.getText();
                String author = authorField.getText();
                library.addBook(new Book(id, title, author));
                displayArea.setText("Book Added Successfully!\n");
            }
        });
        
        displayBooksBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                library.displayBooks();
            }
        });
        
        searchBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = bookIdField.getText();
                library.searchBook(id);
            }
        });
        
        borrowBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = bookIdField.getText();
                library.borrowBook(id);
            }
        });
        
        returnBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = bookIdField.getText();
                library.returnBook(id);
            }
        });
        
        addMemberBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String memberId = memberIdField.getText();
                String name = nameField.getText();
                String email = emailField.getText();
                library.addMember(new Member(memberId, name, email));
                displayArea.setText("Member Added Successfully!\n");
            }
        });
        
        displayMembersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                library.displayMembers();
            }
        });
        
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new LibraryManagementGUI();
    }
}
