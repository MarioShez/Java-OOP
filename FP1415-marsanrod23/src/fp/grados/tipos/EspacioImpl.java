package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionEspacioNoValido;

public class EspacioImpl implements Espacio{
	
	private String nombre;
	private Integer capacidad;
	private Integer planta;
	private TipoEspacio tipo;
	
	public EspacioImpl(String s){
		//1. trocear la cadena
		String[] trozos = s.split(",");
		
		//2. checkear que el numero de valores es correcto
		if(trozos.length != 4){
			throw new IllegalArgumentException();
		}
		checkCapacidad(new Integer(trozos[2].trim()));
		
		//3. hacer una copia de los valores que hay en esa cadena
		this.nombre = trozos[0].trim();
		this.planta = new Integer(trozos[1].trim());
		this.capacidad = new Integer(trozos[2].trim());
		if(trozos.length == 4){
			this.tipo = TipoEspacio.valueOf(trozos[3].trim());
		}
		
	}
	
	
	public EspacioImpl(TipoEspacio tipo, String nombre, Integer capacidad, Integer planta) {
		checkCapacidad(capacidad);
		this.tipo = tipo;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.planta = planta;
		
		
	}
	
	private void checkCapacidad(Integer capacidad){
		if(capacidad <= 0){
			throw new ExcepcionEspacioNoValido();
		}
	}
	
	
	@Override
	public String getNombre() {
		return nombre;
	}
	
	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;	
	}
	
	@Override
	public Integer getCapacidad() {
		return capacidad;
	}
	
	@Override
	public void setCapacidad(Integer capacidad) {
		checkCapacidad(capacidad);
		this.capacidad = capacidad;
	}
	
	@Override
	public Integer getPlanta() {
		return planta;
	}
	
	@Override
	public TipoEspacio getTipo() {
		return tipo;
	}
	
	@Override
	public void setTipo(TipoEspacio tipo) {
		this.tipo = tipo;
		
	}
	
	public boolean equals(Object o){
		boolean r = false;
		if(o instanceof Espacio){
		Espacio e = (Espacio) o;
		r = this.getNombre().equals(e.getNombre()) && this.getPlanta().equals(e.getPlanta());
		}
		return r;
	}
	
	
	public int hashCode(){
		return getNombre().hashCode() + getPlanta().hashCode()*1;
	}
	
	
	public int compareTo(Espacio e) {
		int r;
		if(e==null){
		throw new NullPointerException();
		}
		r = getPlanta().compareTo(e.getPlanta());
		if (r == 0) {
			r = getNombre().compareTo(e.getNombre());
		}
		return r;
	}
	
	
	@Override
	public String toString() {
		return getNombre() + " (PLANTA" + getPlanta() + ")";
	}

	

	
	

	
	
	
	
	
	
	
	
	
	
	
	
}
