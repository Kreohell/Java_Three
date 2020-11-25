package lesson_d;

public class MFU {

    static class MFUThread extends Thread{

        private final Object lockPrint = new Object();
        private final Object lockScan = new Object();

        public void print(String name) {
            synchronized (lockPrint) {
                System.out.printf("%s начинает печать\n", name);
                for (int i = 0; i < 1; i++) {
                    System.out.printf("%s печатает...\n", name);
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("%s закончил печать.\n", name);
            }
        }

        public void scan(String name) {
            synchronized (lockScan) {
                System.out.printf("%s начинает сканировать.\n", name);
                for (int i = 0; i < 1; i++) {
                    System.out.printf("%s сканирует...\n", name);
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("%s закончил сканировать.\n", name);
            }
        }

        public void xerox(String name) {
            synchronized (lockScan) {
                System.out.printf("%s начинает ксерокопировать.\n", name);
                scan(name);
                synchronized (lockPrint) {
                    print(name);
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("%s закончил ксерокопировать.\n", name);
            }
        }
    }

    public static void main(String[] args) {
        MFUThread mfuThread = new MFUThread();
        Thread t1 = new Thread(() -> mfuThread.print("Васян"));
        Thread t2 = new Thread(() -> mfuThread.scan("Гриша"));
        Thread t3 = new Thread(() -> mfuThread.xerox("Егор"));
        t1.start();
        t2.start();
        t3.start();
    }
}
