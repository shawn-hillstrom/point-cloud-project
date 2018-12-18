package core;

public class Point {
	double [] coord = new double[3];
	double x;
	double y;
	double z;
	
	public Point(double x, double y, double z) {
		coord[0] = x;
		coord[1] = y;
		coord[2] = z;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void print() {
		System.out.println(x + " " + y + " " + z);
	}
}
