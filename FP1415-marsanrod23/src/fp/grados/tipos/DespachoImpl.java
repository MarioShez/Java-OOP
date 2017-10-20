package fp.grados.tipos;

import java.util.HashSet;
import java.util.Set;

import fp.grados.excepciones.ExcepcionDespachoNoValido;


public class DespachoImpl extends EspacioImpl implements Despacho{

	private Set<Profesor> profesores;
	
	public DespachoImpl(String s){
		super(s + ",OTRO" );
		// 4. chekear las restricciones del tipo
		profesores = new HashSet<Profesor>();
	}
	
	
	public DespachoImpl(String nombre, Integer capacidad, Integer planta, Set<Profesor> profesores) {
		super(TipoEspacio.OTRO, nombre, capacidad, planta);
		this.profesores = profesores;
		checkCapacidad(capacidad);
	}

	public DespachoImpl(String nombre, Integer capacidad, Integer planta, Profesor profesor) {
		super(TipoEspacio.OTRO, nombre, capacidad, planta);
		this.profesores = new HashSet<Profesor>();
		profesores.add(profesor);
	}
	
	public DespachoImpl(String nombre, Integer capacidad, Integer planta) {
		super(TipoEspacio.OTRO, nombre, capacidad, planta);
		this.profesores = new HashSet<Profesor>();
	} 
	
	private void checkCapacidad(Integer capacidad){
		if(profesores.size() > capacidad){
			 throw new ExcepcionDespachoNoValido();
		 }
	}
	
	
	public void setTipo(TipoEspacio e){
		throw new UnsupportedOperationException();
	}
	
	
	@Override
	public Set<Profesor> getProfesores() {
		return new HashSet<Profesor> (this.profesores);
	}

	@Override
	public void setProfesores(Set<Profesor> profesores) {
		this.profesores = profesores;
		checkCapacidad(getCapacidad());
	}
	
	
	public String toString(){
		return super.toString() + getProfesores();
	}
	
	
	
	
	
	
}
