// ------------------------- polynomialDriver.cpp ------------------------------

// Anneliese Pessoa CSS 343 A

// April 1, 2016

// April 6, 2016

// -----------------------------------------------------------------------------

// Purpose - This file provides a driver neccessary to create polynomials
//           in the form of a circular doubly linked list. This tests all public
//           functions within the polynomial ADT.

// -----------------------------------------------------------------------------

//  All output will be printed out onto the console as it runs.

// -----------------------------------------------------------------------------
#include <iostream>
#include <fstream>
#include "polynomial.h"

using namespace std;


int main( ){
    //make basic polynomials
    cout << "Creating three basic polynomials for testing" << endl;
    cout << endl;
    
    //make first polynomial
    Polynomial p1;
    //test adding positive terms
    p1.changeCoefficient(1, 1);
    p1.changeCoefficient(2, 2);
    p1.changeCoefficient(3, 3);
    p1.changeCoefficient(4, 4);
    //test operator <<
    cout << "Polynomial 1: " << p1 << endl;
    //test degree()
    cout << "Polynomial 1 degree: " << p1.degree() << endl;
    //test coefficient()
    cout << "Polynomial 1 coefficient of x^2: " << p1.coefficient(2) << endl;
    cout << endl;
    
    //make second polynomial
    Polynomial p2;
    //test adding positive and negative terms
    p2.changeCoefficient(1.2, 1);
    p2.changeCoefficient(-2.3, 2);
    p2.changeCoefficient(3.4, 3);
    p2.changeCoefficient(-4.5, 4);
    //test operator <<
    cout << "Polynomial 2: " << p2 << endl;
    //test degree()
    cout << "Polynomial 2 degree: " << p2.degree() << endl;
    //test coefficient()
    cout << "Polynomial 2 coefficient of x^2: " << p2.coefficient(2) << endl;
    cout << endl;
    
    //make thrird polynomial by copy constructor
    Polynomial p3(p1);
    //test operator <<
    cout << "Polynomial 3: " << p3 << endl;
    //test degree()
    cout << "Polynomial 3 degree: " << p3.degree() << endl;
    //test coefficient()
    cout << "Polynomial 3 coefficient of x^2: " << p3.coefficient(2) << endl;
    cout << endl << endl;
    
    
    
    
    cout << "Testing operator == and !=" << endl;
    //compare all polynomials to eachother using ==
    bool p1p2 = p1 == p2;
    bool p1p3 = p1 == p3;
    bool p2p3 = p2 == p3;
    
    //print results
    cout << "Polynomial 1 == Polynomial 2? " << std::boolalpha << p1p2 << endl;
    cout << "Polynomial 1 == Polynomial 3? " << std::boolalpha << p1p3 << endl;
    cout << "Polynomial 2 == Polynomial 3? " << std::boolalpha << p2p3 << endl;
    cout << endl;
    
    //compare all polynomials to eachother using !=
    p1p2 = p1 != p2;
    p1p3 = p1 != p3;
    p2p3 = p2 != p3;
    
    //print results
    cout << "Polynomial 1 != Polynomial 2? " << std::boolalpha << p1p2 << endl;
    cout << "Polynomial 1 != Polynomial 3? " << std::boolalpha << p1p3 << endl;
    cout << "Polynomial 2 != Polynomial 3? " << std::boolalpha << p2p3 << endl;
    cout << endl << endl;
    
    
    
    
    //test operator + and -
    cout << "Testing operator + and -" << endl;
    cout << endl;
    
    //make fouth polynomial by adding p1 and p2
    cout << "Polynomial 4 = Polynomial 1 + Polynomial 2" << endl;
    Polynomial p4 = p1 + p2;
    cout << "Polynomial 4: " << p4 << endl;
    cout << endl;
    
    //make fifth polynomial by adding p3 and p4
    cout << "Polynomial 5 = Polynomial 3 + Polynomial 4" << endl;
    Polynomial p5 = p3 + p4;
    cout << "Polynomial 5: " << p5 << endl;
    cout << endl;
    
    //make sixth polynomial by subtracting p1 from p5
    cout << "Polynomial 6 = Polynomial 5 - Polynomial 1" << endl;
    Polynomial p6 = p5 - p1;
    cout<< "Polynomial 6: " << p6 << endl;
    cout << endl;
    
    //make seventh polynomial by subtracting p6 from p5
    cout << "Polynomial 7 = Polynomial 5 + Polynomial 6" << endl;
    Polynomial p7 = p5 - p6;
    cout << "Polynomial 7: " << p7 << endl;
    cout << endl << endl;
    
    
    
    
    //test operator += and -=
    cout << "Testing operator += and -=" << endl;
    cout << endl;
    
    p1 += p2;
    cout << "Polynomial 1 += Polynomial 2: " << p1 << endl;
    
    //undo previous addition by subtracting p2
    p1 -= p2;
    cout << "Polynomial 1 -= Polynomial 2: " << p1 << endl;
    cout << endl << endl;
    
    
    
    
    //test removing of terms
    cout << "Testing removing terms" << endl << endl;
    //remove term with highest degree
    p1.changeCoefficient(0, p1.degree());
    cout << "Remove largest term from Polynomial 1: " << p1 << endl << endl;
    
    //remove term with power of 2
    p2.changeCoefficient(0, 2);
    cout << "Remove term with power of 2 from Polynomial 2: " << p2 << endl;
    
    //attempt to remove a term that does not exist
    cout << "Remove term with power of 8 from Polynomial 1: ";
    p1.changeCoefficient(0, 8);
    
    return 0;
}
