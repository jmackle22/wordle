import java.io.IOException;
import java.util.Scanner;

public class WordleRunner {
    public static void main(String[] args) throws IOException {
        Scanner john = new Scanner(System.in);
        Words dict = new Words();
        Wordle game = new Wordle();

        boolean quit = false;
        while (!quit) {
            game.play();
            System.out.println("Would you like to play again? (y/n)");
            if (john.nextLine().equals("n")) quit = true;
        }

        System.out.println("\"Thanks for playing wordle boginga\" - William Thomas Christening");
    }
}