import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * JPanel descendant which has a paintComponent function that will actually paint faces to the screen.
 * @author Michael Pedzimaz
 *
 */
class FacePanel extends JPanel{
	/**
	 * Making our ArrayList of faces accessible to the whole class.
	 */
	private ArrayList<Face> faces;
	public FacePanel(ArrayList<Face> faces){
		this.faces = faces;
	}
	/**
	 * Calling super.paintComponent so that the backgrounds and borders are drawn properly. 
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		/**
		 * Painting the faces with a for loop sifting through each face in our array.
		 */
		for (Face f : faces){
			g.drawOval(f.getX(), f.getY(), 2*f.getRadius(), 2*f.getRadius());
			/**
			 * Drawing the randomly generated left and right eyes within preselected sections within the drawn faces. 
			 */
			g.drawOval(f.getLeftEyeX(), f.getEyeY(), f.getEyeWidth(), f.getEyeWidth());
			g.drawOval(f.getRightEyeX(), f.getEyeY(), f.getEyeWidth(), f.getEyeWidth());
			/**
			 * If smileType is 0, we are just drawing a simple rectangle for a blah face.
			 */
			if (f.getSmileType() == 0){
				g.drawRect(f.getMouthX(), f.getMouthY(), f.getMouthWidth(), f.getMouthHeight());
				/**
				 * Otherwise, our mouth will require an arc. Start angle will be 180, and our arc angle will be derived from our getMouthAngle function.
				 */
			}else{
				g.drawArc(f.getMouthX(), f.getMouthY(), f.getMouthWidth(), f.getMouthHeight(), 180, f.getMouthAngle());
			}
		}
	}
}
public class FaceFrame extends JFrame{
	/**
	 * Constructor where we set up the user interface.
	 * @param faces
	 */
	public FaceFrame(ArrayList<Face> faces){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Faces");
		setBounds(100,100,600,600);
		/**
		 * Grabbing hold of the place on the frame where we can start adding lightweight components. 
		 */
		Container c = getContentPane();
		/**
		 * BorderLayout layout manager so we have a North, East, South, West, and Center section.
		 */
		c.setLayout(new BorderLayout());
		FacePanel panFace = new FacePanel(faces);
		/**
		 * Lightweight components need a home, so we are adding them to our created Container, "c". 
		 */
		c.add(panFace, BorderLayout.CENTER);
	}

}
