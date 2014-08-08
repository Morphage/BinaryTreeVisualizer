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

In order to visualize the binary trees, you must use the BinaryTreeVisualizer. It is programmed in Javascript using the JIT library. The files for the visualizer are:

    -binary_tree.js
    
    -binary_tree.html
    
    -jit.js (Javascript InfoVis Toolkit)
    
    -base.css are modified versions of the original CSS files used for the Spacetree demo.
     Links to the originals: 
     http://philogb.github.io/jit/static/v20/Jit/Examples/css/base.css

###How to use
The function responsible for rendering the tree is called <code>visualize(binary_tree_json)</code>, and can be found in **binary_tree.js**. The function takes the JSON representation
of the binary tree as a parameter. Call this function in the html file, for instance, <body onload="visualize(binary_tree_json);">. This will render the binary tree in the web page
at loading time. To customize the BinaryTreeVisualizer, take a look at the References section, which gives links to the Javascript InfoVis Toolkit, where you can find tutorials
and more examples of how to customize visualizations, animations, colors, etc...

###Binary Search Tree JSON Representation
Using the API's <code>toJSON()</code> function makes it a lot easier to generate the JSON representation used by the BinaryTreeVisualizer to render the trees. However, to allow
people to create their own trees manually, the JSON format is shown and explained below:

```json
{id: "3700",
 name: "37",
 data: {},
 children: [{
     id: "2200",
     name: "22",
     data: {},
     children: [{
         id: "1300",
         name: "13",
         data: {},
         children: []},
             {id: "2390",
              name: "null",
              data: {},
              children: []}
        ]
    }, 
    {id: "4400",
     name: "44",
     data: {},
     children: []}
    ]
};
```

Try to organize it so that the json code is on the left and the corresponding binary tree is on the right. If this isn't possible using layout, then make an image of the code
and the tree side by side.

####Known bugs
For parent nodes which only have one child, sometimes the visualizer won't place the child node in the appropriate location (i.e. to the left or to the right of the parent node). Instead,
the child node is placed directly under the parent node. The cause of this bug has to do with how "null" nodes are removed from the visualization. Depending on whether the "null" nodes
are removed before or after the other nodes have been rendered, this bug can occur. (GIVE BETTER EXPLANATION)

###References
-http://philogb.github.io/jit/static/v20/Jit/Examples/css/base.css
   
###TODO list   
* Write small tutorial on how to use the visualizer.
* Explain JSON format for binary tree + example.
* Clean up javascript code, html code.
* Add links to library and references used for this project.
