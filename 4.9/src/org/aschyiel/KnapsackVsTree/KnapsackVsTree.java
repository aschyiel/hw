package org.aschyiel.KnapsackVsTree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.aschyiel.KnapsackVsTree.tree.Node;


/**
 * For question 4.9 in Cracking the Coding Interview.
 * 
 * By default, our program creates a binary tree of 16 random positive
 * integer values from 1 to 11 (tree-height of 4 ) and tries to find all
 * the paths that add up to 21.
 * 
 * The tree is printed, as well as the various paths (solutions), if any.
 * 
 * Accounts for duplicate paths (ie. A->B->C == C->B->A).
 * 
 * @author toy
 *
 */
public class KnapsackVsTree
{

  /**
   * @param args
   */
  public static void main( String[] args )
  {
    // TODO Accept CLI parameters.
    int n = 32;
    int target = 21;
    int min = -99;
    int max =  99;
    
    Node<Integer> root = buildRandomTree( n, min, max );
    root.print();
    List<Path> paths = findPathsThatAddUp( root, target );
    printPaths( paths, target ); 
  }

  public static Node<Integer> buildRandomTree( int n, int min, int max )
  { 
    Random random = new Random();
    int delta = max - min;
    Node<Integer> root = new Node<Integer>( "A", random.nextInt( delta ) + min );
    
    int beforeA = 65;
    int maxChars = 26;
    Map<Character, Integer> charCounts = new HashMap<Character, Integer>();
    int idx = 1;
    while( --n > 0 )
    {
      char c = Character.toChars( beforeA + idx++ % maxChars )[0];
      Integer count = charCounts.get( c );
      String label = String.valueOf( c ) +
          ( ( null == count )? "" : count );
      root.insert( new Node<Integer>( label, random.nextInt( delta ) + min ) ); 
      charCounts.put( c, ( null == count )? 2 : ++count );
    }
    return root;
  }

  public static void printPaths( List<Path> li, Integer target )
  {
    if ( null != li )
    {
      for ( Path it : li )
      {
        System.out.println( it );
      }
    }
    else
    { 
      System.out.println( "No paths found that add up to (" + target +")." );
    }
  }
  
  public static List<Path> findPathsThatAddUp( Node<Integer> root, int target )
  {
    return (new Solution( root, target ))
        .solve()
        .getPaths();
  }
  
  
}
