package org.aschyiel.MergeSort;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MergeSortTest
{
  private Comparator<Integer> comparator = new Comparator<Integer>(){

    @Override
    public int compare( Integer a, Integer b )
    {
      return ( a == b )?
         0 : ( a < b  )?
        -1 : 1;
    }
    
  };

  @Before
  public void setUp() throws Exception
  {
  }

  @After
  public void tearDown() throws Exception
  {
  }

  @Test
  public void testMergeSortOnLargeScale()
  {
    int n = 999999;
    List<Integer> numbers = new ArrayList<Integer>( n );
    Random r = new Random();
    while ( n-- > 0 )
    {
      numbers.add( r.nextInt() );
    }
    MergeSort.sort( numbers, comparator ); 
    
    List<Integer> sortedNumbers = new ArrayList<Integer>( numbers );
    Collections.sort( sortedNumbers );
    
    assertArrayEquals( numbers.toArray(), sortedNumbers.toArray() );   
  }

  @Test
  public void testMergeSortOnSmallSample()
  {
    List<Integer> numbers = Arrays.asList( new Integer[]{ 3, 5, 2, 1 } );
    MergeSort.sort( numbers, comparator ); 
    
    List<Integer> sortedNumbers = new ArrayList<Integer>( numbers );
    Collections.sort( sortedNumbers );
    
    assertArrayEquals( numbers.toArray(), sortedNumbers.toArray() );
  }

}
