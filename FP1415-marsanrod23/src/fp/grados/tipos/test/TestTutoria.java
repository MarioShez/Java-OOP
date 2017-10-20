package fp.grados.tipos.test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import fp.grados.excepciones.ExcepcionTutoriaNoValida;
import fp.grados.tipos.Tutoria;
import fp.grados.tipos.TutoriaImpl;


public class TestTutoria {

	public static void main(String[] args) {
		testConstructor1Normal();
		testConstructor2Normal();
		testConstructor1Excepcion1();
		testConstructor1Excepcion2();
		testConstructor2Excepcion1();
		testConstructor2Excepcion3();
	}
		
	//============================================NORMAL==========================================================	
									
									/******** CONSTRUCTOR 1 ******/
	private static void testConstructor1Normal(){
		System.out.println("==================================Probando el constructor 1");
		Tutoria t = new TutoriaImpl(DayOfWeek.FRIDAY, LocalTime.of(9, 00), LocalTime.of(12, 00));
		mostrarTutoria(t);
	}
	
										/******** CONSTRUCTOR 2 ******/
	private static void testConstructor2Normal(){
		System.out.println("==================================Probando el constructor 2");
		Tutoria t = new TutoriaImpl(DayOfWeek.FRIDAY, LocalTime.of(9, 00), 150);
		mostrarTutoria(t);
	}
	
	
	//============================================EXCEPCIONES==========================================================	
	
										/******** CONSTRUCTOR 1 ******/
	
	private static void testConstructor1Excepcion1(){
		System.out.println("==================================Probando el constructor 1, solo hay clase de lunes a viernes");
		testConstructor1(DayOfWeek.SATURDAY, LocalTime.of(9, 00), LocalTime.of(13, 00));
	}
	
	private static void testConstructor1Excepcion2(){
		System.out.println("==================================Probando el constructor 1, duracion no puede ser menor que 15 minutos");
		testConstructor2(DayOfWeek.FRIDAY, LocalTime.of(9, 00), 5);
	}
	
	
	
										/******** CONSTRUCTOR 2 ******/
	
	private static void testConstructor2Excepcion1(){
		System.out.println("==================================Probando el constructor 2, solo hay clase de lunes a viernes");
		testConstructor1(DayOfWeek.SATURDAY, LocalTime.of(9, 00), LocalTime.of(13, 00));
	}
	
	
	private static void testConstructor2Excepcion3(){
		System.out.println("==================================Probando el constructor 2, duracion no puede ser menor que 15 minutos");
		testConstructor2(DayOfWeek.FRIDAY, LocalTime.of(9, 00), 5);
	}
	
	
	//============================================AUXILIARES============================================================	
	
										/******** CONSTRUCTOR 1 ******/
	
	private static void testConstructor1(DayOfWeek diaSemana, LocalTime horaComienzo,LocalTime horaFin){
		try{
			Tutoria n = new TutoriaImpl(diaSemana, horaComienzo, horaFin);
			mostrarTutoria(n);
		}catch(ExcepcionTutoriaNoValida e){
			System.out.println("******************** Se ha capturado la excepción ExcepcionTutoriaNoValida");
		}catch(Exception e){
			System.out.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}
	
										/******** CONSTRUCTOR 2 ******/
	
	private static void testConstructor2(DayOfWeek diaSemana, LocalTime horaComienzo, Integer duracion){
		try{
			Tutoria n = new TutoriaImpl(diaSemana, horaComienzo, duracion);
			mostrarTutoria(n);
		}catch(ExcepcionTutoriaNoValida e){
			System.out.println("******************** Se ha capturado la excepción ExcepcionTutoriaNoValida");
		}catch(Exception e){
			System.out.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}
	
										/******** NORMAL ******/
	
	private static void mostrarTutoria(Tutoria t){
		System.out.println();
		System.out.println("Dia semana: " + t.getDiaSemana());
		System.out.println("Hora comienzo: " + t.getHoraComienzo());
		System.out.println("Hora fin: " + t.getHoraFin());
		System.out.println("Duracion: " + t.getDuracion());
		System.out.println("Tutoria: " + t);
	}
	
	
	
	
}
