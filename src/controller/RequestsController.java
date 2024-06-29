package controller;

import domain.enums.RequestState;
import domain.model.*;
import domain.model.podaci.RequestsList;
import domain.serializeddata.AnimalList;
import domain.serializeddata.BreedList;
import domain.serializeddata.PostList;
import dtos.PostDTO;

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
}
