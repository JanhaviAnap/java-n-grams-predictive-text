
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
	String testString = "this is just a test yes this is a simple test";
	String[] myText = testString.split("\\s+");

    //private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}

	public void testIndexOf() {
    	String testString = "this is just a test yes this is a simple test";
		String[] words = testString.split("\\s+" );
		indexOf(words, "yes", 0);
	}

	private int indexOf(String[] words, String target, int start) {
		for (int i = start; i < words.length; i++) {
			if (words[i].equals(target)) {
				//System.out.println("Found \"" + words[i] + "\" at index " + i);
				return i;
			}
		}
    	return -1;
	}

	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-1);  // random word to start with
		String key = myText[index];
		sb.append(key);
		sb.append(" ");
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(key);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key = next;
		}
		
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(String key) {
	    ArrayList<String> follows = new ArrayList<String>();
	    int keyIndex = indexOf(myText, key, 0);
	    if (keyIndex != -1) {
			for (int i = keyIndex + 1; i < myText.length; i++) {
				follows.add(myText[i]);
			}
		}
	    return follows;
    }

	public static void main(String[] args) {
		MarkovWordOne mwo = new MarkovWordOne();
		//mwo.testIndexOf();
		//mwo.getRandomText(3);
	}

}
