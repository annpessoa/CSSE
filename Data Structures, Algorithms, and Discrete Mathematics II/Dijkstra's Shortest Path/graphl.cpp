// --------------------------- graphl.cpp --------------------------------------

// Anneliese Pessoa CSS 343 A

// May 1, 2017

// May 12, 2017

// -----------------------------------------------------------------------------

// Purpose - This file provides implementation neccessary to build a graph of up
//           to 100 locations, perform depth first search on each node, and
//           print out results to the console.
// -----------------------------------------------------------------------------
#include "graphl.h"

//------------------------- GraphL() -------------------------------------------
// Description
// GraphM():        Default GraphL constructor.
// Preconditions:   None
// Postconditions:  An empty graph is created. All values in the adjacency list
//                  are set to NULL.
//------------------------------------------------------------------------------
GraphL::GraphL(){
    //initialize size
    size = 0;
    
    //initialize adjacency list
    for(int i = 1; i < MAXNODESL; i++){
        //set each data to NULL
        aList[i].data = NULL;
        //set each edgeHead to NULL
        aList[i].edgeHead = NULL;
        //set visited for each node to false
        aList[i].visited = false;
    }
}

//------------------------- ~GraphL() ------------------------------------------
// Description
// GraphM():        GraphL destructor.
// Preconditions:   None
// Postconditions:  If the graph contains nodes, the graph is cleared.
//------------------------------------------------------------------------------
GraphL::~GraphL(){
    if(size > 0){
        clear();
    }
}

//------------------------- Graph Functions-------------------------------------

//------------------------- buildGraph() ---------------------------------------
// Description
// buildGraph():    Creates a graph from the input file.
// Preconditions:   The input file is formatted in the way specified in the
//                  requirements.
// Postconditions:  An edge is inserted between each node listed in the text
//                  file. Additionally, the names of each node are added as
//                  NodeData.
//------------------------------------------------------------------------------
bool GraphL::buildGraph(ifstream & input){
    //first get initial data: size and node names
    getGraphData(input);
    
    //initialize ints that represent two nodes
    int first = 0, second = 0;
    
    //ensure size input is valid
    if(size <= 0) return false;
    
    //infinite loop - ends when no more data is available for the graph
    for(;;){
        if(input.eof()) break;
        input >> first;
        input >> second;
        
        //first node not within range, no edge is inserted
        if(first == 0 || first > 101) break;
        //second node not within range, no edge is inserted
        if(second == 0 || second >101) break;
        
        //insert edge into graph
        insert(first, second);
    }
    return true;
}

//------------------------- getGraphData() -------------------------------------
// Description
// getGraphData():  Helper function for buildGraph(). Reads the size of the
//                  graph and all node names from the text file.
// Preconditions:   None;
// Postconditions:  The size data member is set and each node name is added
//                  to the adjacency list.
//------------------------------------------------------------------------------
bool GraphL::getGraphData(ifstream & input){
    //get the number of nodes from the text file
    input >> size;
    
    //prevent having more than 100 nodes
    if(size > 100) return false;
    
    //remove extra character before node name
    string extra;
    getline(input, extra);
    
    //get the name of each location in the text file and place in array of
    //NodeData
    for(int i = 1; i <= size; i++){
        //make new NodeData for each GraphNode
        getline(input, extra);
        
        //add name of GraphNode to adjacency list
        aList[i].data = new NodeData(extra);
    }
    
    //if no errors in input detected - return true
    return true;
    
}

//------------------------- insert() -------------------------------------------
// Description
// insert():        Inserts an edge in between two given nodes in the graph.
//                  helper function for buildGraph();
// Preconditions:   None.
// Postconditions:  Edges are set in the adjacaency array.
//------------------------------------------------------------------------------
bool GraphL::insert(int firstNode, int secondNode){
    //prevent making an edge if the nodes are the same
    if(firstNode == secondNode) return false;
    //invalid first node
    if(firstNode < 1 || firstNode > size) return false;
    //invalid second node
    if(secondNode < 1 || secondNode > size) return false;
    
    //create new node to be inserted
    EdgeNode * newNode = new EdgeNode;
    
    //give new node initial values
    //set the adjacent node to the new node to the int, secondNode
    newNode->adjGraphNode = secondNode;
    
    //non-empty list
    if(aList[firstNode].edgeHead != NULL){
        //insert new node before head
        newNode->nextEdge = aList[firstNode].edgeHead;
        //set new edge
        aList[firstNode].edgeHead = newNode;
    }else{
        //insert into an empty list
        //make the new edge the head of the list
        aList[firstNode].edgeHead = newNode;
        //there is no next node yet so it is set to NULL
        newNode->nextEdge = NULL;
    }
    //successful insert
    return true;
}

