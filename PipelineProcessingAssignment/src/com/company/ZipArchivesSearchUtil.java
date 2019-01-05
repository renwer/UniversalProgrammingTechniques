package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipArchivesSearchUtil {

    public static List<ZipEntry> searchZipArchiveByFileName(String fileName, String searchQuery) {

        ZipFile zipFile = null;
        List<ZipEntry> result = new ArrayList<>();
        try {
            zipFile = new ZipFile(fileName);
            result = zipFile.stream()
                    .filter(e -> e.getName().contains(searchQuery))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Error opening archive. Possibly invalid location or broken file.");
        } finally {
            try {
                if (zipFile != null) {
                    zipFile.close();
                }
            } catch (IOException e) {
                System.out.println("Error while closing the archive");
            }
        }
        return result;
    }


    public static List<ZipEntry> searchZipArchiveByFileContent(String fileName, String searchQuery) {

        ZipFile zipFile = null;
        List<ZipEntry> result = new ArrayList<>();
        try {
            zipFile = new ZipFile(fileName);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                InputStream entryStream = zipFile.getInputStream(entry);
                BufferedReader reader = new BufferedReader(new InputStreamReader(entryStream));
                List<String> linesMatched = reader.lines()
                        .filter(l -> l.contains(searchQuery))
                        .collect(Collectors.toList());
                if (linesMatched.size() > 0) {
                    result.add(entry);
                }
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Error opening archive. Possibly invalid location or broken file.");
        } finally {
            try {
                if (zipFile != null) {
                    zipFile.close();
                }
            } catch (IOException e) {
                System.out.println("Error while closing the archive");
            }
        }
        return result;

    }





}
