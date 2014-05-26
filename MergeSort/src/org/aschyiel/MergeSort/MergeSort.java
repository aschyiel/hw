package org.aschyiel.MergeSort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSort<E>
{ 
  private List<E> li;
  private Comparator<E> comparator;
  private List<E> buf;
  
  public static <E> void sort( List<E> li, Comparator<E> comparator )
  {
    ( new MergeSort<E>() )._sort( li, comparator );
  }
  
  private void _sort( List<E> li, Comparator<E> comparator )
  {
    if ( null == li )
    {
      throw new RuntimeException( "Illegal argument - MergeSort#sort "+
                                  "was given an empty list to sort." );
    } 
    
    this.comparator = comparator;
    this.li = li;
    buf = new ArrayList<E>( li );
    _mergeSort( 0, li.size() - 1 ); 
  } 

  private void _mergeSort( int left, int right )
  {
    if ( left < right )
    {
      int mid = ( left + right ) / 2; 
      _mergeSort( left, mid );
      _mergeSort( mid + 1, right );
      _merge( left, mid, right );
    }
  }
  
  private void _merge( int left, int mid, int right )
  {
    //
    // Copy both sub-parts into our temporary buffer. 
    //

    int idx;
    for ( idx = left; idx < right + 1; idx++ )
    {
      buf.set( idx, li.get( idx ) );
    }
    

    //
    // Merge the two sorted sub-lists.
    //
    
    final int maxRight = right;
    right = mid + 1;    // Re-use as right-hand sub-list index.
    idx = left;
    for ( idx = left; idx < maxRight + 1; idx++ )
    {
      final E a = ( left > mid )?       null : buf.get( left );
      final E b = ( right > maxRight )? null : buf.get( right );
      final int comparison = ( null == b || null == a )? 0 : comparator.compare( a, b );
      if ( null != a &&  1 > comparison )
      { 
        li.set( idx, a );
        left++;
      }
      else if ( null != b && -1 < comparison )
      {
        li.set( idx, b );
        right++;
      } 
    } 
  }
}
