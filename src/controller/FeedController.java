package controller;

import domain.model.Breed;
import domain.model.Post;
import domain.model.Animal;
import domain.serializeddata.AnimalList;
import domain.serializeddata.BreedList;
import domain.serializeddata.PostList;
import dtos.PostDTO;

import java.util.ArrayList;

public class FeedController {
    public FeedController() {

    }

    public ArrayList<PostDTO> getAllPostsWithAnimalsAndBreeds() {
        ArrayList<PostDTO> posts = new ArrayList<PostDTO>();

        for(Post post : PostList.getInstance().getPosts()) {
            int animalId = post.getAnimalId();

            Animal animal = AnimalList.getInstance().getAnimal(animalId);
            Breed breed = BreedList.getInstance().getBreedByAnimalId(animalId);

            posts.add(new PostDTO(animal.getMultimedia().get(0), animal.getName(), breed.getName(),
                    animal.getColour(), animal.getBorn().toString(), animal.getState().toString()));
        }

        return posts;
    }
    public PostDTO getById(Integer id){
        Post post = PostList.getInstance().getById(id);
        if (post == null) {return null;}
        int animalId = post.getAnimalId();
        Animal animal = AnimalList.getInstance().getAnimal(animalId);
        Breed breed = BreedList.getInstance().getBreedByAnimalId(animalId);
        return new PostDTO(animal.getMultimedia().get(0), animal.getName(), breed.getName(),
                    animal.getColour(), animal.getBorn().toString(), animal.getState().toString());
    }
}
