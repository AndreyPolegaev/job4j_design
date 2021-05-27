package ru.job4j.analize;

import java.util.List;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        int changed = 0;
        int added = 0;
        int deleted = 0;
        int rsl = current.size() - previous.size();
        if (rsl > 0) {
            added = rsl;
        } else if (rsl < 0) {
            deleted = Math.abs(current.size() - previous.size());
        }
        for (int i = 0; i < Math.min(current.size(), previous.size()); i++) {
            if (!current.get(i).name.equals(previous.get(i).name)) {
                changed++;
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
