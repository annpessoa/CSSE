// --------------------------- bintree.cpp -------------------------------------

// Anneliese Pessoa CSS 343 A

// April 10, 2016

// April 21, 2016

// -----------------------------------------------------------------------------

// Purpose - This file provides implementation neccessary to create Binary Search
//           Trees. Provides functions that allow the user to create new BSTs,
//           add new Nodes, retrieve nodes fromt the Tree, compare
//           BSTs,  get the height of a given Node, convert from tree->array and
//           array->tree, and output the tree onto the console.

// -----------------------------------------------------------------------------

#include "bintree.h"

//------------------------- constructors/destructor ----------------------------

//------------------------- BinTree() ------------------------------------------
// Description
// BinTree():       Default BinTree constructor.
// Preconditions:   None
// Postconditions:  An empty Binary Search Tree is created.
//------------------------------------------------------------------------------
BinTree::BinTree(){
    //create empty tree
    root = NULL;
}

//------------------------- BinTree(BinTree &) ---------------------------------
// Description
// BinTree(BinTree &):  BinTree copy constructor.
// Preconditions:       None
// Postconditions:      A Binary Search Tree is created as a deep copy of
//                      the passed in BinTree.
//------------------------------------------------------------------------------
BinTree::BinTree(const BinTree & bstToCopy){
    //deep copy of BinTree b
    if(bstToCopy.root == NULL){
        root = NULL;
    }else{
        copyTree(root, bstToCopy.root);
    }
}

//------------------------- ~BinTree() -----------------------------------------
// Description
// ~BinTree():      BinTree deconstructor.
// Preconditions:   None
// Postconditions:  Deallocates all memory used by the Binary Search Tree.
//------------------------------------------------------------------------------
BinTree::~BinTree(){
    //deallocate all nodes in BST if the tree is not empty
    if(root != NULL){
        makeEmpty();
    }
}

//------------------------- copyTree() -----------------------------------------
// Description
// copyTree():          Helper function for copy constructor and = operator.
// Preconditions:       This tree must be empty before calling this method.
// Postconditions:      After execution, this tree becomes a deep copy of the
//                      tree to be copied.
//------------------------------------------------------------------------------
void BinTree::copyTree(Node * & thisTree, Node * treeToCopy )const{
    //if the tree that is being copied is empty, make this tree NULL
    if(treeToCopy == NULL){
        thisTree = NULL;
    }else{
        //make a new node
        thisTree = new Node;
        //copy data onto the new node
        thisTree->data = new NodeData(*treeToCopy->data);
        //copy left side of tree
        copyTree(thisTree->left,treeToCopy->left);
        //copy right side of tree
        copyTree(thisTree->right,treeToCopy->right);
    }
}

//------------------------- clearTree() ----------------------------------------
// Description
// clearTree():     Recursive helper function to delete all nodes in the BST.
// Preconditions:   None
// Postconditions:  Deallocates all memory used by the Binary Search Tree.
//------------------------------------------------------------------------------
void BinTree::clearTree(Node * parent){
    //if there are still children to delete
    if(parent != NULL){
        //recursive call to clear left children
        clearTree(parent->left);
        //recursive call to clear right children
        clearTree(parent->right);
        //delete NodeData
        delete parent->data;
        parent->data = NULL;
        //delete Node
        delete parent;
        parent = NULL;
    }
}

//------------------------- isEmpty/makeEmpty ----------------------------------

//------------------------- isEmpty() ------------------------------------------
// Description
// isEmpty():       Determines if the BST is empty.
// Preconditions:   None
// Postconditions:  Returns a bool stating if the BST is empty.
//------------------------------------------------------------------------------
bool BinTree::isEmpty()const{
    return root == NULL;
}


//------------------------- makeEmpty() ----------------------------------------
// Description
// makeEmpty():     Empties the Binary Search Tree.
// Preconditions:   None
// Postconditions:  Deallocates all memory used by the Binary Search Tree.
//------------------------------------------------------------------------------
void BinTree::makeEmpty(){
    //call clearTree and clear from the root of the tree
    clearTree(root);
    root = NULL;
}

//------------------------- overloaded operators -------------------------------

//------------------------- operator<< () --------------------------------------
// Description
// operator<<():        Overloads operator << to print values from tree.
// Preconditions:       None
// Postconditions:      Output is returned with all values of the tree in
//                      inorder order.
//------------------------------------------------------------------------------
ostream& operator<<(ostream& output, const BinTree& b) {
    b.inorderHelper(b.root, output);
    return output << endl;;
}

