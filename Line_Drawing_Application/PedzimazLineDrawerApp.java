import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
/* Author: Michael Pedzimaz			Title: LineDrawerApp		Class: CPSC 24500		Professor: Ray Klump
 * 
 * The 5 functions I would like to be graded on are:
 * -The ability to give different points (and the lines that connect them) different colors. 
 * -The ability to set the sizes of the points in the drawing and still have the lines connect the centers of each point. S, M, and L sizes through a menu item.
 * -The ability to turn off and on the drawing of lines between points, triggered by a menu item selection.
 * -The ability to change the speed of the animation between fast, medium, and slow, set through the main menu.
 * -The ability to show a temporary line segment as you move the mouse around so that you can have a visual guide as you try to figure out where to place the next point.
 */

class Point{
	private int x;
	private int y;
	private Color colr;
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public Color getColr(){
		return colr;
	}
	public void setX(int x){
		this.x = x ;
	}
	public void setY(int y){
		this.y = y;
	}
	public void setColr(Color c){
		this.colr = c;
	}
	public Point(){
		x = 0;
		y = 0;
	}
	public Point(int x, int y, Color c){
		setX(x);
		setY(y);	
		setColr(c);
	}
	
	@Override
	public String toString(){
		return String.format("%d %d", x, y);
	}
	
}
class LinePanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener{
	private ArrayList<Point> points;
	private String message;
	private Color color;
	private Color currentColor;
	private int pointSize;
	private boolean drawLines;
	private int curX, curY;
	
	
	public int getPointSize(){
		return pointSize;
	}
	public void setPointSize(int ps){
		this.pointSize = ps;
	}
	public boolean getDrawLinesBool(){
		return drawLines;
	}
	public void setDrawLinesBool(boolean choice){
		this.drawLines = choice;
	}
	public LinePanel(ArrayList<Point> points){
		this.points = points;
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		color = Color.BLACK;
		setPointSize(8);
		setDrawLinesBool(true);
		setFocusable(true);
		message = "Click the mouse to add points. ";
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(color);
		Point prevPoint = null;
		for (Point p: points){
			g.setColor(p.getColr());
			g.fillOval(p.getX(), p.getY(), getPointSize(), getPointSize());
			if (prevPoint != null){
				if (drawLines == true){
					g.drawLine(prevPoint.getX(), prevPoint.getY(), curX, curY);
					g.drawLine(prevPoint.getX(), prevPoint.getY(), p.getX()+(getPointSize()/2), p.getY()+(getPointSize()/2));
				}
			}
			prevPoint = p;
		}
		if (prevPoint != null){ 
		}
		// draw the message in the right corner of the panel
		g.setColor(color);
		g.drawString(message, 315, 400);
	}
	public void mousePressed(MouseEvent e){
		Point p = new Point(e.getX(), e.getY(),currentColor);
		points.add(p);
		repaint();
	}
	public void keyTyped(KeyEvent e){
	
	}
	public void keyPressed(KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_R){
			currentColor = Color.RED;
			repaint();
		}else if (e.getKeyCode() == KeyEvent.VK_G){
			currentColor = Color.GREEN;
			repaint();
		}else if (e.getKeyCode() == KeyEvent.VK_B){
			currentColor = Color.BLUE;
			repaint();
		}else if (e.getKeyCode() == KeyEvent.VK_K){
			currentColor = Color.BLACK;
			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
			
		}
	}
	public void keyReleased(KeyEvent e){
		
	}
	
	public void mouseClicked(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseMoved(MouseEvent e){
		requestFocusInWindow();
		message = String.format("Mouse is at (%d, %d)", e.getX(), e.getY());
		curX = e.getX();
		curY = e.getY();
		repaint();
	}
	public void mouseDragged(MouseEvent e){
		Point p = new Point(e.getX(), e.getY(), currentColor);
		points.add(p);
		repaint();
	}
}

class PointsIO{
	public boolean savePoints(ArrayList<Point> points, String fname){
		try{
			File f = new File(fname);	
			return savePoints(points, f);
		}catch (Exception ex){
			return false;
		}
	}
	public boolean savePoints(ArrayList<Point> points, File f){
		try{
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
			for (Point p: points){
				pw.println(p);
			}
			pw.close();
			return true;
		}catch(Exception ex){
			return false;
		}
	}
	public boolean loadPoints(ArrayList<Point> points, File f){
		try{
			points.clear(); 
			Scanner sc = new Scanner(f);
			String line;
			String [] parts;
			int x, y, rgb;
			Point p;
			while (sc.hasNextLine()){
				line = sc.nextLine().trim();
				if (!line.equals("")){
					parts = line.split(" ");
					x = Integer.parseInt(parts[0]);
					y = Integer.parseInt(parts[1]);
					rgb = Integer.parseInt(parts[2]);
					p = new Point(x, y);
					points.add(p);
				}
			}
			sc.close();
			return true;
		}catch(Exception ex){
			return false;
		}
	}
}
class LineFrame extends JFrame implements ActionListener{
	private ArrayList<Point> points;
	private Timer tim;
	private Random rnd;
	private LinePanel panLines;
	
