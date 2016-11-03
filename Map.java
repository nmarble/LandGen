import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Map {
	private int xSize;
	private int ySize;
	private List<Node> nodes = new ArrayList<>();
	Random randomGenerator = new Random();
	private double maxHeight = 255;
	
	public Map(int x, int y) {
		xSize = x;
		ySize = y;
	}
	public Map() {
		xSize = 100;
		ySize = 100;
		fillMap();
	}
	private int getXSize() {return xSize;}
	private int getYSize() {return ySize;}
	public List<Node> getNodes() {return nodes;}
	private void fillMap() {
		getPrimeNode();
		int x, y;
		List<Node> closestNode = new ArrayList<>();
		boolean duplicate;
		for (int i = 0; i < (xSize * ySize) - 3; i++) {
			do {
				duplicate = false;
				x = randomGenerator.nextInt(xSize);
				y = randomGenerator.nextInt(ySize);
				for (Node node : nodes) {
					if (node.getX() == x && node.getY() == y) {
						duplicate = true;
					}
				}
			}while (duplicate);
			
			for (Node node: nodes) {
				if (closestNode.size() < 3) {
					closestNode.add(node);
				}else {
					for (Node closeNode: closestNode) {
						if (node.getDistanceXY(x, y) < closeNode.getDistanceXY(x, y)) {
							closestNode.remove(closeNode);
							closestNode.add(node);
							break;
						}
					}
				}
			}
			Double h = 0.0;
			for (Node node: closestNode) {
				h += node.getHeight();
			}
			int dif = randomGenerator.nextInt(20) - 10;
			h = (h / 3) + dif;
			if (h < 0) {
				h = 0.0;
			}else if( h > maxHeight) {
				h = maxHeight;
			}
			Node newNode = new Node(x,y, h);
			nodes.add(newNode);
			closestNode.clear();
		}
	}
	private void getPrimeNode() {
		for(int i = 0; i < 3; i++) {
			int x = randomGenerator.nextInt(xSize);
			int y = randomGenerator.nextInt(ySize);
			double h = (randomGenerator.nextDouble() * maxHeight);
			nodes.add(new Node(x,y,h));
		}
	}

	@Override
	public String toString() {
		return "Map{" +
				"xSize=" + xSize +
				", ySize=" + ySize +
				", nodes=" + nodes.stream().map(Object::toString).collect(Collectors.joining("\n")) +
				", maxHeight=" + maxHeight +
				'}';
	}
}
