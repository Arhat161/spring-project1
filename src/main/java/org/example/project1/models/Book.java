package org.example.project1.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {

    private int bookId;
    //private int personId;

    @NotEmpty(message = "Name not be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String bookName;
    @NotEmpty(message = "Author not be empty")
    @Size(min = 2, max = 100, message = "Author should be between 2 and 100 characters")
    private String bookAuthor;
    @NotEmpty(message = "Year not be empty")
    @Min(value = 1500, message = "Year should be greater than 1500")
    private int bookYear;

    public Book() {
    }

    public Book(String bookName, String bookAuthor, int bookYear) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookYear = bookYear;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    /*public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }*/

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getBookYear() {
        return bookYear;
    }

    public void setBookYear(int bookYear) {
        this.bookYear = bookYear;
    }
}
