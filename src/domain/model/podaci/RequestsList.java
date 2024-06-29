package domain.model.podaci;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import domain.enums.RequestState;
import domain.enums.RequestType;
import domain.enums.UserState;
import domain.model.Account;
import domain.model.Post;
import domain.model.Request;
import domain.model.User;

import java.util.ArrayList;
import java.util.Date;

@XStreamAlias("requestsList")
public class RequestsList {

    public static RequestsList instance = null;

    @XStreamAlias("requests")
    private ArrayList<Request> requests;

    private RequestsList() {
        this.requests = new ArrayList<Request>();
    }

    public static RequestsList getInstance() {
        if (instance == null) {
            instance = new RequestsList();
        }

        return instance;
    }

    public int generateId() {
        int numRequests = requests.size();
        return ++numRequests;
    }

    public static void setInstance(RequestsList requestsListst) {
        instance = requestsListst;
    }

    public ArrayList<Request> getRequests() {
        return this.requests;
    }

    public void setRequests(ArrayList<Request> requests) {
        this.requests = requests;
    }

    public void addRequest(Request request) {
        this.requests.add(request);
    }
    public Request createRequest(User user, Post post, RequestState state, RequestType type){
        Request r = new Request(generateId(),state,type, user.getId(), post.getId());
        addRequest(r);
        return r;
    }
    public void increaseApproved(Request req){
        req.increaseApproved();
    }
    public void decreaseApproved(Request req){
        req.decreaseApproved();
    }
    public void increaseRejected(Request req){
        req.increaseRejected();
    }
    public void decreaseRejected(Request req){
        req.decreaseRejected();
    }
}