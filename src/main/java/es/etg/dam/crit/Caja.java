package es.etg.dam.crit;

public class Caja{

    private final int NUMERO_MAXIMO_EN_CAJA = 10;

    private long finUltimaVenta;
    private long tMaximo;
    private long tMinimo;

    private int numPersonas = 0;
    private String nomCaja;

    public Caja(String nombreCaja){
        nomCaja = nombreCaja;
        finUltimaVenta = getCurrentTime();
    }

    public synchronized void pagar() throws InterruptedException{

        if(numPersonas == 0){
            wait();
        }

        numPersonas--;

        long finVenta = getCurrentTime();

        long tiempoTotalVenta = finVenta - finUltimaVenta;

        compareTime(tiempoTotalVenta);

        notify();
    }

    public synchronized void entrar() throws InterruptedException{

        if (numPersonas == 0) {
            notify();
        }
        if (numPersonas >= NUMERO_MAXIMO_EN_CAJA) {
            wait();
        }

        numPersonas++;

    }

    public String getEstado(){
        final String NOM_CAJA = "Caja numero %s: ".formatted(nomCaja);
        final String ESPACIO = " ";
        final String CHAR_ESPERANDO = "O";
        final String CHAR_VACIO = "-";

        int huecosLibresEnFila =  NUMERO_MAXIMO_EN_CAJA - numPersonas;

        StringBuilder msg = new StringBuilder();
        msg.append(NOM_CAJA);

        for (int i = 0; i < numPersonas; i++) {
            msg.append(ESPACIO).
                append(CHAR_ESPERANDO);
        }

        for (int i = 0; i < huecosLibresEnFila; i++) {
            msg.append(ESPACIO).
                append(CHAR_VACIO);
        }
        
        return msg.toString();
    }

    public synchronized int getNumPersonas() {
        return numPersonas;
    }

    public int getNumMaximoPPer(){
        return NUMERO_MAXIMO_EN_CAJA;
    }

    private long getCurrentTime(){
        return System.currentTimeMillis();
    }

    private void compareTime(long tiempoTotalVenta){

        if (tiempoTotalVenta > tMaximo) {
            tMaximo = tiempoTotalVenta;
        } else if(tiempoTotalVenta < tMinimo){
            tMinimo = tiempoTotalVenta;
        }
    }
}