	public void actionPerformed(ActionEvent e){
		int dx, dy;
		for (Point p: points){
			dx = -10 + rnd.nextInt(20);
			dy = -10 + rnd.nextInt(20);
			p.setX(p.getX() + dx);
			p.setY(p.getY() + dy);
		}
		repaint();
	}
	public void clearPoints(){
		points.clear();
		repaint();
	}
	public void setupMenu(){
		JMenuBar mbar = new JMenuBar();
		JMenu mnuFile = new JMenu("File");
		JMenu mnuSetPtSize = new JMenu("Point Size");
		JMenuItem miSmall = new JMenuItem("Small");
		miSmall.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				panLines.setPointSize(6);
			}
		});
		JMenuItem miMedium = new JMenuItem("Medium");
		miMedium.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				panLines.setPointSize(8);
			}
		});
		JMenuItem miLarge = new JMenuItem("Large");
		miLarge.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				panLines.setPointSize(12);
			}
		});
		mnuSetPtSize.add(miSmall);
		mnuSetPtSize.add(miMedium);
		mnuSetPtSize.add(miLarge);
		mnuFile.add(mnuSetPtSize);
		JMenuItem miDrawLines = new JMenuItem("Draw Lines");
		miDrawLines.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (panLines.getDrawLinesBool() == true){
					panLines.setDrawLinesBool(false);
				}else{
					panLines.setDrawLinesBool(true);
				}
			}
		});
		mnuFile.add(miDrawLines);
		JMenuItem miStartTimer = new JMenuItem("Start Timer");
		miStartTimer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				tim.start();
			}
			
		});
		mnuFile.add(miStartTimer);
		JMenuItem miStopTimer = new JMenuItem("Stop Timer");
		miStopTimer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				tim.stop();
			}
			
		});
		mnuFile.add(miStopTimer);
		JMenu mnuTimerDelay = new JMenu("Timer Delay");
		JMenuItem miTimSlow = new JMenuItem("Slow");
		JMenuItem miTimMedium = new JMenuItem("Medium");
		JMenuItem miTimFast = new JMenuItem("Fast");
		JMenuItem miLoad = new JMenuItem("Load");
		mnuTimerDelay.add(miTimSlow);
		miTimSlow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				tim.setDelay(1400);
			}
		});
		mnuTimerDelay.add(miTimMedium);
		miTimMedium.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				tim.setDelay(1000);
			}
		});
		mnuTimerDelay.add(miTimFast);
		miTimFast.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				tim.setDelay(600);
			}
		});
		miLoad.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				PointsIO pio = new PointsIO();
				JFileChooser jfc = new JFileChooser();
				if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					if (pio.loadPoints(points, jfc.getSelectedFile())){
						JOptionPane.showMessageDialog(null, "Points were read successfully.");
					}else{
						JOptionPane.showMessageDialog(null, "The points file could not be read.");
					}
					repaint();
				}
			}
			
		});
		mnuFile.add(miLoad);
		JMenuItem miSave = new JMenuItem("Save");
		miSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				PointsIO pio = new PointsIO();
/*				if (pio.savePoints(points, "C:\\Users\\mpedz\\Documents\\OOP\\LineDrawingApp\\points.txt")){
					JOptionPane.showMessageDialog(null, "Points written successfully!");
				}else{
					JOptionPane.showMessageDialog(null, "Something bad happened. ");
				}
*/
				JFileChooser jfc = new JFileChooser();
				if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
					pio.savePoints(points, jfc.getSelectedFile());
				}
			}
			
		});
		mnuFile.add(miSave);
		JMenuItem miExit = new JMenuItem("Exit");
		miExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}			
		});
		mnuFile.add(miExit);
		
		JMenu mnuEdit = new JMenu("Edit");
		JMenuItem miClear = new JMenuItem("Clear");
		miClear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				clearPoints();
			}
		});
		mnuEdit.add(miClear);
		mbar.add(mnuFile);
		mbar.add(mnuEdit);
		mbar.add(mnuTimerDelay);
		setJMenuBar(mbar);
	}
	public void setupUI(){
		setBounds(100,100,500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Line Drawing App");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		JPanel panSouth = new JPanel();
		JButton btnClr = new JButton("Clear");
		btnClr.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				clearPoints();
			}
		});
		panSouth.add(btnClr);
		c.add(panSouth, BorderLayout.SOUTH);
		panLines = new LinePanel(points);
		c.add(panLines, BorderLayout.CENTER);
		setupMenu();
	}
	public LineFrame(ArrayList<Point> points){
		this.points = points;
		tim = new Timer(1000, this);
		rnd = new Random();
		setupUI();
		
	}
}
public class PedzimazLineDrawerApp {
	public static void main(String[] args){
		ArrayList<Point> points = new ArrayList<Point>();
		LineFrame frmLine = new LineFrame(points);	
		frmLine.setVisible(true);
	}

}
