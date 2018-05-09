/*
 * This class is used to trac the amount, due date, paid date, and originator of a bill. It 
 * contains the amount of money owed as a Money object, the due date as a Date object, the paid
 * date as a Date object, and the originator as a String. The paid date must be before the due
 * date in order to be valid.
 * 
 * @ Anneliese Pessoa 
 * @ 4/12/2016
 * 
 */
public class Bill {
	private Money amount;
	private Date dueDate;
	private Date paidDate;
	private String originator;

	/*
	 * Constructor that sets the amount for the money object, the due date, and the originator.
	 */
	public Bill(Money amt, Date due, String origin) {
		setAmount(amt); //set the money amount
		setDueDate(due); //set the due date
		setOriginator(origin); //set the originator
	}

	/*
	 * Constructor that sets all values in a Bill object to be equal to that of another Bill object.
	 */
	public Bill(Bill toBill) {
		setAmount(toBill.getAmount()); //set money aount
		setDueDate(toBill.getDueDate()); //set due date
		setOriginator(toBill.getOriginator()); //set the originator
	}

	/*
	 * Return the amount of money on the bill.
	 */
	public Money getAmount() {
		return amount; //return a new Money object
	}

	/*
	 * Return the due date of the bill.
	 */
	public Date getDueDate() {
		return dueDate; //return a new Date object
	}

	/*
	 * Return the paid date of the bill.
	 */
	public Date getPaidDate() {
		return paidDate; //return a new Date object
	}

	/*
	 * Return the originator of the bill.
	 */
	public String getOriginator() {
		return originator; //return a new String object
	}

	/*
	 * Returns a boolean stating whether or not the bill has been paid or not.
	 */
	public boolean isPaid() {
		if (paidDate != null) { //if the paid date exists then return true, return false otherwise
			return true;
		}
		return false;
	}

	/*
	 * This method will set the paid date if the date is valid.
	 */
	public void setPaid(Date onDay) {
		//only sets the paid date if it is before the bill's due date
		if (onDay.getDay() < this.getDueDate().getDay() || onDay.getMonth() < this.getDueDate().getMonth()
				|| onDay.getYear() < this.getDueDate().getYear()) {
			this.paidDate = onDay; //set the paid date to a new Date object
		} else {
			System.err.println("Bill paid after due date!"); //print an error message to the console
		}
	}

	/*
	 * This method will make the bill state that it is unpaid.
	 */
	public void setUnpaid() {
		this.paidDate = null; //set the date values to null
	}

	/*
	 * This method will set the due date but only if the new due date is after the paid date.
	 */
	public void setDueDate(Date nextDate) {
		//test if the new date is after the paid date
		if(this.getPaidDate() == null){
			this.dueDate = new Date(nextDate);
		}else if (nextDate.getDay() > this.getPaidDate().getDay() && nextDate.getMonth() > this.getPaidDate().getMonth()
				&& nextDate.getYear() > this.getPaidDate().getYear()) {
			this.dueDate = new Date(nextDate); //set the new due date
		}else {
			System.err.println("Invalid due date!!"); //print an error message to the console
		}
	}
	
	/*
	 * This method will set the money amount of the Bill.
	 */
	public void setAmount(Money amt) {
		this.amount = new Money(amt); //set money amount
	}

	/*
	 * This method will set the originator of the Bill.
	 */
	public void setOriginator(String origin) {
		this.originator = origin; //set originator
	}

	/*
	 * This method will return a string that prints out the amount, the due date, the originator,
	 * whether or not the bill has been paid, and if it has been paid the paid date will be returned
	 * as well.
	 */
	@Override
	public String toString() {
		if (isPaid()) { //return if the bill has been paid and when it was paid
			return "Bill amount: " + getAmount() + " Due on: " + getDueDate() + " to " + getOriginator()
					+ "\nHas the bill been paid: " + isPaid() + ". Paid on: " + getPaidDate();
		}
		//return if the bill has not been paid yet
		return "Bill amount: " + getAmount() + " Due on: " + getDueDate() + " to " + getOriginator()
				+ "\nHas the bill been paid? " + isPaid();
	}

	/*
	 * Returns whether or not two Bill objects are equal.
	 */
	public boolean equals(Object toCompare) {
		Bill that = (Bill) toCompare; //create a temporary Bill object to compare values
		if (toCompare == null || !(toCompare instanceof Bill)) { //test if the object is truly a Bill object
			return false;
		}
		//return whether or not all values in each Bill object are equal
		return this.getAmount().equals(that.getAmount()) && this.getDueDate().equals(that.getDueDate())
				&& this.getOriginator().equals(that.getOriginator()) && this.getPaidDate().equals(that.getPaidDate());
	}
}
