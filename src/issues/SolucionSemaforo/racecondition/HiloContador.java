package issues.SolucionSemaforo.racecondition;


import java.util.concurrent.Semaphore;

public class HiloContador implements Runnable {

    private final Contador contador;

    private final Semaphore semaforo;


    public HiloContador(Contador contador, Semaphore semaforoBinario) {
        this.contador = contador;
        this.semaforo = semaforoBinario;
    }

    @Override

    public void run() {

        try{
            semaforo.acquire();
            for (int i = 0; i < 1000; i++) {
                contador.incrementarContador();
            }
            semaforo.release();
        }catch(InterruptedException ie){
            System.out.println("Sucedió un excepción interrumpida; " + ie.getMessage());
        }
    }
}