package domain.serializeddata;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import domain.model.Post;

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
}
