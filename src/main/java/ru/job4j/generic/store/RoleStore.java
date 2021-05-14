package ru.job4j.generic.store;

public class RoleStore {
    public static void main(String[] args) {
        UserStore userStore = new UserStore();
        userStore.add(new User("1"));
        userStore.add(new User("2"));
        userStore.replace("1", new User("3"));
        User user = userStore.findById("3");
        System.out.println(user.getId());
    }
}
