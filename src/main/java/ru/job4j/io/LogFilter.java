package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.lines().forEach(x -> {
                String[] data = x.split(" ");
                if (data[data.length - 2].equals("404")) {
                    list.add(x);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        log.forEach(System.out::println);
    }
}
