package ie.atu.sw;

import java.util.Scanner;

public class Menu {
	private boolean keepRunning = true;
	private Scanner s;
	public Utils u;
	
	// Constructor that creates scanner, Utils class and MenuVisuals class objects.
	// -> Utils contains variables, setters, getters and miscellaneous classes needed
	// throughout the program execution.
	// -> MenuVisuals contains method that prints menu and loading bar methods.
	public Menu() {
		s = new Scanner(System.in);
		u = new Utils();
	}
	
	// This method starts the menu functionality and uses 
	// the switch statement to inspect user input and
	// call the correct menu method.
	public void start() throws Exception{
		try {
			while (keepRunning) {
				MenuVisuals.showMenu();
				int userChoice = Integer.parseInt(s.next()); 
				switch (userChoice) {
					case 1 -> specifyDirectory();
					case 2 -> setSize();
					case 3 -> setOutputFile();
					case 4 -> charRange();
					case 5 -> build();
					case 6 -> view();
					case 7 -> System.exit(0);
				}
			}
		} catch (NumberFormatException e) {
			start();
		} catch (Exception e) {
			start();
		}
	} 
		
	// Asks user for file path to directory containing files to be parsed and 
	// sets the user input as the directory variable. 
	private void specifyDirectory() {
			System.out.println("Enter file path to directory (e.g /home/username/N-Gram-Project/TextFiles) -->");
			u.setDirectory(s.next());
	}
	
	// Asks user for N-gram size and sets the ngSize variable as user input.
	// Validates input by ensuring number is 1-5. If its a number outside of this range
	// or not a number at all, an exception is caught an the method is recursively called again.
	private void setSize() {
			System.out.println("Enter N-gram size 1-5 -->");
			try {
				int n = Integer.parseInt(s.next());
				if (n > 0 && n <= 5) {
					u.setNgSize(n);
				}	else {
					System.out.println("ERROR: Unsupported Size");
					setSize();
				}
			} catch (NumberFormatException e) {
				System.out.println("ERROR: Integers Only");
				setSize();
			}
	}
	
	// Asks for the name of the output file where we want n-gram table
	// to be placed and sets outputFile variable as user input.
	// EXTRA: Checks if file name entered has .csv extension, if not 
	// it calls the method again.
	private void setOutputFile() {
		try {
			System.out.println("Enter output file name (.csv only) -->");
			String userFile = s.next();
			if (u.checkCsvFile(userFile)) {
			u.setOutputFile(userFile);
			}	else setOutputFile();
		} catch (Exception e) {
			System.out.println("ERROR: .csv only");
			setOutputFile();
		}
	}
	
	// Prompts user for character range desired and validates input, ensuring 
	// characters specified are A-Z or a-z. 
	private void charRange() {
		u.setRangeOn(true);
		System.out.println("Enter Start Of Range -->");
		char start = s.next().charAt(0);
		if (u.checkCharRange(start)) {
			u.setRangeStart(start);
		}	else charRange();
		System.out.println("Enter End Of Range -->");
		char end = s.next().charAt(0);
		if (u.checkCharRange(end)) {
			u.setRangeEnd(end);
		}	else charRange();
		System.out.println("INFO: Ranges Set Succesfully");
	}
	
	// Creates Parser object with ngSize passed in and
	// calls parseDirectory with the name of the directory 
	// entered by the user.
	private void build() throws Exception {
		if (u.checkInput()) {
			Parser p = new Parser(u);
			MenuVisuals.startProgress(10);
			p.parseDirectory();
		}	else {
				System.out.println("ERROR: Specify target directory, n-gram size and"+"\n"+"output file before selecting build.");
				start();
			}
		System.out.println("INFO: Successful - Check src folder for "+u.getOutputFile());
	}

	// Calls viewInput method from Utils class which displays current input stored in variables.
	private void view() throws InterruptedException {
		u.viewInput();
	}	
	
}
	
	