package ru.job4j.analize;

import java.util.*;
import java.util.stream.Collectors;

public class Analize {

    private final Map<Integer, User> users = new HashMap<>();

    public Info diff(List<User> previous, List<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        current.forEach(user -> users.putIfAbsent(user.id, user));
        for (User temp : previous) {
            if (users.get(temp.id) != null && !users.get(temp.id).name.equals(temp.name)) {
                changed++;
            } else if (users.get(temp.id) == null) {
                deleted++;
            }
        }
        users.keySet().removeAll(previous.stream()
                                         .map(x -> x.id)
                                         .collect(Collectors.toSet()));
        added = users.size();
        return new Info(added, changed, deleted);
    }

    public static class User {
        private final int id;
        private final String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id
                    && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {

        private final int added;
        private final int changed;
        private final int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }
    }
}
