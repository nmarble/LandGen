import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LandGen extends Application{

	private static final Logger LOG = Logger.getLogger(LandGen.class.getName());

	int water = 75;
	public static void main(String[] args) {
		
		launch(args);
	}
	public void start(Stage theStage) {
		Map myMap = new Map();
		theStage.setTitle( "Canvas Example" );
        
	    Group root = new Group();
	    Scene theScene = new Scene( root );
	    theStage.setScene( theScene );
	         
	    Canvas canvas = new Canvas( 400, 200 );
	    root.getChildren().add( canvas );
	         
	    GraphicsContext gc = canvas.getGraphicsContext2D();
	         
	    gc.setFill( Color.RED );
	    gc.setStroke( Color.BLACK );
	    gc.setLineWidth(2);
	    
	    for(Node node: myMap.getNodes()) {
	    	if (node.getHeight() < water) {
	    		gc.setFill(Color.rgb(0, 0, 255));
	    	}else {
	    	gc.setFill(Color.rgb((int)(255 - node.getHeight()), (int)(255 - node.getHeight()), (int)(255 - node.getHeight())));
	    	}
	    	gc.fillOval(node.getX(), node.getY(), 1, 1); 
	    }
	         
	    theStage.show();

		LOG.log(Level.FINE, "myMap: {0}", myMap);
    }
}
