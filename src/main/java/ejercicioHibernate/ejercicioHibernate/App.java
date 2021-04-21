package ejercicioHibernate.ejercicioHibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;



import DAO.EmpleadoDAO;
import controlador.MyLogger;
import modelo.Empleado;
import utils.HibernateUtil;
import java.util.Scanner;

/**
 * 
 * Hello world!
 *
 */
public class App {
	public static Logger logger = MyLogger.crearLogger(App.class);
	

	public static void main(String[] args)
	
	{
	
		int opcion=0;
		MyLogger.createLogger();

		logger.info("Iniciando programa");
		Session session = HibernateUtil.getSessionFactory().openSession();

		
	
		do {
			System.out.println("1. Introduce un numero para insertar un empleado");
			System.out.println("2. Introduce un numero para modificar un empleado");
			System.out.println("3. Introduce un numero para borrar un empleado");
			System.out.println("4. Introduce un numero para actualizar un empleado");
			System.out.println("5. listar empleados");
			Scanner s = new Scanner(System.in);
			opcion = s.nextInt();
			
			switch (opcion) {
			case 1:
				System.out.println("Introduce un numero para insertar un empleado");
				break;
				
			case 2:
				System.out.println("Introduce un numero para modificar un empleado");
				break;
			case 3:
				System.out.println("Introduce un numero para borrar un empleado");
				break;
			case 4:
				System.out.println("Introduce un numero para actualizar un empleado");
				break;
			case 5:
				System.out.println("listar empleados");
				
				List<Empleado> listaEmpleados = EmpleadoDAO.getAllEmpleados(session);
				for (int i = 0; i < listaEmpleados.size(); i++) {
					System.out.println(listaEmpleados.get(i).toString());

				}
				break;

			}

		} while (opcion != 6);

	}
}
