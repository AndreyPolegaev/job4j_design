package ru.job4j.io.walk;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import static org.junit.Assert.*;

public class SearchTest {

    @Test
    public void testSearch() throws IOException {
        List<Path> list = Search.search(Paths.get("./datasearch"),
                path -> path.getFileName().toString().endsWith(".js"));
                Iterator<Path> it = list.iterator();
        assertThat(it.next(), is(Paths.get("./datasearch/a.js").toAbsolutePath()));
        assertThat(it.next(), is(Paths.get("./datasearch/b.js").toAbsolutePath()));
    }

    @Test
    public void testSearch2() throws IOException {
        List<Path> list = Search.search(Paths.get("./datasearch"),
                path -> path.toFile().getName().endsWith(".txt"));
        Iterator<Path> it = list.iterator();
        assertThat(it.next(), is(Paths.get("./datasearch/first.txt").toAbsolutePath()));
        assertThat(it.next(), is(Paths.get("./datasearch/second.txt").toAbsolutePath()));
    }
}