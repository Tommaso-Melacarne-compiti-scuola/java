package online.polp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import online.polp.model.Calculator;

public class PrimaryController {
    @FXML
    private Label result;
    @FXML
    private TextField firstNumber;
    @FXML
    private TextField secondNumber;

    @FXML
    private void handleAction(ActionEvent event) {
        double a = Double.parseDouble(firstNumber.getText());
        double b = Double.parseDouble(secondNumber.getText());

        Button button = (Button) event.getSource();

        double resultDouble;

        switch (button.getText()) {
            case "+":
                resultDouble = Calculator.add(a, b);
                break;
            case "-":
                resultDouble = Calculator.subtract(a, b);
                break;
            case "*":
                resultDouble = Calculator.multiply(a, b);
                break;
            case "/":
                resultDouble = Calculator.divide(a, b);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + button.getText());
        }

        result.setText(String.valueOf(resultDouble));
    }
}
