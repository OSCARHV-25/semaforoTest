package issues.Solucion.Semaforo;

public class SemaforoBinario {

    private boolean control = true;



    public synchronized void acquire() throws InterruptedException {

        while(!control){
            wait();
        }
        control = false;
    }


    public synchronized void release(){
        control = true;
        notify();
    }
}