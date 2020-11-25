package lesson_d;

public class ThreadTaskOne {

    static class MyThread extends Thread {

        private final Object monitor = new Object();
        private volatile char currentChar = 'A';

        public void printChar(char charToDisplay, char nextChar) {
            synchronized (monitor) {
                try {
                    for (int i = 0; i < 5; i++) {
                        while (currentChar != charToDisplay) {
                            monitor.wait();
                        }
                        System.out.print(charToDisplay);
                        currentChar = nextChar;
                        monitor.notifyAll();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread t1 = new Thread(() -> myThread.printChar('A', 'B'));
        Thread t2 = new Thread(() -> myThread.printChar('B', 'C'));
        Thread t3 = new Thread(() -> myThread.printChar('C', 'A'));
        t1.start();
        t2.start();
        t3.start();
    }
}

