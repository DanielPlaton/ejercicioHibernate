package DAO;

import java.util.List;

import org.hibernate.Session;



import modelo.Empleado;

public class EmpleadoDAO {

	public static List<Empleado> getAllEmpleados(Session s) {
		String hQuery = "from Empleado";

		List<Empleado> listaEmpleados = s.createQuery(hQuery, Empleado.class).list();
		return listaEmpleados;
	}

	public static Empleado getEmpleado(Session s, int id) {
		// s.get(Empleado.class, id);
		Empleado e = s.get(Empleado.class, id);

		return e;

	}

	public static void inserEmpleado(Session s, int id, Empleado e) {

		s.save(e);
	}
}
