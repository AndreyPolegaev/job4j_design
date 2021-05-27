package ru.job4j.io;

import java.io.FileOutputStream;

public class MultiplicationTable {
    public static void main(String[] args) {

        try {
            FileOutputStream out = new FileOutputStream("D:\\ne.txt");
            for (int i = 1; i < 10; i++) {
                out.write("\n\n".getBytes());
                for (int j = 1; j < 10; j++) {
                    out.write((i * j + "\t").getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
