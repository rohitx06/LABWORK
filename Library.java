package LABWORK;

class Library {
    protected StringBuffer[] books;
    protected int bookCount;

    public Library(int size) {
        this.books = new StringBuffer[size];
        this.bookCount = 0;
        System.out.println("Library initialized with size " + size + ".");
    }

    public synchronized void addBookDetails(String id, String name, String author) {
        try {
            if (bookCount >= books.length) {
                throw new ArrayIndexOutOfBoundsException("Library is full! Cannot add more books.");
            }
            StringBuffer bookDetails = new StringBuffer();
            bookDetails.append("ID:").append(id).append("\t")
                       .append("Name:").append(name).append("\t")
                       .append("Author:").append(author);
            books[bookCount] = bookDetails;
            bookCount++;
            System.out.println("Book added successfully.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public synchronized void getBookDetails() {
        if (bookCount == 0) {
            System.out.println("No books available in the library.");
            return;
        }
        for (int i = 0; i < bookCount; i++) {
            System.out.println(books[i].toString());
        }
    }

    public synchronized void searchBook(String id) {
        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (books[i].toString().contains("ID:" + id)) {
                System.out.println("Book Found: " + books[i].toString());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No book found with ID: " + id);
        }
    }

    public synchronized void removeBook(String id) {
        try {
            boolean found = false;
            for (int i = 0; i < bookCount; i++) {
                if (books[i].toString().contains("ID:" + id)) {
                    System.out.println("Removing Book: " + books[i].toString());
                    books[i] = books[bookCount - 1];
                    books[bookCount - 1] = null;
                    bookCount--;
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new Exception("Book with ID " + id + " not found.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
