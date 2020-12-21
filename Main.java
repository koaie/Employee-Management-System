import java.io.IOException;

import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.fxml.FXMLLoader;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        JMetro jMetro = new JMetro(Style.LIGHT);
        Parent root = FXMLLoader.load(getClass().getResource("ems.fxml"));
        Scene main = new Scene(root, 665, 350);

        jMetro.setScene(main); 

        primaryStage.setResizable(false);
        primaryStage.setTitle("Employee Mangement System");
        primaryStage.setScene(main);
        primaryStage.show();
    }
}