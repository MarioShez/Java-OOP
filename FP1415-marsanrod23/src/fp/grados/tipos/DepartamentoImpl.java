	package fp.grados.tipos;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Predicate;


public class DepartamentoImpl implements Departamento{
	
	private String nombre;
	private Set<Asignatura> asignaturas;
	private Set<Profesor> profesores;
	
	
										/******** CONSTRUCTOR******/
	public DepartamentoImpl(String nombre) {
		this.nombre = nombre;
		this.asignaturas = new HashSet<Asignatura>();
		this.profesores = new HashSet<Profesor>();
	}

	

	
	
											/******** METODOS B6******/	
	//Añade la asignatura asig al departamento. Este método debe actualizar la propiedad departamento de la asignatura asig.
	//1. ACTUALIZAR INFORMACION DE LA ENTIDAD UNICA(SOLO 1)
	public void nuevaAsignatura(Asignatura a) {
		//1.1 AÑADIR INFORMACION
		asignaturas.add(a);
		//1.2 ACTUALIZAR LA INFORMACION DEL ELEMENTO AÑADIDO
		a.setDepartamento(this);
	}
	

	//Elimina la asignatura asig del departamento. Si la asignatura no es impartida por el departamento, la operación no tiene efecto. 
	//Este método debe actualizar la propiedad departamento de la asignatura asig.
	public void eliminaAsignatura(Asignatura a) {
		//1.3 EL IMINAR LA ENTIDAD DE LA LISTA
		asignaturas.remove(a);
		//1.4 ELIMINA LA RELACION QUE EXISTE CON EL ELEMENTO
		a.setDepartamento(null);
		
	}
	
	//Añade el profesor prof al departamento. Este método debe actualizar la propiedad departamento del profesor prof.
	public void nuevoProfesor(Profesor p) {
		// 1.1 AÑADIR INFORMACION
		profesores.add(p);
		// 1.2 ACTUALIZAR LA INFORMACION DEL ELEMENTO AÑADIDO
		p.setDepartamento(this);
	}
	
	
	//Elimina el profesor prof del departamento. Si el profesor no pertenece al departamento, la operación no tiene efecto. 
	//Este método debe actualizar la propiedad departamento del profesor prof.
	public void eliminaProfesor(Profesor p){
		// 1.3 EL IMINAR LA ENTIDAD DE LA LISTA
		profesores.remove(p);
		// 1.4 ELIMINA LA RELACION QUE EXISTE CON EL ELEMENTO
		p.setDepartamento(null);
	}
	
		
	//b8
	//Elimina las tutorías de todos los profesores del departamento.
	public void borraTutorias() {
		for(Profesor p:getProfesores()){
			p.borraTutorias();
		}
		
	}
	
	
	//b8
	//Elimina las tutorías de todos los profesores del departamento cuya categoría es c.
	public void borraTutorias(Categoria c) {
		for(Profesor p : getProfesores()){//recorro los profesores del departamento
			if(p.getCategoria() == c){//si la categoria del profesor es c...
				p.borraTutorias();//elimino la tutorias de los profesores
			}
		}
		
	}


	//b8
	//Devuelve true si existe al menos un profesor asignado a la asignatura a, es decir, 
	//que imparte algún crédito en ella, y false en caso contrario.
	public Boolean existeProfesorAsignado(Asignatura a) {
		Boolean res = false;
		
		for(Profesor p: getProfesores()){
			res = p.getAsignaturas().contains(a);
			if(res) {
				break;
			}
		}
		return res;
	}
	
	
	//Devuelve true si todas las asignaturas tienen asignado al menos un profesor, y false en caso contrario.
	public Boolean estanTodasAsignaturasAsignadas() {
		Boolean res = true;
		for(Asignatura a : getAsignaturas()){
				if(!existeProfesorAsignado(a)){
					res = false;
				}
		}
		return res;
	}
	

	//Elimina la asignatura a de todos los profesores del departamento que la están impartiendo.
	public void eliminaAsignacionProfesorado(Asignatura a) {
		for(Profesor p : profesores){
			if(p.getAsignaturas().contains(a) && p.dedicacionAsignatura(a)>0){
				p.eliminaAsignatura(a);
			}
		
		}
		
	}
	
	
										/******** GET & SET ******/
	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public Set<Asignatura> getAsignaturas() {
		return new HashSet<Asignatura>(asignaturas);
	}
	
	@Override
	public Set<Profesor> getProfesores() {
		return new HashSet<Profesor>(profesores);
	}
	
	
										/******** EQUALS... ******/
	public int compareTo(Departamento o){
		return getNombre().compareTo(o.getNombre());
	}
	
	@Override
	public int hashCode() {
		return getNombre().hashCode();
	}
	
	public boolean equals(Object o){
		boolean res = false;
		if(o instanceof Departamento){
			Departamento d = (Departamento) o;
			res= getNombre().equals(d.getNombre());
		}
		return res;
	}
	
	
										/******** toString ******/
	public String toString(){
		return getNombre();
	}





	@Override
	public SortedMap<Asignatura, SortedSet<Profesor>> getProfesoresPorAsignatura() {
		SortedMap<Asignatura, SortedSet<Profesor>> res = new TreeMap<Asignatura, SortedSet<Profesor>>();
		for(Profesor p: getProfesores()){
			actualizaMapAsignaturaProfesores(p.getAsignaturas(), p, res);
		}
		return res;
	}




	//b9
	private void actualizaMapAsignaturaProfesores(List<Asignatura> asignaturas, Profesor p, SortedMap<Asignatura, SortedSet<Profesor>> res) {
		for(Asignatura key : asignaturas){
			if(res.containsKey(key)){
				res.get(key).add(p);
			}else{
				SortedSet<Profesor> value = new TreeSet<Profesor>();
				value.add(p);
				res.put(key, value);
			}
		}
		
		
	}


	@Override
	public Profesor getProfesorMaximaDedicacionMediaPorAsignatura() {
		Set<Profesor> prof = getProfesores();
		Comparator<Profesor> cmp = Comparator.comparing(x->x.getDedicacionTotal() / x.getAsignaturas().size());
		Predicate<Profesor> pred = x->!x.getAsignaturas().isEmpty();
		if(prof.isEmpty()){
			throw new NoSuchElementException();
		}
		return prof.stream().filter(pred).max(cmp).get();
	}





	//b9
	//Devuelve un SortedMap<Profesor, SortedSet<Tutoria>> que hace corresponder a cada profesor con el conjunto de tutorías que 
	//tiene. Para construir el SortedMap, comience por recorrer los profesores del departamento y, para cada uno, añada al 
	//SortedMap la pareja formada por el profesor y su conjunto de tutorías.
	public SortedMap<Profesor, SortedSet<Tutoria>> getTutoriasPorProfesor() {
		SortedMap<Profesor, SortedSet<Tutoria>> res = new TreeMap<Profesor, SortedSet<Tutoria>>();
		for(Profesor p: getProfesores()){
			actualizaMapProfesorPorTutorias(p.getTutorias(), p, res);
		}
		return res;
	}


	//b9
	private void actualizaMapProfesorPorTutorias(SortedSet<Tutoria> tutorias, Profesor p, SortedMap<Profesor, SortedSet<Tutoria>> res) {

		if(!res.containsKey(p)){
			res.put(p,  p.getTutorias());
		}else{
			res.get(p).addAll(p.getTutorias());
		}
	
	}





										





										



	




	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
