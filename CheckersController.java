/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aws52bcheckers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DovaReborn
 */
public class CheckersController implements Initializable {

    private CheckerBoard board;
    private Stage stage;
    private Scene scene;
    //since rows=cols, just use one variable for both
    private int numRowCol;
    private double boardWidth;
    private double boardHeight;
    private Color lightColor;
    private Color darkColor;
    
    @FXML
    private VBox vbox;
    
    @FXML
    private StackPane stackPane;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        board = null;
        lightColor = Color.RED;
        darkColor = Color.BLACK;
        numRowCol = 8;
    }    
    
    public void ready(Stage stage, Scene scene){
        this.stage = stage;
        this.scene = scene;
        
        ChangeListener<Number> listener = (ObservableValue<? extends Number> observable, Number oldValue, final Number newValue) -> {
            render();
        };
        
        stage.widthProperty().addListener(listener);
        stage.heightProperty().addListener(listener);
        
        render();
    }
    
    public void render(){
        try{//this WILL fail the first time it trys to render because the object being removed is null
            vbox.getChildren().remove(board.getBoard());
        }catch(Exception ex){
            System.out.printf("%s\n", ex.toString());
        }
        
        boardWidth = stage.getWidth();
        boardHeight = stage.getHeight() - 50;//allow for menu bar

        CheckerBoard newBoard = new CheckerBoard(numRowCol, numRowCol, boardWidth, boardHeight, lightColor, darkColor);
        //make sure a valid anchor pane exists
        if(newBoard.getBoard()!= null){
            board = newBoard;
            vbox.getChildren().add(board.getBoard());
        }
    }
    
    @FXML
    public void changeNumBoxes(ActionEvent event){
        MenuItem source = (MenuItem)event.getSource();
        switch(source.getId()){
            case "sixteen":
                numRowCol = 16;
                break;
                
            case "ten":
                numRowCol = 10;
                break;
                
            /*case "eight"://just let this case default to the end
                numRowCol = 8;
                break;*/
                
            case "three":
                numRowCol = 3;
                break;
                
            default:
                numRowCol = 8;
                break;
        }
        render();
    }
    
    @FXML
    public void changeColor(ActionEvent event){
        MenuItem source = (MenuItem)event.getSource();
        switch(source.getId()){
            case "blue":
                lightColor = Color.SKYBLUE;
                darkColor = Color.DARKBLUE;
                break;
                
            default:
                lightColor = Color.RED;
                darkColor = Color.BLACK;
                break;
        }
        
        render();
    }
}
