package org.aschyiel.KnapsackVsTree;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.List;

import tree.Node;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SolutionTest
{
  private int target = 21;
  private Node<Integer> root;
  private Node<Integer> b;
  private Node<Integer> c;
  private Node<Integer> d;
  private Node<Integer> e;
  
  private Solution subject;

  @Before
  public void setUp() throws Exception
  {
    /**
     *             |-E:1
     *       |-C:5-|
     *       |     |-D:15
     * A:10 -|
     *       |-B:11
     */
    root = new Node<Integer>( "A", 10 );
    b = new Node<Integer>( "B", 11 );
    c = new Node<Integer>( "C",  5 );
    d = new Node<Integer>( "D", 15 );
    e = new Node<Integer>( "E",  1 );
    root.setLeft( b );
    root.setRight( c );
    c.setLeft( d );
    c.setRight( e );
    subject = new Solution( root, target );
  }

  @After
  public void tearDown() throws Exception
  {
  }

  @Test
  public void testSolveShouldReturnASimplePathForAdjacentPairs()
  {
    Path path = subject.solve( root, b, root );
    assertEquals( path.from, root );
    assertEquals( path.to, b );
  }
  
  @Test
  public void testSolveShouldReturnTheExtendedPathWhenNotASimplePair()
  {
    Path path = subject.solve( root, d, root );
    assertEquals( path.from, root );
    assertEquals( path.to, d );
    assertTrue( path.nodes.size() == 3 );
    assertEquals( path.nodes.get( 0 ), root );
    assertEquals( path.nodes.get( 1 ), c );
    assertEquals( path.nodes.get( 2 ), d );
    assertTrue( path.value == root.getValue() + c.getValue() + d.getValue() );
  }
  
  @Test
  public void testSolveInGeneral()
  {
    subject.solve();
    Map<?, Path> xi = subject.memo.get( root );
    assertTrue( xi.get( root ).value == 10 );
    assertTrue( xi.get( b ).value == 21 );
    assertTrue( xi.get( c ).value == 15 );
    assertTrue( xi.get( d ).value == 30 );
    assertTrue( xi.get( e ).value == 16 );
    
    // Also, we should be able to have paths go "across", not just up-and-down.
    Map<?, Path> zi = subject.memo.get( e );
    assertTrue( zi.get( b ).value == 27 );
    assertTrue( zi.get( b ).nodes.size() == 4 );
  }
  
  @Test
  public void testGetPaths()
  {
    List<Path> answers = subject.solve().getPaths();
    // GOTCHA: Includes dupe answers: A->B vs. B->A.
    assertTrue( answers.size() == 4 ); 

    // GOTCHA: Alphabetically sorted.
    assertTrue( answers.get( 0 ).from == root );
    assertTrue( answers.get( 0 ).to   == b );
    assertTrue( answers.get( 1 ).from == b );
    assertTrue( answers.get( 1 ).to   == root );
    assertTrue( answers.get( 2 ).from == d );
    assertTrue( answers.get( 2 ).to   == e );
    assertTrue( answers.get( 3 ).from == e );
    assertTrue( answers.get( 3 ).to   == d );
  }

}
