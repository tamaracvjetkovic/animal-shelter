package main;

import domain.enums.AnimalState;
import domain.model.Animal;
import domain.model.Post;
import domain.serializeddata.AnimalList;
import domain.serializeddata.PostList;
import serialization.Serialization;
import view.LoginWindow;
import view.MainWindow;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try {
            Serialization serialization = new Serialization();
            serialization.load();
            System.out.println(AnimalList.getInstance().getAnimals());
            MainWindow mainWindow = new MainWindow();
//            PostList.getInstance().addPost(new Post(PostList.getInstance().generateId(), 1,
//                    new ArrayList<>(Arrays.asList(1,2,3))));
//            AnimalList.getInstance().addAnimal(new Animal(1, "Jezic", "braon", new Date(), 0,
//                    AnimalState.NOTADOPTED, new ArrayList<>(), 0, 1));
//            serialization.save();
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("Desila se greska!");
        }
    }
}
