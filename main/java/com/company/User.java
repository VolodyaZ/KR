package com.company;

public class User {
    public interface RoleKeyWord {
        String ADMIN = "ADMIN";
        String USER = "USER";
    }
    public enum Role {
        ADMIN,
        USER;
    }

    private String name;
    private String login;
    private String email;
    private String password;
    private Role role;

    public User() {
    }

    @Override
    public String toString() {
        return name + ", " +
                login + ", " +
                email + " ," +
                password + ", " +
                role;
    }

    public User(String name, String login, String email, String password, Role role) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
