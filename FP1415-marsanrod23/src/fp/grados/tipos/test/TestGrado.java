package fp.grados.tipos.test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import fp.grados.excepciones.ExcepcionGradoNoValido;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Grado;
import fp.grados.tipos.GradoImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.TipoAsignatura;

public class TestGrado {
	public static void main(String[] args) {
		
	testConstructorNormal();
	testExcepcion1();
	testExcepcion2();
	testNumeroTotalCreditos();
	testGetDepartamento();
	testGetProfesores();
	testGetAsignaturas();
	testGetAsignatura();
	
	}
	
	
	//============================================NORMAL==========================================================
	private static void testConstructorNormal() {
		System.out.println("\n==================================Probando el constructor");
		
		Set<Asignatura> asignaturasObligatorias = new HashSet<Asignatura>();
		Set<Asignatura> asignaturasOptativas = new HashSet<Asignatura>();
		
		Asignatura a1 = new AsignaturaImpl("Fundamentos de Programación","2050001",5.0, TipoAsignatura.ANUAL, 1, null);
		Asignatura a2 = new AsignaturaImpl("Calculo","8650010",5., TipoAsignatura.ANUAL, 1, null);
		Asignatura a3 = new AsignaturaImpl("Fisica","8655010",5., TipoAsignatura.ANUAL, 1, null);
		
		Grado g = new GradoImpl("nombreNombre", asignaturasObligatorias, asignaturasOptativas, 1.);
		
		testConstructor1(g, a1, a2, a3);
	}
	
	

	
	//============================================EXCEPCIONES==========================================================	
	private static void testExcepcion1() {
		System.out.println("\n==================================Probando excepcion, creditos de asignaturas optativas ");
		
		Set<Asignatura> asignaturasObligatorias = new HashSet<Asignatura>();
		Set<Asignatura> asignaturasOptativas = new HashSet<Asignatura>();
		
		Asignatura a1 = new AsignaturaImpl("Fundamentos de Programación","2050001",6.0, TipoAsignatura.ANUAL, 1, null);
		Asignatura a2 = new AsignaturaImpl("Calculo","8650010",5., TipoAsignatura.ANUAL, 1, null);
		Asignatura a3 = new AsignaturaImpl("Fisica","8655010",6., TipoAsignatura.ANUAL, 1, null);
		
		
		Grado g = new GradoImpl("nombreNombre", asignaturasObligatorias, asignaturasOptativas, 5.);
		
		testConstructor1(g, a1, a2, a3);
	}
	
	private static void testExcepcion2() {
		System.out.println("\n==================================Probando excepcion, número mínimo de créditos de asignaturas comprendido entre 0 y y el número total de créditos de asignaturas optativas");
		
		Set<Asignatura> asignaturasObligatorias = new HashSet<Asignatura>();
		Set<Asignatura> asignaturasOptativas = new HashSet<Asignatura>();
		
		Asignatura a1 = new AsignaturaImpl("Fundamentos de Programación","2050001",6.0, TipoAsignatura.ANUAL, 1, null);
		Asignatura a2 = new AsignaturaImpl("Calculo","8650010",25., TipoAsignatura.ANUAL, 1, null);
		Asignatura a3 = new AsignaturaImpl("Fisica","8655010",5., TipoAsignatura.ANUAL, 1, null);
		
		
		Grado g = new GradoImpl("nombreNombre", asignaturasObligatorias, asignaturasOptativas, -5.);
		
		g.getAsignaturasObligatorias().add(a1);
		g.getAsignaturasOptativas().add(a2);
		g.getAsignaturasOptativas().add(a3);
		
		//testExcepcion2(g);
	}

	
	//============================================METODOS==========================================================
	private static void testNumeroTotalCreditos() {
		System.out.println("\n==================================Probando NumeroTotalCreditos");
		
		Set<Asignatura> asignaturasObligatorias = new HashSet<Asignatura>();
		Set<Asignatura> asignaturasOptativas = new HashSet<Asignatura>();
		
		Asignatura a1 = new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1, null);
		Asignatura a2 = new AsignaturaImpl("Calculo","8650000",6., TipoAsignatura.ANUAL, 1, null);
		
