package core;

public class SortedLinkedList {
	
	private ListNode head = null;
	private ListNode tail = null;
	private int size = 0;
	private int coordid;
	
	public SortedLinkedList(int coordid) {
		this.coordid = coordid;
	}
	
	public synchronized void insert(Point item) {
		ListNode itemnode = new ListNode(item);
		ListNode current = head;
		int iteration = 0;
		while (current != null && current.data.coord[coordid] < item.coord[coordid]) {
			current = current.next;
			iteration++;
		}
		if (iteration == 0) {
			if (head == null) 
				tail = itemnode;
			else {
				head.prev = itemnode;
				itemnode.next = head;
			}
			head = itemnode;
		} else if (iteration == size) {
			tail.next = itemnode;
			itemnode.prev = tail;
			tail = itemnode;
		} else {
			itemnode.next = current;
			itemnode.prev = current.prev;
			current.prev.next = itemnode;
			current.prev = itemnode;
		}
		size++;
	}
	
	public ListNode getFirst() {
		return head;
	}
	
	public ListNode getLast() {
		return tail;
	}
	
	public int size() {
		return size;
	}
	
	public double max() {
		return head.data.coord[coordid];
	}
	
	public double min() {
		return head.data.coord[coordid];
	}
	
	public ListIterator iterator() {
		return new ListIterator(head);
	}
	
	public void printList() {
		ListIterator iterator = iterator();
		while (iterator.hasNext()) {
			iterator.next().print();
		}
	}
}

class ListIterator {
	
	private ListNode current;
	
	public ListIterator(ListNode start) {
		current = start;
	}
	
	public boolean hasNext() {
		return (current != null) ? true:false;
	}
	
	public Point next() {
		ListNode val = current;
		current = current.next;
		return val.data;
	}
}