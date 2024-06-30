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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try {
            Serialization serialization = new Serialization();
            serialization.load();
            MainWindow mainWindow = new MainWindow();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    serialization.save();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }));
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("Desila se greska!");
        }
    }
}
