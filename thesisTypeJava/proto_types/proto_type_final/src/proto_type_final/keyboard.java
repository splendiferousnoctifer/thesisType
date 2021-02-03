package proto_type_final;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
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
public class keyboard  implements KeyListener {
	JLabel text_output = new JLabel("Please Enter your Text");
	JLabel instructions = new JLabel();

	
	final int ONE = KeyEvent.VK_H;
	final int TWO = KeyEvent.VK_T;
	final int THREE = KeyEvent.VK_R;
	final int FOUR = KeyEvent.VK_S;
	
	int first_key = 0;

	ArrayList<Integer> inputCombination = new ArrayList<Integer>();
	
	String text= "";
	
	
	char[][][] alphabet = {
			{
				{ 0, 'a','j','s',' '},
				{ 0 , 0 ,'b','e','h'},
				{ 0 ,'k', 0 ,'n','q'},
				{ 0 ,'t','w', 0 ,'z'},
				{ 0 ,'.',',','?', 0 }
			},

			{
				{ 0 , 0 , 0 , 0 , 0 },
				{ 0 , 0 , 0 , 0 , 0 },
				{ 0 , 0 , 0 ,'c','d'},
				{ 0 , 0 ,'f', 0 ,'g'},
				{ 0 , 0 , 0 ,'i', 0 } //TODO change i to 2
			},
			
			{
				{ 0 , 0 , 0 , 0 , 0 },
				{ 0 , 0 , 0 ,'l','m'},
				{ 0 , 0 , 0 , 0 , 0 },
				{ 0 ,'o', 0 , 0 ,'p'},
				{ 0 ,'r', 0 , 0 , 0 }
			},
			
			{
				{ 0 , 0 , 0 , 0 , 0 },
				{ 0 , 0 ,'u', 0 ,'v'},
				{ 0 ,'x', 0 , 0 ,'y'},
				{ 0 , 0 , 0 , 0 , 0 },
				{ 0 , 0 , 0 , 0 , 0 }
			},
			
			{
				{ 0 , 0 , 0 , 0 , 0 },
				{ 0 , 0 , 0 , 0 , 0 },
				{ 0 , 0 , 0 , 0 , 0 },
				{ 0 , 0 , 0 , 0 , 0 },
				{ 0 , 0 , 0 , 0 , 0 }
			}
		};
	
	//TODO guide
	String instr = "+-------+-------+-------+ +-----+<br>" + 
			"|&nbsp;";
	
	
	/**
	 * constructor
	 */
    public keyboard(){
    	setup();
    	
    }
	
	
	/**
	 * key event,
	 * determines the letter for output by counting how often a key is pressed within a time frame
	 * after pause expires letter is set to 'word'
	 * for any kind of key
	 */
	public void keyPressed(KeyEvent e) {
		
		first_key = e.getKeyCode();
		
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

		instructions.setText("<html><p style=\"width:300px;text-align:center\">"+ instr +"</p></html>");

	}
	 
	 
	/**
	 * Key event
	 * checks for ESC and closes game
	 * checks for DEL and deletes previous character
	 */
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			System.out.println("bye!");
			System.exit(0);
		}    
		
		if(e.getKeyCode() == first_key) {
//			removeDuplicates(inputCombination);
			
			//all four pressed is delete
			if(inputCombination.size() >= 4 && text.length() > 0 ) {
				text = text.substring(0, text.length() -1);
				inputCombination.clear();

			} else {
				char letter = getLetter(inputCombination);
				
				//only print letters and whitespaces
				if (Character.isLetter(letter) || Character.isWhitespace(letter) || letter == '?' || letter == '.' || letter == ',') {
					text += letter;
				}
			
				inputCombination.clear();
			
			}
			
			//set output text
			text_output.setText("<html><p style=\"width:250px\">"+ text +"</p></html>");
		}
		
	}
	
	
	/**
	 * checks for combination and assigns letter
	 * @param list
	 * @return
	 */
	char getLetter(ArrayList<Integer> list) {
		int[] combinationArray = {0,0,0};
		if (list.size() >= 4)
			list.subList(3, list.size()).clear();
		
		for (int i = 2-(list.size()-1); i <3 ; i++) {
			combinationArray[i] = list.get(0).intValue();
			list.remove(0);
		}
		
		char letter = alphabet[combinationArray[0]][combinationArray[1]][combinationArray[2]];
		return letter;
	}
	
	
	/**
	 * removes Duplicates of ArrayList
	 * @param <T>
	 * @param list
	 * @return
	 */
	<T> ArrayList<T> removeDuplicates(ArrayList<T> list) 
    { 
  
        // Create a new LinkedHashSet 
        Set<T> set = new LinkedHashSet<>(); 
  
        // Add the elements to set 
        set.addAll(list); 
  
        // Clear the list 
        list.clear(); 
  
        // add the elements of set 
        // with no duplicates to the list 
        list.addAll(set); 
  
        // return the list 
        return list; 
    }  
    
    
    /**
     * creates Frame and labels
     * includes font and colour settings + guidance image
     */
    public void setup() {
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
		instructions.setText("<html><p style=\"width:300px;text-align:center\">"+ instr +"</p></html>");

    	
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
     
    
    /**
     * Key Event
     * doesn't do anything yet
     * for unicode keys
     */
	public void keyTyped(KeyEvent e) {
		
	}





 
}