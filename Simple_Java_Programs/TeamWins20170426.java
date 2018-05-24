//Michael Pedzimaz
//This program manages data related to a baseball season.

import java.util.Scanner;
import java.io.File;

public class TeamWins20170426 {
	private static String[] names; 
	private static int[] wins;
	private static int capacity; //number of teams I can store
	private static int count;    //number of teams I am storing
	
	public static boolean readFile(String fname) {
		try{
			File f = new File(fname);
			Scanner fsc = new Scanner(f);
			String line;
			String[] parts;
			String teamName;
			int winCount;
			while(fsc.hasNextLine()){
				line = fsc.nextLine();
				parts = line.split(" ");
				teamName = parts[0];
				winCount = Integer.parseInt(parts[1]);
				names[count] = teamName;
				wins[count] = winCount;
				count = count + 1;
			}
			fsc.close();
			
			return true; 
		}catch(Exception ex){
			return false;
		}
	}
	
	public static void printTeams(){
		for (int i = 0; i < count; i++){
			System.out.printf("%s %d\n", names[i], wins[i]);
		}
	}
	
	public static void printTeamsWithXWins(int threshold){
		for (int i = 0; i < count; i++){
			if (wins[i] >= threshold){
				System.out.println(names[i]);
			}
		}
	}
	
	public static void printTeamsWithinWinRange(int min, int max){
		for (int i = 0; i < count; i++){
			if (wins[i] >= min && wins[i] <= max){
				System.out.printf("%s %d\n", names[i], wins[i]);
			}
		}
	}
	
	public static void sortByWins() {
		int minWins;
		int minPos;
		int temp;
		String tempName;
		for (int i = 0; i < count; i++){
			minWins = wins[i];
			minPos = i;
			for (int j = i+1; j < count; j++){
				if (wins[j] < minWins){
					minWins = wins[j];
					minPos = j;
				}
			}
			temp = wins[i];
			wins[i] = wins[minPos];
			wins[minPos] = temp;
			tempName = names[i];
			names[i] = names[minPos];
			names[minPos] = tempName;
		}
	}
	
	public static void appendTeam(String teamName, int winCount){
		if(isFull() == false){
		names[count] = teamName;
		wins[count] = winCount;
		count = count + 1;
		}	
	}
	
	public static void removeLast(){
		count = count - 1;
	}
	public static int findPositionOfTeam(String name){
		for (int i = 0; i < count; i++){
			if (names[i].equals(name)){
				return i;
			}
		}
		return -1;
		
	}
	public static void removeTeam(String name){
		int pos = findPositionOfTeam(name);
		if (pos >= 0){
			//actually remove the value of position pos
			for (int i = pos; i < count-1; i ++){
				names[i] = names[i+1];
				wins[i] = wins[i+1];
			}
			count = count - 1; 
		}
	}
	public static void insertAt(String name, int winCount, int pos){
		if (isFull() == false){
		if (pos >= count){
			appendTeam(name, winCount);
		} else{
		for (int i = count; i > pos; i --){
			names[i] = names[i-1];
			wins[i] = wins[i-1];
		}
		names[pos] = name;
		wins[pos] = winCount;
		count = count + 1;
	}
		}
	}
	public static boolean isFull(){
		if (count == capacity){
			return true;
		} else {
			return false;
		}
	}
	public static void resize(int newSize){
		String[] resizedNames = new String[newSize];
		int[] resizedWins = new int[newSize];
		//copy the values from the old list to the new list
		for (int i = 0; i < count; i++){
			resizedNames[i] = names[i];
			resizedWins[i] = wins[i];
			
		}
		capacity = newSize;
		
	}
	public static void main (String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the name of the file: ");
		String fname;
		fname = sc.nextLine();
		capacity = 20;           //store up to 20 teams
		count = 0;
		names = new String[capacity];
		wins = new int[capacity];
		readFile(fname);
		printTeams();
		int winCutoff;
		System.out.print("Enter the win threshold: ");
		winCutoff = sc.nextInt();
		sc.nextLine();
		printTeamsWithXWins(winCutoff);
		System.out.print("Enter min and max win cutoffs: ");
		int minWinCount, maxWinCount;
		minWinCount = sc.nextInt();
		maxWinCount = sc.nextInt();
		sc.nextLine();
		printTeamsWithinWinRange(minWinCount, maxWinCount);
		System.out.println("Here is the list sorted by wins: ");
		sortByWins();
		printTeams();
		System.out.print("Enter a new team and its wins: ");
		String newTeam;
		int newWins;
		newTeam = sc.next();
		newWins = sc.nextInt();
		sc.nextLine();
		appendTeam(newTeam, newWins);
		sortByWins();
		printTeams();
		System.out.println("Now I will remove the last element in the list: ");
		removeLast();
		printTeams();
		System.out.print("Enter the name of the team to remove: ");
		String toRemove;
		toRemove = sc.nextLine();
		removeTeam(toRemove);
		printTeams();
		System.out.print("Enter team, wins, and position: ");
		String nameToAdd;
		int winsToAdd;
		int where;
		nameToAdd = sc.next();
		winsToAdd = sc.nextInt();
		where = sc.nextInt();
		sc.nextLine();
		insertAt(nameToAdd, winsToAdd, where);
		printTeams();
	}

}
