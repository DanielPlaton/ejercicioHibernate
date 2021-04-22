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

	public static List<Empleado> getEmpleadoDeDepartamento(Session s, int departamento) {
		// s.get(Empleado.class, id);
		
		String hQuery = "from Empleado e where e.codDepartamento = :departamento";

		List<Empleado> listaEmpleados =  s.createQuery(hQuery, Empleado.class)
				.setParameter("departamento", departamento)
				.list();

		return listaEmpleados;

	}

	public static void inserEmpleado(Session s, Empleado e) {

		s.save(e);
		
	}
	
	public static void actualizarEmpleado(Session s, int id,String nombre) {
		// s.get(Empleado.class, id);
		
		Empleado e = s.get(Empleado.class, id);
		e.setNombre(nombre);
		s.update(e);
	

	}
	

	public static void borrarEmpleado(Session s, int id) {
		// s.get(Empleado.class, id);
		Empleado e = s.get(Empleado.class, id);

		s.delete(e);

	}
	
}
