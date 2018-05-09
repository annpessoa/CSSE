/*
 * This class is used to track a valid month day and year integer value. The day values will range
 * from 1 to 31, the month values will range from 1 to 12, and the year values will range from 2014
 * to 2024.
 * 
 * @ Anneliese Pessoa 
 * @ 4/12/2016
 * 
 */
public class Date {
	private int month;
	private int day;
	private int year;

	/*
	 * Empty constructor.
	 */
	public Date() {
		setMonth(1); //set the month value
		setDay(1); //set the day value
		setYear(2014); //set the year value
	}

	/*
	 * Constructor to set the month value.
	 */
	public Date(int mo) {
		setMonth(mo); //set the month value
	}

	/*
	 * Constructor to set the month and day values.
	 */
	public Date(int mo, int d) {
		setMonth(mo); //set the month value
		setDay(d); //set the day value
	}

	/*
	 * Constructor to set the month, day, and year values.
	 */
	public Date(int mo, int d, int yr) {
		setMonth(mo); //set the month value
		setDay(d); //set the day value
		setYear(yr); //set the year value
	}

	/*
	 * Constructor to set the month, day, and year values from an existing Date object.
	 */
	public Date(Date newDate) {
		setMonth(newDate.getMonth()); //set month value
		setDay(newDate.getDay()); //set day value
		setYear(newDate.getYear()); //set year value
	}

	/*
	 * Set the day value if it is within the range of 1-31.
	 */
	public void setDay(int d) {
		if (d >= 1 && d <= 31) { //test if the day value fits the correct range
			this.day = d; //set the day to a new integer value
		} else {
			System.err.println("Bad day!" + d); //print out an error message saying the day is invalid
		}
	}

	/*
	 * Set the year value if it is within the range of 2014-2024.
	 */
	public void setYear(int y) {
		if (y >= 2014 && y <= 2024) { //test if the year is within the correct range
			this.year = y; //set the year value to a new integer value
		} else {
			System.err.println("Bad Year!" + y); //print out an error message saying the year is invalid
		}
	}

	/*
	 * Set the month value if it is within the range of 1-12.
	 */
	public void setMonth(int m) {
		if (m >= 1 && m <= 12) { //test if the month is within the correct range
			this.month = m; //set the month value to a new integer value
		} else {
			System.err.println("Bad month!" + m); //print out an error message saying the month is invalud
		}
	}
	
	/*
	 * Set all values of the date.
	 */
	public void setDate(int m, int d, int y) {
		setMonth(m);
		setDay(d);
		setYear(y);
	}

	/*
	 * Return the value of the year.
	 */
	public int getYear() {
		return new Integer(year); //return a new integer with the value of year
	}

	/*
	 * Return the value of the day.
	 */
	public int getDay() {
		return new Integer(day); //return a new integer with the value of day
	}

	/*
	 * Return the value of the month.
	 */
	public int getMonth() {
		return new Integer(month); //return a new integer with the value of month
	}

	/*
	 * Return a string with the values of the month, day, and year.
	 */
	@Override
	public String toString() {
		return getMonth() + "/" + getDay() + "/" + getYear(); //return month day and year
	}

	/*
	 * Return a boolean that states whether a Date object equals another Date object.
	 */
	@Override
	public boolean equals(Object o) {
		Date that = (Date) o; //create a new Date object to compare with the current object
		if (o == null || !(o instanceof Date)) { //test if the object is a Date object
			return false;
		}
		//test if all the values inside of each date are equal
		return this.getYear() == that.getYear() && this.getMonth() == that.getMonth() && this.getDay() == that.getDay();
	}

}
