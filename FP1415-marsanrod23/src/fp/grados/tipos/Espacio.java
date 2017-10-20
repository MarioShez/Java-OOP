package fp.grados.tipos;

public interface Espacio extends Comparable<Espacio>{
	
	String getNombre();
	void setNombre(String nombre);
	
	Integer getCapacidad();
	void setCapacidad(Integer capacidad);
	
	Integer getPlanta();
	
	TipoEspacio getTipo();
	void setTipo(TipoEspacio tipo);

}
