package service;

import models.Book;
import models.Member;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private final Connection conn;

    public Library() {
        this.conn = DatabaseConnection.getConnection();
    }

    public void addBook(Book book) {
        String sql = "INSERT INTO books (bookID, title, author, isBorrowed) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getBookID());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.setBoolean(4, false);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMember(Member member) {
        String sql = "INSERT INTO members (memberID, name, email, fineAmount) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, member.getMemberID());
            stmt.setString(2, member.getName());
            stmt.setString(3, member.getEmail());
            stmt.setDouble(4, 0);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getAllBooks() {
        String sql = "SELECT * FROM books";
        StringBuilder result = new StringBuilder("Books List:\n");

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                result.append("ID: ").append(rs.getString("bookID"))
                      .append(" | Title: ").append(rs.getString("title"))
                      .append(" | Author: ").append(rs.getString("author"))
                      .append(" | Status: ").append(rs.getBoolean("isBorrowed") ? "Borrowed" : "Available")
                      .append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error fetching books.";
        }
        return result.toString();
    }

    public String getAllMembers() {
        String sql = "SELECT * FROM members";
        StringBuilder result = new StringBuilder("Members List:\n");

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                result.append("ID: ").append(rs.getString("memberID"))
                      .append(" | Name: ").append(rs.getString("name"))
                      .append(" | Email: ").append(rs.getString("email"))
                      .append(" | Fine: $").append(rs.getDouble("fineAmount"))
                      .append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error fetching members.";
        }
        return result.toString();
    }

    public String searchBook(String bookID) {
        String sql = "SELECT * FROM books WHERE bookID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bookID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return "Book Found:\nID: " + rs.getString("bookID") +
                       " | Title: " + rs.getString("title") +
                       " | Author: " + rs.getString("author") +
                       " | Status: " + (rs.getBoolean("isBorrowed") ? "Borrowed" : "Available");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Book not found.";
    }

    public void removeBook(String bookID) {
        String sql = "DELETE FROM books WHERE bookID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bookID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBookStatus(String bookID, boolean borrow) {
        String sql = "UPDATE books SET isBorrowed = ? WHERE bookID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, borrow);
            stmt.setString(2, bookID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayBooks() {
        Thread t = new Thread(() -> System.out.println(getAllBooks()));
        t.start();
    }

    public void displayMembers() {
        Thread t = new Thread(() -> System.out.println(getAllMembers()));
        t.start();
    }

    public void borrowBook(String bookID) {
        Thread t = new Thread(() -> {
            String sql = "UPDATE books SET isBorrowed = TRUE WHERE bookID = ? AND isBorrowed = FALSE";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, bookID);
                int updated = stmt.executeUpdate();
                if (updated > 0) {
                    System.out.println("Book borrowed successfully.");
                } else {
                    System.out.println("Book not available or already borrowed.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        t.start();
    }

    public void returnBook(String bookID) {
        Thread t = new Thread(() -> {
            String sql = "UPDATE books SET isBorrowed = FALSE WHERE bookID = ? AND isBorrowed = TRUE";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, bookID);
                int updated = stmt.executeUpdate();
                if (updated > 0) {
                    System.out.println("Book returned successfully.");
                } else {
                    System.out.println("Book is not currently borrowed.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        t.start();
    }
}
