/*
*  Made By: spencer jones
 * Date: 2018-11-26
 * Description: Demo program to help others understand custom hitboxes using polygons
*/
package jones.hitboxesdemo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.util.Duration;

//imports for collision
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;


public class FXMLController implements Initializable {

    //Here you are defining your Polygons and Panes that you are using
    @FXML
    private Polygon polRock;
    @FXML
    private Polygon polCar;
    @FXML
    private Pane panRock;
    @FXML
    private Pane panCar;
  
    //Collison checking method
    //This method is a boolean that returns true if the two shapes you pass it are colliding
    
    private boolean checkCol(Shape obj1, Shape obj2) { 
        
        Shape intersect = Shape.intersect(obj1, obj2); //this creates a shape that has areas that are contained in both of the shapes given

        return intersect.getBoundsInParent().getWidth() > 0; // This will retun true if the intersecting shape created has a width greater than 0, in other word this returns true if the intersecting shape has a width becuse if it does not the two odjects are not colliding

    }
   
    //This is just a timer for the demo to show the collision. It is not needed collision to work.
    Timeline movement = new Timeline(new KeyFrame(Duration.millis(8), ae -> move()));

    // This method is used to move the rock pane into the car and check for collision
    private void move() {

        if (panRock.getTranslateX() != 0) {

            panRock.setTranslateX(panRock.getTranslateX() - 5);
        } else {
            panRock.setTranslateX(900);
        }

        
        if (checkCol(polCar, polRock) == true) { //this is the if statment acualy checks for collision. you use the method checkCol by pasing it the two shapes you what to check, and if they are colliding then checkCol will return true

            panRock.setTranslateX(1000);
        }

    }

    

     
    /*
    This is were the magic happens
    becase the polygons used for the collion are in two diffrant Panes the objects in them can not directly collied.
    This method creates a transparent copy of whatever polygon you pass it.
    It is used in the initilize to create a copy of the polygon used for the rock hitbox then it adds that copy to the car pane so we can check for collision.
    The invisble copy does every thing it's original does
    */
    private Polygon createBoundsPolygon(Polygon poly) {  
        Polygon pol = new Polygon();                    //creates new polygon called pol
        pol.setFill(Color.TRANSPARENT);                 //makes pol transparent so you don't see it
        pol.getPoints().addAll(poly.getPoints());       // polygons use points (x and y quadrants) to determine there size and shape. So in this line it is setting the points of pol to all of the points of the polygon you passed it
        return pol;                                     // returns the new polygon named pol
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // This line uses the createBoundsPolygon method to create an teansparent copy of the polygon you pass to it, inside the pane tou specify
        panCar.getChildren().add(createBoundsPolygon(polRock));
        
        //this is just used to start and run the movement timer
        movement.setCycleCount(Timeline.INDEFINITE);
        movement.play();
    }
}
/*These are different methods that can be used if you want to use shapes other than polygons for this type of collision

import javafx.geometry.Bounds; This import is need for all of the folowing

RECTANGLE:

import javafx.scene.shape.Rectangle;

 pane.getChildren().add(createBoundsRectangle(rectangleYouWantToAdd.getBoundsInParent())); //this line would go in your inizalize if you are using rectagles. It adds a copy of the rectanlgle you you give it to the Pane you giv it
        
private Rectangle createBoundsRectangle(Bounds bounds) {  
Rectangle rect = new Rectangle();
rect.setFill(Color.TRANSPARENT);
rect.setX(bounds.getMinX());
rect.setY(bounds.getMinY());
rect.setWidth(bounds.getWidth());
rect.setHeight(bounds.getHeight());
return rect;
}

ELLIPSE:

import javafx.scene.shape.Ellipse;

pane.getChildren().add(createBoundsEllipse(ellipseYouWantToAdd.getBoundsInParent()));  //this line would go in your inizalize if you are using Ellipse. It adds a copy of the Ellipse you you give it to the Pane you giv it

 private Ellipse createBoundsEllipse(Bounds bounds) {  
        Ellipse ell = new Ellipse();
        ell.setFill(Color.TRANSPARENT);
        ell.setCenterX(bounds.getMinX());
        ell.setCenterY(bounds.getMinY());
        ell.setRadiusX(bounds.getWidth());
        ell.setRadiusY(bounds.getHeight());
        return ell;
    }

CIRCLE:

import javafx.scene.shape.Circle;

pane.getChildren().add(createBoundsCircle(circleYouWantToAdd.getBoundsInParent())); //This line  would go in your inizalize if you are using Circle. It does the samething as the top two except for circles

private Circle createBoundsCircle(Bounds bounds) {  
        Circle cir = new Circle();
        cir.setFill(Color.TRANSPARENT);
        cir.setCenterX(bounds.getMinX());
        cir.setCenterY(bounds.getMinY());
        cir.setRadiusX(bounds.getWidth());
        cir.setRadiusY(bounds.getHeight());
        return cir;
    }

}*/
