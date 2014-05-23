package org.aschyiel.HeapSort; 

public interface Heap<T>
{
  /**
   * Build up a heap given a set of items.
   * 
   * @param items The thing we're copying our items from.
   */
  public void buildHeap( T[] items );
  
  /**
   * Try to add an item to the heap if it fits.
   */
  void insert( T item ); 
  
  /**
   * Returns the number of items currently inside of the heap.
   */
  int size();
  
  /**
   * Returns the heap-sorted product.
   */
  T[] sort();
  
  /**
   * Look at the top-thing in the heap.
   */
  T peek();
}
