import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * This is a Slot Machine program that takes in wagers of .50, .25, and .10 
 * with a win condition of matching the three shapes (vary between Circle or Square)
 * and also matching colors (vary between Red, Green, or Blue.)
 * You lose if you hit a balance of $0.00 or below.
 * @author Michael Pedzimaz
 *
 */
/**
 * Class InfoPanel extends JPanel and is located in our northern section.  
 * It provides a welcome message, balance updates, and a message indicating when the player has lost.
 * It has a string (message) passed in through its setMessage function that provides updates as 
 * the user presses the buttons. 
 */
class InfoPanel extends JPanel{
	private String message;
	public void setMessage(String msg){
		message = msg;
	}
	public InfoPanel(){
		setPreferredSize(new Dimension(500,100));
		message = "Welcome";
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawString(message, 10, 50);
	}
}

class SlotPanel extends JPanel{
	private Color col;
	private int colorCode;
	private int shapeType;
	public int getColorCode() {
		return colorCode;
	}
	public int getShapeType() {
		return shapeType;
	}
	
	private Random rnd;
	
	public SlotPanel(){
		rnd = new Random();
		spin();
		
	}
	public void spin(){
		int colorCode = rnd.nextInt(3);
		if (colorCode == 0){
			col = Color.RED;
		} else if (colorCode == 1){
			col = Color.GREEN;
		} else {
			col = Color.BLUE;
		}
		shapeType = rnd.nextInt(2);
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(col);
		if (shapeType == 0){
			g.fillOval(20, 20, 100, 100);
		}else {
			g.fillRect(20, 20, 100, 100);
		}
	}
}
/** 
 * MyFrame class extends JFrame and houses all of the lightweight components.
 * A BorderLayout layout manager is used to separate the North (InfoPanel),
 * South(wager Buttons), and Center( 1 by 3 GridLayout). 
 * It contains three buttons that each incorporates it's own ActionListener,
 * which performs the required operations for that button once the end user
 * clicks on the button.
 */
class MyFrame extends JFrame{
	private double balance;
	public MyFrame(){
		balance = 1.0;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100,100,500,500);
		setTitle("Slot Machine");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		JPanel panSouth = new JPanel();	
		panSouth.setLayout(new FlowLayout());
		JPanel panCenter = new JPanel();
		panCenter.setLayout(new GridLayout(1,3));
		SlotPanel cpLeft = new SlotPanel(); 
		SlotPanel cpMid = new SlotPanel();
		SlotPanel cpRight = new SlotPanel();		
		panCenter.add(cpLeft);
		panCenter.add(cpMid);
		panCenter.add(cpRight);
		c.add(panCenter, BorderLayout.CENTER);
		InfoPanel panInfo = new InfoPanel();
		c.add(panInfo, BorderLayout.NORTH);
		
		
		JButton btnBetMax = new JButton("Bet Max");
		btnBetMax.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){						
						cpLeft.spin();
						cpMid.spin();
						cpRight.spin();
						if ((cpLeft.getColorCode() == cpMid.getColorCode() & cpLeft.getColorCode() == cpRight.getColorCode()) 
						& (cpLeft.getShapeType() == cpMid.getShapeType() & cpLeft.getShapeType() == cpRight.getShapeType())){
							balance = balance + .5;
							panInfo.setMessage("Current Balance:"+ balance);
							
						} else{
							balance = balance - .5;
							if (balance < 0){
								panInfo.setMessage("You have no money left. Game Over.");
								setEnabled(false);
							} else{
								panInfo.setMessage("Current Balance:" + balance);
							}
						}
						repaint();
					}			
				}				
		);
		panSouth.add(btnBetMax); 
		JButton btnBetMin = new JButton("Bet Min");
		btnBetMin.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						cpLeft.spin();
						cpMid.spin();
						cpRight.spin();
						if ((cpLeft.getColorCode() == cpMid.getColorCode() && cpLeft.getColorCode() == cpRight.getColorCode()) 
						&& (cpLeft.getShapeType() == cpMid.getShapeType() && cpLeft.getShapeType() == cpRight.getShapeType())){
							balance = balance + .1;
							panInfo.setMessage("Current Balance:"+ balance);
							
						} else{
							balance = balance - .1;
							if (balance < 0){
								panInfo.setMessage("You have no money left. Game Over.");
								setEnabled(false);
							} else{
								panInfo.setMessage("Current Balance:" + balance);
							}
						}
						repaint();
					}	
				}			
		);
		panSouth.add(btnBetMin);
		JButton btnSpin = new JButton("Spin");
		btnSpin.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						cpLeft.spin();
						cpMid.spin();
						cpRight.spin();
						if ((cpLeft.getColorCode() == cpMid.getColorCode() && cpLeft.getColorCode() == cpRight.getColorCode()) 
						&& (cpLeft.getShapeType() == cpMid.getShapeType() && cpLeft.getShapeType() == cpRight.getShapeType())){
							balance = balance + .25;
							panInfo.setMessage("Current Balance:"+ balance);			
						} else{
							balance = balance - .25;
							if (balance < 0){
								panInfo.setMessage("You have no money left. Game Over.");
								setEnabled(false);
							} else{
								panInfo.setMessage("Current Balance:" + balance);
							}
						}
						repaint();
					}					
				}
		);
		panSouth.add(btnSpin);
		c.add(panSouth, BorderLayout.SOUTH);
	}
}
/**
 * 
 * Main class that implements the JFrame and it's components.
 * We set it to visible so we can run and interact with the program.
 */
public class PedzimazSlotMachine {
	public static void main(String[] args) {
		MyFrame frm = new MyFrame();
		frm.setVisible(true);
	}
}
