package org.aschyiel.KnapsackVsTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.aschyiel.KnapsackVsTree.tree.Node;


public class Solution
{
  private Node<Integer> root;
  private Integer target;

  protected Map<Node<Integer>, Map<Node<Integer>, Path>> memo;
  
  public Solution( Node<Integer> root, Integer target )
  {
    this.root   = root;
    this.target = target;
    
    memo = new HashMap<Node<Integer>, Map<Node<Integer>, Path>>();
  }
  
  /**
   * Alphabetically sorted nodes.
   */
  private List<Node<Integer>> nodes = null;
  
  /**
   * Executes the DP stuff.
   * 
   * Chainable
   * @return
   */
  public Solution solve()
  {
    nodes = new ArrayList<Node<Integer>>();
    flatten( root, nodes );
    Collections.sort( nodes );

    for ( int i = 0; i < nodes.size(); i++ )
    {
      for ( int j = 0; j < nodes.size(); j++ )
      {
        solve( nodes.get( i ), nodes.get( j ) );
      }
    }

    return this;
  } 
 
  /**
   * Flattens the tree as a list of nodes.
   */
  private void flatten( Node<Integer> it, List<Node<Integer>> li )
  {
    if ( null != it )
    {
      flatten( it.getLeft(), li );
      li.add( it );
      flatten( it.getRight(), li );
    }
  } 
  
  /**
   * Recursively solves from point a to point b --- trolling through as necessary.
   */
  private Path solve( Node<Integer> a, Node<Integer> b )
  {
    return solve( a, b, a );
  }
  protected Path solve( Node<Integer> a, Node<Integer> b, Node<Integer> prev )
  {
    // Reuse (DP).
    Path memoPath = getMemo( a, b );
    if ( null != memoPath )
    {
      return memoPath;
    }
    
    if ( null == a || null == b )
    {
      return null;
    }
    
    Path path = null;
    if ( a.isAdjacent( b ) )
    {
      path = new Path( a, b );
    }
    else
    {
      Path sub = null;    // The solution to the sub-path(s).
      if ( a.getLeft() != prev )
      {
        solve( a.getLeft(), b, a );
      }
      if ( null == sub && a.getRight() != prev )
      {
        sub = solve( a.getRight(), b, a );
      }
      if ( null == sub && a.getParent() != prev )
      { 
        sub = solve( a.getParent(), b, a );
      }
      if ( null != sub )
      {
        path = new Path( a, sub );
      }
    }
    
    if ( null != path )
    {
      setMemo( a, b, path );
      return path;
    }
    return null;
  } 
  
  private Path getMemo( Node<Integer> a, Node<Integer> b )
  {
    Map<Node<Integer>, Path> m = memo.get( a );
    return ( m != null )? m.get( b ) : null;
  }
  
  private void setMemo( Node<Integer> a, Node<Integer> b, Path path )
  {
    Map<Node<Integer>, Path> m = memo.get( a );
    if ( null == m )
    {
      m = new HashMap<Node<Integer>, Path>();
      memo.put( a, m );
    }

    Path existing = m.get( b );
    if ( null == existing )
    { 
      m.put( b, path );
      // Also set the reverse-path.
      setMemo( b, a, Path.reverse( path ) );
    }
  }
  
  /**
   * Returns the valid paths that add up to
   * the target value - may be empty or null if none.
   */
  public List<Path> getPaths()
  {
    // TODO: Optimization would be to keep references
    //   to exact-paths during the "trolling" process.
    List<Path> paths = new LinkedList<Path>();
    for ( Node<?> a : nodes )
    for ( Node<?> b : nodes )
    {
      Path path = memo.get( a ).get( b ); 
      if ( target == path.value )
      {
        paths.add( path );
      }
    }
    return paths;
  }

}
