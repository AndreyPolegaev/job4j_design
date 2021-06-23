package ru.job4j.email;

import java.util.*;

public class Start1 {

    Map<String, Set<String>> users = new LinkedHashMap<>();
    Scanner in = new Scanner(System.in);

    private void addUser() {
        boolean condition = true;
        while (condition) {
            System.out.println("Введите пользователя - 0 для выхода / показать результат");
            String name = in.nextLine();
            if (name.equals("0")) {
                condition = false;
                in.close();
                merge(users);
            } else {
                addEmails(name);
            }
        }
    }

    private void addEmails(String name) {
        Set<String> set = new HashSet<>();
        while (true) {
            System.out.println("Введите email и 0 для создания нового пользвателя");
            String em = in.nextLine();
            if (em.equals("0")) {
                users.put(name, set);
                break;
            }
            set.add(em);
        }
    }

    public Map<String, Set<String>> merge(Map<String, Set<String>> map) {
        Map<String, Set<String>> ultimate = new LinkedHashMap<>();
        Set<String> merge;
        for (Map.Entry<String, Set<String>> temp : map.entrySet()) {
            merge = new TreeSet<>();
            merge.addAll(temp.getValue());
            for (Map.Entry<String, Set<String>> temp2 : map.entrySet()) {
                if (!Collections.disjoint(merge, temp2.getValue())) {
                    merge.addAll(temp2.getValue());
                }
            }
            boolean b = check(ultimate, temp.getValue());
            if (b) {
                ultimate.put(temp.getKey(), merge);
            }
        }
        ultimate.forEach((k, v) -> {
            System.out.println(k);
            v.forEach(System.out::println);
            System.out.println();
        });
        return ultimate;
    }

    private boolean check(Map<String, Set<String>> map, Set<String> set) {
        for (var temp : map.values()) {
            if (!Collections.disjoint(temp, set)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new Start1().addUser();
    }
}


