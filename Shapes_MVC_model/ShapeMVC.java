// Michael Pedzimaz
// This program illustrates creating a model view controller.
// The focus is on the Model - the data

// Defining the model classes - the things that are the data we care about
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.io.PrintStream;
import java.io.File;
/**
 * Shape is the base class for a variety of geometric shapes
 * @author mpedz
 *
 */
abstract class Shape {
	private Point origin; //example of ownership
	public int getX() {
		return origin.getX();
	}
    public int getY() {
    	return origin.getY();
    }
    public void setX(int x){
		origin.setX(x);
	}
	public void setY(int y){
		origin.setY(y);
	}
	public Shape(){
		origin = new Point();
	}
	public Shape(int x, int y){
		origin = new Point(x,y);
	}
	private int x;
	private int y;
	
	
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
class Point{
	private int x;
	private int y;
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public Point(){
		x = 0;
		y = 0;
	}
	public Point(int x, int y){
		setX(x);
		setY(y);
	}
	@Override
	public String toString(){
		return String.format("%d %d", x, y);
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
class PrintShapesToPrintStreamController{
	public static void printShapesToStream(ArrayList<Shape> shapes, PrintStream ps){
		for (Shape s: shapes){
			ps.println(s);
		}
	}
}

public class ShapeMVC {
	public static void printHeading(){
		System.out.println("*******************************************");
		System.out.println("*         Random Shape Extravaganza        *");
		System.out.println("*******************************************");
	}
	public static void printInstructions(){
		System.out.println("This program generates a user-specified");
		System.out.println("number of shapes and prints their string");
		System.out.println("descriptions to the screen.");
	}
	public static void printShapes(ArrayList<Shape> shapes, PrintStream ps){
		for (Shape s: shapes){
			ps.println(s);;
		}
	}
	public static void main(String[] args){
		printHeading();
		printInstructions();
		Random rnd = new Random();
		Scanner sc = new Scanner(System.in);
		System.out.print("How many shapes do you want? ");
		int shapeCount = sc.nextInt();
		int shapeType;
		int x,y;
		int radius;
		int width, height;
		Circle c;
		Rectangle r;
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		for (int i = 0; i < shapeCount; i ++){
			shapeType = rnd.nextInt(2);
			x = -200 + rnd.nextInt(401);
			y = -200 + rnd.nextInt(401);
			if (shapeType == 0){
				radius = rnd.nextInt(100);
				c = new Circle(x,y,radius);
				shapes.add(c);
			} else if (shapeType == 1){
				height = rnd.nextInt(100);
				width = rnd.nextInt(100);
				r = new Rectangle(x,y,width,height);
				shapes.add(r);
			}
		}
		//print out the shapes
		//PrintShapesToPrintStreamController pspsc = new PrintShapesToPrintStreamController();
		//pspsc.printShapesToStream(shapes, System.out);
		PrintShapesToPrintStreamController.printShapesToStream(shapes, System.out);
		System.out.print("Enter name of file to save shapes to: ");
		sc.nextLine();
		String fname = sc.nextLine();
		try{
			File f = new File(fname);
			PrintStream stream = new PrintStream(f);
			PrintShapesToPrintStreamController.printShapesToStream(shapes, stream);
			stream.close();
		}catch (Exception ex){
			
		}
	}

}

