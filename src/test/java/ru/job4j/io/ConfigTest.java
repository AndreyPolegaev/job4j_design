package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"),
                is("org.hibernate.dialect.PostgreSQLDialect"));
    }

    @Test
    public void whenPairWithEmptyLines() {
        String path = "./data/app2.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username"),
                is("postgres"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongPairTemplate() {
        String path = "./data/app3.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.driver_class"),
                is(nullValue()));
    }
}