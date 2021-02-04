package altype_emulator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.*;

public class setup implements collection{
	JFrame window = new JFrame("altype - emulator");
	JMenuBar menuBar = new JMenuBar();

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
        startscreenFrame();       

    	window.pack();
    	window.setVisible(true);
    	window.setLocationRelativeTo(null);
    	
    	
    	
    }
    
    /**
     * creates main menu
     */
    void createMainMenu() {
    	window.setJMenuBar(menuBar);
    	menuBar.removeAll();
    	
		JMenu mainMenu = new JMenu("keyType");
		JMenu aboutMenu = new JMenu("about");
        JMenu helpMenu = new JMenu("help");

    	JMenuItem menuItemAltype = new JMenuItem("altype");
    	JMenuItem menuItemT9 = new JMenuItem("t9 - like");
    	JMenuItem menuItemAbout = new JMenuItem("about");
    	JMenuItem menuItemContact = new JMenuItem("contact");
    	JMenuItem menuItemHelp = new JMenuItem("help");

    	
        //menu options
        menuBar.add(mainMenu);
        menuBar.add(aboutMenu);
        menuBar.add(helpMenu);

        
        //menu item selection action is set
        menuItemAltype.addActionListener(new ActionListener() {
  			@Override
			public void actionPerformed(ActionEvent arg0) {
  				altypeKeyboard= new altype(window, menuBar);
			}
        });
        
        menuItemT9.addActionListener(new ActionListener() {
  			@Override
			public void actionPerformed(ActionEvent arg0) {
  				//TODO t9
  				System.out.println("t9 selected");
			}
        });
        
        menuItemAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aboutFrame();
				
			}
		});
        
        menuItemContact.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Desktop desktop = Desktop.getDesktop(); 
				   try {
					desktop.mail(new URI("mailto:s1810238017@students.fh-hagenberg.at"));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
				
			}
		});
        
        menuItemHelp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UIManager UI = new UIManager();
				UI.put("Panel.background", Color.black);
				UI.put("OptionPane.background", Color.black);

				JOptionPane.showMessageDialog(window, "<html><font color=#ffffff face=\"Courier New\">Select a keyType to start.<br> For further information on keyTypes, <br> go to a keyType and click help.</font>", "help",
						JOptionPane.PLAIN_MESSAGE);
			}
		});
        
        
        //menu item added to menu
        mainMenu.add(menuItemAltype);
        mainMenu.add(menuItemT9);
        aboutMenu.add(menuItemAbout);
        aboutMenu.add(menuItemContact);
        helpMenu.add(menuItemHelp);
	}
    
   
    /**
	 * creates startscreen
	 * @param window
	 */
	void startscreenFrame() {
		window.getContentPane().removeAll();
		JLabel start = new JLabel();
		String startText = "<b>	altype</b><br>"
				+ " keyboard - emulator<br><br><br>"
				+ "Select keyType to start!";
		
		start.setForeground(Color.white);
		start.setFont(new Font("Courier New", Font.PLAIN, 16));
		start.setHorizontalAlignment(JLabel.CENTER);
		start.setVerticalAlignment(SwingConstants.CENTER);
		
		start.setText("<html><p style=\"width:200px;text-align:left\">"+ startText +"</p></html>");
		
		createMainMenu();
		
		window.add(start);
		window.pack();
		window.setVisible(true);
		window.setLocationRelativeTo(null);
    
	}
    
    /**
	 * creates about screen
	 * @param window
	 */
	void aboutFrame() {
		window.getContentPane().removeAll();
		JLabel about = new JLabel();
		String aboutText = 	"<b>altype - thesis project</b><br>" + 
							"five-finger text input device<br>" + 
							"(four-finger implementation with mouse interaction)<br><br>"
							+ "©Samuel Ebner - 2021<br>"
							+ "s1810238017@students.fh-hagenberg.at";
		
		about.setForeground(Color.white);
		about.setFont(new Font("Courier New", Font.PLAIN, 12));
		about.setHorizontalAlignment(JLabel.CENTER);
		about.setVerticalAlignment(SwingConstants.CENTER);
		about.setText("<html><p style=\"width:200px;text-align:left\">"+ aboutText +"</p></html>");
		
		window.add(about);
				
		createMainMenu();
		
		window.pack();
		window.setVisible(true);
		window.setLocationRelativeTo(null);
			
	}



}
