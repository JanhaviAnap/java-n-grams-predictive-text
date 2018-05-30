
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.Random;

public class MarkovWordTwo implements IMarkovModel {

    private String[] myText;
    private Random myRandom;

    public MarkovWordTwo() {

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
		int wordIndex = indexOf(words, "just", "a", 0);
		System.out.println(wordIndex);

	}

	private int indexOf(String[] words, String target1, String target2, int start) {

		for (int i = start; i < words.length; i++) {
			if (i+1 < words.length) {
				if (words[i].equals(target1) && words[i + 1].equals(target2)) {
					return i;
				}
			}
		}

    	return -1;
	}

	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-2);  // random word to start with
		String key1 = myText[index];
		String key2 = myText[index + 1];
		sb.append(key1);
		sb.append(" ");
		sb.append(key2);
		sb.append(" ");
		for(int k=0; k < numWords-1; k++){
			ArrayList<String> follows = getFollows(key1, key2);
			//System.out.println("Key: "+key+"\tValue: "+follows);
			if (follows.size() == 0) {
				break;
			}
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key1 = key2;
			key2 = next;
		}

		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(String key1, String key2) {

        ArrayList<String> follows = new ArrayList<String>();
        int index = indexOf(myText, key1, key2, 0);

        while (index!=-1) {
        	if (index + 2 < myText.length){
				follows.add(myText[index+2]);
				index = indexOf(myText, key1, key2,index+1);
			}
        }

        return follows;

    }

	public static void main(String[] args) {
		MarkovWordTwo mwt = new MarkovWordTwo();
		mwt.setTraining("this is just a test yes this is a simple test");
		//mwt.testIndexOf();
		ArrayList<String> follows =  mwt.getFollows("just", "a");
		System.out.println(follows);
		//String text = mwt.getRandomText(5);
		//System.out.println(text);
	}

}
