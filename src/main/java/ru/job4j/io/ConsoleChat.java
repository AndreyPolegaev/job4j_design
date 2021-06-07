package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {

    private final String path;
    private final String pathAnswers;
    private String[] answers = new String[0];
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String pathAnswers) {
        this.path = path;
        this.pathAnswers = pathAnswers;
    }

    public void run() {
        List<String> answers = new ArrayList<>();
        String str = "";
        try (Scanner in = new Scanner(System.in)) {
            while (!str.equals(OUT)) {
                str = in.nextLine();
                answers.add(str);
                if (str.equals(OUT)) {
                    continue;
                }
                if (str.equals(STOP)) {
                    while (!str.equals(CONTINUE)) {
                        str = in.nextLine();
                        answers.add(str);
                        if (str.equals(OUT)) {
                            break;
                        }
                    }
                }
                if (!str.equals(OUT)) {
                    answers.add(answer());
                }
            }
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(
                    new FileOutputStream(path), StandardCharsets.UTF_8));
            answers.forEach(pw::println);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String answer() throws IOException {
        if (answers.length == 0) {
            answers = Files.lines(Path.of(pathAnswers), StandardCharsets.UTF_8).toArray(String[]::new);
        }
        String botAnswers = answers[new Random().nextInt(answers.length)];
        System.out.println(botAnswers);
        return botAnswers;
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("dil.txt", "answers.txt");
        cc.run();
    }
}