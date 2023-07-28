
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

  //---------------- nested Node class ----------------
  /** Nested static class for a binary tree node. */
  protected static class Node<E> implements Position<E> {
    private E element;          // an element stored at this node
    private Node<E> parent;     // a reference to the parent node (if any)
    private Node<E> left;       // a reference to the left child (if any)
    private Node<E> right;      // a reference to the right child (if any)


    public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
      element = e;
      parent = above;
      left = leftChild;
      right = rightChild;
    }

    // accessor methods
    public E getElement() { return element; }
    public Node<E> getParent() { return parent; }
    public Node<E> getLeft() { return left; }
    public Node<E> getRight() { return right; }

    // update methods
    public void setElement(E e) { element = e; }
    public void setParent(Node<E> parentNode) { parent = parentNode; }
    public void setLeft(Node<E> leftChild) { left = leftChild; }
    public void setRight(Node<E> rightChild) { right = rightChild; }
  } //----------- end of nested Node class -----------

  /** Factory function to create a new node storing element e. */
  protected Node<E> createNode(E e, Node<E> parent,
                                  Node<E> left, Node<E> right) {
    return new Node<E>(e, parent, left, right);
  }

  // LinkedBinaryTree instance variables
  /** The root of the binary tree */
  protected Node<E> root = null;     // root of the tree

  /** The number of nodes in the binary tree */
  private int size = 0;              // number of nodes in the tree


  public LinkedBinaryTree() { }      // constructs an empty binary tree


  protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
    if (!(p instanceof Node))
      throw new IllegalArgumentException("Not valid position type");
    Node<E> node = (Node<E>) p;       // safe cast
    if (node.getParent() == node)     // our convention for defunct node
      throw new IllegalArgumentException("p is no longer in the tree");
    return node;
  }
  

  public Iterable<Position<E>> diagonalTraversal() {
	  
      if (this.root() == null) return null;

      List<Position<E>> caprazElemanlar = new ArrayList<>();
      int i = 0;
      
      SinglyLinkedList<Position<E>> oncekiCapraz = new SinglyLinkedList<>();
      SinglyLinkedList<Position<E>> simdikiCapraz = new SinglyLinkedList<>();

      Position<E> cur = this.root();
      if (caprazElemanlar.isEmpty()) {
          while (cur != null) {
        	  simdikiCapraz.addLast(cur);
              cur = right(cur);
          }
      }

      while (true) {
    	  
          if (!caprazElemanlar.isEmpty()) {
        	  caprazElemanlar.add(i,new Node<E>(null, null, null, null));
              i++;
          }

          while (!simdikiCapraz.isEmpty()) {
        	  caprazElemanlar.add(i, simdikiCapraz.first());
              i++;
              oncekiCapraz.addLast(simdikiCapraz.removeFirst());
          }

          cur = oncekiCapraz.removeFirst();
          if (left(cur) ==null) break;
          
          while (true) {
        	  
        	  
              if (left(cur) != null) {
                  Position<E> x =left(cur);
                  simdikiCapraz.addLast(x);
                  
                  
                  while (right(x) != null) {
                	  simdikiCapraz.addLast(right(x));
                      x =right(x);
                  }
                  
                  
              }

              if (oncekiCapraz.first() != null)
                  cur =oncekiCapraz.removeFirst();
              else
                  break;
          }
      }

      return caprazElemanlar;
  
} 





  @Override
  public int size() {
    return size;
  }


  @Override
  public Position<E> root() {
    return root;
  }


  @Override
  public Position<E> parent(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return node.getParent();
  }


  @Override
  public Position<E> left(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return node.getLeft();
  }


  @Override
  public Position<E> right(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return node.getRight();
  }


  public Position<E> addRoot(E e) throws IllegalStateException {
    if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
    root = createNode(e, null, null, null);
    size = 1;
    return root;
  }


  public Position<E> addLeft(Position<E> p, E e)
                          throws IllegalArgumentException {
    Node<E> parent = validate(p);
    if (parent.getLeft() != null)
      throw new IllegalArgumentException("p already has a left child");
    Node<E> child = createNode(e, parent, null, null);
    parent.setLeft(child);
    size++;
    return child;
  }


  public Position<E> addRight(Position<E> p, E e)
                          throws IllegalArgumentException {
    Node<E> parent = validate(p);
    if (parent.getRight() != null)
      throw new IllegalArgumentException("p already has a right child");
    Node<E> child = createNode(e, parent, null, null);
    parent.setRight(child);
    size++;
    return child;
  }


  public E set(Position<E> p, E e) throws IllegalArgumentException {
    Node<E> node = validate(p);
    E temp = node.getElement();
    node.setElement(e);
    return temp;
  }


  public void attach(Position<E> p, LinkedBinaryTree<E> t1,
                    LinkedBinaryTree<E> t2) throws IllegalArgumentException {
    Node<E> node = validate(p);
    if (isInternal(p)) throw new IllegalArgumentException("p must be a leaf");
    size += t1.size() + t2.size();
    if (!t1.isEmpty()) {                  // attach t1 as left subtree of node
      t1.root.setParent(node);
      node.setLeft(t1.root);
      t1.root = null;
      t1.size = 0;
    }
    if (!t2.isEmpty()) {                  // attach t2 as right subtree of node
      t2.root.setParent(node);
      node.setRight(t2.root);
      t2.root = null;
      t2.size = 0;
    }
  }


  public E remove(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    if (numChildren(p) == 2)
      throw new IllegalArgumentException("p has two children");
    Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight() );
    if (child != null)
      child.setParent(node.getParent());  // child's grandparent becomes its parent
    if (node == root)
      root = child;                       // child becomes root
    else {
      Node<E> parent = node.getParent();
      if (node == parent.getLeft())
        parent.setLeft(child);
      else
        parent.setRight(child);
    }
    size--;
    E temp = node.getElement();
    node.setElement(null);                // help garbage collection
    node.setLeft(null);
    node.setRight(null);
    node.setParent(node);                 // our convention for defunct node
    return temp;
  }

  public void addOrdered(E element){

    Position<E> temp = this.root;
    recur(element,temp);

  }

  public void recur(E element, Position<E> temp){

    if(this.isEmpty()){
      this.addRoot(element);
    }
    else{
      if((Integer)element < (Integer)temp.getElement()){
        if(this.left(temp) == null)
          this.addLeft(temp,element);
        else{
          recur(element, this.left(temp));
        }
      }
      else{
        if(this.right(temp) == null)
          this.addRight(temp,element);
        else{
          recur(element, this.right(temp));
        }
      }
    }
  }

} //----------- end of LinkedBinaryTree class -----------
