package fp.grados.tipos.test;

import fp.grados.excepciones.ExcepcionExpedienteOperacionNoPermitida;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Convocatoria;
import fp.grados.tipos.Expediente;
import fp.grados.tipos.ExpedienteImpl;
import fp.grados.tipos.Nota;
import fp.grados.tipos.NotaImpl;
import fp.grados.tipos.TipoAsignatura;

public class testExpediente {

	public static void main(String[] args) {
		
		testConstructorNormal();
		testNuevaNota();
		testNuevaNota2();
		testNotaMedia();

	}

	//============================================NORMAL==========================================================
	private static void testConstructorNormal(){
		System.out.println("\n==================================Probando el primer constructor");
		testConstructor();
	}
	
	
	
	
	//============================================EXCEPCIONES==========================================================	
	
	
	
	//============================================METODOS==========================================================
	private static void testNuevaNota(){
		System.out.println("\n==================================Probando nuevaNota");
		Expediente ex = new ExpedienteImpl();
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1, null);
		Nota n = new NotaImpl(a, 1, Convocatoria.PRIMERA, 6.);
		testNuevaNota(ex, n);
	}
	
	private static void testNuevaNota2(){
		System.out.println("\n==================================Probando nuevaNota2");
		Expediente ex = new ExpedienteImpl();
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1, null);
		Nota n = new NotaImpl(a, 1, Convocatoria.PRIMERA, 6.);
		Nota n2 = new NotaImpl(a, 1, Convocatoria.PRIMERA, 6.);
		testNuevaNota2(ex, n, n2);
	}
	
	private static void testNotaMedia(){
		System.out.println("\n==================================Probando notaMedia");
		Expediente ex = new ExpedienteImpl();
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1, null);
		Asignatura b = new AsignaturaImpl("Fundamentos de Programación","2070001",12.0, TipoAsignatura.ANUAL, 1, null);
		Nota n = new NotaImpl(a, 1, Convocatoria.PRIMERA, 10.);
		Nota n2 = new NotaImpl(b, 1, Convocatoria.PRIMERA, 0.);
		testNotaMedia(ex, n, n2);
	}
	
	
	
	
	//============================================SETs==========================================================
	
	
	
	
	//============================================AUXILIARES==========================================================
										/******** CONSTRUCTOR ******/
	private static void testConstructor(){
		try {
			Expediente ex = new ExpedienteImpl();
			mostrarExpediente(ex);
		} catch (ExcepcionExpedienteOperacionNoPermitida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionExpedienteOperacionNoPermitida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	
	
										/******** METODOS ******/
	private static void testNuevaNota(Expediente ex, Nota n) {

		try {
			System.out.println("Las notas del expediente son: " + ex.getNotas());
			ex.nuevaNota(n);
			System.out.println("Las notas del expediente actualizadas son: " + ex.getNotas());
		} catch (ExcepcionExpedienteOperacionNoPermitida e1) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionCentroOperacionNoPermitida");
		} catch (Exception e1) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testNuevaNota2(Expediente ex, Nota n, Nota n2) {

		try {
			System.out.println("Las notas del expediente son: " + ex.getNotas());
			ex.nuevaNota(n);
			ex.nuevaNota(n2);
			System.out.println("Las notas del expediente actualizadas son: " + ex.getNotas());
		} catch (ExcepcionExpedienteOperacionNoPermitida e1) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionCentroOperacionNoPermitida");
		} catch (Exception e1) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testNotaMedia(Expediente ex, Nota n, Nota n2) {

		try {
			
			ex.nuevaNota(n);
			ex.nuevaNota(n2);
			System.out.println("Las notas del expediente son: " + ex.getNotas());
			System.out.println("Las notas medias del expediente son: " + ex.getNotaMedia());
		} catch (ExcepcionExpedienteOperacionNoPermitida e1) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionCentroOperacionNoPermitida");
		} catch (Exception e1) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
										/******** NORMAL ******/
	private static void mostrarExpediente(Expediente ex) {
		System.out.println("Notas: " + ex.getNotas());
	}
	
	
	
}
