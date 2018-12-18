package core;

public class RawPly {
	
	private Point[] vertices;
	private int curindex = 0;
	
	public double[] max = new double[3];
	public double[] min = new double[3];
	
	public RawPly(int size) {
		vertices = new Point[size];
	}
	
	public void insert(double x, double y, double z) {
		Point insert = new Point(x, y, z);
		vertices[curindex] = insert;
		curindex++;
		if (x > max[0])
			max[0] = x;
		else if (x < min[0])
			min[0] = x;
		if (y > max[1])
			max[1] = y;
		else if (y < min[1])
			min[1] = y;
		if (z > max[2])
			max[2] = z;
		else if (z < min[2])
			min[2] = z;
	}
	
	public Point get(int index) {
		return vertices[index];
	}
	
	public Point[] getList() {
		return vertices;
	}
	
	public int size() {
		return vertices.length;
	}
	
	public int getCoordID() {
		double xdif = Math.abs(max[0] - min[0]);
		double ydif = Math.abs(max[1] - min[1]);
		double zdif = Math.abs(max[2] - min[2]);
		if (xdif > ydif && xdif > zdif)
			return 0;
		else if (ydif > xdif && ydif > zdif)
			return 1;
		else
			return 2;
	}
	
	public PlyIterator iterator() {
		return new PlyIterator(vertices, 0);
	}
	
	public void printList() {
		PlyIterator iterator = iterator();
		while (iterator.hasNext()) {
			iterator.next().print();
		}
	}
}

class PlyIterator {
	
	private Point[] array;
	private int current;
	
	public PlyIterator(Point[] array, int start) {
		this.array = array;
		current = start;
	}
	
	public boolean hasNext() {
		return (current < array.length) ? true:false;
	}
	
	public Point next() {
		int val = current;
		current++;
		return array[val];
	}
}