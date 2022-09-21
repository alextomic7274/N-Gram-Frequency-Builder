package ie.atu.sw;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class Utils implements Serializable{
	private static final long serialVersionUID = 1L;
	private String directory;
	private int ngSize;
	private String outputFile;
	private int totalNGrams;
	private int count = 0;
	private boolean filter;
	private long totalTime;
	private double percentLimit = 0.0001;
	private String percentText = "< 0.0001";
	private int rangeStart;
	private int rangeEnd;
	private boolean rangeSet = false;
	
	// Below are getters and setters which are called in different classes, this is done in an 
	// effort to make code more organised and store variables in the same place.
	
	public int getRangeStart() {
		return rangeStart;
	}
	public int getRangeEnd() {
		return rangeEnd;
	}
	public boolean isSetRange() {
		return rangeSet;
	}
	public void setRangeOn(boolean setRange) {
		this.rangeSet = setRange;
	}
	public void setRangeStart(char rangeStart) {
		this.rangeStart = (int)Character.toLowerCase(rangeStart);
	}
	public void setRangeEnd(char rangeEnd) {
		this.rangeEnd = (int)Character.toLowerCase(rangeEnd);
	}
	public String getPercentText() {
		return percentText;
	}
	public double getPercentLimit() {
		return percentLimit;
	}
	public long getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(long start, long end) {
		this.totalTime = end-start;
	}
	// Constructor
	public Utils() {}
	
	public String getDirectory() {
		return directory;
	}
	public boolean filterOn() {
		return filter;
	}
	public void setFilter(boolean filter) {
		this.filter = filter;
	}
	public int getCount() {
		return count;
	}
	public void addToCount() {
		this.count = count+1;
	}
	public int getTotalNgrams() {
		return totalNGrams;
	}
	public void setTotalNgrams(int totalNgrams) {
		this.totalNGrams = totalNgrams;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	public int getNgSize() {
		return ngSize;
	}
	public void setNgSize(int ngSize) {
		this.ngSize = ngSize;
	}
	public String getOutputFile() {
		return outputFile;
	}
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}
	
	// Returns the percentage value for the n-gram passed in which is calculated by multiplying the ngram count 
	// by the total n-grams extracted to the table.
	// Limits the result the decimal places to 7.
	public double getPercentage(long ngrams) {
		double result = ((double)ngrams * (double)100) / (double)totalNGrams;
		return limitDecimals(result, 7);
	}
	
	// Takes in the percentage value and limits the decimal places to 7, then returns the value.
	public static double limitDecimals(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	// This method uses a string builder to build a new word by using only characters specified in the 
	// range desired by the user. 
	public String rangeFilter(String word) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < word.length(); i++) {
			int temp = (int)word.charAt(i);
			if (temp >= rangeStart && temp <= rangeEnd) {
				sb.append(word.charAt(i));
			}else {
				continue;
			}
		}
		return sb.toString();
	}
	
	// Takes a string (user input) and checks if
	// last three characters equal "csv", if not returns false.
	public boolean checkCsvFile(String input) {
		String lastThree = input.substring(input.length()-3);
		if (lastThree.equals("csv")) {
			return true;
		}	else return false;
	}
	
	// Checks that all the input needed to build the n-gram frequency table
	// are entered and no variable is empty.
	// If just one variable is empty, the method returns false.
	public boolean checkInput() {
		if ((directory != null) && (ngSize != 0) && (outputFile != null)) {
			return true;
		}	else return false;
	}
	
	// Handles exception if file or directory does not exist.
	public void fileError() {
		System.out.println("ERROR: Cannot Find Directory and/or File(s)");
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// Validates character range entered by user ensuring it is between a and z.
	public boolean checkCharRange(char c) {
		char letter = Character.toLowerCase(c);
		if (letter >= 'a' && letter <= 'z') {
			return true;
		}	else return false;
	}
	
	// Displays current user input stored in variables.
	public void viewInput() throws InterruptedException {
		if (directory != null) {
			System.out.println("Directory: "+directory);
		}	else System.out.println("Directory: None");
		
		if (ngSize != 0) {
			System.out.println("N-Gram Size: "+ngSize);
		}	else System.out.println("N-Gram Size: None");
		
		if (outputFile != null) {
			System.out.println("Output File: "+outputFile);
		}	else System.out.println("Output File: None");
		
		if (rangeStart != 0 && rangeEnd != 0) {
			System.out.println("Range: "+(char)rangeStart+" - "+(char)rangeEnd);
		}	else System.out.println("Range: None");
	}
		
	
}
