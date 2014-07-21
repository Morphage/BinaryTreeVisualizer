BinaryTreeVisualizer
====================
**BinaryTreeVisualizer** consists of a Java implementation to create **Binary Search Trees** and some Javascript code to render the tree in a web page.

###Files
The Java implementation resides in a single file called `BST.java`.
```java
    BST<Integer> bst = new BST();
    
    bst.insert(new Integer(34));
    bst.insert(new Integer(22));
    bst.insert(new Integer(28));
    bst.insert(new Integer(56));
    
    String bstJSON = bst.toJSON();
```
The code snippet above shows the creation of a binary tree, how to insert a few elements and how to get the JSON representation of the tree, so that it can be used by the visualizer.

The Binary Search Tree visualizer, coded in Javascript using the JIT library. The files for the visualizer are:

    -binary_tree.js
    
    -binary_tree.html
    
    -jit.js
    
    -Spacetree.css and base.css are modified versions of the original CSS files used for the Spacetree demo.
     Links to the originals: 
     http://philogb.github.io/jit/static/v20/Jit/Examples/css/base.css
     http://philogb.github.io/jit/static/v20/Jit/Examples/css/Spacetree.css
     
   
How to use this visualizer? Write small tutorial TODO!!
