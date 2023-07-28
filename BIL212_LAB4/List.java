
import java.util.Iterator;


public interface List<E> extends Iterable<E> {

  int size();

  /**
   * Tests whether the list is empty.
   * @return true if the list is empty, false otherwise
   */
  boolean isEmpty();


  E get(int i) throws IndexOutOfBoundsException;


  E set(int i, E e) throws IndexOutOfBoundsException;


  void add(int i, E e) throws IndexOutOfBoundsException;


  E remove(int i) throws IndexOutOfBoundsException;


  Iterator<E> iterator();
}