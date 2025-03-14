package LABWORK;

import models.Book;
import models.Member;
import service.Library;
import java.util.Scanner;

public class LMS {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\n1. Add Book");
                System.out.println("2. Display Books");
                System.out.println("3. Search Book");
                System.out.println("4. Remove Book");
                System.out.println("5. Add Member");
                System.out.println("6. Display Members");
                System.out.println("7. Borrow Book");
                System.out.println("8. Return Book");
                System.out.println("9. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter Book ID: ");
                        String id = sc.nextLine();
                        System.out.print("Enter Book Title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter Author Name: ");
                        String author = sc.nextLine();
                        library.addBook(new Book(id, title, author)); //uses array of objects concept here
                        break;

                    case 2:
                        library.displayBooks();
                        break;

                    case 3:
                        System.out.print("Enter Book ID to search: ");
                        library.searchBook(sc.nextLine());
                        break;

                    case 4:
                        System.out.print("Enter Book ID to remove: ");
                        library.removeBook(sc.nextLine());
                        break;

                    case 5:
                        System.out.print("Enter Member ID: ");
                        String memberId = sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Email: "); // Add email input
                        String email = sc.nextLine();
                        library.addMember(new Member(memberId, name, email)); // Now matches the expected constructor
                        break;

                    case 6:
                        library.displayMembers();
                        break;

                    case 7:
                        System.out.print("Enter Book ID: ");
                        library.borrowBook(sc.nextLine());
                        break;

                    case 8:
                        System.out.print("Enter Book ID: ");
                        library.returnBook(sc.nextLine());
                        break;

                    case 9:
                        System.out.println("Exiting program...");
                        sc.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice! Please enter a valid option.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                sc.nextLine(); // Clear invalid input
            }
        }
    }
}
