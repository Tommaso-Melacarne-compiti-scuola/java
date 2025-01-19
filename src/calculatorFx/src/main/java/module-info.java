module online.polp {
    requires javafx.controls;
    requires javafx.fxml;

    opens online.polp to javafx.fxml;
    exports online.polp;
}
