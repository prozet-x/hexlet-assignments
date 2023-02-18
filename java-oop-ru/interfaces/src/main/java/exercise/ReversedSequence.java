package exercise;

// BEGIN
public class ReversedSequence implements java.lang.CharSequence {
    private String str;
    private String reversedStr;
    public ReversedSequence(String str) {
        this.str = str;
        this.reversedStr = "";
        for (int i = 0; i < str.length(); i++) {
            this.reversedStr = str.charAt(i) + this.reversedStr;
        }
    }

    @Override
    public int length() {
        return reversedStr.length();
    }

    @Override
    public char charAt(int i) {
        return reversedStr.charAt(i);
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return reversedStr.subSequence(i, i1);
    }

    @Override
    public String toString() {
        return reversedStr;
    }
}
// END
