package ru.job4j.io.deleteduplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.nio.file.FileVisitResult.CONTINUE;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, Boolean> map = new HashMap<>();

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return super.preVisitDirectory(dir, attrs);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(),
                file.toFile().getName());
        map.computeIfPresent(fileProperty, (key, value) -> value = true);
        map.putIfAbsent(fileProperty, false);
        return super.visitFile(file, attrs);
    }

    public List<FileProperty> getResult() {
        if (map.containsValue(true)) {
            System.out.println("There are duplicates");
        } else {
            System.out.println("No duplicates");
        }
        return map.entrySet()
                .stream()
                .filter(e -> !e.getValue().equals(false))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return super.postVisitDirectory(dir, exc);
    }
}
