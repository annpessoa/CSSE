// --------------------------- bintree.h ---------------------------------------

// Anneliese Pessoa CSS 343 A

// April 10, 2016

// April 21, 2016

// -----------------------------------------------------------------------------

// Purpose - This file provides definitions neccessary to create Binary Search
//           Trees. Provides functions that allow the user to create new BSTs,
//           add new Nodes, retrieve nodes fromt the Tree, compare
//           BSTs,  get the height of a given Node, convert from tree->array and
//           array->tree, and output the tree onto the console.

// -----------------------------------------------------------------------------

#ifndef bintree_h
#define bintree_h

#include <stdio.h>
#include <fstream>
#include "nodedata.h"
class BinTree {				// you add class/method comments and assumptions
    friend ostream & operator<<(ostream &, const BinTree &bt);
public:
    //------------------------- constructors/destructor ------------------------
    
    //------------------------- BinTree() --------------------------------------
    // Description
    // BinTree():       Default BinTree constructor.
    // Preconditions:   None
    // Postconditions:  An empty Binary Search Tree is created.
    //--------------------------------------------------------------------------
    BinTree();
    
    //------------------------- BinTree(BinTree &) -----------------------------
    // Description
    // BinTree(BinTree &):  BinTree copy constructor.
    // Preconditions:       None
    // Postconditions:      A Binary Search Tree is created as a deep copy of
    //                      the passed in BinTree.
    //--------------------------------------------------------------------------
    BinTree(const BinTree & bstToCopy);
    
    //------------------------- ~BinTree() -------------------------------------
    // Description
    // ~BinTree():      BinTree deconstructor.
    // Preconditions:   None
    // Postconditions:  Deallocates all memory used by the Binary Search Tree.
    //--------------------------------------------------------------------------
    ~BinTree();
    
    
    
    //------------------------- isEmpty/makeEmpty ------------------------------
    
    //------------------------- isEmpty() --------------------------------------
    // Description
    // isEmpty():       Determines if the BST is empty.
    // Preconditions:   None
    // Postconditions:  Returns a bool stating if the BST is empty.
    //--------------------------------------------------------------------------
    bool isEmpty() const;
    
    //------------------------- makeEmpty() ------------------------------------
    // Description
    // makeEmpty():     Empties the Binary Search Tree.
    // Preconditions:   None
    // Postconditions:  Deallocates all memory used by the Binary Search Tree.
    //--------------------------------------------------------------------------
    void makeEmpty();
    
    
    //------------------------- overloading operators --------------------------
    
    //------------------------- operator<< () ----------------------------------
    // Description
    // operator<<():        Overloads operator << to print values from tree.
    // Preconditions:       None
    // Postconditions:      Output is returned with all values of the tree in
    //                      inorder order.
    //--------------------------------------------------------------------------
    friend ostream& operator<<( ostream &output, const BinTree& p );
    
    //------------------------- operator= () -----------------------------------
    // Description
    // operator=():         Overloads operator =.
    // Preconditions:       None
    // Postconditions:      This trees values are changed to be the values in
    //                      BinTree b.
    //--------------------------------------------------------------------------
    BinTree& operator=(const BinTree &);
    
    //------------------------- operator== () ----------------------------------
    // Description
    // operator==():        Overloads operator == to test BinTree equality.
    // Preconditions:       None
    // Postconditions:      A boolean is returned representing whether or not the
    //                      two trees are equal.
    //--------------------------------------------------------------------------
    bool operator==(const BinTree &) const;
    
    //------------------------- operator!= () ----------------------------------
    // Description
    // operator!=():        Overloads operator != to test BinTree inequality.
    // Preconditions:       None
    // Postconditions:      A boolean is returned representing whether or not the
    //                      two trees are not equal.
    //--------------------------------------------------------------------------
    bool operator!=(const BinTree &) const;
    
    
    //------------------------- insert/retrieve --------------------------------
    
    //------------------------- insert () ------------------------------------------
    // Description
    // insert():        Inserts a new node into the binary search tree in inorder
    //                  form.
    // Preconditions:   The NodeData * nd points to a NodeData object that has been
    //                  instantiated properly.
    // Postconditions:  If the node does not currently exist in the tree, it is
    //                  added in the correct position. If the node already exists,
    //                  no new node is added to the tree.
    //--------------------------------------------------------------------------
    bool insert(NodeData* newChild);
    
    //------------------------- retrieve () ------------------------------------
    // Description
    // retrieve():      Searches for the target NodeData in the BST and returns a
    //                  a pointer to its location in the tree.
    // Preconditions:   None
    // Postconditions:  If the node is not found or the tree is empty, no pointer to
    //                  a location in the tree is returned and the function returns
    //                  false. If the node is found, the location of the node is
    //                  stored in the node pointer, targetLoc and the function
    //                  returns true.
    //--------------------------------------------------------------------------
    bool retrieve(const NodeData & target, NodeData* & targetLoc) const;
    
    //------------------------- additional public functions --------------------
    
    //------------------------- displaySideways() ------------------------------
    // Description
    // displaySideways():   Displays a binary tree as though you are viewing it from
    //                      the side; hard coded displaying to standard output.
    // Preconditions:       None.
    // Postconditions:      BinTree remains unchanged.
    //--------------------------------------------------------------------------
    void displaySideways() const;
    
    //------------------------- getHeight () -----------------------------------
    // Description
    // getHeight():     Takes a NodeData object and searches for it in the tree to
    //                  find its height in the tree relative to the leaves.
    // Preconditions:   None
    // Postconditions:  An int is returned that represents the height of the node
    //                  which begins at the leaves and increments with each parent
    //                  node.
    //--------------------------------------------------------------------------
    int getHeight (const NodeData &) const;
    
