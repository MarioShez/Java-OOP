	package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionBecaNoValida;

public class BecaImpl implements Beca{
	
	private String codigo;
	private Double cuantiaTotal;
	private Integer duracion;
	private TipoBeca tipo;
	
	private static final Double CUANTIA_MINIMA =1500.;
	//private static final Integer DURACION_MINIMA =1;
	
	public BecaImpl(String s){
		//1. trocear la cadena
		String[] trozos = s.split(",");
		
		//2. checkear que el numero de valores es correcto
		if(trozos.length != 4){
			throw new IllegalArgumentException();
		}
		
		//3. hacer una copia de los valores que hay en esa cadena
		String codigo = trozos[0].trim();
		Double cuantiaTotal = new Double(trozos[1].trim());
		Integer duracion = new Integer(trozos[2].trim());
		TipoBeca tipo = TipoBeca.valueOf(trozos[3].trim());
		
		//4. chekear las restricciones del tipo
		checkCodigo(codigo);
		checkCuantiaTotal(cuantiaTotal);
		checkDuracion(duracion);
		
		//5. copiar en los atributos
		this.codigo = codigo;
		this.cuantiaTotal = cuantiaTotal;
		this.duracion = duracion;
		this.tipo = tipo;
	}
	
	public BecaImpl(String codigo, TipoBeca tipo){
		checkCodigo(codigo);
		this.codigo = codigo;
		this.tipo = tipo;
		cuantiaTotal = 1500.0;
		duracion = 1;
	}
	
	public BecaImpl(String codigo, Double cuantiaTotal, Integer duracion, TipoBeca tipo) {
		checkCodigo(codigo);
		checkCuantiaTotal(cuantiaTotal);
		checkDuracion(duracion);
	
		this.codigo = codigo;
		this.cuantiaTotal = cuantiaTotal;
		this.duracion = duracion;
		this.tipo = tipo;
	}

	
	private void checkDuracion(Integer duracion2){
		if(duracion2<1){
			throw new ExcepcionBecaNoValida();
		}
	}
	
	private void checkCodigo(String codigo2){
		if(codigo2.length()<7){
			throw new ExcepcionBecaNoValida("pocos caracteres en el codigo");
		}
		if(codigo2.length()>7){
			throw new ExcepcionBecaNoValida("demasiados caracteres en el codigo");
		}
		for(int i=0; i<3; i++){
			if(!Character.isLetter(codigo2.charAt(i))){
				throw new ExcepcionBecaNoValida("el caracter de la posicion " + i + " no es valido");
			}
		}
		for(int e=3; e<=6; e++){
			if(!Character.isDigit(codigo2.charAt(e))){
				throw new ExcepcionBecaNoValida("el caracter de la posicion " + e + " no es valido");
			}
		}
	}
	
	private void checkCuantiaTotal(Double cuantiaTotal2){
		if(cuantiaTotal2<CUANTIA_MINIMA){
			throw new ExcepcionBecaNoValida();
		}
	}

	@Override
	public String getCodigo() {
		return codigo;
	}

	@Override
	public Double getCuantiaTotal() {
		return cuantiaTotal;
	}

	@Override
	public void setCuantiaTotal(Double cuantiaTotal) {
		checkCuantiaTotal(cuantiaTotal);
		this.cuantiaTotal = cuantiaTotal;
		
	}

	@Override
	public Integer getDuracion() {
		return duracion;
	}

	@Override
	public void setDuracion(Integer duracion) {
		checkDuracion(duracion);
		this.duracion = duracion;
		
	}

	@Override
	public TipoBeca getTipo() {
		return tipo;
	}

	@Override
	public Double getCuantiaMensual() {
		return getCuantiaTotal()/getDuracion();
	}
	
	
	public boolean equals(Object o){
		boolean r = false;
		if(o instanceof Beca){
		Beca b = (Beca) o;
		r = this.getCodigo().equals(b.getCodigo()) && this.getTipo().equals(b.getTipo());
		}
		return r;
	}
	
	
	public int hashCode(){
		return getCodigo().hashCode() + getTipo().hashCode()*31;
	}
	
	
	public int compareTo(Beca o) {
		int r;
		
		//mismo c�digo
		String codigo1 = getCodigo();
		String codigo2 = o.getCodigo();
		r = codigo1.compareTo(codigo2);
		
		//mismo tipo de beca
		if(r==0){
			TipoBeca tipo1 = getTipo();
			TipoBeca tipo2 = o.getTipo();
			r = tipo1.compareTo(tipo2);
		}
		return r;
	}
	
	
	
	
	@Override
	public String toString() {
		return "[" + getCodigo() + ", " + getTipo() + "]";
	}
	
	
	

}
