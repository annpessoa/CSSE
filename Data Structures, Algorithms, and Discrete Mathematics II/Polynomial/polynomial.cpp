// --------------------------- polynomial.cpp ----------------------------------

// Anneliese Pessoa CSS 343 A

// April 1, 2016

// April 6, 2016

// -----------------------------------------------------------------------------

// Purpose - This file provides implementation neccessary to create polynomials
//           in the form of a circular doubly linked list. Provides functions
//           that allow the user to create new polynomials, add new terms,
//           remove terms, find the degree of the polynomial, get coefficients
//           of specific terms, add and subtract polynomials, compare
//           polynomials, and output them onto the console.

// -----------------------------------------------------------------------------

//  This ADT functions with a dummy head node at the start of the linked list.
//  In order to add a term, the user must use the function, changeCoefficient().
//  To remove a term, the user must change the coefficient of the term to 0.

// -----------------------------------------------------------------------------

#include <stdio.h>
#include "polynomial.h"

// ---------------------------- operator <<() ----------------------------------

// Description

// operator <<():   Allows polynomial terms to be output onto the console.
// Preconditions:   This function assumes that the terms in the list have been
//                  sorted in the correct order.
// Postconditions:  The polynomial is output onto the console with the form,
//                  cnx^n + cn-1x^(n-1) + ... + c1x + c0.

// -----------------------------------------------------------------------------
ostream & operator << ( ostream &output, const Polynomial& p ){
    //temp cur pointer to loop through list of polynomials
    Polynomial::Term *cur = p.head;
    //loop until the head is reached
    if(p.size > 0){
        cur=cur->next;
        while (cur->coeff != 0){
            if(cur->power == p.degree() && cur->coeff < 0){
                output << "-";
            }
            //case where the power of x is 1
            if(cur->power == 1){
                //case where the coefficient is greater than or less than 1
                if((int)cur->coeff > 1 || cur->coeff < 1){
                    if(cur->coeff >1){
                        output << cur->coeff << "x";
                    }else{
                        output << -cur->coeff << "x";
                    }
                } else{
                    //case if the coefficient is 1
                    output<< "x";
                }
                //output correct symbol for the next term in the polynomial
                if(cur->next->coeff > 0){
                    output << " + ";
                }else if(cur->next->coeff < 0){
                    output << " - ";
                }
            }
            //case where x has a power > 1
            else if(cur->power != 0){
                //case where the coefficient is greater than or less than 1
                if(cur->coeff > 1 || cur->coeff < 1){
                    if(cur->coeff>1){
                        output << cur->coeff << "x^" << cur->power;
                    }else{
                        output << -cur->coeff << "x^" << cur->power;
                    }
                }else{
                    //case if the coefficient is 1
                    output << "x^" << cur->power;
                }
                //output correct symbol for the next term in the polynomial
                if(cur->next->coeff > 0){
                    output << " + ";
                }else if(cur->next->coeff < 0){
                    output << " - ";
                }
            }else{ //case for terms without a power
                output << cur->coeff;
            }
            cur = cur->next;
        }
    }
    return output;
}

// ------------------------------------Polynomial()-----------------------------

// Description

// Polynomial():    Default polynomial constructor.
// Preconditions:   None
// Postconditions:  A polynomial is created with one dummy node as a head.

// -----------------------------------------------------------------------------
Polynomial::Polynomial(){
    //create dummy head node
    head = new Term;
    head->coeff = 0.0;
    head->power = 0;
    head->prev = head;
    head->next = head;
    //set size variable
    size = 0;
}

// ------------------------------------Polynomial()-----------------------------

// Description

// Polynomial(const Polynomial& p): Polynomial copy constructor
// Preconditions:   None
// Postconditions:  All values in Polynomial p are copied into this polynomial.

