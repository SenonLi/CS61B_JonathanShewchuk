/**
 * Created by SenonLi on 6/29/2017.
 */
public class BadTransactionException extends Exception {
	public int amountNumber;  // The invalid account number.

	/**
	 *  Creates an exception object for nonexistent account "badAcctNumber".
	 **/
	public BadTransactionException(int badAmount) {
		super("Invalid amount number: " + badAmount);
		amountNumber = badAmount;
	}
}
