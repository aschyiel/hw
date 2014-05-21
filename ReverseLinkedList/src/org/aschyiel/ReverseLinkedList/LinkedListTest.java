package org.aschyiel.ReverseLinkedList;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest
{
  private LinkedList<String> subject = null;

  @Before
  public void setUp() throws Exception
  {
    subject = new LinkedList<String>();
    subject.add( "A" );
    subject.add( "B" );
    subject.add( "C" );
  }

  @After
  public void tearDown() throws Exception
  {
  }
  
  @Test
  public void testReverse()
  {
    subject.reverse();
    assertEquals( "[ C, B, A ]", subject.toString() );
    subject.add( "D" );
    assertEquals( "[ C, B, A, D ]", subject.toString() );
  }

  @Test
  public void testSize()
  {
    assertEquals( 3, subject.size() ); 
  }
  
  @Test
  public void testToString()
  {
    assertTrue( "[ A, B, C ]".equals( subject.toString() ) );
  }
  
  @Test
  public void testRemove()
  {
    subject.remove( "A" );
    assertEquals( 2, subject.size() ); 
    assertTrue( "[ B, C ]".equals( subject.toString() ) );
  }
  
  @Test
  public void testIsCyclic()
  {
    LinkedList<String> li = new LinkedList<String>();
    li.add( "A" );
    li.add( "B" );
    li.add( "C" );
    li.add( "D" );
    
    assertFalse( li.isCyclic() ); 
    li.tail.next = li.head;
    assertTrue( li.isCyclic() ); 
  }

}
