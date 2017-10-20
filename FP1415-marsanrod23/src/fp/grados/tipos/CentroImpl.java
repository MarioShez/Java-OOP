package fp.grados.tipos;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionCentroNoValido;
import fp.grados.excepciones.ExcepcionCentroOperacionNoPermitida;

public class CentroImpl implements Centro{

	
	private String nombre;
	private String direccion;
	private Integer numeroPlantas;
	private Integer numeroSotanos;
	private Set<Espacio> espacios;
	
	
	
								/******** CONSTRUCTOR******/
	public CentroImpl(String nombre, String direccion, Integer numeroPlantas, Integer numeroSotanos) {
		checkNumeroPlantas(numeroPlantas);
		checkNumeroSotanos(numeroSotanos);
		this.nombre = nombre;
		this.direccion = direccion;
		this.numeroPlantas = numeroPlantas;
		this.numeroSotanos = numeroSotanos;
		this.espacios = new HashSet<Espacio>();
	}
	
	
	
								/******** RESTRICCIONES******/
	//Un centro puede tener cero o más sótanos. Si no se cumple esta restricción, lance la excepción ExcepcionCentroNoValido.
	private void checkNumeroSotanos(Integer sotanos){
		if(sotanos<0){
			throw new ExcepcionCentroNoValido();
		}
	}
	
	 //Un centro debe tener al menos una planta. Si no se cumple esta restricción, lance la excepción ExcepcionCentroNoValido.
	 private void checkNumeroPlantas(Integer plantas){
		if(plantas<1){
			throw new ExcepcionCentroNoValido();
		}
	}

	
	
	 								/******** METODOS ******/
	 	//b6
		//Añade el espacio e al centro, siempre que la planta del espacio sea un número de planta válido en el centro. 
	 	//Para ello debe estar comprendida en el intervalo [-s, p-1], siendo p el número de plantas del centro 
	 	//y s el número de sótanos. En caso contrario, lance la excepción ExcepcionCentroOperacionNoPermitida.
		public void nuevoEspacio(Espacio e) {
			Integer p = e.getPlanta();
			  if (p >= (-getNumeroSotanos()) && p <= (getNumeroPlantas())-1 ) {
			   espacios.add(e);
			  } else
			   throw new ExcepcionCentroOperacionNoPermitida();
		}

		//b6
		//Elimina el espacio e del centro. Si el espacio no pertenece al centro, la operación no tiene efecto.	
		public void eliminaEspacio(Espacio e) {
			// 1.3 EL IMINAR LA ENTIDAD DE LA LISTA
			espacios.remove(e);
		}

		
		
		
		
								/******** GET & SET ******/
		//b8
		//Devuelve un conjunto con todos los espacios de tipo Despacho que hay en el centro.
		public Set<Despacho> getDespachos() {
			Set<Despacho> res = new HashSet<Despacho>();//parto de una coleccion vacia
			
			for(Espacio e: getEspacios()){//recorro todos los espacios 
				if(e instanceof Despacho){//instanceof devuleve true si e es del tipo despacho
					res.add((Despacho)e);
				}
			}
			return res;
		}

		//b8
		//Devuelve un conjunto con todos los despachos del centro donde hay al menos un profesor del departamento d.
		public Set<Despacho> getDespachos(Departamento d) {
			Set<Despacho> res = new HashSet<Despacho>();
			
			for(Despacho despacho: getDespachos()){
				Set<Profesor> profsDpto = d.getProfesores();
				Set<Profesor> profsDespacho = despacho.getProfesores();
				
				profsDespacho.retainAll(profsDpto);//interseccion
				
				if(!profsDespacho.isEmpty()){
					res.add(despacho);
				}
			}
			return res;
		}

		
		//b8
		//Devuelve un array de 5 elementos de tipo Integer que representan el número de espacios de tipo aula de teoría, 
		//laboratorio, seminario, aula de examen u otro tipo, respectivamente, que hay en el centro.
		public Integer[] getConteosEspacios() {
			Integer[] res = new Integer[TipoEspacio.values().length];
			Arrays.fill(res, 0);
			
			for(Espacio e: getEspacios()){
				res[e.getTipo().ordinal()]++;//metodo ordinal devuelve entero con respecto a la posicion que ocupa[su es teoria el valor es 0, si es seminario sera 3...]
			}
			
			
			return res;
		}
		
		
		//b8
		//Devuelve el conjunto de todos los profesores que tienen un despacho en el centro.
	public Set<Profesor> getProfesores(){

		Set<Profesor> res = new HashSet<Profesor>();// parto de una cadenavacia
													// de profesores

		for (Despacho d : getDespachos()) {
			res.addAll(d.getProfesores());
		}
		return res;

	}

		
	// Devuelve el conjunto de todos los profesores que pertenecen al
	// departamento d y tienen un despacho en el centro.
	public Set<Profesor> getProfesores(Departamento d) {

		Set<Profesor> res = new HashSet<Profesor>();// parto de una cadenavacia
													// de profesores

		for (Profesor p : d.getProfesores()) {// bucle de despachos
			if (p.getDepartamento()==d) {
				res.add(p);
			}

		}
		return res;
	}

		//Devuelve el espacio con mayor capacidad del centro.
		public Espacio getEspacioMayorCapacidad() {
			Espacio res = new EspacioImpl(null, null, 1, null);
			for(Espacio e : getEspacios()){
				if(res == null || res.getCapacidad() < e.getCapacidad()){
					res = e;
				}
			}if(res == null){
				throw new ExcepcionCentroOperacionNoPermitida();
			}
			
			return res;
		}
		
		
		

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public String getDireccion() {
		return direccion;
	}

	@Override
	public Integer getNumeroPlantas() {
		return numeroPlantas;
	}

	@Override
	public Integer getNumeroSotanos() {
		return numeroSotanos;
	}
	
	public Set<Espacio> getEspacios() {
		return new HashSet<Espacio>(espacios);
	}


	
	
										/******** EQUALS... ******/
	
	public int compareTo(Centro o){
		return getNombre().compareTo(o.getNombre());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}
	
	public boolean equals(Object o){
		boolean res = false;
		if(o instanceof Centro){
			Centro c = (Centro) o;
			res= getNombre().equals(c.getNombre());
		}
		return res;
	}
	
	
	
									/******** toString ******/
	public String toString(){
		return getNombre();
	}


	

	

	//b9
	public SortedMap<Profesor, Despacho> getDespachosPorProfesor() {
		SortedMap<Profesor, Despacho> res = new TreeMap<Profesor, Despacho>();
		for(Despacho d: getDespachos()){
			actulaizaMapProfesorDespacho(d.getProfesores(), d, res);
		}
		return res;
	}


	//b9
	private void actulaizaMapProfesorDespacho(Set<Profesor> profesores,Despacho d, SortedMap<Profesor, Despacho> res) {
		for(Profesor p: profesores){
			res.put(p, d);
		}
		
	}



	@Override
	public SortedSet<Espacio> getEspaciosOrdenadosPorCapacidad() {
		Comparator<Espacio> cmp = Comparator.comparing(Espacio::getCapacidad).reversed().thenComparing(Comparator.naturalOrder());
		
		SortedSet<Espacio> res = new TreeSet<Espacio>(cmp);
		res.addAll(getEspacios());
		
		return res;
	}

	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
