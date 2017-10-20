package fp.grados.tipos.test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import fp.grados.excepciones.ExcepcionDespachoNoValido;
import fp.grados.excepciones.ExcepcionProfesorNoValido;
import fp.grados.tipos.Despacho;
import fp.grados.tipos.DespachoImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.TipoEspacio;

public class TestDespacho {

	public static void main(String[] args) {
		
		testConstructor1Normal();
		testConstructor2Normal();
		testConstructor3Normal();
		testConstructor1Excepcion();
		testSetTipoExcepcion();
		testSetprofesores();
		
	}

	//============================================NORMAL==========================================================
	
	private static void testConstructor1Normal(){
		System.out.println("==================================Probando el constructor 1");
		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.INTERINO, null);
		Profesor p2 = new ProfesorImpl("47309527E", "Mario", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.INTERINO, null);
		Set<Profesor> prof = new HashSet<Profesor>();
		prof.add(p1);
		prof.add(p2);
		testConstructor1(TipoEspacio.OTRO, "M2.25", 25, 3, prof);
	}
	
	private static void testConstructor2Normal(){
		System.out.println("==================================Probando el constructor 2");
		Profesor p = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.INTERINO, null);
		testConstructor2(TipoEspacio.OTRO, "M2.25", 25, 3, p);
	}
	
	
	private static void testConstructor3Normal(){
		System.out.println("==================================Probando el constructor 3");
		testConstructor3(TipoEspacio.OTRO, "M2.25", 25, 3);
	}
	
	
	//============================================EXCEPCIONES==========================================================
	
									    /******** CONSTRUCTOR 1 ******/
	
	
	private static void testConstructor1Excepcion(){
		System.out.println("==================================Probando la excepcion del constructor 1, El número de profesores que ocupan un despacho superar la capacidad del mismo");
		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.INTERINO, null);
		Profesor p2 = new ProfesorImpl("47309527E", "Mario", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.INTERINO, null);
		Set<Profesor> prof = new HashSet<Profesor>();
		prof.add(p1);
		prof.add(p2);
		testConstructor1(TipoEspacio.OTRO, "M2.25", 1, 3, prof);
	}
	
	
	//============================================SETs==========================================================
	
	private static void testSetTipoExcepcion() {
		System.out.println("\n==================================Probando setTipo");
		//Profesor p = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", TipoCategoria.INTERINO);
		Despacho d = new DespachoImpl("M2.25", 25, 3);

		try {
			d.setTipo(TipoEspacio.EXAMEN);
		} catch (UnsupportedOperationException e) {
			System.out.println("******************** Se ha capturado la excepción UnsupportedOperationException");
		} catch (Exception e) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}

	}
		
		private static void testSetprofesores() {
			System.out.println("\n==================================Probando setProfesores");

			Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.INTERINO, null);
			Profesor p2 = new ProfesorImpl("47309527E", "Mario", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.INTERINO, null);
			//Profesor p3 = new ProfesorImpl("1234567X", "Paco", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", TipoCategoria.AYUDANTE);
			
			Set<Profesor> prof1 = new HashSet<Profesor>();
			prof1.add(p1);
									
			Despacho d = new DespachoImpl("M2.25", 25, 3, prof1);
			
			Set<Profesor> prof2 = new HashSet<Profesor>();
			prof2.add(p2);
			
			
			testsetProfesores(d, prof2);
		}
	
	//============================================AUXILIARES==========================================================
	
									   /******** CONSTRUCTOR 1 ******/
	
	private static void testConstructor1(TipoEspacio tipo, String nombre, Integer capacidad, Integer planta, Set<Profesor> profesores){
		try{
			Despacho d = new DespachoImpl(nombre, capacidad, planta, profesores);
			mostrarDespacho(d);
		}catch(ExcepcionDespachoNoValido e){
			System.out.println("******************** Se ha capturado la excepción ExcepcionDespachoNoValido");
		}catch(Exception e){
			System.out.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}
	
	
										/******** CONSTRUCTOR 2 ******/
	private static void testConstructor2(TipoEspacio tipo, String nombre, Integer capacidad, Integer planta, Profesor profesor){
		try{
			Despacho d = new DespachoImpl(nombre, capacidad, planta, profesor);
			mostrarDespacho(d);
		}catch(ExcepcionDespachoNoValido e){
			System.out.println("******************** Se ha capturado la excepción ExcepcionDespachoNoValido");
		}catch(Exception e){
			System.out.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}
	
	
									    /******** CONSTRUCTOR 3 ******/
	private static void testConstructor3(TipoEspacio tipo, String nombre, Integer capacidad, Integer planta){
		try{
			Despacho d = new DespachoImpl(nombre, capacidad, planta);
			mostrarDespacho(d);
		}catch(ExcepcionDespachoNoValido e){
			System.out.println("******************** Se ha capturado la excepción ExcepcionDespachoNoValido");
		}catch(Exception e){
			System.out.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}
	
	
	
	
												/******** SET ******/	
	private static void testsetProfesores(Despacho d, Set<Profesor> profesores) {

		try {
			System.out.println("El profesor antes de la operación es: "+ d.getProfesores());
			System.out.println("El profesor despues de la operación es: "+ profesores);
			d.setProfesores(profesores);
			System.out.println("El profesor después de la operación es: "+ d.getProfesores());
		} catch (ExcepcionProfesorNoValido e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionProfesorNoValido");
		} catch (Exception e) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
											/******** NORMAL ******/
	private static void mostrarDespacho(Despacho d){
		System.out.println("Tipo: " + d.getTipo());
		System.out.println("Nombre: " + d.getNombre());
		System.out.println("Capacidad: " + d.getCapacidad());
		System.out.println("Planta: " + d.getPlanta());
		System.out.println("Persona: " + d.getProfesores());

	}
	
	
	
	
	
	
	
	
}
