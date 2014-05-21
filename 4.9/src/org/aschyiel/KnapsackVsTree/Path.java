package org.aschyiel.KnapsackVsTree;


import java.util.ArrayList;
import java.util.List;

import org.aschyiel.KnapsackVsTree.tree.Node;

public class Path
{
  public Node<Integer> from;
  public Node<Integer> to;
  public List<Node<Integer>> nodes;
  public Integer value; 
  
  public Path()
  {
    nodes = new ArrayList<Node<Integer>>();
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
   * @param src The previous node in the series we're tacking-on/inserting.
   * @param prev The previous path we're basing ourselves off of.
   */
  public Path( Node<Integer> src, Path prev )
  {
    this();
    from = src;
    to = prev.to;
    nodes.add( src );
    for ( Node<Integer> it : prev.nodes )
    {
      nodes.add( it );
    }
    value = prev.value + src.getValue(); 
  }
  
  /**
   * Creates a reverse-version of the given path.
   * @return
   */
  public static Path reverse( Path path )
  {
    Path htap  = new Path();
    htap.from  = path.to;
    htap.to    = path.from; 
    htap.value = path.value;
    
    // Copy the node-path as is.
    List<Node<Integer>> li = htap.nodes;
    for ( Node<Integer> it : path.nodes )
    {
      li.add( it );
    }
    
    // Then, reverse it (like a string).
    for ( int i = 0, j = path.nodes.size() - 1;
        i < j;
        i++, j-- )
    {
      Node<Integer> tmp = li.get( i );
      li.set( i, li.get( j ) );
      li.set( j, tmp );
    }
    
    return htap;
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
