package com.library.data;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * ConfigPathUtil
 */
public class ConfigPathUtil {

    public static Path getDefaultConfigPath(String appName, String fileName) {
        String os = System.getProperty("os.name").toLowerCase();
        Path configPath;

        if (os.contains("win")) {
            // Windows: Use APPDATA
            String appData = System.getenv("APPDATA");
            if (appData != null) {
                configPath = Paths.get(appData, appName);
            } else {
                throw new IllegalStateException("APPDATA environment variable is not set.");
            }
        } else if (os.contains("mac")) {
            // macOS: Use ~/Library/Application Support
            String userHome = System.getProperty("user.home");
            configPath = Paths.get(userHome, "Library", "Application Support", appName);
        } else {
            // Linux/Unix: Use $XDG_CONFIG_HOME or ~/.config
            String xdgConfigHome = System.getenv("XDG_CONFIG_HOME");
            if (xdgConfigHome != null) {
                configPath = Paths.get(xdgConfigHome, appName);
            } else {
                String userHome = System.getProperty("user.home");
                configPath = Paths.get(userHome, ".config", appName);
            }
        }

        // Ensure the directory path includes the file name
        return configPath.resolve(fileName);
    }

    public static void main(String[] args) {
        // Example usage
        String appName = "LibraryManager";
        String fileName = "data.json";

        try {
            Path configFilePath = getDefaultConfigPath(appName, fileName);
            System.out.println("Config file path: " + configFilePath);

        } catch (IllegalStateException e) {
            System.err.println("Error determining config path: " + e.getMessage());
        }
    }
}
