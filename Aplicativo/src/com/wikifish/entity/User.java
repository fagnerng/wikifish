package com.wikifish.entity;

public class User {
    private String name;
    private Aquarium setUp;
    private String email;
    private String password;

    public User(String name, String email) {
        super();
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Aquarium getSetUp() {
        return setUp;
    }

    public void setSetUp(Aquarium setUp) {
        this.setUp = setUp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        if (!isValidEmail(email))
            throw new Exception("Email invalid");
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {
        if (!isValidPassword(password))
            throw new Exception("Password invalid");
        this.password = password;
    }

    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    private boolean isValidPassword(String password) {
        return password.length() > 4;
    }
}
