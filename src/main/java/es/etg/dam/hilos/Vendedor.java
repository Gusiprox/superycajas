package es.etg.dam.hilos;

import es.etg.dam.crit.Caja;

public class Vendedor implements Runnable{

    private Caja caja;

    public Vendedor(Caja caja){
        this.caja = caja;
    }

    @Override
    public void run() {

        while (true) { 
            try {
                caja.pagar();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    

}