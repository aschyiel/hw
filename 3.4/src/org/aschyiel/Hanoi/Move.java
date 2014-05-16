package org.aschyiel.Hanoi;

/**
 * Represents a single "move" in our game;
 * captures all of the appropriate information.
 * 
 * @author toy
 *
 */
public class Move
{
  public Integer size;
  public Tower from;
  public Tower to; 
  public Integer step;

  public Move( Integer size, Tower from, Tower to )
  {
    this( -1, size, from, to );
  }

  public Move( Integer step, Disc disk, Tower from, Tower to )
  {
    this( step, disk.size, from, to );
  }

  public Move( Integer step, Integer size, Tower from, Tower to )
  {
    this.step = step;
    this.size = size;
    this.from = from;
    this.to   = to;
  }
  
  @Override
  public String toString()
  {
    return "Step ("+ step +"): Move disc-"+ size + " from tower \""+
        from.name +"\" to tower \""+ to.name +"\".";
  }
}
