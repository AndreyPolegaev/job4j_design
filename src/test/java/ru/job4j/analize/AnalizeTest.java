package ru.job4j.analize;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AnalizeTest {

    Analize analize = new Analize();

    @Test
    public void whenNoChange() {
        List<Analize.User> listCurrent  = new ArrayList<>(List.of(
                new Analize.User(1, "name1"),
                new Analize.User(2, "name2"),
                new Analize.User(3, "name3")
        ));
        List<Analize.User> listPrevious  = new ArrayList<>(List.of(
                new Analize.User(1, "name1"),
                new Analize.User(2, "name2"),
                new Analize.User(3, "name3")
        ));
        Analize.Info analizeInfo = analize.diff(listPrevious, listCurrent);
        assertThat(analizeInfo.getAdded(), is(0));
        assertThat(analizeInfo.getChanged(), is(0));
        assertThat(analizeInfo.getDeleted(), is(0));
    }

    @Test
    public void whenTwoUserWasDeleted() {
        List<Analize.User> listCurrent  = new ArrayList<>(List.of(
                new Analize.User(1, "name1")
        ));
        List<Analize.User> listPrevious  = new ArrayList<>(List.of(
                new Analize.User(1, "name1"),
                new Analize.User(2, "name2"),
                new Analize.User(3, "name3")
        ));
        Analize.Info analizeInfo = analize.diff(listPrevious, listCurrent);
        assertThat(analizeInfo.getAdded(), is(0));
        assertThat(analizeInfo.getChanged(), is(0));
        assertThat(analizeInfo.getDeleted(), is(2));
    }

    @Test
    public void whenTwoUserAddAndTwoWasChanged() {
        List<Analize.User> listCurrent  = new ArrayList<>(List.of(
                new Analize.User(1, "NAME"),
                new Analize.User(2, "SomeName"),
                new Analize.User(3, "name3"),
                new Analize.User(4, "name4"),
                new Analize.User(5, "name5")
        ));
        List<Analize.User> listPrevious  = new ArrayList<>(List.of(
                new Analize.User(1, "name1"),
                new Analize.User(2, "name2"),
                new Analize.User(3, "name3")
        ));
        Analize.Info analizeInfo = analize.diff(listPrevious, listCurrent);
        assertThat(analizeInfo.getAdded(), is(4));
        assertThat(analizeInfo.getChanged(), is(2));
        assertThat(analizeInfo.getDeleted(), is(2));
    }

    @Test
    public void additionalTest() {
        List<Analize.User> listCurrent  = new ArrayList<>(List.of(
                new Analize.User(5, "Andrey"),
                new Analize.User(7, "Natasha"),
                new Analize.User(0, "Dima"),
                new Analize.User(2, "Nikita")
        ));
        List<Analize.User> listPrevious  = new ArrayList<>(List.of(
                new Analize.User(5, "Andrey"),
                new Analize.User(7, "Vladimir"),
                new Analize.User(0, "Dima"),
                new Analize.User(1, "Olga")
        ));
        Analize.Info analizeInfo = analize.diff(listPrevious, listCurrent);
        assertThat(analizeInfo.getAdded(), is(2));
        assertThat(analizeInfo.getChanged(), is(1));
        assertThat(analizeInfo.getDeleted(), is(2));
    }
}