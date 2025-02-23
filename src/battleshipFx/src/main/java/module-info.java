module polp.online {
    requires javafx.fxml;
    requires static lombok;
    requires atlantafx.base;

    opens polp.online to javafx.fxml;
    exports polp.online;
}
