package controller;

import domain.model.*;
import domain.serializeddata.AnimalList;
import domain.serializeddata.BreedList;
import domain.serializeddata.CommentsList;
import domain.serializeddata.PostList;
import dtos.PostDTO;

import java.util.ArrayList;

public class FeedController {

    public FeedController() {

    }

    public ArrayList<PostDTO> getAllPostsWithAnimalsAndBreeds() {
        ArrayList<PostDTO> posts = new ArrayList<>();

        for (Post post : PostList.getInstance().getPosts()) {
            int animalId = post.getAnimalId();

            Animal animal = AnimalList.getInstance().getAnimal(animalId);
            Breed breed = BreedList.getInstance().getById(animal.getBreedId());

            posts.add(new PostDTO(post.getId(), animal.getMultimedia().get(0), animal.getName(), breed.getName(),
                    animal.getColour(), animal.getBorn().toString(), animal.getState().toString()));
        }

        return posts;
    }

    public ArrayList<PostDTO> getAllPostsWithAnimalsAndBreeds(User user) {
        ArrayList<PostDTO> posts = new ArrayList<>();

        for(Post post : PostList.getInstance().getPosts()) {
            if (user.getCreatedPostsIds().contains(post.getId())) {
                int animalId = post.getAnimalId();

                Animal animal = AnimalList.getInstance().getAnimal(animalId);
                Breed breed = BreedList.getInstance().getById(animal.getBreedId());

                posts.add(new PostDTO(post.getId(), animal.getMultimedia().get(0), animal.getName(), breed.getName(),
                        animal.getColour(), animal.getBorn().toString(), animal.getState().toString()));
            }
        }

        return posts;
    }

    public ArrayList<PostDTO> getAllPostsUserAdopted(User user) {
        ArrayList<PostDTO> posts = new ArrayList<>();

        for(Post post : PostList.getInstance().getPosts()) {
            if (user.getPostsIds().contains(post.getId())) {
                int animalId = post.getAnimalId();

                Animal animal = AnimalList.getInstance().getAnimal(animalId);
                Breed breed = BreedList.getInstance().getById(animal.getBreedId());

                posts.add(new PostDTO(post.getId(), animal.getMultimedia().get(0), animal.getName(), breed.getName(),
                        animal.getColour(), animal.getBorn().toString(), animal.getState().toString()));
            }
        }

        return posts;
    }

    public Post getById(Integer id){
        return PostList.getInstance().getById(id);
    }

    public PostDTO getDTOById(Integer id) {
        Post post = PostList.getInstance().getById(id);
        if (post == null) {
            return null;
        }
        int animalId = post.getAnimalId();
        Animal animal = AnimalList.getInstance().getAnimal(animalId);
        Breed breed = BreedList.getInstance().getBreedByAnimalId(animalId);
        return new PostDTO(post.getId(), animal.getMultimedia().get(0), animal.getName(), breed.getName(),
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

    public void addComment(PostDTO postDTO, String message) {
        // creating comment... should add saving it and serializing
        Comment comment = CommentsList.getInstance().createComment(message);
        PostList.getInstance().addComment(postDTO.getId(), comment.getId());
    }

    public ArrayList<Comment> getAllCommentsByPost(Post post) {
        ArrayList<Comment> comments = new ArrayList<>();
        for (int id : post.getCommentsIds()) {
            Comment comment = getCommentById(id);
            comments.add(comment);
        }

        return comments;
    }

    private Comment getCommentById(int id) {
        return CommentsList.getInstance().getComment(id);
    }

    public String[] getBreedsForPicker() {
        String[] breeds = new String[BreedList.getInstance().getBreeds().size()];
        int i = 0;
        for(Breed breed : BreedList.getInstance().getBreeds()) {
            breeds[i++] = breed.getName();
        }

        return breeds;
    }

    public String[] getSpeciesForPicker() {
        String[] speciess = new String[SpeciesList.getInstance().getSpeciess().size()];
        int i = 0;
        for(Species species : SpeciesList.getInstance().getSpeciess()) {
            speciess[i++] = species.getName();
        }

        return speciess;
    }

    public int getBreedId(String name) {
        for(Breed breed : BreedList.getInstance().getBreeds()) {
            if(breed.getName().equalsIgnoreCase(name)) {
                return breed.getId();
            }
        }

        return -1;
    }

    public int getSpeciesId(String name) {
        for(Species species : SpeciesList.getInstance().getSpeciess()) {
            if(species.getName().equalsIgnoreCase(name)) {
                return species.getId();
            }
        }

        return -1;
    }

    public Animal getAnimalFromPost(PostDTO postDTO) {
       return AnimalList.getInstance().getAnimal(PostList.getInstance().getById(postDTO.getId()).getAnimalId());
    }

    public Address getAddress(int id) {
        return AddressList.getInstance().getAddress(id);
    }

    public Address createAddress(String city, String street, String number) {
        return AddressList.instance.createAddress(new Address(0, city, street, Integer.parseInt(number)));
    }
}
