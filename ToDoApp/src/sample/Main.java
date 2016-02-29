package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {






    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("list.fxml"));
        primaryStage.getIcons().add(
                new javafx.scene.image.Image(
                       getClass().getResourceAsStream("icon_lighbulb.png")));
        primaryStage.setTitle("To Do App");
        primaryStage.setScene(new Scene(root, 270, 270));
        primaryStage.setMinWidth(270);
        primaryStage.setMinHeight(270);
        primaryStage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }


}
