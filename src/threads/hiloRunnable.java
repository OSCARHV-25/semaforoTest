package threads;

public class hiloRunnable implements Runnable {


    @Override
    public void run() {
        System.out.println("Ejecutando hilo"+ Thread.currentThread().getName());
    }
}
