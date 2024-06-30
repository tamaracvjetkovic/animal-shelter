package controller;

import domain.enums.RequestState;
import domain.enums.RequestType;
import domain.model.*;
import domain.serializeddata.RequestsList;

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
}
