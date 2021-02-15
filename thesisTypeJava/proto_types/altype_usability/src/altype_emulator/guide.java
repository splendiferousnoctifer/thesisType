package altype_emulator;
import java.util.ArrayList;

/**
 * Creates and manages Guide for keyboard input
 * @author Samuel Ebner
 *
 */
public class guide {
	/**
	 * creates return String for guide
	 * by filling the string with the given substrings
	 * @param fillArray
	 * @return
	 */
	static String fillString(String[] fillArray) {

		String guide ="+-----+<br>";
		for (int i = 0; i < 4; i++) {
			guide += String.format("|  %s   |<br>", fillArray[i]);
		}
		guide += "+-----+";
				
		return guide;
	}
	
	/**
	 * creates start guide
	 * @return
	 */
	public static String startGuide() {
		//first selection possibilities
		String[] arrayGuideStart = {"<u>a</u>-i", "<u>j</u>-r", "<u>s</u>-z", "..." };
		
		String guide ="+-----+<br>";
		for (int i = 0; i < 4; i++) {
			guide += String.format("|  %s   |<br>", arrayGuideStart[i]);
		}
		guide += "+-----+";
				
		return guide;
	}
	 
	/**
	 * creates current guide
	 * @param combination
	 * @return
	 */
	public static String getGuide(ArrayList<Integer> combination) {
		//one button pressed
		String[][] arrayGuideTwo = {
				{"<b>a-i</b>", "<u>b</u>-d", "<u>e</u>-g", "<u>h</u>-i"},
				{"<u>k</u>-m", "<b>j-r</b>", "<u>n</u>-p", "<u>q</u>-r"},
				{"<u>t</u>-v", "<u>w</u>-y", "<b>s-z</b>", "&nbsp;z&nbsp;"},
				{"&nbsp;.&nbsp;", "&nbsp;,&nbsp;", "&nbsp;?&nbsp;", "<b>\" \"</b>"}
		};
		
		//two buttons are pressed
		String[][][] arrayGuideThree = {
				{
					{},
					{"<b>a-i</b>", "<b>b-d</b>", "&nbsp;<u>c</u>&nbsp;", "&nbsp;<u>d</u>&nbsp;"},
					{"<b>a-i</b>", "&nbsp;<u>f</u>&nbsp;", "<b>e-g</b>", "&nbsp;<u>g</u>&nbsp;"},
					{"<b>a-i</b>", "&nbsp;<u>i</u>&nbsp;", "&nbsp;&nbsp;&nbsp;", "<b>h-i</b>"}
				},
				{
					{"<b>k-m</b>", "<b>j-r</b>", "&nbsp;<u>l</u>&nbsp;", "&nbsp;<u>m</u>&nbsp;"},
					{},
					{"&nbsp;<u>o</u>&nbsp;", "<b>j-r</b>", "<b>n-p</b>", "&nbsp;<u>p</u>&nbsp;"},
					{"&nbsp;<u>r</u>&nbsp;", "<b>j-r</b>", "&nbsp;&nbsp;&nbsp;", "<b>q-r</b>"}
				},
				{
					{"<b>t-v</b>", "&nbsp;<u>u</u>&nbsp;", "<b>s-z</b>", "&nbsp;<u>v</u>&nbsp;"},
					{"&nbsp;<u>x</u>&nbsp;", "<b>w-y</b>", "<b>s-z</b>", "&nbsp;<u>y</u>&nbsp;"},
					{},
					{"&nbsp;&nbsp;&nbsp;", "&nbsp;&nbsp;&nbsp;", "<b>s-z</b>", "&nbsp;<b>z</b>&nbsp;"}
				},
				{
					{"&nbsp;&nbsp;&nbsp;", "&nbsp;&nbsp;&nbsp;", "&nbsp;&nbsp;&nbsp;", "&nbsp;&nbsp;&nbsp;"},
					{"&nbsp;&nbsp;&nbsp;", "&nbsp;&nbsp;&nbsp;", "&nbsp;&nbsp;&nbsp;", "&nbsp;&nbsp;&nbsp;"},
					{"&nbsp;&nbsp;&nbsp;", "&nbsp;&nbsp;&nbsp;", "&nbsp;&nbsp;&nbsp;", "&nbsp;&nbsp;&nbsp;"},
					{}
				}
		};
		
		//output string
		String[] arrayFill = new String[4];
		
		/*
		 * depending on the amount of buttons pressed and the combination, the guide array is filled and then returned via fillString
		 */
		for (int i = 0; i < arrayFill.length; i++) {
			
			if (combination.size() == 1) {
					arrayFill[i] = arrayGuideTwo[combination.get(0)-1][i];
			} else if(combination.size() == 2) {
					arrayFill[i]=arrayGuideThree[combination.get(0)-1][combination.get(1)-1][i];
			} else {
					arrayFill[i]="&nbsp;&nbsp;&nbsp;";
			}
		}
			
		
		
		return fillString(arrayFill);
	}
		
}
	

