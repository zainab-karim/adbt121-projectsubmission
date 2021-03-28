package game;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates how high-score data can be read from a text
 * file and printed to the terminal.
 */
public class HighScoreReader {

    private String fileName;

    /**
     * Initialise a new HighScoreReader
     * @param fileName the name of the high-score file
     */
    public HighScoreReader(String fileName) {
        this.fileName = fileName;

    }

    /**
     * Read the high-score data from the high-score file and print it to
     * the terminal window.
     */
    public List<String> readScores() throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        List<String> scores = new ArrayList<>();
        try {
            System.out.println("Reading " + fileName + " ...");
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                // file is assumed to contain one name, score pair per line
                String[] tokens = line.split(",");
                String name = tokens[0];
                int score = Integer.parseInt(tokens[1]);
                scores.add("Name: \t" + name + "\t\t\t Score:\t" + score);
                line = reader.readLine();
            }
            System.out.println("...done.");
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
            return scores;
        }
    }

}