package org.aschyiel.QuickSort;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QuickSortTest
{
  private List<Integer> numbers;
  private List<Integer> sorted;
  private QuickSort<Integer> subject;

  @Before
  public void setUp() throws Exception
  {
    numbers = Arrays.asList( new Integer[]{ 3, 7, 8, 5, 2, 1, 9, 5, 4 } );
    sorted = new ArrayList<Integer>( numbers );
    Collections.sort( sorted );
    subject = new QuickSort<Integer>( numbers );
  }

  @After
  public void tearDown() throws Exception
  {
    subject = null;
    numbers = null;
  }
  
  @Test
  public void testInsertionSortAgainstSomethingHarderThanWhatWeAreGonnaUseItFor()
  {
    List<String> li = Arrays.asList( new String[]{ "A", "C", "T", "Y", "O",
        "E", "L", "V", "S", "U", "Y", "O", "O", "S", "!", "U", "H", "C", "M" } );
    QuickSort<String> qs = new QuickSort<String>( li );
    List<String> sorted = new ArrayList<String>( li );
    Collections.sort( sorted );
    qs._insertionSort( 0, li.size() - 1 );
    assertArrayEquals( sorted.toArray(), li.toArray() );
  }

  @Test
  public void testMiniInsertionSortRoutine()
  {
    List<Integer> nearlySorted = Arrays.asList( new Integer[]{ 1, 2, 4, 3, 5, 7, 6 } );
    subject.items = nearlySorted;
    subject._insertionSort( 0, nearlySorted.size() - 1 );
    sorted = new ArrayList<Integer>( nearlySorted );
    Collections.sort( nearlySorted );
    assertArrayEquals( sorted.toArray(), nearlySorted.toArray() );
  }

  @Test
  public void testTinyInsertionSortRoutineCornerCase()
  { 
    subject.items = Arrays.asList( new Integer[]{ 2, 1 } );
    subject._insertionSort( 0, subject.items.size() - 1 );
    assertTrue( subject.items.get( 0 ) == 1 );
    assertTrue( subject.items.get( 1 ) == 2 );
  }

  @Test
  public void testInsertionSortOtherCornerCaseWithNearlySortedDupes()
  {
    QuickSort<String> qs = new QuickSort<String>( null );
    qs.items = Arrays.asList( new String[]{ "O", "S", "O", "O" } );
    qs._insertionSort( 0, qs.items.size() - 1 );
    assertTrue( qs.items.get( 0 ) == "O" );
    assertTrue( qs.items.get( 1 ) == "O" );
    assertTrue( qs.items.get( 2 ) == "O" );
    assertTrue( qs.items.get( 3 ) == "S" );
  }
  
  @Test
  public void testInsertionSortBaseCase()
  { 
    subject.items = Arrays.asList( new Integer[]{ 3 } );
    subject._insertionSort( 0, 0 );
    assertTrue( subject.items.get( 0 ) == 3 );
  }
  
  @Test
  public void testComparators()
  {
    assertEquals( -1, subject.comparator.compare( 0, 1 ) );
    assertEquals(  0, subject.comparator.compare( 1, 1 ) );
    assertEquals( +1, subject.comparator.compare( 2, 1 ) );
  }

  @Test
  public void testComparatorsForStrings()
  {
    // GOTCHA: By default, strings return the index offset between each-other;
    //   instead of just "+1" vs. "-1", etc.
    QuickSort<String> qs = new QuickSort<String>( null );
    assertEquals( -1, qs.comparator.compare( "O", "S" ) );
    assertEquals(  0, qs.comparator.compare( "S", "S" ) );
    assertEquals( +1, qs.comparator.compare( "S", "O" ) );
  }

}
