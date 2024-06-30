package domain.serializeddata;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import domain.enums.MessageOwner;
import domain.model.Account;
import domain.model.Breed;
import domain.model.Message;
import domain.model.User;

import java.util.ArrayList;
import java.util.Comparator;

@XStreamAlias("messagesList")

public class MessagesList {
    public static MessagesList instance = null;

    @XStreamAlias("messages")
    private ArrayList<Message> messages;

    private MessagesList() {
        this.messages = new ArrayList<Message>();
    }

    public static MessagesList getInstance() {
        if (instance == null) {
            instance = new MessagesList();
        }

        return instance;
    }

    public Integer generateId() {
        int numMessages = messages.size();
        return numMessages + 1;
    }

    public static void setInstance(MessagesList messagesList) {
        instance = messagesList;
    }

    public ArrayList<Message> getMessages() {
        return this.messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public Message addMessage(Message message) {
        this.messages.add(message);
        return message;
    }
    public ArrayList<Message> getByUser(User user) {
        ArrayList<Message> res = new ArrayList<>();
        for (Message message : messages) {
            if (message.getUserId().equals(user.getId())) {
                res.add(message);
            }
        }
        res.sort(Comparator.comparing(Message::getSentAt));
        System.out.println("Soritrane poruke za korisnika");
        for (Message message : res) {
            System.out.println(message.getSentAt());
        }
        return res;
    }
    public ArrayList<Message> getAll() {
        ArrayList<Message> res = new ArrayList<>();
        res = messages;
        res.sort(Comparator.comparing(Message::getSentAt));
        System.out.println("Soritrane poruke za korisnika");
        for (Message message : res) {
            System.out.println(message.getSentAt());
        }
        return res;
    }
    public String messageFrom(Message message){
        if(message.getOwner() == MessageOwner.ANIMALSHELTER){
            return "shelter";
        }
        User user = UsersList.getInstance().getById(message.getUserId());
        Account acc = AccountsList.getInstance().getById(user.getAccountId());
        return acc.getUsername();
    }
    public String messageTo(Message message){
        if(message.getOwner() == MessageOwner.MEMBER){
            return "shelter";
        }
        User user = UsersList.getInstance().getById(message.getUserId());
        Account acc = AccountsList.getInstance().getById(user.getAccountId());
        return acc.getUsername();
    }

}
