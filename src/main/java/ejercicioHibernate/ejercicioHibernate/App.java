package ejercicioHibernate.ejercicioHibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import DAO.EmpleadoDAO;
import controlador.MyLogger;
import modelo.Empleado;
import utils.HibernateUtil;


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
    	
    	List<Empleado>listaEmpleados =  EmpleadoDAO.getAllEmpleados(session);
    	for(int i = 0; i<listaEmpleados.size();i++) {
    		System.out.println(listaEmpleados.get(i).toString());
    	}
    	
    	
    
    
        
    }
}
