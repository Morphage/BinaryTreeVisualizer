BinaryTreeVisualizer
====================
**BinaryTreeVisualizer** consists of a Java implementation to create **Binary Search Trees** and some Javascript code to render these trees in a web page. I used this API
in my final year project (jSCAPE) to provide a visual aid to help students answer exercises on binary search trees.

###Files
The Java implementation resides in a single file called `BST.java`.
```java
    BST<Integer> bst = new BST<>();
    
    bst.insert(34);
    bst.insert(22);
    bst.insert(28);
    bst.insert(56);
    
    String bstJSON = bst.toJSON();
```
The code snippet above shows the creation of a binary tree, how to insert a few elements and how to get the JSON representation of the tree, so that it can be used by the visualizer. Take
a look at the rest of the API for other functions which can be called on binary search trees.

The Binary Search Tree visualizer is programmed in Javascript using the JIT library. The files for the visualizer are:

    -binary_tree.js
    
    -binary_tree.html
    
    -jit.js
    
    -base.css are modified versions of the original CSS files used for the Spacetree demo.
     Links to the originals: 
     http://philogb.github.io/jit/static/v20/Jit/Examples/css/base.css

####Known bugs
For parent nodes which only have one child, sometimes the visualizer won't place the child node in the appropriate location (i.e. to the left or to the right of the parent node). Instead,
the child node is placed directly under the parent node. The cause of this bug has to do with how "null" nodes are removed from the visualization. Depending on whether the "null" nodes
are removed before or after the other nodes have been rendered, this bug can occur. (GIVE BETTER EXPLANATION)
   
###TODO list   
* Write small tutorial on how to use the visualizer.
* Explain JSON format for binary tree + example.
* Clean up javascript code, html code.
* Add links to library and references used for this project.
