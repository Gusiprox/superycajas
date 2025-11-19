package es.etg.dam.hilos;

import es.etg.dam.crit.Caja;

public class Cliente implements Runnable{

    private  Caja caja;

    public Cliente(Caja caja){

        this.caja = caja;
    }

    @Override
    public void run() {

        caja.entrar();

    }
}