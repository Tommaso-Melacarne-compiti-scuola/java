package online.polp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import online.polp.models.Calculator;

import java.io.IOException;

public class PrimaryController {
    @FXML
    private TextField display;


    @FXML
    private void handleAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        String textToAdd = button.getText();

        switch (textToAdd) {
            case "=":
                display.setText(Calculator.calculate(display.getText()));
                break;
            case "C":
                display.setText("");
                break;
            case "DEL":
                if (!display.getText().isEmpty()) {
                    String lastDigitRemoved = display.getText().substring(0, display.getText().length() - 1);

                    display.setText(lastDigitRemoved);
                }
                break;
            default:
                display.setText(display.getText() + textToAdd);
                break;
        }
    }
}
