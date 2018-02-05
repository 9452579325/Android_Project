package com.example.dream.logindemo;

/**
 * Created by dream on 18-Jun-17.
 */

public class MessageData {

    int id;
    String from="";
    String to="";
    String time="";
    String message="";

    public MessageData() {
    }

    public MessageData(int id, String from, String to, String time, String message) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.time = time;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