// -----------------------------------------------------------------------------
Polynomial::Polynomial(const Polynomial& p){
    //create dummy head node
    head = new Term;
    head->coeff = 0.0;
    head->power = 0;
    head->prev = head;
    head->next = head;
    //set size variable
    size = 0;
    //loop through p
    Term * cur = p.head->next;
    while(cur->coeff != 0){
        //add all values from terms in p to this polynomial
        changeCoefficient(cur->coeff, cur->power);
        cur = cur->next;
    }
}

// ---------------------------------- ~Polynomial() ----------------------------

// Description

// ~Polynomial():   Polynomial destructor
// Preconditions:   None
// Postconditions:  Deallocates memory used to create the polynomial

// -----------------------------------------------------------------------------
Polynomial::~Polynomial(){
    //temp term to loop through list
    Term *cur = head->next;
    //loop through list
    while(cur != head){
        //make a new term to hold the next term in the list
        Term * next = cur->next;
        //delete cur from the list
        remove(cur);
        cur = next;
    }
    //delete the dummy head in the list
    delete head;
    head = NULL;
}

// ---------------------------------- degree() ---------------------------------

// Description

// degree():        Returns the degree of the polynomial.
// Preconditions:   The polynomial must have valid terms in order to return a
//                  value that is not 0.
// Postconditions:  An integer is returned representing the highest power in the
//                  polynomial

// -----------------------------------------------------------------------------
int Polynomial::degree() const{
    //get the power of the next node after the dummy head node
    return head->next->power;
}

// ---------------------------- coefficient () ---------------------------------

// Description

// coefficient():   Returns the coefficient of the x^power term.
// Preconditions:   Requires a term with the power to be present in the
//                  polynomial; otherwise, the returned coefficient will be 0.
// Postconditions:  A double is returned representing the coefficient of the
//                  x^power term. If no term exists that has the parameter power
//                  0 is returned.

// -----------------------------------------------------------------------------
double Polynomial::coefficient(const int power) const{
    //cur pointer to loop through list of terms
    Term * cur = head->next;
    //initialize coefficient to 0
    double coefficient = 0;
    
    //loop through list to find the term with the parameter power
    while(cur->coeff != 0){
        //if the power in cur matches the parameter power
        if(cur->power == power){
            //set the returned coefficient value
            coefficient = cur->coeff;
        }
        //loop through list
        cur = cur->next;
    }
    return coefficient;
}

// ------------------------- changeCoefficient () ------------------------------

// Description

// changeCoefficient():   Changes the coefficient of the term with the input
//                        power to the new input coefficient.
// Preconditions:   Requires positive power inputs.
// Postconditions:  If a term with the input power does not exist, a new term is
//                  added to the list. If the term with the input power does
//                  exist, only the coefficient of that term is altered. Error
//                  message will be displayed if power is negative. If 0 is
//                  passed in as newCoefficient, the term with power is deleted
//                  but only if it exists within the polynomial.

// -----------------------------------------------------------------------------
bool Polynomial::changeCoefficient(const double newCoefficient, const int power){
    //error handling: prevent terms with negtive powers
    if(power < 0){
        cerr << "Invalid Input: Power cannot be negative." << endl;
        return false;
    }
    //delete terms with 0 as a coefficient
    if(newCoefficient == 0){
        if(!hasPower(power)){
            cerr << "Term does not exist within the Polynomial." << endl;
            return false;
        }
        Term * junk = head->next;
        while(junk->power != power){
            junk = junk->next;
        }
        remove(junk);
        return true;
    }
    //cur pointer to loop through list of terms
    Term * cur = head->next;
    //if the list is empty, make a new term
    if(size == 0){
        insert(head, newCoefficient, power);
        return true;
    }else if(hasPower(power)){
        //loop to find existing term with power
        while(cur->next->coeff != 0){
            if(cur->power == power){
                //change the coefficient of the existing term
                cur->coeff = newCoefficient;
                return true;
            }
            cur = cur->next;
        }
    }else{
        //if no existing power, make new term
        //loop until at position to insert new term in proper location
        while(cur->power > power){
            cur = cur->next;
        }
        //insert new term in correct position
        insert(cur,newCoefficient,power);
        return true;
    }
    return false;
}

