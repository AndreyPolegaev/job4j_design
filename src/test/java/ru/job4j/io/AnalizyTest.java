package ru.job4j.io;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.*;
import java.nio.file.Paths;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void analyzeTest() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        new Analizy().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder sb = new StringBuilder();
        String ln = System.lineSeparator();
        try (BufferedReader buf = new BufferedReader(new FileReader(target))) {
            buf.lines().forEach(x -> sb.append(x).append(ln));
        }
        assertThat(sb.toString(), is(
                   "10:57:01;10:59:01;"
                        + ln
                        + "11:01:02;11:02:02;"
                        + ln
        ));
    }

    @Test
    public void analyzeTest2() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("400 11:01:02");
            out.println("200 11:02:02");
        }
        new Analizy().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder sb = new StringBuilder();
        String ln = System.lineSeparator();
        try (BufferedReader buf = new BufferedReader(new FileReader(target))) {
            buf.lines().forEach(x -> sb.append(x).append(ln));
        }
        assertThat(sb.toString(), is(
                "10:57:01;11:02:02;"
                        + ln
        ));
    }
}