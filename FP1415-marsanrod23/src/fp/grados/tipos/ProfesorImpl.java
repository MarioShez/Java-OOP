package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionProfesorNoValido;
import fp.grados.excepciones.ExcepcionProfesorOperacionNoPermitida;

public class ProfesorImpl extends PersonaImpl implements Profesor{

	private Categoria categoria;
	private SortedSet<Tutoria> tutorias;
	private List<Asignatura> asignaturas;
	private List<Double> creditos;
	private Departamento departamento;
	
	private static final Double creditosMaximos = 24.;
	
									/******** CONSTRUCTOR ******/	
	public ProfesorImpl(String dni, String nombre, String apellidos, LocalDate fechaNacimiento, String email, Categoria categoria, Departamento departamento) {
		super(dni, nombre, apellidos, fechaNacimiento, email);
		checkEdad(fechaNacimiento);
		this.categoria = categoria;
		this.tutorias = new TreeSet<Tutoria>();
		asignaturas = new ArrayList<Asignatura>();
		creditos = new ArrayList<Double>();
		setDepartamento(departamento);
	}

	
									/******** EXCEPCIONES ******/	
	/*private void checkAsignatura(Asignatura asig){
		if(!(getDepartamento().equals(asig.getDepartamento()))){
			throw new ExcepcionProfesorOperacionNoPermitida();
		}
	}*/
	
	
	private void checkDedicacion(Double dedicacion, Double numeroCreditosAsignatura){
		if(dedicacion < 0 || dedicacion > numeroCreditosAsignatura){
			throw new ExcepcionProfesorOperacionNoPermitida();
		}
	}
	
	
	
	//LA EDAD NO PUEDE SER MENOR QUE 18
	private void checkEdad(LocalDate fechaNacimiento){
		Integer edad = (int)fechaNacimiento.until(LocalDate.now(),ChronoUnit.YEARS);
		if(edad < 18){
			throw new ExcepcionProfesorNoValido();
		}
	}
	
	
	//b8
	//un profesor puede impartir un m�ximo de 24 cr�ditos (valor que puede cambiar en el futuro). 
	//Deber� tenerlo en cuenta cuando a�ada nuevas asignaturas a un profesor o cuando modifique la dedicaci�n 
	//de �ste a una asignatura. Si no se cumple esta restricci�n, lance la excepci�n ExcepcionProfesorOperacionNoPermitida.
	 private void checkCreditos(List<Double> creditos){
		Double res = 0.;
		for(Double cred : creditos){
			res = res + cred;
		}
		if(creditosMaximos < res){
			throw new ExcepcionProfesorOperacionNoPermitida();
		}
	}
	
	
	
									/******** METODOS ******/	
	@Override		//A�ade una nueva tutor�a
	public void nuevaTutoria(LocalTime horaComienzo, Integer duracionMinutos, DayOfWeek dia) {
		tutorias.add(new TutoriaImpl(dia, horaComienzo, duracionMinutos));
	}

	
	//Elimina la tutor�a con el d�a y hora de comienzo indicados; si el profesor no ten�a esa tutor�a, la operaci�n no tiene efecto.
	public void borraTutoria(LocalTime horaComienzo, DayOfWeek dia) {
		Tutoria t = new TutoriaImpl(dia,horaComienzo, 15);
		tutorias.remove(t);
	}
	
	
	//ELIMINA TODAS LAS TUTORIAS DEL PROFESOR
	public void borraTutorias() {
		tutorias.removeAll(tutorias);
	}
	
	
	public void imparteAsignatura(Asignatura asig, Double dedicacion) {
		//checkAsignatura(asig);
		checkDedicacion(dedicacion, asig.getCreditos());

		Integer index = getAsignaturas().indexOf(asig);

		if (index.equals(-1)) {
			asignaturas.add(asig);
			creditos.add(dedicacion);
		} else {
			creditos.set(index, dedicacion);
		}

		checkCreditos(getCreditos());

	}
	

	@Override
	public Double dedicacionAsignatura(Asignatura asig) {
		Double res = 0.;
		Integer index = getAsignaturas().indexOf(asig);
		if(!index.equals(-1)){
			res = getCreditos().get(index);
		}
		return res;
	}


	@Override
	public void eliminaAsignatura(Asignatura asig) {
		Integer index = asignaturas.indexOf(asig);
		if(!index.equals(-1)){
			asignaturas.remove(asig);
			creditos.remove(index);
		}
	}
	
	
	
									/******** GETs & SETs ******/	
	@Override
	public Categoria getCategoria() {
		return categoria;
	}

	@Override
	public SortedSet<Tutoria> getTutorias() {
		return new TreeSet<Tutoria>(tutorias);
	}


	@Override
	public List<Asignatura> getAsignaturas() {
		return new ArrayList<Asignatura>(asignaturas);
	}


	@Override
	public List<Double> getCreditos() {
		return new ArrayList<Double>(creditos);
	}


	//b8  
	//representa el n�mero total de cr�ditos impartidos por el profesor,
	//y que se calcula sumando los cr�ditos que imparte el profesor en cada una de sus asignaturas.
	public Double getDedicacionTotal() {
		Double res = 0.;
		//Double acumulador = 0.;
		for(Double acumulador : getCreditos()){
			res = res + acumulador;
		
		}
		
		return res;
	}
	
	//b6
	public Departamento getDepartamento() {
		return departamento;
	}
	
	
	@Override
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	
	public void setFechaNacimiento(LocalDate fechaNacimiento){
		checkEdad(fechaNacimiento);
		super.setFechaNacimiento(fechaNacimiento);
	}
	
	//b6
	public void setDepartamento(Departamento nuevoDpto){
		//1.TOMA EL ELMEMTO QUE SE VA A CAMBIAR
		Departamento viejoDpto = this.departamento;
		
		//2.CHEKEAR IDENTIDAD(LO NUEVO DEBE DE SER DISTINTO)
		if(nuevoDpto != viejoDpto){
			//3.ACTUALIZAR LA PROPIEDAD CON EL NUEVO VALOR
			this.departamento = nuevoDpto;
			
		//4.ELIMINAR EL OBJETO UNICO AL QUE PERTENECIA
		if(viejoDpto != null){
			viejoDpto.eliminaProfesor(this);
		}
		//5.A�ADIR EL NUEVO OBJETO UNICO AL QUE PERTENECE
		if(nuevoDpto != null){
			nuevoDpto.nuevoProfesor(this);
			}
		}
	}
	
	
	
									/******** toString ******/	
	public String toString(){
		return super.toString() + " " + "(" + getCategoria() + ")";
	}


	
	
	
	






	
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
