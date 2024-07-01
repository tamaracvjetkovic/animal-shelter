package domain.serializeddata;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import domain.enums.MessageOwner;
import domain.enums.RequestState;
import domain.enums.RequestType;
import domain.enums.UserState;
import domain.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

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
        Request r = new Request(generateId(),state,type, user.getId(), post.getId(), null, null);
        addRequest(r);
        return r;
    }
    public void addApproved(Request req, User user){
        req.addApproved(user.getId());
    }
    public void decreaseApproved(Request req, User user){
        req.removeApproved(user.getId());
    }
    public void addRejected(Request req, User user){
        req.addRejected(user.getId());
    }
    public void decreaseRejected(Request req, User user){
        req.removeRejected(user.getId());
    }


    public void updateVolunteeringRequests() {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime threeDaysAgo = currentDate.minusDays(3);

        for (Request request : requests) {
            if (request.getType() == RequestType.VOLUNTEERING && request.getState() == RequestState.PENDING && request.getSentAt() != null) {
                if (request.getSentAt().isBefore(threeDaysAgo)) {
                    if (request.getRejected().size() > request.getApproved().size() || request.getApproved().size() < 3) {
                        requestRejected(request);
                    } else {
                        request.setState(RequestState.APPROVED);
                        User user = UsersList.getInstance().getById(request.getUserId());
                        if(user == null){System.out.println("User je null u updateVolunteeringRequests"); return;}
                        user.setUserState(UserState.VOLUNTEER);     //update member to volunteer
                    }
                }
            }
        }
    }
    public void requestRejected(Request req) {
        req.setState(RequestState.REJECTED);            //request rejected
        //send rejection message
        String text = "";
        Post post;
        String animalName;
        Animal animal;
        switch (req.getType()) {
            case ADOPTION:
                post = PostList.getInstance().getById(req.getPostId());
                animal = AnimalList.getInstance().getAnimal(post.getAnimalId());
                animalName = animal.getName();
                text = "Your request for adoption of animal "+animalName+" was denied.";
                break;
            case TEMPORARY_CARE:
                post = PostList.getInstance().getById(req.getPostId());
                animal = AnimalList.getInstance().getAnimal(post.getAnimalId());
                animalName = animal.getName();
                text = "Your request for temporary care of animal "+animalName+" was denied.";
                break;
            case VOLUNTEERING:
                text = "Your request for volunteering was denied.";
                break;
            case ANIMAL_REGISTRATION:
                text = "Your request for registration of animal was denied.";
                break;
            case POST_EDITING:
                post = PostList.getInstance().getById(req.getPostId());
                animal = AnimalList.getInstance().getAnimal(post.getAnimalId());
                animalName = animal.getName();
                text = "Your request for editing post of animal "+animalName+" was denied.";
                break;
            default:
                System.out.println("Nije dobar tip zahteva u requestRejected.");
        }
        Message newMessage = new Message(text, MessageOwner.ANIMALSHELTER, req.getUserId());
        MessagesList.getInstance().addMessage(newMessage);
    }
    public ArrayList<Request> getPendingByUser(User user){
        ArrayList<Request> reqs = new ArrayList<Request>();
        for (Request request : requests) {
            if (request.getState() == RequestState.PENDING && request.getUserId().equals(user.getId())) {
                reqs.add(request);
            }
        }
        return reqs;
    }
    public ArrayList<Request> getPendingForVolunteer(User user){
        if(user.getUserState() != UserState.VOLUNTEER) {
            return null;
        }
        ArrayList<Request> reqs = new ArrayList<Request>();
        for (Request request : requests) {
            if (request.getState() == RequestState.PENDING && !hasUserVoted(request,user)) {        // if request is pending and volunteer hasn't voted yet add it to list
                reqs.add(request);
            }
        }
        return reqs;
    }
    public boolean hasUserVoted(Request req,User user) {
        return req.getApproved().contains(user.getId()) || req.getRejected().contains(user.getId());
    }

    public Request getRequestById(int id) {
        for (Request request : requests) {
            if (request.getId() == id) {
                return request;
            }
        }

        return null;
    }

    public Request deleteRequest(int id) {
        Request request = getRequestById(id);

        return requests.remove(request) ? request : null;
    }
}