/*
Michael Pedzimaz
This program reads a file of text into memory.
It then allows the user to print that list in order
or in a new shuffled order.
*/

import java.util.Scanner;
import java.util.Random;
import java.io.File;

public class TeamManager20170419 {
    public static String[] readFileIntoList(String fname) {
        try {
            File f = new File(fname);
            Scanner filesc = new Scanner(f);
            /* because we don't know how many lines are in the file
            we don't know how big to make the resulting list.
            To fix that, first read through the file to find out how
            many lines there are. Once you know that, you can size
            the resulting list of lines properly. */
            int lineCount = 0;
            while (filesc.hasNextLine()) {
                filesc.nextLine();  // reads the line, doesn't store it
                lineCount = lineCount + 1;
            }
            filesc.close();
            String[] result = new String[lineCount];
            filesc = new Scanner(f);
            for (int i = 0; i < lineCount; i++) {
                result[i] = filesc.nextLine();
            }
            filesc.close();
            return result;
        } catch (Exception ex) {
            return null;  // null means "non-existent";
                            // we failed to produce a list of strings
        }
    }
    public static void printList(String[] list) {
        if (list == null) {
            System.out.println("No lines exist in the list.");
        } else {
            for (String str : list) {
                System.out.println(str);
            }
        }
    }
    public static boolean inRange(String test, String start, String end){
    	if (test.compareTo(start) >= 0 && test.)
    }
    public static String[] findTeamsWithinRange(String[] teams, String startTeam, String endTeam){
    	int qualifiers = 0; 
    	for (String str: teams){
    		if inRange(str, startTeam, endTeam)
    			if (inRange(str,startTeam,endTeam) == true){
    				qualifiers = qualifiers + 1;
    			}
    	}
    	String[] result = new String[qualifiers];
    	int index = 0; //the location in result where I will add the next team
    	for (String str : teams){
    		if (inRange(str, startTeam, endTeam) == true){
    			result[index] = str;
    			index = index + 1;
    		}
    	}
    	return result;
    }
    public static void showMenu() {
        System.out.println("Here are your options:");
        System.out.println("1. Show the list in original order.");
        System.out.println("2. Show the list in shuffled order.");
        System.out.println("3. Find a team.");
        System.out.println("4. Find a team while counting comparisons.");
        System.out.println("5. Find all teams in an alphabetic range.");
        System.out.println("6. Sort the list of teams.");
        System.out.println("7. Find a team using the binary search.");
        System.out.println("8. Quit");
        System.out.print("Enter the number of your choice: ");
    }
    public static String[] shuffleList(String[] original) {
        String[] result;
        boolean[] used;
        int lineCount;
        Random rnd = new Random();
        int randomIndex;
        if (original == null) {
            return null;
        } else {
            lineCount = original.length;
            result = new String[lineCount];
            used = new boolean[lineCount];
            for (int i = 0; i < lineCount; i++) {
                used[i] = false;
            }
            for (int i = 0; i < lineCount; i++) {
                randomIndex = rnd.nextInt(lineCount);
                while (used[randomIndex] == true) {
                    randomIndex = rnd.nextInt(lineCount);
                }
                result[i] = original[randomIndex];
                used[randomIndex] = true;
            }
            return result;
        }
    }
    public static boolean findTeam(String[] teams, String target){
    	/* This is a sequential search
    	 * march through the list of teams in sequence, comparing
    	 * each against the target. As soon as I find one match,
    	 * return true. If I fail to find the match before reaching
    	 * the end of the list, return false.
    	 */
    	for (String str : teams){
    		if (str.equals(target)){
    			return true;
    		}
    	}
    	return false;
    }
    public static int findTeamCountingComparisons(String[] teams, String target){
    	int count = 0;
    	for (String str : teams){
    		count = count + 1;
    		if (str.equals(target)){
    			return count;
    		}
    	}
    	return -1; // I failed to find what I was looking for
    }
    public static String[] findTeamsInRange(String[] teams, String startTeam, String endTeam){
    	//step 1: figure out how many teams 
    	int qualifiers = 0;
    	for (String str : teams){
    		if (str.compareTo(startTeam) >= 0 && str.compareTo(endTeam) <= 0){
    			qualifiers = qualifiers + 1;
    		}
    	}
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the name of the text file: ");
        String fname;
        String[] teams;
        String[] shuffled;
        String target;
        Boolean found;
        int compCount;
        int choice;
        fname = sc.nextLine();
        teams = readFileIntoList(fname);
        do {
            showMenu();
            choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                printList(teams);
            } else if (choice == 2) {
                shuffled = shuffleList(teams);
                printList(shuffled);
            } else if (choice == 3){
            	// ask the user for the team to find
            	// call findTeam function to find it
            	// report the result
            	System.out.print("What team do you want to find?");
            	target = sc.nextLine();
            	found =findTeam(teams, target);
            	if (found == true){
            		System.out.println("That team was found.");
            	}else {
            		System.out.println("That team wasn't found.");
            	}
            	
            	
            } else if (choice == 4){
            	System.out.print("What team do you want to find? ");
            	target = sc.nextLine();
            	compCount = findTeamCountingComparisons(teams, target);
            	if (compCount < 0){
            		System.out.println("That team was not found.");
            	} else {
            		System.out.println("That team was not found.");
            	}
            }
        } while(choice != 8);
        System.out.println("Thank you for using this program.");
    }
}