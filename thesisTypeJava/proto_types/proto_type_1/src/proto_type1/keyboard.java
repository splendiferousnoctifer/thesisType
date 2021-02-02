package proto_type1;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * keyboard class
 * implememts Key Listener for Key Events
 * 
 * Proto-Type 1 Variation a
 * Creates a t9 like key setup and remaps the key input to the set output of keyplacement
 * Variation a uses the standard t9 setup with slight changes to the used characters and an additional delete button
 * @author Samuel Ebner
 */
public class keyboard  implements KeyListener {
	JLabel text_output = new JLabel("Please Enter your Text");
	
	//time tolerance for key-repetition
	long difTolerance = 300;
	
	long pause = 0;
	long startTime;
	long endTime;
	boolean first = true;
	char[] activeKey = {' '};
	int current;
	int lastKey;
	Timer timer;
		
	//define key codes for keys
	final int ONE = KeyEvent.VK_7;
	final int TWO = KeyEvent.VK_8;
	final int THREE = KeyEvent.VK_9;
	final int FOUR = KeyEvent.VK_4;
	final int FIVE = KeyEvent.VK_5;
	final int SIX = KeyEvent.VK_6;
	final int SEVEN = KeyEvent.VK_1;
	final int EIGHT = KeyEvent.VK_2;
	final int NINE = KeyEvent.VK_3;
	final int ZERO = KeyEvent.VK_0;

	

	//key lookup table
	char[] k48 = {' ','0'};
	char[] k49 = {'.',',','!', '1'};
	char[] k50 = {'a','b','c', '2'};
	char[] k51 = {'d','e','f', '3'};
	char[] k52 = {'g','h','i', '4'};
	char[] k53 = {'j','k','l', '5'};
	char[] k54 = {'m','n','o', '6'};
	char[] k55 = {'p','q','r','s', '7'};
	char[] k56 = {'t','u','v', '8'};
	char[] k57 = {'w','x','y','z', '9'};
	
	//text for output
	String text= "";
	
	/**
	 * constructor
	 */
    public keyboard(){
    	setup();
    	
    }
	
	/**
	 * after pause expired, word is reset
	 */
	void setWord(){
	    lastKey= 0;
	    current = 0;
	    first=true;
	}
	
	
	/**
	 * nested class
	 * @author Samuel Ebner
	 */
	class checkPause extends TimerTask{
		private boolean hasStarted = false;

		/**
		 * checks if pause has expired or is still ongoing
		 */
	    public void run() {
	        this.hasStarted = true;
	        endTime = System.currentTimeMillis();
	        if (endTime - startTime >= difTolerance) {
	        	timer.cancel();	       
	        	setWord();
	        }			
	    }

