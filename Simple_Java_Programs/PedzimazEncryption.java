/*                           Michael Pedzimaz
 *This program reads a text file inputed by the user and offers three choices
 * with which to encrypt the file; railfence, row shuffle, and the one-time pad.
 * It then displays the contents of the file encrypted in the selected manner
 * and continues to cycle through the program until the user chooses to quit the program.
*/
import java.util.Scanner; //lines 7-8 import the respective libraries required by the program to function properly
import java.util.Random;
import java.io.File;

public class PedzimazEncryption { // class declaration
	public static String[] readFileIntoList(String fname){ //function readFileIntoList takes in the file name provided by the user and returns a listed version of the text file
		try { //try/catch block designed to 'catch' errors during the access of file
			File f = new File(fname);
			Scanner filesc = new Scanner(f);
			int lineCount = 0; //integer variable designed to keep track oh how many lines are in the loaded text file 
			while (filesc.hasNextLine()){ //while loop reads the lines of the file one by one
				filesc.nextLine();
				lineCount = lineCount + 1;
			} filesc.close();
			String[] result = new String[lineCount];//declaring new string array that has elements equal to the number of lines in the text file
			
			filesc = new Scanner(f);
			for (int i = 0; i < lineCount; i++){ //for loop saving each text line into its respective slot in our new array
				result[i] = filesc.nextLine();
			} 
			filesc.close(); //just being a disciplined file closer
			return result; //returning our new list of text file lines to main
			
			}catch (Exception ex){
			return null; //returning nothing if there is an error during the file access
			
		}
		
	}
	public static void railFenceEncryption(String fname){ //railFenceEncryption function takes in the file name and performs work without returning anything to main
		System.out.print("\nHere is the file encrypted using railfence:\n\n");
		try{//another try/catch block designed to contain errors whilst file access occurs
			File f = new File(fname);
			Scanner filesc = new Scanner(f);
			String origLine;
			char ch;
			String encrypted = ""; //empty string that will be filled with the respective letters that we shuffle around using the railfence technique
			while (filesc.hasNextLine()){//while loop reads lines one by one and stores them in origLine, performing the swapping of letters and printing the resulting string 
				origLine = filesc.nextLine();
				for (int i = 0; i < origLine.length(); i=i+2){ //for loop grabbing each even letter and tacking it onto the encrypted string 
				 ch = origLine.charAt(i);//pulling the targeted letter out and storing it in a char variable 
					encrypted = encrypted + ch;//tacking the letter onto the encrypted string
				}
				for (int i = 1; i < origLine.length(); i=i+2){ //same process as previous for loop, except this time we are targeting every odd letter
				 ch = origLine.charAt(i);
				 	encrypted = encrypted + ch;
				 	
				}
				System.out.printf("%s\n", encrypted); 
				encrypted = "";//resetting the encrypted string to a blank, so we could target the next line in the file and repeat our encryption process
			}
			filesc.close();
		}catch (Exception ex2){
			System.out.println("Error during request of file access in railFenceEncryption."); //catching errors during file access in this function and letting the user know
			ex2.printStackTrace();
		}
	}
	
	
	public static String[] rowShuffleEncryption(String[] original){ //function rowShuffleEncryption takes in the listed array we created and returns a shuffled version 
		String[] result;
		int lineCount;
		boolean[] usedLine; // boolean array usedLine created
		Random rnd = new Random();
		int randIndex;
		
		if (original == null){ //if nothing was returned from function readFileIntoList, we return nothing with this function
			return null;
		} else {
			lineCount = original.length; //storing the size of our original array in to the integer variable lineCount
			result = new String[lineCount];//setting the quantity of elements in our new array, result, to the amount of lines in the original array
			usedLine = new boolean[lineCount]; //size of usedLine boolean array initiated to the quantity of lines in our original array   
			for (int i = 0; i < lineCount; i++){//for loop initiates every element in our boolean array to false
				usedLine[i] = false;
			}
			for (int i = 0; i < lineCount; i++){
				randIndex = rnd.nextInt(lineCount); //integer variable randIndex set to a random number within the scope of the number of lines we have have in the text file
				while (usedLine[randIndex] == true){//while loop places the text lines randomly into respective slots within our new shuffled array
					randIndex = rnd.nextInt(lineCount);//resets randIndex variable to a new number if our boolean array element has already been used
				}
				result[i] = original[randIndex];//stores a random line not yet chosen into a sequential slot in our newly created shuffled array
				usedLine[randIndex] = true; //setting our current boolean element to true so we don't reuse the text lines
			}
			return result; //returning our newly constructed shuffled array
			
			
		}
	}
	
