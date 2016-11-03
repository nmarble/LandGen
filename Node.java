
public class Node {
	int x;
	int y;
	double height;
	public Node(int x, int y, double h) {
		this.x = x;
		this.y = y;
		height = h;
	}
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() { return x;}
	public int getY() { return y;}
	public void setHeight(double h) {
		height = h;
	}
	public double getHeight() { return height;}
	public void printInfo() {
		System.out.format("X: %d Y: %d H: %f\n", x, y, height);
	}
	public double getDistance(Node node) {
		return Math.sqrt(Math.pow((node.getX() - x), 2) + Math.pow((node.getY() - y), 2)); 
	}
	public double getDistanceXY(int x2, int y2) {
		return Math.sqrt(Math.pow((x2 - x), 2) + Math.pow((y2 - y), 2)); 
	}

}
