// ----------------------------- polynomial.h ----------------------------------

// Anneliese Pessoa CSS 343 A

// April 1, 2016

// April 6, 2016

// -----------------------------------------------------------------------------

// Purpose - This file provides function definitions neccessary to create
//           polynomials in the form of a circular doubly linked list. Provides
//           functions that allow the user to create new polynomials, add new
//           terms, remove terms, find the degree of the polynomial, get
//           coefficients of specific terms, add and subtract polynomials,
//           compare polynomials, and output them onto the console.

// -----------------------------------------------------------------------------

//  This ADT functions with a dummy head node at the start of the linked list.
//  In order to add a term, the user must use the function, changeCoefficient().
//  To remove a term, the user must change the coefficient of the term to 0.

// -----------------------------------------------------------------------------

// A Polynomial class
#include <iostream>
#include <string>
using namespace std;

class Polynomial {
    
    // ---------------------------- operator <<() ------------------------------
    
    // Description
    
    // operator <<():   Allows polynomial terms to be output onto the console.
    // Preconditions:   This function assumes that the terms in the list have
    //                  been sorted in the correct order.
    // Postconditions:  The polynomial is output onto the console with the form,
    //                  cnx^n + cn-1x^(n-1) + ... + c1x + c0.
    
    // -------------------------------------------------------------------------
    // Overloaded <<: prints Cn * x^n + Cn-1 * X^n-1 + ... C1 * X + C0
    friend ostream& operator<<( ostream &output, const Polynomial& p );
    
    // Constructor: the default is a 0-degree polynomial with 0.0 coefficient
public:
    
    // Member functions
    // ------------------------------------Polynomial()-------------------------
    
    // Description
    
    // Polynomial():    Default polynomial constructor.
    // Preconditions:   None
    // Postconditions:  A polynomial is created with one dummy node as a head.
    
    // -------------------------------------------------------------------------
    Polynomial( );
    
    // ------------------------------------Polynomial()-------------------------
    
    // Description
    
    // Polynomial(const Polynomial& p): Polynomial copy constructor
    // Preconditions:   None
    // Postconditions:  All values in Polynomial p are copied into this
    //                  polynomial.
    
    // -------------------------------------------------------------------------
    Polynomial( const Polynomial& p );
    
    // ---------------------------------- ~Polynomial() ------------------------
    
    // Description
    
    // ~Polynomial():   Polynomial destructor
    // Preconditions:   None
    // Postconditions:  Deallocates memory used to create the polynomial
    
    // -------------------------------------------------------------------------
    ~Polynomial( );
    
    // ---------------------------------- degree() -----------------------------
    
    // Description
    
    // degree():        Returns the degree of the polynomial.
    // Preconditions:   The polynomial must have valid terms in order to return
    //                  a value that is not 0.
    // Postconditions:  An integer is returned representing the highest power in
    //                  the polynomial
    
    // -------------------------------------------------------------------------
    int degree( ) const; // returns the degree of a polynomial
    
    // ---------------------------- coefficient () -----------------------------
    
    // Description
    
    // coefficient():   Returns the coefficient of the x^power term.
    // Preconditions:   Requires a term with the power to be present in the
    //                  polynomial; otherwise, the returned coefficient will be
    //                  0.
    // Postconditions:  A double is returned representing the coefficient of the
    //                  x^power term. If no term exists that has the parameter
    //                  power 0 is returned.
    
    // -------------------------------------------------------------------------
    double coefficient( const int power ) const;
    
    // ------------------------- changeCoefficient () --------------------------
    
    // Description
    
    // changeCoefficient():   Changes the coefficient of the term with the input
    //                        power to the new input coefficient.
    // Preconditions:   Requires positive power inputs.
    // Postconditions:  If a term with the input power does not exist, a new
    //                  term is added to the list. If the term with the input
    //                  power does exist, only the coefficient of that term is
    //                  altered. Error message will be displayed if power is
    //                  negative. If 0 is passed in as newCoefficient, the term
    //                  with power is deleted but only if it exists within the
    //                  polynomial.
    
    // -------------------------------------------------------------------------
    // returns the coefficient of the x^power term.
    bool changeCoefficient( const double newCoefficient, const int power );
    // replaces the coefficient of the x^power term
    
