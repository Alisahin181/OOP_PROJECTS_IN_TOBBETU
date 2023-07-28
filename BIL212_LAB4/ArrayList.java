

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class ArrayList<E> implements List<E> {

  public static final int CAPACITY=16;     // default array capacity


  private E[] data;                        // generic array used for storage


  private int size = 0;                    // current number of elements


  public ArrayList() { this(CAPACITY); }   // constructs list with default capacity


  @SuppressWarnings({"unchecked"})
  public ArrayList(int capacity) {         // constructs list with given capacity
    data = (E[]) new Object[capacity];     // safe cast; compiler may give warning
  }


  public int size() { return size; }


  public boolean isEmpty() { return size == 0; }


  public E get(int i) throws IndexOutOfBoundsException {
    checkIndex(i, size);
    return data[i];
  }


   
  public E set(int i, E e) throws IndexOutOfBoundsException {
    checkIndex(i, size);
    E temp = data[i];
    data[i] = e;
    return temp;
  }


  public void add(int i, E e) throws IndexOutOfBoundsException {
    checkIndex(i, size + 1);
    if (size == data.length)               // not enough capacity
      resize(2 * data.length);             // so double the current capacity
    for (int k=size-1; k >= i; k--)        // start by shifting rightmost
      data[k+1] = data[k];
    data[i] = e;                           // ready to place the new element
    size++;
  }


  public E remove(int i) throws IndexOutOfBoundsException {
    checkIndex(i, size);
    E temp = data[i];
    for (int k=i; k < size-1; k++)         // shift elements to fill hole
      data[k] = data[k+1];
    data[size-1] = null;                   // help garbage collection
    size--;
    return temp;
  }

 
  protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
    if (i < 0 || i >= n)
      throw new IndexOutOfBoundsException("Illegal index: " + i);
  }

  /** Resizes internal array to have given capacity >= size. */
  @SuppressWarnings({"unchecked"})
  protected void resize(int capacity) {
    E[] temp = (E[]) new Object[capacity];     // safe cast; compiler may give warning
    for (int k=0; k < size; k++)
      temp[k] = data[k];
    data = temp;                               // start using the new array
  }


  private class ArrayIterator implements Iterator<E> {
    /** Index of the next element to report. */
    private int j = 0;                   // index of the next element to report
    private boolean removable = false;   // can remove be called at this time?


    public boolean hasNext() { return j < size; }   // size is field of outer instance


    public E next() throws NoSuchElementException {
      if (j == size) throw new NoSuchElementException("No next element");
      removable = true;   // this element can subsequently be removed
      return data[j++];   // post-increment j, so it is ready for future call to next
    }


     public void remove() throws IllegalStateException {
      if (!removable) throw new IllegalStateException("nothing to remove");
      ArrayList.this.remove(j-1);  // that was the last one returned
      j--;                         // next element has shifted one cell to the left
      removable = false;           // do not allow remove again until next is called
      } 
  } //------------ end of nested ArrayIterator class ------------

  private class ArrayListIterator implements ListIterator<E> {
    /** Index of the next element to report. */
    private int j;                   // index of the next element to report
    private boolean removable;   // can remove be called at this time?

    protected ArrayListIterator() {
      j = 0;
      removable = false;
    }

    protected ArrayListIterator(int i) {
      j = i;
      removable = false;
    }

    public boolean hasNext() { return j < size; }   // size is field of outer instance


    public E next() throws NoSuchElementException {
      if (j == size) throw new NoSuchElementException("No next element");
      removable = true;   // this element can subsequently be removed
      return data[j++];   // post-increment j, so it is ready for future call to next
    }

    @Override
    public boolean hasPrevious() {
      return j > 0;
    }

    @Override
    public E previous() {
      if (j == 0) throw new NoSuchElementException("No next element");
      removable = true;   // this element can subsequently be removed
      return data[--j];   // post-increment j, so it is ready for future call to next
    }

    @Override
    public int nextIndex() {
      return j + 1;
    }

    @Override
    public int previousIndex() {
      return j - 1;
    }


    public void remove() throws IllegalStateException {
      if (!removable) throw new IllegalStateException("nothing to remove");
      ArrayList.this.remove(j-1);  // that was the last one returned
      j--;                         // next element has shifted one cell to the left
      removable = false;           // do not allow remove again until next is called
    }

    @Override
    public void set(E e) {
      ArrayList.this.set(j, e);
    }

    @Override
    public void add(E e) {
      ArrayList.this.add(j, e);
    }
  } //------------ end of nested ArrayIterator class ------------


  @Override
  public Iterator<E> iterator() {
    return new ArrayIterator();     // create a new instance of the inner class
  }

  public ListIterator<E> listIterator() {
    return new ArrayListIterator();     // create a new instance of the inner class
  }

  public ListIterator<E> listIterator(int i) {
    return new ArrayListIterator(i);     // create a new instance of the inner class
  }

  public String toString() {
    StringBuilder sb = new StringBuilder("(");
    for (int j = 0; j < size; j++) {
      if (j > 0) sb.append(", ");
      sb.append(data[j]);
    }
    sb.append(")");
    return sb.toString();
  }
}
