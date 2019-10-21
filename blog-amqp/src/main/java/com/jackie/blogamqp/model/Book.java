package com.jackie.blogamqp.model;

import java.util.Objects;
import java.util.UUID;

public class Book {
    private String id;
    private String bookName;
    private double price;
    private String author;

    public Book() {
    }

    public Book(String bookName, double price, String author) {
        this.id = UUID.randomUUID().toString().replaceAll("-", "");
        this.bookName = bookName;
        this.price = price;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Double.compare(book.getPrice(), getPrice()) == 0 &&
                getId().equals(book.getId()) &&
                getBookName().equals(book.getBookName()) &&
                getAuthor().equals(book.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBookName(), getPrice(), getAuthor());
    }


    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", bookName='" + bookName + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                '}';
    }
}
