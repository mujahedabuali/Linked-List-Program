package application;

import java.util.Date;
import java.util.LinkedList;

//A MartyrList class is it a doubly linked list to make a list of Martyr type
//To save a Martyr and make operaiton in it
//The class have many method to make many opareation in the Martyr object

public class MartyrList {

	public DoublyNode Front,Rear;
	private int size;
	
	public MartyrList() {
		Front=Rear=null;
		size=0;
	}
	
	public Martyr getFirst() {
		if(size==0)return null;
		else return (Martyr) Front.getData();
	}
	public Martyr getLast() {
		if(size==0)
			return null;
		else
			return (Martyr) Rear.getData();
	}
	
	public Martyr get(int index) {
		if (index < 0 || index > size)throw new IndexOutOfBoundsException();
	    else if(index==0)return getFirst();
	    else if(index==size-1)return getLast();
	    
		DoublyNode curr = Front;
	   
	    int i=1;
		while (i < index - 1 && curr.next != null) {
         curr = curr.next;
         i++;
		}
	    return (Martyr) curr.getData();
	}
	public int getSize() {
		return size;
	}
	//Add martyr and sorted it
	 public void add(Martyr m) {
	        DoublyNode newNode = new DoublyNode(m);

	        if (Front == null) 
	        	Front =Rear= newNode;
	         else {
	        	DoublyNode c = Front;
	            while (c != null && m.getDateOfDeath().compareTo(((Martyr)c.getData()).getDateOfDeath()) > 0) {
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
	
    public int getIndex(String name) {
		DoublyNode c = Front;
	    int index = 0;
	    while (c != null) {
	        if (((Martyr)c.getData()).getName().equalsIgnoreCase(name)) {
	            return index;
	        }
	        c = c.next;
	        index++;
	    }
	    return -1; 
	}
    //Update method for martyr object
	public void update(int index,String name) {
		DoublyNode c = Front;
		int currIndex =0;
		while (c != null&&currIndex!=index) {
			c = c.next;
			currIndex++;
	        }
		if (c != null) 
			((Martyr)c.getData()).setName(name);	
		else
			throw new IndexOutOfBoundsException();
}
	public void update(int index,byte age) {
		DoublyNode c = Front;
		int currIndex =0;
		while (c != null&&currIndex!=index) {
			c = c.next;
			currIndex++;
	        }
		if (c != null) 
			((Martyr)c.getData()).setAge(age);
		else
			throw new IndexOutOfBoundsException();
}
	public void update(int index,Date date,String name,byte age,char gender) {
		
           remove(index);
           add(new Martyr(name,age,date,gender));
           
  
}
	
	public void update(int index,char gender) {
		DoublyNode c = Front;
		int currIndex =0;
		while (c != null&&currIndex!=index) {
			c = c.next;
			currIndex++;
	        }
		if (c != null) 
			((Martyr)c.getData()).setGender(gender);
		else
			throw new IndexOutOfBoundsException();
}
	public LinkedList<Martyr> getAll() {
		
		LinkedList<Martyr> martyer = new LinkedList<>();
		DoublyNode c = Front;
		while (c != null) {
			martyer.add((Martyr) c.getData());
			c = c.next;
		}        
		return martyer;
	}
	//Search method
	public boolean search(String name) {
		DoublyNode c = Front;
	  
	    while (c != null) {
	        if (((Martyr) c.getData()).getName().equalsIgnoreCase(name)) 
	            return true;
	        c = c.next;
	    }
	    return false; 
	}
	
	    
}