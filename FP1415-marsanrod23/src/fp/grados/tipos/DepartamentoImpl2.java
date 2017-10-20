package fp.grados.tipos;

import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class DepartamentoImpl2 extends DepartamentoImpl implements Departamento{

	public DepartamentoImpl2(String nombre) {
		super(nombre);
	}

	
	//b13
	//Elimina las tutorías de todos los profesores del departamento.
	public void borraTutorias(){
		getProfesores().stream().forEach(Profesor::borraTutorias);//llamo a la clase profesor y llamo al metodo borraTutorias.
	}
	
	
	//b13
	//Elimina las tutorías de todos los profesores del departamento cuya categoría es c.
	public void borraTutorias(Categoria categoria){
		getProfesores().stream().filter(p -> p.getCategoria().equals(categoria)).forEach(Profesor::borraTutorias);
	}
	
	
	//b13
	//Devuelve true si existe al menos un profesor asignado a la asignatura a, es decir, 
	//que imparte algún crédito en ella, y false en caso contrario.
	public Boolean existeProfesorAsignado(Asignatura a){
		return getProfesores().stream().anyMatch(p -> p.dedicacionAsignatura(a)>0);
	}
	
	
	//b13
	//Devuelve true si todas las asignaturas tienen asignado al menos un profesor, y false en caso contrario.
	public Boolean estanTodasAsignaturasAsignadas() {
		Boolean res = false;
		try {
			return getAsignaturas().stream().allMatch(
					a -> a.getDepartamento().existeProfesorAsignado(a));
		} catch (Exception e) {
			res = false;
		}
		return res;
	}
	
	
	//b13
	//Elimina la asignatura a de todos los profesores del departamento que la están impartiendo.
	public void eliminaAsignacionProfesorado(Asignatura a){
		getProfesores().stream().filter(p -> p.getAsignaturas().contains(a) && p.getDedicacionTotal() > 0).forEach(p -> p.eliminaAsignatura(a));
	}
	
	
	//b13
	//Devuelve un SortedMap<Profesor, SortedSet<Tutoria>> que hace corresponder a cada profesor con el conjunto de tutorías que 
	//tiene. Para construir el SortedMap, comience por recorrer los profesores del departamento y, para cada uno, añada al 
	//SortedMap la pareja formada por el profesor y su conjunto de tutorías.
	public SortedMap<Profesor, SortedSet<Tutoria>> getTutoriasPorProfesor(){
		Map<Profesor, SortedSet<Tutoria>> map = getProfesores().stream().collect(Collectors.toMap(p -> p, p -> p.getTutorias()));
		return new TreeMap<Profesor, SortedSet<Tutoria>>(map);
	}
	
	
	
}
