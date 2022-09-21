package ie.atu.sw;

import java.io.FileNotFoundException;

public class NGramBuilder {
	private Object[][] table;
	private NGramSaver ngSaver;
	private int index;
	private Utils u;

	// Constructor that instantiates Utils class, the table that will store n-grams, and initiating n-gram saver class.
	public NGramBuilder(int ngSize, Utils sg) {
		u = new Utils();
		this.u = sg;
		table = new Object[(int) Math.pow(26, u.getNgSize())][2];
		ngSaver = new NGramSaver(u);
	}
	
	// Takes in a word w and n-gram size, breaks into n-grams then
	// passes to addNGram method for adding to table.
	public void getNGrams(String w, int ngSize) {
		int n = ngSize;
		String[] ngrams = new String[w.length() - n + 1];
						
		for (int i = 0; i <= w.length()-n; i++) {
				ngrams[i] = w.substring(i, i + n);
		}
		for (String t : ngrams) {
			addNGram(t);
		}
	}
	
	// Takes individual n-grams and adds them to the table by using hashCode() % table length
	// to get the index where the ngram will be placed.
	public void addNGram (String ngram) {
		u.setTotalNgrams((u.getTotalNgrams()+1));
		index = ngram.hashCode() % table.length;
		long counter = 1;
		if (table[index][0] != null) {
			counter += (Long) table[index][1];
		} 
		table[index][0] = ngram;
		table[index][1] = counter;		
	}
	
	// Takes the parameters of outputFile and n-gram size and passes them to save and getReport methods.
	public void save(String outputFile, int ngSize) throws FileNotFoundException{
		ngSaver.save(table, outputFile, ngSize);
		ngSaver.getReport(u.getTotalTime());
	}

}