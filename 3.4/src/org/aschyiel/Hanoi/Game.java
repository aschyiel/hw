package org.aschyiel.Hanoi;

import java.util.ArrayList;
import java.util.List;

/**
 * Cracking The Coding Interview 3.4 p81
 * 
 * In the classic problem of the Towers of Hanoi, you have 3 towers and N disks
 * of different sizes which can slide onto any tower.  The puzzle starts with
 * disks sorted in ascending order of size from top to bottom (ie., each disk
 * sits on top of an even larger one).  You have the following constraints:
 * 
 *   1.)  Only one disk can be moved at a time.
 *   2.)  A disk is slid off the top of one tower onto the next tower.
 *   3.)  A disk can only be placed on top of a larger disk.
 *   
 * Write a program to move the disks from the first tower
 * to the last using stacks.
 *
 * @author toy
 *
 */
public class Game
{

  static Integer MINIMUM_NUMBER_OF_DISKS = 1;
  static Integer DEFAULT_NUMBER_OF_DISKS = 3;
  
  /**
   * Tower from "left" to "right" named as league of legends
   * lanes for top, middle, and bottom.
   */
  public Tower top;
  public Tower mid;
  public Tower bot;

  /**
   * The "n" value that established at setup-time.
   */
  public Integer discCount;
  
  /**
   * @param args
   */
  public static void main( String[] args )
  {
    (new Game())
        .setup( getDiscCount( args ) )
        .run( true );
  }
 
  /**
   * Normalizes the command-line options for specifying the nth disc-size.
   * @param args The original args passed forward to "main".
   * @return int Our disc-count.
   */
  public static int getDiscCount( String[] args )
  {
    int n = DEFAULT_NUMBER_OF_DISKS;
    if ( args.length > 0 )
    {
      try
      {
        n = Integer.parseInt( args[0] );
      }
      catch( NumberFormatException wtf )
      {
        System.out.println( "uh, yah, try giving me an actual number "+
            "next time instead of \""+ args[0] +"\"." );
      }
      if ( n < MINIMUM_NUMBER_OF_DISKS )
      {
        n = DEFAULT_NUMBER_OF_DISKS;
      }
    }
    return n;
  }
  
  /**
   * Runs the game and returns the list of moves we're rolling with.
   * @return List<Move> The moves aka solution to the game.
   */
  public List<Move> run()
  {
    return run( false );
  }

  public List<Move> run( boolean verbose )
  {
    Game game = this;
    List<Move> solution = new ArrayList<Move>();
    game.solve( solution, game.discCount, game.top, game.bot, game.mid );
    
    if ( verbose )
    {
      int step = 1;
      for ( Move move : solution )
      {
        move.step = step++; 
        System.out.println( move.toString() );
      }
    }

    game.checkSolution( solution );
    return solution;
  }
  
  /**
  * Executes the solution step-by-step - throws an error if
  * there's a problem in our solution.
  */
  public void checkSolution( List<Move> solution )
  {
    for ( Move move : solution )
    {
      move.to.push( move.from.pop() );
    }
    if ( !fin() )
    {
      throw new RuntimeException( "This solution sucks." );
    }
  }

  /**
   * Setup the game, Chainable.
   * 
   * @param n
   * @return Game Ourselves.
   */
  public Game setup( Integer n )
  {
    discCount = n;
    
    top = new Tower( "A" );
    mid = new Tower( "B" );
    bot = new Tower( "C" );

    //
    // Hook-up neighboring towers as a cycle.
    //

    top.up = bot; top.down = mid;
    mid.up = top; mid.down = bot;
    bot.up = mid; bot.down = top;

    //
    // In "classic" Hanoi, the left-most tower starts off
    // with all of the disks (sorted).
    //

    for ( ; n > 0 ; n-- )
    {
      top.push( new Disc( n ) );
    }

    return this;
  }

  /**
  * Recursively solve the game - move all the disks as sets of n-1.
  * 
  * @param List<Move> solution Captures all of our moves as we go along.
  * @param Integer n The nth disk we're trying to move for this iteration.
  * @return List<Move> The solution captured as step-by-step "moves".
  * @see http://www.mathsisfun.com/games/towerofhanoi.html
  * @see http://zylla.wipos.p.lodz.pl/games/hanoi-ex.html
  * @see http://en.wikipedia.org/wiki/Tower_of_Hanoi#General_shortest_paths_and_the_number_466.2F885
  * @see https://www.youtube.com/watch?v=bIgjzlumfsQ
  *
  * @param List<Move> soln The thing that captures our solution's moves.
  * @param Integer n The current disc-size we're operating on.
  * @param Tower src The source-tower.
  * @param Tower dst The destination-tower.
  * @param Tower aux The intermediate-tower.
  * @return void
  */
  public void solve( List<Move> soln, Integer n, Tower src, Tower dst, Tower aux )
  {
    if ( n < 1 )
    {
      return;
    }

    //
    // To move n disc from peg A to C:
    //   1.)  Move (n-1) disc from peg A to B using C as intermediate peg.
    //   2.)  Move nth disc from peg A to C.
    //   3.)  Move (n-1) disc from B to C using A as intermediate.
    //

    solve( soln, n - 1, src, aux, dst );    // Step 1.
    soln.add( new Move( n, src, dst ) );    // Step 2.
    solve( soln, n - 1, aux, dst, src );    // Step 3.
  }

  /**
   * Returns true when the game is "won"/over.
   */
  public boolean fin()
  {
    return null == top.peek() &&
        null == mid.peek();
  }
}
