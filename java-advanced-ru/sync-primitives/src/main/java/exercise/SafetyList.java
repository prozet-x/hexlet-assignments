package exercise;

class SafetyList {
    // BEGIN
    private int[] elements = new int[10];
    private int size = 0;

    public synchronized void add(int elem) {
        if (size == elements.length) {
            int[] newElements = new int[size * 2];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
        elements[size++] = elem;
    }

    public int get(int pos) {
        return elements[pos];
    }

    public int getSize() {
        return size;
    }
    // END
}
