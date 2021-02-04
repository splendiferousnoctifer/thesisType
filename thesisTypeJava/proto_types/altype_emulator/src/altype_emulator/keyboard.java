package altype_emulator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * keyboard class
 * Implements Key Listener for Key Events
 * 
 * Proto-Type Final Version
 * @author Samuel Ebner
 */
public class keyboard  implements KeyListener, collection {
	final int ONE = KeyEvent.VK_H;
	final int TWO = KeyEvent.VK_T;
	final int THREE = KeyEvent.VK_R;
	final int FOUR = KeyEvent.VK_S;
	
	JLabel text_output = new JLabel("Please Enter your Text");
	JLabel instructions = new JLabel();
	
	String text= "";
	int first_key = 0;

	ArrayList<Integer> inputCombination = new ArrayList<Integer>();
	
	
	/**
	 * constructor
	 */
    public keyboard(){
    	setup();
    	
    }
	
	
	/**
	 * key event that determines the depressed the combination
	 * for any kind of key
	 */
	public void keyPressed(KeyEvent e) {
		
		//first key that is pressed
		first_key = e.getKeyCode();
		
		//which key is pressed
		switch(e.getKeyCode()){
		case ONE:
			inputCombination.add(1);
			break;
		case TWO:
			inputCombination.add(2);
			break;
		case THREE:
			inputCombination.add(3);
			break;
		case FOUR:
			inputCombination.add(4);
			break;
		}	
		
		removeDuplicates(inputCombination);
		
		//guide is set
		instructions.setText("<html><p style=\"width:300px;text-align:center\">"+ guide.getGuide(inputCombination) +"</p></html>");


	}
	

	/**
	 * key event that determines the character by checking the combination
	 * additional function is to delete
	 */
	public void keyReleased(KeyEvent e) {
		//esc exit
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			System.out.println("bye!");
			System.exit(0);
		}    
		
		//if the first depressed key is let go
		if(e.getKeyCode() == first_key) {
			//all four pressed is delete
			if(inputCombination.size() >= 4 && text.length() > 0 ) {
				text = text.substring(0, text.length() -1);
			} else {
				char letter = getLetter(inputCombination);
				
				//only print letters and whitespaces
				if (Character.isLetter(letter) || Character.isWhitespace(letter) || letter == '?' || letter == '.' || letter == ',') {
					text += letter;
				}				
			}
			
			//combination is cleared
			inputCombination.clear();
			
			//reset guide
			instructions.setText("<html><p style=\"width:300px;text-align:center\">"+ guide.startGuide() +"</p></html>");
			
			//set output text
			text_output.setText("<html><p style=\"width:250px\">"+ text +"</p></html>");
		}
	}


     
    
    /**
     * key Event
     * doesn't do anything yet
     * for unicode keys
     */
	public void keyTyped(KeyEvent e) {
		
	}
	
	
    /**
     * creates Frame and labels
     * includes font and colour settings + guidance image
     */
    private void setup() {
    	JFrame window = new JFrame("Proto-type 1"); 
    	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	window.setLayout(new BorderLayout());
    	
    	//window settings
    	window.setPreferredSize(new Dimension(400, 500));
    	window.getContentPane().setBackground(Color.black);
    	
    	//label text settings
    	text_output.setHorizontalAlignment(JLabel.CENTER);
    	text_output.setForeground(Color.white);
    	text_output.setFont(new Font("Courier New", Font.PLAIN, 18));
    	text_output.setVerticalAlignment(SwingConstants.CENTER);
    
    	//label guidance image
    	
    	instructions.setForeground(Color.white);
    	instructions.setFont(new Font("Courier New", Font.PLAIN, 12));
    	instructions.setVerticalAlignment(SwingConstants.CENTER);
		instructions.setText("<html><p style=\"width:300px;text-align:center\">"+ guide.startGuide() +"</p></html>");

    	
    	//placement of labels
    	Container c = window.getContentPane();
        c.setLayout(new GridLayout(2,1));
        window.add(text_output);
    	
    	//add keylistener
    	window.addKeyListener(this);
    	window.add(instructions);
    	
    	window.pack();
    	window.setVisible(true);
    	window.setLocationRelativeTo(null);
    }





 
}