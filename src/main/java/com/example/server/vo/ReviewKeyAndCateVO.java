package com.example.server.vo;

import java.util.List;

public class ReviewKeyAndCateVO {

    String keyword;

    List<Integer> categoriList;

    public ReviewKeyAndCateVO (String keyword, List<Integer> categoriList) {
        this.keyword = keyword;
        this.categoriList = categoriList;
    }
}
