package controller;

import domain.enums.UserState;
import domain.model.User;
import domain.serializeddata.AccountsList;
import domain.serializeddata.UsersList;

import java.util.Date;

public class LogInController {
    public User logIn(String username, String password) {
        return UsersList.getInstance().getByUsernameAndPassword(username, password);
    }

    public User signUp(String name, String lastname, String email, Date birthDate, String username, String password){
        User user = UsersList.getInstance().getByUsername(username);
        if(user!=null){
            return null;    //user already exists
        }

        user = UsersList.getInstance().createUser(name,lastname,email, birthDate,username,password,UserState.MEMBER);
        return user;
    }

    public void changePassword(User user, String newPassword) {
        AccountsList.getInstance().changePassword(user.getAccountId(), newPassword);
    }
}
