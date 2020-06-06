package com.example.server.domain;

import java.time.LocalDateTime;

public class Review {
    public int id;
    public String name;
    public int category;
    public String place;
    public String content;
    public int rating;
    public LocalDateTime time;
    public int view;

    public Review(){

    }


    public Review(int id,
            String name,
            int category,
            String place,
            String content,
            int rating,
            LocalDateTime time,
            int view
    ) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.place = place;
        this.content = content;
        this.rating = rating;
        this.time = time;
        this.view = view;
    }
}
