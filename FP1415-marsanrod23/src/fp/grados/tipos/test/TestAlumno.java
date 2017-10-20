package fp.grados.tipos.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import fp.grados.excepciones.ExcepcionAlumnoNoValido;
import fp.grados.excepciones.ExcepcionAlumnoOperacionNoPermitida;
import fp.grados.tipos.Alumno;
import fp.grados.tipos.AlumnoImpl;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Convocatoria;
import fp.grados.tipos.TipoAsignatura;

public class TestAlumno {

	public static void main(String[] args) {
		
		testConstructorNormal();
		testConstructorExcepcion();

		testSetEmailNormal();
		testSetEmailExcepcion();

		testMatriculaAsignatura();
		testMatriculaAsignaturaExcepcion();

		testEliminaAsignatura();
		testEliminaAsignaturaExcepcion();
		testCursoAlumno();
		testEstaMatriculadoEn();
		testEvaluaAlumno();
		testEvaluaAlumno2();
	}
	
	
	//============================================NORMAL==========================================================	
	private static void testConstructorNormal() {
		System.out.println("\n==================================Probando el primer constructor");
		testConstructor("47309527E", "Mario", "Nadie Nadie", LocalDate.of(1993, 3, 15), "juan.nadie@alum.us.es");
	}

	//============================================EXCEPCIONES==========================================================	
	private static void testConstructorExcepcion() {
		System.out.println("\n==================================Probando el primer constructor, email incorrecto");
		testConstructor("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	
	private static void testMatriculaAsignaturaExcepcion() {
		System.out
				.println("\n==================================Probando matriculaAsignatura, matricula doble en una asignatura");
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programación",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1, null);
		a.matriculaAsignatura(asig);
		testMatriculaAsignatura(a, asig);
	}
	
	private static void testEliminaAsignaturaExcepcion() {
		System.out.println("\n==================================Probando eliminaAsignatura, asignatura no matriculada");
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0, TipoAsignatura.ANUAL, 1, null);
		testEliminaAsignatura(a, asig);
	}
	
	
	//============================================METODOS==========================================================	
	private static void testMatriculaAsignatura() {
		System.out.println("\n==================================Probando matriculaAsignatura");
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0, TipoAsignatura.ANUAL, 1, null);
		testMatriculaAsignatura(a, asig);
	}

	

