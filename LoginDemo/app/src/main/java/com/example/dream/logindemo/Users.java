package com.example.dream.logindemo;

/**
 * Created by dream on 15-Jun-17.
 */

public class Users {

    String name="";
    String username="";
    String password="";
    String image="";
    String token="";

    public Users() {
    }

    public Users(String name, String username, String password, String image, String token) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.image = image;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

