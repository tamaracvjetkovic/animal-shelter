package domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import domain.enums.RequestState;
import domain.enums.RequestType;

import java.time.LocalDateTime;
import java.util.ArrayList;

@XStreamAlias("request")

public class Request {
    private Integer id;
    private RequestState state;
    private RequestType type;
    private Integer userId;
    private Integer postId;
    private ArrayList<Integer> approved;
    private ArrayList<Integer> rejected;
    private LocalDateTime sentAt;
    private Animal updatedAnimal;
    private String additionalText;

    public Request(Integer id, RequestState state, RequestType type, Integer userId, Integer postId,
                   Animal animal, String additionalText) {
        this.id = id;
        this.state = state;
        this.type = type;
        this.userId = userId;
        this.postId = postId;
        this.approved = new ArrayList<>();
        this.rejected = new ArrayList<>();
        this.sentAt = LocalDateTime.now();
        this.updatedAnimal = animal;
        this.additionalText = additionalText;
    }
    public Request (){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public ArrayList<Integer> getApproved() {
        return approved;
    }

    public void setApproved(ArrayList<Integer> approved) {
        this.approved = approved;
    }

    public ArrayList<Integer> getRejected() {
        return rejected;
    }

    public void setRejected(ArrayList<Integer> rejected) {
        this.rejected = rejected;
    }

    public RequestState getState() {
        return state;
    }

    public void setState(RequestState state) {
        this.state = state;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }
    public void addApproved( Integer userId){
        approved.add(userId);
    }
    public void addRejected(Integer userId){
        rejected.add(userId);
    }
    public void removeApproved(Integer userId){
        approved.remove(userId);
    }
    public void removeRejected(Integer userId){
        rejected.remove(userId);
    }

    public Animal getUpdatedAnimal() {
        return updatedAnimal;
    }

    public void setUpdatedAnimal(Animal updatedAnimal) {
        this.updatedAnimal = updatedAnimal;
    }

    public String getAdditionalText() {
        return additionalText;
    }

    public void setAdditionalText(String additionalText) {
        this.additionalText = additionalText;
    }
}
