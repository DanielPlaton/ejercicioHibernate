package ejercicioHibernate.ejercicioHibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
		Transaction tx = null;
		MyLogger.createLogger();

		logger.info("Iniciando programa");
		Session session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();

		
	
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
				Empleado e= new Empleado(2,"dani","perez","perez","zamora","15-06-20","Calle argentina","666869935","Informatico",4);
				
				
					boolean existe = buscarId(e,session);
					
						System.out.println("El id ya existe en la base de datos añade otro");
						int idnuevo= s.nextInt();
						e.setCodigo(idnuevo);
					
						EmpleadoDAO.inserEmpleado(session, 2, e);
						tx.commit();
						
				
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


	private static boolean buscarId(Empleado e, Session session) {
		boolean existe=false;
		List<Empleado> listaEmpleadosBuscar = EmpleadoDAO.getAllEmpleados(session);
		for(int i=0;i<listaEmpleadosBuscar.size() ;i++) {
			if(listaEmpleadosBuscar.get(i).getCodigo() == e.getCodigo()) {
				existe= true;
			}else {
				existe= false;
			}
		}
		return existe;
	}
}
