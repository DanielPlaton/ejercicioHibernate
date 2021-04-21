package ejercicioHibernate.ejercicioHibernate;

import org.apache.log4j.Logger;

import controlador.MyLogger;


/**
 * Hello world!
 *
 */
public class App 
{
	public static Logger logger = MyLogger.crearLogger(App.class);
    public static void main( String[] args )
    {
    	
    	MyLogger.createLogger();
    
    	logger.info("Iniciando programa");
    
        
    }
}
