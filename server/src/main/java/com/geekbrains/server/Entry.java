package com.geekbrains.server;

import java.util.Objects;

public class Entry {

    String login;
    String password;

    Entry(String login, String password) {

        this.login = login;
        this.password = password;
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
        return Objects.equals(login, entry.login) && Objects.equals(password, entry.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}

