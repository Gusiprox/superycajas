package es.etg.dam.hilos;

import java.util.Random;
import es.etg.dam.crit.Caja;
import es.etg.dam.crit.Supermercado;

public class Cliente implements Runnable{
    final int TIEMPO_MAXIMO = 3000;
    final int TIEMPO_MINIMO = 1000;

    private Supermercado supermercado;

    public Cliente(Supermercado supermercado){

        this.supermercado = supermercado;
    }

    @Override
    public void run() {

        try {

            Thread.sleep(getRandomWait());
            supermercado.getBestCaja().entrar();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private int getRandomWait(){

        Random ra = new Random();
        return ra.nextInt(TIEMPO_MAXIMO - TIEMPO_MINIMO) + TIEMPO_MINIMO;

    }
}