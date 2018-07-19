package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("My Contacts");
        primaryStage.setScene(new Scene(root, 500, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        try {
            ContactData.getInstance().saveContacts();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
        try {
            ContactData.getInstance().loadContacts();
        } catch ( Exception e ) {
            e.printStackTrace();
        }

    }
}
