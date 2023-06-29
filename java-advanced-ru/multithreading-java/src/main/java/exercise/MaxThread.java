package exercise;

// BEGIN
public class MaxThread extends Thread {
    private int[] arr;
    private int max;

    public MaxThread(int[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        max = arr[0];
        int count = arr.length;
        for(int i = 1; i < count; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
    }

    public int getMax() {
        return max;
    }
}
// END
