package issues.Solucion.Starvation;

import issues.Solucion.Semaforo.SemaforoBinario;

public class Starvation {
    public static void main(String[] args) {
        SemaforoBinario semaforoBinario = new SemaforoBinario();
        Thread hiloAltaPrioridad = new Thread(new TareaAltaPrioridad(semaforoBinario), "Hilo alta prioridad") ;
        Thread hiloBajaPrioridad = new Thread(new TareaBajaPrioridad(semaforoBinario), "Hilo baja prioridad" );



        hiloAltaPrioridad.setPriority(Thread.MAX_PRIORITY);
        hiloBajaPrioridad.setPriority(Thread.MIN_PRIORITY);

        hiloAltaPrioridad.start();
        hiloBajaPrioridad.start();


    }

    static class TareaAltaPrioridad implements Runnable {

        private final SemaforoBinario semaforoBinario;


        public TareaAltaPrioridad(SemaforoBinario semaforoBinario) {
            this.semaforoBinario = semaforoBinario;
        }
        @Override
        public void run() {

            while(true) {
                System.out.println("Hilo de alta prioridad ejecutandose");

                try {
                    semaforoBinario.acquire();
                    Thread.sleep(100);
                    semaforoBinario.release();
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    static class TareaBajaPrioridad implements Runnable {


        private final SemaforoBinario semaforoBinario;


        public TareaBajaPrioridad(SemaforoBinario semaforoBinario) {
            this.semaforoBinario = semaforoBinario;
        }
        @Override
        public void run() {
            while(true) {
                System.out.println("Hilo de baja prioridad ejecutandose");

                try {
                    semaforoBinario.acquire();
                    Thread.sleep(1000);
                    semaforoBinario.release();
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }

        }
    }
}