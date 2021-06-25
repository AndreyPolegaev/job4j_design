package ru.job4j.filesearch;

/**
 * Программа должна искать данные в заданном каталоге и подкаталогах.
 * Имя файла может задаваться, целиком и по маске
 * Программа должна собираться в jar и запускаться через java -jar find.jar -d=c:/ -n=*.txt -t=mask -o=log.txt
 * -d - директория, в которой начинать поиск.
 * -n - имя файла, маска или реглярное выражение
 * -t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
 * -o - результат записать в файл.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Search implements FileVisitor<Path> {

    /** передаем предикат в зависимости от поиска по плному имени или маске или regex */
    private final Predicate<Path> conditions;

   /** сохраняем ссылки на подходящие файлы для последющей записи в файл */
    private final List<Path> list = new ArrayList<>();

    /** путь для записи результата в файл в файл */
    private final String path;

    public Search(Predicate<Path> conditions, String path) {
        this.conditions = conditions;
        this.path = path;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    /** Если выражение предиката true добавляем в лист пути к файлам */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (conditions.test(file)) {
            list.add(file.toAbsolutePath());
        }
        return FileVisitResult.CONTINUE;
    }

    /** Запись в файл */
    public void getResult() {
        try (PrintWriter out = new PrintWriter(new FileWriter(this.path, StandardCharsets.UTF_8), true)) {
            list.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}
