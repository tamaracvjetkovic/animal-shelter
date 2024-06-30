package domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import domain.enums.UserState;

import java.util.ArrayList;
import java.util.Date;
@XStreamAlias("user")

public class User {
	private Integer id;
	private String name;
	private String lastname;
	private String email;
	private Date birthDate;
	private Integer accountId;
	private UserState userState;
	private ArrayList<Integer> postsIds;
	private ArrayList<Integer> createdPostsIds;

	public User(Integer id, String name, String lastname, String email, Date birthDate, Integer accountId, UserState state) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.birthDate = birthDate;
		this.accountId = accountId;
		this.userState = state;
		this.postsIds = new ArrayList<>();
	}
	public User(){};
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public UserState getUserState() {
		return userState;
	}

	public void setUserState(UserState userState) {
		this.userState = userState;
	}

	public ArrayList<Integer> getPostsIds() {
		return postsIds;
	}

	public void setPostsIds(ArrayList<Integer> postsIds) {
		this.postsIds = postsIds;
	}

	public ArrayList<Integer> getCreatedPostsIds() {
		return createdPostsIds;
	}

	public void setCreatedPostsIds(ArrayList<Integer> createdPostsIds) {
		this.createdPostsIds = createdPostsIds;
	}

	public void addPostId(Integer postId){this.postsIds.add(postId);}
	public void addCreatedPostId(Integer postId){this.createdPostsIds.add(postId);}
	public void removePostId(Integer postId){this.postsIds.remove(postId);}
	public void removeCreatedPostId(Integer postId){this.createdPostsIds.remove(postId);}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", lastname='" + lastname + '\'' +
				", email='" + email + '\'' +
				", birthDate=" + birthDate +
				", accountId=" + accountId +
				", userState=" + userState +
				", postsIds=" + postsIds +
				'}';
	}
}
