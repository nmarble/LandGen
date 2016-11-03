
public class Node {
	int x;
	int y;
	double height;
	public Node(int x, int y, double h) {
		this.x = x;
		this.y = y;
		height = h;
	}
	public int getX() { return x;}
	public int getY() { return y;}
	public double getHeight() { return height;}

	@Override
	public String toString() {
		return "Node{" +
				"x=" + x +
				", y=" + y +
				", height=" + height +
				'}';
	}

	public double getDistance(Node node) {
		return Math.sqrt(Math.pow((node.getX() - x), 2) + Math.pow((node.getY() - y), 2)); 
	}
	public double getDistanceXY(int x2, int y2) {
		return Math.sqrt(Math.pow((x2 - x), 2) + Math.pow((y2 - y), 2)); 
	}

}
