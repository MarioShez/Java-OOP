package fp.grados.excepciones;

public class ExcepcionExpedienteOperacionNoPermitida extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ExcepcionExpedienteOperacionNoPermitida(){
		
	}
	
	public ExcepcionExpedienteOperacionNoPermitida(String s){
		super(s);
	
	}
}