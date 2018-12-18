package core;

public class HashedList {
	
	private int size;
	private int coordid;
	private double max;
	private double min;
	
	private SortedLinkedList[] hashlist;
	
	public HashedList(int size, int coordid, double max, double min) {
		
		this.size = size;
		this.coordid = coordid;
		this.max = max;
		this.min = min;
		
		hashlist = new SortedLinkedList[size];
		
		for (int i = 0; i < size; i++) {
			hashlist[i] = new SortedLinkedList(coordid);
		}
	}
	
	public void insert(Point item) {
		double multfactor = (size-1)/(max-min);
		int hashindex = hashcode(item.coord[coordid]-min, multfactor);
		hashlist[hashindex].insert(item);
	}
	
	public int hashcode(double data, double multfactor) {
		return (int)(multfactor*data);
	}
	
	public int size() {
		return size;
	}
	
	public double max() {
		return max;
	}
	
	public double min() {
		return min;
	}
	
	public SortedLinkedList[] getList() {
		return hashlist;
	}
	
	public HashIterator iterator() {
		return new HashIterator(hashlist, 0);
	}
	
	public void printList() {
		HashIterator shell = iterator();
		while (shell.hasNext()) {
			ListIterator subiterator = shell.next().iterator();
			if (!subiterator.hasNext())
				System.out.println("Null");
			else {
				while (subiterator.hasNext()) {
					subiterator.next().print();
				}
			}
		}
	}
}

class HashIterator {
	
	private SortedLinkedList[] array;
	private int current;
	
	public HashIterator(SortedLinkedList[] array, int start) {
		this.array = array;
		current = start;
	}
	
	public boolean hasNext() {
		return (current < array.length) ? true:false;
	}
	
	public SortedLinkedList next() {
		int val = current;
		current++;
		return array[val];
	}
}