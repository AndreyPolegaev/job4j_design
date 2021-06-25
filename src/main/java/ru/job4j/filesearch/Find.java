package ru.job4j.filesearch;

import ru.job4j.io.ArgsName;
import java.awt.geom.IllegalPathStateException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Find {
    public static void main(String[] args) throws IOException {

        /** валидация шаблона параметров и получение параметров */
        ArgsName argsName = ArgsName.of(args);
        Predicate<Path> conditions;

        /** типа файла: маска, полное имя или регулярное выражение */
        if (argsName.get("t").equals("mask")) {
            conditions = p -> p.toFile().getName().endsWith(argsName.get("n").split("\\.")[1]);
        } else if (argsName.get("t").equals("name")) {
            conditions = p -> p.toFile().getName().equals(argsName.get("n"));
        } else if (argsName.get("t").equals("regex")) {
            Pattern regexp = Pattern.compile(argsName.get("n")); // получаем регулярное выражение
            conditions = p -> regexp.matcher(p.toFile().getName()).find();
        } else {
            throw new IllegalArgumentException("wrong key word");
        }

        /** Класс для обхода дерева каталогов, ппринимает предикат и путь для записи данных в файл */
        Search search = new Search(conditions, argsName.get("o"));

        /** валидация директории */
        if (!Files.isDirectory(Path.of(argsName.get("d")))) {
            throw new IllegalPathStateException("NOT A DIRECTORY");
        }
        /** директтория в которой начинать поиск */
        Path path = Paths.get(argsName.get("d"));

        /** запуск обхода дерева */
        Files.walkFileTree(path, search);

        /** запуск записи в файл */
        search.getResult();
    }
}
