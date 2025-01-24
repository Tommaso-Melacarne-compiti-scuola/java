module online.polp {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    opens online.polp to javafx.fxml;
    exports online.polp;
}
