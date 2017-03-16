package Latches;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by damonwingo on 3/16/17.
 */
public class Latch implements Runnable{
    private CountDownLatch latch;

    public Latch(CountDownLatch latch) {
        this.latch = latch;
    }


    @Override
    public void run() {
        System.out.println("Started...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }

    public static void main(String[] args) {
        CountDownLatch la = new CountDownLatch(3);
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for(int i = 0; i <3; i++){
            executor.submit(new Latch(la));
        }

        try {
            la.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed");
    }


}
