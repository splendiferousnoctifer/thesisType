package altype_emulator;
import java.awt.event.KeyEvent;
import java.util.*;


public interface collection {
	/**
	 * lookuptable for altype button combinations
	 */
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
				{ 0 , 0 ,'i', 0 , 0 }
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
	
	
	/**
	 * checks for combination and assigns letter
	 * used by altype
	 * @param list
	 * @return
	 */
	default char getLetter(ArrayList<Integer> list) {
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
	 * used by altype
	 * @param <T>
	 * @param list
	 * @return
	 */
	default ArrayList<Integer> removeDuplicates(ArrayList<Integer> list) 
    { 
  
        // Create a new LinkedHashSet 
        Set<Integer> set = new LinkedHashSet<>(); 
  
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
	 * define key codes for keys t9
	 */
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

		

	/**
	 * key lookup table for t9
	 */
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
	
	
	/**
	 * guidance image for t9
	 */
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
		

	
	
	
	
}
