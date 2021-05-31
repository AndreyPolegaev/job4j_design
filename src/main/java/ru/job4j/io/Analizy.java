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
import java.util.List;
import java.util.stream.Collectors;

public class Analizy {

    public void unavailable(String source, String target) {
        try (
                BufferedReader bf = Files.newBufferedReader(Paths.get(source));
                PrintWriter pr = new PrintWriter(new FileOutputStream(target))
        ) {
            List<String> list = bf.lines().collect(Collectors.toList()); // Сборка строк в лист
            int index = 0;
            StringBuilder sb = new StringBuilder();
            for (int i = index; i < list.size(); i++) {
                if (list.get(i).startsWith("400") || list.get(i).startsWith("500")) {
                    index = i;
                    String currentString; // текущая строка
                    do {
                        currentString = list.get(index++);
                        sb
                          .append(currentString.split(" ")[1])
                          .append(";");
                    } while (currentString.startsWith("400") || currentString.startsWith("500"));
                    i = --index;
                    String[] forWrite = sb.toString().split(";");
                    pr.println(forWrite[0] + ";" + forWrite[forWrite.length - 1] + ";");  // запись в файл
                    sb.delete(0, sb.length());
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



