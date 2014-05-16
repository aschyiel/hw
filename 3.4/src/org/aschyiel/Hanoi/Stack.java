package org.aschyiel.Hanoi;

/**
 * A stack yo.
 * @author toy
 *
 * @param <T>
 */
public interface Stack<T>
{
  /**
   * Add a thing.
   * 
   * @param it
   */
  void push( T it );

  /**
   * Remove the top-most thing.
   * 
   * @return T The thing.
   */
  T pop();

  /**
   * Look at the top-most thing.
   * 
   * @return T The value of the top-most thing, if any.
   */
  T peek();
}
