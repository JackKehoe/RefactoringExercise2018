package bankexercise;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileMenuMethods extends BankApplication {

	protected static RandomAccessFile input;
	protected static RandomAccessFile output;
	protected static JFileChooser fc;
	protected static String fileToSaveAs = "";

	public static void writeFile() {
		if (table.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No accounts, please create an account", "WARNING", JOptionPane.WARNING_MESSAGE);
		} else
			openFileWrite();
			saveToFile();
			closeFile();
	}

	public static void saveFileAs() {
		if (table.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No accounts, please create an account", "WARNING", JOptionPane.WARNING_MESSAGE);
		} else
			saveToFileAs();
			saveToFile();
			closeFile();
	}

	public static void readFile() {
			openFileRead();
			readRecords();
			closeFile();
	}

	public static void openFile() {
		readFile();
		currentItem = 0;
		while (!table.containsKey(currentItem)) {
			currentItem++;
		}
		displayDetails(currentItem);
	}

	public static void openFileRead() {
		table.clear();
		fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {

		} else {
		}
		try {
			if (fc.getSelectedFile() != null)
				input = new RandomAccessFile(fc.getSelectedFile(), "r");
		} catch (IOException ioException) {
			JOptionPane.showMessageDialog(null, "File Does Not Exist.");
		}
	}

	public static void openFileWrite() {
		if (fileToSaveAs != "") {
			try {
				output = new RandomAccessFile(fileToSaveAs, "rw");
				JOptionPane.showMessageDialog(null, "Accounts saved to " + fileToSaveAs);
			} catch (IOException ioException) {
				JOptionPane.showMessageDialog(null, "File does not exist.");
			}
		} else
			saveToFileAs();
	}

	public static void saveToFileAs() {

		fc = new JFileChooser();

		int returnVal = fc.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();

			fileToSaveAs = file.getName();
			JOptionPane.showMessageDialog(null, "Accounts saved to " + file.getName());
		} else {
			JOptionPane.showMessageDialog(null, "Save cancelled by user");
		}

		try {
			if (fc.getSelectedFile() == null) {
				JOptionPane.showMessageDialog(null, "Cancelled");
			} else
				output = new RandomAccessFile(fc.getSelectedFile(), "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void closeFile() {
		try {
			if (input != null)
				input.close();
		} catch (IOException ioException) {

			JOptionPane.showMessageDialog(null, "Error closing file.");
		}
	}

	public static void readRecords() {

		RandomAccessBankAccount record = new RandomAccessBankAccount();

		try {
			while (true) {
				do {
					if (input != null)
						record.read(input);
				} while (record.getAccountID() == 0);

				BankAccount ba = new BankAccount(record.getAccountID(), record.getAccountNumber(),
						record.getFirstName(), record.getSurname(), record.getAccountType(), record.getBalance(),
						record.getOverdraft());

				Integer key = Integer.valueOf(ba.getAccountNumber().trim());

				int hash = (key % TABLE_SIZE);

				while (table.containsKey(hash)) {

					hash = hash + 1;
				}

				table.put(hash, ba);

			}
		} catch (EOFException eofException) {
			return;
		} catch (IOException ioException) {
			JOptionPane.showMessageDialog(null, "Error reading file.");
			System.exit(1);
		}
	}

	public static void saveToFile() {

		RandomAccessBankAccount record = new RandomAccessBankAccount();

		for (Map.Entry<Integer, BankAccount> entry : table.entrySet()) {
			record.setAccountID(entry.getValue().getAccountID());
			record.setAccountNumber(entry.getValue().getAccountNumber());
			record.setFirstName(entry.getValue().getFirstName());
			record.setSurname(entry.getValue().getSurname());
			record.setAccountType(entry.getValue().getAccountType());
			record.setBalance(entry.getValue().getBalance());
			record.setOverdraft(entry.getValue().getOverdraft());

			if (output != null) {

				try {
					record.write(output);
				} catch (IOException u) {
					u.printStackTrace();
				}
			}

		}

	}

}
