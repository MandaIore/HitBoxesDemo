# HitBoxesDemo
This is a short demo for JavaFX, it is meant to be opened and ran in NetBeans IED (if you can't open it the read me will explain the basics as well.) It goes over how to create and use custom hit boxes for objects using polygons and a simple collision code.

Creating custom hit boxes using javafx is actually fairly simple, just fallow along i and i will explain the process.

    The Interface
    
To create a ojcect with a hit box you are going to need 3 nodes: a ImageView, a Polygon, and a Pane. 

Using sceneBilder (highly recomended):
1. Drag a ImageView and a Polygon on the scene
2. Select both by holding ctrl and cliking on both nodes, then right click and select wrap in pane
3. Now add your Image to the ImageView
4. Cover the image with the Polygon the easiest way to do this is to set it's fill to transparent and use ctrl click to add points to the polygon the  drag the points to outline the shape of your picture
5. Done.

       The Code
       
 You will need the following imports for hit boxes to work:
 
    import javafx.scene.layout.Pane;
    import javafx.scene.paint.Color;
    import javafx.scene.shape.Polygon;
    import javafx.scene.shape.Shape;
    
The next step is a method that makes this whole program work.

Due to the fact that the polygons used for the collion are in two different Panes the Polygons in them can not directly collied. (It's like there on two different planes of existence).
This method creates a transparent copy of whatever polygon you pass it, this copy will do everything the original does (Baiscaly alows a polygon to be two places at once).

The method is passed the polygon you want to copy, then makes a new transparent polygon, then it adds all of the points from the polygon passed to it to the new polygon. Polygons use points for there shape and size each point has it's own x and y quardinat and linkin the order they are listed. Finally the method returns the completed copy for use.

     private Polygon createBoundsPolygon(Polygon poly) {  
        Polygon pol = new Polygon();                   
        pol.setFill(Color.TRANSPARENT);                 
        pol.getPoints().addAll(poly.getPoints());       
        return pol;                                     
    }
    
The next step to setting up hit boxes in in the initialize.

The line bellow uses the method above to add a copy of the polygon you pass it (in this example porlock) to the other pane you want to check collision with (normally your main caracter)

    panCar.getChildren().add(createBoundsPolygon(polRock));
    
To actually tell if somthing has collied you can use this simple method that will return true if two Shapes (polygons are a type of shape) are colliding.

The metho below creates a shape at the intersection of the two shapes you pass it, if the shape made has a width greater than 0 (It was created made) then return true. 

    private boolean checkCol(Shape obj1, Shape obj2) { 
        Shape intersect = Shape.intersect(obj1, obj2); 
        return intersect.getBoundsInParent().getWidth() > 0; 
    }

For example if I want to see if two polygons have collied i could use

     if (checkCol(polCar, polRock) == true) {
     }
     
This would only run the cod if polCar and polRock have collied.

These are some code if you want to create shapes other than polygons

RECTANGLE:

    import javafx.scene.shape.Rectangle;

    pane.getChildren().add(createBoundsRectangle(rectangleYouWantToAdd.getBoundsInParent()));
        
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

    pane.getChildren().add(createBoundsEllipse(ellipseYouWantToAdd.getBoundsInParent()));  

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

    pane.getChildren().add(createBoundsCircle(circleYouWantToAdd.getBoundsInParent())); 

    private Circle createBoundsCircle(Bounds bounds) {  
    Circle cir = new Circle();
    cir.setFill(Color.TRANSPARENT);
    cir.setCenterX(bounds.getMinX());
    cir.setCenterY(bounds.getMinY());
    cir.setRadiusX(bounds.getWidth());
    cir.setRadiusY(bounds.getHeight());
    return cir;
    }    

I hope you found this brief tutorial informative. If you have any surgestions fell free to message my gitHub.
 
