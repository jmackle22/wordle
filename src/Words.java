import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Words {
    private Scanner scGuesses;
    private Scanner scSolutions;
    private int solutionsLength;

    public Words() throws IOException {
        resetScanners();
        while (scSolutions.hasNext()) {
            solutionsLength++;
            scSolutions.nextLine();
        }
    }

    public boolean isValid(String guess) throws IOException {
        resetScanners();
        while (scSolutions.hasNext()) if (guess.equals(scSolutions.nextLine())) return true;
        while (scGuesses.hasNext()) if (guess.equals(scGuesses.nextLine())) return true;
        return false;
    }

    public String randomSolution() throws IOException {
        resetScanners();
        int selection = (int) (Math.random() * solutionsLength);
        for (int i = 0; i < selection; i++) scSolutions.nextLine();
        return scSolutions.nextLine();
    }

    private void resetScanners() throws IOException {
        scGuesses = new Scanner(new File("src/valid_guesses.csv"));
        scGuesses.nextLine(); // skip the column indicator
        scSolutions = new Scanner(new File("src/valid_solutions.csv"));
        scSolutions.nextLine();
    }
}
