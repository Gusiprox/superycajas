package es.etg.dam;

import es.etg.dam.crit.Supermercado;
import es.etg.dam.hilos.ComunicadorEstado;

public class Apk {
    public static void main(String[] args) throws InterruptedException{

        final int CAJAS_CREADAS = 3;
        final int CLIENTES_ENTRANTES = 100;

        Supermercado supermercado = new Supermercado(CAJAS_CREADAS);

        Thread estado = new Thread(new ComunicadorEstado(supermercado));
        estado.start();

        for (int i = 0; i < CLIENTES_ENTRANTES; i++) {
            supermercado.addNewCliente();
        }

        Thread.sleep(45000);
        estado.interrupt();

        System.out.println(supermercado.getInforme());

    }
}
