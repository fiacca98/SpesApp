package com.example.luigi.spesapp;

/**
 * Created by Giulia on 09/04/2018.
 */

class User {
    String username,name,email,password;
    Boolean tutorial;

    public User (String username, String name, String email, String password, Boolean tutorial){
        this.username=username;
        this.email=email;
        this.password=password;
        this.tutorial=tutorial;
        this.name=name;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getTutorial() {
        return tutorial;
    }
}
