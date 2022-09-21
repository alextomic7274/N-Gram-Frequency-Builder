# N-Gram Frequency Builder
## Overview

The n-gram frequency builder is an object-oriented java application that
processes a directory of text files and outputs n-gram frequencies to a
CSV file.

The menu gives the user the choice of setting the directory, n-gram size,
output file name, character range for filtering (optional), building n-
grams, viewing entered parameters (optional), and quitting the program.
The menu will not create n-grams if the user has not filled in the
mandatory variables first. Once variables are entered, option 5 (build)
can now be selected.

## Development Summary

When build is selected, a loading bar prints to the screen, and the
process begins as the parser class methods process each file and pass
words to a getNGrams method within NGramBuilder class. 

N-Grams are
processed in a lowercase 1-character overlapping fashion e.g Hello
returns bigrams: he, el, ll, lo.
N-grams and their frequencies are then mapped to a table object where
their corresponding frequency is incremented if the n-gram already exists
within the table, otherwise, the new n-gram is added.

After each file is processed, the table is written to the .csv file
specified by the user.

Upon execution, a CSV file will appear in the src directory where you can
view n-grams, their frequencies, and their percentages in relation to
total n-grams. If you chose to filter characters, only n-grams with those
characters would be added to the output file.


## Installation

Download the java files from this repository then compile and run them on the command line.

Java 14+ required.

```bash
javac ConsoleColour.java MenuVisuals.java NGramSaver.java Runner.java Menu.java 
NGramBuilder.java
Parser.java Utils.java
```

## Usage

1. Place file(s) in the directory you want to parse and take note of the absolute file path.
2. Run the main class named Runner.
3. Choose from the menu options shown.
4. When used properly, a CSV file containing n-grams, their frequencies, and probabilities will be created in the directory housing the java files.

#### Main Menu
![alt text](https://i.imgur.com/DT7n1cZ.png)

#### Processing N-Grams From Specified Directory
![alt text](https://i.imgur.com/KECmz63.png)

#### N-Gram Report Generates Upon Execution
![alt text](https://i.imgur.com/8NJernA.png)

#### Example Output
![alt text](https://i.imgur.com/AYylIwy.png)

## Contributing
Pull requests are welcome. Please open an issue first to discuss what you would like to change.
