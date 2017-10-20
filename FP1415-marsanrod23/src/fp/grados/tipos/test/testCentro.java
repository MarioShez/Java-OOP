package fp.grados.tipos.test;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import fp.grados.excepciones.ExcepcionCentroNoValido;
import fp.grados.excepciones.ExcepcionCentroOperacionNoPermitida;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Centro;
import fp.grados.tipos.CentroImpl;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Despacho;
import fp.grados.tipos.DespachoImpl;
import fp.grados.tipos.Espacio;
import fp.grados.tipos.EspacioImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.TipoEspacio;

public class testCentro {

	public static void main(String[] args) {
		
		testConstructorNormal();
		
		testConstructorExcepcion1();
		testConstructorExcepcion2();
		
		testNuevoEspacio();
		testNuevoEspacioErroneo();
		testEliminaEspacio();
		
		testGetDespachos();
		testGetDespachos2();
		testGetConteosEspacios();
		testGetProfesores();
		testGetEspacioMayorCapacidad();

	}

	//============================================NORMAL==========================================================
	private static void testConstructorNormal(){
		System.out.println("\n==================================Probando el primer constructor");
		testConstructor("CentroCentro", "direccionDrireccion", 4, 3);
	}
	
	
	//============================================EXCEPCIONES==========================================================	
	private static void testConstructorExcepcion1() {
		System.out.println("\n==================================Probando restriccion numero de sotanos incorrecto");
		testConstructor("CentroCentro", "direccionDrireccion", 4, -1);
	}
	
	private static void testConstructorExcepcion2() {
		System.out.println("\n==================================Probando restriccion numero de sotanos incorrecto");
		testConstructor("CentroCentro", "direccionDrireccion", -1, 1);
	}
	
	
	//============================================METODOS==========================================================
	private static void testNuevoEspacio(){
		System.out.println("\n==================================Probando nuevoEspacio");
		Espacio e = new EspacioImpl(TipoEspacio.EXAMEN, "nombreNombre", 32, 1);
		Centro c = new CentroImpl("CentroCentro", "direccionDrireccion", 4, 2);
		testNuevoEspacio(e, c);
	}
	
	private static void testNuevoEspacioErroneo(){
		System.out.println("\n==================================Probando nuevoEspacio erroneo");
		Espacio e = new EspacioImpl(TipoEspacio.EXAMEN, "nombreNombre", 32, 5);
		Centro c = new CentroImpl("CentroCentro", "direccionDrireccion", 4, 2);
		testNuevoEspacio(e, c);
	}
	
	private static void testEliminaEspacio(){
		System.out.println("\n==================================Probando eliminaEspacio");
		Espacio e = new EspacioImpl(TipoEspacio.EXAMEN, "nombreNombre", 32, 1);
		Espacio e2 = new EspacioImpl(TipoEspacio.EXAMEN, "nombreNombre", 32, 2);
		Centro c = new CentroImpl("CentroCentro", "direccionDrireccion", 4, 2);
		testEliminaEspacio(e, e2, c);
	}
	
	private static void testGetDespachos(){
		System.out.println("\n==================================Probando GetDespachos");
		Espacio e = new EspacioImpl(TipoEspacio.EXAMEN, "nombreNombre", 32, 1);
		Espacio e2 = new EspacioImpl(TipoEspacio.EXAMEN, "nombreNombre", 32, 2);
		Espacio e3 = new EspacioImpl(TipoEspacio.EXAMEN, "nombreNombre", 32, 2);
		Despacho d = new DespachoImpl("nombreNombre", 15, 2);
		Centro c = new CentroImpl("CentroCentro", "direccionDrireccion", 4, 2);
		testGetDespachos(e, e2, e3, d, c);
	}
	
	private static void testGetDespachos2(){
		System.out.println("\n==================================Probando GetDespachos(Departamento d)");
		Departamento dep = new DepartamentoImpl("Fisica Aplicada");
		Departamento d = new DepartamentoImpl("Programacion");
		
		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.INTERINO, dep);
		Profesor p2 = new ProfesorImpl("47309527E", "Mario", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.INTERINO, d);
		
		Despacho desp = new DespachoImpl("nombreNombre", 25, 3, p1);
		Despacho desp2 = new DespachoImpl("nombreNombre", 25, 3, p2);
		
		Centro c = new CentroImpl("CentroCentro", "direccionDrireccion", 4, 2);
		
