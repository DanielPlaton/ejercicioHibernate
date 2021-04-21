package ejercicioHibernate.ejercicioHibernate;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import DAO.EmpleadoDAO;
import utils.*;
import modelo.*;



import controlador.MyLogger;


/**
 * 
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
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	
    	EmpleadoDAO a = new EmpleadoDAO();
    	
    	
    	
    
        
    }
}
