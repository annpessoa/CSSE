// --------------------------- graphl.h ----------------------------------------

// Anneliese Pessoa CSS 343 A

// May 1, 2017

// May 12, 2017

// -----------------------------------------------------------------------------

// Purpose - This file provides definitions neccessary to build a graph of up
//           to 100 locations, perform depth first search on each node, and
//           print out results to the console.
// -----------------------------------------------------------------------------

#ifndef graphl_h
#define graphl_h

#include <stdio.h>
#include <iostream>
#include <iomanip>
#include "nodedata.h"
#include "limits.h"

const int MAXNODESL = 101;

class GraphL {
    
    struct EdgeNode;      // forward reference for the compiler
    
    struct GraphNode {    // structs used for simplicity, use classes if desired
        EdgeNode* edgeHead; // head of the list of edges
        NodeData* data;     // data information about each node
        bool visited;
    };
    
    struct EdgeNode {
        int adjGraphNode;  // subscript of the adjacent graph node
        EdgeNode* nextEdge;
    };
    
public:
    //------------------------- GraphL() ---------------------------------------
    // Description
    // GraphM():        Default GraphL constructor.
    // Preconditions:   None
    // Postconditions:  An empty graph is created. All values in the adjacency
    //                  array are set to NULL.
    //--------------------------------------------------------------------------
    GraphL();
    
    //------------------------- ~GraphL() --------------------------------------
    // Description
    // GraphM():        GraphL destructor.
    // Preconditions:   None
    // Postconditions:  If the graph contains nodes, the graph is cleared.
    //--------------------------------------------------------------------------
    ~GraphL();
    
    //------------------------- buildGraph() -----------------------------------
    // Description
    // buildGraph():    Creates a graph from the input file.
    // Preconditions:   The input file is formatted in the way specified in the
    //                  requirements.
    // Postconditions:  An edge is inserted between each node listed in the text
    //                  file. Additionally, the names of each node are added as
    //                  NodeData.
    //--------------------------------------------------------------------------
    bool buildGraph(ifstream & input);
    
    //------------------------- displayGraph() ---------------------------------
    // Description
    // displayGraph():  Displays all values contained in the graph.
    // Preconditions:   None.
    // Postconditions:  Each node is numbered and that number, along with the node's
    //                  name, is printed onto the console. Beneath that, each edge
    //                  is output onto the console as well.
    //--------------------------------------------------------------------------
    void displayGraph();
    
    //------------------------- depthFirstSearch() -----------------------------
    // Description
    // depthFirstSearch(): Performs depth first search on the graph starting at
    //                     node 1.
    // Preconditions:      None;
    // Postconditions:     The DFS is output onto the console.
    //--------------------------------------------------------------------------
    void depthFirstSearch();
    
private:
    int size;
    // array of GraphNodes
    GraphNode aList[MAXNODESL];
    
    //------------------------- Helper Functions--------------------------------
    
    //------------------------- getGraphData() ---------------------------------
    // Description
    // getGraphData():  Helper function for buildGraph(). Reads the size of the
    //                  graph and all node names from the text file.
    // Preconditions:   None
    // Postconditions:  The size data member is set and each node name is added
    //                  to the adjacency list.
    //--------------------------------------------------------------------------
    bool getGraphData(ifstream & input);
    
    //------------------------- insert() ---------------------------------------
    // Description
    // insert():        Inserts an edge in between two given nodes in the graph.
    //                  helper function for buildGraph();
    // Preconditions:   None.
    // Postconditions:  Edges are set in the adjacacency array.
    //--------------------------------------------------------------------------
    bool insert(int begin, int end);
    
    //------------------------- remove() ---------------------------------------
    // Description
    // remove():        Removes the node at the nodeIndex.
    //                  Helper function for clear() and the destructor.
    // Preconditions:   None.
    // Postconditions:  The node at nodeIndex is removed from the graph.
    //--------------------------------------------------------------------------
    bool remove(int nodeIndex);
    
    //------------------------- dfs() ------------------------------------------
    // Description
    // dfs():           Helper function for depthFirstSearch(). Performs the full
    //                  dfs algorothm recursively.
    // Preconditions:   None.
    // Postconditions:  The DFS of the graph is output onto the console.
    //--------------------------------------------------------------------------
    void dfs(int curNode);
    
    //------------------------- setNodesUnvisited() ----------------------------
    // Description
    // setNodesUnvisited():  Reinitializes all node's visited values to false.
    //                       Helper function for DFS algoritm.
    // Preconditions:        None.
    // Postconditions:       All nodes in the table have the value, visited, set
    //                       to false.
    //--------------------------------------------------------------------------
    void setNodesUnvisited();
    
    //------------------------- printEdges() -----------------------------------
    // Description
    // printEdges():    Helper function for displayGraph().
    // Preconditions:   None.
    // Postconditions:  Each valid edge is output onto the console.
    //--------------------------------------------------------------------------
    void printEdges(int index);
    
    //------------------------- clear() ----------------------------------------
    // Description
    // clear():         Helper function for the deconstructor. Removes all nodes
    //                  from the graph.
    // Preconditions:   None.
    // Postconditions:  All nodes are removed from the adjacency list.
    //--------------------------------------------------------------------------
    void clear();
    
};
#endif /* graphl_h */
