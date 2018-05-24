/**
 * All of our faces are going to be circles in essence, 
 * so we will extend this class, "Face", from our already pre-constructed class "Circle"
 * @author Michael Pedzimaz
 *
 */
public class Face extends Circle{
	/**
	 * Our smileType variable will designate the type of emotion
	 * that our constructed face will present in display.
	 * 0 - meh 1 - smile, 2 - frown 
	 */
	private int smileType;
	/**
	 * Completely private int variable just for convenient use within our "Face" class.
	 */
	private int faceDiam;
	public int getSmileType(){
		return smileType;
	}
	/** 
	 * We want to provide some sort of range checking in 
	 * case the end user provides a non-correct value.
	 * @param st
	 */
	public void setSmileType(int st){
		if (st < 0){
			smileType = 0;
		} else if (st > 2){
			smileType = 2;
		} else {
			smileType = st;
		}
	}
	/**
	 * There is an invisible call in our default constructor 
	 * to the super class's, "Cirle", default constructor 
	 * which sets x, y, and radius automatically to 0. 
	 */
	public Face(){
		smileType = 0;
		faceDiam = 0;
	}
	/**
	 * We are taking information into this non-default constructor,
	 * but are also calling the super class, "Circle", in order to automatically set:
	 * @param x
	 * @param y
	 * @param r
	 * Also want to provide some range checking by calling setSmileType here.
	 * @param st
	 *
	 */
	public Face(int x, int y, int r, int st){
		super (x,y,r);
		setSmileType(st);
		faceDiam = 2* r;
	}
	@Override
	public String getShapeType(){
		return "Face";
	}
	/** 
	 * We use this function to return the string version of our selected (or randomly generated) smile type.
	 * @return
	 */
	public String getSmileTypeAsString(){
		if (smileType == 0){
			return "meh";
		} else if (smileType == 1){
			return "smile";
		} else {
			return "frown";
		}
	}
	/**
	 * 	Using "Circle" class's version of toString while also concatenating a returned smile type.
	 */
	@Override
	public String toString(){
		return String.format("%s %s", super.toString(), getSmileTypeAsString());
	}
	/**
	 * Helper functions for drawing the eyes of the randomly created faces. 
	 * Results are typecasted as ints, so proper calculations can occur.
	 * @return
	 */
	public int getLeftEyeX(){
		return (int)(getX() + 0.1 * faceDiam);
	}
	public int getRightEyeX(){
		return (int)(getX() + 0.7 * faceDiam);
	}
	public int getEyeY(){
		return (int)(getY() + 0.2 * faceDiam);
	}
	public int getEyeWidth(){
		return (int)(0.2 * faceDiam);
	}
	/**
	 * Helper functions for drawing the mouths of the randomly created faces. 
	 * @return
	 */
	public int getMouthX(){
		return (int)(getX() + 0.1 * faceDiam);
	}
	public int getMouthWidth(){
		return (int)(0.8 * faceDiam);
	}
	public int getMouthHeight(){
		return (int)(0.2 * faceDiam);
	}
	public int getMouthY(){
		return (int)(getY() + 0.7 * faceDiam);
	}
	/**
	 * Specifying the bounding angle, the starting angle, and finally the sweep angle. 
	 * @return
	 */
	public int getMouthAngle(){
		if (smileType == 0){
			return 0;
		} else if (smileType == 1){
			return 180;
		} else {
			return -180;
		}
	}
}	
