package es.etg.dam.hilos;

import java.util.Random;
import es.etg.dam.crit.Caja;

public class Vendedor implements Runnable{
    final int TIEMPO_MAXIMO = 2000;
    final int TIEMPO_MINIMO = 500;

    private Caja caja;

    public Vendedor(Caja caja){
        this.caja = caja;
    }

    @Override
    public void run() {

        while (true) { 
            try {

                Thread.sleep(getRandomWait());
                caja.pagar();

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private int getRandomWait(){

        Random ra = new Random();
        return ra.nextInt(TIEMPO_MAXIMO - TIEMPO_MINIMO) + TIEMPO_MINIMO;

    }
    

}