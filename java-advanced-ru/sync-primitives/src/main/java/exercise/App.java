package exercise;

class App {

    public static void main(String[] args) {
        // BEGIN
        SafetyList safeList = new SafetyList();

        ListThread t1 = new ListThread(safeList);
        ListThread t2 = new ListThread(safeList);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(safeList.getSize());
        // END
    }
}

