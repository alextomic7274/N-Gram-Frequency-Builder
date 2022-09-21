package ie.atu.sw;

import java.io.*;

public class NGramSaver {
	private Utils sg3;
	
	public NGramSaver(Utils sg) {
		sg3 = new Utils();
		this.sg3 = sg;
	}
	
	// Save() writes the table to a .CSV file, it names the file what the user specified and it only
	// writes rows that are not null, counter-acting the sporadic placement caused by the hash code
	// modulus indexing.
	public void save(Object[][] table, String file, int ngSize) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File(file));
		String newLine = System.getProperty("line.separator");
		pw.println(ngSize + "-Gram" + "," + "Count" + "," + "% of Total");
		
		for (int row = 0; row < table.length; row++) {
			if (table[row][0] != null && table[row][1] != null) {
				sg3.addToCount();
				double percent = sg3.getPercentage((long) table[row][1]);
				if (percent > sg3.getPercentLimit()) {
					pw.write(table[row][0] + "," + table[row][1] + "," + percent + newLine);
				}	else {
					pw.write(table[row][0] + "," + table[row][1] + "," + sg3.getPercentText() + newLine);
					}
		    }
		}
		pw.close();
	}
	
	// This method generates a report which prints to the terminal when the program finishes building n-grams.
	// It shows the total amount of n-grams extracted, how many different n-grams there is
	// and the total time it took to parse each file and extract the n-grams.
	public void getReport(long totalTime) {
		System.out.println("--------------------------------------------------------------------");
		System.out.println("------------------------------ REPORT ------------------------------");
		System.out.println("Total "+sg3.getNgSize()+"-Grams"+" Extracted: "+sg3.getTotalNgrams());
		System.out.println("Total Different "+sg3.getNgSize()+"-Gram Types Found: "+sg3.getCount());
		System.out.println("Total Time For Parsing "+sg3.getNgSize()+"-Grams: "+totalTime+" ms");
		System.out.println("--------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------");
	}
	


}