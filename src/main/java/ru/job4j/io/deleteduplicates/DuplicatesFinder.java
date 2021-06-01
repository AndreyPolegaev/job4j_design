package ru.job4j.io.deleteduplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class DuplicatesFinder {

    public static void main(String[] args) throws IOException {
        System.out.println("Enter directory, for example  - D:\\dir");
        Scanner in = new Scanner(System.in);
        DuplicatesVisitor dv = new DuplicatesVisitor();
        Files.walkFileTree(Paths.get(in.nextLine()), dv);
        List<FileProperty> list = dv.getResult();
        list.forEach(dup -> System.out.printf("Name: %s, Size: %s%n", dup.getName(), dup.getSize()));
    }
}
