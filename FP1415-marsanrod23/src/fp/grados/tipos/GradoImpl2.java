package fp.grados.tipos;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class GradoImpl2 extends GradoImpl implements Grado{

	public GradoImpl2(String nombre, Set<Asignatura> asignaturasObligatorias, Set<Asignatura> asignaturasOptativas,
			Double numeroMinimoCreditosOptativas) {
		super(nombre, asignaturasObligatorias, asignaturasOptativas, numeroMinimoCreditosOptativas);
		
	}
	
	
	//b13
	//Este número se calcula sumando los créditos de todas las asignaturas obligatorias 
	//y el número mínimo de créditos de asignaturas optativas que debe cursar
	public Double getNumeroTotalCreditos(){
		return getAsignaturasObligatorias().stream().mapToDouble(a -> a.getCreditos()).sum() + getNumeroMinimoCreditosOptativas();
	}
	
	
	//b13
	//Devuelve un conjunto con todas las asignaturas del grado, tanto obligatorias como optativas, 
	//que se imparten en el curso curso.
	public Set<Asignatura> getAsignaturas(Integer curso){
		
		Set<Asignatura> oblig = seleccionaAsignaturas(getAsignaturasObligatorias(), curso);
		Set<Asignatura> opt = seleccionaAsignaturas(getAsignaturasOptativas(), curso);
		
		Set<Asignatura> res = new HashSet<Asignatura>();
		res.addAll(oblig);
		res.addAll(opt);
		
		return res;
	}
	
	private Set<Asignatura> seleccionaAsignaturas(Set<Asignatura> asig, Integer curso){
		return asig.stream().filter(a -> a.getCurso().equals(curso)).collect(Collectors.toSet());
	}
	
	
	//b13
	//Devuelve la asignatura del grado cuyo código es codigo, o null si no existe ninguna asignatura con dicho código.
	public Asignatura getAsignatura(String codigo){
		Set<Asignatura> todasAsigs = getAsignaturasObligatorias();
		todasAsigs.addAll(getAsignaturasOptativas());
		return (Asignatura) todasAsigs.stream().filter(ta -> ta.getCodigo().equals(codigo));
	}
	
	//b13
	//Representa los departamentos con docencia en el grado, que son aquellos que imparten alguna asignatura en el mismo.
	public Set<Departamento> getDepartamentos(){
		Set<Asignatura> todasAsigs = getAsignaturasObligatorias();
		todasAsigs.addAll(getAsignaturasOptativas());
		return todasAsigs.stream().map(a -> a.getDepartamento()).collect(Collectors.toSet());
	}

	//b13
	//Propiedad derivada, de tipo Set<Profesor>. Representa los profesores que pertenecen a cualquiera de los departamentos 
	//con docencia en el grado. Consultable.
	public Set<Profesor> getProfesores(){
		return getDepartamentos().stream().flatMap(d -> d.getProfesores().stream()).collect(Collectors.toSet());
	}

	
	//b13
	//Devuelve un SortedMap<Asignatura, Double> que hace corresponder a cada asignatura con su número de créditos. Para construir 
	//el Map, comience por recorrer todas las asignaturas del centro, tanto obligatorias como optativas, y, para cada una, añada al
	//SortedMap la pareja formada por la asignatura y su número de créditos.
	public SortedMap<Asignatura, Double> getCreditosPorAsignatura(){
		Set<Asignatura> todasAsigs = getAsignaturasObligatorias();
		todasAsigs.addAll(getAsignaturasOptativas());
		
		Map<Asignatura, Double> res = todasAsigs.stream().filter(a -> contieneCredito(a))
				.collect(Collectors.toMap(a -> a ,a -> a.getCreditos()));
		
		
		return new TreeMap<Asignatura, Double>(res);
	}


	private Boolean contieneCredito(Asignatura a) {
		Boolean res = false;
		if(a.getCreditos() > 0.){
			res = true;
		}
		return res;
	}
	
	
	
	
	
	
	
	
	

}
