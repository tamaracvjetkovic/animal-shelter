package domain.serializeddata;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import domain.model.Post;
import domain.model.User;

import java.util.ArrayList;

@XStreamAlias("postList")
public class PostList {
    public static PostList instance = null;

    @XStreamAlias("posts")
    private ArrayList<Post> posts;

    private PostList() {
        this.posts = new ArrayList<Post>();
    }

    public static PostList getInstance() {
        if (instance == null) {
            instance = new PostList();
        }

        return instance;
    }

    public Integer generateId() {
        int amountOfPosts = posts.size();
        return amountOfPosts + 1;
    }

    public static void setInstance(PostList posts) {
        instance = posts;
    }

    public ArrayList<Post> getPosts() {
        return this.posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public Post addPost(Post post) {
        this.posts.add(post);
        return post;
    }
    public Post getById(Integer id){
        for(Post post : posts){
            if(post.getId().equals(id)){
                return post;
            }
        }
        return null;
    }
    public ArrayList<Post> getByUser(User user){
        ArrayList<Post> res = new ArrayList<Post>();
        for(Post post : posts){
            if(user.getPostsIds().contains(post.getId())){
                res.add(post);
            }
        }
        return res;
    }

    public void likePost(int postId) {
        Post post = getById(postId);
        post.setLikes(post.getLikes() + 1);
    }

    public void addComment(int postId, int commentId) {
        Post post = getById(postId);
        post.getCommentsIds().add(commentId);
    }
}
