package domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import domain.enums.MessageOwner;

import java.time.LocalDateTime;
@XStreamAlias("message")

public class Message {
    private String text;
    private LocalDateTime sentAt;
    private MessageOwner owner;
    private Integer userId;

    public Message(String text, MessageOwner owner, Integer userId) {
        this.text = text;
        this.sentAt = LocalDateTime.now();
        this.owner = owner;
        this.userId = userId;
    }
    public Message(){}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public MessageOwner getOwner() {
        return owner;
    }

    public void setOwner(MessageOwner owner) {
        this.owner = owner;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "text=" + text +
                ", sentAt=" + sentAt +
                ", owner=" + owner +
                ", userId=" + userId +
                '}';
    }
}