// ---------------------------- hasPower () ------------------------------------

// Description

// hasPower():      Determines whether or not the power given exists within the
//                  list.
// Preconditions:   None
// Postconditions:  A boolean value is returned stating whether or not the power
//                  exists within the list.

// -----------------------------------------------------------------------------
bool Polynomial::hasPower(int power)const{
    //cur pointer to loop through list of terms
    Term * cur = head->next;
    //loop through list
    while(cur->coeff != 0){
        //if term with power exists, return true
        if(cur->power == power){
            return true;
        }
        cur = cur->next;
    }
    return false;
}

// ---------------------------- operator + () ----------------------------------

// Description

// operator +():    Add p from the this polynomial.
// Preconditions:   None
// Postconditions:  The polynomail p is added from this polynomial and a
//                  new polynomial is returned with the newly added
//                  polynomial

// -----------------------------------------------------------------------------
Polynomial Polynomial::operator+(const Polynomial& p) const{
    //make a new polynomial to return
    Polynomial newPoly;
    //temp cur term to loop through p
    Term *cur = p.head->next;
    //loop through p
    while(cur->coeff != 0){
        //if this polynomial contains a term with the current power
        if(hasPower(cur->power)){
            //add coefficient in p to this polynomial
            double coeffSum = coefficient(cur->power) + cur->coeff;
            newPoly.changeCoefficient(coeffSum, cur->power);
        }else{
            //add a new term to the polynomial
            newPoly.changeCoefficient(cur->coeff, cur->power);
        }
        cur=cur->next;
    }
    return newPoly;
}

// ---------------------------- operator - () ----------------------------------

// Description

// operator -():    Subtract p from the this polynomial.
// Preconditions:   None
// Postconditions:  The polynomail p is subtracted from this polynomial and a
//                  new polynomial is returned with the newly subtracted
//                  polynomial

// -----------------------------------------------------------------------------
Polynomial Polynomial::operator-(const Polynomial &p) const{
    //make a new polynomial to return
    Polynomial newPoly;
    //temp cur term to loop through p
    Term *cur = p.head->next;
    //loop through p
    while(cur->coeff != 0){
        //if this polynomial contains a term with the current power
        if(hasPower(cur->power)){
            //subtract coefficient in p from existing term
            double coeffDiff = coefficient(cur->power) - cur->coeff;
            newPoly.changeCoefficient(coeffDiff, cur->power);
        }else{
            //create a new negative term
            newPoly.changeCoefficient(-cur->coeff, cur->power);
        }
        cur=cur->next;
    }
    return newPoly;
}

// ---------------------------- operator == () ---------------------------------

// Description

// operator ==():   Determines if two polynomials are the same.
// Preconditions:   None
// Postconditions:  A boolean is returned representing if the two polynomials
//                  are the same.

// -----------------------------------------------------------------------------
bool Polynomial::operator==(const Polynomial &p) const{
    //if the two polynomials are not the same size, they cannot be the same.
    if(size != p.size)return false;
    //make temporary cur nodes for this polynomial and p
    Term *pcur = p.head->next;
    Term *cur = head->next;
    //loop through this polynomial
    while(cur->coeff !=0){
        //if the coefficients are not the same, return false
        if(cur->coeff != pcur->coeff)return false;
        //if the powers are not the same, return false
        if(cur->power != pcur->power)return false;
        //increment through both lists
        cur = cur->next;
        pcur = pcur->next;
    }
    return true;
}

// ---------------------------- operator != () ---------------------------------

// Description

// operator !=():   Determines if two polynomials are the not the same.
// Preconditions:   Requires operator == to be working.
// Postconditions:  A boolean is returned representing if the two polynomials
//                  are not the same.

