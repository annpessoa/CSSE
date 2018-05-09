/*
 * This class serves to contain information on each fraction in a text file. 
 * This class will store both the numerator and denominator, reduce fractions
 * and test for equality.
 * 
 * @ Anneliese Pessoa 
 * @ 4/10/2016
 * 
 */

public class Fraction {
	private int numerator;
	private int denominator;

	/*
	 * This is the default no argument constructor.
	 */
	public Fraction() {
		setNumerator(0);
		setDenominator(0);
	}

	/*
	 * This is the constructor that initializes data.
	 */
	public Fraction(int num, int denom) {
		if (num == denom) { // if both the num and denom are equal, reduce to 1
			setNumerator(1);
			setDenominator(1);
		} else if (denom == 0) { // throw an error if the denominator is zero
			System.err.println("Cannot divide by zero.");
			System.exit(0);
		} else {
			setNumerator(num); // set the numerator
			setDenominator(denom); // set the denominator
		}
	}

	/*
	 * This constructor creates a new fraction based on the values of an already
	 * existing fractions.
	 */
	public Fraction(Fraction newFrac) {
		this.setNumerator(newFrac.getNumerator()); // set numerator
		this.setDenominator(newFrac.getDenominator()); // set denominator
	}

	/*
	 * This method will test whether one Fraction object will equal another
	 * Fraction object
	 */
	@Override
	public boolean equals(Object o) {
		// first return false if the object is null or not a Fraction
		if (o == null || !(o instanceof Fraction)) {
			return false;
		}
		Fraction that = (Fraction) o; // create a new Fraction object to test
										// equality
		// return if the num and denom of both fractions are equal
		if (this.getDenominator() == 0 || that.getDenominator() == 0) {
			return false; // return false if any denominator is zero
		}
		return this.numerator * that.denominator == this.denominator * that.numerator;
	}

	/*
	 * Return the numerator of the Fraction object
	 */
	public int getNumerator() {
		return numerator;
	}

	/*
	 * Set the numerator of the Fraction object.
	 */
	public void setNumerator(int numerator) {
		this.numerator = numerator; // set the numerator
	}

	/*
	 * Return the denominator of the Fraction object.
	 */
	public int getDenominator() {
		return denominator;
	}

	/*
	 * Set the denominator of the Fraction object.
	 */
	public void setDenominator(int denominator) {
		this.denominator = denominator; // set the denominator
	}

	/*
	 * This function will reduce the fraction.
	 */
	public void reduce() {
		// create local variables for the numerator and denominator
		int num = numerator;
		int denom = denominator;
		int gcf = gcf(num, denom); // get the greatest common factor
		setNumerator(num / gcf); // divide the numerator by the gcf and set it
		setDenominator(denom / gcf); // divide the denominator by the gcf and set it
	}

	/*
	 * This method returns the greatest common factor of both the numerator and
	 * the denominator.
	 */
	public int gcf(int num, int denom) {
		if (denom == 0) {
			return num;
		}
		return gcf(denom, num % denom);
	}

	/*
	 * This method will return a string of the fraction.
	 */
	public String toString() {
		return numerator + "/" + denominator;
	}
}
