import java.util.ArrayList;
import java.util.EmptyStackException;

public class Deque<E> {
	
	 class Stack<E> {

		private ArrayList<E> stackArray;
		private int capacity;
		
		public Stack() {
			
			capacity=0;
			stackArray= new ArrayList<>();
			
		}
		
		public void push(E element) {

				stackArray.add(element);
				capacity++;
			
		}
		
	    public E pop() {
	    	
	        if (capacity == 0) {
	            throw new EmptyStackException();
	        }else {
	        	
	        	
	            return stackArray.remove(--capacity);
	        	
	        }	

	            
	    }
	    
	    public E peek() {
	    	
	        if (capacity == 0) {
	            throw new EmptyStackException();
	        }
	        
	    	return stackArray.get(capacity-1);
	    }
	    
	    public boolean empty() {
	    	
	    	return capacity==0;
	    }
	    
	    public int capacity() {
	        return capacity;
	    }
	    
	    @Override
	    public String toString() {
	        if (capacity == 0) {
	            return "";
	        }

	        StringBuilder sb = new StringBuilder();
	        for (int i = capacity-1; i > 0; i--) {
	            sb.append(stackArray.get(i));
	            if (i > 0) {
	                sb.append(", ");
	            }
	        }
	        sb.append(stackArray.get(0));
	        

	        return sb.toString();
	    }
	       
	}
	
	private Stack<E> stack1;
	private Stack<E> stack2;
	
	public Deque() {
		
		stack1= new Stack<>();
		stack2=new Stack<>();
	}
	
	public void addFirst(E element) {
		
		stack1.push(element);
		
	}
	
	public void addLast(E element) {
		
		while(!stack1.empty()) {
			
			stack2.push(stack1.pop());
			
		}
		
		stack1.push(element);
		
		while(!stack2.empty())
			stack1.push(stack2.pop());
	}
	
	public E removeFirst() {
		
		if(isEmpty())
			return null;
		
		return stack1.pop();
		
	}
	
	public E removeLast() {
		
		while(stack1.capacity()>1) {
			
			stack2.push(stack1.pop());
		}
		
		E lastElement=stack1.pop();
		
		while(!stack2.empty())
			stack1.push(stack2.pop());
		
		return lastElement;
		
	}
	
	public E first() {
		
		if(isEmpty())
			return null;
		
		return stack1.peek();
		
	}
	
	public E last() {
		
		if(isEmpty())
			return null;
		
		while(!stack1.empty())
			stack2.push(stack1.pop());
		
		E lastElement=stack2.peek();
		
		while(!stack2.empty())
			stack1.push(stack2.pop());
		
		return lastElement;
		
	}
	
    public int size() {
        return stack1.capacity();
    }
    
    public void reverse() {
    	
    	while(!stack1.empty())
    		stack2.push(stack1.pop());
    	
    	Stack<E> temp= stack1;
    	stack1=stack2;
    	stack2=temp;
    	
    }
    
    public String toString() {
    	
    	return stack1.toString();
    	
    }
	
    public boolean isEmpty() {
        return stack1.empty();
    }

}
