package ie.atu.sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;

public class Parser {
	private NGramBuilder ngBuilder;
	private Utils u;
	
	// Constructor that creates an instance of Utils and takes the previous Utils Object.
	// Sets previous Utils object as this.sg so we can access the Utils class from this class and
	// all the variables previously set.
	// Also creates an instance of ngBuilder as we will need this to build and add nGrams to our table.
	public Parser(Utils utils) {
		u = new Utils();
		this.u = utils;
		ngBuilder = new NGramBuilder(u.getNgSize(), u);
	}
	
	// This method goes through the text for each file by using a buffered reader.
	// It reads each line and if its not null, it splits each word into an array of words for each line.
	// It then passes every word to the getNGram method if the word length is larger or equal the user specified n-gram size.
	// This prevents trying to retrieve ngrams for words that are smaller than n-gram size such as the later 'a' when 
	// n-gram size 4 is entered.
	public void parseFile(String fileName) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		String line = null;
			while ((line = br.readLine()) != null) {
				String[] words = line.split("\\s+");
				for (String word : words) {
					word = word.trim().replaceAll("[^a-zA-Z]", "").toLowerCase();
					if (u.isSetRange()) {				   // If User specified a range, the word is passed
						word = u.rangeFilter(word);        // to rangeFilter method which returns new word with only characters
					}									   // desired by user.
					if (word.length() >= u.getNgSize()) {
						ngBuilder.getNGrams(word, u.getNgSize());
					}	else continue;
				}
			}	
		br.close();
	}	 
	
	// This method separates every file in the user entered directory 
	// and passes each file to the parseFile method.
	// Also, it tracks runtime of parseFile method and the entire build n-gram frequency process.
	// This measurement can be used to check the programs efficiency and compare smaller and larger file parsing speeds but
	// users should take note that this form of timing is inaccurate and doesn't account for a computers other running processes.
	
	// Once parsing directory is done and files are all processed, ngBuilder is called which passes table to a save method, the table
	// is then written to the CSV file.
	public void parseDirectory() {
		try {
			File fileDir = new File(u.getDirectory());
			String fileSeparator = FileSystems.getDefault().getSeparator();
			String[] files = fileDir.list();
			long startTime = System.currentTimeMillis();
			for (String file : files) {
				parseFile(fileDir.getAbsolutePath() + fileSeparator + file);
			}
			long endTime = System.currentTimeMillis();
			u.setTotalTime(startTime, endTime);
			ngBuilder.save(u.getOutputFile(), u.getNgSize());
		} catch (FileNotFoundException e) {
			u.fileError();
		 	return;
		} catch (Exception e) {
			u.fileError();
		 	return;
		}
	}
	
}