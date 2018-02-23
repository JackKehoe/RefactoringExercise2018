package bankexercise;

import javax.swing.JOptionPane;

public class RecordsMenuMethods extends BankApplication {

	private static final long serialVersionUID = 1L;

	public static void createItem() {
		new CreateBankDialog(table);
	}

	public static void modifyItem() {
		if (table.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No accounts, please create an account", "WARNING",
					JOptionPane.WARNING_MESSAGE);
		} else {
			surnameTextField.setEditable(true);
			firstNameTextField.setEditable(true);
			openValues = true;
		}
	}

	public static void deleteItem() {
		if (table.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No accounts, please create an account", "WARNING",
					JOptionPane.WARNING_MESSAGE);
		} else {
			accountList.remove(currentItem);
			JOptionPane.showMessageDialog(null, "Account Deleted");
			currentItem = 0;
			if (currentItem < (accountList.size() - 1)) {
				currentItem++;
			}
			displayDetails(currentItem);
		}
	}

	public static void setOverdraft() {
		if (table.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No accounts, please create an account", "WARNING",
					JOptionPane.WARNING_MESSAGE);
		} else if (table.get(currentItem).getAccountType().trim().equals("Current")) {
			String newOverdraftStr = JOptionPane.showInputDialog(null, "Enter new Overdraft",
					JOptionPane.OK_CANCEL_OPTION);
			overdraftTextField.setText(newOverdraftStr);
			table.get(currentItem).setOverdraft(Double.parseDouble(newOverdraftStr));
		} else
			JOptionPane.showMessageDialog(null, "Overdraft only applies to Current Accounts");

	}

	public static void setInterest() {
		if (table.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No accounts, please create an account", "WARNING",
					JOptionPane.WARNING_MESSAGE);
		} else {
			String interestRateStr = JOptionPane.showInputDialog("Enter Interest Rate: (do not type the % sign)");
			if (interestRateStr != null && !interestRateStr.contains("%"))
				interestRate = Double.parseDouble(interestRateStr);

		}

	}
	
}