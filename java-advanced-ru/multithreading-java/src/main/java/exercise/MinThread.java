package exercise;

// BEGIN
public class MinThread extends Thread {
    private int[] arr;
    private int min;

    public MinThread(int[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        min = arr[0];
        int count = arr.length;
        for(int i = 1; i < count; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
    }

    public int getMin() {
        return min;
    }
}
// END
