package ru.job4j.io;
/**
 * Метод load() - должен считать все ключи в карту values. (пары разделены через = )
 * Важно в файле могут быть пустые строки и комментарии их нужно пропускать.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;

    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(x -> {
                if (!x.startsWith("#") && !x.isEmpty()) {
                    String[] str = x.split("=");
                    if (str.length < 2) {
                        throw new IllegalArgumentException("некорректный файл");
                    }
                    values.put(str[0], str[1]);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("./data/app.properties"));
    }
}
