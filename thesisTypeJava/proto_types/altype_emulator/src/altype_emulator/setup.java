package altype_emulator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class setup {
	JFrame window = new JFrame("altype - emulator");
	JMenuBar menuBar = new JMenuBar();
	JMenu connectionMenu = new JMenu("Connection");
	JMenuItem menuItemConnect = new JMenuItem("Connect");
	boolean altype = false;
	

	
	altype altypeKeyboard;
	
	/**
     * creates Frame and labels
     * includes font and colour settings + guidance image
     */
    public setup() {
    	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	window.setLayout(new BorderLayout());
    	
    	//window settings
    	window.setPreferredSize(new Dimension(400, 500));
    	window.getContentPane().setBackground(Color.black);
    	
    	//create menu
        window.setJMenuBar(menuBar);
        
        //menu options
        menuBar.add(connectionMenu);

        
        //menu item selection action is set
        menuItemConnect.addActionListener(new ActionListener() {
  			@Override
			public void actionPerformed(ActionEvent arg0) {
  				altypeKeyboard= new altype(window);
			}
        });
        
        
        //menu item added to menu
        connectionMenu.add(menuItemConnect);
        
        //TODO
        //depending on menu selection different options are started
       

    	window.pack();
    	window.setVisible(true);
    	window.setLocationRelativeTo(null);
    	
    	
    	
    }



}
