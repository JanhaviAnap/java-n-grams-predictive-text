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
        String testString = "this is just a test yes this is a simple test";
        String[] words = testString.split("\\s+");
        int wordIndex = indexOf(words, "this", 3);
        System.out.println(wordIndex);
    }

    private int indexOf(String[] words, String target, int start) {
        for (int i = start; i < words.length; i++) {
            if (words[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - 1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for (int k = 0; k < numWords - 1; k++) {
            ArrayList<String> follows = getFollows(key);
            //System.out.println("Key: "+key+"\tValue: "+follows);
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
        int index = indexOf(myText, key, 0);

        while (index != -1) {
            follows.add(myText[index + 1]);
            index = indexOf(myText, key, index + 1);
        }

        return follows;
    }

    public static void main(String[] args) {
        //MarkovWordOne mwo = new MarkovWordOne();
        //mwo.setTraining("this is just a test yes this is a simple test");
        //mwo.testIndexOf();
        //String text = mwo.getRandomText(5);
        //System.out.println(text);
    }
}
