import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LandGen extends Application{
	
	int water = 75; //Water height
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage theStage) {
		/*Set map x and y limit and number of prome nodes. The
		 * more prime nodes the more sporadic
		 */
		Map myMap = new Map(300,300,30);
		
		theStage.setTitle( "Map Gen" );
        
	    Group root = new Group();
	    Scene theScene = new Scene( root );
	    theStage.setScene( theScene );
	         
	    Canvas canvas = new Canvas( 400, 400 );
	    root.getChildren().add( canvas );
	         
	    GraphicsContext gc = canvas.getGraphicsContext2D();
	         
	    //Draw each node and change color to blue if under water height
	    for(Node node: myMap.getNodes()) {
	    	//What happens below a certain height
	    	if (node.getHeight() < water) {
	    		gc.setFill(Color.rgb(0, 0, 255));
	    	}
	    	else{
	    		gc.setFill(Color.rgb((int)(255 - node.getHeight()), (int)(255 - node.getHeight()), (int)(255 - node.getHeight())));
	    	}
	    	gc.fillOval(node.getX(), node.getY(), 1, 1); 
	    }
	         
	    theStage.show();
    }
}