	private static void testEliminaAsignatura() {
		System.out
				.println("\n==================================Probando eliminaAsignatura");
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programación",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1, null);
		a.matriculaAsignatura(asig);
		testEliminaAsignatura(a, asig);
		
	}
	
	
	public static void testCursoAlumno(){
		System.out.println("\n==================================Probando getCurso");
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0, TipoAsignatura.ANUAL, 1, null);
		Asignatura a2 = new AsignaturaImpl("Redes de Computadores", "2055001", 12.0, TipoAsignatura.ANUAL, 3, null);
		Asignatura a3 = new AsignaturaImpl("Algebra", "2056001", 12.0, TipoAsignatura.ANUAL, 2, null);
		Alumno alm = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		testCursoAlumno(alm, a, a2, a3);
		
	}
	
	
	public static void testEstaMatriculadoEn(){
		System.out.println("\n==================================Probando estaMatriculadoEn");
		Alumno alm = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0, TipoAsignatura.ANUAL, 1, null);
		Asignatura a2 = new AsignaturaImpl("Redes de Computadores", "2055001", 12.0, TipoAsignatura.ANUAL, 3, null);
		testEstaMatriculadoEn(alm, a, a2);
	}
	
	
	public static void testEvaluaAlumno(){
		System.out.println("\n==================================Probando evaluaAlumno");
		Alumno alm = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0, TipoAsignatura.ANUAL, 1, null);
		Asignatura a2 = new AsignaturaImpl("Redes de Computadores", "2055001", 12.0, TipoAsignatura.ANUAL, 3, null);
		testEvaluaAlumno(alm, a, a2);
	}
	
	public static void testEvaluaAlumno2(){
		System.out.println("\n==================================Probando evaluaAlumno2");
		Alumno alm = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0, TipoAsignatura.ANUAL, 1, null);
		Asignatura a2 = new AsignaturaImpl("Redes de Computadores", "2055001", 12.0, TipoAsignatura.ANUAL, 3, null);
		testEvaluaAlumno2(alm, a, a2);
	}
	
	//============================================SETs==========================================================	
	private static void testSetEmailNormal() {
		System.out.println("\n==================================Probando setEmail");

		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		testSetEmail(a, "juan@alum.us.es");
	}

	private static void testSetEmailExcepcion() {
		System.out
				.println("\n==================================Probando setEmail, email incorrecto");

		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		testSetEmail(a, "juan@gmail.com");
	}


	
	//============================================AUXILIARES==========================================================
										/******** CONSTRUCTOR ******/
	private static void testConstructor(String dni, String nombre, String apellidos, LocalDate fechaNacimiento, String email) {

		try {
			Alumno a = new AlumnoImpl(dni, nombre, apellidos, fechaNacimiento, email);
			mostrarAlumno(a);
		} catch (ExcepcionAlumnoNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionAlumnoNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}

	}

											/******** SETs ******/
	private static void testSetEmail(Alumno a, String nuevoEmail) {

		try {
			System.out.println("El email antes de la operación es: "
					+ a.getEmail());
			System.out.println("El nuevo email es: " + nuevoEmail);
			a.setEmail(nuevoEmail);
			System.out.println("El email después de la operación es: "
					+ a.getEmail());
		} catch (ExcepcionAlumnoNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionAlumnoNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}
	}

	 									/******** METODOS ******/
	private static void testMatriculaAsignatura(Alumno a, Asignatura asig) {

		try {
			System.out.println("Las asignaturas antes de la operación son: "
					+ a.getAsignaturas());
			System.out.println("Nueva asignatura a matricular: " + asig);
			a.matriculaAsignatura(asig);
			System.out.println("Las asignaturas después de la operación son: "
					+ a.getAsignaturas());
		} catch (ExcepcionAlumnoOperacionNoPermitida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionAlumnoOperacionNoPermitida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}
	}

	private static void testEliminaAsignatura(Alumno a, Asignatura asig) {

		try {
			System.out.println("Las asignaturas antes de la operación son: "+ a.getAsignaturas());
			System.out.println("Asignatura a eliminar: " + asig);
			a.eliminaAsignatura(asig);
			System.out.println("Las asignaturas después de la operación son: "
					+ a.getAsignaturas());
		} catch (ExcepcionAlumnoOperacionNoPermitida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionAlumnoOperacionNoPermitida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}
	}

			
	private static void testCursoAlumno(Alumno alm, Asignatura a, Asignatura a2, Asignatura a3) {

		try {
			System.out.println("Las asignaturas a matricuar son: "+ a + a2 + a3);
			alm.matriculaAsignatura(a);
			alm.matriculaAsignatura(a2);
			alm.matriculaAsignatura(a3);
			System.out.println("Asignaturas Matriculadas: " + alm.getAsignaturas());
			System.out.println("Curso de las asignaturas matriculadas: a=" + a.getCurso() + ", a2=" + a2.getCurso() + ", a3=" 
			+ a3.getCurso());
			System.out.println("Curso en el que esta matriculado el alumno: " + alm.getCurso());
		} catch (ExcepcionAlumnoOperacionNoPermitida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionAlumnoOperacionNoPermitida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testEstaMatriculadoEn(Alumno alm, Asignatura a, Asignatura a2){
		try {
			System.out.println("Las asignaturas a matricular son: "+"a = " + a);
			alm.matriculaAsignatura(a);
			System.out.println("Las asignaturas matriculadas son: " + alm.getAsignaturas());
			System.out.println("¿esta el alumno matriculado en a?: " + alm.estaMatriculadoEn(a));
			System.out.println("¿esta el alumno matriculado en a2?: " + alm.estaMatriculadoEn(a2));
		} catch (ExcepcionAlumnoOperacionNoPermitida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionAlumnoOperacionNoPermitida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	public static void testEvaluaAlumno(Alumno alm, Asignatura a, Asignatura a2){
		try {
			System.out.println("Las asignaturas a matricular son: "+"a = " + a);
			System.out.println("Las asignaturas no matriculadas son: "+"a2 = " + a2);
			alm.matriculaAsignatura(a);
			System.out.println("Las asignaturas matriculadas son: " + alm.getAsignaturas());
			alm.evaluaAlumno(a, 1, Convocatoria.PRIMERA, 5.);
			alm.evaluaAlumno(a2, 1, Convocatoria.PRIMERA, 5.);
			System.out.println("Las notas del expediente son: " + alm.getExpediente());
			System.out.println();
		} catch (ExcepcionAlumnoOperacionNoPermitida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionAlumnoOperacionNoPermitida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	public static void testEvaluaAlumno2(Alumno alm, Asignatura a, Asignatura a2){
		try {
			System.out.println("Las asignaturas a matricular son: "+"a = " + a);
			System.out.println("Las asignaturas no matriculadas son: "+"a2 = " + a2);
			alm.matriculaAsignatura(a);
			System.out.println("Las asignaturas matriculadas son: " + alm.getAsignaturas());
			alm.evaluaAlumno(a, 1, Convocatoria.PRIMERA, 9., true);
			alm.evaluaAlumno(a2, 1, Convocatoria.PRIMERA, 5., false);
			System.out.println("Las notas del expediente son: " + alm.getExpediente());
			System.out.println();
		} catch (ExcepcionAlumnoOperacionNoPermitida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionAlumnoOperacionNoPermitida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
											/******** NORMAL ******/
	private static void mostrarAlumno(Alumno a) {
		System.out.println("Alumno --> <" + a + ">");
		System.out.println("\tDNI: <" + a.getDNI() + ">");
		System.out.println("\tNombre: <" + a.getNombre() + ">");
		System.out.println("\tApellidos: <" + a.getApellidos() + ">");
		System.out.println("\tFecha Nacimiento: <"
				+ a.getFechaNacimiento().format(
						DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ">");
		System.out.println("\tEdad: <" + a.getEdad() + ">");
		System.out.println("\tEmail:  <" + a.getEmail() + ">");
		System.out.println("\tAsignaturas:  <" + a.getAsignaturas() + ">");
	}

	

}
