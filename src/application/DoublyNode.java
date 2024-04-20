package application;

	//Class DoublyNode to make a doublyLinked list
	//Use this class to save the data in his nods and put it in linked list
	//This class can use for All linked list(Martyr,Location)

public class DoublyNode {

	private Object data;
	public DoublyNode next,prev;
	//construct
	public DoublyNode(Object data) {
		this(data,null,null);
	}
	public DoublyNode(Object data, DoublyNode next, DoublyNode prev) {
		this.data = data;
		this.next = next;
		this.prev = prev;
	}
	// Getter and setter
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}