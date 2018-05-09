/**
 * HW3.java: Simple driver to test Money, Date, and Bill classes
 * 
 * @author Rob Nash, borrowed from cfolsen
 */
public class BillMoneyDateDriver {

	/**
	 * main driver function pre: none post: exercises the methods in Bill,
	 * Money, and Date (not done)
	 */
	public static void main(String[] args) {
		// Construct some money
		Money money1 = new Money(10);
		Money money2 = new Money(money1);
		money1.setMoney(30, 50); // test setMoney()
		// TODO: do more functional exercises with the money class

		System.out.println("Money objects output:");
		System.out.println("money1 : " + money1);
		System.out.println("money2 : " + money2);
		money1.setDollars(87); // test setDollar()
		System.out.println("\nset money1's dollar value to 87 : " + money1);
		money2.setCents(32); // test setCents()
		System.out.println("set money2's cent value to 32 : " + money2);
		// test getDollars()
		System.out.println("\nget the dollar value of money1 : " + money1.getDollars());
		// test getCents()
		System.out.println("get the cent value of money2 : " + money2.getCents());
		// test getMoney()
		System.out.println("\nTest getMoney method: " + money1.getMoney());
		money1.add(5); // test add(int) function
		System.out.println("\nadd $5 to money1 : " + money1);
		money2.add(3, 50); // test add(int, int) function
		System.out.println("add $3.50 to money2 : " + money2);
		money1.add(money2); // test add(Money) function
		System.out.println("add money2 to money1 : " + money1);
		System.out.println("\nis money1 equal to money2? " + money1.equals(money2));
		System.out.println("\nFinal value of money1: " + money1.toString());
		System.out.println("Final value of money2: " + money2.toString());
		System.out.println();

		// construct some dates
		System.out.println("Date objects output:");
		// create four date objects
		Date dateOne = new Date(4);
		Date dateTwo = new Date(4, 15);
		Date dateThree = new Date(4, 15, 2016);
		Date dateFour = new Date(dateThree);
		// test get methods
		System.out.println("get the month of dateOne: " + dateOne.getMonth());
		System.out.println("get the day of dateTwo: " + dateTwo.getDay());
		System.out.println("get the year of dateThree: " + dateThree.getYear());
		// test set methods
		dateOne.setMonth(2); // test setMonth()
		dateOne.setDay(6); // test setDay()
		dateOne.setYear(2018); // test setYear()
		dateTwo.setDate(8, 2, 2015); // test setDate()
		dateThree.setDate(2, 22, 2022);
		// test toString()
		System.out.println("\nNew values for dates:");
		System.out.println("dateOne: " + dateOne.toString());
		System.out.println("dateTwo: " + dateTwo.toString());
		System.out.println("dateThree: " + dateThree.toString());
		System.out.println("dateFour: " + dateFour.toString());
		// test equals()
		System.out.println("\ndateOne equals dateTwo: " + dateOne.equals(dateTwo));
		System.out.println("dateThree equals dateFour: " + dateThree.equals(dateFour));
		System.out.println();

		// Construct some bills
		Money amount = new Money(20);
		Date dueDate = new Date(4, 30, 2014);
		Bill bill1 = new Bill(amount, dueDate, "The phone company");

		Bill bill2 = new Bill(bill1);
		bill2.setDueDate(new Date(5, 30, 2015));
		amount.setMoney(31, 99);
		dueDate.setDay(29);
		Bill bill3 = new Bill(amount, dueDate, "The record company");

		System.out.println("Bill objects output:");
		System.out.println(bill1);
		System.out.println(bill2);
		System.out.println(bill3);
		// test get methods
		System.out.println("\namount on bill1: " + bill1.getAmount().toString());
		System.out.println("due date of bill2: " + bill2.getDueDate());
		System.out.println("Originator of bill3: " + bill3.getOriginator());
		// test setPaid() and setUnpaid() methods
		Date onDay = new Date(1, 1, 2014);
		bill1.setPaid(onDay); // set the bill so that is paid
		System.out.println("\nChanged paid status for bill1: " + bill1.isPaid());
		bill1.setUnpaid(); // set the bill so that it is unpaid
		System.out.println("Changed paid status for bill1: " + bill1.isPaid());
		bill2.setPaid(onDay);
		bill3.setPaid(onDay);
		// test set dueDate() method
		Date newDueDate = new Date(6, 25, 2016);
		bill2.setDueDate(newDueDate);
		System.out.println("Changed due date for bill2: " + bill2.getDueDate());
		// test setOriginator() method
		bill3.setOriginator("Electricity company");
		System.out.println("Changed originator for bill3: " + bill3.getOriginator());
		// test equals()
		System.out.println("\nbill1 is equal to bill2: " + bill1.equals(bill2));
		System.out.println("bill2 is equal to bill3: " + bill2.equals(bill3));
		System.out.println("bill2 is equal to bill2: " + bill2.equals(bill2));
		System.out.println("bill1 is equal to bill3: " + bill1.equals(bill3));
		// test toString()
		System.out.println("\nFinal values for all bills:");
		System.out.println(bill1.toString());
		System.out.println(bill2.toString());
		System.out.println(bill3.toString());
	}
}