import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;

public class DrawFaceController {
	private Random rnd;
	private int areaWidth;
	private int areaHeight;
	private int faceWidth;
	public DrawFaceController(){
		rnd = new Random();
		areaHeight = 400;
		areaWidth = 400;
		faceWidth = 50;
	}
	public DrawFaceController(int aw, int ah){
		setAreaWidth(aw);
		setAreaHeight(ah);
		rnd = new Random();
		faceWidth = (int)(0.25 * aw);
	}
	public int getAreaWidth() {
		return areaWidth;
	}

	public void setAreaWidth(int areaWidth) {
		this.areaWidth = Math.abs(areaWidth);
	}

	public int getAreaHeight() {
		return areaHeight;
	}

	public void setAreaHeight(int areaHeight) {
		this.areaHeight = Math.abs(areaHeight);
	}

	public void drawFace(ArrayList<Face> faces, JComponent comp){
		Graphics g = comp.getGraphics();
		for (Face f : faces){
			g.drawOval(f.getX(), f.getY(), 2*f.getRadius(), 2*f.getRadius());
			g.drawOval(f.getLeftEyeX(), f.getEyeY(), f.getEyeWidth(), f.getEyeWidth());
			g.drawOval(f.getRightEyeX(), f.getEyeY(), f.getEyeWidth(), f.getEyeWidth());
			if (f.getSmileType() == 0){
				g.drawRect(f.getMouthX(), f.getMouthY(), f.getMouthWidth(), f.getMouthHeight());
			}else{
				g.drawArc(f.getMouthX(), f.getMouthY(), f.getMouthWidth(), f.getMouthHeight(), 180, f.getMouthAngle());
			}
		}
		public void addRandomFace(ArrayList<Face> faces){
			Face f = new Face(rnd.nextInt(areaWidth), rnd.nextInt(areaHeight), 25+rnd.nextInt(50),rnd.nextInt(3));
			faces.add(f);
		}
		
	}

}
