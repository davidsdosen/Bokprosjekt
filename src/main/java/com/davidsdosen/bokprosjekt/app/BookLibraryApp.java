package com.davidsdosen.bokprosjekt.app;
import com.davidsdosen.bokprosjekt.repository.Registry;
import com.davidsdosen.bokprosjekt.model.Book;
import java.util.Optional;
import java.util.Scanner;

/**
 * com.davidsdosen.bokprosjekt.app.BookLibraryApp class
 * @author David
 * @version 0.0.1 (16 Nov, 2021)
 */
public class BookLibraryApp {

    /**
     * non-initialized field that holds one register object of class com.davidsdosen.bokprosjekt.repository.Registry that again
     * holds multiple book objects of class com.davidsdosen.bokprosjekt.model.Book
     */
    Registry register;

    /**
     * psvm for starting up to run the user interface application
     */
    public static void main(String[] args) {
        BookLibraryApp bookLibraryApp = new BookLibraryApp();
        bookLibraryApp.init();
        bookLibraryApp.start();
    }

    /**
     * Constants representing the different menu choices
     */
    private final static int ADD_BOOK = 1;
    private final static int LIST_ALL_BOOKS = 2;
    private final static int FIND_BOOK_BY_TITLE = 3;
    private final static int FIND_BOOKS_BY_AUTHOR = 4;
    private final static int FIND_BOOK_BY_BARCODE = 5;
    private final static int DELETE_BOOK = 6;
    private final static int EXIT = 9;

    /**
     * Method to initialize the program
     * Runs inside psvm
     */
    public void init(){
        register = new Registry();
        register.addBooks();
    }

    /**
     * 1st part of user interface application
     * Method to show the menu in user interface
     * @return returns the menuChoice from user input after displaying the user input options
     */
    private int showMenu()
    {
        int menuChoice = 0;
        System.out.println("\n***** Library Application v0.1 *****\n");
        System.out.println("1. Add a book");
        System.out.println("2. List all books");
        System.out.println("3. Search by title");
        System.out.println("4. Search by author");
        System.out.println("5. Search by barcode");
        System.out.println("6. Delete a book from the registry");
        System.out.println("9. Quit");
        System.out.println("\nPlease enter a number between 1 and 9.\n");
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            menuChoice = sc.nextInt();
        } else {
            System.out.println("You must enter a number, not text");
        }
        return menuChoice;
    }

    /**
     * 2nd part of user interface application
     * This method runs after showMenu()
     * using switch case to let user decide what to do
     */
    public void start(){
        boolean finished = false;
        while (!finished) {
            int menuChoice = this.showMenu();
            switch (menuChoice)
            {
                case ADD_BOOK:
                    System.out.println("[Process of adding new book started]");
                    System.out.println("Enter book title:");
                    String title = this.stringChoice();
                    System.out.println("Enter book author:");
                    String author = this.stringChoice();
                    System.out.println("Enter book publisher:");
                    String publisher = this.stringChoice();
                    System.out.println("Enter book release year:");
                    int releaseYear = this.intChoice();
                    while (releaseYear <= 0){
                        System.out.println("Error: You must enter a positive value");
                        releaseYear = this.intChoice();
                    }
                    System.out.println("Enter amount of pages in book:");
                    int pages = this.intChoice();
                    while (pages <= 0){
                        System.out.println("Error: You must enter a positive value");
                        pages = this.intChoice();
                    }
                    System.out.println("Enter the book's barcode:");
                    int barcode = this.intChoice();
                    while (barcode <= 0){
                        System.out.println("Error: You must enter a positive value");
                        barcode = this.intChoice();
                    }
                    Book book = new Book(title, author, publisher, releaseYear, pages, barcode);
                    this.register.addBook(book);
                    System.out.println("Book added successfully.");
                    break;
                case LIST_ALL_BOOKS:
                    System.out.println("Listed all books:");
                    this.register.listAllBooks();
                    break;
                case FIND_BOOK_BY_TITLE:
                    System.out.println("Enter a title");
                    String titleOfBook = this.stringChoice();
                    if(this.register.findFirstByName(titleOfBook).isEmpty()){
                        System.out.println("Error: This book does not exist");
                    }
                    else {
                        System.out.println(this.register.findFirstByName(titleOfBook).toString());
                    }
                    break;
                case FIND_BOOKS_BY_AUTHOR:
                    System.out.println("Find all books by author");
                    String stringChoice = this.stringChoice();
                    if(this.register.findAllByAuthor(stringChoice) == null){
                        System.out.println("Error: This book does not exist");
                    }
                    else {
                        System.out.println(this.register.findAllByAuthor(stringChoice).toString());
                    }
                    break;
                case FIND_BOOK_BY_BARCODE:
                    System.out.println("Enter the barcode");
                    int barcodeInt = this.intChoice();
                    Optional<Book> result = register.findByBarcode(barcodeInt);
                    System.out.println(result.map(Book::toString).orElse("Book not found"));

                    /* Old method

                    if(this.register.findByBarcode(barcodeInt).isEmpty()){
                        System.out.println("Error: This book does not exist");
                    }
                    else {
                        System.out.println(this.register.findByBarcode(barcodeInt).toString());
                    }*/
                    break;
                case DELETE_BOOK:
                    System.out.println("Enter the barcode");
                    int barcodeIntForDeletion = this.intChoice();
                    if(register.deleteByBarcode(barcodeIntForDeletion)){
                        System.out.println("Book has been deleted correctly");
                    }else {
                        System.out.println("Book does not exist");
                    }
                        break;
                case EXIT:
                    System.out.println("Thank you for using the Properties app!\n");
                    finished = true;
                    break;
                default:
                    System.out.println("Unrecognized menu selected..");
                    break;
            }
        }
    }

    /**
     * Method for user input and controlling that user input is text
     * @return returns user input if it passes checks
     */
    private String stringChoice() {
        String stringChoice = null;
        boolean isAText = false;
        while (!isAText) {
            Scanner sc = new Scanner(System.in);
            if (!sc.hasNextInt() || !sc.hasNextDouble()) {
                stringChoice = sc.nextLine();
                isAText = true;
            } else {
                System.out.println("Error: You must enter text");
            }
        }
        return stringChoice;
    }

    /**
     * Method for user input controlling that user input is a number
     * @return returns user input if it passes checks
     */
    private int intChoice(){
        int intChoice = 0;
        boolean isANumber = false;
        while (!isANumber) {
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                intChoice = sc.nextInt();
                isANumber = true;
            } else {
                System.out.println("Error: You must enter a number");
            }
        }
        return intChoice;
    }

    public double doubleChoice(){
        while (true){
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()){
                return Math.abs(sc.nextInt());
            }
            System.out.println("You need to input a valid number\n");
        }
    }
}