package org.aschyiel.Hanoi;

/**
 * Represents an individual disk of a given size.
 * 
 * @author toy
 *
 */
public class Disc
{

  public Integer size;

  /**
   * A reference to the "larger" disk in the tower stack.
   * @see Tower#push
   */
  public Disc next;
  
  /**
   * @param args
   */
  public Disc( Integer size )
  {
    this.size = size; 
  }

  /**
   * Returns true if this disk may be placed ontop of another (larger) disk.
   *
   * @param them
   * @return
   */
  public boolean isSmallerThan( Disc them )
  {
    if ( null == them )
    {
      return true;
    }
    return them.size > this.size;
  }
  
  /**
   * The opposite yo.
   * @param them
   * @return
   */
  public boolean isLargerThan( Disc them )
  {
    if ( null == them )
    {
      return true;
    }
    return them.isSmallerThan( this );
  }
  
}
