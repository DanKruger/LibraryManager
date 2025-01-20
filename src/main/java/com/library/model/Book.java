package com.library.model;

import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private int id;

    public Book(String title, String author) {
        this.id = (int) (Math.random() * 101);
        this.title = title;
        this.author = author;
    }

    public Book(int id, String title, String author) {
        this(title, author);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toPrettyString() {
        String italic = "\033[3m";
        String bold = "\033[1m";
        String reset = "\033[0m";
        return bold + title + " " + reset + "\n"
                + italic + "by " + author + reset + "\n"
                + "ID: " + id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Book book = (Book) o;
        return getId() == book.getId() && Objects.equals(getTitle(), book.getTitle())
                && Objects.equals(getAuthor(), book.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthor(), getId());
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", id=" + id +
                '}';
    }

}
