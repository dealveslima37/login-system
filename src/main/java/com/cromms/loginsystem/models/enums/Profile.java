package com.cromms.loginsystem.models.enums;

public enum Profile {

    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String description;

    private Profile(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
