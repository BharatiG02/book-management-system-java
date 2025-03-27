import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BookManagementSystem {

    // A map to store books by their ID
    private static Map<String, Book> books = new HashMap<>();

    // Book class to store book details
    static class Book {
        String title;
        String author;
        String genre;
        String availability;

        public Book(String title, String author, String genre, String availability) {
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.availability = availability;
        }

        @Override
        public String toString() {
            return "Title: " + title + "\nAuthor: " + author + "\nGenre: " + genre + "\nAvailability: " + availability;
        }
    }

    // Function to add a book
    public static void addBook(Scanner scanner) {
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        if (books.containsKey(bookId)) {
            System.out.println("Error: Book ID must be unique.");
            return;
        }

        System.out.print("Enter Title: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("Error: Title cannot be empty.");
            return;
        }

        System.out.print("Enter Author: ");
        String author = scanner.nextLine().trim();
        if (author.isEmpty()) {
            System.out.println("Error: Author cannot be empty.");
            return;
        }

        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine().trim();

        System.out.print("Enter Availability (Available/Checked Out): ");
        String availability = scanner.nextLine().trim().toLowerCase();
        if (!availability.equals("available") && !availability.equals("checked out")) {
            System.out.println("Error: Availability status must be either 'Available' or 'Checked Out'.");
            return;
        }

        Book book = new Book(title, author, genre, availability.equals("available") ? "Available" : "Checked Out");
        books.put(bookId, book);
        System.out.println("Book added successfully.");
    }

    // Function to view all books
    public static void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        for (Map.Entry<String, Book> entry : books.entrySet()) {
            System.out.println("Book ID: " + entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("----------------------------");
        }
    }

    // Function to search a book by ID or Title
    public static void searchBook(Scanner scanner) {
        System.out.print("Enter Book ID or Title to search: ");
        String searchTerm = scanner.nextLine().trim();

        // Search by Book ID
        if (books.containsKey(searchTerm)) {
            Book book = books.get(searchTerm);
            System.out.println("Book ID: " + searchTerm);
            System.out.println(book);
        } else {
            boolean found = false;
            for (Map.Entry<String, Book> entry : books.entrySet()) {
                if (entry.getValue().title.toLowerCase().contains(searchTerm.toLowerCase())) {
                    System.out.println("Book ID: " + entry.getKey());
                    System.out.println(entry.getValue());
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No matching books found.");
            }
        }
    }

    // Function to update a book
    public static void updateBook(Scanner scanner) {
        System.out.print("Enter Book ID to update: ");
        String bookId = scanner.nextLine();
        if (!books.containsKey(bookId)) {
            System.out.println("Error: Book not found.");
            return;
        }

        Book book = books.get(bookId);

        System.out.println("Current details of the book:");
        System.out.println(book);

        System.out.print("Enter new Title (or press Enter to keep current): ");
        String title = scanner.nextLine().trim();
        if (!title.isEmpty()) {
            book.title = title;
        }

        System.out.print("Enter new Author (or press Enter to keep current): ");
        String author = scanner.nextLine().trim();
        if (!author.isEmpty()) {
            book.author = author;
        }

        System.out.print("Enter new Genre (or press Enter to keep current): ");
        String genre = scanner.nextLine().trim();
        if (!genre.isEmpty()) {
            book.genre = genre;
        }

        System.out.print("Enter new Availability (Available/Checked Out or press Enter to keep current): ");
        String availability = scanner.nextLine().trim().toLowerCase();
        if (!availability.isEmpty() && (availability.equals("available") || availability.equals("checked out"))) {
            book.availability = availability.equals("available") ? "Available" : "Checked Out";
        }

        books.put(bookId, book);
        System.out.println("Book updated successfully.");
    }

    // Function to delete a book
    public static void deleteBook(Scanner scanner) {
        System.out.print("Enter Book ID to delete: ");
        String bookId = scanner.nextLine();
        if (!books.containsKey(bookId)) {
            System.out.println("Error: Book not found.");
            return;
        }

        books.remove(bookId);
        System.out.println("Book with ID " + bookId + " deleted successfully.");
    }

    // Main function to display menu and handle user input
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nBook Management System");
            System.out.println("1. Add a Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book by ID or Title");
            System.out.println("4. Update Book Details");
            System.out.println("5. Delete a Book Record");
            System.out.println("6. Exit System");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    viewAllBooks();
                    break;
                case 3:
                    searchBook(scanner);
                    break;
                case 4:
                    updateBook(scanner);
                    break;
                case 5:
                    deleteBook(scanner);
                    break;
                case 6:
                    System.out.println("Exiting system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
