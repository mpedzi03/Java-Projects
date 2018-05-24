import java.util.Scanner;
import java.util.Random;
		
public class RandomNoGuessGame {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random rand = new Random();
		int randomNumber = rand.nextInt(20) + 1;
		int numberOfGuesses = 0;
		int guessedNumber;
		boolean guessedCorrectly = false; 
		
		while (guessedCorrectly == false) {
		System.out.print("Guess a number between 1 and 20: ");
		guessedNumber = sc.nextInt();
		numberOfGuesses++;
		
		if (guessedNumber == randomNumber) {
			guessedCorrectly = true;
		} else if (guessedNumber < randomNumber) {
			System.out.println("Your guess is too low!\n");
		} else if (guessedNumber > randomNumber) {
			System.out.println("Your guess is too high!\n");
		}
		
		}
		System.out.printf("You have guessed the number %d correctly!\n", randomNumber);
		System.out.printf("It took you %d times to guess the right number. Not bad!", numberOfGuesses);
	}
}