//------------------------- inorderHelper () -----------------------------------
// Description
// inorderHelper():     Helper function for operator<<().
// Preconditions:       None
// Postconditions:      The ostrem output will contain all values in the tree
//                      in inorder order.
//------------------------------------------------------------------------------
void BinTree::inorderHelper(Node * parent, ostream& output) const{
    //if there are children of Node parent, search through tree inorder
    if(parent != NULL){
        //look at left side of tree
        if(parent->left){
            inorderHelper(parent->left, output);
        }
        
        //add parent's data to ouput
        output << " " << *parent->data << " ";
        
        //look at right side of tree
        if(parent->right){
            inorderHelper(parent->right, output);
        }
    }
}

//------------------------- operator= () ---------------------------------------
// Description
// operator=():         Overloads operator =.
// Preconditions:       None
// Postconditions:      This trees values are changed to be the values in
//                      BinTree b.
//------------------------------------------------------------------------------

BinTree & BinTree::operator=(const BinTree & b){
    //ensure no self assignment
    if(*this != b){
        //empty this tree
        makeEmpty();
        //copy right hand of the = into this tree
        copyTree(root, b.root);
    }
    //return this binary search tree
    return *this;
    
}

//------------------------- operator==,!= --------------------------------------

//------------------------- operator== () --------------------------------------
// Description
// operator==():        Overloads operator == to test BinTree equality.
// Preconditions:       None
// Postconditions:      A boolean is returned representing whether or not the
//                      two trees are equal.
//------------------------------------------------------------------------------
bool BinTree::operator==(const BinTree &b) const{
    //if either tree is not empty, continue testing for equality
    return comparisonHelper(root,b.root);
}

//------------------------- operator!= () --------------------------------------
// Description
// operator!=():        Overloads operator != to test BinTree inequality.
// Preconditions:       None
// Postconditions:      A boolean is returned representing whether or not the
//                      two trees are not equal.
//------------------------------------------------------------------------------
bool BinTree::operator!=(const BinTree &b) const{
    return !(*this == b);
}

//------------------------- comparisonHelper() ---------------------------------
// Description
// comparisonHelper():  Helper funciton for operator ==.
// Preconditions:       None
// Postconditions:      A boolean is returned representing whether or not the
//                      two trees are equal.
//------------------------------------------------------------------------------
bool BinTree::comparisonHelper(Node* thisTree, Node* otherTree)const{
    //if both trees are empty, return true
    if(thisTree == NULL && otherTree == NULL) return true;
    //if either node is null, return false
    if(thisTree == NULL || otherTree == NULL) return false;
    //if the two nodes both have values, check if the values are the same
    if(thisTree != NULL && otherTree != NULL){
        //test if the two nodes are equal
        if(*thisTree->data == *otherTree->data){
            if(thisTree->left == NULL && otherTree->left == NULL){
                if(thisTree->right == NULL && otherTree->right == NULL){
                    return true;
                }
            }
        }
    }
    //recursive call to continue checking other children in the tree
    return *thisTree->data == *otherTree->data && comparisonHelper(thisTree->left, otherTree->left)
    && comparisonHelper(thisTree->right, otherTree->right);
}

//------------------------- insert functions -----------------------------------

//------------------------- insert () ------------------------------------------
// Description
// insert():        Inserts a new node into the binary search tree in inorder
//                  form.
// Preconditions:   The NodeData * nd points to a NodeData object that has been
//                  instantiated properly.
// Postconditions:  If the node does not currently exist in the tree, it is
//                  added in the correct position. If the node already exists,
//                  no new node is added to the tree.
//------------------------------------------------------------------------------
bool BinTree::insert(NodeData* newChild){
    //add node to empty tree
    if(isEmpty()){
        //make a new node
        root = new Node;
        //set data
        root->data = newChild;
        //set children to NULL
        root->left = root->right = NULL;
        return true; //successful insert
    }
    
    //if the tree has been populated by at least 1 node, call helper function
    return insertHelper(root, newChild);
    
}

//------------------------- insertHelper () ------------------------------------
// Description
// insertHelper():        Inserts a new node into the binary search tree in inorder
//                  form.
// Preconditions:   None
// Postconditions:  If the node does not currently exist in the tree, it is
//                  added in the correct position. If the node already exists,
//                  no new node is added to the tree.
//------------------------------------------------------------------------------
bool BinTree::insertHelper(Node * parent, NodeData * newChild){
    //case to terminate function if newChild already exists in the tree
    if(newChild == parent->data) return false;
    
    //test if new node is less than parent
    else if(*newChild < *parent->data){
        //if the left child is NULL, insert node left of parent
        if(parent->left == NULL){
            //make new node
            parent->left = new Node;
            //set data of the new child
            parent->left->data = newChild;
            //set children of new child to NULL
            parent->left->left = parent->left->right = NULL;
            //statements for debugging
            //cout << "added left" <<endl;
            return true; //successful insert
        }else{
            //if there are more nodes in the tree, change parent to left child
            return insertHelper(parent->left, newChild);
        }
        
        //test if new node is greater than parent
    } else if(*newChild > *parent->data){
        //if the right child is NULL, insert node right of parent
        if(parent->right == NULL){
            //make new node
            parent->right = new Node;
            //set data of new child
            parent->right->data = newChild;
            //set children of new child to NULL
            parent->right->left = parent->right->right = NULL;
            //statements for debugging
            //cout << "added right" <<endl;
            return true; //successful insert
        }else{
            //if there are more nodes in the tree, change parent to right child
            return insertHelper(parent->right, newChild);
        }
    }
    //failed insert
    return false;
}