	public static void oneTimePadEncryption(String fname){ //this function takes in our file name and performs a one time pad encryption, then prints out the encrypted lines sequentially
		System.out.print("\nHere is the file encrypted using one-time pad:\n\n");
		try{// another try/catch block to keep file access flowing smoothly
			File f = new File(fname);
			Scanner filesc = new Scanner(f);
			Random rnd = new Random();
			String line;
			char plainCh; //lines 103-105 are declared character variables used synchronous fashion to provide one-time pad style encryption
			char keyCh;
			char encCh;
			while (filesc.hasNextLine()){ //while loop reads the text file lines sequentially and stores them in our string variable 'line'
				line = filesc.nextLine();
				String encrypted ="";//setting and resetting our encrypted line to a blank, so we could build our next encrypted text line
				for (int i = 0; i < line.length(); i++){
					plainCh = line.charAt(i);//grabbing and storing each character from the text file line in selection
					keyCh = (char)(65 + rnd.nextInt(26)); //creating a random character variable that we will XOR with our chosen line character
					encCh =(char) (plainCh ^ keyCh);//performing XOR operation on our two char variables
					encrypted = encrypted + encCh; //forming the encrypted text line 
				}
				System.out.printf("%s\n", encrypted); //printing the ecrypted text line
			} 
			filesc.close();
		} catch (Exception ex3){
			System.out.println("Error during request of file access in rowShuffleEncryption."); // catch phrase lets us know if file access was corrupted within this function
			ex3.printStackTrace();
		} 
		
	}
	
	public static void showMenu(){ //shoMenu function lists the choices to the user
		System.out.println("\nChoose encryption technique:");
		System.out.println("1. Railfence");
		System.out.println("2. Row Shuffle");
		System.out.println("3. One-Time Pad");
		System.out.println("4. Quit");
		System.out.print("Enter your choice: ");
	}
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String fname;
		String[] textLines;
		String[] shuffledLines;
		int choice; //stores integer value inputed by user to choose option from the menu
		System.out.println("******************************************");
		System.out.println("         Welcome to the Encryptor     ");
		System.out.println("******************************************\n");
		System.out.print("Enter name of file: ");//asking user for a file name and storing it our created string variable 'fname'
		fname = sc.nextLine();
		textLines = readFileIntoList(fname); //function call to the function that creates a neatly formed list of text lines 
		do { //do while loop used because we want the user to at least select a menu option once
			showMenu();
			choice = sc.nextInt();
			sc.nextLine(); //erasing end of line marker after taking choice integer from user
			if (choice == 1){//if else statements cycling through menu options
			railFenceEncryption(fname);
			} else if (choice == 2){
			shuffledLines = rowShuffleEncryption(textLines);
			System.out.print("\nHere is the file encrypted by shuffling rows:\n\n");
				for (String line : shuffledLines){
					System.out.printf("%s\n", line);
				}
			} else if (choice == 3){
				oneTimePadEncryption(fname);
			} else if (choice != 4){//if choice entered by the user is not an integer within the scope of 1-4, we let them reselect a valid menu option 
				System.out.println("That is not a valid choice.");
			}
		
		
		} while (choice != 4);
		System.out.println("\nThank you for using this program.");
	
	}
}
