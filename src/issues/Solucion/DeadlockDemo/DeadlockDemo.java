package issues.Solucion.DeadlockDemo;


import issues.Solucion.Semaforo.SemaforoBinario;

public class DeadlockDemo {

    public static void main(String[] args) throws InterruptedException {
        Object ob1 = new Object();
        Object ob2 = new Object();
        Object ob3 = new Object();
        SemaforoBinario semaforoBinario = new SemaforoBinario();

        /// se agrego el parametro de semaforoBinario a cada hilo
        Thread t1 = new Thread(new SyncThread(ob1, ob2, semaforoBinario), "hilo1");
        Thread t2 = new Thread(new SyncThread(ob2, ob3, semaforoBinario), "hilo2");
        Thread t3 = new Thread(new SyncThread(ob3, ob1, semaforoBinario), "hilo3");

        t1.start();
        Thread.sleep(5000);
        t2.start();
        Thread.sleep(5000);
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Finalizado");


    }
}

class SyncThread implements Runnable {

    public Object ob1;
    public Object ob2;
    private SemaforoBinario semaforoBinario;


    public SyncThread(Object ob1, Object ob2, SemaforoBinario semaforoBinario) {
        this.ob1 = ob1;
        this.ob2 = ob2;
        this.semaforoBinario = semaforoBinario;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " generando lock en " + ob1 );

        try {
            semaforoBinario.acquire();
            synchronized (ob1) {
                System.out.println(name + " lock generado en " +  ob1);
                work();
                System.out.println(name + " generando lock en " + ob2);
                synchronized (ob2) {
                    System.out.println(name + " lock generado en " +  ob2);
                    work();
                }
                System.out.println(name + " lock liberado en " +  ob2);

            }
            semaforoBinario.release();
        }catch (InterruptedException ie){
            System.out.println("Sucedió un InterruptedException: " + ie.getMessage());
        }

        System.out.println(name + " lock liberado en " +  ob1 );
        System.out.println("Finalizó ejecución");


    }

    private void work(){
        try {
            Thread.sleep(7000);

        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}