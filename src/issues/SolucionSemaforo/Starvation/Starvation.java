package issues.SolucionSemaforo.Starvation;



import java.util.concurrent.Semaphore;

public class Starvation {
    public static void main(String[] args) {
        Semaphore semaforo = new Semaphore(1);
        Thread hiloAltaPrioridad = new Thread(new TareaAltaPrioridad(semaforo), "Hilo alta prioridad") ;
        Thread hiloBajaPrioridad = new Thread(new TareaBajaPrioridad(semaforo), "Hilo baja prioridad" );



        hiloAltaPrioridad.setPriority(Thread.MAX_PRIORITY);
        hiloBajaPrioridad.setPriority(Thread.MIN_PRIORITY);

        hiloAltaPrioridad.start();
        hiloBajaPrioridad.start();


    }

    static class TareaAltaPrioridad implements Runnable {

        private final Semaphore semaforo;


        public TareaAltaPrioridad(Semaphore semaforo) {
            this.semaforo = semaforo;
        }
        @Override
        public void run() {

            while(true) {
                System.out.println("Hilo de alta prioridad ejecutandose");

                try {
                    semaforo.acquire();
                    Thread.sleep(100);
                    semaforo.release();
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    static class TareaBajaPrioridad implements Runnable {


        private final Semaphore semaforo;


        public TareaBajaPrioridad(Semaphore semaforo) {
            this.semaforo = semaforo;
        }
        @Override
        public void run() {
            while(true) {
                System.out.println("Hilo de baja prioridad ejecutandose");

                try {
                    semaforo.acquire();
                    Thread.sleep(1000);
                    semaforo.release();
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }

        }
    }
}