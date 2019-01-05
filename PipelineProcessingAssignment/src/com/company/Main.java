package com.company;

import java.io.File;
import java.util.List;
import java.util.zip.ZipEntry;

public class Main {
    public static void main(String[] args) {
        List<File> searchRes = RegularFilesSearchUtil.searchDirectoryByFileName("/home/lofi/", "Rack");
        System.out.println(searchRes);

        List<ZipEntry> zipSearchRes = ZipArchivesSearchUtil.searchZipArchiveByFileContent("/home/lofi/zipSearchTest.zip", "text");
        System.out.println(zipSearchRes);

        List<ZipEntry> zipContentSearchRes = ZipArchivesSearchUtil.searchZipArchiveByFileContent("/home/lofi/jarSearchTest.jar", "text");
        System.out.println(zipContentSearchRes);

        List<File> zipContentSearchRes2 = RegularFilesSearchUtil.searchDirectoryByFileContent("/home/lofi", "<html>");
        System.out.println(zipContentSearchRes2);
    }
}
