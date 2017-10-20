package fp.grados.tipos;

import java.util.Arrays;
import java.util.List;

import fp.grados.excepciones.ExcepcionAsignaturaNoValida;

public class AsignaturaImpl implements Asignatura{
	
	
	private String nombre;
	private  String codigo;
	private Double creditos;
	private TipoAsignatura tipo;
	private Integer curso;
	private Departamento departamento;
	
	
											/******** CONSTRUCTOR******/
	public AsignaturaImpl(String nombre, String codigo, Double creditos, TipoAsignatura tipo, Integer curso, Departamento departamento) {
		checkCodigo(codigo);
		checkCreditos(creditos);
		checkCurso(curso);
		
		this.nombre = nombre;
		this.codigo = codigo;
		this.creditos = creditos;
		this.tipo = tipo;
		this.curso = curso;
		//DELEGO LA INICIALIZACION EN EL SET
		setDepartamento(departamento);
	}
	
	public AsignaturaImpl(String s){
		// 1. trocear la cadena
		List<String> trozos = Arrays.asList(s.split("#"));//otra forma de hacerlo, esta vez con una lista

		// 2. checkear que el numero de valores es correcto
		if (trozos.size() != 5) {
			throw new IllegalArgumentException();
		}

		// 3. hacer una copia de los valores que hay en esa cadena
		String nombre = trozos.get(0).trim();
		String codigo = trozos.get(1).trim();
		Double creditos = new Double(trozos.get(2).trim());
		TipoAsignatura tipo = TipoAsignatura.valueOf(trozos.get(3).trim());
		Integer curso = new Integer(trozos.get(4).trim());

		// 4. chekear las restricciones del tipo
		checkCodigo(codigo);
		checkCreditos(creditos);
		checkCurso(curso);

		// 5. copiar en los atributos
		this.nombre = nombre;
		this.codigo = codigo;
		this.creditos = creditos;
		this.tipo = tipo;
		this.curso = curso;
	}

	
										/******** RESTRICCIONES******/
	private void checkCurso(Integer curso){
		if(curso<1 || curso>4){
			throw new ExcepcionAsignaturaNoValida();
		}
	}
	
	private void checkCodigo(String codigo){
		if(codigo.length()!=7){
			throw new ExcepcionAsignaturaNoValida();
		}
		
		try{
			new Integer(codigo);
		}catch (NumberFormatException c){
			throw new ExcepcionAsignaturaNoValida();
			
		}
		
	}
	
	private void checkCreditos(Double creditos){
		if(creditos<=0){
			throw new ExcepcionAsignaturaNoValida("El  numero de creditos no es valido");
		}
	}
	
	
									/******** GET & SET ******/
	@Override
	public String getNombre() {
		return nombre;
	}

	
	//b8
	//se construye tomando las letras mayúsculas que 
	//aparecen en el nombre de una asignatura. Por ejemplo, el acrónimo de “Fundamentos de Programación” es “FP”.
	public String getAcronimo() {
		//defino la variable acumuladora con un valor inicial
		String res= "";
		String nombre = getNombre();
		//recorro el String nombre 
		for(Character c: nombre.toCharArray()){//paso el String a Array para recorrerlo
			//filtro
			if(Character.isUpperCase(c)){//metodo para sacar las mayusculas
				res = res + c;//actualiza la variable acumuladora
			}
		}
		
		return res;
	}

	@Override
	public String getCodigo() {
		return codigo;
	}

	@Override
	public Double getCreditos() {
		return creditos;
	}
		

	@Override
	public TipoAsignatura getTipo() {
		return tipo;
	}

	@Override
	public Integer getCurso() {
		return curso;
	}
	
	//b6
	public Departamento getDepartamento() {
		return departamento;
	}

	
	//b6
	//2 TIENE QUE MDIFICAR LA INFORMACION DEL ELEMENTO MULTIPLE
	public void setDepartamento(Departamento newDpto) {
		//2.1 TOMAR ELEMENTO ACTUAL QUE SE VA A CAMBIAR
		Departamento oldDpto = this.departamento;
		
	//2.2 CHEKEAR IDENTIDAD(LO NUEVO DEBE SER DISTINTO)
		if(newDpto != oldDpto){
			//2.3 ACTUALIZAR LA PROPIEDAD CON EL VALOR NUEVO
			this.departamento = newDpto;
			
			//2.4 ELIMINARME DEL OBJETO UNICO AL QUE PERTENEZCO
			if(oldDpto != null){
				oldDpto.eliminaAsignatura(this);
			}
			
			//2.5 AÑADIRME AL NUEVO OBJETO UNICO AL QUE PERTENEZCO
			if(newDpto != null){
				newDpto.nuevaAsignatura(this);
			}
		}
		
	}
	
									/******** EQUALS... ******/
	public boolean equals(Object o){
		boolean r = false;
		if(o instanceof Asignatura){
		Asignatura a = (Asignatura) o;
		r = this.getCodigo().equals(a.getCodigo());
		}
		return r;
	}
	
	
	public int hashCode(){
		return getCodigo().hashCode();
	}
	
	
	public int compareTo(Asignatura o) {
		//Orden natural de codigo
		String codigo1 = getCodigo();
		String codigo2 = o.getCodigo();
		
		return codigo1.compareTo(codigo2);
	}
	
	
	
											/******** toString ******/
	@Override
	public String toString() {
		return  "(" + getCodigo() + ") " + getNombre();
	}


	
	
}
