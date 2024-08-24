package issues.SolucionSemaforo.racecondition;



import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Contador contador = new Contador();
        Semaphore semaforo = new Semaphore(1);

        Runnable tarea1 = new HiloContador(contador, semaforo);// debemos agregar el semaforoBinario
        Runnable tarea2 = new HiloContador(contador,semaforo);
        Runnable tarea3 = new HiloContador(contador,semaforo);
        Runnable tarea4 = new HiloContador(contador,semaforo);
        Runnable tarea5 = new HiloContador(contador,semaforo);
        Runnable tarea6 = new HiloContador(contador,semaforo);
        Runnable tarea7 = new HiloContador(contador,semaforo);

        Thread hilo1 = new Thread(tarea1);
        Thread hilo2 = new Thread(tarea2);
        Thread hilo3 = new Thread(tarea3);
        Thread hilo4 = new Thread(tarea4);
        Thread hilo5 = new Thread(tarea5);
        Thread hilo6 = new Thread(tarea6);
        Thread hilo7 = new Thread(tarea7);

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        hilo6.start();
        hilo7.start();


        hilo1.join();
        hilo2.join();
        hilo3.join();
        hilo4.join();
        hilo5.join();
        hilo6.join();
        hilo7.join();

        System.out.println("Valor final del contador " + contador.getContador());


    }
}