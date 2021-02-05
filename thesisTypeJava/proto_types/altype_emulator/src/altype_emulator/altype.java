package altype_emulator;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.util.ArrayList;


/**
 * altype
 * takes input from 4 buttons and remaps their connections to pre-set letters
 * Implements Key Listener for Key Events
 * 
 * @author Samuel Ebner
 */
public class altype implements KeyListener, collection {
	JLabel text_output = new JLabel("Please Enter your Text");
	JLabel instructions = new JLabel();
	
	boolean keyboardUsed = true;
	
	//when keyboard is used
	final int ONE_KEYBOARD = KeyEvent.VK_H;
	final int TWO_KEYBOARD = KeyEvent.VK_T;
	final int THREE_KEYBOARD = KeyEvent.VK_R;
	final int FOUR_KEYBOARD = KeyEvent.VK_S;
	
	//TODO change to correct keyEvents
	//when handheld device is used
	final int ONE_HANDHELD = 97;
	final int TWO_HANDHELD = 98;
	final int THREE_HANDHELD = KeyEvent.VK_L;
	final int FOUR_HANDHELD = KeyEvent.VK_A; 

	String text= "";
	int first_key = 0;

	ArrayList<Integer> inputCombination = new ArrayList<Integer>();
	
	
	/**
	 * constructor
	 * @param window 
	 * @param text_output 
	 * @param instructions 
	 */
    public altype(JFrame window, JMenuBar menubar){
    	window.getContentPane().removeAll();
    	window.addKeyListener(this);
    	
    	//label text settings
    	text_output.setHorizontalAlignment(JLabel.CENTER);
    	text_output.setForeground(Color.white);
    	text_output.setFont(new Font("Courier New", Font.PLAIN, 18));
    	text_output.setVerticalAlignment(SwingConstants.CENTER);
    
    	//label guidance image
    	instructions.setForeground(Color.white);
    	instructions.setFont(new Font("Courier New", Font.PLAIN, 16));
    	instructions.setVerticalAlignment(SwingConstants.CENTER);
		instructions.setText("<html><p style=\"width:300px;text-align:center\">"+ guide.startGuide() +"</p></html>");    	
    	
    	//placement of labels
    	Container c = window.getContentPane();
        c.setLayout(new GridLayout(2,1));
        window.add(text_output);   	
    	window.add(instructions);
    	
    	createAltypeMenu(window, menubar);
    	

        window.pack();
    	window.setVisible(true);
    	window.setLocationRelativeTo(null);
    }
	

	/**
	 * key event that determines the depressed the combination
	 * for any kind of key
	 */
	public void keyPressed(KeyEvent e) {
		
		//first key that is pressed
		first_key = e.getKeyCode();
		
		//which key is pressed
		if(keyboardUsed) {
			switch(e.getKeyCode()){
			case ONE_KEYBOARD:
				inputCombination.add(1);
				break;
			case TWO_KEYBOARD:
				inputCombination.add(2);
				break;
			case THREE_KEYBOARD:
				inputCombination.add(3);
				break;
			case FOUR_KEYBOARD:
				inputCombination.add(4);
				break;
			}	
		} else {
			switch(e.getKeyCode()){
			case ONE_HANDHELD:
				inputCombination.add(1);
				break;
			case TWO_HANDHELD:
				inputCombination.add(2);
				break;
			case THREE_HANDHELD:
				inputCombination.add(3);
				break;
			case FOUR_HANDHELD:
				inputCombination.add(4);
				break;
			}	
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
	 * creates menu for altype
	 * @param window
	 */
	void createAltypeMenu(JFrame window, JMenuBar menubar) {
		// create menu
		JMenuBar menuBarAltype = new JMenuBar();
		window.setJMenuBar(menuBarAltype);

		//menu options
		JMenu mainMenu = new JMenu("home");
		JMenu settingsMenu = new JMenu("settings");
        JMenu helpMenu = new JMenu("help");

        //menu items
    	JMenuItem menuItemStart = new JMenuItem("home");
    	JMenuItem menuItemExit = new JMenuItem("exit");
    	JMenuItem menuItemKeyboard = new JMenuItem("keyboard");
    	JMenuItem menuItemHandheld = new JMenuItem("handheld");
    	JMenuItem menuItemHelp = new JMenuItem("help");

    	
        //menu options are set to menu bar
    	menuBarAltype.add(mainMenu);
    	menuBarAltype.add(settingsMenu);
    	menuBarAltype.add(helpMenu);

        
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

				
				JLabel label = new JLabel("altype - help");
				label.setFont(new Font("Courier New", Font.PLAIN, 16));
				label.setText(altypeHelp);
				label.setForeground(Color.white);
				
				JOptionPane.showMessageDialog(window, label, "altype - help", JOptionPane.PLAIN_MESSAGE);
				
			}
		});
        
        menuItemKeyboard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				keyboardUsed = true;
			}
		});
        
        menuItemHandheld.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				keyboardUsed = false;
			}
		});
        
        //menu item added to menu
        mainMenu.add(menuItemStart);
        mainMenu.add(menuItemExit);
        settingsMenu.add(menuItemKeyboard);
        settingsMenu.add(menuItemHandheld);
        helpMenu.add(menuItemHelp);
	}
	
}