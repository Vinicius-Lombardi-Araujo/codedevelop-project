package com.vinicius.codedevelop_project_api.domain.enums;

public enum UserType {
    ADMIN("ADMIN"),
    USER("USER");

    private String type;
    UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
