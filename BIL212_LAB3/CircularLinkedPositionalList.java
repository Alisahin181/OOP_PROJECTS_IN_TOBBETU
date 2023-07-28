
import java.util.Iterator;
import java.util.NoSuchElementException;

public class  CircularLinkedPositionalList<E extends Comparable<E>> implements PositionalList<E> {

    private static class Node<E> implements Position<E> {

        
        private E element;               // reference to the element stored at this node

        
        private Node<E> prev;            // reference to the previous node in the list

        
        private Node<E> next;            // reference to the subsequent node in the list


        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        public Integer getIntegerAmount() {
            String empty="";
            if(element!=null) {
                empty+=element;
                return Integer.parseInt(empty);	//	Burada alinan E nesnesi string uzerinden parse edilir.
            }
            return 0;
        }

        public E getElement() throws IllegalStateException {
            if (next == null || prev == null) // Check if the node is no longer valid
                throw new IllegalStateException("Position no longer valid");
            return element;
        }


        public Node<E> getPrev() {
            return prev;
        }


        public Node<E> getNext() {
            return next;
        }

        public void setElement(E e) {
            element = e;
        }


        public void setPrev(Node<E> p) {
            prev = p;
        }


        public void setNext(Node<E> n) {
            next = n;
        }
    }
    //----------- end of nested Node class -----------

    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Invalid p");
        Node<E> node = (Node<E>) p;     // safe cast
        if (node.getNext() == null)     // convention for defunct node
            throw new IllegalArgumentException("p is no longer in the list");
        return node;
    }
    
    
    private Node<E> header;
    private int size;

    /** Constructs a new empty list. */
    public CircularLinkedPositionalList() {
    	
        header = new Node<>(null, null, null);
        header.setPrev(header);
        header.setNext(header);
        size = 0;

    }



    private Position<E> position(Node<E> node) {
        if (node== null || node== header)
            return null;
        
        return node;
    }
    

    @Override
    public int size() {
    	
    	return size;
    }


    @Override
    public boolean isEmpty() {
    	
    	return size==0;
    }


    @Override
    public Position<E> first() {
    	
    	return position(header.getNext());

    }

    @Override
    public Position<E> last() {
    	
    	return position(header.getPrev());
    }


    @Override
    public Position<E> before(Position<E> p) throws IllegalArgumentException {

    	Node<E> node= validate(p);
    	return position(node.getPrev());
    }

    @Override
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
    	
        Node<E> node = validate(p);
        return position(node.getNext());
    	
    }


    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {

    	Node<E> newNode = new Node<>(e, pred, succ);
    	pred.setNext(newNode);
    	succ.setPrev(newNode);
    	size++;
    	return newNode;
    }

    @Override
    public Position<E> addFirst(E e) {       // just after the header
    	
    	return addBetween(e, header, header.getNext());
    }

    @Override
    public Position<E> addLast(E e) {
    	return addBetween(e, header.getPrev(), header);  // just before the header
    }


    @Override
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
    	
    	Node<E> node = validate(p);
    	return addBetween(e, node.getPrev(), node);
    	
    }

    @Override
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
    	
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    @Override
    public E set(Position<E> p, E e) throws IllegalArgumentException {

        Node<E> node = validate(p);
        E replacedElement = node.getElement();
        node.setElement(e);
        return replacedElement;
        
    }


    @Override
    public E remove(Position<E> p) throws IllegalArgumentException {

    	Node<E> node = validate(p);
    	Node<E> predecessor = node.getPrev();
    	Node<E> successor = node.getNext();
    	predecessor.setNext(successor);
    	size--;
    	E removedElement = node.getElement();
        node.setElement(null);
        node.setPrev(null);
        node.setNext(null);
        return removedElement;
    }

    public String toString() {
        if (isEmpty()) {
            return "liste: header -> header";
        } else {
            StringBuilder sb = new StringBuilder("liste: header -> ");
            Node<E> current = header.getNext();
            while (current != header) {
                sb.append(current.getElement());
                sb.append(" -> ");
                current = current.getNext();
                if (current == header) {
                    sb.append("header");
                }
            }
            return sb.toString();
        }
    }

















    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    private class ElementIterator implements Iterator<E> {
        private Node<E> current = header.getNext();
        private boolean removable = false;

        public boolean hasNext() {
            return current != header;
        }

        public E next() throws NoSuchElementException {
            if (current == header)
                throw new NoSuchElementException("No more elements");
            E nextElement = current.getElement();
            current = current.getNext();
            removable = true;
            return nextElement;
        }

        public void remove() throws IllegalStateException {
            if (!removable)
                throw new IllegalStateException("Cannot remove");
            Node<E> predecessor = current.getPrev();
            Node<E> successor = current.getNext();
            predecessor.setNext(successor);
            successor.setPrev(predecessor);
            size--;
            removable = false;
        }
    }
    
}