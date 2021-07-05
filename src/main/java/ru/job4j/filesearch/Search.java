package ru.job4j.filesearch;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import static java.nio.file.FileVisitResult.CONTINUE;

public class Search extends SimpleFileVisitor<Path> {

    /**
     * передаем предикат в зависимости от поиска по плному имени или маске или regex
     */
    private final Predicate<Path> conditions;

    private final List<Path> list = new ArrayList<>();

    /**
     * путь для запись в файл
     */
    private final String path;

    public Search(Predicate<Path> conditions, String path) {
        this.conditions = conditions;
        this.path = path;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (conditions.test(file)) {
            list.add(file);
        }
        return super.visitFile(file, attrs);
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return CONTINUE;
    }

    public List<Path> getResult() {
        return this.list;
    }
}
