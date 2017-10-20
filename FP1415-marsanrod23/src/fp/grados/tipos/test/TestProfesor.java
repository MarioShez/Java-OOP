package fp.grados.tipos.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import fp.grados.excepciones.ExcepcionProfesorNoValido;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.TipoAsignatura;

public class TestProfesor {

	public static void main(String[] args) {
		
	testConstructor();
	testConstructorExcepcion();
	testSetCategoria();
	
	testNuevaTutoria();
	testBorraTutoria();
	testBorraTutorias();
	testImparteAsignatura();
	testEliminaAsignatura();
	testGetDedicacionTotal();
	
		
	}
	
	//============================================NORMAL==========================================================	
	
	private static void testConstructor() {
		System.out.println("\n==================================Probando el primer constructor");
		testConstructor("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.INTERINO, null);
	}
	
	
	
	//============================================EXCEPCIONES==========================================================
	
	private static void testConstructorExcepcion() {
		System.out.println("\n==================================Probando la excepcion del constructor; eres menor de edad");
		testConstructor("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(2000, 10, 18), "vjkykku@us.es", Categoria.AYUDANTE, null);
	}
	
	
	//===============================================SETs==========================================================
	
	private static void testSetCategoria() {
		System.out.println("\n==================================Probando setCategoria");

		Profesor p = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.AYUDANTE, null);
		
		testsetCategoria(p, Categoria.TITULAR);
	}
	
	//============================================METODOS==========================================================
	private static void testNuevaTutoria(){
		System.out.println("\n==================================Probando nuevaTutoria");
		Profesor p = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.AYUDANTE, null);
		testNuevaTutoria(p);
	}
	
