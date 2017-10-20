package fp.grados.tipos;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class AlumnoImpl2 extends AlumnoImpl implements Alumno{

	public AlumnoImpl2(String dni, String nombre, String apellidos, LocalDate fechaNacimiento, String email) {
		super(dni, nombre, apellidos, fechaNacimiento, email);
		
	}
	
	public AlumnoImpl2(String s){
		super(s);
	}
	
	
	// el curso de un alumno es el mayor de los cursos de las asignaturas en que
	// está matriculado, o 0 si no está matriculado en ninguna asignatura.
	public Integer getCurso() {
		Set<Asignatura> asignaturas = getAsignaturas();
		Comparator<Asignatura> cmp = Comparator.comparing(Asignatura::getCurso);
		if(asignaturas.isEmpty()){
			return 0;
		}
		return asignaturas.stream().max(cmp).get().getCurso();
	}
	
	
	//b13
	//Devuelve un SortedMap<Asignatura, Calificacion> que hace corresponder a cada asignatura que aparece en el expediente 
	//del alumno con la calificación máxima obtenida en ella. Para construir el SortedMap, comience por recorrer las notas del 
	//expediente del alumno y, para cada una, añada al SortedMap la pareja formada por la asignatura y su calificación. Antes de 
	//hacerlo, compruebe si el SortedMap ya contiene una calificación para dicha asignatura, en cuyo caso deberá sustituirla por 
	//la nueva si ésta supera a la existente.
	public SortedMap<Asignatura, Calificacion> getCalificacionPorAsignatura(){
		SortedMap<Asignatura, Calificacion> res = new TreeMap<Asignatura, Calificacion>();
		List<Nota> notas = getExpediente().getNotas();
		
		res = notas.stream().collect(Collectors.toMap(n->n.getAsignatura(), n-> n.getCalificacion(), (x1,x2)->cmp(x1,x2),TreeMap::new));
		return res;
	}

	private Calificacion cmp(Calificacion x1, Calificacion x2) {
		Calificacion notaMayor = null;
		if(x1.ordinal() > x2.ordinal()){
			notaMayor = x1;
		}else{
			notaMayor = x2;
		}
		return notaMayor;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
