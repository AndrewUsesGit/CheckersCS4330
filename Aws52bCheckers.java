/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aws52bcheckers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author DovaReborn
 */
public class Aws52bCheckers extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Checkers.fxml"));
        Parent root = loader.load();
        CheckersController controller = loader.getController();//gets the controller
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        //need dimensions for stage fpr the board
        controller.ready(stage, scene);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
