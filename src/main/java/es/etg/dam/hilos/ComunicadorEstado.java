package es.etg.dam.hilos;

import es.etg.dam.crit.Supermercado;

public class ComunicadorEstado implements Runnable {
    final private int TIEMPO_ESPERA_ESTADO = 1000;

    private Supermercado supermercado;

    public ComunicadorEstado(Supermercado supermercado) {
        this.supermercado = supermercado;
    }

    @Override
    public void run() {

        try {
            while (true) {
                Thread.sleep(TIEMPO_ESPERA_ESTADO);
                System.out.println(supermercado.getEstadoCajas());
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
