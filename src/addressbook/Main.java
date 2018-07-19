package addressbook;

import addressbook.datamodel.ContactData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("My Contacts");
        primaryStage.setScene(new Scene(root, 600, 400));
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
