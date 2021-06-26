package ru.job4j.filesearch;

/**
 * Программа должна искать данные в заданном каталоге и подкаталогах.
 * Имя файла может задаваться, целиком и по маске
 * Программа должна собираться в jar и запускаться через java -jar find.jar -d=D:\\ -n=*.txt -t=mask -o=D:\\log.txt
 * -d - директория, в которой начинать поиск.
 * -n - имя файла, маска или реглярное выражение
 * -t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
 * -o - результат записать в файл.
 */

import ru.job4j.io.ArgsName;
import java.awt.geom.IllegalPathStateException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Find extends SimpleFileVisitor<Path> {

    /**
     * передаем предикат в зависимости от поиска по плному имени или маске или regex
     */
    private final Predicate<Path> conditions;

    /**
     * сохраняем ссылки на подходящие файлы для последющей записи в файл
     */
    private final List<Path> list = new ArrayList<>();

    public Find(Predicate<Path> conditions) {
        this.conditions = conditions;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (conditions.test(file)) {
            list.add(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return CONTINUE;
    }

    private static Predicate<Path> predicate(String type, String typeName) {
        Predicate<Path> conditions;
        /** типа файла: маска, полное имя или регулярное выражение */
        if (type.equals("mask")) {
            String rsl = typeName.replace("*", ".*").replace("?", ".");
            rsl = rsl
                    .chars()
                    .mapToObj(s -> (char) s)
                    .map(String::valueOf)
                    .collect(Collectors.joining("", "^", "$"));
            Pattern regexp = Pattern.compile(rsl); // получаем регулярное выражение из маски
            conditions = p -> regexp.matcher(p.toFile().getName()).find();
        } else if (type.equals("name")) {
            conditions = p -> p.toFile().getName().equals(typeName);
        } else if (type.equals("regex")) {
            Pattern regexp = Pattern.compile(typeName); // получаем регулярное выражение
            conditions = p -> regexp.matcher(p.toFile().getName()).find();
        } else {
            throw new IllegalArgumentException("wrong key word");
        }
        return conditions;
    }

    private void record(String path) {
        try (PrintWriter out = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8), true)) {
            list.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        /** валидация шаблона параметров и получение параметров */
        ArgsName argsName = ArgsName.of(args);

        /** получаем предикат */
        Predicate<Path> conditions = predicate(argsName.get("t"), argsName.get("n"));

        /** Класс для обхода дерева каталогов, ппринимает предикат и путь для записи данных в файл */
        Find search = new Find(conditions);

        /** валидация директории */
        if (!Files.isDirectory(Path.of(argsName.get("d")))) {
            throw new IllegalPathStateException("NOT A DIRECTORY");
        }
        /** директтория в которой начинать поиск */
        Path path = Paths.get(argsName.get("d"));

        /** запуск обхода дерева */
        Files.walkFileTree(path, search);

        /** запись в файл */
        search.record(argsName.get("o"));
    }
}

