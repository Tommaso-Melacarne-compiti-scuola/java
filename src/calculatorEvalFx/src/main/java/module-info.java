module online.polp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.scripting;
    requires java.desktop;
    requires org.graalvm.polyglot;

    opens online.polp to javafx.fxml;
    exports online.polp;
}
