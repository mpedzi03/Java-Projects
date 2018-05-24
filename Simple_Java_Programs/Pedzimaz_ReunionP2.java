//Michael Pedzimaz
//This Java program is a reunion mailer that performs a variety of display and sorting functions. 

import java.util.Scanner;
import java.io.File;
import java.util.Arrays;


public class Pedzimaz_Reunion {
	private static String[] firstname;     //lines 10 to 14 are global variables that can be used throughout this program.
	private static String[] lastname;
	private static int[] gradyear; 
	private static int capacity;
	private static int count; 

	public static void welcome(){   //this is an average welcome message function that doesn't do anything special
		System.out.print("Welcome to Reunion Planner. This program will help you organize\na reunion"
				+ "for your high school. Graduates can be grouped by decade.\nGraduates of specific"
				+ "years can also be identified so that you can\ninclude them in a special way.\n\n");
	}

	
	public static void showMenu(){ // here is my showMenu function that displays all of the options available to the user within this reunion organizer program
		System.out.println("\nWhat would you like to do?");
		System.out.println("1. See full list of alumni sorted by last name");
		System.out.println("2. See list of alumni by decade");
		System.out.println("3. See 10-year reunion alumni");
		System.out.println("4. See 25-year reunion alumni");
		System.out.println("5. See 40-year reunion alumni");
		System.out.println("6. Find alumnus by last name");
		System.out.println("7. Add an alumnus");
		System.out.println("8. Remove an alumnus");
		System.out.println("9. Quit");
		System.out.print("Please enter your choice: ");
	}
	
	public static void alumniResults(){   //this is the function that powers through and prints all of the alumni information when called
		for (int i = 0; i < count; i++){
		System.out.printf("%s, %s %d\n", lastname[i], firstname[i], gradyear[i]);
	}
}
	
	public static boolean readFile(String filename){ //here is the function that reads a file and splits it up into the 3 categories; last name, first name, and graduation year
		try{ //contained in a try-catch block in order to catch any file reading errors
			File f = new File(filename);
			Scanner fsc = new Scanner(f);
			String line;
			String[] parts;
			String name;
			String lname;
			int grdyear;
			while (fsc.hasNextLine()){
				line = fsc.nextLine();
				parts = line.split(" ");  //splitting the text lines in the file up at the spaces and saving it them in 3 separate arrays
				name = parts[0];
				lname = parts[1];
				grdyear = Integer.parseInt(parts[2]);
				firstname[count] = name;  //categorizing and incrementing the arrays per the unit count simultaneously 
				lastname[count] = lname;
				gradyear[count] = grdyear;
				count = count + 1;
			}
			fsc.close();
			return true;
		} catch (Exception ex){
			return false;
		}
	}
	
	public static void sortByLastName(){  //this function sorts the elements of the global array 'lastname[]' alphabetically, and then ensures that the 'firstname[]' and 'gradyear[]' arrays remain parallel with the sorted array
		for (int i = 0; i < count; i++)
		{
			int minIndex = i;
			for (int j = i + 1; j < count; j++)
			{
				if(lastname[j].compareTo(lastname[minIndex]) < 0)
				{
					minIndex = j;
				}
			}
			String templname = lastname[i];  
			lastname[i] = lastname[minIndex];
			lastname[minIndex] = templname;
			
			String tempfname = firstname[i];
			firstname[i] = firstname[minIndex];
			firstname[minIndex] = tempfname;
			
			int tempyear = gradyear[i];
			gradyear[i] = gradyear[minIndex];
			gradyear[minIndex] = tempyear;
			
		}
	}
	
	public static void sortByAlumniDecade(){ //this function sorts the parallel arrays by decade and displays them in a neatly organized layout for the user
		System.out.println("-- Alumni Listed by Decade --");
		System.out.println("-- 1950 - 1959 --");
		for (int i = 0; i < count; i++){
			if (gradyear[i] >= 1950 && gradyear[i] <= 1959){
				System.out.printf("%s, %s %d\n", lastname[i], firstname[i], gradyear[i]);
			}
		}
		System.out.println("\n-- 1960 - 1969 --");
		for (int i = 0; i < count; i++){
			if (gradyear[i] >= 1960 && gradyear[i] <= 1969){
				System.out.printf("%s, %s %d\n", lastname[i], firstname[i], gradyear[i]);
			}
		}
		System.out.println("\n-- 1970 - 1979 --");
		for (int i = 0; i < count; i++){
			if (gradyear[i] >= 1970 && gradyear[i] <= 1979){
				System.out.printf("%s, %s %d\n", lastname[i], firstname[i], gradyear[i]);
			}
		}
		System.out.println("\n-- 1980 - 1989 --");
		for (int i = 0; i < count; i++){
			if (gradyear[i] >= 1980 && gradyear[i] <= 1989){
				System.out.printf("%s, %s %d\n", lastname[i], firstname[i], gradyear[i]);
			}
		}
		System.out.println("\n-- 1990 - 1999 --");
		for (int i = 0; i < count; i++){
			if (gradyear[i] >= 1990 && gradyear[i] <= 1999){
				System.out.printf("%s, %s %d\n", lastname[i], firstname[i], gradyear[i]);
			}
		}
		System.out.println("\n-- 2000 - 2009 --");
		for (int i = 0; i < count; i++){
			if (gradyear[i] >= 2000 && gradyear[i] <= 2009){
				System.out.printf("%s, %s %d\n", lastname[i], firstname[i], gradyear[i]);
			}
		}
		System.out.println("\n-- 2010 - 2016 --");
		for (int i = 0; i < count; i++){
			if (gradyear[i] >= 2010 && gradyear[i] <= 2016){
				System.out.printf("%s, %s %d\n", lastname[i], firstname[i], gradyear[i]);
			}
		}
	}
	
