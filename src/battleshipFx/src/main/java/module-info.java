module polp.online {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    opens polp.online to javafx.fxml;
    exports polp.online;
}
