import java.util.*;

public class MarkovWord implements IMarkovModel {

    private String[] myText;
    private Random myRandom;
    private int myOrder;

    public MarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text) {
        myText = text.split("\\s+");
    }

    public void testIndexOf() {
        String testWords = "this is just a test yes this is a simple test";
        String[] words = testWords.split("\\s+");
        String testWordsgram = "this is a";
        String[] wordgramArray = testWordsgram.split("\\s+");
        WordGram wg = new WordGram(wordgramArray, 0, 3);
        int wordIndex = indexOf(words, wg, 0);
        System.out.println(wordIndex);
    }

    private int indexOf(String[] words, WordGram target, int start) {
        for (int i = start; i < words.length - target.length(); i++) {
            if (words[i].equals(target.wordAt(0))) {
                boolean targetFound = true;
                for (int k = 0; k < target.length(); k++) {
                    if (!words[k+i].equals(target.wordAt(k))) {
                        targetFound = false;
                        break;
                    }
                    if (targetFound) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    public String getRandomText(int numChars) {

        StringBuilder sb = new StringBuilder();

        int index = myRandom.nextInt(myText.length-myOrder);
        WordGram key = new WordGram(myText,index,myOrder);

        sb.append(key.toString());

        for (int i=0;i<numChars-myOrder;i++) {
            ArrayList<String> follows = getFollows(key);
            if (follows == null || follows.size()==0) break;
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(sb, next);
            key = key.shiftAdd(next);
        }

        return sb.toString().trim();
    }

    private ArrayList<String> getFollows(WordGram kGram) {

        ArrayList<String> follows = new ArrayList<String>();
        int index = indexOf(myText, kGram, 0);

        while (index != -1) {
            follows.add(myText[index + myOrder]);
            index = indexOf(myText, kGram, index + 1);
        }

        return follows;
    }

    public static void main(String[] args) {
        MarkovWord mw = new MarkovWord(0);
        mw.testIndexOf();
        //System.out.println(text);
    }
}
