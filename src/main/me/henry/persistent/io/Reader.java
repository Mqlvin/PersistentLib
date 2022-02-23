package main.me.henry.persistent.io;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Reader {
    public static String readString(File path) {
        if(path.exists()) {
            try {
                return String.join("", new ArrayList<>(Files.readAllLines(Paths.get(path.toString()))));
            } catch(Exception e) {
                System.out.println("Error: " + e);
            }
        }
        return null;
    }

    public static ArrayList<String> readList(File path) {
        if(path.exists()) {
            try {
                return new ArrayList<>(Files.readAllLines(Paths.get(path.toString())));
            } catch(Exception e) {
                System.out.println("Error: " + e);
            }
        }
        return null;
    }
}
