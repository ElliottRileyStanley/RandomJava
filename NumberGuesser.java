import java.util.Scanner;
import java.util.Random;

public class NumberGuesser {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        int answer = rand.nextInt(10)+1;
        System.out.println("Guess a number from 1 to 10");
        int guess = 0;
        int guess_count = 0;
        String input = "";
        while (guess != answer) {
            input = scanner.next();
            try {
                guess = Integer.parseInt(input);
                if (guess > answer) {
                    System.out.println(guess + " is too high");
                } else if (guess < answer) {
                    System.out.println(guess + " is too low");
                } else {
                    guess_count++;
                    break;
                }
                guess_count++;
            } catch (NumberFormatException e) {
                System.out.println("Enter a number from 1 to 10");
            }
            
        }

        System.out.println("Correct! The number was " + answer + " and you got it correct in " + guess_count + " guesses!");
        scanner.close();
    }
}