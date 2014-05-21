package org.aschyiel.ReverseLinkedList;

import java.util.Iterator;

/**
 * Your usual singly linked-list implementation.
 * 
 * @author toy
 *
 */
public class LinkedList<T> implements Iterable<T>
{
  protected Node<T> head = null;
  protected Node<T> tail = null;
  private int length = 0; 
  
  public void add( T val )
  {
    Node<T> n =  new Node<T>( val );
    if ( null == head )
    {
      head = n;
      tail = n;
      length = 1;
    }
    else
    { 
      tail.next = n;
      tail = n;
      length++;
    }
  } 
  
  public void remove( T val )
  {
    Node<T> n = head;
    while ( null != n )
    {
      if ( n.value == val )
      {
        n.markAsRemoved();
        break;
      }
      n = n.next;
    }
    length--;
    if ( length < 0 )
    {
      length = 0;
    }
  }
  
  public int size()
  {
    return length;
  }
  
  /**
   * Returns the quick-n-dirty javaScript like syntax for the list.
   * 
   * For Example:
   *   If you had a list consisting of 3 nodes with the values 1, 2, and 3;
   *   we'd return "[ 1, 2, 3 ]".
   */
  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append( "[ " );
    
    int i = size();
    for ( T val : this )
    {
      sb.append( val );
      if ( --i > 0 )
      {
        sb.append( ", " );
      }
    }
    sb.append( " ]" );
    return sb.toString();
  }
  
  /**
   * Reverse the list in-place (mutates).
   * 
   * @see http://stackoverflow.com/questions/9076923/how-to-reverse-a-linked-list
   */
  public void reverse()
  { 
    Node<T> oldHead = head;
    Node<T> oldTail = tail;
    tail = head;
    head = oldTail;

    Node<T> current  = oldHead; 
    Node<T> previous = null;
    Node<T> forward  = null;
    while ( null != current )
    { 
      forward      = current.next;
      current.next = previous;
      previous     = current;
      current      = forward;
    }
    
    if ( isCyclic() )
    {
      throw new RuntimeException( "Yo, it's cyclic." );
    }
  }
  
  /**
   * Returns true if we've messed up our list.
   */
  protected boolean isCyclic()
  {
    Node<T> walker = head;
    Node<T> runner = ( null != walker )? walker.next : null;
    
    while ( null != walker && null != runner )
    {
      if ( walker == runner )
      {
        return true;
      }
      walker = walker.next;
      runner = ( null != runner.next )? runner.next.next : null;
    } 
    
    return false;
  }
  
  
  /**
   * The individual nodes that make up the list.
   */
  protected class Node<U>
  {
    public U value = null;
    public boolean isIgnored = false;
    public Node<U> next = null; 
    
    public Node( U val )
    {
      this.value = val;
    }
    
    public void markAsRemoved()
    {
      value = null;
      isIgnored = true;
    }
  }


  @Override
  public Iterator<T> iterator()
  {
    return new Iterator<T>()
    { 
      private Node<T> it = head;

      @Override
      public boolean hasNext()
      {
        return _hasNext( it );
      }
      private boolean _hasNext( Node<T> n )
      { 
        if ( null != n && n.isIgnored )
        {
          return _hasNext( n.next );
        }
        return null != n;
      }

      @Override
      public T next()
      {
        Node<T> current = it;
        it = hasNext() ? it.next : null;
        if ( null != current && current.isIgnored )
        {
          return next();
        }
        
        return ( null != current )? current.value : null;
      }

      @Override
      public void remove()
      {
        throw new UnsupportedOperationException();
        
      }
      
    };
  }
}
