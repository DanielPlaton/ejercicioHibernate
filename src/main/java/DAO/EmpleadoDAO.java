package DAO;

import java.util.List;

import org.hibernate.Session;

import modelo.Empleado;



public class EmpleadoDAO {
	
	public static List<Empleado> getAllEmpleados(Session s) {
		String hQuery = "from Empleado";
		List<Empleado> listaEmpleados = s.createQuery(hQuery, Empleado.class)
				   	   			           .list();
		return listaEmpleados;
	}
}
