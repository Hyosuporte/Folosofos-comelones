package modelo;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author carlos andres
 */

public class Cuchara {
    private ReentrantLock ocupado;
    
    public Cuchara(){
        ocupado = new ReentrantLock();
    }
    
    public void tomando(){
        ocupado.lock();
    }
    
    public void desocupado(){
        if(ocupado.isHeldByCurrentThread()){
            ocupado.unlock();
        } else{
            return;
        }
    }
}