//------------------------- retrieve -------------------------------------------

//------------------------- retrieve () ----------------------------------------
// Description
// retrieve():      Searches for the target NodeData in the BST and returns a
//                  a pointer to its location in the tree.
// Preconditions:   None
// Postconditions:  If the node is not found or the tree is empty, no pointer to
//                  a location in the tree is returned and the function returns
//                  false. If the node is found, the location of the node is
//                  stored in the node pointer, targetLoc and the function
//                  returns true.
//------------------------------------------------------------------------------
bool BinTree::retrieve(const NodeData & target, NodeData* & targetLoc) const{
    return retrieveHelper(root, target, targetLoc);
}

//------------------------- retrieveHelper () ----------------------------------
// Description
// retrieveHelper():    Helper function for retrieve().
// Preconditions:       None
// Postconditions:      If the node is not found or the tree is empty, no
//                      pointer to a location in the tree is returned and the
//                      function returns false. If the node is found, the
//                      location of the node is stored in the node pointer,
//                      targetLoc and the function returns true.
//------------------------------------------------------------------------------
bool BinTree::retrieveHelper(Node * parent, const NodeData & target, NodeData* & targetLoc)const{
    //base case, node is not found/empty tree
    if(parent == NULL) return false;
    //if the target is found
    else if(*parent->data == target){
        //set targetLoc pointer
        targetLoc = parent->data;
        //successful retrieval
        return true;
    }else if(target <= *parent->data){
        //recursive call to search the left side of tree
        return retrieveHelper(parent->left, target, targetLoc);
    } else{
        //recursive call to search the right side of tree
        return retrieveHelper(parent->right, target, targetLoc);
    }
}

//------------------------- displaySideways ------------------------------------

//------------------------- displaySideways() ----------------------------------
// Description
// displaySideways():   Displays a binary tree as though you are viewing it from
//                      the side; hard coded displaying to standard output.
// Preconditions:       None.
// Postconditions:      BinTree remains unchanged.
//------------------------------------------------------------------------------
void BinTree::displaySideways() const {
    sideways(root, 0);
}

//------------------------- sideways() -----------------------------------------
// Description
// sideways():      Helper method for displaySideways
// Preconditions:   None.
// Postconditions:  BinTree remains unchanged.
//------------------------------------------------------------------------------
void BinTree::sideways(Node* current, int level) const {
    if (current != NULL) {
        level++;
        sideways(current->right, level);
        
        // indent for readability, 4 spaces per depth level
        for (int i = level; i >= 0; i--) {
            cout << "    ";
        }
        
        cout << *current->data << endl;        // display information of object
        sideways(current->left, level);
    }
}

//------------------------- getHeight ------------------------------------------

//------------------------- getHeight () ---------------------------------------
// Description
// getHeight():     Takes a NodeData object and searches for it in the tree to
//                  find its height in the tree relative to the leaves.
// Preconditions:   None
// Postconditions:  An int is returned that represents the height of the node
//                  which begins at the leaves and increments with each parent
//                  node.
//------------------------------------------------------------------------------
int BinTree::getHeight (const NodeData & n) const{
    //if the tree is empty, return 0, no possible node to get height
    if(isEmpty())return 0;
    
    //make temp node for location of NodeData n
    NodeData* temp;
    //node does not exist in the tree, no possible node to get height
    if(!retrieve(n, temp))return 0;
    
    //node exists in the tree, call helper function to find height
    return getHeightHelper(n, root);
}

//------------------------- getHeightHelper () ---------------------------------
// Description
// getHeightHelper():   Helper function for getHeight. Searches for the given
//                      node in the tree and then calculates the height of it.
// Preconditions:       None
// Postconditions:      An int is returned that represents the height of the
//                      node which begins at the leaves and increments with each
//                      parent node.
//------------------------------------------------------------------------------
int BinTree:: getHeightHelper(const NodeData & n, Node* subTree) const{
    //if the subTree is null, return 0 because there are no nodes to work with
    if(subTree == NULL) return 0;
    //if the method reaches the NodeData in the tree, get the height of that
    //subtree by calling getSubtreeHeight
    else if(n == *subTree->data){
        return getSubtreeHeight(subTree);
    }else {
        //search through left children of subTree
        int leftChild = getHeightHelper(n, subTree->left);
        //search through right children of subTree
        int rightChild = getHeightHelper(n, subTree->right);
        //return greatest height of the node
        return leftChild + rightChild;
    }
    return 0;
}

