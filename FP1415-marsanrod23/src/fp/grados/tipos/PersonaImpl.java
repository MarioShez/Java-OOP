
package fp.grados.tipos;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import fp.grados.excepciones.ExcepcionPersonaNoValida;

public class PersonaImpl implements Persona{
	
	private String dni;
	private String nombre;
	private String apellidos;
	private LocalDate fechaNacimiento;
	private String email;
	
	
	//b10
	public PersonaImpl(String s){
		//1. trocear la cadena
		String[] trozos = s.split(",");
		
		//2. checkear que el numero de valores es correcto
		if(trozos.length != 5){
			throw new IllegalArgumentException();
		}
		
		//3. hacer una copia de los valores que hay en esa cadena
		String dni = trozos[0].trim();
		String nombre= trozos[1].trim();
		String apellidos =trozos[2].trim();
		LocalDate fechaNacimiento = LocalDate.parse(trozos[3].trim(), DateTimeFormatter.ofPattern("d/M/y"));//el parse transforma String a LocalDate
		String email = trozos[4].trim();
		
		//4. chekear las restricciones del tipo
		checkDNI(dni);
		checkEmail(email);
		
		//5. copiar en los atributos
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
	}
	
	

	public PersonaImpl(String dni, String nombre, String apellidos, 
			LocalDate fechaNacimiento, String email) {
		checkDNI(dni);
		checkEmail(email);
		
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
	}
	
	public PersonaImpl(String dni, String nombre, String apellidos, 
			LocalDate fechaNacimiento) {
		checkDNI(dni);
		
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		email = "";
	}
	
	
	private void checkEmail(String email){
		if(!email.contains("@") && email != ""){
			throw new ExcepcionPersonaNoValida();
		}
	}
	
	private void checkDNI(String dni){
		
		Boolean res = false;
		res = dni.length()==9 &&
				Character.isDigit(dni.charAt(0)) &&
				Character.isDigit(dni.charAt(1)) &&
				Character.isDigit(dni.charAt(2)) &&
				Character.isDigit(dni.charAt(3)) &&
				Character.isDigit(dni.charAt(4)) &&
				Character.isDigit(dni.charAt(5)) &&
				Character.isDigit(dni.charAt(6)) &&
				Character.isDigit(dni.charAt(7)) &&
				Character.isLetter(dni.charAt(8));
		
		if(res){
			String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
			Integer numero = new Integer(dni.substring(0, 8));
			res = dni.charAt(8) == letras.charAt(numero%23);
		}if(!res){
			throw new ExcepcionPersonaNoValida();	
		}
		
	}

	@Override
	public String getDNI() {
		return dni;
	}

	@Override
	public void setDNI(String dni) {
		checkDNI(dni);
		this.dni = dni;
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
	public String getApellidos() {
		return apellidos;
	}

	@Override
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
		
	}

	@Override
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	@Override
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		checkEmail(email);
		this.email = email;
	}

	
	@Override
	public Integer getEdad() {
		LocalDate ahora = LocalDate.now();
		LocalDate nacimiento = getFechaNacimiento();
		
		Period trancurrido = nacimiento.until(ahora);//until = calcula la diferencia en tiempo
		Long numeroMeses = trancurrido.toTotalMonths();
		
		return (int) (numeroMeses/12);
	}

	
	
	public boolean equals(Object o){
		boolean r = false;
		if(o instanceof Persona){
		Persona p = (Persona) o;
		r = this.getDNI().equals(p.getDNI()) && this.getNombre().equals(p.getNombre()) && this.getApellidos().equals(p.getApellidos());
		}
		return r;
	}
	
	
	public int hashCode(){
		return getDNI().hashCode() + getNombre().hashCode()*31 + getApellidos().hashCode()*31*31;
	}
	
	
	public int compareTo(Persona o) {
		int r;
		
		//mismo Apellidos
		r = getApellidos().compareTo(o.getApellidos());
		
		//mismo nombre
		if(r==0){
			r = getNombre().compareTo(o.getNombre());
		
			//mismo DNI	
			if(r==0){
				r = getDNI().compareTo(o.getDNI());
			}
		
		}
		return r;
	}
	
	
	@Override
	public String toString() {
		return getDNI() + " - " + getApellidos() + ", " + getNombre() + " - " + getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	
	
	
	
	
	
	
}
