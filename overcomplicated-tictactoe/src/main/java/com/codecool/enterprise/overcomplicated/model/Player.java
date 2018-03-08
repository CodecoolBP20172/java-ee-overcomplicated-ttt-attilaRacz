package com.codecool.enterprise.overcomplicated.model;

import org.springframework.stereotype.Component;

@Component
public class Player {
    private String userName = "Anonymous";
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