    // Arithmetic operators
    // ---------------------------- operator + () ------------------------------
    
    // Description
    
    // operator +():    Add p from the this polynomial.
    // Preconditions:   None
    // Postconditions:  The polynomail p is added from this polynomial and a
    //                  new polynomial is returned with the newly added
    //                  polynomial
    
    // -------------------------------------------------------------------------
    Polynomial operator+( const Polynomial& p ) const;
    
    // ---------------------------- operator - () ------------------------------
    
    // Description
    
    // operator -():    Subtract p from the this polynomial.
    // Preconditions:   None
    // Postconditions:  The polynomail p is subtracted from this polynomial and
    //                  a new polynomial is returned with the newly subtracted
    //                  polynomial
    
    // -------------------------------------------------------------------------
    Polynomial operator-( const Polynomial& p ) const;
    
    // Boolean comparison operators
    // ---------------------------- operator == () -----------------------------
    
    // Description
    
    // operator ==():   Determines if two polynomials are the same.
    // Preconditions:   None
    // Postconditions:  A boolean is returned representing if the two
    //                  polynomials are the same.
    
    // -------------------------------------------------------------------------
    bool operator==( const Polynomial& p ) const;
    
    // ---------------------------- operator != () -----------------------------
    
    // Description
    
    // operator !=():   Determines if two polynomials are the not the same.
    // Preconditions:   Requires operator == to be working.
    // Postconditions:  A boolean is returned representing if the two
    //                  polynomials are not the same.
    
    // -------------------------------------------------------------------------
    bool operator!=( const Polynomial& p ) const;
    
    // Assignment operators
    // ---------------------------- operator =() -------------------------------
    
    // Description
    
    // operator =():    Sets this polynomial to equal polynomial p.
    // Preconditions:   Requires working operator ==.
    // Postconditions:  The left hand side of the operand is deallocated and
    //                  replaced by the values in p.
    
    // -------------------------------------------------------------------------
    Polynomial& operator=( const Polynomial& p );
    
    // ---------------------------- operator += () -----------------------------
    
    // Description
    
    // operator +=():   Adds the polynomial from this polynomial.
    // Preconditions:   Requires working operator =.
    // Postconditions:  Polynomial p is added to this polynomial and this
    //                  polynomial is returned.
    
    // -------------------------------------------------------------------------
    Polynomial& operator+=( const Polynomial& p );
    
    // ---------------------------- operator -= () -----------------------------
    
    // Description
    
    // operator -=():   Subtracts the polynomial from this polynomial.
    // Preconditions:   Requires working operator =.
    // Postconditions:  Polynomial p is subtracted from this polynomial and this
    //                  polynomial is returned.
    
    // -------------------------------------------------------------------------
    Polynomial& operator-=( const Polynomial& p );
    
private:
    struct Term {     // a term on the sparce polynomial
        double coeff;   // the coefficient of each term
        int power;      // the degree of each term
        Term *prev;     // a pointer to the previous higher term
        Term *next;     // a pointer to the next lower term
    };
    int size;         // # terms in the sparce polynomial
    Term *head;       // a pointer to the doubly-linked circular list of
    // sparce polynomial
    
    // ---------------------------- hasPower () --------------------------------
    
    // Description
    
    // hasPower():      Determines whether or not the power given exists within
    //                  the list.
    // Preconditions:   None
    // Postconditions:  A boolean value is returned stating whether or not the
    //                  power exists within the list.
    
    // -------------------------------------------------------------------------
    //added function
    bool hasPower(int power)const;
    
    // ------------------------------ insert () --------------------------------
    
    // Description
    
    // insert():        Inserts a new Term node with newCoefficient and power
    //                  just before the existing Term pointed by the pos pointer
    // Preconditions:   The passed Term, next, is the correct Term to be placed
    //                  after the newly inserted Term.
    // Postconditions:  A new node is inserted into the list in the correct
    //                  position.
    
    // -------------------------------------------------------------------------
    bool insert( Term* next, const double newCoefficient, const int power );
    
    // ------------------------------ remove () --------------------------------
    
    // Description
    
    // remove():        Removes the term pos from the list of terms.
    // Preconditions:   The term cannot be NULL.
    // Postconditions:  The term is deallocated from memory.
    
    // -------------------------------------------------------------------------
    bool remove( Term* &pos );
};


