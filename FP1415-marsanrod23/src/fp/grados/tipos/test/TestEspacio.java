package fp.grados.tipos.test;

import fp.grados.excepciones.ExcepcionEspacioNoValido;
import fp.grados.tipos.Espacio;
import fp.grados.tipos.EspacioImpl;
import fp.grados.tipos.TipoEspacio;

public class TestEspacio {

	public static void main(String[] args) {
		testConstructor1();
		testConstructorExcepcion1();
		
		testSetNombreNormal();
		testSetCapacidadNormal();
		testSetTipoNormal();
	}
	
	private static void testConstructor1(){
		System.out.println("==================================Probando el constructor 1");
		Espacio e = new EspacioImpl(TipoEspacio.LABORATORIO,"mario", 5, 3);
		mostrarEspacio(e);
	}
	
	//============================================EXCEPCIONES==========================================================	
	
	private static void testConstructorExcepcion1(){
		System.out.println("==================================Probando el constructor, la capacidad no puede ser negativa");
		testConstructor1(TipoEspacio.LABORATORIO, "mario", -5, 3);
	}
	
	
	//============================================SETs==========================================================
	
	private static void testSetNombreNormal(){
		System.out.println("==================================Probando setNombre");
		Espacio esp = new EspacioImpl(TipoEspacio.LABORATORIO, "Mario", 5, 3);
		testSetNombre(esp, "Paco");	
	}
	
	
	private static void testSetCapacidadNormal(){
		System.out.println("==================================Probando setCapacidad");
		Espacio esp = new EspacioImpl(TipoEspacio.LABORATORIO, "Mario", 5, 3);
		testSetCapacidad(esp, 7);	
	}
	
	
	private static void testSetTipoNormal(){
		System.out.println("==================================Probando setTipo");
		Espacio esp = new EspacioImpl(TipoEspacio.LABORATORIO,"Mario", 5, 3);
		testSetTipo(esp, TipoEspacio.TEORIA);	
	}
	
	//============================================AUXILIARES==========================================================
	
									/******** CONSTRUCTOR 1 ******/		
	
	private static void testConstructor1(TipoEspacio tipo, String nombre, Integer capacidad, Integer planta){
		try{
			Espacio e = new EspacioImpl(tipo, nombre, capacidad, planta);
			mostrarEspacio(e);
		}catch(ExcepcionEspacioNoValido e){
			System.out.println("******************** Se ha capturado la excepción ExcepcionEspacioNoValida");
		}catch(Exception e){
			System.out.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}
	
	
										 /******** SET ******/
	
	private static void testSetNombre(Espacio esp, String nombre){
		try {
			System.out.println("El nombre antes de la operación es: "+ esp.getNombre());
			System.out.println("El nuevo valor es: "+  nombre);
			esp.setNombre(nombre);
			System.out.println("El nombre después de la operación es: "+ esp.getNombre());
		} catch (ExcepcionEspacioNoValido e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionBecaNoValida");
		} catch (Exception e) {
			System.out.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}
	
	private static void testSetCapacidad(Espacio esp, Integer capacidad){
		try {
			System.out.println("La capacidad antes de la operación es: "+ esp.getCapacidad());
			System.out.println("El nuevo valor es: "+  capacidad);
			esp.setCapacidad(capacidad);
			System.out.println("La capacidad después de la operación es: "+ esp.getCapacidad());
		} catch (ExcepcionEspacioNoValido e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionBecaNoValida");
		} catch (Exception e) {
			System.out.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}
	
	private static void testSetTipo(Espacio esp, TipoEspacio tipo){
		try {
			System.out.println("El tipo antes de la operación es: "+ esp.getTipo());
			System.out.println("El nuevo valor es: "+  tipo);
			esp.setTipo(tipo);
			System.out.println("El tipo después de la operación es: "+ esp.getTipo());
		} catch (ExcepcionEspacioNoValido e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionBecaNoValida");
		} catch (Exception e) {
			System.out.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}
	
										/******** NORMAL ******/
	
	private static void mostrarEspacio(Espacio e){
		System.out.println("Tipo espacio: " + e.getTipo());
		System.out.println("Nombre: " + e.getNombre());
		System.out.println("Capacidad: " + e.getCapacidad());
		System.out.println("Planta: " + e.getPlanta());
		System.out.println("Espacio: " + e );
		
	}
	
	
	
	
	
	
	
}
