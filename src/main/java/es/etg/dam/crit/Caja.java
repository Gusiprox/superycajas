package es.etg.dam.crit;

public class Caja{

    private int tMaximo;
    private int tMinimo;
    private int numPersonas = 0;


    synchronized  public void pagar() throws InterruptedException{

        if(numPersonas == 0){
            wait();
        }
        numPersonas--;
        System.err.println("Se ha atendido al cliente");
    }

    synchronized public void entrar(){

        numPersonas++;
        System.out.println("Ha entrado un cliente");
        notify();
        
    }
}

