package domain.serializeddata;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import domain.enums.UserState;
import domain.model.Account;
import domain.model.User;

import java.util.ArrayList;
import java.util.Date;

@XStreamAlias("usersList")
public class UsersList {

    public static UsersList instance = null;

    @XStreamAlias("users")
    private ArrayList<User> users;

    private UsersList() {
        this.users = new ArrayList<User>();
    }

    public static UsersList getInstance() {
        if (instance == null) {
            instance = new UsersList();
        }

        return instance;
    }

    public int generateId() {
        int numUsers = users.size();
        return ++numUsers;
    }

    public static void setInstance(UsersList usersList) {
        instance = usersList;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }
    public User createUser(String name, String lastname, String email,Date birthDate,String username, String password,UserState state){
        Account acc = AccountsList.getInstance().createAccount(username,password);
        User u = new User(generateId(), name,lastname, email, birthDate, acc.getId(),state);
        addUser(u);
        return u;
    }
    public User getByUsername(String username) {
        User u = new User();
        for(User user : users){
            Account acc = AccountsList.getInstance().getById(user.getAccountId());
            if(acc !=null && acc.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }
    public User getById(Integer id){
        for(User user : users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }
}