package controller;

import domain.enums.UserState;
import domain.model.User;
import domain.model.podaci.UsersList;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class LogInController {
    public User logIn(String username, String password) {
        User user = UsersList.getInstance().getByUsername(username);
        return user;
    }

    public User signUp(String name, String lastname, String email, String birthDate, String username, String password){
        User user = UsersList.getInstance().getByUsername(username);
        if(user!=null){
            return null;    //user already exists
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            Date date = Date.from(Instant.from(LocalDate.parse(birthDate, formatter)));
            System.out.println("Parsed Date: " + date);
            user = UsersList.getInstance().createUser(name,lastname,email, date,username,password,UserState.MEMBER);

        } catch (DateTimeParseException e) {
            System.out.println("Doslo je do greske pri parsovanje datuma u registraciji");
            return null;
        }
        return user;
    }
}
