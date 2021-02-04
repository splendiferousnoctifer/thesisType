package altype_emulator;
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

	


	
	
	
	
}
