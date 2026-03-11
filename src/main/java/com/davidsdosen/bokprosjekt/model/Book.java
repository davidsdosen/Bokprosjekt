package com.davidsdosen.bokprosjekt.model;

/**
 * com.davidsdosen.bokprosjekt.model.Book class
 * @author David
 * @version 0.0.1 (16 Nov, 2021)
 * @version 0.0.2 (10 Mar, 2026) refactoring
 */


public class Book
{
    /**
     * Fields with parameters in class book
     */
    private final String title;
    private final String author;
    private final String publisher;
    private final int releaseYear;
    private final int pages;
    private final int barcode;
    private boolean lent;

    /**
     * Constructor
     * @param title Title of book
     * @param author author of book
     * @param publisher publisher of book
     * @param releaseYear release year of book
     * @param pages amount of pages in book
     * @param barcode book's barcode
     */
    public Book(String title, String author, String publisher, int releaseYear, int pages, int barcode)
    {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.releaseYear = releaseYear;
        this.pages = pages;
        this.barcode = barcode;
        this.lent = false;
    }

    public String getTitle(){
        return this.title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public int getPages() {
        return pages;
    }

    public int getBarcode() {
        return barcode;
    }

    public boolean isLent() {
        return lent;
    }

    public void lend() {
        if (lent) {
            throw new IllegalStateException("Book is already lent");
        }
        lent = true;
    }

    public void returnBook() {
        lent = false;
    }

    /**
     * Reusing toString() method from java by using @Override to return for printing information about a book
     * @return returns the book with its details
     */
    @Override
    public String toString() {
        return "//////////////" + "\n" +
                "Title: " + this.title + "\n" +
                "Author: " + this.author + "\n" +
                "Publisher: " + this.publisher + "\n" +
                "Release year: " + this.releaseYear + "\n" +
                "Pages: " + this.pages + "\n" +
                "Barcode: " + this.barcode + "\n" +
                "Lending status: " + this.lent + "\n" +
                "//////////////";
    }
}