package altype_emulator;

import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

/**
 * t9 class
 * implememts Key Listener for Key Events
 * 
 * 
 * Creates a t9 like key setup and remaps the key input to the set output of keyplacement
 * Variation a uses the standard t9 setup with slight changes to the used characters and an additional delete button
 * @author Samuel Ebner
 */
public class tnine  implements KeyListener, collection {
	JLabel text_output = new JLabel("Please Enter your Text");
	JLabel instructions = new JLabel();
	
	//time tolerance for key-repetition
	long difTolerance = 300;
	long pause = 0;
	long startTime;
	long endTime;
	Timer timer;
	
	//key determination
	boolean first = true;
	int current;
	int lastKey;
	char[] activeKey = {' '};
	
	//text for output
	String text= "";
	
	
	/**
	 * constructor
	 */
    public tnine(JFrame window, JMenuBar menubar){
    	window.getContentPane().removeAll();
    	window.addKeyListener(this);
    	
    	
    	//label text settings
    	text_output.setHorizontalAlignment(JLabel.CENTER);
    	text_output.setForeground(Color.white);
    	text_output.setFont(new Font("Courier New", Font.PLAIN, 18));
    	text_output.setVerticalAlignment(SwingConstants.CENTER);
    	
    		
    	//label guidance image
    	instructions.setForeground(Color.white);
    	instructions.setFont(new Font("Courier New", Font.PLAIN, 12));
    	instructions.setText("<html><p style=\"width:300px;text-align:center\">"+ instr +"</p></html>");
    	instructions.setVerticalAlignment(SwingConstants.CENTER);
    	
    	//placement of labels
    	Container c = window.getContentPane();
        c.setLayout(new GridLayout(2,1));
        window.add(text_output);
    	window.add(instructions);
    	
    	//create menubar
    	createTnineMenu(window, menubar);
    	
    	window.pack();
    	window.setVisible(true);
    	window.setLocationRelativeTo(null);    	
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
	
	
	/**
	 * creates menu for t9
	 * @param window
	 */
	void createTnineMenu(JFrame window, JMenuBar menubar) {
		// create menu
		JMenuBar menuBarT9 = new JMenuBar();

		window.setJMenuBar(menuBarT9);
		JMenu mainMenu = new JMenu("start");
        JMenu helpMenu = new JMenu("help");

    	JMenuItem menuItemStart = new JMenuItem("start");
    	JMenuItem menuItemExit = new JMenuItem("exit");
    	JMenuItem menuItemHelp = new JMenuItem("help");

    	
        //menu options
    	menuBarT9.add(mainMenu);
    	menuBarT9.add(helpMenu);

        
        //start - workaround
        menuItemStart.addActionListener(new ActionListener() {
  			@Override
			public void actionPerformed(ActionEvent arg0) {
  				window.getContentPane().removeAll();
  				window.getContentPane().setLayout(new BorderLayout());
  				JLabel start = new JLabel();
  				String startText = "<b>	altype</b><br>"
  						+ " keyboard - emulator<br><br><br>"
  						+ "Select keyType to start!";
  				
  				start.setForeground(Color.white);
  				start.setFont(new Font("Courier New", Font.PLAIN, 16));
  				start.setHorizontalAlignment(JLabel.CENTER);
  				start.setVerticalAlignment(SwingConstants.CENTER);
  				
  				start.setText("<html><p style=\"width:200px;text-align:left\">"+ startText +"</p></html>");
  				  				
  				window.setJMenuBar(menubar);
  				window.add(start);
  				window.pack();
  				window.setVisible(true);
  				window.setLocationRelativeTo(null);
			}
        });
        
        //exit
        menuItemExit.addActionListener(new ActionListener() {
  			@Override
			public void actionPerformed(ActionEvent arg0) {
  				System.exit(0);
			}
        });
             
        //TODO Explanation
        menuItemHelp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UIManager.put("Panel.background", Color.black);
				UIManager.put("OptionPane.background", Color.black);

				JOptionPane.showMessageDialog(window,
						"<html><font color=#ffffff face=\"Courier New\">Select a keyType to start.<br> For further information on keyTypes, <br> go to a keyType and click help.</font>",
						"altype - help",
						JOptionPane.PLAIN_MESSAGE);
			}
		});
        

        
        //menu item added to menu
        mainMenu.add(menuItemStart);
        mainMenu.add(menuItemExit);
        helpMenu.add(menuItemHelp);
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

}