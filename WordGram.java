
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
        if (this.length() != other.length()) {
            return false;
        }
        for (int k=0; k < myWords.length; k++) {
            if (! myWords[k].equals(other.wordAt(k))) {
                return false;
            }

        }
        return true;

    }

    public WordGram shiftAdd(String word) {	
        String[] shiftedWords = new String[this.length()];
        for (int i = 1; i < this.length(); i++) {
            shiftedWords[i - 1] = this.wordAt(i);
        }
        shiftedWords[this.length() - 1] = word;
        return new WordGram(shiftedWords, 0, shiftedWords.length);
    }



}