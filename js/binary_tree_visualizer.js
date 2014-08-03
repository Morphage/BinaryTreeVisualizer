var labelType, useGradients, nativeTextSupport, animate;

(function() {
    var ua = navigator.userAgent,
        iStuff = ua.match(/iPhone/i) || ua.match(/iPad/i),
        typeOfCanvas = typeof HTMLCanvasElement,
        nativeCanvasSupport = (typeOfCanvas == 'object' || typeOfCanvas == 'function'),
        textSupport = nativeCanvasSupport 
            && (typeof document.createElement('canvas').getContext('2d').fillText == 'function');

    /* 
     * I'm setting this based on the fact that ExCanvas provides text support for IE
     * and that as of today iPhone/iPad current text support is lame.
     */
    labelType = (!nativeCanvasSupport || (textSupport && !iStuff))? 'Native' : 'HTML';
    nativeTextSupport = labelType == 'Native';
    useGradients = nativeCanvasSupport;
    animate = !(iStuff || !nativeCanvasSupport);
})();

function visualize(binary_tree_json){
    // Initialize Spacetree
    // Create a new ST instance
    var st = new $jit.ST({
        /* id of viz container element */
        injectInto: 'infovis',

        /* Set duration for the animation. */
        duration: 200,
		orientation: 'top',

        /* Set animation transition type. */
        transition: $jit.Trans.Quart.easeInOut,

        /* Set distance between node and its children. */
        levelDistance: 50,
		levelsToShow: 8,
		constrained: false,

        /* Enable panning. */
        Navigation: {
          enable:true,
          panning:true
        },

        /* Set node and edge styles
           Set overridable = true for styling individual
           Nodes or edges */
        Node: {
            height: 20,
            width: 60,
			dim: 30,
            type: 'circle',
            color: '#aaa',
            overridable: true
        },
        
        Edge: {
            type: 'line',
            overridable: true
        },
        
        // This method is called on DOM label creation.
        // Use this method to add event handlers and styles to
        // your node.
        onCreateLabel: function(label, node){
            label.id = node.id;            
            label.innerHTML = node.name;
            //set label styles
            var style = label.style;
            style.width = 60 + 'px';
            style.height = 17 + 'px';            
            style.cursor = 'pointer';
            style.color = '#333';
            style.fontSize = '0.8em';
            style.textAlign= 'center';
            style.paddingTop = '3px';
        },
        
        // This method is called right before plotting
        // a node. It's useful for changing an individual node
        // style properties before plotting it.
        // The data properties prefixed with a dollar
        // sign will override the global node style properties.
        onBeforePlotNode: function(node){
            //add some color to the nodes in the path between the
            //root node and the selected node.
            if (node.selected) {
                node.data.$color = "#ff7";
            }
            else {
                delete node.data.$color;
                //if the node belongs to the last plotted level
                if(!node.anySubnode("exist")) {
                    //count children number
                    var count = 0;
                    node.eachSubnode(function(n) { count++; });
                    //assign a node color based on
                    //how many children it has
                    node.data.$color = ['#aaa', '#baa', '#caa', '#daa', '#eaa', '#faa'][count];                    
                }
            }
        },
        
        /* This method is called right before plotting an edge. It's useful for changing an
         * individual edge style properties before plotting it. Edge data properties prefixed
         * with a dollar sign will override the Edge global style properties.
         */
        onBeforePlotLine: function(adj){
            if (adj.nodeFrom.selected && adj.nodeTo.selected) {
                adj.data.$color = "#eed";
                adj.data.$lineWidth = 3;
            }
            else {
                delete adj.data.$color;
                delete adj.data.$lineWidth;
            }
        }
    });
    
    /* Load JSON data. */
    st.loadJSON(binary_tree_json);

    /* Compute node positions and layout. */
    st.compute();

    /* Emulate a click on the root node to expand it. */
    st.onClick(st.root);

	/* Remove null nodes from the visualization. */
    st.graph.eachNode(function(node) {  
        if (node.name == "null") {
            st.op.removeNode(node.id, {  
                type: 'fade:seq',
                hideLabels: false,
                transition: $jit.Trans.Quart.easeOut
            });
		}
    });
}
