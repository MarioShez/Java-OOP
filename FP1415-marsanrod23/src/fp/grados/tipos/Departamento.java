package fp.grados.tipos;

import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

public interface Departamento extends Comparable<Departamento> {
	
	
	//B6
	String getNombre();

	Set<Asignatura> getAsignaturas();

	void nuevaAsignatura(Asignatura a);

	void eliminaAsignatura(Asignatura a);

	//Para hacer en casa
	 Set<Profesor> getProfesores();
	 void nuevoProfesor(Profesor p);
	 void eliminaProfesor(Profesor p);
	
	 
	 
	 //B7-b8
	 public void borraTutorias();
	 public Boolean existeProfesorAsignado(Asignatura a);
	 
	 
	 //b8
	 void borraTutorias(Categoria c);
	 Boolean estanTodasAsignaturasAsignadas();
	 void eliminaAsignacionProfesorado(Asignatura a);
	 
	 //b9
	 SortedMap<Asignatura, SortedSet<Profesor>> getProfesoresPorAsignatura();
	 SortedMap<Profesor, SortedSet<Tutoria>> getTutoriasPorProfesor();
	 
	 
	 Profesor getProfesorMaximaDedicacionMediaPorAsignatura();
}


