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

            posts.add(new PostDTO(post.getId(), animal.getMultimedia().get(0), animal.getName(), breed.getName(),
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
        return new PostDTO(post.getId(),animal.getMultimedia().get(0), animal.getName(), breed.getName(),
                    animal.getColour(), animal.getBorn().toString(), animal.getState().toString());
    }

    public ArrayList<PostDTO> getFilteredPosts(String name, String breed, String status, String color) {
        ArrayList<PostDTO> filteredPosts = new ArrayList<>();

        for (PostDTO post : getAllPostsWithAnimalsAndBreeds()) {
            boolean matches = false;

            if (post.getName().equalsIgnoreCase(name)) {
                matches = true;
            }
            if (post.getBreed().equalsIgnoreCase(breed)) {
                matches = true;
            }
            if (post.getStatus().equalsIgnoreCase(status)) {
                matches = true;
            }
            if (post.getColor().equalsIgnoreCase(color)) {
                matches = true;
            }

            if (matches) {
                filteredPosts.add(post);
            }
        }

        return filteredPosts;
    }

    public void likePost(PostDTO postDTO) {
        // one person can like the post many times...
        // it can be improved, but this is currently according to the class diagram
        PostList.getInstance().likePost(postDTO.getId());
    }

    public void addComment(PostDTO postDTO, String comment) {
        // creating comment... should add saving it and serializing
        PostList.getInstance().addComment(postDTO.getId(), 0);
    }
}
