package controlador;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.ImageIcon;
import modelo.Cuchara;
import modelo.filosofo;
import vista.Formulario;

/**
 *
 * @author carlos andres
 */

public class controlador extends Thread implements ActionListener{
    private List<Cuchara> cucharas;
    private List<filosofo> filosofos;
    private Iterator<Long> tiempo;
    Formulario objVista = new Formulario();

    public controlador() {
        objVista.setVisible(true);
        objVista.getBtnIniciar().addActionListener((ActionListener) this);
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
                // "filosofo"+?
                filosofos.add(filo);
            }
    }
    
    public void actionPerformed(ActionEvent e){
        System.out.println("hola");
        if(e.getSource() == objVista.getBtnIniciar()){
            run();
        }
        
    }
    
    public  synchronized long getTiempo(){
        return tiempo.next();
    }
    
    @Override
    public void run(){
        ExecutorService executorService = Executors.newFixedThreadPool(filosofos.size());
        for(filosofo f : filosofos){
            if(f.getNombre().equals("Filosofo 1")){
                System.out.println("1");
                objVista.getFilosofo1().setVisible(false);
            }
            if(f.getNombre().equals("Filosofo 2")){
                System.out.println("2");
                objVista.getFilosofo2().setBackground(Color.red);
            }
            if(f.getNombre().equals("Filosofo 3")){
                System.out.println("3");
                objVista.getFilosofo3().setForeground(Color.red);
            }
            if(f.getNombre().equals("Filosofo 4")){
                System.out.println("4");
                objVista.getFilosofo4().setForeground(Color.red);
            }
            if(f.getNombre().equals("Filosofo 5")){
                System.out.println("5");
                objVista.getFilosofo5().setForeground(Color.red);
            }
            executorService.submit(f);
        }
    }
    
}