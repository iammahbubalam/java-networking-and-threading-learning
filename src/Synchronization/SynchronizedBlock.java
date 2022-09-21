package Synchronization;

class CallMeSyncBlock {
    void call(String str) {
        System.out.print("[" + str);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void call2() {
        System.out.print("] ");
    }
}

class CallerSyncBlock implements Runnable {
    String message;
    Thread t;
    CallMeSyncBlock target;

    public CallerSyncBlock(CallMeSyncBlock callMe, String s) {
        this.t = new Thread(this);
        this.target = callMe;
        this.message = s;
        t.start();
    }

    @Override
    public void run() {
        synchronized (target) {
            target.call(message);
            target.call2();
        }

    }
}

public class SynchronizedBlock {
    public static void main(String[] args) {
        CallMeSyncBlock target = new CallMeSyncBlock();
        //CallMeSyncBlock target2 = new CallMeSyncBlock();
        // [Hello] [Synchronized] [World]
        CallerSyncBlock hello = new CallerSyncBlock(target, "Hello");
        CallerSyncBlock sync = new CallerSyncBlock(target, "Synchronized");
        CallerSyncBlock world = new CallerSyncBlock(target, "World");
    }
}