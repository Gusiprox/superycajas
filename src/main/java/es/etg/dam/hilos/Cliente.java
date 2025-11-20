package es.etg.dam.hilos;

import java.util.Random;
import es.etg.dam.crit.Caja;

public class Cliente implements Runnable{
    final int TIEMPO_MAXIMO = 3000;
    final int TIEMPO_MINIMO = 1000;

    private Caja caja;

    public Cliente(Caja caja){

        this.caja = caja;
    }

    @Override
    public void run() {

        try {

            //Thread.sleep(getRandomWait());
            caja.entrar();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private int getRandomWait(){

        Random ra = new Random();
        return ra.nextInt(TIEMPO_MAXIMO - TIEMPO_MINIMO) + TIEMPO_MINIMO;

    }
}