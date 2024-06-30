package main;

import controller.RequestsController;
import domain.serializeddata.RequestsList;
import serialization.Serialization;
import view.MainWindow;

import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        try {
            Serialization serialization = new Serialization();
            serialization.load();
            RequestsController controller = new RequestsController();
            controller.updateVolunteeringRequests();                //check if time is up for voting for volunteering requests
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
