package domain.model;

import domain.enums.RequestState;
import domain.enums.RequestType;

public class Request {
    private Integer id;
    private RequestState state;
    private RequestType type;
    private Integer userId;
    private Integer postId;
    private Integer approved;
    private Integer rejected;

    public Request(Integer id,RequestState state,RequestType type, Integer userId, Integer postId) {
        this.state = state;
        this.type = type;
        this.userId = userId;
        this.postId = postId;
        this.approved = 0;
        this.rejected = 0;
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

    public Integer getApproved() {
        return approved;
    }

    public void setApproved(Integer approved) {
        this.approved = approved;
    }
    public Integer getRejected() {
        return rejected;
    }

    public void setRejected(Integer rejected) {
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

    public void setPostId(Integer postId) {
        this.postId = postId;
    }
    public void increaseApproved(){
        approved++;
    }
    public void increaseRejected(){
        rejected++;
    }
    public void decreaseApproved(){
        approved--;
    }
    public void decreaseRejected(){
        rejected--;
    }


}
