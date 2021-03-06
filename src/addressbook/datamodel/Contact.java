package addressbook.datamodel;

import javafx.beans.property.SimpleStringProperty;

public class Contact {

    private final SimpleStringProperty firstName = new SimpleStringProperty("");
    private final SimpleStringProperty lastName = new SimpleStringProperty("");
    private final SimpleStringProperty phoneNumber = new SimpleStringProperty("");
    private final SimpleStringProperty notes = new SimpleStringProperty("");

    Contact() {
        this("", "", "", "");
    }

    public Contact(String firstName, String lastName, String phoneNumber, String notes) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.phoneNumber.set(phoneNumber);
        this.notes.set(notes);
    }

    public String getFirstName() {
        return firstName.get();
    }

    void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getNotes() {
        return notes.get();
    }

    void setNotes(String notes) {
        this.notes.set(notes);
    }
}
