package controller;

import domain.enums.AnimalState;
import domain.enums.RequestState;
import domain.enums.RequestType;
import domain.enums.UserState;
import domain.model.*;
import domain.serializeddata.AnimalList;
import domain.serializeddata.PostList;
import domain.serializeddata.RequestsList;
import domain.serializeddata.UsersList;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class RequestsController {
    public ArrayList<Request> getPendingRequests() {
        ArrayList<Request> reqs = new ArrayList<Request>();

        for(Request req : RequestsList.getInstance().getRequests()) {
            if(req.getState() == RequestState.PENDING){
                reqs.add(req);
            }
        }

        return reqs;
    }
    private ArrayList<Request> getPendingByUser(User user){
        return RequestsList.getInstance().getPendingByUser(user);
    }

    public void requestToBeVolunteer(User user, String reason) {
        int id = RequestsList.getInstance().generateId();
        RequestsList.getInstance().addRequest(new Request(id, RequestState.PENDING,
                RequestType.VOLUNTEERING, user.getId(), null, null, reason));
    }

    public void requestAnimalAdoption(User user, Post post) {
        RequestsList.getInstance().createRequest(user, post, RequestState.PENDING, RequestType.ADOPTION);
    }

    public void requestAnimalTemporaryCare(User user, Post post) {
        RequestsList.getInstance().createRequest(user, post, RequestState.PENDING, RequestType.TEMPORARY_CARE);
    }

    public void requestPostUpdate(User user, Post post) {
        RequestsList.getInstance().createRequest(user, post, RequestState.PENDING, RequestType.POST_EDITING);
        // AnimalList.getInstance().updateAnimal(newAnimal);
    }

    public void requestPostRegistration(User user, Post post) {
        RequestsList.getInstance().createRequest(user, post, RequestState.PENDING, RequestType.ANIMAL_REGISTRATION);
    }
    public ArrayList<Request> getPendingForVolunteer(User user){
        return RequestsList.getInstance().getPendingForVolunteer(user);
    }

    public void updateVolunteeringRequests() {
       RequestsList.getInstance().updateVolunteeringRequests();
    }

//APPROVING AND REJECTING OF REQUESTS
    public void requestRejected(Request req){
        req.setState(RequestState.REJECTED);            //request rejected
        //TO DO: send message to user that request was denied
    }
    public void adoptionApproved(Request req){
        if(req.getType() != RequestType.ADOPTION){
            System.out.println("Request nije za adoption u adoptionApproved u RequestsController.");
            return;
        }
        req.setState(RequestState.APPROVED);            //request approved
        User user = UsersList.getInstance().getById(req.getUserId());
        if(user==null){
            System.out.println("User je null u adoptionApproved u RequestsController.");
            return;
        }
        user.addPostId(req.getPostId());            //post added to users posts
        Post post = PostList.getInstance().getById(req.getPostId());

        if(post == null){
            System.out.println("Post je null u adoptionApproved u RequestsController.");
            return;
        }
        Animal animal = AnimalList.getInstance().getAnimal(post.getAnimalId());
        if(animal == null){
            System.out.println("Animal je null u adoptionApproved u RequestsController.");
            return;
        }
        animal.setState(AnimalState.ADOPTED);

    }

    public void fosterCareApproved(Request req){
        if(req.getType() != RequestType.TEMPORARY_CARE){
            System.out.println("Request nije za temporary care u fosteCareApproved u RequestsController.");
            return;
        }

        req.setState(RequestState.APPROVED);            //request approved
        User user = UsersList.getInstance().getById(req.getUserId());
        if(user==null){
            System.out.println("User je null u fosteCareApproved u RequestsController.");
            return;
        }

        user.addPostId(req.getPostId());            //post added to users posts
        Post post = PostList.getInstance().getById(req.getPostId());
        if(post == null){
            System.out.println("Post je null u fosteCareApproved u RequestsController.");
            return;
        }

        Animal animal = AnimalList.getInstance().getAnimal(post.getAnimalId());
        if(animal == null){
            System.out.println("Animal je null u fosteCareApproved u RequestsController.");
            return;
        }
        animal.setState(AnimalState.INFOSTERCARE);

    }
}
