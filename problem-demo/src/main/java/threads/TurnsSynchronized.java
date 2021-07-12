package threads;

/**
 * 3个线程轮有打印：1、2、3、1、2、3、1、2、3、、、
 * synchronized+wait+notifyAll
 */
public class TurnsSynchronized {
    static class Inner implements Runnable {
        private Object lock;
        private static int num = 0;
        private int end = 75;
        private int threadId;

        public Inner(int threadId, Object lock) {
            this.threadId = threadId;
            this.lock = lock;
        }

        @Override
        public void run() {
            while (num <= end) {
                synchronized (lock) {
                    if (num % 3 == threadId) {
                        System.out.println("线程：" + threadId + ":" + num++);
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Object lock = new Object();
        new Thread(new Inner(0, lock)).start();
        new Thread(new Inner(1, lock)).start();
        new Thread(new Inner(2, lock)).start();
    }
}
