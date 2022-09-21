package Synchronization;

class CallMe {
    void call(String str) {
        System.out.print("[" + str);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("] ");
    }
}

class Caller implements Runnable {
    String message;
    Thread t;
    CallMe target;

    public Caller(CallMe callMe, String s) {
        this.t = new Thread(this);
        this.target = callMe;
        this.message = s;
        t.start();
    }

    @Override
    public void run() {
        target.call(message);
    }
}

public class UnSynchronized {
    public static void main(String[] args) {
        CallMe target = new CallMe();
        // [Hello] [Synchronized] [World]
        Caller hello = new Caller(target, "Hello");
        Caller sync = new Caller(target, "Synchronized");
        Caller world = new Caller(target, "World");
    }
}
