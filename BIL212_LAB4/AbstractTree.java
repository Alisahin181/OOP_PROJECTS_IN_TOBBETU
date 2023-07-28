

import java.util.Iterator;
import java.util.List;         // for use as snapshot iterator
import java.util.ArrayList;    // for use as snapshot iterator


public abstract class AbstractTree<E> implements Tree<E> {


  @Override
  public boolean isInternal(Position<E> p) { return numChildren(p) > 0; }


  @Override
  public boolean isExternal(Position<E> p) { return numChildren(p) == 0; }


  @Override
  public boolean isRoot(Position<E> p) { return p == root(); }


  @Override
  public int numChildren(Position<E> p) {
    int count=0;
    for (Position child : children(p)) count++;
    return count;
  }


  @Override
  public int size() {
    int count=0;
    for (Position p : positions()) count++;
    return count;
  }


  @Override
  public boolean isEmpty() { return size() == 0; }

  public int depth(Position<E> p) throws IllegalArgumentException {
    if (isRoot(p))
      return 0;
    else
      return 1 + depth(parent(p));
  }


  private int heightBad() {             // works, but quadratic worst-case time
    int h = 0;
    for (Position<E> p : positions())
      if (isExternal(p))                // only consider leaf positions
        h = Math.max(h, depth(p));
    return h;
  }


  public int height(Position<E> p) throws IllegalArgumentException {
    int h = 0;                          // base case if p is external
    for (Position<E> c : children(p))
      h = Math.max(h, 1 + height(c));
    return h;
  }


  private class ElementIterator implements Iterator<E> {
    Iterator<Position<E>> posIterator = positions().iterator();
    public boolean hasNext() { return posIterator.hasNext(); }
    public E next() { return posIterator.next().getElement(); } // return element!
    public void remove() { posIterator.remove(); }
  }

  @Override
  public Iterator<E> iterator() { return new ElementIterator(); }


  @Override
  public Iterable<Position<E>> positions() { return preorder(); }


  private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
    snapshot.add(p);                       // for preorder, we add position p before exploring subtrees
    for (Position<E> c : children(p))
      preorderSubtree(c, snapshot);
  }


  public Iterable<Position<E>> preorder() {
    List<Position<E>> snapshot = new ArrayList<>();
    if (!isEmpty())
      preorderSubtree(root(), snapshot);   // fill the snapshot recursively
    return snapshot;
  }


  private void postorderSubtree(Position<E> p, List<Position<E>> snapshot) {
    for (Position<E> c : children(p))
      postorderSubtree(c, snapshot);
    snapshot.add(p);                       // for postorder, we add position p after exploring subtrees
  }


  public Iterable<Position<E>> postorder() {
    List<Position<E>> snapshot = new ArrayList<>();
    if (!isEmpty())
      postorderSubtree(root(), snapshot);   // fill the snapshot recursively
    return snapshot;
  }


  public Iterable<Position<E>> breadthfirst() {
    List<Position<E>> snapshot = new ArrayList<>();
    if (!isEmpty()) {
      Queue<Position<E>> fringe = new LinkedQueue<>();
      fringe.enqueue(root());                 // start with the root
      while (!fringe.isEmpty()) {
        Position<E> p = fringe.dequeue();     // remove from front of the queue
        snapshot.add(p);                      // report this position
        for (Position<E> c : children(p))
          fringe.enqueue(c);                  // add children to back of queue
      }
    }
    return snapshot;
  }
}
