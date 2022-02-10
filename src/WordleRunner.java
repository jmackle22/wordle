import java.io.IOException;
import java.util.Scanner;

public class WordleRunner {
    public static void main(String[] args) throws IOException {
        Scanner john = new Scanner(System.in);
        Words dict = new Words();
        Wordle game = new Wordle();

        game.play();

    }
}