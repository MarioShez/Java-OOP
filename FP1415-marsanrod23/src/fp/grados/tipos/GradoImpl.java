package fp.grados.tipos;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;


import fp.grados.excepciones.ExcepcionGradoNoValido;

public class GradoImpl implements Grado{
	
	private String nombre;
	private Set<Asignatura> asignaturasObligatorias;
	private Set<Asignatura> asignaturasOptativas;
	private Double numeroMinimoCreditosOptativas;
	
	
									/******** CONSTRUCTOR******/
	public GradoImpl(String nombre, Set<Asignatura> asignaturasObligatorias, Set<Asignatura> asignaturasOptativas,
			Double numeroMinimoCreditosOptativas){
		checkNumeroMinimoCreditosOptativas(numeroMinimoCreditosOptativas, asignaturasOptativas);
		checkCreditos(asignaturasOptativas);
		
		this.nombre = nombre;
		this.asignaturasObligatorias = asignaturasObligatorias;
		this.asignaturasOptativas = asignaturasOptativas;
		this.numeroMinimoCreditosOptativas = numeroMinimoCreditosOptativas;
	}
	
	
									/******** RESTRICCIONES******/
	//Todas las asignaturas optativas de un grado deben tener el mismo número de créditos. Si no se cumple esta restricción, 
	//lance la excepción ExcepcionGradoNoValido.
	private void checkCreditos(Set<Asignatura> asignaturasOptativas){
		
		for(Asignatura a : asignaturasOptativas){
			a.getCreditos();
			for(Asignatura a1 : asignaturasOptativas){
				if(!(a1.getCreditos().equals(a.getCreditos()))){
					throw new ExcepcionGradoNoValido();
				}
			}
		}
	}

	
	
	//El número mínimo de créditos de asignaturas optativas que debe cursar un alumno debe estar comprendido entre cero y el
	//número total de créditos de asignaturas optativas del grado. Si no se cumple esta restricción, lance la excepción ExcepcionGradoNoValido.
	private void checkNumeroMinimoCreditosOptativas(Double numeroMinimoCreditosOptativas, Set<Asignatura> asignaturasOptativas){
		
		Double res = 0.;
		for(Asignatura a: asignaturasOptativas){
			res = a.getCreditos() + res;
		}
		if(numeroMinimoCreditosOptativas < 0. || res < numeroMinimoCreditosOptativas){
			throw new ExcepcionGradoNoValido();
		}
		
	}
	
	
	
									   /******** GET & SET ******/
	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public Double getNumeroMinimoCreditosOptativas() {
		return numeroMinimoCreditosOptativas;
	}

	@Override
	public Set<Asignatura> getAsignaturasObligatorias() {
		return asignaturasObligatorias;
	}

	@Override
	public Set<Asignatura> getAsignaturasOptativas() {
		
		return asignaturasOptativas;
	}

	//Este número se calcula sumando los créditos de todas las asignaturas obligatorias 
	//y el número mínimo de créditos de asignaturas optativas que debe cursar
	public Double getNumeroTotalCreditos() {
		Double numCreditos = 0.;
		for(Asignatura a : getAsignaturasObligatorias()){
			numCreditos = numCreditos + a.getCreditos();
		}
		return numCreditos + getNumeroMinimoCreditosOptativas();
	}

	//Representa los departamentos con docencia en el grado, que son aquellos que imparten alguna asignatura en el mismo.
	public Set<Departamento> getDepartamentos() {
		Set<Departamento>  departamento= new HashSet<Departamento>();
		Set<Asignatura> todasAsig = getAsignaturasOptativas();
		todasAsig.addAll(getAsignaturasObligatorias());
		
		for(Asignatura a : todasAsig){
			departamento.add(a.getDepartamento());
		}
		
		return departamento;
	}

	//Propiedad derivada, de tipo Set<Profesor>. Representa los profesores que pertenecen a cualquiera de los departamentos 
	//con docencia en el grado. Consultable.
	public Set<Profesor> getProfesores() {
		Set<Profesor> profesores = new HashSet<Profesor>();
		for(Departamento d : getDepartamentos()){
			profesores.addAll(d.getProfesores());
		}
		return profesores;
	}

	
	
	//Devuelve un conjunto con todas las asignaturas del grado, tanto obligatorias como optativas, 
	//que se imparten en el curso curso.
	public Set<Asignatura> getAsignaturas(Integer curso) {
		
		Set<Asignatura> res = new HashSet<Asignatura>();
		
		for(Asignatura a : getAsignaturasObligatorias()){
			if(a.getCurso() == curso){
				res.add(a);
			}
		}
		
		for(Asignatura as : getAsignaturasOptativas()){
			if(as.getCurso() == curso){
				res.add(as);
			}
		}
		return res;
	}

	//Devuelve la asignatura del grado cuyo código es codigo, o null si no existe ninguna asignatura con dicho código.
	public Asignatura getAsignatura(String codigo) {
		Asignatura res = null;
		for(Asignatura a : getAsignaturasObligatorias()){
			if(a.getCodigo() == codigo){
				res = a;
				break;
			}
		}
		
		for(Asignatura b : getAsignaturasOptativas()){
			if(b.getCodigo() == codigo){
				res = b;
				break;
			}
		}
		
		return res;
	}
	
	
	
									   /******** EQUALS... ******/
	@Override
	public int compareTo(Grado o){
		return getNombre().compareTo(o.getNombre());
	}
	
	
	@Override
	public int hashCode() {
		return getNombre().hashCode();
	}
	
	
	public boolean equals(Object o){
		boolean res = false;
		if(o instanceof Grado){
			Grado g = (Grado) o;
			res= getNombre().equals(g.getNombre());
		}
		return res;
	}
	
	
	
									    /******** toString ******/
	public String toString(){
		return getNombre();
	}


	//b9
	public SortedMap<Asignatura, Double> getCreditosPorAsignatura() {
		SortedMap<Asignatura, Double> res = new TreeMap<Asignatura, Double>();
		for(Asignatura a : getAsignaturasObligatorias()){
			ActualizaMapAsignaturaCreditos(a, a.getCreditos(), res);
		}
		for(Asignatura a : getAsignaturasOptativas()){
			ActualizaMapAsignaturaCreditos(a, a.getCreditos(), res);
		}
		return res;
	}

	//b9
	private void ActualizaMapAsignaturaCreditos(Asignatura a, Double creditos, SortedMap<Asignatura, Double> res) {
		res.put(a, creditos);
		
	}


	@Override
	public SortedSet<Departamento> getDepartamentosOrdenadosPorAsignaturas() {
		Comparator<Departamento> cmp = Comparator.comparing((Departamento x) -> x.getAsignaturas().size()).reversed().thenComparing(Comparator.naturalOrder());
		
		SortedSet<Departamento> res = new TreeSet<Departamento>(cmp);
		res.addAll(getDepartamentos());
		
		return res;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
