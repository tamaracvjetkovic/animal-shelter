package domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;

@XStreamAlias("post")
public class Post {
	private Integer id;
	private Integer animalId;
	private Integer likes;
	private ArrayList<Integer> commentsIds;
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Post(Integer id, Integer animalId, Integer likes, ArrayList<Integer> commentsIds) {
		super();
		this.id = id;
		this.animalId = animalId;
		this.likes = likes;
		this.commentsIds = commentsIds;
	}
	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAnimalId() {
		return animalId;
	}
	public void setAnimalId(Integer animalId) {
		this.animalId = animalId;
	}
	public ArrayList<Integer> getCommentsIds() {
		return commentsIds;
	}
	public void setCommentsIds(ArrayList<Integer> commentsIds) {
		this.commentsIds = commentsIds;
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", animalId=" + animalId + ", commentsIds=" + commentsIds + "]";
	}

}