	private static void testBorraTutoria(){
		System.out.println("\n==================================Probando borraTutoria");
		Profesor p = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.AYUDANTE, null);
		testBorraTutoria(p);
	}
	
	private static void testBorraTutorias(){
		System.out.println("\n==================================Probando borraTutorias");
		Profesor p = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.AYUDANTE, null);
		testBorraTutorias(p);
	}
	
	private static void testImparteAsignatura(){
		System.out.println("\n==================================Probando imparteAsignaturas");
		Departamento d = new DepartamentoImpl("Fisica");
		Profesor p = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.AYUDANTE, d);
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación","2050001",7.0, TipoAsignatura.ANUAL, 1, d);
		testImparteAsignatura(p, a, d);
	}
	
	private static void testEliminaAsignatura(){
		System.out.println("\n==================================Probando eliminaAsignaturas");
		Profesor p = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.AYUDANTE, null);
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación","2050001",14.0, TipoAsignatura.ANUAL, 1, null);
		testEliminaAsignatura(p, a);
	}
	
	private static void testGetDedicacionTotal(){
		System.out.println("\n==================================Probando getDedicacionTotal");
		Profesor p = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.AYUDANTE, null);
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación","2050001",8.0, TipoAsignatura.ANUAL, 1, null);
		Asignatura b = new AsignaturaImpl("Fundamentos de Programación","2050801",14.0, TipoAsignatura.ANUAL, 1, null);
		testGetDedicacionTotal(p, a, b);
	}
	
	
	//============================================AUXILIARES==========================================================
	
								   	   /******** CONSTRUCTOR ******/
	
	private static void testConstructor(String dni, String nombre, String apellidos, LocalDate fechaNacimiento, String email, Categoria categoria, Departamento departamento) {

		try {
			Profesor p = new ProfesorImpl(dni, nombre, apellidos, fechaNacimiento, email, categoria, departamento);
			
			mostrarProfesor(p);
		} catch (ExcepcionProfesorNoValido e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionProfesorNoValido");
		} catch (Exception e) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}

	}
	
										  /******** SET ******/
	
	private static void testsetCategoria(Profesor p, Categoria categoria) {

		try {
			System.out.println("La categoria antes de la operación es: " + p.getCategoria());
			System.out.println("La nueva categoria es: " + categoria);
			p.setCategoria(categoria);
			System.out.println("La categoria después de la operación es: " + p.getCategoria());
		} catch (ExcepcionProfesorNoValido e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionProfesorNoValido");
		} catch (Exception e) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
										/******** METODOS ******/
	private static void testNuevaTutoria(Profesor p) {

		try {
			System.out.println("Las tutorias del profesor p son: " + p.getTutorias());
			p.nuevaTutoria(LocalTime.of(9, 00), 50, DayOfWeek.FRIDAY);
			System.out.println("Las tutorias actualizadas del profesor p son: " + p.getTutorias());
		} catch (ExcepcionProfesorNoValido e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionProfesorNoValido");
		} catch (Exception e) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testBorraTutoria(Profesor p) {

		try {
			p.nuevaTutoria(LocalTime.of(9, 00), 50, DayOfWeek.FRIDAY);
			p.nuevaTutoria(LocalTime.of(9, 00), 50, DayOfWeek.MONDAY);
			System.out.println("Las tutorias del profesor p son: " + p.getTutorias());
			p.borraTutoria(LocalTime.of(9, 00), DayOfWeek.FRIDAY);
			System.out.println("Las tutorias actualizadas del profesor p son: " + p.getTutorias());
		} catch (ExcepcionProfesorNoValido e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionProfesorNoValido");
		} catch (Exception e) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testBorraTutorias(Profesor p) {

		try {
			p.nuevaTutoria(LocalTime.of(9, 00), 50, DayOfWeek.FRIDAY);
			p.nuevaTutoria(LocalTime.of(9, 00), 50, DayOfWeek.MONDAY);
			System.out.println("Las tutorias del profesor p son: " + p.getTutorias());
			p.borraTutorias();
			System.out.println("Las tutorias actualizadas del profesor p son: " + p.getTutorias());
		} catch (ExcepcionProfesorNoValido e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionProfesorNoValido");
		} catch (Exception e) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testImparteAsignatura(Profesor p, Asignatura a, Departamento d) {

		try {
			System.out.println("Las Asignaturas impartidas por p son: " + p.getAsignaturas());
			
			p.imparteAsignatura(a, 6.);
			System.out.println("Las Asignaturas impartidas actualizadas por p son: " + p.getAsignaturas());
		} catch (ExcepcionProfesorNoValido e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionProfesorNoValido");
		} catch (Exception e) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testEliminaAsignatura(Profesor p, Asignatura a) {

		try {
			
			p.imparteAsignatura(a, 12.);
			System.out.println("Las Asignaturas impartidas por p son: " + p.getAsignaturas());
			p.eliminaAsignatura(a);
			System.out.println("Las Asignaturas impartidas actualizadas por p son: " + p.getTutorias());
		} catch (ExcepcionProfesorNoValido e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionProfesorNoValido");
		} catch (Exception e) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testGetDedicacionTotal(Profesor p, Asignatura a, Asignatura b) {

		try {
			
			p.imparteAsignatura(a, 6.);
			p.imparteAsignatura(b, 12.);
			System.out.println("los creditos de a son: " + a.getCreditos() + " y los creditos de b son: " + b.getCreditos());
			System.out.println("Los creditos totales impartidos por p son: " + p.getDedicacionTotal());
		} catch (ExcepcionProfesorNoValido e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionProfesorNoValido");
		} catch (Exception e) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	
										 /******** NORMAL ******/
	
	private static void mostrarProfesor(Profesor p) {
		System.out.println("Profesor --> <" + p + ">");
		System.out.println("\tDNI: <" + p.getDNI() + ">");
		System.out.println("\tNombre: <" + p.getNombre() + ">");
		System.out.println("\tApellidos: <" + p.getApellidos() + ">");
		System.out.println("\tFecha Nacimiento: <" + p.getFechaNacimiento() + ">");
		System.out.println("\tEmail: <" + p.getEmail() + ">");
		System.out.println("\tTipo categoria: <" + p.getCategoria() + ">");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
