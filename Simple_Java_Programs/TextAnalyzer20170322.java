//Michael Pedzimaz
//This program does some statistical analysis on text

import java.util.Scanner;

public class TextAnalyzer20170322 {
	public static int countCapitalLetters(String text) {
		int result = 0;
		char ch;
		for (int i = 0; i < text.length(); i++){
			ch = text.charAt(i);
			if (ch >= 65 && ch <= 90){
				result = result + 1;
			}
		}
		return result;
	}
	public static int countSmallLetters(String text){
		int result = 0;
		char ch;
		for (int i = 0; i < text.length(); i++){
			ch = text.charAt(i);
			if (ch >= 65 && ch <= 90){
				result = result + 1;
			}
		}
		return result;
	}
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String str;
		in capitals;
		int smalls;
		int puncts;
		System.out.print("Enter some text: ");
		str = sc.nextLine();
		capitals = countCapitalLetters(str);
		smalls = countSmallLetters(str);
//		puncts = countPunctuation(str);
		System.out.printf("%d capitals, %d smalls\n", capitals, smalls,);
		
		
	}

}
