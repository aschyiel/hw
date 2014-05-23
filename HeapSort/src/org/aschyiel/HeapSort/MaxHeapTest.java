package org.aschyiel.HeapSort;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MaxHeapTest
{

  @Before
  public void setUp() throws Exception
  {
  }

  @After
  public void tearDown() throws Exception
  {
  }

  @Test
  public void testBuildHeap()
  {
    
    Integer[] initialNumbers = new Integer[]{ 7, 2, 8, 3, 4, 5 };
    MaxHeap<Integer> heap = new MaxHeap<Integer>( initialNumbers.length ); 
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>( initialNumbers.length, heap.comparator );

    List<Integer> li = Arrays.asList( initialNumbers );
    priorityQueue.addAll( li ); 
    heap.buildHeap( initialNumbers );
    assertTrue( heap.size() == li.size() );
    assertTrue( heap.max() == priorityQueue.peek() );

    Integer[] numbers = new Integer[]{ 42, 50, 7, -8, 9, 69, 101,
        109, 110, 333, 1337, 6, 7, 8, 9, 10, -3, 12, 20, 123, 41, 0, 14 };
    for ( int i = numbers.length; --i > -1 ; )
    {
      heap.insert( numbers[i] );
      priorityQueue.add( numbers[i] );
    }
    assertTrue( heap.size() == li.size() );
    assertTrue( heap.max() == priorityQueue.peek() );
    assertTrue( 1337 == heap.max() );
  }
  
  @Test
  public void testExtractMaxAndStuffOnLargeScale()
  {
    int maxCapacity = 999999;    // A million.
    MaxHeap<Integer> us = new MaxHeap<Integer>( maxCapacity ); 
    PriorityQueue<Integer> them = new PriorityQueue<Integer>( maxCapacity, us.comparator ); 
    
    // Building...
    
    Random r = new Random();
    for ( int i = maxCapacity - 1; --i > 0; )
    {
      int n = r.nextInt( 99 );
      them.add( n );
      us.insert( n );
      assertTrue( them.peek() == us.max() );
    }
    
    // Taking it part bit by bit.
    while ( them.size() > 0 )
    { 
      int theirMax = them.poll();
      int ourMax   = us.extractMax();
      
      assertTrue( theirMax == ourMax );
    } 
    
    // GOTCHA: This test won't work if we over-fill our heap;
    //  Because it's behavior will diverge from java.util.PriorityQueue
    //  which is not a fixed-sized implementation.
    //
    // Aka, we'll start dropping stuff.
    //
  }

  @Test
  public void testExtract()
  {
    MaxHeap<Integer> heap = new MaxHeap<Integer>( 16 );
    heap.insert( 10 );
    heap.insert( 15 );
    heap.insert( 8 );
    heap.insert( 3 );
    heap.insert( 2 );
    assertTrue( heap.items.get( 0 ) == 15 );
    assertTrue( heap.items.get( 1 ) == 10 );
    assertTrue( heap.items.get( 2 ) ==  8 );
    assertTrue( heap.items.get( 3 ) ==  3 );
    assertTrue( heap.items.get( 4 ) ==  2 );
    
    assertTrue( heap.extractMax() == 15 );
    assertTrue( heap.max() == 10 );
    assertTrue( heap.items.get( 0 ) == 10 );
    assertTrue( heap.items.get( 1 ) ==  3 );
    assertTrue( heap.items.get( 2 ) ==  8 );
    assertTrue( heap.items.get( 3 ) ==  2 );
    assertTrue( heap.items.get( 4 ) ==  null );

    heap.extractMax();
    assertTrue( heap.items.get( 0 ) ==  8 );
    assertTrue( heap.items.get( 1 ) ==  3 );
    assertTrue( heap.items.get( 2 ) ==  2 );

    heap.extractMax();
    assertTrue( heap.items.get( 0 ) ==  3 );
    assertTrue( heap.items.get( 1 ) ==  2 );

    heap.extractMax();
    assertTrue( heap.max() == 2 );

    assertTrue( 2 == heap.extractMax() );
    assertTrue( null == heap.extractMax() );
  }

  @Test
  public void testHeapProperty()
  {
    MaxHeap<Integer> heap = new MaxHeap<Integer>( 3 );
    heap.insert( -6 );
    assertTrue( heap.peek() == -6 );
    assertTrue( heap.items.get( 0 ) == -6 );
    
    heap.insert( 11 );
    assertTrue( heap.peek() == 11 );
    assertTrue( heap.items.get( 0 ) == 11 );
    assertTrue( heap.items.get( 1 ) == -6 );

    heap.insert( 2 );
    assertTrue( heap.peek() == 11 );
    assertTrue( heap.items.get( 0 ) == 11 );
    assertTrue( heap.items.get( 1 ) == -6 );
    assertTrue( heap.items.get( 2 ) ==  2 );

    heap.insert( 35 );
    assertTrue( heap.peek() == 35 );
    assertTrue( heap.items.get( 0 ) == 35 );
    assertTrue( heap.items.get( 1 ) == -6 );
    assertTrue( heap.items.get( 2 ) == 11 );

    heap.insert( 0 );
    assertTrue( heap.peek() == 35 );
    assertTrue( heap.items.get( 0 ) == 35 );
    assertTrue( heap.items.get( 1 ) == -6 );
    assertTrue( heap.items.get( 2 ) ==  0 ); 
  }

}
