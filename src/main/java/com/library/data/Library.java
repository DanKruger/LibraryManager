package com.library.data;

import com.library.model.Book;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Book getBook(int id) {
        for (Book book : books)
            if (book.getId() == id)
                return book;
        return null;
    }

    public void updateBook(Book book) {
        for (Book b : books) {
            if (b.getId() == book.getId()) {
                b.setTitle(book.getTitle());
                b.setAuthor(book.getAuthor());
            }
        }
    }

    public boolean removeBook(int id) {
        return books.removeIf(book -> book.getId() == id);
    }

    public List<Book> getAllBooks() {
        return books;
    }

}
