package com.example.luigi.spesapp;

/**
 * Created by Giulia on 09/04/2018.
 */

class User {
    String username, email,password;
    Boolean tutorial;

    public User (String username, String email, String password, Boolean tutorial){
        this.username=username;
        this.email=email;
        this.password=password;
        this.tutorial=tutorial;
    }

    public String getUsername() {
        return username;
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
