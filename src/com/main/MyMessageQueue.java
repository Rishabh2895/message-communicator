package com.main;

import java.util.LinkedList;
import java.util.Queue;

public class MyMessageQueue {
    private final Queue<Message> messageQ = new LinkedList<>();
    private final int maxCount;
    private int messageCount = 0;

    public MyMessageQueue(int maxCount) {
        this.maxCount = maxCount;
    }

    public synchronized void sendMessage(Message message) throws InterruptedException {
        while (messageCount == maxCount) {
            wait();
        }
        messageQ.offer(message);
        messageCount++;
        if (messageCount == 1) {
            notify();
        }
    }

    public synchronized Message receiveMessage() throws InterruptedException {
        while (messageCount == 0) {
            wait();
        }
        Message message = messageQ.poll();
        messageCount--;
        if (messageCount == maxCount - 1) {
            notify();
        }
        return message;
    }
}
