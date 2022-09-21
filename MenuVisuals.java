package ie.atu.sw;

public class MenuVisuals {
	
	public MenuVisuals() {
		super();
	}

	public static void showMenu() {
		// Print menu options and asks user to make a selection.
		System.out.println(ConsoleColour.WHITE);
		System.out.println("************************************************************");
		System.out.println("**                                                        **");
		System.out.println("**                  N-Gram Frequency Builder              **");
		System.out.println("**                                                        **");
		System.out.println("************************************************************");
		System.out.println("  SELECT AN OPTION 1 - 7                               ");
		System.out.println("  (1) Specify Text File Directory                        ");
		System.out.println("  (2) Specify N-Gram Size                                ");
		System.out.println("  (3) Specify Output File                                ");
		System.out.println("  (4) Specify Character Range (a-z)                      ");
		System.out.println("  (5) Build N-Grams                                      ");
		System.out.println("  (6) View Current Parameters                            ");
		System.out.println("  (7) Quit                                               ");
		System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
	}
	
	// Starts the progress meter by calling the printProgress method and 
	// uses a loop to repeat until progress reaches 100%.
	static void startProgress(int time) throws InterruptedException {
		System.out.print(ConsoleColour.YELLOW);	//Change the colour of the console text
		int size = 100;							//The size of the meter. 100 equates to 100%
		for (int i =0 ; i < size ; i++) {		//The loop equates to a sequence of processing steps
			printProgress(i + 1, size); 		//After each (some) steps, update the progress meter
			Thread.sleep(time);					//Slows things down so the animation is visible 
		}
		System.out.println();
	}
	
	// Prints the progress bar if the index entered is larger than total.
		public static void printProgress(int index, int total) {
			if (index > total) return;	//Out of range
	        int size = 50; 				//Must be less than console width
		    char done = '█';		
		    char todo = '░';				    
		    //Compute basic metrics for the meter
	        int complete = (100 * index) / total;
	        int completeLen = size * complete / 100;
	        /*
	         * A StringBuilder should be used for string concatenation inside a 
	         * loop. However, as the number of loop iterations is small, using
	         * the "+" operator may be more efficient as the instructions can
	         * be optimized by the compiler. Either way, the performance overhead
	         * will be marginal.  
	         */
	        StringBuilder sb = new StringBuilder();
	        sb.append("[");
	        for (int i = 0; i < size; i++) {
	        	sb.append((i < completeLen) ? done : todo);
	        }
	        /*
	         * The line feed escape character "\r" returns the cursor to the 
	         * start of the current line. Calling print(...) overwrites the
	         * existing line and creates the illusion of an animation.
	         */
	        System.out.print("\r" + sb + "] " + complete + "%");
	        //Once the meter reaches its max, move to a new line.
	        if (done == total) System.out.println("\n");
	    }
		
		

}
