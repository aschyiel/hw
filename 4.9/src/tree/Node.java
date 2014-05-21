package tree;

import java.util.Random;

/**
 * An individual tree-node.
 */
public class Node<T> implements Comparable<Node<T>>
{
  
  /**
   * Can be null --- meaning it is empty.
   */
  private T value;
  
  /**
   * A way of labeling ourselves.
   */
  private String name;

  protected Node<T> up;
  protected Node<T> left;
  protected Node<T> right;
  
  public Node( String name, T value )
  {
    this.name  = name;
    this.value = value;
  } 
  
  /**
   * The read-only value property for this node.
   */
  public T getValue()
  {
    return value;
  }
  
  /**
   * Print an individual node as a proper label.
   */
  @Override
  public String toString()
  {
    return "{"+ name +":"+ value +"}";
  }

  private static final Random random = new Random();
  
  /**
   * Append another node into our tree - starting "here" as the root.
   * Since we're NOT a binary-search tree - the order is random.
   * @param node
   */
  public void insert( Node<T> node )
  {
    if ( random.nextBoolean() )
    {
      if ( null == left )
      {
        setLeft( node );
      }
      else
      {
        left.insert( node );
      }
    }
    else
    {
      if ( null == right )
      {
        setRight( node );
      }
      else
      {
        right.insert( node );
      }
    }
      
  }
  
  /**
   * Returns true for immediate neighbors or against itself.
   * @return
   */
  public boolean isAdjacent( Node<T> them )
  {
    Node<T> us = this;
    return us       == them ||
           us.left  == them ||
           us.up    == them ||
           us.right == them;
  }
  
  public String getName()
  {
    return name;
  }
  
  public void setLeft( Node<T> them )
  {
    left = them;
    them.up = this;
  } 
  public void setRight( Node<T> them )
  {
    right = them;
    them.up = this;
  }
  
  public Node<T> getLeft()
  {
    return left;
  }
  
  public Node<T> getRight()
  {
    return right;
  }
  
  public Node<T> getParent()
  {
    return up;
  }
  
  /**
   * @see http://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
   */
  public void print()
  {
    print( "", null );
  } 
  protected void print( String prefix, Integer spin )
  {
    if ( null != right )
    {
      right.print( prefix + ".   ", 1 );
    }
    String c = ( null == spin )?
        "-" : ( 1 == spin )?
        "/" : "\\";
    System.out.println( prefix + c + "---" + this.toString() ); 
    if ( null != left )
    {
      left.print( prefix  + ".   ", -1 );
    }
  }

  @Override
  public int compareTo( Node<T> them )
  {
    return name.compareTo( them.name );
  }
  
}
