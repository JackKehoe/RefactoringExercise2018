package bankexercise;

public class BankAccount {

	private int accountID;
	private String accountNumber, surname, firstName, accountType;
	private double balance, overdraft;


	public BankAccount(int accountID, String accountNumber, String surname, String firstName, String accountType,
			double balance, double overdraft) {
		this.accountID = accountID;
		this.accountNumber = accountNumber;
		this.surname = surname;
		this.firstName = firstName;
		this.accountType = accountType;
		this.balance = balance;
		this.overdraft = overdraft;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(double overdraft) {
		this.overdraft = overdraft;
	}

	public String toString() {
		return "\nAccount id: " + accountID + "Account Num: " + accountNumber + "\nName: " + surname + " " + firstName
				+ "\n";
	}

}
