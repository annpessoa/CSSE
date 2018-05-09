// --------------------------- graphm.cpp --------------------------------------

// Anneliese Pessoa CSS 343 A

// May 1, 2017

// May 12, 2017

// -----------------------------------------------------------------------------

// Purpose - This file provides implementation neccessary to build a graph of up
//           to 100 locations, insert edges between the locations, find the
//           shortest distance from one location to another (for all nodes), and
//           print out results to the console.
// -----------------------------------------------------------------------------

#include "graphm.h"


//------------------------- constructors/destructor ----------------------------

//------------------------- GraphM() -------------------------------------------
// Description
// GraphM():        Default GraphM constructor.
// Preconditions:   None
// Postconditions:  An empty graph is created. All values in the 2D array are
//                  set as follows: visited is set to false, dist is set to
//                  infinity, and path is set to 0. A 2D cost array is also
//                  initialized so that all values are infinity.
//------------------------------------------------------------------------------
GraphM::GraphM(){
    //initialize size to 0
    size = 0;
    //initialize data in the table
    for(int i = 1; i < MAXNODES; i++){
        for(int j = 1; j < MAXNODES; j++){
            //initialize table, each node has a path of 0, dist of infinity, and
            //none have been visited yet
            T[i][j].visited = false;
            T[i][j].dist = INT_MAX;
            T[i][j].path = 0;
            
            //initialize the adjacency matrix
            C[i][j] = INT_MAX;
        }
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
bool GraphM::buildGraph(ifstream & input){
    //first get initial data: size and node names
    getGraphData(input);
    
    //update graph with all edges specified in the text file
    int first = 0, second = 0, distance = 0;
    
    //ensure size input is valid
    if(size <= 0) return false;
    //infinite loop - ends when no more data is available for the graph
    for(;;){
        if(input.eof()) break;
        //get first node, second node, and distance between the two
        input >> first;
        input >> second;
        input >> distance;
        
        //first node not within range, no edge is inserted
        if(first == 0 || first > 101) break;
        //second node not within range, no edge is inserted
        if(second == 0 || second >101) break;
        
        //insert edge between first and second node in text file
        insertEdge(first, second, distance);
    }
    return true;
}

//------------------------- getGraphData() -------------------------------------
// Description
// getGraphData():  Helper function for buildGraph(). Reads the size of the
//                  graph and all node names from the text file.
// Preconditions:   None;
// Postconditions:  The size data member is set and each node name is added
//                  to the data array.
//------------------------------------------------------------------------------
bool GraphM::getGraphData(ifstream &input){
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
        data[i].setData(input);
    }
    
    //if no errors in input detected - return true
    return true;
}

//------------------------- insertEdge() ---------------------------------------
// Description
// insertEdge():    Inserts an edge in between two given nodes in the graph.
// Preconditions:   None.
// Postconditions:  The distance between the first and second node is added
//                  into the cost array.
//------------------------------------------------------------------------------
bool GraphM::insertEdge(int firstNode, int secondNode, int distance){
    //prevents inserting an edge if first node == second node
    if(firstNode == secondNode) return false;
    //invalid first node
    if(firstNode < 1 || firstNode > size) return false;
    //invalid second node
    if(secondNode < 1 || secondNode > size) return false;
    //invalid distance
    if(distance <= 0 || distance >= INT_MAX ) return false;
    
    //all values are valid, an edge is added between the two nodes
    C[firstNode][secondNode] = distance;
    
    //successful edge insert
    return true;
}

//------------------------- removeEdge() ---------------------------------------
// Description
// removeEdge():    Removes an edge in between two given nodes in the graph.
// Preconditions:   None.
// Postconditions:  The distance set in between the two nodes is set to
//                  infinity. If the edge does not exist, no edge is removed.
//------------------------------------------------------------------------------
bool GraphM::removeEdge(int firstNode, int secondNode){
    //prevents inserting an edge if first node == second node
    if(firstNode == secondNode) return false;
    //invalid first node
    if(firstNode < 1 || firstNode > size) return false;
    //invalid second node
    if(secondNode < 1 || secondNode > size) return false;
    //make sure not to delete an edge that does not exist
    if(C[firstNode][secondNode] == INT_MAX) return false;
    
    //all values are valid, the edge between the two nodes is removed
    C[firstNode][secondNode] = INT_MAX;
    //update shortest paths
    
    return true;
}

//------------------------- findShortestPath() ---------------------------------
// Description
// findShortestPath():  Perform Dijkstra's shortest path in the graph to
//                      find shortest paths from each node, to every other
//                      node.
// Preconditions:       The graph is built properly from valid input file.
// Postconditions:      The path value in the table is set to contain the
//                      shortest paths to each node in the graph.
//------------------------------------------------------------------------------
void GraphM::findShortestPath(){
    //initialize v
    int v = 0;
    
    //loop through all nodes in the graph
    for(int source = 1; source <= size; source++){
        //set the distance for the source to 0 - ex dist from 1 to 1 = 0
        T[source][source].dist = 0;
        
        //loop through every other node
        for(int i = 1; i <= size; i++){
            //find v
            v = getV(source);
            
            //mark v visited
            T[source][v].visited = true;
            
            //for each w adjacent to v
            for(int w = 1; w <= size; w++){
                //if w not visited
                //if the distance between v and w is not infinity
                if(C[v][w] != INT_MAX && T[source][w].visited == false){
                    if(T[source][w].dist > T[source][v].dist + C[v][w]){
                        //set the distance to the smaller distance
                        T[source][w].dist = T[source][v].dist + C[v][w];
                        //set path to reference back to the previous node
                        T[source][w].path = v;
                    }
                }
            }
            
            
        }
    }
    //re-initialize the visited variable in all nodes
    setNodesUnvisited();
}

//------------------------- getV() ---------------------------------------------
// Description
// getV():         Helper function for findShortestPath(). Helps find node
//                  v by finding the neighbor with minimum dist from the node.
// Preconditions:   None.
// Postconditions:  An integer is returned that represents the index of
//                  the node that has the shortest distance fromt the source.
//------------------------------------------------------------------------------
int GraphM::getV(int source){
    //initialize minimum distance
    int minDist = INT_MAX;
    
    //initialize return value of v
    int retV = 0;
    
    //loop through each column in the 2D array
    for (int i = 1; i <= size; i++){
        
        //if the value in the array is less than the minimum distance
        //and if the node has not been visited yet
        if(T[source][i].dist < minDist && T[source][i].visited == false){
            //set the minimum distance
            minDist = T[source][i].dist;
            
            //set current index of min distance
            retV = i;
        }
    }
    //return neighbor with shortest distance
    return retV;
}

//------------------------- setNodesUnvisited() --------------------------------
// Description
// setNodesUnvisited():  Reinitializes all node's visited values to false.
//                       Helper function fro findShortestPath().
// Preconditions:        None.
// Postconditions:       All nodes in the table have the value, visited, set
//                       to false.
//------------------------------------------------------------------------------
void GraphM::setNodesUnvisited(){
    //loop through rows and columns of table
    for(int i = 1; i <= size; i++){
        for(int j = 1; j <= size; j++){
            //set each node to unvisited
            T[i][j].visited = false;
        }
    }
}

//------------------------- displayAll() ---------------------------------------
// Description
// displayAll():    Displays all data regarding the graph.
// Preconditions:   The graph was built from valid data and findShortestPath()
//                  has been run before calling this method.
// Postconditions:  All nodes and their shortest paths are output onto the
//                  console.
//------------------------------------------------------------------------------
void GraphM::displayAll() const{
    //print descriptions for top of table
    cout << "Description         From node   To node   Dijkstra's     Path" << endl;
    
    //go through all nodes in the graph
    for(int i = 1; i <= size; i++){
        //display name
        cout << data[i] << endl;
        
        //display path from current node to all other nodes
        for(int j = 1; j <= size; j++){
            //prevent finding path from starting node to end node
            if(j != i && T[i][j].dist > 0 && T[i][j].dist < INT_MAX){
                //print out which nodes are being worked with
                cout <<setw(27)<< i << setw(8) << j;
                
                //display shortest path for each pair of nodes
                cout << setw(11)<< T[i][j].dist << setw(12);
                
                //display numbered path
                displayNumberedPath(i, j);
                cout << endl;
                
            }else if(j != i){ //no path available from the two nodes
                cout <<setw(27)<< i << setw(8) << j;
                cout << setw(11) <<" ---";
                cout << endl;
            }
        }
        cout << endl;
    }
    
}

//------------------------- display() ------------------------------------------
// Description
// display():       Displays the shortest distance from one node to the other
//                  and the path taken to get from start to finish.
// Preconditions:   The graph was built from valid data and findShortestPath()
//                  has been run before calling this method.
// Postconditions:  Numbered and named paths from first node to second node
//                  is printed onto the console.
//------------------------------------------------------------------------------
void GraphM::display(int firstNode, int secondNode) const{
    if(firstNode < 1 || firstNode > size) return;
    if(secondNode < 1 || secondNode > size) return;
    //print out the beginning and end node
    cout << setw(7) << firstNode << setw(7) << secondNode;
    if(T[firstNode][secondNode].dist == 0){
        cout << setw(7) << "---" << endl;
    }
    //print out shortest known distance
    else if(T[firstNode][secondNode].dist != INT_MAX){
        cout << setw(7) << T[firstNode][secondNode].dist << setw(7);
        //print out path taken to get from begin to end (node numbers)
        displayNumberedPath(firstNode, secondNode);
        //print out path taken to get from begin to end (node names)
        cout << endl;
        displayNamedPath(firstNode, secondNode);
    }else{
        cout << setw(7) << "---" << endl;
    }
    cout << endl;
}

//------------------------- displayNumberedPath() ------------------------------
// Description
// displayNumberedPath():   Helper function for display() and displayAll().
// Preconditions:           None
// Postconditions:          Numbered path from one node to the other is
//                          output onto the console.
//------------------------------------------------------------------------------
void GraphM::displayNumberedPath(int firstNode, int secondNode) const{
    if(firstNode != secondNode){
        //if there is an available path
        if(T[firstNode][secondNode].path != 0){
            //recursive call to last node in the path
            displayNumberedPath(firstNode, T[firstNode][secondNode].path);
        }
    }
    
    //output path
    cout << secondNode << " ";
}

//------------------------- displayNamedPath() ---------------------------------
// Description
// displayNamedPath():      Helper function for display() and displayAll().
// Preconditions:           None
// Postconditions:          Named path from one node to the other is
//                          output onto the console.
//------------------------------------------------------------------------------
void GraphM::displayNamedPath(int firstNode, int secondNode) const{
    if(firstNode != secondNode){
        //if there is an available path
        if(T[firstNode][secondNode].path != 0){
            //recursive call to last node in the path
            displayNamedPath(firstNode, T[firstNode][secondNode].path);
        }
    }
    
    //output path
    cout << data[secondNode] << endl;
}
