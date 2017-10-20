package fp.grados.tipos;

import java.time.LocalDate;

public interface Persona extends Comparable<Persona>{
	
	String getDNI();
	void setDNI(String dni);
	
	String getNombre();
	void setNombre(String nombre);
	
	String getApellidos();
	void setApellidos(String apellidos);
	
	LocalDate getFechaNacimiento();
	void setFechaNacimiento(LocalDate fechaNacimiento);
	
	String getEmail();
	void setEmail(String email);
	
	Integer getEdad();

	
}
