package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label deadline;
    @FXML
    private Label goal;
    @FXML
    private Label height;
    @FXML
    private Label weight;
    @FXML
    private Label age;
    @FXML
    private Label gender;
    @FXML
    private Label bodyfat;
    @FXML
    private Label paid;
    @FXML
    private Label session;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText("Name: " + person.getName().fullName);
        phone.setText("Phone Number: " + person.getPhone().value);
        address.setText("Address: " + person.getAddress().value);
        height.setText("Height: " + person.getHeight().value + " cm");
        weight.setText("Weight: " + person.getWeight().value + " kg");
        age.setText("Age: " + person.getAge().value + " years old");
        gender.setText("Gender: " + person.getGender().value);
        email.setText("Email: " + person.getEmail().value);
        deadline.setText("Payment Deadline: " + person.getDeadline().toStorageString());
        goal.setText("Personal Goal: " + person.getGoal().value);
        bodyfat.setText("Bodyfat Percentage: " + person.getBodyfat().toString() + "%");
        paid.setText("Payment Status: " + (person.getPaymentStatus().value ? "Paid" : "Not Paid"));
        session.setText("Session: " + person.getSession().toStorageString());
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
