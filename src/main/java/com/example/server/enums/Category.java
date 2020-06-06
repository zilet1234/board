package com.example.server.enums;

public enum Category {
    KOREA("korea"),
    JAPANESE("japanese"),
    CHINESE("chinese"),
    ASIAN("asian"),
    ITALIAN("italian"),
    MEXICAN("mexican"),
    BURGERS("burgers"),
    PIZZA("pizza"),
    CAFEDESSERT("cafe&dessert"),
    BAR("bar"),
    OTHERS("others")
    ;

    private String value;
    Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
