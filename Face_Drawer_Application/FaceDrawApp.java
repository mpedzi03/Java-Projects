import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
/**
 * This is our main class, FaceDrawApp. It utilizes a class called "Face",
 * which is derived from a super class called "Circle", 
 * which ultimately descends from it's abstract super class "Shape". 
 * We are also utilizing a JFrame class called "FaceFrame" as well as a JPanel class called FacePanel.
 * 
 * @author Michael Pedzimaz
 *
 */
public class FaceDrawApp {
	/**
	 * Creating a random number generator as a private data member so we don't have to keep recreating it within this program.
	 */
	private static Random rnd;
	/**
	 * Function that incorporates a random number generator in order to return a face that is randomly generated
	 * At least 50 pixels wide with a random smileType.
	 * @return
	 */
	public static Face randomFace(){
		return new Face(rnd.nextInt(400), rnd.nextInt(400), 50 + rnd.nextInt(50), rnd.nextInt(3));
	}
	/**
	 * Function printFaces takes in an ArrayList of faces and prints them out to the command prompt. 
	 * @param faces
	 */
	public static void printFaces(ArrayList<Face> faces){
		for (Face f: faces){
			System.out.println(f);
		}
	}
	/**
	 * Main function where we will create a bunch of faces
	 * print them to a screen & draw them on a frame.
	 */
	public static void main(String[] args){
		/**
		 * Initializing a new random object. Now we can use it within this class. 
		 */
		rnd = new Random();
		/**
		 * Here we are creating a home for the faces we will be creating.
		 */
		ArrayList<Face> faces = new ArrayList<Face>();
		/**
		 * Converting the string entered by the user into an integer value using Integer.parseInt.
		 */
		int faceCount = Integer.parseInt(JOptionPane.showInputDialog("How many faces do you want? "));
		/** 
		 * Here we are generating the amount of faces the end user requested. 
		 */
		for (int i = 0; i < faceCount; i++){
			faces.add(randomFace());
		}
		printFaces(faces);
		FaceFrame frm = new FaceFrame(faces);
		frm.setVisible(true);
	}

}
