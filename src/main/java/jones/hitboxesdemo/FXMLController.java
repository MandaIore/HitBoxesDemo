package jones.hitboxesdemo;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class FXMLController implements Initializable {

    @FXML
    private Polygon polRock;
    @FXML
    private Rectangle recCar;
    @FXML
private Pane panRock;
    @FXML
private Pane panCar;
     Timeline movement = new Timeline(new KeyFrame(Duration.millis(8), ae -> move()));
     
   
    
     private void move() {
     System.out.println("working");
         if (panRock.getTranslateX() != 0){
              System.out.println("moving");
          panRock.setTranslateX(panRock.getTranslateX()-5);
         }
         else{
             panRock.setTranslateX(900);
         }
         
         if(checkCol(recCar, polRock) == true){
              System.out.println("hit");
               panRock.setTranslateX(900);
                 }
         
     }
     
       private boolean checkCol(Shape obj1, Shape obj2) {
        Shape intersect = Shape.intersect(obj1, obj2);

        return intersect.getBoundsInParent().getWidth() > 0;

    }
     

    private Rectangle createBoundsRectangle(Bounds bounds) {  //method used to make the blank copy in other pane
        Rectangle rect = new Rectangle();
        rect.setFill(Color.TRANSPARENT);
        rect.setX(bounds.getMinX());
        rect.setY(bounds.getMinY());
        rect.setWidth(bounds.getWidth());
        rect.setHeight(bounds.getHeight());
        return rect;
    }

    private Polygon createBoundsPolygon() {  //method used to make the blank copy in other pane
        Polygon pol = new Polygon();
        pol.setFill(Color.TRANSPARENT);
        pol.getPoints().addAll(polRock.getPoints());

        return pol;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         panCar.getChildren().add(createBoundsPolygon());
          movement.setCycleCount(Timeline.INDEFINITE);
         movement.play();
    }
}
