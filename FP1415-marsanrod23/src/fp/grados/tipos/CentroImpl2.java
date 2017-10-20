package fp.grados.tipos;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CentroImpl2 extends CentroImpl implements Centro{

	public CentroImpl2(String nombre, String direccion, Integer numeroPlantas, Integer numeroSotanos) {
		super(nombre, direccion, numeroPlantas, numeroSotanos);
		
	}
	
	
	//Devuelve el espacio con mayor capacidad del centro.
	public Espacio getEspacioMayorCapacidad(){
		Set<Espacio> espacios = getEspacios();
		Comparator<Espacio> cmp = Comparator.comparing(Espacio::getCapacidad);
		return espacios.stream().max(cmp).get();
	}
	
	
	//b13
	//Devuelve un array de 5 elementos de tipo Integer que representan el n�mero de espacios de tipo aula de teor�a, 
	//laboratorio, seminario, aula de examen u otro tipo, respectivamente, que hay en el centro.
	public Integer[] getConteosEspacios(){
		Set<Espacio> espacios = getEspacios();
		return new Integer[]{
				cuentaEspaciosTipo(TipoEspacio.TEORIA, espacios),
				cuentaEspaciosTipo(TipoEspacio.LABORATORIO, espacios),
				cuentaEspaciosTipo(TipoEspacio.SEMINARIO, espacios),
				cuentaEspaciosTipo(TipoEspacio.EXAMEN, espacios),
				cuentaEspaciosTipo(TipoEspacio.OTRO, espacios),
		};
	}
	
	
	//b13
	private Integer cuentaEspaciosTipo(TipoEspacio tipo, Set<Espacio> espacios){
		return (int) espacios.stream().filter(e -> e.getTipo().equals(tipo)).count();
	}
	
	
	//b13
	//Devuelve el conjunto de todos los profesores que tienen un despacho en el centro.
	public Set<Profesor> getProfesores(){
		return getDespachos().stream().flatMap(d -> d.getProfesores().stream()).collect(Collectors.toSet());
	}
	
	
	//b13
	// Devuelve el conjunto de todos los profesores que pertenecen al
	// departamento d y tienen un despacho en el centro.
	public Set<Profesor> getProfesores(Departamento d){
		return getProfesores().stream().filter(p -> p.getDepartamento().equals(d)).collect(Collectors.toSet());
	}
	
	
	//b13
	//Devuelve un conjunto con todos los espacios de tipo Despacho que hay en el centro.
	public Set<Despacho> getDespachos(){
		return getEspacios().stream().filter(e -> e instanceof Despacho).map(e -> (Despacho) e).collect(Collectors.toSet());
	}
	
	
	//b13
	//Devuelve un conjunto con todos los despachos del centro donde hay al menos un profesor del departamento d.
	public Set<Despacho> getDespachos(Departamento d){
		return getDespachos().stream().filter(dep -> dep.getProfesores().stream().anyMatch(p -> p.getDepartamento().equals(d))).collect(Collectors.toSet());
	}	
	
	
	//b13
	//Devuelve un SortedMap<Profesor, Despacho> que hace corresponder a cada profesor con el despacho que ocupa en el centro. 
	//Para construir el Map, comience por recorrer los despachos del centro y, para cada uno, recorra a su vez los profesores 
	//que lo ocupan y a�ada al Map la pareja formada por el profesor y el despacho.
	public SortedMap<Profesor, Despacho> getDespachosPorProfesor(){
		return getProfesores().stream().filter(p -> tieneDespacho(p)).collect(Collectors.toMap(p -> p, p -> buscaDespacho(p), (d1, d2) -> d1, TreeMap::new));
	}


	private Boolean tieneDespacho(Profesor p) {
		return getDespachos().stream().anyMatch(d -> d.getProfesores().contains(p));//anyMatch:existe algun profesor en el despacho??
	}
	
	private Despacho buscaDespacho(Profesor p) {
		return getDespachos().stream().filter(d -> d.getProfesores().contains(p)).findFirst().get();
	}
	
	
	
}
