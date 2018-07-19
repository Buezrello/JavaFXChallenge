package addressbook;

import addressbook.datamodel.Contact;
import addressbook.datamodel.ContactData;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class Controller {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private TableView<Contact> tableView;


    public Contact getSelectedContact() {
        return tableView.getSelectionModel().getSelectedItem();
    }

    public void initialize() {
        tableView.setItems(ContactData.getInstance().getContacts());
    }

    @FXML
    public void showNewContactDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add New Contact");
        dialog.setHeaderText("Use this dialog to add a New Contact");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("newContactDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch ( IOException e ) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DialogController dialogController = fxmlLoader.getController();
            Contact newContact = dialogController.processResult();
            tableView.getSelectionModel().select(newContact);
        }
    }

    @FXML
    public void showEditContactDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Edit Contact");
        dialog.setHeaderText("Use this dialog to edit selected Contact");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("newContactDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch ( IOException e ) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Contact selectedContact = getSelectedContact();
        if (selectedContact != null) {
            DialogController dialogController = fxmlLoader.getController();
            dialogController.selectedContact(selectedContact);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                ContactData.getInstance().deleteContact(selectedContact);
                Contact editedContact = dialogController.processResult();
                tableView.getSelectionModel().select(editedContact);
            }
        }


    }

    @FXML
    public void handleDelete() {
        Contact selectedContact = getSelectedContact();
        if (selectedContact != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Contact");
            alert.setHeaderText("Delete Contact: " + selectedContact.getFirstName() + " " + selectedContact.getLastName());
            alert.setContentText("Are you sure? Press OK to confirm or CANCEL to Back out.");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                ContactData.getInstance().deleteContact(selectedContact);
            }
        }
    }

    @FXML
    public void handleExit() {
        Platform.exit();
    }
}