		testGetDespachos2(desp, desp2, dep, d, p1, p2, c);
	}
	
	private static void testGetConteosEspacios(){
		System.out.println("\n==================================Probando GetConteosEspacios");
		Espacio e = new EspacioImpl(TipoEspacio.EXAMEN, "nombreNombre", 32, 1);
		Centro c = new CentroImpl("CentroCentro", "direccionDrireccion", 4, 2);
		testGetConteosEspacios(e, c);
	}
	
	private static void testGetProfesores(){
		System.out.println("\n==================================Probando GetProfesores");
		
		Departamento dep = new DepartamentoImpl("Fisica Aplicada");
		Departamento dep2 = new DepartamentoImpl("Departamento de programacion");
		
		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.INTERINO, dep);
		Profesor p2 = new ProfesorImpl("47309527E", "Mario", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.INTERINO, dep2);
		Set<Profesor> prof = new HashSet<Profesor>();
		prof.add(p1);
		prof.add(p2);
		Despacho desp = new DespachoImpl("nombreNombre", 25, 3, prof);
		
		Centro c = new CentroImpl("CentroCentro", "direccionDrireccion", 4, 2);
		testGetProfesores(dep, dep2, desp, c);
	}
	
	private static void testGetEspacioMayorCapacidad(){
		System.out.println("\n==================================Probando GetEspacioMayorCapacidad");
		Espacio e = new EspacioImpl(TipoEspacio.EXAMEN, "nombreNombre", 32, 1);
		Espacio e2 = new EspacioImpl(TipoEspacio.EXAMEN, "nombreNombre2", 10, 2);
		Espacio e3 = new EspacioImpl(TipoEspacio.EXAMEN, "nombreNombre3",32, 2);
		Centro c = new CentroImpl("CentroCentro", "direccionDrireccion", 30, 2);
		testGetEspacioMayorCapacidad(e, e2, e3, c);
	}
	
	
	//============================================SETs==========================================================
	
	
	
	
	
	//============================================AUXILIARES==========================================================
										/******** CONSTRUCTOR ******/
	private static void testConstructor(String nombre, String direccion, Integer numeroPlantas, Integer numeroSotanos){
		try {
			Centro c = new CentroImpl(nombre, direccion, numeroPlantas, numeroSotanos);
			mostrarCentro(c);
		} catch (ExcepcionCentroNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionCentroNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	
										/******** METODOS ******/
	private static void testNuevoEspacio(Espacio e, Centro c) {

		try {
			System.out.println("El numero de plantas del centro es: " + c.getNumeroPlantas());
			System.out.println("El numero de sotanos del centro es: " + c.getNumeroSotanos());
			 c.nuevoEspacio(e);
			System.out.println("Los espacios del centro son: " + c.getEspacios());
		} catch (ExcepcionCentroOperacionNoPermitida e1) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionCentroOperacionNoPermitida");
		} catch (Exception e1) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	
	private static void testEliminaEspacio(Espacio e,  Espacio e2, Centro c) {

		try {
			c.nuevoEspacio(e);
			c.nuevoEspacio(e2);
			System.out.println("Los espacios del centro son: " + c.getEspacios());
			c.eliminaEspacio(e);
			System.out.println("Si elimino el espacio tengo los siguientes espacios del centro: " + c.getEspacios());
		} catch (ExcepcionCentroOperacionNoPermitida e1) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionCentroOperacionNoPermitida");
		} catch (Exception e1) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	
	private static void testGetDespachos(Espacio e, Espacio e2, Espacio e3, Despacho d , Centro c) {

		try {
			c.nuevoEspacio(e);
			c.nuevoEspacio(e2);
			c.nuevoEspacio(e3);
			System.out.println("Los espacios del centro son: " + c.getEspacios() );
			System.out.println("Los espacios de tipo Despacho que hay en el centro son: " + c.getDespachos());
		} catch (ExcepcionCentroOperacionNoPermitida e1) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionCentroOperacionNoPermitida");
		} catch (Exception e1) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testGetDespachos2(Despacho desp, Despacho desp2, Departamento dep, Departamento d, Profesor p1, Profesor p2, Centro c) {

		try {
			System.out.println("Los siguientes profesores pertenecen a los siguintes departamento: p1= " + p1.getDepartamento()
					+ " p2= " + p2.getDepartamento());
			System.out.println("Despachos del centro donde hay al menos un profesor del departamento d: " + c.getDespachos(d));
		} catch (ExcepcionCentroOperacionNoPermitida e1) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionCentroOperacionNoPermitida");
		} catch (Exception e1) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	

	private static void testGetConteosEspacios(Espacio e, Centro c) {

		try {
			c.nuevoEspacio(e);
			System.out.println("Los espacios del centro son: " + c.getEspacios() );
			System.out.println("El Integer que representan el número de espacios del centro es: " + Arrays.toString(c.getConteosEspacios()));
		} catch (ExcepcionCentroOperacionNoPermitida e1) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionCentroOperacionNoPermitida");
		} catch (Exception e1) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testGetProfesores(Departamento dep, Departamento dep2, Despacho desp, Centro c) {

		try {
			System.out.println("Los profesores que tienen un despacho en el centro son: " + c.getProfesores());
		} catch (ExcepcionCentroOperacionNoPermitida e1) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionCentroOperacionNoPermitida");
		} catch (Exception e1) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testGetEspacioMayorCapacidad(Espacio e, Espacio e2, Espacio e3, Centro c){
		c.nuevoEspacio(e);
		c.nuevoEspacio(e2);
		c.nuevoEspacio(e3);
		System.out.println("Los espacios del centro son: " + c.getEspacios());
		System.out.println("Los espacio con mayor capacidad del centro es: " + c.getEspacioMayorCapacidad());
		
	}
	
	
											/******** NORMAL ******/
	private static void mostrarCentro(Centro c) {
		System.out.println("Nombre : " + c.getNombre());
		System.out.println("Direccion: " + c.getDireccion());
		System.out.println("Numero plantas: " + c.getNumeroPlantas());
		System.out.println("Numero Sotanos: " + c.getNumeroSotanos());
		System.out.println("Espacios: " + c.getEspacios());
	}

}
