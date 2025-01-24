package online.polp;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import online.polp.models.Contact;
import online.polp.models.ContactsSingleton;

import java.util.List;

public class PrimaryController {
    @FXML
    private ScrollPane contactsPane;
    @FXML
    private TextField searchField;

    public void initialize() {
        renderContacts();
    }

    @FXML
    private void renderContacts() {
        String filter = searchField.getText();

        List<Contact> contacts = ContactsSingleton.getInstance().getSortedFilteredList(filter);

        VBox contactsVBox = new VBox(5);

        contactsVBox.setPadding(new Insets(10));

        List<HBox> contactBoxes = contacts.stream()
                                          .map(this::fromContactToHBox)
                                          .toList();

        contactsVBox.getChildren().addAll(contactBoxes);

        contactsPane.setContent(contactsVBox);
    }

    private HBox fromContactToHBox(Contact contact) {
        HBox hBox = new HBox(40, new Text(contact.getName()), new Text(contact.getPhone()));
        hBox.setPadding(new Insets(5, 0, 5, 0));

        return hBox;
    }
}
