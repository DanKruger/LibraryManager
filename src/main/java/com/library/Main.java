package com.library;

import java.util.List;
import java.util.Scanner;

import com.library.data.Library;
import com.library.data.LibraryStore;
import com.library.model.Book;

public class Main {
    private static Library library;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String user = getUser(scanner);
        library = LibraryStore.loadLibrary(user);
        start(scanner);
    }

    private static String getUser(Scanner scanner) {
        String user = "";
        do {
            System.out.println("Please choose a user");
            user = prompt("Username", scanner);
        } while (user.isEmpty());
        return user;
    }

    private static void start(Scanner scanner) {
        while (true) {
            String input = prompt(scanner);
            Commands command = null;
            try {
                command = Commands.valueOf(input.toUpperCase());
            } catch (Exception e) {
                System.out.println("Not a command");
                continue;
            }

            switch (command) {
                case ADD:
                    addBook(scanner);
                    break;
                case LIST:
                    getBooks();
                    break;
                case UPDATE:
                    updateBook(scanner);
                    break;
                case REMOVE:
                    removeBook(scanner);
                    break;
                case HELP:
                    help();
                    break;
                case EXIT:
                case QUIT:
                    System.out.println("Goodbye");
                    System.exit(1);
                    break;
                default:
                    break;
            }
        }
    }

    private static void help() {
        System.out.println("Data files are stored in:");
        System.out.println(LibraryStore.getFilePath());
    }

    private static void updateBook(Scanner scanner) {
        System.out.println("Which book do you want to update?");
        String id = prompt("ID", scanner);
        if (!id.matches("[0-9]+")) {
            System.out.println("You need to enter a number");
            return;
        }
        Book book = library.getBook(Integer.parseInt(id));
        if (book == null) {
            System.out.println("could not find that book.");
            return;
        }
        System.out.println("What do you want to update?");
        System.out.println("Title? (T)\nAuthor? (A)");
        String change = prompt("T/A", scanner);
        System.out.println("Enter a new value.");
        if (change.equalsIgnoreCase("A")) {
            String newAuthor = prompt("New Author", scanner);
            book.setAuthor(newAuthor);
        } else if (change.equalsIgnoreCase("T")) {
            String newTitle = prompt("New Title", scanner);
            book.setTitle(newTitle);
        }
        System.out.println("Updated Book");
        System.out.println(book.toPrettyString());
        library.updateBook(book);
        LibraryStore.saveLibrary(library);
    }

    private static void removeBook(Scanner scanner) {
        System.out.println("Which book do you want to remove? (id)");
        String id = prompt("ID", scanner);
        if (!id.matches("[0-9]+")) {
            System.out.println("You need to enter a number");
            return;
        }
        boolean result = library.removeBook(Integer.parseInt(id));
        if (!result) {
            System.out.println("Could not find that ID");
            return;
        }
        LibraryStore.saveLibrary(library);
    }

    private static void getBooks() {
        List<Book> books = library.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("There are no books yet");
        }
        for (Book book2 : books) {
            System.out.println(book2.toPrettyString());
            if (books.size() > 1 && book2 != books.get(books.size() - 1)) {
                System.out.println();
            }
        }
    }

    private static void addBook(Scanner scanner) {
        System.out.println("Enter a title");
        String title = prompt("Title", scanner);
        System.out.println("Enter a author");
        String author = prompt("Author", scanner);
        Book book = new Book(title, author);
        library.addBook(book);
        LibraryStore.saveLibrary(library);
        System.out.println("Added book:");
        System.out.println(book.toPrettyString());
    }

    private static String prompt(String customPrompt, Scanner scanner) {
        String prompt = customPrompt + "> ";
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static String prompt(Scanner scanner) {
        return prompt("Library", scanner);
    }
}
