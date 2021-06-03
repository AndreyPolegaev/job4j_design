package ru.job4j.io.walkddone;

import ru.job4j.io.ArgsName;
import ru.job4j.io.walk.Search;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        sources.forEach(x -> packSingleFile(x.toFile().getAbsoluteFile(), target));
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        Predicate<Path> condition = p -> !p.toFile().getName().endsWith(argsName.get("e"));
        List<Path> list = Search.search(Paths.get(argsName.get("d")), condition);
    //    list.forEach(x -> System.out.println(x));
        Zip zip = new Zip();
        zip.packFiles(list, new File(argsName.get("o")));
    }
}