// -----------------------------------------------------------------------------
bool Polynomial::operator!=(const Polynomial &p) const{
    return !(*this == p);
}

// ---------------------------- operator =() -----------------------------------

// Description

// operator =():    Sets this polynomial to equal polynomial p.
// Preconditions:   Requires working operator ==.
// Postconditions:  The left hand side of the operand is deallocated and
//                  replaced by the values in p.

// -----------------------------------------------------------------------------
Polynomial & Polynomial::operator=(const Polynomial &p){
    //if the polynomials are the same, there is no change to the polynomial.
    if(*this == p){
        cerr << "The two polynomials are the same" << endl;
    }else{
        //temp term to loop through list
        Term *cur = head->next;
        //loop through list
        while(cur != head){
            //make a new term to hold the next term in the list
            Term * next = cur->next;
            //delete cur from the list
            remove(cur);
            cur = next;
        }
        //make pcur to look through p's list
        Term *pcur = p.head->next;
        //loop through p
        while(pcur-> coeff != 0){
            //add each term from p to this polynomial
            changeCoefficient(pcur->coeff, pcur->power);
            pcur = pcur->next;
        }
    }
    return *this;
}

// ---------------------------- operator += () ---------------------------------

// Description

// operator +=():   Adds the polynomial from this polynomial.
// Preconditions:   Requires working operator =.
// Postconditions:  Polynomial p is added to this polynomial and this
//                  polynomial is returned.

// -----------------------------------------------------------------------------
Polynomial & Polynomial::operator+=(const Polynomial &p){
    *this = *this + p;
    return *this;
}

// ---------------------------- operator -= () ---------------------------------

// Description

// operator -=():   Subtracts the polynomial from this polynomial.
// Preconditions:   Requires working operator =.
// Postconditions:  Polynomial p is subtracted from this polynomial and this
//                  polynomial is returned.

// -----------------------------------------------------------------------------
Polynomial & Polynomial::operator-=(const Polynomial &p){
    *this = *this - p;
    return *this;
}

// ------------------------------ insert () ------------------------------------

// Description

// insert():        Inserts a new Term node with newCoefficient and power just
//                  before the existing Term pointed by the pos pointer.
// Preconditions:   The passed Term, next, is the correct Term to be placed
//                  after the newly inserted Term.
// Postconditions:  A new node is inserted into the list in the correct
//                  position.

// -----------------------------------------------------------------------------
bool Polynomial::insert(Term* next, const double newCoefficient, const int power){
    //make a new term
    Term *newTerm = new Term;
    //set coefficient
    newTerm->coeff = newCoefficient;
    //set power
    newTerm->power = power;
    //place the new term before pos
    //point newTerm's next pointer to pos
    newTerm->next = next;
    //point prev pointer to term that was originally before pos
    newTerm->prev = next->prev;
    //point prev in pos to newTerm
    next->prev = newTerm;
    //set term that was originally before pos to point to the new term
    newTerm->prev->next = newTerm;
    //increment size
    size++;
    return true;
}

// ------------------------------ remove () ------------------------------------

// Description

// remove():        Removes the term pos from the list of terms.
// Preconditions:   The term cannot be NULL.
// Postconditions:  The term is deallocated from memory.

// -----------------------------------------------------------------------------
bool Polynomial::remove(Term* &pos){
    //do not allow removal of node if it is NULL
    if(head == NULL || pos == NULL) return false;
    //case if pos is the head of the list
    if(head == pos){
        head = pos-> next;
    }
    //move term pointers away from the term pos
    if(pos->next != NULL){
        pos->next->prev = pos->prev;
    }
    if(pos->prev != NULL){
        pos->prev->next = pos->next;
    }
    //set the next and prev nodes to NULL
    pos->next = pos->prev = NULL;
    
    //delete the node
    delete pos;
    //set pointer to NULL
    pos = NULL;
    //decrement size
    size--;
    return true;
    
}
