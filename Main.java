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
        Parent root = FXMLLoader.load(getClass().getResource("ems.fxml"));
        Scene scene = new Scene(root, 600, 342);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene); 
        primaryStage.setResizable(false);


        primaryStage.setTitle("Employee Mangement System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}