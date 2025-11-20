package es.etg.dam.crit;

public class Caja{

    final String ESPACIO = " ";
    final String SALTO_LINEA = "\n";
    private final int NUMERO_MAXIMO_EN_CAJA = 10;

    private long finUltimaVenta;
    private long tMaximo;
    private long tMinimo = 4000;
    private int numPersonasAtendidas = 0;

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
        numPersonasAtendidas++;
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

    public String getInforme(){
        String strgNombre = "%s:".formatted(nomCaja);
        String strgPersonasAtendidas = "Personas atendidas: %d".formatted(numPersonasAtendidas);
        String strgTMaximo = "Tiempo maximo: %d".formatted(tMaximo);
        String strgTMinimo = "Tiempo minimo: %d".formatted(tMinimo);

        StringBuilder msg = new StringBuilder();

        msg.append(strgNombre).append(SALTO_LINEA).
            append(ESPACIO).append(strgPersonasAtendidas).append(SALTO_LINEA).
            append(ESPACIO).append(strgTMaximo).append(SALTO_LINEA).
            append(ESPACIO).append(strgTMinimo).append(SALTO_LINEA).append(SALTO_LINEA);

        return msg.toString();
    }

    public String getEstado(){
        final String NOM_CAJA = "Caja numero %s: ".formatted(nomCaja);
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
        } 
        if(tiempoTotalVenta < tMinimo){
            tMinimo = tiempoTotalVenta;
        }
    }
}

