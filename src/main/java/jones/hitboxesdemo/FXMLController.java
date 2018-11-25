package jones.hitboxesdemo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

/* Imoprts for rectangle
import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;
*/

public class FXMLController implements Initializable {

    @FXML
    private Polygon polRock;
    @FXML
    private Polygon polCar;
    @FXML
    private Pane panRock;
    @FXML
    private Pane panCar;
    Timeline movement = new Timeline(new KeyFrame(Duration.millis(8), ae -> move()));

    private void move() {

        if (panRock.getTranslateX() != 0) {

            panRock.setTranslateX(panRock.getTranslateX() - 5);
        } else {
            panRock.setTranslateX(900);
        }

        if (checkCol(polCar, polRock) == true) {

            panRock.setTranslateX(1000);
        }

    }

    private boolean checkCol(Shape obj1, Shape obj2) {
        Shape intersect = Shape.intersect(obj1, obj2);

        return intersect.getBoundsInParent().getWidth() > 0;

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
/*private Rectangle createBoundsRectangle(Bounds bounds) {  //method used to make the blank copy in other pane
Rectangle rect = new Rectangle();
rect.setFill(Color.TRANSPARENT);
rect.setX(bounds.getMinX());
rect.setY(bounds.getMinY());
rect.setWidth(bounds.getWidth());
rect.setHeight(bounds.getHeight());
return rect;
}*/
