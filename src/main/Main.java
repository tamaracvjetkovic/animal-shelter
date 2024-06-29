package main;

import domain.model.Post;
import domain.serializeddata.PostList;
import serialization.Serialization;
import view.MainWindow;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            Serialization serialization = new Serialization();
            serialization.load();
            System.out.println(PostList.getInstance().getPosts());
            MainWindow mainWindow = new MainWindow();
//            PostList.getInstance().addPost(new Post(PostList.getInstance().generateId(), 1,
//                    new ArrayList<>(Arrays.asList(1,2,3))));
//            serialization.save();
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("Desila se greska!");
        }
    }
}
