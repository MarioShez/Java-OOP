package fp.grados.excepciones;

public class ExcepcionCentroOperacionNoPermitida extends RuntimeException{
private static final long serialVersionUID = 1L;
	
	public ExcepcionCentroOperacionNoPermitida(){
		
	}
	
	public ExcepcionCentroOperacionNoPermitida(String s){
		super(s);
	}
}
