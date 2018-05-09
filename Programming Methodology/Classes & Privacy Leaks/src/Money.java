/*
 * This class is used to track a USD amount consisting of two integers to manage dollars and cents.
 * All dollars and cents will be positive or 0, and cents will never exceed 99.
 * 
 * @ Anneliese Pessoa 
 * @ 4/12/2016
 * 
 */
public class Money {
	private int dollars;
	private int cents;

	/*
	 * Empty constructor.
	 */
	public Money() {

	}

	/*
	 * Constructor to set the dollar amount of the Money object.
	 */
	public Money(int dol) {
		setDollars(dol); //set dollar amount
	}

	/*
	 * Constructor to set both the dollar and the cent values of the Money object.
	 */
	public Money(int dol, int cent) {
		setDollars(dol); //set dollar amount
		setCents(cent); //set cents amount
	}
	
	/*
	 * Constructor to set the value of the Money object to the values of another Money object.
	 */
	public Money(Money other) {
		setDollars(other.getDollars()); //set dollar amount from the other Money object
		setCents(other.getCents()); //set cents amount from the other money object
	}

	/*
	 * Returns the dollar value of the money object.
	 */
	public int getDollars() {
		return new Integer(dollars); //return a new integer that is the value of the dollars int
	}

	/*
	 * Sets the dollar value of the object but only if the value is positive.
	 */
	public void setDollars(int dol) {
		if (dol >= 0) { //test if the dollar amount is positive
			this.dollars = new Integer(dol); //set the dollar amount
		} else {
			System.err.println("Bad dollar value!" + dol); //print out an error message saying the dollar is invalid
		}
	}

	/*
	 * Returns the cent value of the money object.
	 */
	public int getCents() {
		return new Integer(cents); //return a new integer that is the value of the cents int
	}

	/*
	 * Sets the cent value of the object if the value is positive and within the range of 0-99.
	 * If the cent value is more than 99, the dollar value will increase along with the cent value.
	 */
	public void setCents(int cent) {
		if (cent >= 0 && cent <= 99) { //test if the cent value is within the acceptable range
			this.cents = new Integer(cent); //set the cent amount
		} else if (cent > 99) { //if the cent amount is more than one dollar
			add(cent / 100); //get a dollar value from the cent integer
			this.cents = new Integer(cent % 100); //get a cent value from the remainder 
		} else{
			System.err.println("Bad cent value! " + cent); //print out an error message saying the cent is invalid
		}
	}

	/*
	 * Returns the double value created by both the dollar and cent values.
	 */
	public double getMoney() {
		double retVal = this.dollars; //get double for money by getting the dollar value to begin with
		double cen = (double) this.cents / 100; //get cent value as a decimal
		return new Double(retVal += cen); //add the cent value to dollar and return as a new double
	}

	/*
	 * Sets the money value outside of the constructor.
	 */
	public void setMoney(int dol, int cent) {
		this.setDollars(dol); //set dollar value 
		this.setCents(cent); //set cent value
	}

	/*
	 * Adds a dollar amount to the existing dollar amount.
	 */
	public void add(int dol) {
		this.setDollars(this.getDollars() + dol); //add the dol amount to the Money object's total dollar amount
	}

	/*
	 * Adds a dollar and cent amount to the existing dollar and cent amounts.
	 */
	public void add(int dol, int cent) {
		this.setDollars(this.getDollars() + dol); //add the dol amount to the Money object's total dollar amount
		this.setCents(this.getCents() + cent); //add the cent amount to the Money object's total cents amount
	}

	/*
	 * Adds both a dollar and cent amount to the existing money object from another Money object.
	 */
	public void add(Money other) {
		add(other.getDollars(), other.getCents()); //add the dollar and cent value from the other Money object
	}

	/*
	 * Equals function to test if two Money objects are equal.
	 */
	@Override
	public boolean equals(Object o) {
		Money that = (Money) o; //create a new Money object to test equality
		if (o == null || !(o instanceof Money)) { //return false if the object is not a Money object
			return false;
		}
		return this.getMoney() == that.getMoney(); //return whether or not the total money amounts of both objects are equal
	}

	/*
	 * Returns a String containing the value of the Money object.
	 */
	@Override
	public String toString() {
		return String.format("$%.2f", getMoney()); //return the money amount with a dollar sign
	}
}
