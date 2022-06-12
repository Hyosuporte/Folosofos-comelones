package modelo;

import controlador.controlador;
import static java.lang.Thread.sleep;

/**
 *
 * @author carlos andres
 */

public class filosofo implements Runnable{
    private String nombre;
    private Cuchara cucharaIz;
    private Cuchara cucharaDe;
    private controlador tiempos;
    
    public filosofo(String nombre, Cuchara cucharaIz, Cuchara cucharaDe, controlador tiempos) {
        this.nombre = nombre;
        this.cucharaIz = cucharaIz;
        this.cucharaDe = cucharaDe;
        this.tiempos = tiempos;
    }
    
    public void pensando() throws InterruptedException {
        long tiempo = tiempos.getTiempo();
        System.out.println(nombre +" Estare pensando durante " + tiempo + "\n");
        sleep(tiempo);
    }
    
    public void comiendo() throws InterruptedException{
        tomarCuchara();
        long tiempo = tiempos.getTiempo();
        System.out.println(nombre +" Estare comiendo durante " + tiempo + "\n");
        sleep(tiempo);
        soltarCuchara();
    }
    
    public void tomarCuchara(){
        cucharaDe.tomando();
        cucharaIz.tomando();
    }

    public void soltarCuchara(){
        cucharaIz.desocupado();
        cucharaDe.desocupado();
    }
    
    @Override
    public void run() {
        while(true){
            try{
                pensando();
                comiendo();
            } catch(Exception e) {
                System.out.println("Error al " + e.getMessage());
            }
        }
    }
    
}