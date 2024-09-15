import java.util.*;
/**
 * This is a class that holds the book data
 * 
 * @author (Sabir Hussein) 
 * @version (22/03/24)
 */
public class Book extends PrintedItem {
    // Additional fields for books
    private String author;
    private String isbn;
    
    // Getters
    public String getAuthor() {
        return author;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    // Setters
    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    // Method to read book data
    @Override
    public void readData(Scanner scanner) {
        // Additional fields for books
        this.author = scanner.next();
        this.isbn = scanner.next();
        
        super.readData(scanner);
        // Read additional fields specific to books
    }
    
    // Print Details
    @Override
    public void printDetails() {
        // Print details specific to books
        System.out.println("Book Data:");
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        
        super.printDetails(); 
        // Call printDetails method of superclass
        
        System.out.println("");
        // Adds a gap in between each section
        }
}