		Grado g = new GradoImpl("nombreNombre", asignaturasObligatorias, asignaturasOptativas, 5.);
		testNumeroTotalCreditos(g, a1, a2);
	}
	
	private static void testGetDepartamento() {
		System.out.println("\n==================================Probando getDepartamentos");
		
		Set<Asignatura> asignaturasObligatorias = new HashSet<Asignatura>();
		Set<Asignatura> asignaturasOptativas = new HashSet<Asignatura>();
		
		Departamento d = new DepartamentoImpl("Fisica");
		
		Asignatura a1 = new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1, null);
		Asignatura a2 = new AsignaturaImpl("Calculo","8650000",6., TipoAsignatura.ANUAL, 1, null);

		d.nuevaAsignatura(a1);
		d.nuevaAsignatura(a2);
		
		Grado g = new GradoImpl("nombreNombre", asignaturasObligatorias, asignaturasOptativas, 5.);
		testGetDepartamento(g, a1, a2, d);
	}
	
	private static void testGetProfesores() {
		System.out.println("\n==================================Probando getProfesores");
		
		Set<Asignatura> asignaturasObligatorias = new HashSet<Asignatura>();
		Set<Asignatura> asignaturasOptativas = new HashSet<Asignatura>();
		
		Departamento d = new DepartamentoImpl("Fisica");
		
		Asignatura a1 = new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1, d);
		Asignatura a2 = new AsignaturaImpl("Calculo","8650000",6., TipoAsignatura.ANUAL, 1, d);
		
		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1993, 10, 18), "vjkykku@us.es", Categoria.INTERINO, null);
		
		Grado g = new GradoImpl("nombreNombre", asignaturasObligatorias, asignaturasOptativas, 5.);
		testGetProfesores(g, a1, a2, d, p1);
	}
	
	private static void testGetAsignaturas() {
		System.out.println("\n==================================Probando getAsignaturas");
		
		Set<Asignatura> asignaturasObligatorias = new HashSet<Asignatura>();
		Set<Asignatura> asignaturasOptativas = new HashSet<Asignatura>();
		
		Departamento d = new DepartamentoImpl("Fisica");
		
		Asignatura a1 = new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1, null);
		Asignatura a2 = new AsignaturaImpl("Calculo","8650000",6., TipoAsignatura.ANUAL, 1, null);
		
		Grado g = new GradoImpl("nombreNombre", asignaturasObligatorias, asignaturasOptativas, 5.);
		testGetAsignaturas(g, a1, a2, d);
	}
	
	private static void testGetAsignatura() {
		System.out.println("\n==================================Probando getAsignatura");
		
		Set<Asignatura> asignaturasObligatorias = new HashSet<Asignatura>();
		Set<Asignatura> asignaturasOptativas = new HashSet<Asignatura>();
		
		Departamento d = new DepartamentoImpl("Fisica");
		
		Asignatura a1 = new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1, null);
		Asignatura a2 = new AsignaturaImpl("Calculo","8650000",6., TipoAsignatura.ANUAL, 1, null);
		
		Grado g = new GradoImpl("nombreNombre", asignaturasObligatorias, asignaturasOptativas, 5.);
		testGetAsignatura(g, a1, a2, d);
	}
    
	
	//============================================AUXILIARES==========================================================
	
									   /******** CONSTRUCTOR 1 ******/
	private static void testConstructor1(Grado g, Asignatura a1, Asignatura a2, Asignatura a3) {

		try {
			g.getAsignaturasObligatorias().add(a1);
			g.getAsignaturasOptativas().add(a2);
			g.getAsignaturasOptativas().add(a3);
			
			mostrarGrado(g);
			
		} catch (ExcepcionGradoNoValido e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionGradoNoValido");
		} catch (Exception e) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}

	}
	
	private static void testNumeroTotalCreditos(Grado g, Asignatura a1, Asignatura a2) {

		try {
			g.getAsignaturasObligatorias().add(a1);
			g.getAsignaturasOptativas().add(a2);
			System.out.println("El numero de creditos de a1 es " + a1.getCreditos());
			System.out.println("El numero total de creditos es: " +g.getNumeroTotalCreditos());
		} catch (ExcepcionGradoNoValido e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionGradoNoValido");
		} catch (Exception e) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}

	}
	
	private static void testGetDepartamento(Grado g, Asignatura a1, Asignatura a2, Departamento d) {

		try {
		
			System.out.println("Las asignaturas de del depratamento son: " + d.getAsignaturas());
			System.out.println("Los departamentos de las asignaturas son: a1= " + a1.getDepartamento() + ", a2= " +a2.getDepartamento());
			
			g.getAsignaturasObligatorias().add(a1);
			g.getAsignaturasOptativas().add(a2);
			System.out.println("Las asignatiras del grado son: " + a1 + a2);
			System.out.println("Departamentos del grado: " +g.getDepartamentos());
		} catch (ExcepcionGradoNoValido e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionGradoNoValido");
		} catch (Exception e) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}

	}
	
	private static void testGetProfesores(Grado g, Asignatura a1, Asignatura a2, Departamento d, Profesor p) {

		try {
			g.getAsignaturasObligatorias().add(a1);
			g.getAsignaturasOptativas().add(a2);
			d.nuevaAsignatura(a1);
			d.nuevoProfesor(p);
			System.out.println("los profesores son: " + g.getProfesores());
		} catch (ExcepcionGradoNoValido e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionGradoNoValido");
		} catch (Exception e) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}

	}
	
	private static void testGetAsignaturas(Grado g, Asignatura a1, Asignatura a2, Departamento d) {

		try {
			g.getAsignaturasObligatorias().add(a1);
			g.getAsignaturasOptativas().add(a2);
			d.nuevaAsignatura(a1);
			d.nuevaAsignatura(a2);
			System.out.println("los asignaturas son: " + g.getAsignaturas(1));
		} catch (ExcepcionGradoNoValido e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionGradoNoValido");
		} catch (Exception e) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}

	}
	
	private static void testGetAsignatura(Grado g, Asignatura a1, Asignatura a2, Departamento d) {

		try {
			g.getAsignaturasObligatorias().add(a1);
			g.getAsignaturasOptativas().add(a2);
			d.nuevaAsignatura(a1);
			d.nuevaAsignatura(a2);
			System.out.println("la asignatura es: " + g.getAsignatura("2050001"));
			System.out.println("la asignatura es: " + g.getAsignatura("8650000"));
		} catch (ExcepcionGradoNoValido e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionGradoNoValido");
		} catch (Exception e) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}

	}
	
	
										/******** NORMAL ******/
	private static void mostrarGrado(Grado g) {
		System.out.println("Nombre: " + g.getNombre() );
		System.out.println("Asignaturas Obligatorias: " + g.getAsignaturasObligatorias());
		System.out.println("Asignaturas Optativas: " + g.getAsignaturasOptativas());
		System.out.println("Numero Minimo Creditos Optativas: " + g.getNumeroMinimoCreditosOptativas());
		System.out.println("Numero total de creditos: " + g.getNumeroTotalCreditos());
	}
	
	
}
