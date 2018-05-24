// Michael Pedzimaz
// This program illustrates creating a model view controller.
// The focus is on the Model - the data

// Defining the model classes - the things that are the data we care about
/**
 * Shape is the base class for a variety of geometric shapes
 * @author mpedz
 *
 */
abstract class Shape {
	/**
	 * x represents the x-coordinate of the shape's origin
	 */
	private int x;
	/**
	 * y represents the y-coordinate of the shape's origin
	 */
	private int y;
	public void setX(int tx){
		this.x = x;
	}
	public int getX() {
		return x;
	}
	public void setY(int y){
		this.y = y;
	}
	public int getY() {
		return y;
	}
	public Shape(){
		x = 0;
		y = 0;
	}
	public Shape(int x, int y){
		setX(x);
		setY(x);
	}
	public abstract double findArea();
	public abstract double findPerim();
	/**
	 * A function that some subclasses will override to describe what they are.
	 * @return a word that describes what this particular shape is
	 */
	public String getShapeType(){
		return "Shape";
	}
	@Override
	public String toString(){
		return String.format("%s %d %d", getShapeType(), x, y);
	}
}
class Rectangle extends Shape{
	private int width;
	private int height;
	public int getWidth(){
		return width;
	}
	public void setWidth(int w){
		if (w < 0){
			width = 0;
		}else {
			width = w;
		}
	}
	public int getHeight(){
		return height;
	}
	public int setHeight(int h){
		if (h < 0){
			height = 0;
		}else {
			height = h;
		}
		return height;
	}
	public Rectangle(){
		//remember - default ancestor constructor is called automatically
		height = 0;
		width = 0;
	}
	public Rectangle(int x, int y, int w, int h){
		super(x,y);
		setWidth(w);
		setHeight(h);
	}
	@Override
	public double findArea(){
		return height * width;
	}
	@Override
	public double findPerim(){
		return 2 *(height + width);
	}
	@Override
	public String getShapeType(){
		return "Rectangle";
	}
	public String toString(){
		return String.format("%s %d %d", super.toString(), width, height);
	}
}

class Circle extends Shape {
	private int radius;
	public int getRadius(){
		return radius;
	}
	public void setRadius(int rad){
		if (rad < 0){
			radius = 0;
		}else{
			radius = rad;
		}
	}
	public Circle(){
		super(); //even if I left this out, it would be called automatically
		radius = 0;
	}

	public Circle(int x, int y, int rad){
		super(x,y); //calls superclass's 2-int constructor to set the x and the y
		setRadius(rad);
	}
	@Override
	public double findArea(){
		return Math.PI * radius * radius;
	}
	@Override
	public double findPerim(){
		return 2 * Math.PI * radius;
	}
	@Override 
	public String getShapeType(){
		return "Circle";
	}
	@Override
	public String toString(){
	//String desc = getShapeType() + " " + getX() + " " + getY() + " " + radius;
	//return desc;
		return String.format("%s %d" , super.toString(), radius);
	}
}
public class ShapeMVC {
	public static void main(String[] args){
		System.out.println("Hi");
		Circle defCircle = new Circle();
		Circle customCircle = new Circle(5,10,15);
		System.out.println(defCircle);
		System.out.println(customCircle);
		System.out.printf("Circle 1 has a circumference of %.2f\n", defCircle.findPerim());
		System.out.printf("Circle 2 has an area of %.2f.\n", customCircle.findArea());
		Rectangle defRect = new Rectangle();
		Rectangle custRect = new Rectangle(5,10,15,20);
		System.out.println(defRect);
		System.out.println(custRect);
		defRect.setHeight(33);
		defRect.setWidth(17);
		double area = defRect.findArea();
		System.out.println("Area of the defRect rectangle is " + area + ".");
		
	}

}

