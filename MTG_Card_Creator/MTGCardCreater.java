import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

class Card {
	private int convertedManaCost;
	private String cardName;
	private String color;
	
	public int getCMC(){
		return convertedManaCost;
	}
	public void setCMC(int cmc){
		convertedManaCost = cmc;
	}
	
	public String getCardName(){
		return cardName;
	}
	public void setCardName(String cn){
		cardName = cn;
	}
	
	public String getColor(){
		return color;
	}
	public void setColor(String c){
		color = c;
	}
	
	public Card(){
		convertedManaCost = 0;
		cardName = "Card Name";
		color = "White";
	}
	public Card(int cmc, String cn, String c){
		setCMC(cmc);
		setCardName(cn);
		setColor(c);
	}
	@Override
	public String toString(){
		return String.format("%d %s %s", convertedManaCost, cardName, color);
	}
}

class MTGPanel extends JPanel{
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
}

class MTGFrame extends JFrame{
	private ArrayList<Card> MTGcards;
	private MTGPanel magicPanel; 
	
	public void setupUI(){
		setBounds(200,200,500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void setupMenu(){
		JMenuBar MtgMBar= new JMenuBar();
		JMenu mFile = new JMenu("File");
		JMenuItem miCreateCard = new JMenuItem("Create New Card");
		JMenuItem miDrawHand = new JMenuItem("Draw Hand");           // Might be too hard 
		JMenuItem miDrawAdditional = new JMenuItem("Draw One Card"); // Might be too hard 
		JMenu mEdit = new JMenu("Edit");
		JMenuItem miShuffle = new JMenuItem("Shuffle");
		
	}
	
	public MTGFrame(ArrayList<Card> cards){
		MTGcards = cards;
		setupUI();
}

public class MTGCardCreater{
	public static void main(String[] args){
		ArrayList<Card> MTGcards = new ArrayList<Card>();
		MTGFrame mFrame = new MTGFrame(MTGcards);
		mFrame.setVisible(true);
	}
}