    //------------------------- bstreeToArray () -------------------------------
    // Description
    // bstreeToArray(): Converts the current binary search tree to an array.
    // Preconditions:   The current number of nodes cannot exceed 100.
    // Postconditions:  The array will contain the inorder order of nodes in the
    //                  tree. The tree will be empty after execution.
    //--------------------------------------------------------------------------
    void bstreeToArray(NodeData* arr[]);
    
    //------------------------- arrayToBSTree () -------------------------------
    // Description
    // arrayToBSTree(): Converts the current array into a binary search tree.
    // Preconditions:   The current number of nodes cannot exceed 100.
    // Postconditions:  A balanced tree is created that contains inorder nodes
    //                  from  array.
    //--------------------------------------------------------------------------
    void arrayToBSTree(NodeData* arr[]);
    
    
    
    
private:
    struct Node {
        NodeData* data;						// pointer to data object
        Node* left;							// left subtree pointer
        Node* right;						// right subtree pointer
    };
    Node* root;								// root of the tree
    
    //------------------------- utility functions ------------------------------
    
    //------------------------- inorderHelper () -------------------------------
    // Description
    // inorderHelper():     Helper function for operator<<().
    // Preconditions:       None
    // Postconditions:      The ostrem output will contain all values in the
    //                      tree in inorder order.
    //--------------------------------------------------------------------------
    void inorderHelper(Node * parent, ostream& output) const;
    
    //------------------------- sideways() -------------------------------------
    // Description
    // sideways():      Helper method for displaySideways
    // Preconditions:   None.
    // Postconditions:  BinTree remains unchanged.
    //--------------------------------------------------------------------------
    void sideways(Node*, int) const;
    
    
    //------------------------- added helper functions -------------------------
    
    //------------------------- copyTree() -------------------------------------
    // Description
    // copyTree():          Helper function for copy constructor and = operator.
    // Preconditions:       This tree must be empty before calling this method.
    // Postconditions:      After execution, this tree becomes a deep copy of
    //                      the tree to be copied.
    //--------------------------------------------------------------------------
    void copyTree(Node * & thisTree, Node * treeToCopy)const;
    
    //------------------------- clearTree() ------------------------------------
    // Description
    // clearTree():     Recursive helper function to delete all nodes in the BST
    // Preconditions:   None
    // Postconditions:  Deallocates all memory used by the Binary Search Tree.
    //--------------------------------------------------------------------------
    void clearTree(Node * parent);
    
    //------------------------- comparisonHelper() -----------------------------
    // Description
    // comparisonHelper():  Helper funciton for operator ==.
    // Preconditions:       None
    // Postconditions:      A boolean is returned representing whether or not the
    //                      two trees are equal.
    //--------------------------------------------------------------------------
    bool comparisonHelper(Node* thisTree, Node* otherTree)const;
    
    //------------------------- insertHelper () --------------------------------
    // Description
    // insertHelper():  Inserts a new node into the binary search tree in inorder
    //                  form.
    // Preconditions:   None
    // Postconditions:  If the node does not currently exist in the tree, it is
    //                  added in the correct position. If the node already exists,
    //                  no new node is added to the tree.
    //--------------------------------------------------------------------------
    bool insertHelper(Node * parent, NodeData * newChild);
    
    //------------------------- retrieveHelper () ------------------------------
    // Description
    // retrieveHelper():    Helper function for retrieve().
    // Preconditions:       None
    // Postconditions:      If the node is not found or the tree is empty, no
    //                      pointer to a location in the tree is returned and the
    //                      function returns false. If the node is found, the
    //                      location of the node is stored in the node pointer,
    //                      targetLoc and the function returns true.
    //--------------------------------------------------------------------------
    bool retrieveHelper(Node * parent, const NodeData & target, NodeData* & targetLoc)const;
    
    //------------------------- getHeightHelper () -----------------------------
    // Description
    // getHeightHelper():   Helper function for getHeight. Searches for the given
    //                      node in the tree and then calculates the height of it.
    // Preconditions:       None
    // Postconditions:      An int is returned that represents the height of the
    //                      node which begins at the leaves and increments with each
    //                      parent node.
    //--------------------------------------------------------------------------
    int getHeightHelper(const NodeData & n, Node* subTree) const;
    
    //------------------------- getSubtreeHeight () ----------------------------
    // Description
    // getSubtreeHeight():  Helper function for getHeightHelper. Performs most of
    //                      the height calculation.
    // Preconditions:       The node has been found properly and has parent nodes.
    // Postconditions:      An integer is returned representing the height of the
    //                      given subtree.
    //--------------------------------------------------------------------------
    int getSubtreeHeight(Node* subtree)const;
    
    //------------------------- inorderArrayHelper () --------------------------
    // Description
    // inorderArrayHelper():    Helper function for bstreeToArray function.
    // Preconditions:           The current number of nodes cannot exceed 100.
    // Postconditions:          An inorder traversal is performed on the tree and
    //                          all nodes in the tree are added to the array in
    //                          inorder
    //--------------------------------------------------------------------------
    void inorderArrayHelper(NodeData* arr[], Node * currentNode, int &i);
    
    //------------------------- arrayToBSTreeHelper () -------------------------
    // Description
    // arrayToBSTreeHelper (): Converts the current binary search tree to an array.
    // Preconditions:          The current number of nodes cannot exceed 100.
    // Postconditions:         A balanced tree is created that contains inorder
    //                          nodes from array.
    //--------------------------------------------------------------------------
    void arrayToBSTreeHelper(int low, int high, Node * currentNode, NodeData*arr[]);
};
#endif /* bintree_h */
