package com.testuser.user.payload.auth.respone;

public class MessageRespones {

    private String message ;

    public MessageRespones(String message) {
        this.message = message;
    }

    public MessageRespones() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


