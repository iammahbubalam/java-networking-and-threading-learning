package Synchronization;

class CallMeSync {
    synchronized void call(String str) {
        System.out.print("[" + str);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class CallerSync implements Runnable {
    String message;
    Thread t;
    CallMeSync target;

    public CallerSync(CallMeSync callMe, String s) {
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

public class SynchronizedMethod {
    public static void main(String[] args) {
        CallMeSync target = new CallMeSync();
        // [Hello] [Synchronized] [World]
        CallerSync hello = new CallerSync(target, "Hello");
        CallerSync sync = new CallerSync(target, "Synchronized");
        CallerSync world = new CallerSync(target, "World");
    }
}
