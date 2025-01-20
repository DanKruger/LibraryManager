package com.library.data;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * LibraryStore
 */
public class LibraryStore {
    private static String USER;
    private static String FILE_PATH = "library.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String getFilePath() {
        return FILE_PATH;
    }

    /**
     * Save Library to disk
     * 
     * @param library - Library to save
     */
    public static void saveLibrary(Library library) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(library, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load library from disk
     *
     * @return {@link Library}
     */
    public static Library loadLibrary(String user) {
        USER = user;
        Path path = ConfigPathUtil.getDefaultConfigPath("LibraryManager", USER + "_" + FILE_PATH);
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        FILE_PATH = path.toString();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            return gson.fromJson(reader, Library.class);
        } catch (IOException e) {
            return new Library();
        }
    }

    public LibraryStore() {
    }

    public LibraryStore(String user) {
        USER = user;
    }

}
