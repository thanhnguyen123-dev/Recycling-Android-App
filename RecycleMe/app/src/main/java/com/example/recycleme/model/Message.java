package com.example.recycleme.model;

/**
 * An entity class for a Message object
 * A Message has the following attributes:
 * - String senderId: the user ID who sends the message
 * - long sendTime: the time when the message is sent
 * - String sendMessage: the content of the message
 * @author Le Thanh Nguyen - u7594144
 */
public class Message {
    private String senderId;
    private long sendTime;
    private String sendMessage;

    public Message() {
    }
    public Message(String senderId, long sendTime, String sendMessage) {
        this.senderId = senderId;
        this.sendTime = sendTime;
        this.sendMessage = sendMessage;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(String sendMessage) {
        this.sendMessage = sendMessage;
    }
}
