package org.aschyiel.Hanoi;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TowerTest
{
  private Tower subject;
  
  @Before
  public void setUp() throws Exception
  {
    subject = new Tower( "foo" );
    subject.push( new Disc( 3 ) );
    subject.push( new Disc( 2 ) );
  }

  @After
  public void tearDown() throws Exception
  {
    subject = null;
  }

  @Test(expected=RuntimeException.class)
  public void testBadPush()
  {
    subject.push( new Disc( 10 ) );    // Meaning the disc is too large.
  }
  
  @Test
  public void testPush()
  {
    subject.push( new Disc( 1 ) );
    assertEquals( 3, subject.size() );
  }

  @Test
  public void testSize()
  {
    assertEquals( 2, subject.size() );
  }
  
  @Test
  public void testPeek()
  {
    assertTrue( 2 == subject.peek().size );
    assertTrue( 2 == subject.size() );
    assertTrue( 2 == subject.peek().size );
  }
  
  @Test
  public void testPop()
  {
    subject.pop(); assertEquals( 1, subject.size() );
    subject.pop(); assertEquals( 0, subject.size() );
    subject.pop(); assertEquals( 0, subject.size() );    //..redundancy check..
  }
}
