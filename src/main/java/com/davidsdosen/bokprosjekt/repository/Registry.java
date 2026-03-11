package com.davidsdosen.bokprosjekt.repository;
import com.davidsdosen.bokprosjekt.model.Book;
import java.util.ArrayList;
import java.util.Optional;

/**
 * com.davidsdosen.bokprosjekt.repository.Registry class
 * @author David
 * @version 0.0.1 (16 Nov, 2021)
 */
public class Registry {

    /**
     * field bookList that holds an ArrayList that holds the books (com.davidsdosen.bokprosjekt.model.Book objects)
     */
    ArrayList<Book> bookList;

    /**
     * Method that initializes the field bookList
     */
    public Registry(){
        bookList = new ArrayList<>();
    }

    /**
     * Method that adds new book
     * @param book book with its respective fields
     */
    public void addBook(Book book){
        if (book == null)
        {
            System.out.println("Error: empty value cannot be added as book");
        }
        else
            this.bookList.add(book);
    }

    /**
     * addBooks method adds sample books to the library
     * Stage 2 Task 2
     */
    public void addBooks() {
        this.addBook(new Book("Harry Potter", "J.K. Rowling", "Gyldendal", 1997, 600, 201));
        this.addBook(new Book("Kokebok", "David", "NTNU Forlag",2016,150,123));
        this.addBook(new Book("Avoid being ganked on midlane", "Raul", "Mancave Publishing",2021,10,124));
        this.addBook(new Book("David's Book of Grudges", "David", "Mancave Publishing",2021,3,125));
    }

    /**
     * Method that lists all books
     * Stage 2 Task 3
     */
    public void listAllBooks() {
        int index = 0;
        while (index < bookList.size())
        {
            Book book = bookList.get(index);
            System.out.println("----------------------");
            System.out.println("Title :" + book.getTitle());
            System.out.println("Author :" + book.getAuthor());
            System.out.println("Publisher :" + book.getPublisher());
            System.out.println("Release year :" + book.getReleaseYear());
            System.out.println("Pages :" + book.getPages());
            System.out.println("Barcode :" + book.getBarcode());
            System.out.println("Currently lent :" + book.isLent());
            index++;
        }
    }

    /**
     * Method that finds first by title
     * @param searchString the title that comes from user input
     * @return returns the book
     * Stage 3 Task 2
     */
    public Optional<Book> findFirstByName(String searchString){
        int index = 0;
        while (index < bookList.size()){
            Book filename = bookList.get(index);
            if (filename.getTitle().contains(searchString)){
                return Optional.of(filename);
            }
            index++;
        }
        return Optional.empty();
    }

    /**
     * Method that finds book by barcode
     * @param searchInt the barcode int variable that comes from user input
     * @return returns book that was found by the specific barcode
     */
    public Optional<Book> findByBarcode(int searchInt){
        int index = 0;
        while (index < bookList.size()){
            Book filename = bookList.get(index);
            if (filename.getBarcode() == searchInt){
                return Optional.of(filename);
            }
            index++;
        }
        return Optional.empty();
    }

    /**
     * Method that finds all books by author name
     * @param searchAuthor author's name from user input
     * @return returns a list with all the books by said author
     * Stage 3 Task 3
     */
    public ArrayList<Book> findAllByAuthor(String searchAuthor){
        int index = 0;
        ArrayList<Book> tempBookList = new ArrayList<>();
        while (index < bookList.size()){
            Book filename = bookList.get(index);
            if (filename.getAuthor().contains(searchAuthor)){
                tempBookList.add(filename);
            }
            index++;
        }
        return tempBookList;
    }

    /**
     * Method that deletes book by barcode
     * @param barcodeInt barcode entered by user input
     * @return
     */
    public boolean deleteByBarcode(int barcodeInt){
        for (Book book: bookList) {
            if(book.getBarcode() == barcodeInt){
                bookList.remove(book);
                return true;
            }
        }
        return false;
    }

}
