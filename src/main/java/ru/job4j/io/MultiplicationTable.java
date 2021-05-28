package ru.job4j.io;

import java.io.FileOutputStream;

public class MultiplicationTable {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("D:\\ne.txt")) {
            String ln = System.lineSeparator();
            for (int i = 1; i < 10; i++) {
                for (int j = 1; j < 10; j++) {
                    out.write((i * j + "\t").getBytes());
                }
                out.write((ln + ln).getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
