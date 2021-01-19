package proto_type1;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;



public class determineKey implements KeyListener {
	JLabel text_output = new JLabel("Please Enter your Text");
	   
    public determineKey(){
    	setup();
    }
    
        
    public void setup() {
    	JFrame window = new JFrame("Proto-type 1"); 
    	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	window.setLayout(new BorderLayout());
    	
    	//window settings
    	window.setPreferredSize(new Dimension(400, 300));
    	window.getContentPane().setBackground(Color.black);
    	
    	//label settings
    	window.add(text_output);
    	text_output.setHorizontalAlignment(JLabel.CENTER);
    	text_output.setForeground(Color.white);
    	text_output.setFont(new Font("Century Gothic", Font.PLAIN, 18));
    	
    	//add keylistener
    	window.addKeyListener(this);
    	
    	
    	window.pack();
    	window.setVisible(true);
    	window.setLocationRelativeTo(null);
    }

    
    
    //For keys convertible to unicode
    public void keyTyped(KeyEvent e) {
    }
    
      
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            System.out.println("bye!");
            System.exit(0);
        } 
    }


	@Override
	public void keyPressed(KeyEvent e) {
    	System.out.println(e.getKeyCode() + " ");
		
	}
 
}