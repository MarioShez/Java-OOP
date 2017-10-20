package fp.grados.tipos.test;

import fp.grados.excepciones.ExcepcionAlumnoOperacionNoPermitida;
import fp.grados.excepciones.ExcepcionAsignaturaNoValida;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.TipoAsignatura;

public class TestAsignatura {

	public static void main(String[] args) {
		
	
			testConstructorNormal();
			
			testConstructorExcepcion1();
			testConstructorExcepcion2();
			testConstructorExcepcion3();
			testConstructorExcepcion4();
			testConstructorExcepcion5();
			testConstructorExcepcion6();
			testConstructorExcepcion7();
			
			testgetAcronimo();
		
			//testsetDepartamento();
		}
		
//============================================NORMAL==========================================================	
		private static void testConstructorNormal() {
			System.out.println("==================================Probando el constructor");
			Departamento d = new DepartamentoImpl("Fisica aplicada");
			testConstructor("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1, d);
		}

//============================================EXCEPCIONES==========================================================	
		private static void testConstructorExcepcion1() {
			System.out.println("==================================Probando el constructor, código de asignatura más largo");
			testConstructor("Fundamentos de Programación","20500010",12.0, TipoAsignatura.ANUAL, 1, null);
		}
		
		private static void testConstructorExcepcion2() {
			System.out.println("==================================Probando el constructor, código de asignatura más corto");
			testConstructor("Fundamentos de Programación","205000",12.0, TipoAsignatura.ANUAL, 1, null);
		}
		
		private static void testConstructorExcepcion3() {
			System.out.println("==================================Probando el constructor, código de asignatura no numérico");
			testConstructor("Fundamentos de Programación","2A50001",12.0, TipoAsignatura.ANUAL, 1, null);
		}
		
		private static void testConstructorExcepcion4() {
			System.out.println("==================================Probando el constructor, créditos incorrectos (0.0)");
			testConstructor("Fundamentos de Programación","2050001",0.0, TipoAsignatura.ANUAL, 1, null);
		}
			
		private static void testConstructorExcepcion5() {
			System.out.println("==================================Probando el constructor, créditos incorrectos (-1.0)");
			testConstructor("Fundamentos de Programación","2050001",-1.0, TipoAsignatura.ANUAL, 1, null);
		}
		
		
		private static void testConstructorExcepcion6() {
			System.out.println("==================================Probando el constructor, curso menor de 1");
			testConstructor("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, -2, null);
		}
		
		private static void testConstructorExcepcion7() {
			System.out.println("==================================Probando el constructor, curso mayor de 4");
			testConstructor("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 5, null);
		}
		
		
//============================================METODOS==========================================================	
	public static void testgetAcronimo(){
		System.out.println("\n==================================Probando getAcronimo");
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0, TipoAsignatura.ANUAL, 1, null);
		Asignatura a2 = new AsignaturaImpl("Redes de Computadores", "2055001", 12.0, TipoAsignatura.ANUAL, 3, null);
		testgetAcronimo(a, a2);
	}
		
	/*
	public static void testsetDepartamento(){
		System.out.println("\n==================================Probando SetDepartamento");
		Departamento dptOld = new DepartamentoImpl("Fisica Aplicada");
		Departamento dptNew = new DepartamentoImpl("Fundamentos de la Programacion");
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0, TipoAsignatura.ANUAL, 1, dptOld);
		Asignatura a2 = new AsignaturaImpl("Fisica", "1050001", 12.0, TipoAsignatura.ANUAL, 1, dptOld);
		Asignatura a3 = new AsignaturaImpl("Fundamentos de Programación", "2850001", 12.0, TipoAsignatura.ANUAL, 1, dptNew);
		Asignatura a4 = new AsignaturaImpl("IISSI", "2850001", 12.0, TipoAsignatura.ANUAL, 1, dptNew);
		testsetDepartamento(dptOld, dptNew, a);
	}
	/*	
		
//============================================AUXILIARES==========================================================
									/******** CONSTRUCTOR ******/
		private static void testConstructor(String nombre, String codigo, Double creditos,
				TipoAsignatura tipo, Integer curso, Departamento departamento) {
			try {
				Asignatura a = new AsignaturaImpl(nombre, codigo, creditos, tipo, curso, departamento);
				mostrarAsignatura(a);
			} catch (ExcepcionAsignaturaNoValida e) {
				System.out.println("******************** Se ha capturado la excepción ExcepcionAsignaturaNoValida");
			} catch (Exception e) {
				System.out.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
			}
		}

									/******** METODOS ******/
		private static void testgetAcronimo(Asignatura a, Asignatura a2){
			try {
				System.out.println("Las asignaturas son: " + "a= " + a + ", a2= " + a2);
				System.out.println("sus acronims son: a= " + a.getAcronimo() + ", a2= " + a2.getAcronimo());
			} catch (ExcepcionAlumnoOperacionNoPermitida e) {
				System.out
						.println("******************** Se ha capturado la excepción ExcepcionAsignaturaNoValida");
			} catch (Exception e) {
				System.out
						.println("******************** Se ha capturado una excepción inesperada.");
			}
		
		}
		
		/*
		private static void testsetDepartamento(Departamento dptOld, Departamento dptNew, Asignatura a){
			try {
				System.out.println("El antiguo departamento es: " + dptOld);
				System.out.println("Las asignaturas que pertenecen al dptOld son: " + dptOld.getAsignaturas());
				System.out.println("El nuevo departamento seria: " + dptNew);
				System.out.println("Las asignaturas que pertenecen al dptNew son: " + dptNew.getAsignaturas());
				System.out.println("Probando e metodo: " + a.setDepartamento(dptNew));
			} catch (ExcepcionAlumnoOperacionNoPermitida e) {
				System.out
						.println("******************** Se ha capturado la excepción ExcepcionAsignaturaNoValida");
			} catch (Exception e) {
				System.out
						.println("******************** Se ha capturado una excepción inesperada.");
			}
		
		}
		
	*/	
		
			/******************************** hashCode... **************************/
		Asignatura a1 = new AsignaturaImpl ("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1, null);
		
		
		
		
		
										/******** NORMAL ******/
		private static void mostrarAsignatura(Asignatura a) {		
			System.out.println("Assignatura --> <" + a + ">");
			System.out.println("\tNombre: <" + a.getNombre() + ">");
			System.out.println("\tCódigo: <" + a.getCodigo() + ">");
			System.out.println("\tCréditos: <" + a.getCreditos() + ">");
			System.out.println("\tTipo: <" + a.getTipo() + ">");
			System.out.println("\tCurso: <" + a.getCurso() + ">");
			System.out.println("\tDepartamento: <" + a.getDepartamento() + ">");
		}
	
	
	
	
	
		
	
	
	
	
	
	
}
