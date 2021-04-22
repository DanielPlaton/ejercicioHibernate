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

		int opcion = 0;
		boolean existe;
		List<Empleado> listaEmpleados;
		Transaction tx = null;
		MyLogger.createLogger();

		logger.info("Iniciando programa");
		Session session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
		Empleado e = new Empleado(2, "dani", "perez", "perez", "zamora", "15-06-20", "Calle argentina", "666869935",
				"Informatico", 4);

		do {
			System.out.println("1. Introduce un numero para insertar un empleado");
			System.out.println("2. Introduce un numero para modificar un empleado");
			System.out.println("3. Introduce un numero para borrar un empleado");
			System.out.println("4. listar empleados");
			Scanner s = new Scanner(System.in);
			opcion = s.nextInt();

			switch (opcion) {

			case 1:
				System.out.println("Insertando un empleado");
				existe = buscarId(e, session);

				if (existe) {
					System.out.println("El codigo ya existe en la base de datos añade otro");
					e.toString();
					int idnuevo = s.nextInt();
					e.setCodigo(idnuevo);

				}
				if(!existe){

					EmpleadoDAO.inserEmpleado(session, e.getCodigo(), e);
					tx.commit();
					logger.info("Empleado insertado con codigo " + e.getCodigo());
				}

				break;

			case 2:
				System.out.println("modificar un empleado");
				listaEmpleados = EmpleadoDAO.getAllEmpleados(session);
				listarEmpleados(listaEmpleados);

				Scanner sn = new Scanner(System.in);
				System.out.println("Introducce codigo del empleado a modificar ");
				int codigo = sn.nextInt();

				String nombre = "modificandonombre";

				EmpleadoDAO.actualizarEmpleado(session, codigo, nombre);
				logger.info("Empleado modificado del codigo " + e.getCodigo());
				tx.commit();
				break;
				
			case 3:
				System.out.println("Introduce un numero para borrar un empleado");
				listaEmpleados = EmpleadoDAO.getAllEmpleados(session);
				listarEmpleados(listaEmpleados);
				
				Scanner sn2 = new Scanner(System.in);
				System.out.println("Introducce codigo del empleado a borrar ");
				int codigo2 = sn2.nextInt();
				
				EmpleadoDAO.borrarEmpleado(session, codigo2);
				logger.info("Empleado borrado con codigo "+codigo2);
				
				break;

			case 4:
				System.out.println("listar empleados");

				listaEmpleados = EmpleadoDAO.getAllEmpleados(session);
				listarEmpleados(listaEmpleados);
				logger.info("Recuperada lista empleados");
			
				break;
			}

		} while (opcion != 5);

	}

	private static void listarEmpleados(List<Empleado> listaEmpleados) {
		// TODO Auto-generated method stub
		for (int i = 0; i < listaEmpleados.size(); i++) {
			System.out.println(listaEmpleados.get(i).toString());

		}

	}

	private static boolean buscarId(Empleado e, Session session) {
		boolean existe = false;
		List<Empleado> listaEmpleadosBuscar = EmpleadoDAO.getAllEmpleados(session);
		for (int i = 0; i < listaEmpleadosBuscar.size(); i++) {
			if (listaEmpleadosBuscar.get(i).getCodigo() == e.getCodigo()) {
				existe = true;
				break;
			} else {
				existe = false;
			}
		}
		return existe;
	}
}
