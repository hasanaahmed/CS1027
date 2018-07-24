
public class SavingsAccount extends BankAccount {
	public double rate;

	public SavingsAccount(double initialAmount, double theRate) {
		super(initialAmount);
		rate = theRate;
	}
	
	public void calculateInterest() {
		balance = (balance * rate) + balance;
	}
	
	public String toString() {
		return ("Savings Account balance: $" + balance + " with a rate of: " + rate );
	}
}
