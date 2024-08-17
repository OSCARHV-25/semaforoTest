package main;


import issues.racecondition.Contador;
import issues.racecondition.HiloContador;



public class Main {
    public static void main(String[] args) throws InterruptedException {


        Contador contador = new Contador();
        Runnable tarea1 = new HiloContador(contador);
        Runnable tarea2 = new HiloContador(contador);

        Thread hilo1 = new Thread(tarea1);
        Thread hilo2 = new Thread(tarea2);

        hilo1.start();
        hilo2.start();

        hilo1.join();
        hilo2.join();

        System.out.println("Valor final del contador " + contador.getContador());
    }
}