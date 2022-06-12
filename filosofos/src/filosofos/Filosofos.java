package filosofos;

import java.util.logging.Logger;
import controlador.controlador;

/**
 *
 * @author carlos andres
 */

public class Filosofos {

    private static final Logger logger = Logger.getLogger("Mi loggor");

    public static void main(String[] args) throws InterruptedException {
        logger.info("Comenzando la cena");
        controlador control = new controlador();
        Thread cenar = new Thread(control);
        logger.info("Comenzo la cena");
    }

}