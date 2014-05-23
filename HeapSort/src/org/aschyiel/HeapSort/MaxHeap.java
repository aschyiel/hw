package org.aschyiel.HeapSort; 

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaxHeap<T> implements Heap<T>
{
  
  /**
   * The actual items.  I'd use an actual array directly instead
   * of an array-list, but, well. yeah.
   */
  protected List<T> items;
  
  /**
   * The maximum number of items this heap can manage - ie. 100.
   */
  private Integer capacity;
  
  private int _size = 0;
  
  public MaxHeap( int capacity )
  {
    this.capacity = capacity;
    prepareArrayList(); 
  } 
  
  private void prepareArrayList()
  {
    items = new ArrayList<T>( capacity ); 

    // Workaround for indexing bug.
    for ( int i = capacity; i-- > 0;  )
    {
      items.add( null );
    }
  }

  @Override
  public void buildHeap( T[] items )
  {
    _size = 0;
    capacity = items.length;
    prepareArrayList(); 
    for ( int i = 0; i < items.length; i++ )
    {
      this.items.set( i, items[i] );
      _size++;
    }
    rebuildHeap();
  }

  /**
  * Organize the heap from the bottom-up.
  */
  private void rebuildHeap()
  { 
    for ( int i = size() / 2; i > 0; i-- )
    {
      heapify( i );
    }
  }

  /**
   * Recursively heapifies from top-to-bottom (usually it's bottom-to-top).
   */
  private void reverseHeapify( int idx )
  {
    if ( idx > _size )
    {
      return;
    }
    T item   = getItem(     idx );
    T left   = getItem( 2 * idx );
    T right  = getItem( 2 * idx + 1 );
    
    boolean goLeft  = null != left  && needsSwapped( item, left );
    boolean goRight = null != right && needsSwapped( item, right ); 
    if ( goLeft && goRight )
    { 
      if ( -1 == comparator.compare( left, right ) )
      {
        goRight = false;
      }
      else
      {
        goLeft = false;
      }
    }

    if ( goLeft )
    {
      setItem(     idx, left );
      setItem( 2 * idx, item );
      reverseHeapify( idx * 2 );
    }
    else if ( goRight )
    {
      setItem(     idx,     right );
      setItem( 2 * idx + 1, item );
      reverseHeapify( idx * 2 + 1 );
    }
  }
  
  /**
   * Recursively heapifies starting at the given index and sifts up.
   */
  private void heapify( int idx )
  {
    if ( idx == 0 )
    {
      return;
    }
    
    // TODO: Abstract accessing heap-index from array-index which is off by one.
    // Return null items if index-out-of0-bounds.
    
    T item   = getItem(     idx );
    T left   = getItem( 2 * idx );
    T right  = getItem( 2 * idx + 1 );
    T parent = getItem(     idx / 2 ); 

    //
    // Bubble-up smaller items in our max-heap as necessary.
    // 
    
    if ( null != left && needsSwapped( item, left ) )
    {
      setItem(     idx, left );
      setItem( 2 * idx, item );
    }
    else if ( null != right && needsSwapped( item, right ) )
    {
      setItem(     idx,     right );
      setItem( 2 * idx + 1, item );
    }
    else if ( null != parent && needsSwapped( parent, item ) )
    {
      setItem( idx,     parent );
      setItem( idx / 2, item );
    }
    heapify( idx / 2);
  } 
  
  /**
   * Returns the item by index;
   * Secretly converts the heap's one-based indices
   * to the array-list's zero-based indices...
   */
  private T getItem( int idx )
  {
    if ( idx > _size )
    {
      return null;
    }
    idx--; 
    return ( idx > -1 )? items.get( idx ) : null;
  }
  
  /**
   * Another heap vs. array -ism.
   */
  private void setItem( int idx, T it )
  {
    items.set( --idx, it );
  } 
  
  /**
   * The heap's comparator - set it for custom heaps.
   * By default, organizes larger items to be placed
   * before smaller items (Max-Heap property).
   */
  protected Comparator<T> comparator = new Comparator<T>()
  { 
    @Override
    public int compare( T a, T b )
    {
      // Just assume we're working with numbers by default.
      return ( a == b )?
           0 : ( (Integer) a < (Integer) b )?
           1 : -1 ;
    }
  };
  
  /**
   * Peeks at the top of the heap and returns the "maximum" item.
   * @return
   */
  public T max()
  {
    return getItem( 1 );
  }
  public T peek()
  {
    return max();
  }
  
  /**
   * Returns the largest item, and mutates the heap.
   */
  public T extractMax()
  {
    T max = max(); 
    setItem( 1, null );
    if ( _size > 1 )
    {
      // Swap out the last-child with the root.
      setItem( 1, getItem( _size ) );
      setItem( _size, null );
      reverseHeapify( 1 );
    }

    _size--;
    if ( _size < 0 ) 
    {
      _size = 0;
    }
    return max;
  } 
  
  @Override
  public void insert( T item )
  {
    if ( size() < capacity )
    {
      // Append and rebuild from the bottom-up.
      setItem( ++_size, item );
    }
    else
    {
      // Replace the last-item in the heap, since we're at capacity.
      setItem( _size, item );
    }
    heapify( _size );
  }

  
  /**
   * Returns true if two items need to be swapped;
   * Where a is the parent (or an incoming item),
   * and b is the child (or existing root).
   * 
   * So if A is "larger" than B, it's gonna be false.
   * Otherwise, true.
   * 
   * @return
   */
  private boolean needsSwapped( T a, T b )
  {
    return 1 == comparator.compare( a, b );
  }

  @Override
  public String toString()
  {
    return items.toString();
  } 
  
  @Override
  public int size()
  {
    return _size;
  }

  @Override
  public T[] sort()
  {
    // TODO Auto-generated method stub
    return null;
  }

}
