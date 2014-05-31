package org.aschyiel.BinarySearch;

import java.util.Arrays;
import java.util.List;

public class Utils
{
  /**
   * Your typical binary-search through a sorted array implementation.
   * 
   * Returns the index of the given value, if any.
   * Expects to operate on sorted arrays/lists (only).
   * Returns -1 if the value isn't there.
   * 
   * Because I'm lazy, it's hardcoded to work with integers only for now...
   */ 
  public static int binarySearch( int[] li, int val )
  {
    Integer[] boxxx = new Integer[ li.length ];
    for ( int i = 0; i < li.length; i++ )
    {
      boxxx[ i ] = li[ i ];
    }
    return binarySearch( boxxx, val );
  } 
  public static int binarySearch( Integer[] li, int val )
  { 
    return binarySearch( Arrays.asList( li ), val );
  }
  public static int binarySearch( List<Integer> li, int val )
  {
    if ( null == li )
    {
      throw new RuntimeException( "wtf!" );
    }
    return _binarySearch( li, val, 0, li.size() - 1 );
  }

  /**
   * Meaning, there is no spoon.
   */
  public static final int NOPE = -1;

  private static int _binarySearch( List<Integer> li, int val, int left, int right )
  {
    if ( left > right )
    {
      return NOPE;
    } 
    int mid = left + ( ( right - left ) / 2 );
    int it = li.get( mid ); 
    return  ( isSame( it, val )    )?
      mid : ( isSmaller( val, it ) )? 
          _binarySearch( li, val, left, mid - 1 ) :
          _binarySearch( li, val, mid + 1, right );
    
  }
  
  /**
   * Reserving the right to use a proper Comparator later, if and when...
   */ 
  private static boolean isSame( int a, int b )
  {
    return a == b;
  } 
  private static boolean isSmaller( int a, int b )
  {
    return a < b;
  }
}
