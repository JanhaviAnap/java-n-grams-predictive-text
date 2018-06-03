
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for (String word : myWords) {
            ret = ret + word + " ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        // TODO: Complete this method
        return true;

    }

    public WordGram shiftAdd(String word) {	
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        // TODO: Complete this method
        return out;
    }

    public static void main(String[] args) {
        String[] source = new String[3];
        source[0] = "Cheese";
        source[1] = "Pepperoni";
        source[2] = "Black Olives";
        WordGram wg = new WordGram(source, 0, 3);
        System.out.println("WordGram length():");
        System.out.println(wg.length());
        System.out.println("WordGram toString():");
        System.out.println(wg.toString());

    }

}