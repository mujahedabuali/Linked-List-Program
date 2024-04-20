package application;

import java.util.LinkedList;

//A LocationList class is it a doubly linked list to make a list of location type
//To save a location and make operaiton in it
//The class have many method to make many opareation in the location object


public class LocationList {

	private DoublyNode Front,Rear;
	private int size;
	
	public LocationList() {
		Front=Rear=null;
		size=0;
		}
	
	public int getSize() {
		return size;
	}
	//Add object sorted to location list 
	 public void add(Location location) {
	        DoublyNode newNode = new DoublyNode(location);

	        if (Front == null) 
	        	Front =Rear= newNode;
	         else {
	        	DoublyNode c = Front;
	        	//O(n)
	            while (c != null && location.getName().compareTo(((Location)c.getData()).getName()) > 0) {
	                c = c.next;
	            }
	            if (c == null) {
	                Rear.next = newNode;
	                newNode.prev = Rear;
	                Rear = newNode;
	            } else if (c.prev == null) {
	                newNode.next = Front;
	                Front.prev = newNode;
	                Front = newNode;
	            } else {
	                newNode.next = c;
	                newNode.prev = c.prev;
	                c.prev.next = newNode;
	                c.prev = newNode;
	            }
	            size++;
	        }
	 }
	// remove object and keep linked list sorted
	public void remove(int index) {
		if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            Front = Front.next;
            if (Front != null) {
            	Front.prev = null;
            }else Rear = null;
            
        } else if (index == getSize() - 1) {
            Rear = Rear.prev;
            if (Rear != null) {
            	Rear.next = null;
            }else
                Front = null;
            
        } else {
            DoublyNode c = Front;
            int cIndex = 0;
            //O(n)
            while (c != null && cIndex < index) {
                c = c.next;
                cIndex++;
            }
            c.prev.next = c.next;
            c.next.prev = c.prev;
        }
    }
	public boolean isEmpty() {
		return Front == null;
	}
	// Search a object in linked list
	public boolean search(String name) {
		DoublyNode c = Front;
		//O(n)
	    while (c != null) {
	        if (((Location) c.getData()).getName().equalsIgnoreCase(name)) 
	            return true;
	        c = c.next;
	    }
	    return false; 
	}
	public int getIndex(String name) {
		DoublyNode c = Front;
	    int index = 0;
	    while (c != null) {
	        if (((Location)c.getData()).getName().equalsIgnoreCase(name)) {
	            return index;
	        }
	        c = c.next;
	        index++;
	    }
	    return -1; 
	}
	// Update linked list and keep t sorted
	public void update(int index,String name) {
		 if (index < 0 || index >= getSize()) {
	            throw new IndexOutOfBoundsException("Invalid index");
	        }
	        DoublyNode c = Front;
	        int Index = 0;
	        //O(n)
	        while (c != null && Index < index) {
	            c = c.next;
	            Index++;
	        }
	        if (c != null) {
	        	((Location)c.getData()).setName(name);
	            while (c.prev != null &&((Location)c.getData()).getName().compareTo(((Location)c.prev.getData()).getName()) < 0) {
	                Location l = (Location)c.getData();
	                c.setData(c.prev.getData());
	                c.prev.setData(l);
	                c = c.prev;
	            }
	            //O(n)
	            while (c.next != null && ((Location)c.getData()).getName().compareTo(((Location)c.next.getData()).getName()) > 0) {
	                Location l = (Location)c.getData();
	                c.setData(c.next.getData());
	                c.next.setData(l);
	                c = c.next;
	            }
	        }
	    }
	
	 public LinkedList<Location> getAll() {
		    LinkedList<Location> l = new LinkedList<>();
	        DoublyNode c = Front;
	        while (c != null) {
	        	l.add((Location) c.getData());
	            c = c.next;
	        }        
	        return l;
	    }
	    
	public Location get(int index) {
		if (index < 0 || index > size)throw new IndexOutOfBoundsException();
		
		DoublyNode curr = Front;
		int i=0;
		while (i < index && curr.next != null) {
			curr = curr.next;
			i++;
		}
		return (Location) curr.getData();
	}
}