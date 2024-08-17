package issues.racecondition;

public class Contador {
    private int contador = 0;

    public synchronized void incrementarContador(){
        contador ++;

    }

    public int getContador(){
        return contador;
}

}
