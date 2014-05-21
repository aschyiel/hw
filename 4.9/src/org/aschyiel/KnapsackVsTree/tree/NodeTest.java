package org.aschyiel.KnapsackVsTree.tree;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NodeTest
{
  private Node<Integer> a;
  private Node<Integer> b;

  @Before
  public void setUp() throws Exception
  {
    a = new Node<Integer>( "a", 1 );
    b = new Node<Integer>( "b", 2 );
  }

  @After
  public void tearDown() throws Exception
  {
    a = null;
    b = null;
  }

  @Test
  public void testIsAdjacent()
  {
    assertEquals( true, a.isAdjacent( a ) );
    assertEquals( false, a.isAdjacent( b ) );
    assertEquals( false, b.isAdjacent( a ) );
    
    a.up = b;
    assertEquals( true, a.isAdjacent( b ) );
    
    a.up = null;
    b.left = a;
    assertEquals( true, b.isAdjacent( a ) );
    
    b.left = null;
    b.right = a;
    assertEquals( true, b.isAdjacent( a ) );
  }

}
