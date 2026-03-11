package com.davidsdosen.bokprosjekt.repository;
import com.davidsdosen.bokprosjekt.model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Registry class
 * @author David
 * @version 0.0.1 (16 Nov, 2021)
 * @version 0.0.2 (11 Mar, 2026) refactoring
 */
public class Registry {

    /**
     * field bookList that holds an ArrayList that holds the books (Book objects)
     */
    ArrayList<Book> books;

    /**
     * Method that initializes the field bookList
     */
    public Registry(){
        books = new ArrayList<>();
    }

    /**
     * Method that adds a new book
     * @param book book with its respective fields
     */
    public void addBook(Book book){
        if (book == null)
        {
            System.out.println("Error: empty value cannot be added as book");
        }
        else
            this.books.add(book);
    }

    /**
     * Getter method that returns all books
     */
    public List<Book> getAllBooks(){
        return books;
    }

    /**
     * Method that finds first by title
     * @param searchString the title that comes from user input
     * @return returns the book
     */
    public Optional<Book> findFirstByName(String searchString){
        return books.stream().filter(book -> book.getTitle().contains(searchString)).findFirst();
    }

    /**
     * Method that finds a book by barcode
     * @param searchInt the barcode int variable that comes from user input
     * @return returns a book found by the specific barcode
     */
    public Optional<Book> findByBarcode(int searchInt){
        return books.stream().filter(book -> book.getBarcode() == searchInt).findFirst();
    }

    /**
     * Method that finds all books by author name
     * @param searchAuthor author's name from user input
     * @return returns an ArrayList of books
     */
    public ArrayList<Book> findAllByAuthor(String searchAuthor){
        return books.stream().filter(book -> book.getAuthor().contains(searchAuthor)).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Method that deletes a book by barcode
     * @param barcodeInt barcode entered by user input
     * @return returns true if a book was deleted, false if a book was not found
     */
    public boolean deleteByBarcode(int barcodeInt){
        return books.removeIf(book -> book.getBarcode() == barcodeInt);
    }

    /**
     * addBooks method adds sample books to the library
     */
    public void addBooks() {
        this.addBook(new Book("Harry Potter", "J.K. Rowling", "Gyldendal", 1997, 600, 201));
        this.addBook(new Book("Kokebok", "David", "NTNU Forlag",2016,150,123));
        this.addBook(new Book("Avoid being ganked on midlane", "Raul", "Mancave Publishing",2021,10,124));
        this.addBook(new Book("David's Book of Grudges", "David", "Mancave Publishing",2021,3,125));
    }
}