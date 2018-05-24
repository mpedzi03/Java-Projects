//Michael Pedzimaz CPSC 21000 
import java.util.Scanner;

public class PedzimazCharacterCounter {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean stopRunning = false;                    //Boolean flag
		String answer;                                  //next few lines declaring variables
		String sentence;
		int ascii;
		char[] alphabetArray = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray(); //character array representing the alphabet
		System.out.println("This program analyzes text entered by the user and \ngives a list of letter frequencies associated with the text.\n"); 

		while(stopRunning == false) {                   //while loop repeats until user enters "n"
			System.out.println("Enter a line of text to analyze: ");
			sentence = sc.nextLine().replace(" ", "").replace(",", "").replace(".", "").toUpperCase();  //stores user line in sentence; removing spaces, commas, and periods
			char[] characterArray = sentence.toCharArray(); //converting sentence string to a character array
			int[] frequencyCount = new int[26];         //array that keeps track of letter frequencies
	
			for(int i = 0; i < characterArray.length; i++) {
				ascii = (int) characterArray[i] - 65;   //grabs the integer value of the current character and subtracts 65 to get a value within 0-25
				frequencyCount[ascii]++;                //incrementing the count of the character here
			}

			System.out.println("Here are the letter frequencies:");
			for(int i = 0; i < alphabetArray.length; i++) {     
				System.out.println(alphabetArray[i] + "   " + frequencyCount[i]); //print out current letter and its corresponding frequency
			}
			
			System.out.println("Try again (Y or N)?");
			answer = sc.nextLine().toLowerCase();
			if(answer.equalsIgnoreCase("n")) {
				stopRunning = true;                      //if user states he/she does not want to continue, flag is triggered exiting the while loop
			}
			characterArray = null;                       //values in character array set to null, for fresh new inputs ready to be entered by user  
		}
	}
}