	    /**
	     * No longer used
	     * checks if the timer is still running 
	     * @return boolean hasRunStarted
	     */
	    public boolean hasRunStarted() {
	        return this.hasStarted;
	    }

	   
	}
	
	
	/**
	 * key event,
	 * determines the letter for output by counting how often a key is pressed within a time frame
	 * after pause expires letter is set to 'word'
	 * for any kind of key
	 */
	public void keyPressed(KeyEvent e) {	  
		
		//if new key then reset counter
		if (e.getKeyChar() != lastKey) {
	        current = 0;
	        first = true;
	    }
	    
		
		/*
		 * check which key is pressed and preset to right key map
		 * TODO: improve if-else cascade to switch case?
		 */
		if (e.getKeyChar() == ZERO) {
	        // 0
	        // space    
	        activeKey = k48;    
	    } else if (e.getKeyChar() == ONE) {
	        // 1
	        activeKey = k49;
	    } else if (e.getKeyChar() == TWO) {
	        // 2
	        // a b c
	        activeKey = k50;
	    } else if (e.getKeyChar() == THREE) {
	        // 3
	        // d e f
	        activeKey = k51;
	    } else if (e.getKeyChar() == FOUR) {
	        // 4
	        // g h i
	        activeKey = k52;
	    } else if (e.getKeyChar() == FIVE) {
	        // 5
	        // j k l
	        activeKey = k53;
	    } else if (e.getKeyChar() == SIX) {
	        // 6
	        // m n o
	        activeKey = k54;
	    } else if (e.getKeyChar() == SEVEN) {
	        // 7
	        // p q r s
	        activeKey = k55;
	    } else if (e.getKeyChar() == EIGHT) {
	        // 8
	        // t u v
	        activeKey = k56;    
	    } else if (e.getKeyChar() == NINE) {
	        // 9
	        // w x y z
	        activeKey = k57;
	    }


		/*
		 * if the counter is smaller than the length of the key map, counter is increased
		 * if the last possible character of the key mapis reached, counter starts from the beginning
		 */
	    if (current < activeKey.length-1) {
	        current++;      
	    }else{
	        current = 0;
	        first=true;
	    }
	    
	    //first was for keys with only one option like * and # -> no longer used
	    if (first) {
	        current = 0;
	        first = false;
	    }

	    
	    //new timer is started
	    timer = new Timer();   
	    startTime = System.currentTimeMillis();
	    pause = 0;
	    
	    //checks if pause is expired
	    timer.schedule(new checkPause(), 0, 5);

	    /*
	     * adds the set character to text if its new, and if its still the same key the character is changed in the text accordingly
	     * eg. 'aa' to b
	     */
	    if (e.getKeyChar() != lastKey) {
	        text += activeKey[current];
	    }else{
	    	text = text.substring(0, text.length()-1);
	        text += activeKey[current];
	    }
	    
	    
	    //label is set to text
	    text_output.setText("<html><p style=\"width:250px\">"+ text +"</p></html>");
	    
	    //current key is saved to last key to check for repetition when the next key is pressed
	    lastKey = e.getKeyChar();
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
    	
    	//guidance image
    	String instr = "+-------+-------+-------+ +-----+<br>" + 
    					"|&nbsp;&nbsp;&nbsp;1&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;2&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;3&nbsp;&nbsp;&nbsp;| |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|<br>" + 
    					"|&nbsp;&nbsp;.,!&nbsp;&nbsp;|&nbsp;&nbsp;abc&nbsp;&nbsp;|&nbsp;&nbsp;def&nbsp;&nbsp;| |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|<br>" + 
    					"+-------+-------+-------+ |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|<br>" + 
    					"|&nbsp;&nbsp;&nbsp;4&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;5&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;6&nbsp;&nbsp;&nbsp;| |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|<br>" + 
    					"|&nbsp;&nbsp;ghi&nbsp;&nbsp;|&nbsp;&nbsp;jkl&nbsp;&nbsp;|&nbsp;&nbsp;mno&nbsp;&nbsp;| |&nbsp;DEL&nbsp;|<br>" + 
    					"+-------+-------+-------+ +-----+<br>" + 
    					"|&nbsp;&nbsp;&nbsp;7&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;8&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;9&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>" + 
    					"|&nbsp;pqrs&nbsp;&nbsp;|&nbsp;&nbsp;tuv&nbsp;&nbsp;|&nbsp;&nbsp;wxyz&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>" + 
    					"+-------+-------+-------+&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>" + 
    					"+-------+-------+&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>" +
    					"|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>" + 
    					"|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;SPACE&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>" + 
    					"+-------+-------+&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    			
    	//label guidance image
    	JLabel instructions = new JLabel();
    	instructions.setForeground(Color.white);
    	instructions.setFont(new Font("Courier New", Font.PLAIN, 12));
    	instructions.setText("<html><p style=\"width:300px;text-align:center\">"+ instr +"</p></html>");
    	instructions.setVerticalAlignment(SwingConstants.CENTER);
    	
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
     * Key event
     * checks for ESC and closes game
     * checks for DEL and deletes previous character
     */
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            System.out.println("bye!");
            System.exit(0);
        }    
        
        //has to backspace 2 because the DEL key adds a letter too -> to be removed
        if(e.getKeyCode() == 107){
            text = text.substring(0, text.length() -2);
            text_output.setText("<html><p style=\"width:250px\">"+ text +"</p></html>");
        } 
        
    }

    
    /**
     * Key Event
     * doesn't do anything yet
     * for unicode keys
     */
	public void keyTyped(KeyEvent e) {
		
	}





 
}