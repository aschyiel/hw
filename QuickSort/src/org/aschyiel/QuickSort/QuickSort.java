package org.aschyiel.QuickSort;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class QuickSort<T>
{
  //-------------------------------
  //
  // Relatively Private Properties
  //
  //-------------------------------
  
  protected Comparator<T> comparator;
  protected List<T> items;
  
  /**
   * Will switch over to insertion sort when there are less
   * than however many items remaining in the sub-list.
   */
  private int insertionSortAt = 5;
  
  private Random random = new Random();
  
  //-------------------------------
  //
  // Constructor
  //
  //-------------------------------
  
  public QuickSort( List<T> items )
  {
    this( items, null );
  }
  public QuickSort( List<T> items, Comparator<T> comparator )
  {
    this.items      = items;
    this.comparator = ( comparator == null )?
        getDefaultComparator() : comparator;
  }
  
  //-------------------------------
  //
  // Public Methods
  //
  //------------------------------- 
  
  public static <T> void sort( List<T> items )
  {
    sort( items, null );
  }
  public static <T> void sort( List<T> items, Comparator<T> comparator )
  {
    QuickSort<T> qs = new QuickSort<T>( items, comparator );
    qs._sort( 0, qs.randy( 0, items.size() ), items.size() );
  } 

  //-------------------------------
  //
  // Non-Public Methods
  //
  //------------------------------- 

  protected Comparator<T> getDefaultComparator()
  {
    return new Comparator<T>(){

      @SuppressWarnings( { "rawtypes", "unchecked" } )
      @Override
      public int compare( T a, T b )
      {
        return ( null == a )? 0 : ((Comparable) a).compareTo( b );
      }
      
    };
  }
 
  
  /**
   * Returns a random integer within low and high (inclusive).
   * @see http://stackoverflow.com/questions/363681/generating-random-numbers-in-a-range-with-java
   */
  private int randy( int low, int high )
  {
    return low + random.nextInt( 1 + high - low );
  }
  
  private void _sort( int left, int pivot, int right )
  {
    
  }
  
  private void swap( int a, int b )
  {
    final T tmp = items.get( a );
    items.set( a, items.get( b ) );
    items.set( b, tmp );
  }
  
  /**
   * Run insertion-sort over a sub-list;
   * Assumes the sub-list is *almost* sorted.
   */
  protected void _insertionSort( int left, int right )
  {
    final int min = left  - 1;
    final Comparator<T> komparator = comparator;
    T it;
    for ( ; right > min; right-- )
    {
      it = items.get( right );
      for ( int i = right - 1; i > min; i-- )
      {
        if ( 1 > comparator.compare( items.get( i ), it ) )
        {
          // Park it, and move on to the next guy.
          items.set( i + 1, it );
          break;
        }
        else
        {
          // Shift them over.
          swap( i, i + 1 );
        }
      }
    }
  }
  
}
