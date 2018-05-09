// --------------------------- graphm.h ----------------------------------------

// Anneliese Pessoa CSS 343 A

// May 1, 2017

// May 12, 2017

// -----------------------------------------------------------------------------

// Purpose - This file provides definitions neccessary to build a graph of up
//           to 100 locations, insert edges between the locations, find the
//           shortest distance from one location to another (for all nodes), and
//           print out results to the console.
// -----------------------------------------------------------------------------

#ifndef graphm_h
#define graphm_h

#include <stdio.h>
#include <iostream>
#include <iomanip>
#include "nodedata.h"
#include "limits.h"

const int MAXNODES = 101;

class GraphM {
    //struct for the table of graph info
    struct TableType {
        bool visited;          // whether node has been visited
        int dist;              // shortest distance from source known so far
        int path;              // previous node in path of min dist
    };
    
public:
    //------------------------- constructors/destructor ------------------------
    
    //------------------------- GraphM() ---------------------------------------
    // Description
    // GraphM():        Default GraphM constructor.
    // Preconditions:   None
    // Postconditions:  An empty graph is created. All values in the 2D array are
    //                  set as follows: visited is set to false, dist is set to
    //                  infinity, and path is set to 0. A 2D cost array is also
    //                  initialized so that all values are infinity.
    //--------------------------------------------------------------------------
    GraphM();
    
    //------------------------- Graph Functions---------------------------------
    
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
    
    //------------------------- insertEdge() -----------------------------------
    // Description
    // insertEdge():    Inserts an edge in between two given nodes in the graph.
    // Preconditions:   None.
    // Postconditions:  The distance between the first and second node is added
    //                  into the cost array.
    //--------------------------------------------------------------------------
    bool insertEdge(int firstNode, int secondNode, int distance);
    
    //------------------------- removeEdge() -----------------------------------
    // Description
    // removeEdge():    Removes an edge in between two given nodes in the graph.
    // Preconditions:   None.
    // Postconditions:  The distance set in between the two nodes is set to
    //                  infinity. If the edge does not exist, no edge is removed.
    //--------------------------------------------------------------------------
    bool removeEdge(int firstNode, int secondNode);
    
    //------------------------- findShortestPath() -----------------------------
    // Description
    // findShortestPath():  Perform Dijkstra's shortest path in the graph to
    //                      find shortest paths from each node, to every other
    //                      node.
    // Preconditions:       The graph is built properly from valid input file.
    // Postconditions:      The path value in the table is set to contain the
    //                      shortest paths to each node in the graph.
    //--------------------------------------------------------------------------
    void findShortestPath();
    
    //------------------------- displayAll() -----------------------------------
    // Description
    // displayAll():    Displays all data regarding the graph.
    // Preconditions:   The graph was built from valid data and findShortestPath()
    //                  has been run before calling this method.
    // Postconditions:  All nodes and their shortest paths are output onto the
    //                  console.
    //--------------------------------------------------------------------------
    void displayAll() const;
    
    //------------------------- display() --------------------------------------
    // Description
    // display():       Displays the shortest distance from one node to the other
    //                  and the path taken to get from start to finish.
    // Preconditions:   The graph was built from valid data and findShortestPath()
    //                  has been run before calling this method.
    // Postconditions:  Numbered and named paths from first node to second node
    //                  is printed onto the console.
    //--------------------------------------------------------------------------
    void display(int begin, int end) const;
    
private:
    //------------------------- Helper Functions--------------------------------
    
    //------------------------- getGraphData() ---------------------------------
    // Description
    // getGraphData():  Helper function for buildGraph(). Reads the size of the
    //                  graph and all node names from the text file.
    // Preconditions:   None;
    // Postconditions:  The size data member is set and each node name is added
    //                  to the data array.
    //--------------------------------------------------------------------------
    bool getGraphData(ifstream & input);
    
    //------------------------- getV() -----------------------------------------
    // Description
    // getV():         Helper function for findShortestPath(). Helps find node
    //                  v by finding the neighbor with minimum dist from the node.
    // Preconditions:   None.
    // Postconditions:  An integer is returned that represents the index of
    //                  the node that has the shortest distance fromt the source.
    //--------------------------------------------------------------------------
    int getV(int source);
    
    //------------------------- setNodesUnvisited() ----------------------------
    // Description
    // setNodesUnvisited():  Reinitializes all node's visited values to false.
    //                       Helper function fro findShortestPath().
    // Preconditions:        None.
    // Postconditions:       All nodes in the table have the value, visited, set
    //                       to false.
    //--------------------------------------------------------------------------
    void setNodesUnvisited();
    
    //------------------------- displayNumberedPath() -------------------------
    // Description
    // displayNumberedPath():   Helper function for display() and displayAll().
    // Preconditions:           None
    // Postconditions:          Numbered path from one node to the other is
    //                          output onto the console.
    //--------------------------------------------------------------------------
    void displayNumberedPath(int begin, int end) const;
    
    //------------------------- displayNamedPath() ----------------------------
    // Description
    // displayNamedPath():      Helper function for display() and displayAll().
    // Preconditions:           None
    // Postconditions:          Named path from one node to the other is
    //                          output onto the console.
    //--------------------------------------------------------------------------
    void displayNamedPath(int begin, int end) const;
    
    //------------------------- Class Data -------------------------------------
    NodeData data[MAXNODES];              // data for graph nodes
    int C[MAXNODES][MAXNODES];            // Cost array, the adjacency matrix
    int size;                             // number of nodes in the graph
    TableType T[MAXNODES][MAXNODES];      // stores visited, distance, path
    
};

#endif /* graphm_h */
