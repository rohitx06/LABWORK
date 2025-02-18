package LABWORK;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LMS extends Library {
    public LMS(int size) {
        super(size);
    }

    public static void main(String[] args) {
        LMS obj = new LMS(100);
        Scanner sc = new Scanner(System.in);

        Runnable menuTask = () -> {
            while (true) {
                try {
                    System.out.println("\n1. Add Books");
                    System.out.println("2. Display Books");
                    System.out.println("3. Search Books");
                    System.out.println("4. Remove Book");
                    System.out.println("5. Exit");

                    System.out.print("Enter your choice: ");
                    int ch = sc.nextInt();
                    sc.nextLine();

                    switch (ch) {
                        case 1 -> {
                            System.out.print("Enter the number of books to add: ");
                            int num = sc.nextInt();
                            sc.nextLine();

                            for (int i = 0; i < num; i++) {
                                System.out.print("Enter Book ID: ");
                                String id = sc.nextLine();
                                System.out.print("Enter Book Name: ");
                                String name = sc.nextLine();
                                System.out.print("Enter Author Name: ");
                                String author = sc.nextLine();

                                Thread addBookThread = new Thread(() -> obj.addBookDetails(id, name, author));
                                addBookThread.start();
                                addBookThread.join(); // Ensuring book is added before next input
                            }
                        }
                        case 2 -> {
                            Thread displayThread = new Thread(obj::getBookDetails);
                            displayThread.start();
                            displayThread.join();
                        }
                        case 3 -> {
                            System.out.print("Enter Book ID to search: ");
                            String id = sc.nextLine();

                            Thread searchThread = new Thread(() -> obj.searchBook(id));
                            searchThread.start();
                            searchThread.join();
                        }
                        case 4 -> {
                            System.out.print("Enter Book ID to remove: ");
                            String id = sc.nextLine();

                            Thread removeThread = new Thread(() -> obj.removeBook(id));
                            removeThread.start();
                            removeThread.join();
                        }
                        case 5 -> {
                            System.out.println("Exiting the program. Goodbye!");
                            sc.close();
                            System.exit(0);
                        }
                        default -> System.out.println("Invalid choice! Please enter a number between 1 and 5.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                    sc.nextLine();
                } catch (Exception e) {
                    System.out.println("An error occurred: " + e.getMessage());
                }
            }
        };

        Thread menuThread = new Thread(menuTask);
        menuThread.start();
    }
}
