package org.aschyiel.KnapsackVsTree;

import tree.Node;

import java.util.LinkedList;
import java.util.List;

public class Path
{
  public Node<Integer> from;
  public Node<Integer> to;
  public List<Node<Integer>> nodes;
  public Integer value; 
  
  public Path()
  {
    nodes = new LinkedList<Node<Integer>>();
    value = 0;
    to = null;
    from = null;
  }
  
  /**
   * The building block for paths as pairs - either to itself
   * or from point a to b.
   */
  public Path( Node<Integer> from, Node<Integer> to )
  {
    this();
    this.from = from;
    this.to   = to;
    nodes.add( from );
    if ( from != to )
    {
      nodes.add( to );
      value = from.getValue() + to.getValue();
    }
    else
    {
      value = from.getValue();
    }
  }
  
  /**
   * Base our new path off of an existing one that is off by one node.
   * @param dst The next node in the series we're tacking on.
   * @param prev The previous path we're basing ourselves off of.
   */
  public Path( Node<Integer> dst, Path prev )
  {
    this();
    from = prev.from;
    to = dst;
    for ( Node<Integer> it : prev.nodes )
    {
      nodes.add( it );
    }
    nodes.add( dst );
    value = prev.value + dst.getValue(); 
  }
  
  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    
    sb.append( from.getName() );
    sb.append( " => " );
    sb.append( to.getName() );
    sb.append( ": ( " );
    
    int i = nodes.size();
    for ( Node<?> it : nodes )
    {
      sb.append( it );
      if ( --i > 0 ) {
        sb.append( " + " );
      }
    }
    sb.append( " ) = " );
    sb.append( value );
    
    return sb.toString();
  }
  
}
