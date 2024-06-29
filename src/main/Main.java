package main;

import serialization.Serialization;
import view.MainWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Hello World!");
            MainWindow mainWindow = new MainWindow();
            //Serialization serialization = new Serialization();
            //serialization.load();
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("Desila se greska!");
        }
    }
}
