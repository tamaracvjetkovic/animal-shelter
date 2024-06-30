package domain.serializeddata;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import domain.enums.UserState;
import domain.model.Account;
import domain.model.Comment;

import java.util.ArrayList;


@XStreamAlias("commentsList")
public class CommentsList {

    public static CommentsList instance = null;

    @XStreamAlias("comments")
    private ArrayList<Comment> comments;

    private CommentsList() {
        this.comments = new ArrayList<Comment>();
    }

    public static CommentsList getInstance() {
        if (instance == null) {
            instance = new CommentsList();
        }

        return instance;
    }

    public Integer generateId() {
        int amountOfComments = comments.size();
        return amountOfComments + 1;
    }

    public static void setInstance(CommentsList comments) {
        instance = comments;
    }

    public ArrayList<Comment> getComments() {
        return this.comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public Comment addComment(Comment comment) {
        this.comments.add(comment);
        return comment;
    }

    public Comment getComment(int id) {
        for (Comment comment : comments) {
            if (comment.getId() == id) {
                return comment;
            }
        }

        return null;
    }

    public void updateComment(Comment newComment) {
        Comment comment = getComment(newComment.getId());
        comment.setMessage(newComment.getMessage());
    }

    public Comment createComment(String message) {
        Comment comment = new Comment(generateId(), message);
        addComment(comment);
        return comment;
    }
}
