package exercise;

// BEGIN
public class ReversedSequence implements java.lang.CharSequence {
    private String reversedStr;
    public ReversedSequence(String str) {
        reversedStr = "";
        for (int i = 0; i < str.length(); i++) {
            reversedStr = str.charAt(i) + reversedStr;
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
