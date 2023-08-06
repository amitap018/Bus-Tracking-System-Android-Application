package com.amit018.maps2;

public class users {
    String email, pass, lastMessage;

    public users(String email, String pass, String lastMessage) {
        this.email = email;
        this.pass = pass;
        this.lastMessage = lastMessage;
    }

    public users(){}

    public users(String email, String pass) {
        this.email = email;
        this.pass = pass;
        this.lastMessage = lastMessage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