//------------------------- getSubtreeHeight () --------------------------------
// Description
// getSubtreeHeight():  Helper function for getHeightHelper. Performs most of
//                      the height calculation.
// Preconditions:       The node has been found properly and has parent nodes.
// Postconditions:      An integer is returned representing the height of the
//                      given subtree.
//------------------------------------------------------------------------------
int BinTree::getSubtreeHeight(Node* subTree)const{
    //If the subtree is empty, there is no height
    if(subTree == NULL) return 0;
    
    //get max height from left side
    int leftChild = getSubtreeHeight(subTree->left);
    //getmax height from right side
    int rightChild = getSubtreeHeight(subTree->right);
    
    //return the max height of both left and right children of the subtree
    //add one to increment height for each iteration up from the leaves.
    return 1 + max(leftChild, rightChild);
}

//------------------------- BST to array ---------------------------------------

//------------------------- bstreeToArray () -----------------------------------
// Description
// bstreeToArray(): Converts the current binary search tree to an array.
// Preconditions:   The current number of nodes cannot exceed 100.
// Postconditions:  The array will contain the inorder order of nodes in the
//                  tree. The tree will be empty after execution.
//------------------------------------------------------------------------------
void BinTree::bstreeToArray(NodeData* arr[] ){
    //create an int to keep track of indexes in the array
    int i = 0;
    //add all values into the array in inorder fashion
    inorderArrayHelper(arr, root, i);
    //set root in tree to NULL;
    root = NULL;
}

//------------------------- inorderArrayHelper () ------------------------------
// Description
// inorderArrayHelper():    Helper function for bstreeToArray function.
// Preconditions:           The current number of nodes cannot exceed 100.
// Postconditions:          An inorder traversal is performed on the tree and
//                          all nodes in the tree are added to the array in
//                          inorder
//------------------------------------------------------------------------------
void BinTree::inorderArrayHelper(NodeData* arr[] , Node * currentNode, int & i){
    //only work with nodes that exist
    if(currentNode != NULL){
        //get left child
        inorderArrayHelper(arr,currentNode->left, i);
        //add data to the array
        arr[i] = currentNode->data;
        //increment index
        i++;
        //get right child
        inorderArrayHelper(arr, currentNode->right,i);
        //delete the node being worked with
        delete currentNode;
    }
}

//------------------------- array to BST ---------------------------------------

//------------------------- arrayToBSTree () -----------------------------------
// Description
// arrayToBSTree(): Converts the current array into a binary search tree.
// Preconditions:   The current number of nodes cannot exceed 100.
// Postconditions:  A balanced tree is created that contains inorder nodes
//                  from  array.
//------------------------------------------------------------------------------
void BinTree::arrayToBSTree(NodeData* arr[]){
    //deallocate this tree to make room for nodes in array
    makeEmpty();
    //get low index
    int low = 0;
    //get high index
    int high = 0;
    //loop through list
    for(int i = 0; i < 100; i++){
        //if there is an element present, increment highest index
        if(arr[i] != NULL) high ++;
        else arr[i] = NULL;
    }
    //call to helper to set all values within the tree
    arrayToBSTreeHelper(low, high-1, root, arr);
}

//------------------------- arrayToBSTreeHelper () -----------------------------
// Description
// arrayToBSTreeHelper (): Converts the current binary search tree to an array.
// Preconditions:          The current number of nodes cannot exceed 100.
// Postconditions:         A balanced tree is created that contains inorder
//                          nodes from array.
//------------------------------------------------------------------------------
void BinTree::arrayToBSTreeHelper(int low, int high, Node * currentNode, NodeData*arr[]){
    //if the low index is higher than the high index, set currentNode to NULL
    if(low > high) currentNode = NULL;
    //evaluate list of elements and add to tree
    else{
        //get middle location, which is the index of the current root
        int currentRoot = (low + high) / 2;
        //insert the new node into the tree
        insert(arr[currentRoot]);
        //recursive call to look at left half of data
        arrayToBSTreeHelper(low, currentRoot-1, currentNode, arr);
        //recursive call to look at right half of data
        arrayToBSTreeHelper(currentRoot + 1, high, currentNode, arr);
        //set position in array to NULL
        arr[currentRoot] = NULL;

    }
}
