package es.etg.dam.crit;

import java.util.ArrayList;
import java.util.List;

import es.etg.dam.hilos.Cliente;
import es.etg.dam.hilos.Vendedor;

public class Supermercado {

    final String SALTO_LINEA = "\n";

    
    private List<Caja> cajas = new ArrayList<>();

    public Supermercado(int numCajas){

        for (int i = 1; i < numCajas+1; i++) {
            abrirCaja(i);
        }

    }

    public void addNewCliente(){

        Thread cliente = new Thread(new Cliente(getBestCaja()));
        cliente.start();

    }

    public String getEstadoCajas(){

        StringBuilder estado = new StringBuilder();

        for (Caja caja : cajas) {
            estado.append(caja.getEstado()).
                append(SALTO_LINEA);
        }

        return estado.toString();
    }

    private void abrirCaja(int numero){

        final String NOMBRE_CAJA = "Caja%s".formatted(numero);

        Caja caja = new Caja(NOMBRE_CAJA);

        Thread vendedor = new Thread(new Vendedor(caja));
        vendedor.start();

        cajas.add(caja);

    }

    private Caja getBestCaja(){

        final int DEFAULT_VALUE = 0;
        final boolean DEFAULT_VALUE_CAJAS = true;

        boolean isCajasLlenas = DEFAULT_VALUE_CAJAS;
        int idCajaMasLibre = DEFAULT_VALUE;
        int numCajaMasLibre = cajas.get(DEFAULT_VALUE).getNumPersonas();
            
        for (Caja caja : cajas) {

            int numeroPCajaActual = caja.getNumPersonas();

            if (numeroPCajaActual < caja.getNumMaximoPPer()) {
                isCajasLlenas = false;
            }

            if (numeroPCajaActual < numCajaMasLibre) {
                idCajaMasLibre = cajas.indexOf(caja);
            }
        }
        return cajas.get(idCajaMasLibre);
    }

}
