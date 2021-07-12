package threads;

import java.util.concurrent.Semaphore;

/**
 * Semaphore实现3个线程轮流打印
 */
public class TurnsSemaphore {
    static class Inner implements Runnable {
        private Semaphore mySemaphore;
        private Semaphore nextSemaphore;
        private static int num = 0;
        private int end = 75;
        private int threadId;

        public Inner(int threadId, Semaphore mySemaphore, Semaphore nextSemaphore) {
            this.mySemaphore = mySemaphore;
            this.nextSemaphore = nextSemaphore;
            this.threadId = threadId;
        }

        @Override
        public void run() {
            while (num < end) {
                try {
                    mySemaphore.acquire();
                    System.out.println("线程" + threadId + ":" + num++);
                    nextSemaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Semaphore semaphore0 = new Semaphore(1);
        Semaphore semaphore1 = new Semaphore(0);
        Semaphore semaphore2 = new Semaphore(0);
        new Thread(new Inner(0, semaphore0, semaphore1)).start();
        new Thread(new Inner(1, semaphore1, semaphore2)).start();
        new Thread(new Inner(2, semaphore2, semaphore0)).start();
    }
}
