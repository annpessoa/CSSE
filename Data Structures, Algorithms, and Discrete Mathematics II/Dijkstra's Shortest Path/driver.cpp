// --------------------------- driver.cpp --------------------------------------

// Anneliese Pessoa CSS 343 A

// May 1, 2017

// May 12, 2017

// -----------------------------------------------------------------------------

// Purpose - This file provides a driver neccessary to create graphs with up to
//           100 nodes. This tests all public functions within GraphM and
//           GraphL. The shortest path will be computed for the GraphM objects
//           and DFS will be performed on GraphL objects.
// -----------------------------------------------------------------------------

#include <iostream>
#include <fstream>
#include "graphl.h"
#include "graphm.h"
using namespace std;
void part1(ifstream & input);
void part2(ifstream & input);

//------------------------- main() ---------------------------------------------
// Description
// main():          Main method for GraphM and GraphL Methods.
// Preconditions:   None
// Postconditions:
//------------------------------------------------------------------------------
int main() {
    //------------------------- Part 1 -----------------------------------------
    
    ifstream infile1("data31.txt"); //get input text for part 1
    if (!infile1) {
        cout << "File could not be opened." << endl;
        return 1;
    }
    cout << "PART 1";
    //perform part one of the assignment
    part1(infile1);
    
    //------------------------- Part 2 -----------------------------------------

    ifstream infile2("data32.txt"); //get input text for part 2
    if (!infile2) {
        cout << "File could not be opened." << endl;
        return 1;
    }
    
    cout << endl << endl << "PART 2";
    //perform part two of the assignment
    part2(infile2);
    
    cout << endl;
    return 0;
}

//------------------------- part1() ---------------------------------------------
// Description
// part1():         Performs part one of the assignment.
// Preconditions:   None
// Postconditions:  All public functions in GraphM are tested and output to the
//                  console.
//------------------------------------------------------------------------------
void part1(ifstream & input){
    //for each graph, find the shortest path from every node to all other nodes
    for (;;){
        //create new graph
        GraphM G;
        
        //build graph
        G.buildGraph(input);
        
        //end loop at the end of the input file
        if (input.eof()) break;
        cout << endl << endl << endl << "Building graph: " << endl << endl;
        
        //perform shortest path algorithm
        cout << "Finding shortest paths: " << endl << endl;
        G.findShortestPath();
        
        //display shortest distance, path to cout
        G.displayAll();
        
        //display path from node 3 to 1 to cout
        G.display(3, 1);
        //display path from node 1 to 2 to cout
        G.display(1, 2);
        // display path from node 1 to 4 to cout
        G.display(1, 4);
        
        //added tests
       
        //test remove edge
        cout << "Edge Removal (1,2): " << endl << endl;
        G.removeEdge(1, 2);
        
        //recalculate shortest path
        cout << "recalculating shortest paths: " << endl << endl;
        G.findShortestPath();
        
        //display same paths as before
        //display path from node 3 to 1 to cout
        G.display(3, 1);
        //display path from node 1 to 2 to cout
        G.display(1, 2);
        // display path from node 1 to 4 to cout
        G.display(1, 4);
    }
}

void part2(ifstream & input){
    //for each graph, find the depth-first search ordering
    for (;;) {
        //create new graph
        GraphL G;
        
        //build graph
        G.buildGraph(input);
        
        //end loop if at the end of the file
        if (input.eof())break;
        cout << endl << endl << endl << "Building Graph: " << endl << endl;
        //display all node edges
        G.displayGraph();
        
        //find and display depth-first ordering to cout
        cout << endl << "Performing DFS" << endl << endl;
        G.depthFirstSearch();
    }
}