	public static void printSpecifiedYearAlumniList(int threshold, String reunionyear){ //this function uses a concrete format to cycle through the alumni and display the requested reunion year folks
		System.out.printf("-- %s Year Reunion --\n", reunionyear);
		for (int i = 0; i < count; i++){
			if (gradyear[i] == threshold){
				System.out.printf("%s, %s %d\n", lastname[i], firstname[i], gradyear[i]);
			}
		}
	}
	
	public static void locateAlumniMatch(String userinputedname){ //function locateAlumniMatch uses a built in Array function to test each alumni for a match with what person the user is asking for (if present)
		
		for (int i = 0; i < count; i++){
			if (Arrays.asList(lastname[i]).contains(userinputedname)){
				System.out.printf("%s, %s %d\n", lastname[i], firstname[i], gradyear[i]);
			}
		}
}		
	
	public static void appendAlumni(String lname, String fname, int gyear){ // this function adds an extra alumni entered by the user into the reunion mailer program
		if (count <= capacity){ // as long as there is capacity available within the program 
			lastname[count] = lname;
			firstname[count] = fname;
			gradyear[count] = gyear;
			count = count + 1;
			System.out.println("The alumnus was added to the list.\n");
			} else {
				System.out.println("\nThe new alumnus cannot be added due to the list being full.");
		}
	}
	
	public static int positionOfAlumni(String lname){ //searching the program to see if there is a match for possible removal of an alumni suggested by the user
		for (int i = 0; i < count; i++) {
            if (lastname[i].equals(lname)) {
                return i;
            }
        }
        return -1; //returning a negative number to bypass if statement if there is no existent alumni matching the name entered by the user
	}
	
	public static void removeAlumni(String nameToRemove){ //this function calls he positionOfAlumni function to determine the location of the suggested alumni for removal
		int position = positionOfAlumni(nameToRemove);
		if (position >= 0){
			for (int i = position; i < count; i++){
				lastname[i] = lastname[i+1];  //lines 216 to 218 replaces and moves all of the alumin past the removed alumnus 
				firstname[i] = firstname[i+1];
				gradyear[i] = gradyear[i+1];
			}
			count = count - 1; //reducing the count by 1 since we removed 1 alumnus
		}
	}
	
	
	public static void main (String[] args){
		Scanner sc = new Scanner(System.in);
		welcome();
		System.out.print("Enter the name of the alumni database: ");
		String fname;
		fname = sc.nextLine();
		capacity = 105;
		count = 0;
		int choice;
		firstname = new String[capacity];
		lastname = new String[capacity];
		gradyear = new int[capacity];
		readFile(fname);
		
		
		do { //do while loop to cycle through menus options and sorting the arrays alphabetically / reiniating the String variables to "" respectively
			sortByLastName();
			int threshold = 0;
			String xyearreunion = "";
			String lastnametosearch = "";
			String startinglastname = "";
			String endinglastname = "";
			String lastnametoremove = "";
			String[] alumniWithinRange;
			showMenu();
			choice = sc.nextInt();
			sc.nextLine();
			if (choice == 1){
				System.out.println("\nHere is the full list of alumni, sorted by last name:");
				alumniResults();
			}else if (choice == 2){
				sortByAlumniDecade();
			}else if (choice == 3){
				threshold = 2007;
				xyearreunion = "Ten"; 
				printSpecifiedYearAlumniList(threshold, xyearreunion);
			}else if (choice == 4){
				threshold = 1992;
				xyearreunion = "Twenty-Five"; 
				printSpecifiedYearAlumniList(threshold, xyearreunion);
			}else if (choice == 5){
				threshold = 1977;
				xyearreunion = "Forty"; 
				printSpecifiedYearAlumniList(threshold, xyearreunion);
			}else if (choice == 6){
				System.out.print("\nEnter last name: ");
				lastnametosearch = sc.nextLine();
				locateAlumniMatch(lastnametosearch);
			}else if (choice == 7){
				String lstname = "";
				String frstname = "";
				int graduationyear = 0;
				System.out.print("Enter the alumni's first name: ");
				frstname = sc.nextLine();
				System.out.print("\nEnter the alumni's last name: ");
				lstname = sc.nextLine();
				System.out.print("\nEnter the alumni's graduation year: ");
				graduationyear = sc.nextInt();
				sc.nextLine();
				appendAlumni(lstname, frstname, graduationyear);
				sortByLastName();
				alumniResults();
			}else if (choice == 8){
				System.out.print("Enter the last name of the alumni you want to remove: ");
				lastnametoremove = sc.nextLine(); 
				removeAlumni(lastnametoremove);
				System.out.println("That alumnus was removed.");
			}else if (choice != 9){
				System.out.print("That is not a valid choice.");
			}
		} while (choice != 9);   //exiting the while loop once the user chooses the 'Quit' choice
			System.out.println("Thank you for using this program.");
			System.out.println("Have fun at the reunion.");
		
	}
	
	
	
	
	
	
	
	
	
}
