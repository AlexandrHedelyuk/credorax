package com.credorax.enums;

public enum Topic {

    AUDIT("audit");

    private String name;

    Topic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
