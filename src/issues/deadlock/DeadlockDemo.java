package issues.deadlock;

public class DeadlockDemo {

    public static void main(String[] args) throws InterruptedException {
        Object ob1 = new Object();
        Object ob2 = new Object();
        Object ob3 = new Object();

        semforoBinario semaforo1 = new semforoBinario();
        semforoBinario semaforo2 = new semforoBinario();
        semforoBinario semaforo3 = new semforoBinario();

        Thread t1 = new Thread(new SyncThread(ob1, ob2, semaforo1, semaforo2), "hilo1");
        Thread t2 = new Thread(new SyncThread(ob2, ob3, semaforo2, semaforo3), "hilo2");
        Thread t3 = new Thread(new SyncThread(ob3, ob1, semaforo3, semaforo1), "hilo3");

        t1.start();
        Thread.sleep(1000);
        t2.start();
        Thread.sleep(1000);
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Finalizado");
    }
}

class SyncThread implements Runnable {
    private final Object ob1;
    private final Object ob2;
    private final semforoBinario semaphore1;
    private final semforoBinario semaphore2;

    public SyncThread(Object ob1, Object ob2, semforoBinario semaphore1, semforoBinario semaphore2) {
        this.ob1 = ob1;
        this.ob2 = ob2;
        this.semaphore1 = semaphore1;
        this.semaphore2 = semaphore2;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        try {
            System.out.println(name + " adquiriendo lock en " + ob1);
            semaphore1.acquire();
            System.out.println(name + " lock adquirido en " + ob1);
            work();
            System.out.println(name + " adquiriendo lock en " + ob2);
            semaphore2.acquire();
            System.out.println(name + " lock adquirido en " + ob2);
            work();
        } catch (InterruptedException e) {
            System.out.println(name + " fue interrumpido");
        } finally {
            semaphore2.release();
            System.out.println(name + " lock liberado en " + ob2);
            semaphore1.release();
            System.out.println(name + " lock liberado en " + ob1);
            System.out.println("Finalizó ejecución");
        }
    }

    private void work() throws InterruptedException {
        Thread.sleep(1000); // Espera de 1 segundo
    }
}

class semforoBinario {
    private boolean available = true;

    public synchronized void acquire() throws InterruptedException {
        while (!available) {
            wait();
        }
        available = false;
    }

    public synchronized void release() {
        available = true;
        notify();
    }
}
