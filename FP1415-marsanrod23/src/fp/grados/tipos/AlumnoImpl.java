package fp.grados.tipos;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionAlumnoNoValido;
import fp.grados.excepciones.ExcepcionAlumnoOperacionNoPermitida;

public class AlumnoImpl extends PersonaImpl implements Alumno {
	
	private Set<Asignatura> asignaturas;
	private Expediente expediente;
	
	
								/******** CONSTRUCTOR******/
	public AlumnoImpl(String dni, String nombre, String apellidos, LocalDate fechaNacimiento, String email) {
		super (dni, nombre, apellidos, fechaNacimiento, email);
		checkEmail(email);
		asignaturas = new HashSet<Asignatura>();
		this.expediente = new ExpedienteImpl();
	}
	
	//b10
	public AlumnoImpl(String s){
		super(s);//porque extiende de persona

		// 4. chekear las restricciones del tipo
		checkEmail(getEmail());

		asignaturas = new HashSet<Asignatura>();
		expediente = new ExpedienteImpl();

	}
	
	
	
								/******** RESTRICCIONES******/
	private void checkEmail(String email){
		if(email == "" || !(email.endsWith("@alum.us.es"))){
			throw new ExcepcionAlumnoNoValido();
		}
	}
	
	
								  /******** METODOS ******/
	// el curso de un alumno es el mayor de los cursos de las asignaturas en que
	// está matriculado, o 0 si no está matriculado en ninguna asignatura.
	public Integer getCurso() {
		Integer res = 0;

		for (Asignatura a : getAsignaturas()) {
			Integer curso = a.getCurso();
			if (curso > res) {// no es un filtro
				res = curso;
			}
		}
		return res;
	}
	
	@Override
	public void matriculaAsignatura(Asignatura asig) {
		if(estaMatriculadoEn(asig)){
			throw new ExcepcionAlumnoOperacionNoPermitida();
		}
			asignaturas.add(asig);
	}

	
	
	@Override
	public void eliminaAsignatura(Asignatura asig) {
		if(!(estaMatriculadoEn(asig))){
			throw new ExcepcionAlumnoOperacionNoPermitida();
		}
		asignaturas.remove(asig);
	}
	
	
	@Override
	public Boolean estaMatriculadoEn(Asignatura asig) {
		return getAsignaturas().contains(asig);
	}
	
	
	// b7
	// Añade al expediente la nota cuyas propiedades recibe como parámetros. Si el alumno no está matriculado en la asignatura a,
	// el método lanzará la excepción ExcepcionAlumnoOperacionNoPermitida.
	public void evaluaAlumno(Asignatura a, Integer curso,
			Convocatoria convocatoria, Double nota, Boolean mencionHonor) {
		if (asignaturas.contains(a)) {
			Nota n = new NotaImpl(a, curso, convocatoria, nota, mencionHonor);
			expediente.nuevaNota(n);
		} else {
			throw new ExcepcionAlumnoOperacionNoPermitida();
		}
	}

	// b7
	// Añade al expediente la nota cuyas propiedades recibe como parámetros. Si el alumno no está matriculado en la asignatura a,
	// el método lanzará la excepción ExcepcionAlumnoOperacionNoPermitida.
	public void evaluaAlumno(Asignatura a, Integer curso,
			Convocatoria convocatoria, Double nota) {
		if (asignaturas.contains(a)) {
			Nota n = new NotaImpl(a, curso, convocatoria, nota);
			expediente.nuevaNota(n);
		} else {
			throw new ExcepcionAlumnoOperacionNoPermitida();
		}
	}
	
								  /******** GET & SET ******/
	public Set<Asignatura> getAsignaturas() {
		return new HashSet<Asignatura> (this.asignaturas);
	}
	
	public Expediente getExpediente() {
		return expediente;
	}
	
	public void setEmail(String email){
		checkEmail(email);
		super.setEmail(email);
	}
	
	
										/******** toString ******/
	public String toString(){
		return "( "+ getCurso() + "º)" + super.toString();
	}


	//b9
	public SortedMap<Asignatura, Calificacion> getCalificacionPorAsignatura() {
		SortedMap<Asignatura, Calificacion> res = new TreeMap<Asignatura, Calificacion>();
		for(Nota n : getExpediente().getNotas()){
			ActualizaMapAsignaturaCalificacion(n, res);
		}
		return res;
	}


	private void ActualizaMapAsignaturaCalificacion(Nota n, SortedMap<Asignatura, Calificacion> res) {
		for(Asignatura a : getAsignaturas()){
			if(res.containsKey(a)){
				if(res.get(a).ordinal() < n.getCalificacion().ordinal()){
					res.put(n.getAsignatura(), n.getCalificacion());
				}
			}else{
				res.put(a, n.getCalificacion());
			}
		}
		
	}

	@Override
	public SortedSet<Asignatura> getAsignaturasOrdenadasPorCurso() {
		//construye un comparador en curso y le da la vuelta conreverse
		//en caso de enpate se usa thenComparing
		Comparator<Asignatura> cmp = Comparator.comparing(Asignatura::getCurso).reversed().thenComparing(Comparator.naturalOrder());
		SortedSet<Asignatura> res = new TreeSet<Asignatura>(cmp);
		res.addAll(getAsignaturas());
		
		return res;
	}

	
	
	
}

	
