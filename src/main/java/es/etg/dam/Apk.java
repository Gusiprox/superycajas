package es.etg.dam;

import es.etg.dam.crit.Caja;
import es.etg.dam.hilos.Cliente;
import es.etg.dam.hilos.Vendedor;

public class Apk {
    public static void main(String[] args) {

        Caja caja = new Caja();

        Vendedor vendedor = new Vendedor(caja);

        Cliente cliente = new Cliente(caja);
    }
}
