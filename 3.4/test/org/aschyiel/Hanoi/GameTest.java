package org.aschyiel.Hanoi;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameTest
{
  private Game subject = null;

  @Before
  public void setUp() throws Exception
  {
    subject = new Game();
    subject.setup( Game.DEFAULT_NUMBER_OF_DISKS );
  }

  @After
  public void tearDown() throws Exception
  {
    subject = null;
  }

  @Test
  public void testSetup()
  {
    subject = new Game();
    subject.setup( 3 );
    
    assertEquals( 3, subject.top.size() );
    
    assertTrue( 1 == ((Disc) subject.top.peek()).size );
    assertTrue( 1 == ((Disc) subject.top.pop()).size );
    assertTrue( 2 == ((Disc) subject.top.pop()).size );
    assertTrue( 3 == ((Disc) subject.top.pop()).size );
  }
  
  @Test
  public void testFin()
  {
    assertEquals( false, subject.fin() );
    
    subject.top = new Tower( "foo" );
    assertEquals( true, subject.fin() );
  }

  @Test
  public void testRunBasicThreeDiscSolution()
  {
    List<Move> solution = subject.run();
    assertTrue( solution.size() == Math.pow( 2, subject.discCount ) - 1 );
  }

  @Test
  public void testRunAgainstLargerDiscCountShouldStillUseMinimumSteps()
  {
    // GOTCHA: You might have to fiddle with your java heap space.
    //   As it's gonna be about a million moves or so to complete the game.
    subject.setup( 20 );
     List<Move> solution = subject.run();
    assertTrue( solution.size() == 1048575 ); 
  }

}
