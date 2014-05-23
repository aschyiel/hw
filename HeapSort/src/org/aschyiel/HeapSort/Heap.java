package org.aschyiel.HeapSort; 

import java.util.List;

/**
* A very basic interface representing a heap;
* some of the terminology has been borrowed from java.util.PriorityQueue.
*/
public interface Heap<T>
{
  /**
   * Build up a heap given a set of items.
   * 
   * @param items The thing we're copying our items from.
   */
  public void buildHeap( T[] items );
  public void buildHeap( List<T> items );
  
  /**
   * Try to add an item to the heap if it fits.
   */
  void add( T item ); 
  
  /**
   * Returns the number of items currently inside of the heap.
   */
  int size();
  
  /**
   * Returns the heap-sorted product.
   */
  List<T> sort();
  
  /**
   * Look at the top-thing in the heap.
   */
  T peek();

  /**
  * Removes and returns the top-thin in the heap.
  */
  T poll();
}
