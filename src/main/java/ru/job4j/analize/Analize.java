package ru.job4j.analize;

import java.util.List;
import java.util.Objects;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        int changed = 0;
        int added = 0;
        int deleted = 0;
        for (User temp : current) {
            if (!previous.contains(temp)) {
               added++;
            }
        }
        for (User temp : previous) {
            if (!current.contains(temp)) {
                deleted++;
            }
        }
        for (User temp : previous) {
            for (User temp2 : current) {
                if (temp2.id == temp.id && !temp2.name.equals(temp.name)) {
                    changed++;
                }
            }
        }
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
