import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int attempts = 0;
        int totalAttempts = 0;
        int rounds = 0;

        System.out.println("Welcome to the Number Game!");

        do {
            rounds++;
            System.out.println("\nRound " + rounds);
            int secretNumber = random.nextInt(100) + 1;
            attempts = playGame(scanner, secretNumber);
            totalAttempts += attempts;
            System.out.println("Round " + rounds + " completed in " + attempts + " attempts.");
            System.out.println("Total attempts so far: " + totalAttempts);
        } while (playAgain(scanner));

        System.out.println("\nThank you for playing the Number Game!");
        System.out.println("Your final score is: " + totalAttempts);
    }

    public static int playGame(Scanner scanner, int secretNumber) {
        int attempts = 0;
        while (true) {
            System.out.print("Enter your guess (1-100): ");
            int guess = scanner.nextInt();
            attempts++;

            if (guess == secretNumber) {
                System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                break;
            } else if (guess < secretNumber) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Too high! Try again.");
            }
        }
        return attempts;
    }

    public static boolean playAgain(Scanner scanner) {
        while (true) {
            System.out.print("Do you want to play again? (yes/no): ");
            String choice = scanner.next().toLowerCase();

            if (choice.equals("yes")) {
                return true;
            } else if (choice.equals("no")) {
                return false;
            } else {
                System.out.println("Invalid choice. Please enter 'yes' or 'no'.");
            }
        }
    }
}
