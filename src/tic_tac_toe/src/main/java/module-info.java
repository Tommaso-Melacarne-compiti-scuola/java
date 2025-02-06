module online.polp {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.xml;

    opens online.polp to javafx.fxml;
    exports online.polp;
}
