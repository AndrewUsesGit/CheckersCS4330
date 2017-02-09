/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aws52bcheckers;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author DovaReborn
 */
public class CheckerBoard {
    
    private int numRows;
    private int numCols;
    private double boardWidth;
    private double boardHeight;
    private Color lightColor;
    private Color darkColor;
    private double rectWidth;
    private double rectHeight;
    private double squareEdge;
    private AnchorPane board;
    
    //my current implementation doesn't use this
    public CheckerBoard(int numRows, int numCols, double boardWidth, double boardHeight){
        this(numRows, numCols, boardWidth, boardHeight, Color.RED, Color.BLACK);
    }
    
    public CheckerBoard(int numRows, int numCols, double boardWidth, double boardHeight, Color lightColor, Color darkColor){
        this.numRows = numRows;
        this.numCols = numCols;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.lightColor = lightColor;
        this.darkColor = darkColor;
        rectWidth = boardWidth/numCols;
        rectHeight = boardHeight/numRows;
        if(rectWidth<rectHeight){
            squareEdge = rectWidth;
        }else{
            squareEdge = rectHeight;
        }
        board = null;
    }
    
    public final AnchorPane build(){
        //verify all data is valid
        if(getNumRows()==-1||getNumCols()==-1||getHeight()==-1||getWidth()==-1||getRectangleWidth()==-1||getRectangleHeight()==-1){
            return null;
        }
        
        AnchorPane newPane = new AnchorPane();
        //A source I found said to change all 3... Probably didn't have to
        newPane.setMinSize(boardWidth, boardHeight);
        newPane.setPrefSize(boardWidth, boardHeight);
        newPane.setMaxSize(boardWidth, boardHeight);
        //for each row...
        for(int i = 0; i<numRows; i++){
            //make x boxes
            for(int j = 0; j<numCols; j++){
                Rectangle newRect = new Rectangle((double)squareEdge*j, (double)squareEdge*i, squareEdge, squareEdge);
                //this makes every-other block light/dark
                if(((i%2)+(j%2))%2 == 0){
                    newRect.setFill(darkColor);
                }else{
                    newRect.setFill(lightColor);
                }
                newPane.getChildren().add(newRect);
            }
        }
        
        return newPane;
    }
    
    /***************************************************
    Each getter implements some form of "error checking"
    ***************************************************/
    
    public AnchorPane getBoard(){
        if(board == null){
            board = build();
            return board;
        }
        return board;
    }
    
    public int getNumRows(){
        if(numRows > 0){
            return numRows;
        }
        return -1;
    }
    
    public int getNumCols(){
        if(numCols > 0){
            return numCols;
        }
        return -1;
    }
            
    public double getWidth(){
        if(boardWidth >= 0){
            return boardWidth;
        }
        return -1;
    }

    public double getHeight(){
        if(boardHeight >= 0){
            return boardHeight;
        }
        return -1;
    }

    public Color getLightColor(){
        return lightColor;
    }

    public Color getDarkColor(){
        return darkColor;
    }

    public double getRectangleWidth(){
        if(rectWidth >= 0){
            return rectWidth;
        }
        return -1;
    }

    public double getRectangleHeight(){
        if(rectHeight >= 0){
            return rectHeight;
        }
        return -1;
    }
}
