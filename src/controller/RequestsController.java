package controller;

import domain.enums.*;
import domain.model.*;
import domain.serializeddata.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class RequestsController {
    public ArrayList<Request> getPendingRequests() {
        ArrayList<Request> reqs = new ArrayList<Request>();

        for (Request req : RequestsList.getInstance().getRequests()) {
            if (req.getState() == RequestState.PENDING) {
                reqs.add(req);
            }
        }

        return reqs;
    }

    public ArrayList<Request> getPendingByUser(User user) {
        return RequestsList.getInstance().getPendingByUser(user);
    }

    public ArrayList<Request> getAllByUser(User user) {
        return RequestsList.getInstance().getAllByUser(user);
    }

    public void requestToBeVolunteer(User user, String reason) {
        int id = RequestsList.getInstance().generateId();
        RequestsList.getInstance().addRequest(new Request(id, RequestState.PENDING,
                RequestType.VOLUNTEERING, user.getId(), null, null, reason));
    }

    public void requestAnimalAdoption(User user, Post post, String reason) {
        int id = RequestsList.getInstance().generateId();
        RequestsList.getInstance().addRequest(new Request(id, RequestState.PENDING,
                RequestType.ADOPTION, user.getId(), post.getId(), null, reason));
    }

    public void requestAnimalTemporaryCare(User user, Post post, String reason) {
        int id = RequestsList.getInstance().generateId();
        RequestsList.getInstance().addRequest(new Request(id, RequestState.PENDING,
                RequestType.TEMPORARY_CARE, user.getId(), post.getId(), null, reason));
    }

    public void requestPostUpdate(User user, int postId, Animal newAnimal) {
        int id = RequestsList.getInstance().generateId();
        RequestsList.getInstance().addRequest(new Request(id, RequestState.PENDING,
                RequestType.POST_EDITING, user.getId(), postId, newAnimal, null));
        // AnimalList.getInstance().updateAnimal(newAnimal);
    }

    public void requestPostRegistration(User user, Animal newAnimal, Address address) {
        int id = RequestsList.getInstance().generateId();
        address = AddressList.getInstance().createAddress(address);
        newAnimal.setAddressId(address.getId());
        RequestsList.getInstance().addRequest(new Request(id, RequestState.PENDING,
                RequestType.ANIMAL_REGISTRATION, user.getId(), null, newAnimal, null));
    }

    public ArrayList<Request> getPendingForVolunteer(User user) {
        return RequestsList.getInstance().getPendingForVolunteer(user);
    }

    public void updateVolunteeringRequests() {
        RequestsList.getInstance().updateVolunteeringRequests();
    }

    //APPROVING AND REJECTING OF REQUESTS
    public void requestRejected(Request req) {
        RequestsList.getInstance().requestRejected(req);
    }

    public void adoptionApproved(Request req) {
        if (req.getType() != RequestType.ADOPTION) {
            System.out.println("Request nije za adoption u adoptionApproved u RequestsController.");
            return;
        }
        req.setState(RequestState.APPROVED);            //request approved
        User user = UsersList.getInstance().getById(req.getUserId());
        if (user == null) {
            System.out.println("User je null u adoptionApproved u RequestsController.");
            return;
        }
        user.addPostId(req.getPostId());            //post added to users posts
        Post post = PostList.getInstance().getById(req.getPostId());

        if (post == null) {
            System.out.println("Post je null u adoptionApproved u RequestsController.");
            return;
        }
        Animal animal = AnimalList.getInstance().getAnimal(post.getAnimalId());
        if (animal == null) {
            System.out.println("Animal je null u adoptionApproved u RequestsController.");
            return;
        }
        animal.setState(AnimalState.ADOPTED);
    }

    public void adopt(User user, Post post) {

        user.addPostId(post.getId());            //post added to users posts
        Animal animal = AnimalList.getInstance().getAnimal(post.getAnimalId());
        if (animal == null) {
            System.out.println("Animal je null u adopt u RequestsController.");
            return;
        }
        animal.setState(AnimalState.ADOPTED);
    }

    public void fosterCareApproved(Request req) {
        if (req.getType() != RequestType.TEMPORARY_CARE) {
            System.out.println("Request nije za temporary care u fosteCareApproved u RequestsController.");
            return;
        }

        req.setState(RequestState.APPROVED);            //request approved
        User user = UsersList.getInstance().getById(req.getUserId());
        if (user == null) {
            System.out.println("User je null u fosteCareApproved u RequestsController.");
            return;
        }

        user.addPostId(req.getPostId());            //post added to users posts
        Post post = PostList.getInstance().getById(req.getPostId());
        if (post == null) {
            System.out.println("Post je null u fosteCareApproved u RequestsController.");
            return;
        }

        Animal animal = AnimalList.getInstance().getAnimal(post.getAnimalId());
        if (animal == null) {
            System.out.println("Animal je null u fosteCareApproved u RequestsController.");
            return;
        }
        animal.setState(AnimalState.INFOSTERCARE);

    }

    public void fosterCare(User user, Post post) {
        user.addPostId(post.getId());            //post added to users posts
        Animal animal = AnimalList.getInstance().getAnimal(post.getAnimalId());
        if (animal == null) {
            System.out.println("Animal je null u fosteCare u RequestsController.");
            return;
        }
        animal.setState(AnimalState.INFOSTERCARE);
    }

    public void animalRegistrationApproved(Request req) {
        if (req.getType() != RequestType.ANIMAL_REGISTRATION) {
            System.out.println("Request nije za animal registration u animaRegistrationApproved u RequestsController.");
            return;
        }

        req.setState(RequestState.APPROVED);            //request approved
        User user = UsersList.getInstance().getById(req.getUserId());
        if (user == null) {
            System.out.println("User je null u animaRegistrationApproved u RequestsController.");
            return;
        }
        Animal a = req.getUpdatedAnimal();
        AnimalList animalList = AnimalList.getInstance();
        a.setId(animalList.getInstance().generateId());
        AnimalList.getInstance().addAnimal(a);     //create animal
        Post p = PostList.getInstance().createPost(req.getUpdatedAnimal().getId()); // create post for animal
        user.addCreatedPostId(p.getId());            //post added to users created posts

    }

    public void animalRegistration(User user, String name, String color, Date born, Address address, AnimalState state, ArrayList<String> multimedia, Integer breedId, Integer speciesId) {
        Animal animal = AnimalList.getInstance().createAnimal(name, color, born, address.getId(), state, multimedia, breedId, speciesId);
        Post post = PostList.getInstance().createPost(animal.getId());
        user.addCreatedPostId(post.getId());            //post added to users created posts

    }

    public void postEditingApproved(Request req) {
        if (req.getType() != RequestType.POST_EDITING) {
            System.out.println("Request nije za post editing u animaRegistrationApproved u RequestsController.");
            return;
        }

        req.setState(RequestState.APPROVED);
        updateAnimal(req.getPostId(), req.getUpdatedAnimal());  //update animal
    }

    public void fosterCareEnded(User user, Post post) {
        Animal animal = AnimalList.getInstance().getAnimal(post.getAnimalId());
        if (animal == null) {
            System.out.println("Request nije za foster care ended u RequestsController.");
            return;
        }
        animal.setState(AnimalState.NOTADOPTED);        //change state od animal abck to NOTADOPTED
        user.removePostId(post.getId());                //rmeove post from users posts
    }

    public void updateAnimal(Integer postId, Animal updatedAnimal) {
        Post post = PostList.getInstance().getById(postId);
        if (post == null) return;
        updatedAnimal.setId(post.getAnimalId());
        System.out.println("Updated animal je " + updatedAnimal.toString());
        AnimalList.getInstance().updateAnimal(updatedAnimal);
    }

    public Request deleteRequest(int id){
        return RequestsList.getInstance().deleteRequest(id);
    }
}
