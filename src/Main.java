import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.Mnemonic;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private static Scene scene;

    public static void main(String[] args) {
        SaveLoad.load();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        GridPane root = new MainMenu();


        stage.getIcons().setAll(new Image("Images\\primogem.png"));
        stage.setTitle("Project 3");
        scene = new Scene(root, 500, 500);
        scene.setFill(Color.DEEPSKYBLUE);
        stage.setScene(scene);
        stage.show();
    }

    public static Scene getScene() {
        return scene;
    }
}
