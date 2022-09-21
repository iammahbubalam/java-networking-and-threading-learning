package ProducerConsumer;
class Q2 {
    int n;
    boolean isEmpty = true;
    synchronized int get() {
        if (isEmpty) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Got: " + n);
        isEmpty = true;
        notify();
        return n;
    }
    synchronized void put(int n) {
        if (!isEmpty) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.n = n;
        System.out.println("Put: " + n);
        isEmpty = false;
        notify();
    }
}
class Producer2 implements Runnable {
    Q2 q;
    Producer2(Q2 q) {
        this.q = q;
        new Thread(this, "Producer").start();
    }
    @Override
    public void run() {
        int i = 0;
        while (true) {
            q.put(i++);
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
class Consumer2 implements Runnable {
    Q2 q;
    Consumer2(Q2 q) {
        this.q = q;
        new Thread(this, "Consumer").start();
    }
    @Override
    public void run() {
        while (true) {
            q.get();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

public class CorrectPC {
    public static void main(String[] args) {
        Q2 q = new Q2();
        new Producer2(q);
        new Consumer2(q);
    }
}
