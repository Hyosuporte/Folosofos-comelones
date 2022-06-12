package controlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import modelo.Cuchara;
import modelo.filosofo;

/**
 *
 * @author carlos andres
 */

public class controlador implements Runnable{
    private List<Cuchara> cucharas;
    private List<filosofo> filosofos;
    private Iterator<Long> tiempo;

    public controlador() {
        cucharas = new ArrayList<>();
        filosofos = new ArrayList<>();
        tiempo =  new Random().longs(2000,7000).iterator();
        
        for(int i=0; i<5; i++){
            Cuchara cuchara = new Cuchara();
            cucharas.add(cuchara);
        }
        
        for(int i=0; i<5; i++){
            int n = (i+1) % 5;
            Cuchara iz = cucharas.get(i);
            Cuchara de = cucharas.get(n);
            
            filosofo filo = new filosofo("Filosofo "+(i+1), iz, de, this);
            filosofos.add(filo);
        }
        
    }
    
    public  synchronized long getTiempo(){
        return tiempo.next();
    }
    
    @Override
    public void run(){
        ExecutorService executorService = Executors.newFixedThreadPool(filosofos.size());
        for(filosofo f : filosofos){
            executorService.submit(f);
        }
    }
    
}