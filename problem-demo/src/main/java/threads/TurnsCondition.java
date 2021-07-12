package threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock+Condition实现3个线程轮流打印
 */
public class TurnsCondition {
    static class Inner implements Runnable {
        private ReentrantLock lock;
        private Condition myCondition;
        private Condition nextCondition;
        private int end = 75;
        private static int num = 0;
        private int threadId;

        public Inner(int threadId, ReentrantLock lock, Condition myCondition, Condition nextCondition) {
            this.threadId = threadId;
            this.lock = lock;
            this.myCondition = myCondition;
            this.nextCondition = nextCondition;
        }

        @Override
        public void run() {
            while (num < end) {
                lock.lock();
                try {
                    if (num % 3 == threadId) {
                        System.out.println("线程" + threadId + ":" + num++);
                        nextCondition.signal();
                    } else {
                        myCondition.await();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition0 = lock.newCondition();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        new Thread(new Inner(0, lock, condition0, condition1)).start();
        new Thread(new Inner(1, lock, condition1, condition2)).start();
        new Thread(new Inner(2, lock, condition2, condition0)).start();
    }
}
