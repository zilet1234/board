package com.example.server.domain;

public class Member {
    public int id;
    public String name;
    public String email;
    public String password;

    public Member(){

    }

    public Member(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

}
