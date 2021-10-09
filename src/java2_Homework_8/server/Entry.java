package java2_Homework_8.server;

import java.util.Objects;

public class Entry {
    String name;
    String login;
    String password;

    Entry(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    String getName() {
        return name;
    }

    String getLogin() {
        return login;
    }

    String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return Objects.equals(name, entry.name) && Objects.equals(login, entry.login) && Objects.equals(password, entry.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, login, password);
    }
}
