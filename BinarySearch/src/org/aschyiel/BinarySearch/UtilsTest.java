package org.aschyiel.BinarySearch;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UtilsTest
{
  private List<Integer> sortedNumbers;
  private List<Integer> shuffledNumbers;
  private Random randy = new Random();

  @Before
  public void setUp() throws Exception
  {
    int n = 9999;
    sortedNumbers = new ArrayList<Integer>( n );
    while ( n-- > 0 )
    {
      sortedNumbers.add( randy.nextInt() );
    }
    Collections.sort( sortedNumbers );
    shuffledNumbers = new ArrayList<Integer>( sortedNumbers );
    Collections.shuffle( shuffledNumbers, randy );
  }

  @After
  public void tearDown() throws Exception
  {
  }

  @Test
  public void testBinarySearch()
  {
    int iteration = 50;
    while ( iteration-- > 0 )
    {
      int val = shuffledNumbers.get( iteration );
      assertEquals( Utils.binarySearch( sortedNumbers, val ),
                    sortedNumbers.indexOf( val ) );
    }
  }

  @Test
  public void testBasic()
  {
    int idx = 1234; 
    assertEquals( idx,
        Utils.binarySearch( sortedNumbers, sortedNumbers.get( idx ) ) );
  }
  
  @Test
  public void testItAintThere()
  {
    assertEquals( Utils.NOPE, Utils.binarySearch( new int[]{ 1, 2, 3 }, 5 ) );
  }

  @Test 
  public void testQuicklyThatSortedNumbersAreKindaSorted()
  {
    assertTrue( sortedNumbers.get( 0 ) < sortedNumbers.get( 1 ) );
    assertTrue( sortedNumbers.get( 1 ) < sortedNumbers.get( 2 ) );
    assertTrue( sortedNumbers.get( 2 ) < sortedNumbers.get( 3 ) );
    assertTrue( sortedNumbers.get( 3 ) < sortedNumbers.get( 4 ) );
  }
  
}
