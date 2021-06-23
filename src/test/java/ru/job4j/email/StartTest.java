package ru.job4j.email;

import org.hamcrest.core.Is;
import org.junit.Test;
import java.util.*;
import org.junit.Assert;

public class StartTest {

    @Test
    public void testUsersJob4j() {
        Map<String, Set<String>> users = new LinkedHashMap<>();
        users.put("user1", Set.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"));
        users.put("user2", Set.of("foo@gmail.com", "ups@pisem.net"));
        users.put("user3", Set.of("xyz@pisem.net", "vasya@pupkin.com"));
        users.put("user4", Set.of("ups@pisem.net", "aaa@bbb.ru"));
        users.put("user5", Set.of("xyz@pisem.net"));
        Map<String, Set<String>> expected = new LinkedHashMap<>(Map.of(
                "user1", Set.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru", "ups@pisem.net", "aaa@bbb.ru"),
                "user3", Set.of("xyz@pisem.net", "vasya@pupkin.com")
        ));
        Map<String, Set<String>> output = new Start1().merge(users);
        Assert.assertThat(output, Is.is(expected));
    }

    @Test
    public void test1() {
        Map<String, Set<String>> users = new LinkedHashMap<>();
        users.put("Andrey", Set.of("e1", "e2"));
        users.put("Nikolay", Set.of("qwe", "123"));
        users.put("Masha", Set.of("e2", "e3", "e4"));
        Map<String, Set<String>> expected = new LinkedHashMap<>();
        expected.put("Andrey", Set.of("e1", "e2", "e3", "e4"));
        expected.put("Nikolay", Set.of("qwe", "123"));
        Map<String, Set<String>> output = new Start1().merge(users);
        Assert.assertThat(output, Is.is(expected));
    }
}