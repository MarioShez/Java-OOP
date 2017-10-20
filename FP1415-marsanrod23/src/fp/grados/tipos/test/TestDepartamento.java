package fp.grados.tipos.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import fp.grados.excepciones.ExcepcionCentroNoValido;
import fp.grados.excepciones.ExcepcionCentroOperacionNoPermitida;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.TipoAsignatura;


public class TestDepartamento {

	public static void main(String[] args) {
		
		testConstructorNormal();
		testNuevaAsignatura();
		testEliminaAsignatura();
		testNuevoProfesor();
		testEliminaProfesor();
		testBorraTutorias();
		testBorraTutorias2();
		testExisteProfesorAsignado();
		testEstanTodasAsignaturasAsignadas();
		testEliminaAsignacionProfesorado();
	}

	
	//============================================NORMAL==========================================================
	private static void testConstructorNormal(){
		System.out.println("\n==================================Probando el primer constructor");
		testConstructor("nombreNombre");
	}
	
	
	//============================================EXCEPCIONES==========================================================	
	
	
	
	
	//============================================METODOS==========================================================
	private static void testNuevaAsignatura(){
		System.out.println("\n==================================Probando nuevaAsignatura");
		Departamento d = new DepartamentoImpl("Fisica aplicada");
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1, null);
		testNuevaAsignatura(a, d);
	}
	
	private static void testEliminaAsignatura(){
		System.out.println("\n==================================Probando EliminaAsignatura");
		Departamento d = new DepartamentoImpl("Fisica aplicada");
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1, null);
		testEliminaAsignatura(a, d);
	}
	
	private static void testNuevoProfesor(){
		System.out.println("\n==================================Probando NuevoProfesor");
		Departamento d = new DepartamentoImpl("Fisica aplicada");
		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.INTERINO, null);
		testNuevoProfesor(d, p1);
	}
	
	private static void testEliminaProfesor(){
		System.out.println("\n==================================Probando EliminaProfesor");
		Departamento d = new DepartamentoImpl("Fisica aplicada");
		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.INTERINO, null);
		testEliminaProfesor(p1, d);
	}
	
	private static void testBorraTutorias(){
		System.out.println("\n==================================Probando BorraTutorias");
		Departamento d = new DepartamentoImpl("Fisica aplicada");
		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.INTERINO, null);
		testBorraTutorias(d, p1);
	}
	
	private static void testBorraTutorias2(){
		System.out.println("\n==================================Probando BorraTutorias(Categoria c)");
		Departamento d = new DepartamentoImpl("Fisica aplicada");
		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.INTERINO, null);
		Profesor p2 = new ProfesorImpl("47309527E", "Juan", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.CATEDRATICO, null);
		testBorraTutorias2(d, p1, p2);
	}
	
	private static void testExisteProfesorAsignado(){
		System.out.println("\n==================================Probando ExisteProfesorAsignado");
		Departamento d = new DepartamentoImpl("Fisica aplicada");
		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.INTERINO, null);
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1, null);
		Asignatura b = new AsignaturaImpl("Fundamentos de Programación","2050801",12.0, TipoAsignatura.ANUAL, 1, null);
		testExisteProfesorAsignado(d, p1, a, b);
	}
	
	private static void testEstanTodasAsignaturasAsignadas(){
		System.out.println("\n==================================Probando EstanTodasAsignaturasAsignadas");
		Departamento d = new DepartamentoImpl("Fisica aplicada");
		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.INTERINO, null);
		Profesor p2 = new ProfesorImpl("47309527E", "Juan", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.INTERINO, null);
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1, null);
		Asignatura b = new AsignaturaImpl("Fundamentos de Programación","2050801",12.0, TipoAsignatura.ANUAL, 1, null);
		testEstanTodasAsignaturasAsignadas(d, p1, p2, a, b);
	}
	
	private static void testEliminaAsignacionProfesorado(){
		System.out.println("\n==================================Probando testEliminaAsignacionProfesorado");
		Departamento d = new DepartamentoImpl("Fisica aplicada");
		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.INTERINO, null);
		Profesor p2 = new ProfesorImpl("47309527E", "Juan", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.INTERINO, null);
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1, null);
		Asignatura b = new AsignaturaImpl("Fundamentos de Programación","2050801",12.0, TipoAsignatura.ANUAL, 1, null);
		testEliminaAsignacionProfesorado(d, p1, p2, a, b);
	}
	
	//============================================SETs==========================================================
	
	
	
	
	
	//============================================AUXILIARES==========================================================
										/******** CONSTRUCTOR ******/
	private static void testConstructor(String nombre){
		try {
			Departamento d = new DepartamentoImpl(nombre);
			mostrarDepartamento(d);
		} catch (ExcepcionCentroNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionCentroNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	
										/******** METODOS ******/
	private static void testNuevaAsignatura(Asignatura a, Departamento d) {

		try {
			System.out.println("Asignaturas del departamento: " + d.getAsignaturas());
			System.out.println("Asignaturas a meter en el departamento: " + a);
			d.nuevaAsignatura(a);
			System.out.println("Asignaturas del departamento actualizadas: " + d.getAsignaturas());
		} catch (ExcepcionCentroOperacionNoPermitida e1) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionCentroOperacionNoPermitida");
		} catch (Exception e1) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testEliminaAsignatura(Asignatura a, Departamento d) {

		try {
			d.nuevaAsignatura(a);
			System.out.println("Asignaturas del departamento: " + d.getAsignaturas());
			System.out.println("Asignaturas del departamento a borrar: " + a);
			d.eliminaAsignatura(a);
			System.out.println("Asignaturas del departamento actualizadas: " + d.getAsignaturas());
		} catch (ExcepcionCentroOperacionNoPermitida e1) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionCentroOperacionNoPermitida");
		} catch (Exception e1) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testNuevoProfesor( Departamento d, Profesor p1) {

		try {
			System.out.println("Profesores del departamento: " + d.getProfesores());
			System.out.println("Nuevo profesor a meter en el departamento: " + p1);
			d.nuevoProfesor(p1);;
			System.out.println("Profesores del departamento actualizadas: " + d.getProfesores());
		} catch (ExcepcionCentroOperacionNoPermitida e1) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionCentroOperacionNoPermitida");
		} catch (Exception e1) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testEliminaProfesor(Profesor p1, Departamento d) {

		try {
			d.nuevoProfesor(p1);
			System.out.println("Profesores del departamento: " + d.getProfesores());
			System.out.println("Profesores del departamento a borrar: " + p1);
			d.eliminaProfesor(p1);
			System.out.println("Profesores del departamento actualizadas: " + d.getProfesores());
		} catch (ExcepcionCentroOperacionNoPermitida e1) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionCentroOperacionNoPermitida");
		} catch (Exception e1) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testBorraTutorias(Departamento d, Profesor p1) {

		try {
			d.nuevoProfesor(p1);
			p1.nuevaTutoria(LocalTime.of(9, 00), 100, DayOfWeek.FRIDAY);;
			System.out.println("tutorias del profesor p1: " + p1.getTutorias());
			d.borraTutorias();
			System.out.println("tutorias del profesor p1 actualizadas: " + p1.getTutorias());
		} catch (ExcepcionCentroOperacionNoPermitida e1) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionCentroOperacionNoPermitida");
		} catch (Exception e1) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}

	private static void testBorraTutorias2(Departamento d, Profesor p1, Profesor p2) {

		try {
			d.nuevoProfesor(p1);
			d.nuevoProfesor(p2);
			p1.nuevaTutoria(LocalTime.of(9, 00), 100, DayOfWeek.FRIDAY);
			p2.nuevaTutoria(LocalTime.of(10, 00), 100, DayOfWeek.MONDAY);
			System.out.println("tutorias del profesor p1: " + p1.getTutorias() + ", p2: " + p2.getTutorias());
			d.borraTutorias(Categoria.INTERINO);
			d.borraTutorias(Categoria.CATEDRATICO);
			System.out.println("tutorias del profesor p1 actualizadas: " + p1.getTutorias() +
					"\ntutorias del profesor p2 actualizadas:" + p2.getTutorias());
		} catch (ExcepcionCentroOperacionNoPermitida e1) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionCentroOperacionNoPermitida");
		} catch (Exception e1) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testExisteProfesorAsignado(Departamento d, Profesor p1, Asignatura a, Asignatura b) {

		try {
			d.nuevaAsignatura(a);
			d.nuevaAsignatura(b);
			d.nuevoProfesor(p1);
			p1.imparteAsignatura(a, 12.);
			System.out.println("El profesor p1 imparte la asignatura a: " + d.existeProfesorAsignado(a));
			System.out.println("El profesor p1 imparte la asignatura b: " + d.existeProfesorAsignado(b));
		} catch (ExcepcionCentroOperacionNoPermitida e1) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionCentroOperacionNoPermitida");
		} catch (Exception e1) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testEstanTodasAsignaturasAsignadas(Departamento d, Profesor p1, Profesor p2, Asignatura a, Asignatura b) {

		try {
			d.nuevaAsignatura(a);
			d.nuevaAsignatura(b);
			d.nuevoProfesor(p1);
			d.nuevoProfesor(p2);
			p1.imparteAsignatura(a, 12.);
			p1.imparteAsignatura(b, 12.);
			System.out.println("El profesor p1 imparte todas las asignaturas: " + d.estanTodasAsignaturasAsignadas());
			System.out.println("El profesor p2 imparte todas las asignaturas: " + d.estanTodasAsignaturasAsignadas());
		} catch (ExcepcionCentroOperacionNoPermitida e1) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionCentroOperacionNoPermitida");
		} catch (Exception e1) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testEliminaAsignacionProfesorado(Departamento d, Profesor p1, Profesor p2, Asignatura a, Asignatura b) {

		try {
			d.nuevaAsignatura(a);
			d.nuevaAsignatura(b);
			d.nuevoProfesor(p1);
			d.nuevoProfesor(p2);
			p1.imparteAsignatura(a, 12.);
			p1.imparteAsignatura(b, 12.);
			p2.imparteAsignatura(b, 12.);
			System.out.println("El profesor p1 imparte las asignaturas: " + p1.getAsignaturas());
			System.out.println("El profesor p2 imparte las asignaturas: " + p2.getAsignaturas());
			d.eliminaAsignacionProfesorado(b);
		} catch (ExcepcionCentroOperacionNoPermitida e1) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionCentroOperacionNoPermitida");
		} catch (Exception e1) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	
	
										/******** NORMAL ******/
	private static void mostrarDepartamento(Departamento d) {
		System.out.println("Nombre : " + d.getNombre());
		System.out.println("Asignaturas: " + d.getAsignaturas());
		System.out.println("Profesores: " + d.getProfesores());
	}
	
	
	
	
	
	
	
	
	
}
