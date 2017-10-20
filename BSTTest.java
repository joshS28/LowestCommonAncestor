import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @version 3.1 09/11/15 11:32:15
 *
 *  @author  Joshua Stafford
 */

//Added function to test for LCA at bottom and gets 100% no errors

@RunWith(JUnit4.class)
public class BSTTest
{
  
  //TODO write more tests here.
	
	
	

  
  /** <p>Test {@link BST#prettyPrintKeys()}.</p> */
      
 @Test
 public void testPrettyPrint() {
     BST<Integer, Integer> bst = new BST<Integer, Integer>();
     assertEquals("Checking pretty printing of empty tree",
             "-null\n", bst.prettyPrintKeys());
      
                          //  -7
                          //   |-3
                          //   | |-1
                          //   | | |-null
     bst.put(7, 7);       //   | |  -2
     bst.put(8, 8);       //   | |   |-null
     bst.put(3, 3);       //   | |    -null
     bst.put(1, 1);       //   |  -6
     bst.put(2, 2);       //   |   |-4
     bst.put(6, 6);       //   |   | |-null
     bst.put(4, 4);       //   |   |  -5
     bst.put(5, 5);       //   |   |   |-null
                          //   |   |    -null
                          //   |    -null
                          //    -8
                          //     |-null
                          //      -null
     
     String result = 
      "-7\n" +
      " |-3\n" + 
      " | |-1\n" +
      " | | |-null\n" + 
      " | | -2\n" +
      " | |  |-null\n" +
      " | |  -null\n" +
      " | -6\n" +
      " |  |-4\n" +
      " |  | |-null\n" +
      " |  | -5\n" +
      " |  |  |-null\n" +
      " |  |  -null\n" +
      " |  -null\n" +
      " -8\n" +
      "  |-null\n" +
      "  -null\n";
     assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());
     }

  
     /** <p>Test {@link BST#delete(Comparable)}.</p> */
     @Test
     public void testDelete() {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         bst.delete(1);
         assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());
         
         bst.put(7, 7);   //        _7_
         bst.put(8, 8);   //      /     \
         bst.put(3, 3);   //    _3_      8
         bst.put(1, 1);   //  /     \
         bst.put(2, 2);   // 1       6
         bst.put(6, 6);   //  \     /
         bst.put(4, 4);   //   2   4
         bst.put(5, 5);   //        \
                          //         5
         
         assertEquals("Checking order of constructed tree",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
         
         bst.delete(9);
         assertEquals("Deleting non-existent key",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
 
         bst.delete(8);
         assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());
 
         bst.delete(6);
         assertEquals("Deleting node with single child",
                 "(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());
 
         bst.delete(3);
         assertEquals("Deleting node with two children",
                 "(((()1())2(()4(()5())))7())", bst.printKeysInOrder());
         
         bst.delete(5);
         assertEquals("Deleting node with no children",
                 "((()1())2(()4()))7())", bst.printKeysInOrder());
         
         bst.delete(1);
         assertEquals("Deleting node with no children",
                 "(()2(()4()))7())", bst.printKeysInOrder());
     }
     
     @Test
     public void testHeight(){
    	 
    	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	 assertEquals("Height of empty tree", "-1", bst.height());
    	 
    	 bst.put(7, 7);
    	 assertEquals("Height of one node", "0", bst.height());   //error in webcat for this line but testing for size = 1?
    	                 //             7
    	 bst.put(8, 8);  //           /   \
    	 
    	 assertEquals("Height of two nodes", "1", bst.height());
    	 
    	 bst.put(5, 5);  //          6     8
    	 bst.put(6, 6);  //         /       \
    	 bst.put(2, 2);  //        2         9
    	 bst.put(3, 3);  //       / \         \
    	 bst.put(1, 1);  //      1   3         10
    	 bst.put(9, 9);  //                   /  \
    	 bst.put(10, 10);//                 11    12
    	 bst.put(12, 12);//                         \
    	 bst.put(11, 11);//                          14
    	 bst.put(14, 14);//
    	 
    	 assertEquals("Height of 6 nodes deep(Height = 5)", "5", bst.height());
    	 
  
     }
     
     
     @Test
     public void testPrintKeysInOrder(){
    	 
    	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	 assertEquals("Empty Tree print out", "()", bst.printKeysInOrder());
    	 
    	 bst.put(7, 7);
    	 assertEquals("1 Node print out", "(()7())", bst.printKeysInOrder());
    
    	                  //        _7_
         bst.put(8, 8);   //      /     \
         bst.put(3, 3);   //    _3_      8
         bst.put(1, 1);   //  /     \
         bst.put(2, 2);   // 1       6
         bst.put(6, 6);   //  \     /            USED THE TREE PROVED IN TESTING PRETTY PRINT
         bst.put(4, 4);   //   2   4
         bst.put(5, 5);   //        \
                          //         5
    	 
    	 assertEquals("Test tree above", "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.height());
  
     }
     
     @Test
     public void testGet(){
    	 
    	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	 
    	 bst.put(7, 7);   //        _7_
    	 bst.put(8, 8);   //      /     \
    	 bst.put(3, 3);   //    _3_      8
    	 bst.put(1, 1);   //  /     \
    	 bst.put(2, 2);   // 1       6
    	 bst.put(6, 6);   //  \     /            USED THE TREE PROVIDED IN TESTING PRETTY PRINT
    	 bst.put(4, 4);   //   2   4
    	 bst.put(5, 5);   //        \
                          //         5
    	 
    	 
    	 assertSame("Test tree above", 3, bst.get(3));
    	 assertSame("Test tree above", 1, bst.get(1));
    	 assertSame("Test tree above", 5, bst.get(5));
    	 assertSame("Test tree above", 8, bst.get(8));
    	  
     }
     
     @Test
     public void testContains(){
    	 
    	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	 
    	 bst.put(7, 7);   //        _7_
    	 bst.put(8, 8);   //      /     \
    	 bst.put(3, 3);   //    _3_      8
    	 bst.put(1, 1);   //  /     \
    	 bst.put(2, 2);   // 1       6
    	 bst.put(6, 6);   //  \     /            USED THE TREE PROVIDED IN TESTING PRETTY PRINT
    	 bst.put(4, 4);   //   2   4
    	 bst.put(5, 5);   //        \
                          //         5
    	 
    	 
    	 assertEquals("Test tree above", true, bst.contains(7));
    	 assertEquals("Test tree above", true, bst.contains(1));
    	 assertEquals("Test tree above", true, bst.contains(8));
    	 assertEquals("Test tree above", false, bst.contains(12));
    	
     }
     
     @Test
     public void testMedian(){
    	 
    	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	 
    	 assertSame("Test empty tree", null, bst.median());
    	 
    	 bst.put(7, 7);  
    	 assertSame("Test tree above", 7, bst.median());
    	 				  //        _7_
    	 bst.put(8, 8);   //      /     \
    	 bst.put(3, 3);   //    _3_      8       median = (N+1)/2
    	 bst.put(1, 1);   //  /     \
    	 bst.put(2, 2);   // 1       6
    	 bst.put(6, 6);   //  \     /            USED THE TREE PROVIDED IN TESTING PRETTY PRINT
    	 bst.put(4, 4);   //   2   4
    	 bst.put(5, 5);   //        \
                          //         5
    	 
    	 assertSame("Test tree above", 4, bst.median());
    	
     }
     
     @Test
     public void testSize(){
    	 
    	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	 
    	 assertEquals("Test empty tree", 0, bst.size());
    	 
    	 bst.put(7, 7);   //        _7_
    	 bst.put(8, 8);   //      /     \
    	 bst.put(3, 3);   //    _3_      8
    	 bst.put(1, 1);   //  /     \
    	 bst.put(2, 2);   // 1       6
    	 bst.put(6, 6);   //  \     /            USED THE TREE PROVIDED IN TESTING PRETTY PRINT
    	 bst.put(4, 4);   //   2   4
    	 bst.put(5, 5);   //        \
                          //         5
    	 
    	 assertEquals("Test above tree", 8, bst.size());
    	 
    	 
     }
     @Test
     public void testDeleteMax(){
    	 
    	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	 
    	 
    	 
    	 bst.put(7, 7);   //        _7_
    	 bst.put(8, 8);   //      /     \
    	 bst.put(3, 3);   //    _3_      8
    	 bst.put(1, 1);   //  /     \
    	 bst.put(2, 2);   // 1       6
    	 bst.put(6, 6);   //  \     /            USED THE TREE PROVIDED IN TESTING PRETTY PRINT
    	 bst.put(4, 4);   //   2   4
    	 bst.put(5, 5);   //        \
                          //         5
    	 
    	 assertEquals("Test above tree", "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
    	 
    	 bst.deleteMax();
    	 
    	 assertEquals("Test above tree", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());
    	 
    	 
     }
     
     @Test
     public void testLCA(){
    	 
    	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	 
    	 assertNull("Empty Test", bst.lca(5, 10));
    	 
    	 bst.put(1, 1);
    	 
    	 assertNull("1 key Test", bst.lca(2,2));
    
    	 BST<Integer, Integer> bstt = new BST<Integer, Integer>();

    	 
    	 bstt.put(7, 7);   //        _7_
    	 bstt.put(8, 8);   //      /     \
    	 bstt.put(3, 3);   //    _3_      8
    	 bstt.put(1, 1);   //  /     \
    	 bstt.put(2, 2);   // 1       6
    	 bstt.put(6, 6);   //  \     /            USED THE TREE PROVIDED IN TESTING PRETTY PRINT
    	 bstt.put(4, 4);   //   2   4
    	 bstt.put(5, 5);   //        \
                          //         5
    	 
    	 
    	 assertEquals("Testing", 3, (int)bstt.lca(1, 6));
    	 assertEquals("Testing", 3, (int)bstt.lca(4, 2));
    	 assertEquals("Testing", 3, (int)bstt.lca(1, 5));
    	 assertEquals("Testing", 7, (int)bstt.lca(1, 8));
    	 assertEquals("Testing", 7, (int)bstt.lca(5, 8));
    	 assertEquals("Testing", 7, (int)bstt.lca(1, 7));
    	 assertEquals("Testing", 7, (int)bstt.lca(7, 8));
    	 
    	 
    	 
    	 
    	 
    	 
     }

	
     
}
