package issues.Solucion.racecondition;

import issues.Solucion.Semaforo.SemaforoBinario;

public class HiloContador implements Runnable {

    private final Contador contador;

    private final SemaforoBinario semaforoBinario;


    public HiloContador(Contador contador, SemaforoBinario semaforoBinario) {
        this.contador = contador;
        this.semaforoBinario = semaforoBinario;
    }

    @Override

    public void run() {

        try{
            semaforoBinario.acquire();
            for (int i = 0; i < 1000; i++) {
                contador.incrementarContador();
            }
            semaforoBinario.release();
        }catch(InterruptedException ie){
            System.out.println("Sucedió un excepción interrumpida; " + ie.getMessage());
        }
    }
}