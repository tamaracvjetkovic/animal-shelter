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
        ArrayList<PostDTO> posts = new ArrayList<>();

        for(Post post : PostList.getInstance().getPosts()) {
            int animalId = post.getAnimalId();

            Animal animal = AnimalList.getInstance().getAnimal(animalId);
            Breed breed = BreedList.getInstance().getBreedByAnimalId(animalId);

            posts.add(new PostDTO(animal.getMultimedia().get(0), animal.getName(), breed.getName(),
                    animal.getColour(), animal.getBorn().toString(), animal.getState().toString()));
        }

        return posts;
    }

    public Post getById(Integer id){
        return PostList.getInstance().getById(id);
    }

    public ArrayList<PostDTO> getFilteredPosts(String name, String breed, String status, String color) {
        ArrayList<PostDTO> filteredPosts = new ArrayList<>();

        for (PostDTO post : getAllPostsWithAnimalsAndBreeds()) {
            boolean matches = true;

            if (!name.isEmpty() && !post.getName().equalsIgnoreCase(name)) {
                matches = false;
            }
            if (!breed.isEmpty() && !post.getBreed().equalsIgnoreCase(breed)) {
                matches = false;
            }
            if (!status.isEmpty() && !post.getStatus().equalsIgnoreCase(status)) {
                matches = false;
            }
            if (!color.isEmpty() && !post.getColor().equalsIgnoreCase(color)) {
                matches = false;
            }

            if (matches) {
                filteredPosts.add(post);
            }
        }

        return filteredPosts;
    }
}
