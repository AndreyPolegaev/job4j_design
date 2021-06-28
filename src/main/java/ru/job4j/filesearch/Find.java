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
import java.io.IOException;
import java.nio.file.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Find {

    private static Predicate<Path> predicate(String type, String typeName) {

        Predicate<Path> conditions;
        /** типа файла: маска, полное имя или регулярное выражение */
        if (type.equals("mask")) {
            String rsl = typeName.replace("*", ".*").replace("?", ".");
            rsl = String.join("", "^", rsl, "$");
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

    public static void main(String[] args) throws IOException {

        /** валидация шаблона параметров и получение параметров */
        ArgsName argsName = ArgsName.of(args);

        /** валидация директории */
        if (!Files.isDirectory(Path.of(argsName.get("d")))) {
            throw new IllegalPathStateException("NOT A DIRECTORY");
        }

        /** получаем предикат */
        Predicate<Path> conditions = predicate(argsName.get("t"), argsName.get("n"));

        /** Класс для обхода дерева каталогов, ппринимает предикат и путь для записи данных в файл */
        Search search = new Search(conditions, argsName.get("o"));

        /** директтория в которой начинать поиск */
        Path path = Paths.get(argsName.get("d"));

        /** запуск обхода дерева */
        Files.walkFileTree(path, search);

        /** запись в файл */
        search.getResult();
    }
}

