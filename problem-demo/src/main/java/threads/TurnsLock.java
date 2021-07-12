package threads;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock实现3个线程轮流打印
 */
public class TurnsLock {
    static class Inner implements Runnable {
        private ReentrantLock lock;
        private static int num = 0;
        private int end = 75;
        private int threadId;

        public Inner(int threadId, ReentrantLock lock) {
            this.lock = lock;
            this.threadId = threadId;
        }

        @Override
        public void run() {
            while (num < end) {
                lock.lock();
                if (num % 3 == threadId) {
                    try {
                        Thread.sleep(500l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程：" + threadId + ":" + num++);
                }
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        new Thread(new Inner(0, lock)).start();
        new Thread(new Inner(1, lock)).start();
        new Thread(new Inner(2, lock)).start();
    }
}
