package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionNotaNoValida;

public final class NotaInmutableImpl implements Nota{
	
	
	private final Asignatura asignatura;
	private final Integer cursoAcademico;
	private final Convocatoria convocatoria;
	private final Double valor;
	private final Boolean mencionHonor;
	
	
	//b10
	public NotaInmutableImpl(String s){
		//1. trocear la cadena
		String[] trozos = s.split(";");
		
		// 2. checkear que el numero de valores es correcto
		if (trozos.length != 5) {
			throw new IllegalArgumentException();
		}
		
		//3. hacer una copia de los valores que hay en esa cadena
		Asignatura asignatura = new AsignaturaImpl(trozos[0].trim());
		Integer cursoAcademico = new Integer(trozos[1].trim());
		Convocatoria convocatoria = Convocatoria.valueOf(trozos[2].trim()) ;
		Double valor = new Double (trozos[3].trim());
		Boolean mencionHonor = new Boolean(trozos[4].trim());
		
		//4. chekear las restricciones del tipo
		checkValor(valor);
		checkMencionHonor(mencionHonor, valor);
		
		//5. copiar en los atributos
		this.asignatura = asignatura;
		this.cursoAcademico = cursoAcademico;
		this.convocatoria = convocatoria;
		this.valor = valor;
		this.mencionHonor = mencionHonor;
	}
	
	
	
	public NotaInmutableImpl(Asignatura asignatura, Integer cursoAcademico, Convocatoria convocatoria, Double valor, Boolean mencionHonor) {
		checkValor(valor);
		checkMencionHonor(mencionHonor, valor);
		this.asignatura = copia(asignatura);
		this.cursoAcademico = cursoAcademico;
		this.convocatoria = convocatoria;
		this.valor = valor;
		this.mencionHonor = mencionHonor;
	}
	
	private Asignatura copia(Asignatura a){
		Asignatura res = new AsignaturaImpl(a.getNombre(), a.getCodigo(), a.getCreditos(), a.getTipo(), a.getCurso(), a.getDepartamento());
		return res;
	}

	public NotaInmutableImpl(Asignatura asignatura, Integer cursoAcademico, Convocatoria convocatoria, Double valor) {
		checkValor(valor);
		this.asignatura = copia(asignatura);
		this.cursoAcademico = cursoAcademico;
		this.convocatoria = convocatoria;
		this.valor = valor;
		mencionHonor = false;
	}
	
	private static void checkValor(Double valor){
		if(valor < 0. || valor >10.){
			throw new ExcepcionNotaNoValida();
		}
	}
	
	private static void checkMencionHonor(Boolean mencionHonor, Double valor){
		if(valor < 9. && mencionHonor==true){
			throw new ExcepcionNotaNoValida();
		}
	}
	
	
	
	@Override
	public Asignatura getAsignatura() {
		return copia(asignatura);
	}


	@Override
	public Integer getCursoAcademico() {
		return cursoAcademico;
	}


	@Override
	public Convocatoria getConvocatoria() {
		return convocatoria;
	}


	@Override
	public Double getValor() {
		return valor;
	}


	@Override
	public Boolean getMencionHonor() {
		return mencionHonor;
	}


	@Override
	public Calificacion getCalificacion() {
		Calificacion c = null;
		Double v = getValor();
		
		if(v<5d){
			c = Calificacion.SUSPENSO;
			
		}else if(v>=5d && v<7d){
			c = Calificacion.APROBADO;
			
		}else if(v>=7d && v<9d){
			c = Calificacion.NOTABLE;
			
		}else{
			if(v>=9d && !mencionHonor){
				c = Calificacion.SOBRESALIENTE;

			}else{
				if(v>=9d && mencionHonor){
					c = Calificacion.MATRICULA_DE_HONOR;
				}
			}
		}
		
		return c;
	}
	
	
	public boolean equals(Object o){
		boolean r = false;
		if(o instanceof Nota){
		Nota e = (Nota) o;
		r = this.getCursoAcademico().equals(e.getCursoAcademico()) && this.getAsignatura().equals(e.getAsignatura()) && this.getConvocatoria().equals(e.getConvocatoria());
		}
		return r;
	}
	
	
	public int hashCode(){
		return getCursoAcademico().hashCode() + getAsignatura().hashCode()*1 + getConvocatoria().hashCode()*31;
	}
	
	
	public int compareTo(Nota e) {
		int r;
		if(e==null){
		throw new NullPointerException();
		}
		r = getCursoAcademico().compareTo(e.getCursoAcademico());
		if (r == 0) {
			r = getAsignatura().compareTo(e.getAsignatura());
			if (r == 0) {
				r = getConvocatoria().compareTo(e.getConvocatoria());
			}
		}
		return r;
	}
	
	
	
	
	
	public String toString(){
		String n = "0";
		  String res = "";
		  if (cursoAcademico % 100 < 9) {
		   res = n + String.valueOf(cursoAcademico % 100 + 1);
		  } else {
		   res = String.valueOf(cursoAcademico % 100 + 1);
		  }
		  if (cursoAcademico % 100 == 99) {
		   res = "00";
		  }
		  
		return getAsignatura() + ", " + getCursoAcademico() + "-" + res + "," + getConvocatoria() + ", " + getValor() + ", " + getCalificacion();
	}
	
	
	
	
	
	
	
	
	
}
