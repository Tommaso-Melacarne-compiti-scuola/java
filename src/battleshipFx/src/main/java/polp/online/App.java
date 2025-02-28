package polp.online;

import atlantafx.base.theme.PrimerDark;
import java.io.IOException;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 1080, 600);
        Application.setUserAgentStylesheet(
            new PrimerDark().getUserAgentStylesheet()
        );
        scene
            .getStylesheets()
            .add(
                Objects.requireNonNull(
                    getClass().getResource("style.css")
                ).toExternalForm()
            );
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
            App.class.getResource(fxml + ".fxml")
        );
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
