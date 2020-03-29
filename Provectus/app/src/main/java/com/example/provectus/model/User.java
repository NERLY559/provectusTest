package com.example.provectus.model;

public class User {
    private String email;
    private String gender;
    private String firstname;
    private String lastname;
    private String medium;
    private String cell;
    private String username;
    private String password;

    public User(String email, String gender, String firstname, String lastname, String medium, String cell, String username, String password) {
        this.email = email;
        this.gender = gender;
        this.firstname = firstname;
        this.lastname = lastname;
        this.medium = medium;
        this.cell = cell;
        this.username = username;
        this.password = password;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMedium() {
        return medium;
    }

    public String getCell() {
        return cell;
    }

    public String getPicture() {
        return medium;
    }

    public void setPicture(String picture) {
        this.medium = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
