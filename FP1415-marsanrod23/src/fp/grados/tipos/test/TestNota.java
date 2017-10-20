package fp.grados.tipos.test;

import fp.grados.excepciones.ExcepcionNotaNoValida;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Nota;
import fp.grados.tipos.NotaImpl;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.tipos.Convocatoria;



public class TestNota {

	public static void main(String[] args) {
		testConstructor1Normal();
		testConstructor2Normal();
		testConstructor1Excepcion1();
		testConstructor1Excepcion2();
		testConstructor1Excepcion3();
		testConstructor2Excepcion1();
		testConstructor2Excepcion2();
	}
	
	
	//============================================NORMAL==========================================================	
	
	private static void testConstructor1Normal(){
		System.out.println("==================================Probando el constructor 1");
		Asignatura a = new AsignaturaImpl("FUNDAMENTOS DE PROGRAMACION", "0000230", 5., TipoAsignatura.PRIMER_CUATRIMESTRE, 1, null);
		Nota n = new NotaImpl(a, 1, Convocatoria.PRIMERA, 7. , false);
		mostrarNota(n);
	}
	
	private static void testConstructor2Normal(){
		System.out.println("==================================Probando el constructor 2");
		Asignatura a2 = new AsignaturaImpl("FUNDAMENTOS DE PROGRAMACION", "0000230", 5., TipoAsignatura.PRIMER_CUATRIMESTRE, 1, null);
		Nota n = new NotaImpl(a2, 1, Convocatoria.PRIMERA, 7.);
		mostrarNota(n);
	}
	
	
	
	
	//============================================EXCEPCIONES==========================================================	
										
										/******** CONSTRUCTOR 1 ******/
	
	private static void testConstructor1Excepcion1(){
		System.out.println("==================================Probando el constructor, valor demasiado pequeño");
		Asignatura a = new AsignaturaImpl("FUNDAMENTOS DE PROGRAMACION", "0000230", 2., TipoAsignatura.PRIMER_CUATRIMESTRE, 1, null);
		testConstructor1(a, 1, Convocatoria.PRIMERA, -7. , true);
	}
	
	private static void testConstructor1Excepcion2(){
		System.out.println("==================================Probando el constructor, valor demasiado grande");
		Asignatura a = new AsignaturaImpl("FUNDAMENTOS DE PROGRAMACION", "0000230", 2., TipoAsignatura.PRIMER_CUATRIMESTRE, 1, null);
		testConstructor1(a, 1, Convocatoria.PRIMERA, 15. , true);
	}
	
	private static void testConstructor1Excepcion3(){
		System.out.println("==================================Probando el constructor, mencion de honor");
		Asignatura a = new AsignaturaImpl("FUNDAMENTOS DE PROGRAMACION", "0000230", 2., TipoAsignatura.PRIMER_CUATRIMESTRE, 1, null);
		testConstructor1(a, 1, Convocatoria.PRIMERA, 8. , true);
	}
	
										/******** CONSTRUCTOR 2 ******/
	
	private static void testConstructor2Excepcion1(){
		System.out.println("==================================Probando el constructor, valor demasiado pequeño");
		Asignatura a2 = new AsignaturaImpl("FUNDAMENTOS DE PROGRAMACION", "0000230", 5., TipoAsignatura.PRIMER_CUATRIMESTRE, 1, null);
		testConstructor2(a2, 1, Convocatoria.PRIMERA, -2.);
	}
	
	private static void testConstructor2Excepcion2(){
		System.out.println("==================================Probando el constructor, valor demasiado grande");
		Asignatura a2 = new AsignaturaImpl("FUNDAMENTOS DE PROGRAMACION", "0000230", 5., TipoAsignatura.PRIMER_CUATRIMESTRE, 1, null);
		testConstructor2(a2, 1, Convocatoria.PRIMERA, 15.);
	}
	
	
	
	//============================================AUXILIARES==========================================================	
	
										/******** CONSTRUCTOR 1 ******/
	private static void testConstructor1(Asignatura asignatura, Integer cursoAcademico, Convocatoria convocatoria, Double valor,
			Boolean mencionHonor){
		try{
			Nota n = new NotaImpl(asignatura, cursoAcademico, convocatoria, valor, mencionHonor);
			mostrarNota(n);
		}catch(ExcepcionNotaNoValida e){
			System.out.println("******************** Se ha capturado la excepción ExcepcionNotaNoValida");
		}catch(Exception e){
			System.out.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}
	
										/******** CONSTRUCTOR 2 ******/
	private static void testConstructor2(Asignatura asignatura, Integer cursoAcademico, Convocatoria convocatoria, Double valor){
		try{
			Nota n = new NotaImpl(asignatura, cursoAcademico, convocatoria, valor);
			mostrarNota(n);
		}catch(ExcepcionNotaNoValida e){
			System.out.println("******************** Se ha capturado la excepción ExcepcionNotaNoValida");
		}catch(Exception e){
			System.out.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}
	
										/******** NORMAL ******/
	private static void mostrarNota(Nota n){
		System.out.println("Nombre: " + n.getAsignatura());
		System.out.println("Curso: " + n.getCursoAcademico());
		System.out.println("Convocatoria: " + n.getConvocatoria());
		System.out.println("Valor: " + n.getValor());
		System.out.println("Mencion de honor: " + n.getMencionHonor());
		System.out.println("Nota: " + n);
	}
	

	
	
	
}
