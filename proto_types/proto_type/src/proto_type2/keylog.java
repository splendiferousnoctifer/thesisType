package proto_type2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class keylog  implements KeyListener {
	JLabel text_output = new JLabel("Please Enter your Text");
	int[] text;
	String currentChar = "";

	public keylog(){
		setup();
	}

	
	@Override
	public void keyPressed(KeyEvent e) {

		currentChar += e.getKeyChar();
	}
	
    public void keyReleased(KeyEvent e) {
    	text= checkCombination(currentChar);
    	currentChar = "";
    	System.out.println(Arrays.toString(text));
    }
    
    public int[] checkCombination(String combi) {
    	int[] combination = new int[combi.length()];
    	for (int i = 0; i < combi.length(); i++) {
			switch(combi.charAt(i)){
			case 's':
				combination[i] = 5;
				break;
			case 'r':
				combination[i] = 4;
				break;
			case 't':
				combination[i] = 3;
				break;
			case 'h':
				combination[i] = 2;
				break;
			case ' ':
				combination[i] = 1;
				break;
			}
		}
    	
    	combination = removeDuplicateElements(combination);
    	Arrays.sort(combination);
    	
    	return combination;
    }
    
    public int[] removeDuplicateElements(int arr[]){  
    	int n = arr.length;
    	
        if (n==0 || n==1){  
            return arr;  
        }  
        
        int[] temp = new int[n];  
        
        int j = 0;  
        for (int i=0; i<n-1; i++){  
            if (arr[i] != arr[i+1]){  
                temp[j++] = arr[i];  
            }  
         }  
        temp[j++] = arr[n-1];     

        return temp;  
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
    	text_output.setFont(new Font("Courier New", Font.PLAIN, 18));
    	
    	//add keylistener
    	window.addKeyListener(this);
    	
   
    	window.pack();
    	window.setVisible(true);
    	window.setLocationRelativeTo(null);
    }
    
      



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	





 
}