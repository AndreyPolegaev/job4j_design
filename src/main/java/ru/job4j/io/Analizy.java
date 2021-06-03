package ru.job4j.io;

/**
 * 1. Реализуйте метод unavailable(). source - путь к файлу лога. target - имя путь к файлу результата анализа.
 * 2. Метод anavailable должен находить диапазоны, когда сервер не работал.
 * Сервер не работал, если status = 400 или 500. Диапазоном считается последовательность статусов 400 и 500
 * <p>
 * 10:57:01;10:59:01;
 * 11:01:02;11:02:02;
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Analizy {

    public void unavailable(String source, String target) {
        try (BufferedReader bf = Files.newBufferedReader(Paths.get(source));
             PrintWriter pr = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            String str;
            boolean isActive = true;
            for (str = bf.readLine(); str != null; str = bf.readLine()) {
                if (str.startsWith("400") || str.startsWith("500")) {
                    if (isActive) {
                        pr.print(str.split(" ")[1] + ";");
                        isActive = false;
                    }
                }
                if (!str.startsWith("400") && !str.startsWith("500")) {
                    if (!isActive) {
                        pr.print(str.split(" ")[1] + ";");
                        pr.print(System.lineSeparator());
                        isActive = true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Analizy().unavailable("source.csv", "target.csv");
    }
}



