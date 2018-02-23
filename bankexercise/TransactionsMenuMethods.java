package bankexercise;

import java.util.Map;
import javax.swing.JOptionPane;

public class TransactionsMenuMethods extends BankApplication {

	public static void deposit() {

		String accNum = JOptionPane.showInputDialog("Account number to deposit into: ");
		boolean found = false;

		if (table.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No accounts, please create an account", "WARNING",
					JOptionPane.WARNING_MESSAGE);
		} else {
			for (Map.Entry<Integer, BankAccount> entry : table.entrySet()) {
				if (accNum.equals(entry.getValue().getAccountNumber().trim())) {
					found = true;
					String toDeposit = JOptionPane.showInputDialog("Account found, Enter Amount to Deposit: ");
					entry.getValue().setBalance(entry.getValue().getBalance() + Double.parseDouble(toDeposit));
					displayDetails(entry.getKey());
				}
			}
			if (!found)
				JOptionPane.showMessageDialog(null, "Account number " + accNum + " not found.");
		}
	}

	public static void withdraw() {

		String accNum = JOptionPane.showInputDialog("Account number to withdraw from: ");
		String toWithdraw = JOptionPane.showInputDialog("Account found, Enter Amount to Withdraw: ");

		if (table.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No accounts, please create an account", "WARNING",
					JOptionPane.WARNING_MESSAGE);
		} else {
			for (Map.Entry<Integer, BankAccount> entry : table.entrySet()) {

				if (accNum.equals(entry.getValue().getAccountNumber().trim())) {

					if (entry.getValue().getAccountType().trim().equals("Current")) {
						if (Double.parseDouble(toWithdraw) > entry.getValue().getBalance()
								+ entry.getValue().getOverdraft())
							JOptionPane.showMessageDialog(null, "Transaction exceeds overdraft limit");
						else {
							entry.getValue().setBalance(entry.getValue().getBalance() - Double.parseDouble(toWithdraw));
							displayDetails(entry.getKey());
						}
					} else if (entry.getValue().getAccountType().trim().equals("Deposit")) {
						if (Double.parseDouble(toWithdraw) <= entry.getValue().getBalance()) {
							entry.getValue().setBalance(entry.getValue().getBalance() - Double.parseDouble(toWithdraw));
							displayDetails(entry.getKey());
						} else
							JOptionPane.showMessageDialog(null, "Insufficient funds.");
					}
				}
			}
		}
	}

	public static void calcInterest() {
		if (table.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No Accounts selected, please create an account", "WARNING",
					JOptionPane.WARNING_MESSAGE);
		} else {
			if (!(interestRate == 0)) {
				for (Map.Entry<Integer, BankAccount> entry : table.entrySet()) {
					if (entry.getValue().getAccountType().equals("Deposit")) {
						double equation = 1 + ((interestRate) / 100);
						entry.getValue().setBalance(entry.getValue().getBalance() * equation);
						JOptionPane.showMessageDialog(null, "Balances Updated");
						displayDetails(entry.getKey());
					}
				}
			}
		}
	}
}