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
   * Will switch over to insertion sort when there are
   * however many items or less remaining in the sub-list.
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
    ( new QuickSort<T>( items, comparator ) )._sort( 0, items.size() );
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
        if ( null == a )
        {
          return 0;
        }
        int score = ((Comparable) a).compareTo( b );
        return ( 0 == score )?
           0 : ( 0  > score )?
          -1 : +1 ;
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
  
  private void _sort( int left, int right )
  {
    if ( right < left )
    {
      return;
    } 
    final int pivot = randy( left, right ); 
    final T pivotItem = items.get( pivot );
    int l = left;
    int r = right;
    while ( l < r )
    {
      
    }

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
    final int max = right;
    T it;
    for ( ; right > min; right-- )
    {
      it = items.get( right ); 
      
      // Shift-them to the left (default).
      int i;
      for ( i = right - 1; i > min; i-- )
      { 
        if ( 1 > comparator.compare( items.get( i ), it ) )
        {
          // Park it, and move on to the next guy.
          items.set( i + 1, it );
          break;
        }
        else
        {
          swap( i, i + 1 ); 
        }
      }
      
      // Corner-case - shift them as far-right as possible.
      if ( right + 1 < max )
      {
        it = items.get( right + 1 ); 
        for ( int j = right + 1;
            j < max && 1 == comparator.compare( it, items.get( j + 1 ) );
            j++ )
        {
          swap( j, j + 1 );
        }
      }
    }
  }
  
}
