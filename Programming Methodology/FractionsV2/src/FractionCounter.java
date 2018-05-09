/*
 * This program serves to count Fraction objects in comparison to other Fraction objects.
 * This will store a single Fraction and count its number of occurrences in the file.
 * 
 * @ Anneliese Pessoa 
 * @ 4/1/2016
 * 
 */

public class FractionCounter extends Fraction{
	private Fraction currentFrac = new Fraction();
	private int count = 0;

	/*
	 * Default constructor.
	 */
	public FractionCounter(){
		count = 0;
		currentFrac.setNumerator(1);
		currentFrac.setDenominator(1);
	}
	/*
	 * This constructor sets the value of the current fraction object
	 */
	public FractionCounter(Fraction theFraction) {
		currentFrac.setNumerator(theFraction.getNumerator()); //sets current numerator
		currentFrac.setDenominator(theFraction.getDenominator()); //sets the current denominator
	}

	/*
	 * This method compares the current fraction with another fraction and increments the count.
	 */
	public boolean compareAndIncrement(Fraction newFraction) {
		if(currentFrac.equals(newFraction)){ //if the current fraction object is equal to the new Fraction object
			count++;
			return true;
		}
		return false;
	}

	/*
	 * This overrides the toString method to print out the fraction and the number of occurrences in the text file 
	 */
	@Override
	public String toString() {
		return " has a count of " + count;
	}
}
