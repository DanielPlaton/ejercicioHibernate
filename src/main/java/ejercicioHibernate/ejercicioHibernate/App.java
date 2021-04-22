package ejercicioHibernate.ejercicioHibernate;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import DAO.DepartamentoDAO;
import DAO.EmpleadoDAO;
import controlador.MyLogger;
import modelo.Departamento;
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
		int existeCod;
		List<Empleado> listaEmpleados;
		List<Departamento> listaDepartamentos;
		Transaction tx = null;
		MyLogger.createLogger();

		logger.info("Iniciando programa");
		Session session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
		
		java.sql.Date date= new java.sql.Date(0);
		
		
		Empleado e = new Empleado(2, "dani", "perez", "perez", "zamora", date, "Calle argentina", "666869935",
				"Informatico", 3);
		Departamento d = new Departamento(2, "Informatico", 35);

		do {
			System.out.println("1. Introduce un numero para insertar un empleado");
			System.out.println("2. Introduce un numero para modificar un empleado");
			System.out.println("3. Introduce un numero para borrar un empleado");
			System.out.println("4. listar empleados");
			System.out.println("5. Introduce un numero para insertar un departamento");
			System.out.println("6. Introduce un numero para modificar un departamento");
			System.out.println("7. Introduce un numero para borrar un departamento");
			System.out.println("8. listar departamento");
			System.out.println("9. listar empleados que pertenezcan a un departamento");
			System.out.println("10. listar empleados que sean mayores a una fecha");
			Scanner s = new Scanner(System.in);
			opcion = s.nextInt();

			switch (opcion) {

			case 1:
				System.out.println("Insertando un empleado");
				existe = buscarId(e, session);
				EmpleadoDAO.getAllEmpleados(session);
				if (existe) {
					System.out.println("El codigo " + e.getCodigo() + " ya existe en la base de datos añade otro");
					e.toString();
					int idnuevo = s.nextInt();
					e.setCodigo(idnuevo);

				}
				if (!existe) {

					EmpleadoDAO.inserEmpleado(session, e);
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
				logger.info("Empleado borrado con codigo " + codigo2);

				break;

			case 4:
				System.out.println("listar empleados");

				listaEmpleados = EmpleadoDAO.getAllEmpleados(session);
				listarEmpleados(listaEmpleados);
				logger.info("Recuperada lista empleados");

				break;

			case 5:
				d.toString();
				boolean salir = false;
				do {
					System.out.println("Insertando un Departamento");
					existe = buscarIdDepartamento(d, session);

					if (existe) {
						listaDepartamentos = DepartamentoDAO.getAllDepartamentos(session);
						listarDepartamentos(listaDepartamentos);
						System.out.println("El codigo " + d.getCodigo() + " ya existe en la base de datos añade otro");
						d.toString();
						int idnuevo = s.nextInt();
						d.setCodigo(idnuevo);
						salir = false;

					}

					existeCod = existeCodEmpleado(d, session);
					if (existeCod == -1) {
						listaEmpleados = EmpleadoDAO.getAllEmpleados(session);
						listarEmpleados(listaEmpleados);
						System.out.println("El responsable " + d.getCodResponsable()
								+ " no existe en la tabla empleados pon un codigo de empleado valido");
						int idnuevo = s.nextInt();
						d.setCodResponsable(idnuevo);
						salir = false;
					}

					if (existe == false && existeCod != -1) {
						DepartamentoDAO.inserDepartamento(session, d);
						tx.commit();
						System.out.println("insertado departamento" + d.toString());
						logger.info("insertado departamento " + d.toString());
						salir = true;
					}

				} while (salir == true);
				break;

			case 7:
				System.out.println("Introduce un numero para borrar un Departamento");
				listaDepartamentos = DepartamentoDAO.getAllDepartamentos(session);
				listarDepartamentos(listaDepartamentos);

				Scanner sn3 = new Scanner(System.in);
				System.out.println("Introducce codigo del departamento a borrar ");
				int codigo3 = sn3.nextInt();

				DepartamentoDAO.borrarDepartamento(session, codigo3);
				logger.info("Departamento borrado con codigo " + codigo3);

				break;
			case 8:
				listaDepartamentos = DepartamentoDAO.getAllDepartamentos(session);
				listarDepartamentos(listaDepartamentos);
				logger.info("Recuperada lista departamentos");
				break;

			case 9:
				System.out.println("listar empleados que pertenezcan a un departamento");
				listaDepartamentos = DepartamentoDAO.getAllDepartamentos(session);
				listarDepartamentos(listaDepartamentos);

				Scanner sn4 = new Scanner(System.in);
				System.out.println("Introduce codigo del departamento por el que buscar sus empleados ");
				int nombreDepartamento = sn4.nextInt();
				List<Empleado> listaEmpleadosDepartamento = EmpleadoDAO.getEmpleadoDeDepartamento(session,
						nombreDepartamento);
				listarEmpleados(listaEmpleadosDepartamento);

				break;
				
			case 10:
				System.out.println("listar empleados que pertenezcan a mayores a una fecha");
				listaEmpleados = EmpleadoDAO.getAllEmpleados(session);
				listarEmpleados(listaEmpleados);
				Scanner sn5 = new Scanner(System.in);
				System.out.println("Introduce una fecha en el formato aaaa.MM.dd");
				int fecha = sn5.nextInt();				
				
				EmpleadoDAO.getEmpleadoFecha(session,fecha);
				break;
			}

		} while (opcion != 11);

	}

	private static void listarEmpleados(List<Empleado> listaEmpleados) {
		// TODO Auto-generated method stub
		for (int i = 0; i < listaEmpleados.size(); i++) {
			System.out.println(listaEmpleados.get(i).toString());

		}

	}

	private static void listarDepartamentos(List<Departamento> listaDepartamentos) {
		// TODO Auto-generated method stub
		for (int i = 0; i < listaDepartamentos.size(); i++) {
			System.out.println(listaDepartamentos.get(i).toString());

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

	private static boolean buscarIdDepartamento(Departamento d, Session session) {
		boolean existe = false;
		List<Departamento> listaDepartamentoBuscar = DepartamentoDAO.getAllDepartamentos(session);
		for (int i = 0; i < listaDepartamentoBuscar.size(); i++) {
			if (listaDepartamentoBuscar.get(i).getCodigo() == d.getCodigo()) {
				existe = true;
				break;
			} else {
				existe = false;
			}
		}
		return existe;
	}

	private static int existeCodEmpleado(Departamento d, Session session) {
		int existe = 0;
		List<Empleado> listaEmpleadosBuscar = EmpleadoDAO.getAllEmpleados(session);
		for (int i = 0; i < listaEmpleadosBuscar.size(); i++) {
			if (listaEmpleadosBuscar.get(i).getCodigo() == d.getCodResponsable()) {
				existe = listaEmpleadosBuscar.get(i).getCodigo();
				break;
			} else {
				existe = -1;
			}
		}
		return existe;
	}
}
