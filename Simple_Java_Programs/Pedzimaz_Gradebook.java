//Michael Pedzimaz

import java.util.Scanner;
import java.io.File;
import java.io.*;

public class Pedzimaz_Gradebook {
	public static double getAverageOfScores(String[] dataLine, int pointsPossible){  //function getAverageOfScores takes in an array of strings and an int value and returns a double
		double score;
		double total = 0;
		double average;             //variable used to store and return the computed average of the scores in the a
		for (int i = 2; i < dataLine.length; i++){ //starting with 3rd element, since first 2 elements are the first and last name of the student
			if (dataLine[i].equals("x")){ //filtering out 'x' grades as 0's for missed assignments
				score =(double)0; 
				total += score;
			}else // if grade is not an x but an actual number (still a string at this point)
			score = Double.parseDouble(dataLine[i]); //turning the grade into a double value so we could perform math efficiently
			total += score;
			}
		average = (total / pointsPossible) * 100; //equation for calculating the average
		return average; //returning the double data type average
	}
	public static String getLetterGrade(double avr){ //function getLetterGrade uses the double average computed in getAverageOfScores and returns the correct letter grade to main
		if (avr >= 97){  //'if' statement cycling through possible grades per average grade bracket
			return "A+";
		} else if (avr >= 94){
			return "A";
		} else if (avr >= 90){
			return "A-";
		} else if (avr >= 87){
			return "B+";
		} else if (avr >= 84){
			return "B";
		} else if (avr >= 80){
			return "B-";
		} else if (avr >= 77){
			return "C+";
		} else if (avr >= 74){
			return "C";
		} else if (avr >= 70){
			return "C-";
		} else if (avr >= 67){
			return "D+";
		} else if (avr >= 64){
			return "D";
		} else if (avr >= 60){
			return "D-";
		} else {return "F";}
		
	}
	public static void printStars(){         //function for printing stars to look nice
		System.out.println("****************************************************");
	}
	public static void printDashes(){        //function for printing dashes to look cool
		System.out.println("----------------------------------------------------");
	}
	public static void main(String[] args){  //main function 
		Scanner sc = new Scanner(System.in); //new scanner created for gathering input by the user
		String fileName;
		printStars();
		System.out.println("         WELCOME TO GRADEBOOK VERSION 1.0");  //heading display
		printStars();
		System.out.println("      This application reads a set of grades\n	 " //explanation of program
				+ "from a text file and then prints a\n      "
				+ "report that shows the average and letter\n	     "
				+ "grade for each student.");
		printStars();
		System.out.print("\nEnter the name of the text file: ");
		fileName = sc.nextLine(); //storing the user's inputted file name in fileName
		System.out.printf("\n%-15s%-18s%-12s%s\n", "First Name", "Last Name", "Average", "Grade"); //printing and sizing the category headings
		printDashes();
		try { //try catch block designed to make sure file accessibility remains un-corrupted and smooth
			File f = new File(fileName); //variable f of data type File created 
			Scanner filesc = new Scanner(f);//filesc Scanner created to read in data from the file named by the user 
			
			String initialLine; //lines 76 - 85 are variables declared to be used throughout the program
			String dataLine;
			String firstName;
			String lastName;
			String[] maxScores;
			String[] data;
			int pointsPossible = 0;
			int newVal;
			double average;
			String letterGrade;
			
			initialLine = filesc.nextLine();    //reading the first line in the text file and storing it in the variable initialLine
			initialLine = initialLine.trim();   //trimming white spaces to the left and to the right of the first line
			maxScores = initialLine.split(" "); //splitting up the first line in the text file at the spaces and storing them in the maxScores array
			for (int i = 0; i < maxScores.length; i++){  //for loop cycling through each element in the maxScores array
				newVal = Integer.parseInt(maxScores[i]); //turning the numeric strings in maxScores into integers 
				pointsPossible = pointsPossible + newVal;//adding all of the integers to create the max possible points we will use for calculation
			}
			while (filesc.hasNextLine()){  //while loop repeats operations as long as there is another line of text in the selected file
				dataLine = filesc.nextLine();
				dataLine = dataLine.trim();
				data = dataLine.split(" ");//splitting each line at the spaces and storing in the data array
				firstName = data[0];       //first element in the array is the first name (as it is in the file) 
				lastName  = data[1];       //second element in the array is the last name (as it is in the file)
				if (dataLine.length() != 0){ // as long as there are characters on the line in the text file, we do-
					average = getAverageOfScores(data, pointsPossible); //calling getAverageScores; passing it the data string array and the int maximum pts possible while returning the average 
					letterGrade = getLetterGrade(average);              //sending the average to getLetterGrade to get the corresponding letter grade
					System.out.printf("%-15s%-18s%-12.3f%s\n", firstName, lastName, average, letterGrade); //printing with organized formatting the data report
				}
			}
			filesc.close(); //closing the file because it is good practice
			printDashes();
		}catch(Exception ex){ //if something goes wrong in the operations with the file, we get print out the following line
			System.out.println("Something went wrong when attempting to access your file. Please try again.");
			ex.printStackTrace(); //tracing the activation stack to see what went wrong in the 'try' block
		}
	}

}
