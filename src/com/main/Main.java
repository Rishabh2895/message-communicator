package com.main;

public class Main {
    public static void main(String[] args) {
        MyMessageQueue messageQueue = new MyMessageQueue(3);

        Thread producerThread = new Thread(() -> {
            try {
                messageQueue.sendMessage(new Message("add", 4));
                messageQueue.sendMessage(new Message("multiply", 1));
                messageQueue.sendMessage(new Message("multiply", 8));
                messageQueue.sendMessage(new Message("add", 2));
                messageQueue.sendMessage(new Message("add", 3));
                messageQueue.sendMessage(new Message("add", 99));
                messageQueue.sendMessage(new Message("multiply", 53));

                // Send the ending message
                messageQueue.sendMessage(new Message("end", 0));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                while (true) {
                    Message message = messageQueue.receiveMessage();
                    if (message.getOperation().equals("end")) {
                        break; // Terminate the program
                    }

                    int result = 0;
                    if (message.getOperation().equals("add")) {
                        result = AddCalculation.add(message.getValue());
                    } else if (message.getOperation().equals("multiply")) {
                        result = MultiplyCalculation.multiply(message.getValue());
                    }

                    System.out.println(message.getOperation() + " " + message.getValue() + " -> " + result);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
