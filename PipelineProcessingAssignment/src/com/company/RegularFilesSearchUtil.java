package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegularFilesSearchUtil {

    public static List<File> searchDirectoryByFileName(String dirName, String searchQuery) {
        try {
            File directory = new File(dirName);
            File[] result = directory.listFiles((dir1, name) -> name.contains(searchQuery));

            if (result.length == 0) {
                System.out.println("Nothing was found");
                return new ArrayList<>();
            } else {
                return Arrays.asList(result);
            }
        } catch (NullPointerException npe) {
            System.out.println("Invalid directory name");
            return new ArrayList<>();
        }
    }

    public static List<File> searchDirectoryByFileContent(String dirName, String searchQuery) {
        List<File> result = new ArrayList<>();
        try {
            File directory = new File(dirName);
            File[] files = directory.listFiles();
            for (File f: files) {
                if (!f.isDirectory() && !f.isHidden()) {
                    BufferedReader reader = new BufferedReader(new FileReader(f));
                    List<String> lineMatches = new ArrayList<>();
                    reader.lines()
                            .filter(line -> line.contains(searchQuery))
                            .forEach(line -> lineMatches.add(line));
                    if (lineMatches.size() > 0) {
                        result.add(f);
                    }
                    reader.close();
                }
            }
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }



}
