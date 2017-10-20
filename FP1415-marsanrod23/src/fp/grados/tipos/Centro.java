package fp.grados.tipos;

import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

public interface Centro extends Comparable<Centro>{
	String getNombre();

	String getDireccion();

	Integer getNumeroPlantas();

	Integer getNumeroSotanos();
	
	SortedMap<Profesor, Despacho> getDespachosPorProfesor();

	//Para hacer en casa
	// ¡CUIDADO! Aquí no hay una relación bidireccional
	// (no hay que hacer nada en el tipo Espacio)
	//
	 Set<Espacio> getEspacios();
	 void nuevoEspacio(Espacio e);
	 void eliminaEspacio(Espacio e);
	 
	 Set<Despacho> getDespachos();
	 Set<Despacho> getDespachos(Departamento d);
	 Set<Profesor> getProfesores();
	 Set<Profesor> getProfesores(Departamento d);
	 Espacio getEspacioMayorCapacidad();
	 Integer[] getConteosEspacios();
	 
	 SortedSet<Espacio> getEspaciosOrdenadosPorCapacidad();
	 
	
	 
}
