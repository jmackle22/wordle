import java.io.IOException;
import java.util.Scanner;

public class Wordle {
    public int line;
    public int wins;
    public int losses;
    private Character[][] grid;
    private String solution;
    private Words dict;
    private Scanner sc;

    public Wordle() throws IOException {
        grid = new Character[6][5];
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                grid[i][j] = '-';

        line = 0;
        wins = 0;
        losses = 0;
        dict = new Words();
        sc = new Scanner(System.in);
    }

    // game loop
    public void play() throws IOException {
        solution = dict.randomSolution();
        //System.out.println("random solution: " + solution);

        String guess = "";

        while (!guess.equals(solution) && line != 6) {
            System.out.println("Take a guess");
            displayGrid();
            guess = sc.nextLine();
            if (dict.isValid(guess)) {
                insertWord(guess);
            } else {
                System.out.println("Not a valid word!");
            }
        }

        displayGrid();

        // state win/loss and stats
        if (guess.equals(solution)) {
            wins++;
            System.out.println("Splendid");
        } else {
            losses++;
            System.out.println("The correct solution was \"" + solution + "\".");
        }
        System.out.println("Total wins: " + wins + ". Total losses: " + losses +
                "\nWin percentage: " + ((double)wins/(wins+losses))*100 + "%");
    }

    public void displayGrid() {
        for (int i = 0; i < grid.length; i++) {
            System.out.print("| ");
            Character[] letters = new Character[5];
            for (int j = 0; j < grid[0].length; j++) {
                String color = "";
                if (grid[i][j].equals(solution.charAt(j))) { // highlight in green if in correct location
                    color = "\u001B[42m";
                } else if (solution.contains(grid[i][j].toString())) { // highlight in yellow if in solution (todo: try guessing 'abaca')
                    boolean exists = false;
                    for (Character k : letters)
                        if (grid[i][j].equals(k))
                            exists = true;
                    if (!exists)
                        color = "\u001b[43m";
                }
                System.out.print(color + grid[i][j] + "\u001B[0m ");
                letters[j] = grid[i][j];
            }
            System.out.println("|");
        }
    }

    // inserts a word to the next available spot on the grid
    public void insertWord(String word) {
        for (int i = 0; i < word.length(); i++) {
            grid[line][i] = word.charAt(i);
        }
        line++;
    }
}
