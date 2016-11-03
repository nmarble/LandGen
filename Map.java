import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Map {
	Random randomGenerator = new Random();
	private double maxHeight = 255; //255 correlates the the make RGB color
	private int vary = 20;  //The higher the number the further apart the nodes can be
	
	private int xSize;
	private int ySize;
	private int primeNodes;	//Amount of prime nodes, the more nodes the more sporadic

	private List<Node> nodes = new ArrayList<>();	//Holds all active nodes
	
	public Map(int x, int y, int prime) {
		xSize = x;
		ySize = y;
		primeNodes = prime;
		fillMap();
	}
	
	public Map() {
		xSize = 100;
		ySize = 100;
		primeNodes = 3;
		fillMap();
	}
	
	private int getXSize() {return xSize;}
	private int getYSize() {return ySize;}
	
	public List<Node> getNodes() {return nodes;}
	
	/* Used to set height of all nodes. Creates X random nodes where X is the amount of primeNodes
	 * Then randomly selects one node at a time determining the three closest nodes. Creating a new height
	 * with the average of the three +/- variance
	 */
	private void fillMap() {
		long seed = System.nanoTime();
		List<Node> closestNode = new ArrayList<>();
		Double newHieght;
		
		//Create the primeNodes
		getPrimeNode();
		
		//Create all the nodes with no height and shuffle for random access
		List<Node> flatNodes = createNodes();
		Collections.shuffle(flatNodes, new Random(seed));
		
		
		//Loop through each node checking for the closest completed nodes to get the new height 
		for (Node fNode : flatNodes) {
			
			//Determine closest nodes
			for (Node node: nodes) {
				if (closestNode.size() < 3) {
					closestNode.add(node);
				}
				else {
					for (Node closeNode: closestNode) {
						if (node.getDistance(fNode) < closeNode.getDistance(fNode)) {
							closestNode.remove(closeNode);
							closestNode.add(node);
							break;
						}
					}
				}
			}
			
			newHieght = 0.0;
			//Calculate the height based on the average of the closest and a variance.
			for (Node node: closestNode) {
				newHieght += node.getHeight();
			}
			int dif = randomGenerator.nextInt(vary) - (vary / 2);
			newHieght = (newHieght / 3) + dif;
			
			//Make sure height is within bounds
			if (newHieght < 0) {
				newHieght = 0.0;
			}
			else if( newHieght > maxHeight) {
				newHieght = maxHeight;
			}
			
			//Create node with calculated height
			nodes.add(new Node(fNode.getX(),fNode.getY(), newHieght));
			
			//reset closest node to prevent errors
			closestNode.clear();
		}
	}
	
	//Randomly generate starting nodes
	private void getPrimeNode() {
		for(int i = 0; i < primeNodes; i++) {
			int x = randomGenerator.nextInt(xSize);
			int y = randomGenerator.nextInt(ySize);
			double h = (randomGenerator.nextDouble() * maxHeight);
			nodes.add(new Node(x,y,h));
		}
	}
	
	//Create all nodes without height
	private List<Node> createNodes() {
		List<Node> returnNodes = new ArrayList<>();
		for (int x = 0; x < xSize; x++) {
			for (int y = 0; y < ySize; y++) {
				returnNodes.add(new Node(x, y));
			}	
		}
		return returnNodes;
	}
	
	//Used for debuging, prints all info of each node
	private void printMap() {
		for (Node node : nodes) {
			node.printInfo();
		}
	}
	
}
