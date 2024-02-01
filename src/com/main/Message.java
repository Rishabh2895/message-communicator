package com.main;

public class Message {
    private String operation;
    private int value;

    public Message(String operation, int value) {
        this.operation = operation;
        this.value = value;
    }

    public String getOperation() {
        return operation;
    }

    public int getValue() {
        return value;
    }
}
