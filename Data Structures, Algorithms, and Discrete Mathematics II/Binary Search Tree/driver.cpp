// --------------------------- driver.cpp --------------------------------------

// Anneliese Pessoa CSS 343 A

// April 10, 2016

// April 21, 2016

// -----------------------------------------------------------------------------

// Purpose - This file provides a driver neccessary to create Binary Search
//           Trees tThis tests all public functions within the binTree ADT. Most
//           of main is similar to original driver provided.

// -----------------------------------------------------------------------------

//  All output will be printed out onto the console as it runs.
//  Main will be using original input text file for output.txt

// -----------------------------------------------------------------------------

// presumably bintree.h includes nodedata.h so the include is not needed here
#include "bintree.h"
#include <fstream>
#include <iostream>
using namespace std;

const int ARRAYSIZE = 100;

//global function prototypes
void buildTree(BinTree&, ifstream&);
void initArray(NodeData*[]);             // initialize array to NULL

int main() {
	// create file object infile and open it
	// for testing, call your data file something appropriate, e.g., inputdata.txt
	ifstream infile("inputdata.txt");
	if (!infile) {
		cout << "File could not be opened." << endl;
		return 1;
	}

	// the NodeData class must have a constructor that takes a string
	NodeData notND("not");
	NodeData andND("and");
	NodeData sssND("sss");
	NodeData ttttND("tttt");
	NodeData oooND("ooo");
	NodeData yND("y");
	NodeData eND("e");
	NodeData mND("m");
	NodeData tND("t");

    //additional node data for testing
    NodeData aND("a");
    NodeData bND("b");
    NodeData cND("c");
    
    //Create new tree T, and dup
	BinTree T,T2, dup;
    //create new array of size 100
	NodeData* ndArray[ARRAYSIZE];
    //initialize array to NULL
	initArray(ndArray);
    
    //output original data from input file
	cout << "Initial data:" << endl << "  ";
	buildTree(T, infile);              // builds and displays initial data
	cout << endl;
    
	BinTree first(T);                  // test copy constructor
	dup = dup = T;                     // test operator=, self-assignment
	while (!infile.eof()) {
		cout << "Tree Inorder:" << endl << T;             // operator<< does endl
		T.displaySideways();

		// test retrieve 
		NodeData* p;                    // pointer of retrieved object
		bool found;                     // whether or not object was found in tree
		found = T.retrieve(andND, p);
		cout << "Retrieve --> and:  " << (found ? "found" : "not found") << endl;
		found = T.retrieve(notND, p);
		cout << "Retrieve --> not:  " << (found ? "found" : "not found") << endl;
		found = T.retrieve(sssND, p);
		cout << "Retrieve --> sss:  " << (found ? "found" : "not found") << endl;
        
        //additional retrieve checks
        found = T.retrieve(aND, p);
        cout << "Retrieve --> a:  " << (found ? "found" : "not found") << endl;
        found = T.retrieve(bND, p);
        cout << "Retrieve --> b:  " << (found ? "found" : "not found") << endl;
        found = T.retrieve(cND, p);
        cout << "Retrieve --> c:  " << (found ? "found" : "not found") << endl;

		// test getHeight 
		cout << "Height    --> and:   " << T.getHeight(andND) << endl;
		cout << "Height    --> not:   " << T.getHeight(notND) << endl;
		cout << "Height    --> sss:   " << T.getHeight(sssND) << endl;
		cout << "Height    --> tttt:  " << T.getHeight(ttttND) << endl;
		cout << "Height    --> ooo:   " << T.getHeight(oooND) << endl;
		cout << "Height    --> y:     " << T.getHeight(yND) << endl;
        
        //additional height check
        cout << "Height    --> a:     " << T.getHeight(aND) << endl;
        cout << "Height    --> b:     " << T.getHeight(bND) << endl;
        cout << "Height    --> c:     " << T.getHeight(cND) << endl;
        
		// test ==, and != 
		T2 = T;
		cout << "T == T2?     " << (T == T2 ? "equal" : "not equal") << endl;
		cout << "T != first?  " << (T != first ? "not equal" : "equal") << endl;
		cout << "T == dup?    " << (T == dup ? "equal" : "not equal") << endl;
		dup = T;

		// somewhat test bstreeToArray and arrayToBSTree
		T.bstreeToArray(ndArray);
		T.arrayToBSTree(ndArray);
		T.displaySideways();

		T.makeEmpty();                  // empty out the tree
		initArray(ndArray);             // empty out the array

		cout << "---------------------------------------------------------------"
			<< endl;
		cout << "Initial data:" << endl << "  ";
		buildTree(T, infile);
		cout << endl;
	}

	return 0;
}

//------------------------------- buildTree() ----------------------------------
// Description
// buildTree():     To build a tree, read strings from a line, terminating when
//                  "$$" is specific to the client problem, it's best that
//                  building a tree is not a member function. It's a global function.
// Preconditions:   None
// Postconditions:  The BinTree T is filled with values from the text file.
//------------------------------------------------------------------------------
void buildTree(BinTree& T, ifstream& infile) {
    //initialize string
	string s;
    //loop through input
	for (;;) {
        //get string from file
		infile >> s;
        //print out input
		cout << s << ' ';
		if (s == "$$") break;                // at end of one line
		if (infile.eof()) break;             // no more lines of data
		NodeData* ptr = new NodeData(s);     // NodeData constructor takes string
		// would do a setData if there were more than a string
        //attempt to insert the NodeData
		bool success = T.insert(ptr);
		if (!success)
			delete ptr;                       // duplicate case, not inserted 
	}
}

//------------------------------- initArray() ----------------------------------
// Description
// initArray():     Initialize the array of NodeData* to NULL pointers
// Preconditions:   None
// Postconditions:  The Array is initialized to NULL
//------------------------------------------------------------------------------
//

void initArray(NodeData* ndArray[]) {
    //loop through array
	for (int i = 0; i < ARRAYSIZE; i++)
        //set all values to NULL
		ndArray[i] = NULL;
}
