package threads;

public class hiloThread extends Thread {
    @Override
    public void run() {
        System.out.println("Hilo Thread"+ Thread.currentThread().getName());
    }

}