//------------------------- remove() ---------------------------------------
// Description
// remove():        Removes a node at the nodeIndex.
//                  Helper function for clear and the destructor.
// Preconditions:   None.
// Postconditions:  The node at nodeIndex is removed from the graph.
//------------------------------------------------------------------------------
bool GraphL::remove(int nodeIndex){
    //index out of bounds: cannot remove
    if(nodeIndex < 1 || nodeIndex > MAXNODESL) return false;
    
    //initialize pointer for node to be removed
    EdgeNode * temp;
    
    //set temp to the EdgeNode to be removed
    temp = aList[nodeIndex].edgeHead;
    
    //set previous edge head at nodeIndex and set to the next edge in the list
    aList[nodeIndex].edgeHead = aList[nodeIndex].edgeHead->nextEdge;
    
    //delete the edge
    temp->nextEdge = NULL;
    delete temp;
    temp = NULL;
    
    //successful removal
    return true;
}

//------------------------- depthFirstSearch() ---------------------------------
// Description
// depthFirstSearch(): Performs depth first search on the graph starting at
//                     node 1.
// Preconditions:      None;
// Postconditions:     The DFS is output onto the console.
//------------------------------------------------------------------------------
void GraphL::depthFirstSearch(){
    cout << "Depth-fisrt ordering:";
    //call helper funciton
    dfs(1);
    cout << endl;
}

//------------------------- dfs() ----------------------------------------------
// Description
// dfs():           Helper function for depthFirstSearch(). Performs the full
//                  dfs algorothm.
// Preconditions:   None.
// Postconditions:  The DFS of the graph is output onto the console.
//------------------------------------------------------------------------------
void GraphL::dfs(int curNode){
    //print out the current node
    cout << "  " << curNode;
    //mark current node as visited
    aList[curNode].visited = true;
    
    //create temp cur node to loop through adjacency list
    EdgeNode * cur = aList[curNode].edgeHead;
    
    //continue looping through the list until index == size is reached
    while(cur != NULL){
        //call dfs if the adjacent node is not visited
        if(aList[cur->adjGraphNode].visited == false){
            dfs(cur->adjGraphNode);
        }
        
        //progress in the list
        cur = cur->nextEdge;
    }
}

//------------------------- setNodesUnvisited() --------------------------------
// Description
// setNodesUnvisited():  Reinitializes all node's visited values to false.
//                       Helper function for DFS algoritm.
// Preconditions:        None.
// Postconditions:       All nodes in the table have the value, visited, set
//                       to false.
//------------------------------------------------------------------------------
void GraphL::setNodesUnvisited(){
    //loop through adjacency list and set all visited values to false
    for(int i = 1; i <= size; i++){
        aList[i].visited = false;
    }
    
}

//------------------------- displayGraph() -------------------------------------
// Description
// displayGraph():  Displays all values contained in the graph.
// Preconditions:   None.
// Postconditions:  Each node is numbered and that number, along with the node's
//                  name, is printed onto the console. Beneath that, each edge
//                  is output onto the console as well.
//------------------------------------------------------------------------------
void GraphL::displayGraph(){
    cout << "Graph:" << endl;
    //loop through each node in the adjacency list
    for(int i = 1; i <= size; i++){
        //print out each node number and name
        cout << "Node" << i << "          " << *aList[i].data;
        cout << endl << endl;
        
        //if there are edges available, call helper function
        if(aList[i].edgeHead != NULL){
            printEdges(i);
        }
    }
    
}

//------------------------- printEdges() ---------------------------------------
// Description
// printEdges():    Helper function for displayGraph().
// Preconditions:   None.
// Postconditions:  Each valid edge is output onto the console.
//------------------------------------------------------------------------------
void GraphL::printEdges(int index){
    //make a temp cur pointer to loop through list
    EdgeNode * cur = aList[index].edgeHead;
    
    //loop through adjacency list
    for(int i = 1; i < size; i++){
        //print out the index of the node and index of the adjacent node
        cout << "  edge  " << index << " " << cur->adjGraphNode << endl;
        //progress through the list if the nextEdge is not NULL
        if(cur->nextEdge != NULL){
            cur = cur->nextEdge;
        }else{
            //end loop if no more edges to work with
            break;
        }
    }
}


//------------------------- clear() --------------------------------------------
// Description
// clear():         Helper function for the deconstructor. Removes all nodes
//                  from the graph.
// Preconditions:   None.
// Postconditions:  All nodes are removed from the adjacency list.
//------------------------------------------------------------------------------
void GraphL::clear(){
    //loop through each node in adjacency list
    for(int i = 1; i <= size; i++){
        //while the list is not empty, remove all nodes
        while(aList[i].edgeHead != NULL){
            //remove current node
            remove(i);
            //delete data at current node
            delete aList[i].data;
            aList[i].data = NULL;
            size --;
        }
    }
    
}
